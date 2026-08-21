package hsn.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OpenAi_Key", matches = ".*")
public class OpenAiSpeechModelIT {

    @Autowired
    OpenAiAudioSpeechModel speechModel;


    @Test
    void shouldSpeech_a_response_aboutThePrompt() throws IOException {

        var response = speechModel.call("Meu nome é MD e eu dou o cu. Come meu cu Lucas vaii!");

        assertThat(response).isNotEmpty();

        var tempFile = Files.createTempFile("Audio_", ".mp3");
        Files.write(tempFile, response);
        System.out.println(tempFile.toAbsolutePath());

    }

}
