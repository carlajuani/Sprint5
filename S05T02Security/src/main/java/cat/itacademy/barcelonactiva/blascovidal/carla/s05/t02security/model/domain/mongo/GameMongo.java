package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMongo {

    private int firstDice = (int) ((Math.random() * 6) + 1);
    private int secondDice = (int) (Math.random() * 6) + 1;
    private GameResult gameResult = getGameResult();
    @CreationTimestamp
    private LocalDateTime localDateTime;

    enum GameResult {
        WINNER, LOSER
    }

    public GameResult getGameResult() {
        if (firstDice+secondDice == 7) {
            this.gameResult = GameResult.WINNER;
        } else {
            this.gameResult = GameResult.LOSER;
        }
        return this.gameResult;
    }
}
