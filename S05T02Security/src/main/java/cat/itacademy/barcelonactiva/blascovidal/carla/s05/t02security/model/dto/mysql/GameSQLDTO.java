package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameSQLDTO {

    private int gameID;
    private int firstDice;
    private int secondDice;
    private GameResult gameResult;
    private LocalDateTime localDateTime;
    private int playerId;
    private enum GameResult {
        WINNER, LOSER
    }
}
