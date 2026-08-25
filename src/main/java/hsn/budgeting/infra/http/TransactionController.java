package hsn.budgeting.infra.http;


import hsn.budgeting.application.ListTransactionsByCategoryUseCase;
import hsn.budgeting.application.PersisTransactionUseCase;
import hsn.budgeting.domain.Category;
import hsn.budgeting.infra.http.request.TransactionRequest;
import hsn.budgeting.infra.http.response.TransactionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {


    private final PersisTransactionUseCase transactionUseCase;
    private final ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase;
    private final TranscriptionModel transcptionModel;
    private final ChatClient chatClient;
    private final TextToSpeechModel textToSpeechModel;

    public TransactionController(PersisTransactionUseCase transactionUseCase,
                                 ListTransactionsByCategoryUseCase listTransactionsByCategoryUseCase,
                                 @Value("classpath:/prompts/system.st") Resource systemPrompt,
                                 TranscriptionModel transcptionModel,
                                 ChatClient.Builder chatClientBuilder, TextToSpeechModel textToSpeechModel) throws IOException {
        this.transactionUseCase = transactionUseCase;
        this.listTransactionsByCategoryUseCase = listTransactionsByCategoryUseCase;
        this.transcptionModel = transcptionModel;
        this.textToSpeechModel = textToSpeechModel;
        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(transactionUseCase, listTransactionsByCategoryUseCase)
                .build();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<TransactionResponse> createTransaction(@RequestBody List<TransactionRequest> request){
        var output = transactionUseCase.execute(request.stream().map(TransactionRequest::toInput).toList());

        return output.stream().map(TransactionResponse::from).toList();
    }

    @GetMapping("/{category}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> listTransactionByCategory(@PathVariable Category category){

        return listTransactionsByCategoryUseCase.execute(category).stream().map(TransactionResponse::from).toList();

    }

    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    ResponseEntity<Resource> transcribe(@RequestParam("file")MultipartFile file){

        var userPrompt = transcptionModel.transcribe(file.getResource());
        var result = chatClient.prompt().user(userPrompt).call().content();

        byte[] audio = textToSpeechModel.call(result);
        var resource = new ByteArrayResource(audio);



        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body(resource);
    }

}
