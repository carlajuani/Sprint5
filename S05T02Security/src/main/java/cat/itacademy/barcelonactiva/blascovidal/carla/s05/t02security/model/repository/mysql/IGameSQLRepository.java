package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mysql;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.GameSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameSQLRepository extends JpaRepository<GameSQL, Integer> {
    //List<GameSQL> findByPlayerSQLContaining(PlayerSQL playerSQL);
    //void deleteByPlayerSQLContaining(PlayerSQL playerSQL);

}
