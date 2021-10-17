package infrastructure.filsystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputFileReader {

    private List<String> fileLines;

    public InputFileReader() {
        Path path = Paths.get("src/main/resources/mowerInput.txt");
        try (Stream<String> lines = Files.lines(path)) {

            this.fileLines = lines.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getMowersCommands() {
        List<String> result = new ArrayList<>(fileLines);

        if (result.isEmpty()) {
            return result;
        }
        result.remove(0);
        return result;
    }

    public Optional<String> getLawn() {
        return fileLines.stream().findFirst();
    }
}
