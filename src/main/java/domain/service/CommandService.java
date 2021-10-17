package domain.service;

import domain.model.Command;
import domain.model.Mower;
import domain.port.MowerProgramRetriever;

import java.util.List;
import java.util.Map;


public class CommandService {

    private final MowerProgramRetriever mowerProgramRetriever;

    public CommandService(MowerProgramRetriever mowerProgramRetriever) {
        this.mowerProgramRetriever = mowerProgramRetriever;
    }

    public Map<Mower, List<Command>> buildMowerCommands() {
        return mowerProgramRetriever.retrieveMowerCommands();
    }


}
