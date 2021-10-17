package infrastructure.adapter;

import infrastructure.filsystem.InputFileReader;
import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;
import domain.model.Orientation;
import org.assertj.core.util.VisibleForTesting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import domain.port.MowerProgramRetriever;

import java.util.*;

public class InputFileAdapter implements MowerProgramRetriever {

    private final InputFileReader inputFileReader;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public InputFileAdapter(InputFileReader inputFileReader) {
        this.inputFileReader = inputFileReader;
    }

    @Override
    public Map<Mower, List<Command>> retrieveMowerCommands() {
        List<String> mowersCommands = inputFileReader.getMowersCommands();

        Map<Mower, List<Command>> commandByMower = new HashMap<>();

        for (int i = 0; i < mowersCommands.size(); i = i + 1) {

            Mower mower = getMower(mowersCommands, i);

            i++;

            List<Command> commands = getCommands(mowersCommands, i);

            commandByMower.put(mower, commands);

        }
        return commandByMower;

    }

    @Override
    public Lawn retrieveLawn() {

        if (!inputFileReader.getLawn().isPresent()) {
            throw new NoSuchElementException();
        }

        String input = inputFileReader.getLawn().get();

        if (input.length() != 2) {
            logger.error("input do not contain the height and the width of the lawn");
            throw new IllegalArgumentException();
        }

        Lawn lawn = new Lawn();
        try {
            int width = Character.getNumericValue(input.charAt(0));
            int height = Character.getNumericValue(input.charAt(1));
            lawn.setWidth(width);
            lawn.setHeight(height);

        } catch (Exception e) {
            logger.error("error during lawn building", e);
        }
        return lawn;
    }

    @VisibleForTesting
    protected List<Command> getCommands(List<String> inputLines, int i) {
        String commandLine = inputLines.get(i);
        List<Command> commands = new ArrayList<>();
        for (int j = 0; j < commandLine.length(); j++) {
            Command enumInstruction = Command.valueOf(String.valueOf(commandLine.charAt(j)));
            commands.add(enumInstruction);
        }
        return commands;
    }

    @VisibleForTesting
    protected Mower getMower(List<String> inputLines, int i) {
        String mowerLine = inputLines.get(i);
        int horizontal = Character.getNumericValue(mowerLine.charAt(0));
        int vertical = Character.getNumericValue(mowerLine.charAt(1));
        Orientation orientation = Orientation.valueOf(String.valueOf(mowerLine.charAt(2)));
        return new Mower(horizontal, vertical, orientation);
    }
}
