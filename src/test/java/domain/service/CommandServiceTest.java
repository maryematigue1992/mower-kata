package domain.service;

import domain.model.Command;
import domain.model.Mower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.port.MowerProgramRetriever;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommandServiceTest {

    private CommandService commandService;

    @Mock
    private MowerProgramRetriever programRetriever;

    @BeforeEach
    void setUp() {
        commandService = new CommandService(programRetriever);
    }

    @Test
    void buildMowerCommands() {
        HashMap<Mower, List<Command>> mowerCommands = new HashMap<>();
        when(programRetriever.retrieveMowerCommands()).thenReturn(mowerCommands);
        assertThat(commandService.buildMowerCommands()).isEqualTo(mowerCommands);

    }
}