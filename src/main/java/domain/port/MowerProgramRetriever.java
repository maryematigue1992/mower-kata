package domain.port;

import domain.model.Command;
import domain.model.Lawn;
import domain.model.Mower;

import java.util.List;
import java.util.Map;

public interface MowerProgramRetriever {
    Map<Mower, List<Command>> retrieveMowerCommands();
    Lawn retrieveLawn();
}
