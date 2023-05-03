package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mysql;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.PlayerSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerSQLRepository extends JpaRepository<PlayerSQL, Integer> {
    PlayerSQL findByNicknameContaining(String username);
    Optional<PlayerSQL> findByEmail(String email);
    boolean existsByNickname(String nickname);
}
