package domain.service;

import domain.model.Lawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.port.MowerProgramRetriever;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LawnServiceTest {
    private LawnService lawnService;

    @Mock
    private MowerProgramRetriever programRetriever;

    @BeforeEach
    void setUp() {
        lawnService = new LawnService(programRetriever);
    }

    @Test
    void buildLawn() {
        Lawn lawn = new Lawn();
        when(programRetriever.retrieveLawn()).thenReturn(lawn);
        assertThat(lawnService.buildLawn()).isEqualTo(lawn);

    }
}