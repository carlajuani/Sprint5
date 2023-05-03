package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Players")
public class PlayerMongo {

    @Id
    private ObjectId id;
    private String username = "UNKNOWN";
    private double successRate = 0.00;
    private List<GameMongo> history = new ArrayList<>();

    public PlayerMongo(String username) {
        this.username = username;
    }

    public void setSuccessRate() {
        Double wins = 0.0;
        for (GameMongo gameMongo : history) {
            if (gameMongo.getGameResult() == GameMongo.GameResult.WINNER) {
                wins++;
            }
        }
        Double winningRate = (double) (Math.round(wins/history.size()*100.0))/100.0;
        this.successRate = winningRate*100;
    }

    public void addGameToHistory(GameMongo gameMongo) {
        history.add(gameMongo);
        this.setSuccessRate();
    }

    public void deleteHistory() {
        history.clear();
        this.successRate = 0.0;
    }
}
