package domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mower {
    private int x;
    private int y;
    private Orientation orientation;

    @Override
    public String toString() {
        return String.format("%d%d%s", x, y, orientation.name());
    }
}
