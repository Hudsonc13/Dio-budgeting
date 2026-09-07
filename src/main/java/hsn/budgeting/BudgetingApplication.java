package hsn.budgeting;

import hsn.budgeting.infra.websocket.WebSocketAudioHandler;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetingApplication {


    @Bean
    ChatClient buildChatClient(ChatClient.Builder builder){
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BudgetingApplication.class, args);
    }

}
