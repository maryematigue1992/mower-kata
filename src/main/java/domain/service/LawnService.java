package domain.service;

import domain.model.Lawn;
import domain.port.MowerProgramRetriever;


public class LawnService {
    private final MowerProgramRetriever mowerProgramRetriever;

    public LawnService(MowerProgramRetriever mowerProgramRetriever) {
        this.mowerProgramRetriever = mowerProgramRetriever;
    }

    public Lawn buildLawn() {
        return mowerProgramRetriever.retrieveLawn();
    }
}
