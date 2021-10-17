package infrastructure.adapter;

import infrastructure.filsystem.InputFileReader;
import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;
import domain.model.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class InputFileAdapterTest {

    private InputFileAdapter inputFileAdapter;

    @Mock
    private InputFileReader inputFileReader;

    @BeforeEach
    void setUp() {
        inputFileAdapter = new InputFileAdapter(inputFileReader);
    }

    @Test
    void retrieveMowerCommands() {

        List<String> mowerCommands = Arrays.asList(
                "12N",
                "LF",
                "33E",
                "FF");
        Map<Mower, List<Command>> mowerListMap = new HashMap<>();
        mowerListMap.put(new Mower(1, 2, Orientation.N), Arrays.asList(
                Command.L,
                Command.F
        ));
        mowerListMap.put(new Mower(3, 3, Orientation.E), Arrays.asList(
                Command.F,
                Command.F
        ));

        when(inputFileReader.getMowersCommands()).thenReturn(mowerCommands);
        assertThat(inputFileAdapter.retrieveMowerCommands()).isEqualTo(mowerListMap);

    }

    @Test
    void retrieveLawn() {
        when(inputFileReader.getLawn()).thenReturn(Optional.of("55"));
        assertThat(inputFileAdapter.retrieveLawn()).isEqualTo(new Lawn(5, 5));


        when(inputFileReader.getLawn()).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> inputFileAdapter.retrieveLawn());

        when(inputFileReader.getLawn()).thenReturn(Optional.of("123"));
        assertThrows(IllegalArgumentException.class, () -> inputFileAdapter.retrieveLawn());

    }
}