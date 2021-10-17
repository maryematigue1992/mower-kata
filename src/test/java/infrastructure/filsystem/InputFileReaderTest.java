package infrastructure.filsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputFileReaderTest {

    private InputFileReader inputFileReader;

    @BeforeEach
    void setUp() {
        inputFileReader = new InputFileReader();
    }

    @Test
    void getMowersCommands() {
        List<String> mowersCommands = inputFileReader.getMowersCommands();
        List<String> mowersCommandsExpected = Arrays.asList(
                "12N",
                "LFLFLFLFF",
                "33E",
                "FFRFFRFRRF");
        assertThat(mowersCommands).isEqualTo(mowersCommandsExpected);
    }

    @Test
    void getLawn() {

        Optional<String> lawn = inputFileReader.getLawn();
        String lawnExpected = "55";
        assertTrue(lawn.isPresent());
        assertThat(lawnExpected).isEqualTo(lawn.get());

    }
}