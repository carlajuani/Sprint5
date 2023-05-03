package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSQLDTO {

    private int playerID;
    private String nickname;
    private LocalDateTime registerDate;
    private double successRate = 0.00;
    public PlayerSQLDTO(String nickname) {
        this.nickname = nickname;
    }
}
