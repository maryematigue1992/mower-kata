package domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LawnTest {

    private Lawn lawn;

    @BeforeEach
    void setUp(){
        lawn = new Lawn();
    }
    @Test
    void addMowers() {
        Mower mower = new Mower(1, 1, Orientation.N);
        Set<Mower> mowersInTheLawn = Collections.singleton(mower);
        lawn.addMowers(mowersInTheLawn);
        assertThat(lawn.getMowersInTheLawn()).isEqualTo(mowersInTheLawn);
    }

    @Test
    void removeMowers() {
        Mower mower = new Mower(1, 1, Orientation.N);
        Set<Mower> mowersInTheLawn = new HashSet<>();
        mowersInTheLawn.add(mower);
        lawn.setMowersInTheLawn(mowersInTheLawn);
        lawn.removeMowers(mowersInTheLawn);

        assertThat(lawn.getMowersInTheLawn()).isEqualTo(new HashSet<>());

    }

    @Test
    void isAvailable() {

        Mower mower = new Mower(1, 1, Orientation.N);
        Set<Mower> mowersInTheLawn = Collections.singleton(mower);
        lawn.addMowers(mowersInTheLawn);
        assertThat(lawn.isAvailable(1,1)).isFalse();

    }
}