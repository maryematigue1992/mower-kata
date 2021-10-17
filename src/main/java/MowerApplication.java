import infrastructure.adapter.InputFileAdapter;
import infrastructure.filsystem.InputFileReader;
import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;
import domain.port.MowerProgramRetriever;
import domain.service.CommandService;
import domain.service.LawnService;
import domain.service.MowerService;

import java.util.List;
import java.util.Map;

public class MowerApplication {

    public static void main(String[] args) {

        InputFileReader inputFileReader = new InputFileReader();
        MowerProgramRetriever mowerProgramRetriever = new InputFileAdapter(inputFileReader);
        CommandService commandService = new CommandService(mowerProgramRetriever);
        MowerService mowerService = new MowerService();
        LawnService lawnService = new LawnService(mowerProgramRetriever);

        Map<Mower, List<Command>> mowersCommands = commandService.buildMowerCommands();
        Lawn lawn = lawnService.buildLawn();
        lawn.addMowers(mowersCommands.keySet());
        mowersCommands.keySet().stream().parallel().forEach(mower -> {
            List<Command> commands = mowersCommands.get(mower);
            mowerService.move(mower, lawn, commands);
        });

        lawn.removeMowers(mowersCommands.keySet());
        mowersCommands.keySet().forEach(System.out::println);


    }

}
