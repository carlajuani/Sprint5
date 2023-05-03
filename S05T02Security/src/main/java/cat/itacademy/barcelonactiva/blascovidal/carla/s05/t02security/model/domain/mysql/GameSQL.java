package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "games")
public class GameSQL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gameID;

    @Column(name = "firstDice")
    private int firstDice = (int) ((Math.random() * 6) + 1);

    @Column(name = "secondDice")
    private int secondDice = (int) (Math.random() * 6) + 1;

    @Column(name = "gameResult")
    private GameResult gameResult;

    @Column(name = "timestamp")
    @CreationTimestamp
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerSQL playerSQL;


    public GameSQL(PlayerSQL playerSQL) {
        this.playerSQL = playerSQL;
        setGameResult();
    }

    enum GameResult {
        WINNER, LOSER
    }

    private void setGameResult() {
        if (firstDice+secondDice == 7) {
            this.gameResult = GameResult.WINNER;
        } else {
            this.gameResult = GameResult.LOSER;
        }
    }
}
