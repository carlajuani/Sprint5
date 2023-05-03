package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.GameMongo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerMongoDTO {

    private ObjectId playerID;
    private String username;
    private double successRate = 0.00;
    @JsonIgnore
    private List<GameMongo> history = new ArrayList<>();
    public PlayerMongoDTO(String username) {
        this.username = username;
    }

}
