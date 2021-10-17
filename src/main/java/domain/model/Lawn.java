package domain.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Lawn {

    private int width;
    private int height;
    private Set<Mower> mowersInTheLawn;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
        mowersInTheLawn = new HashSet<>();

    }

    public Lawn() {
        mowersInTheLawn = new HashSet<>();
    }

    public void addMowers(Set<Mower> mowers) {
        this.mowersInTheLawn.addAll(mowers);
    }

    public void removeMowers(Set<Mower> mowers) {
        this.mowersInTheLawn.removeAll(mowers);
    }

    public boolean isAvailable(int x, int y) {
        Set<Mower> mowersInThePosition = mowersInTheLawn.stream().filter(mower -> mower.getX() == x && mower.getY() == y).collect(Collectors.toSet());
        return mowersInThePosition.isEmpty();

    }

}
