package domain.service;

import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;
import domain.model.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MowerServiceTest {

    MowerService mowerService;

    @BeforeEach
    void setUp() {
        mowerService = new MowerService();
    }

    @Test
    void right() {
        Mower mower = new Mower(1, 1, Orientation.S);
        mowerService.right(mower);
        assertThat(mower.getX()).isEqualTo(1);
        assertThat(mower.getY()).isEqualTo(1);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.W);
    }

    @Test
    void left() {
        Mower mower = new Mower(1, 1, Orientation.S);
        mowerService.left(mower);
        assertThat(mower.getX()).isEqualTo(1);
        assertThat(mower.getY()).isEqualTo(1);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.E);

    }

    @Test
    void forward() {
        Mower mower = new Mower(1, 1, Orientation.S);
        Lawn lawn = new Lawn(5, 5);
        mowerService.forward(mower, lawn);
        assertThat(mower.getX()).isEqualTo(1);
        assertThat(mower.getY()).isEqualTo(0);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.S);
        mowerService.forward(mower, lawn);
        assertThat(mower.getY()).isEqualTo(0);

    }

    @Test
    void move() {

        Mower mower = new Mower(0, 0, Orientation.N);
        Lawn lawn = new Lawn(5, 5);
        List<Command> commands = Arrays.asList(Command.R, Command.F, Command.F);

        mowerService.move(mower, lawn, commands);
        assertThat(mower.getX()).isEqualTo(2);
        assertThat(mower.getY()).isEqualTo(0);
        assertThat(mower.getOrientation()).isEqualTo(Orientation.E);
    }
}