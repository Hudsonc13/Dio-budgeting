package hsn.budgeting;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OpenAi_Key", matches = ".*")
public class ToolCallingIT {
    @Autowired
    OpenAiChatModel chatModel;


    public ToolCallingIT(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }


    static class MathTool {

        @Tool(description = "Soma a mais b")
        public int sum(int a, int b){
            return a + b;
        }

        @Tool(description = "subtrai a por b")
        public int diff(int a, int b){
            return a - b;
        }

    }

    @Test
    void should_executeSum_when_prompted(){

        var chatClient = ChatClient.builder(chatModel)
                .defaultSystem("Você é um matematico")
                .defaultTools(new MathTool())
                .build();

        var response = chatClient.prompt("Soma 10 mais 5 menos 3 exeiba apenas o resultado final")
                .call().content();

        assertThat(response).contains("12");
        System.out.println(response);
    }





}
