package hsn.budgeting.application.AI;

import hsn.budgeting.application.CreateTransactionUseCase;
import hsn.budgeting.infra.websocket.WebSocketAudioHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class AiPromptContextUseCase {

    private final TranscriptionModel transcriptionModel;
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;


    private static final Logger logger = LoggerFactory.getLogger(AiPromptContextUseCase.class);


    public AiPromptContextUseCase(TranscriptionModel transcriptionModel,
                                  @Value("classpath:/prompts/system.st") Resource systemPrompt,
                                  ChatClient.Builder chatClientBuilder, ObjectMapper objectMapper) throws IOException {
        this.transcriptionModel = transcriptionModel;
        this.objectMapper = objectMapper;
        this.chatClient = chatClientBuilder
                .defaultSystem(systemPrompt.getContentAsString(Charset.defaultCharset()))
                .defaultTools(new CreateTransactionUseCase())
                .build();
    }

    public String execute(Resource resource){
        var userPrompt = transcriptionModel.transcribe(resource);

        logger.info("Prompt: {}", userPrompt);

        var transactionsOutput = chatClient.prompt().user(userPrompt).call().content();

        return objectMapper.writeValueAsString(transactionsOutput);}

}
