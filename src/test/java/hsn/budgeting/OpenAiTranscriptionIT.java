package hsn.budgeting;


import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OpenAi_Key", matches = ".*")
public class OpenAiTranscriptionIT {

    @Autowired
    OpenAiAudioTranscriptionModel transcriptionModel;

    @ParameterizedTest
    @CsvSource({
            "audio 01.m4a, 20 reais",
            "Audio 02.m4a, 200 reais",
            "Audio 03.m4a, 1000 reais",
    })
    void should_matchSomeKeyWords_whenAudioFileIsProcessed(String fileName, String keywords){

        var recording  = new ClassPathResource("audio/" + fileName);

        var response = transcriptionModel.call(recording);

        assertThat(response).contains(keywords);
        System.out.println(response);

    }




}
