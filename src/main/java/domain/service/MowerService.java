package domain.service;

import lombok.NoArgsConstructor;
import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;
import domain.model.Orientation;

import java.util.List;

@NoArgsConstructor
public class MowerService {

    public void right(Mower mower) {
        switch (mower.getOrientation()) {
            case N:
                mower.setOrientation(Orientation.E);
                break;
            case E:
                mower.setOrientation(Orientation.S);
                break;
            case S:
                mower.setOrientation(Orientation.W);
                break;
            case W:
                mower.setOrientation(Orientation.N);
                break;
        }
    }

    public void left(Mower mower) {
        switch (mower.getOrientation()) {
            case N:
                mower.setOrientation(Orientation.W);
                break;
            case W:
                mower.setOrientation(Orientation.S);
                break;
            case S:
                mower.setOrientation(Orientation.E);
                break;
            case E:
                mower.setOrientation(Orientation.N);
                break;
        }

    }

    public synchronized void forward(Mower mower, Lawn lawn) {
        Orientation orientation = mower.getOrientation();
        switch (orientation) {
            case N:
                if (mower.getY() < lawn.getHeight()
                        && lawn.isAvailable(mower.getX(), mower.getY() + 1)) {
                    mower.setY(mower.getY() + 1);
                }
                break;
            case E:
                if (mower.getX() < lawn.getWidth()
                        && lawn.isAvailable(mower.getX() + 1, mower.getY())) {
                    mower.setX(mower.getX() + 1);
                }
                break;
            case W:
                if (mower.getX() > 0
                        && lawn.isAvailable(mower.getX() - 1, mower.getY())) {
                    mower.setX(mower.getX() - 1);
                }
                break;
            case S:
                if (mower.getY() > 0
                        && lawn.isAvailable(mower.getX() - 1, mower.getY())) {
                    mower.setY(mower.getY() - 1);
                }
                break;
        }
    }


    public void move(Mower mower, Lawn lawn, List<Command> commands) {
        for (Command command : commands) {
            switch (command) {
                case F:
                    forward(mower, lawn);
                    break;
                case L:
                    left(mower);
                    break;
                case R:
                    right(mower);
                    break;
            }
        }
    }

}
