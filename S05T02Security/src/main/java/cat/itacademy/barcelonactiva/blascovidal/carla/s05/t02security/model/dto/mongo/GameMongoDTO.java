package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMongoDTO {

    private int firstDice;
    private int secondDice;
    private GameResult gameResult;
    private LocalDateTime localDateTime;
    private enum GameResult {
        WINNER, LOSER
    }
}
