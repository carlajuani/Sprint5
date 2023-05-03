package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mongo;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.GameMongo;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.PlayerMongo;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.GameMongoDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.PlayerMongoDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface IMongoService {

    PlayerMongoDTO savePlayer(PlayerMongoDTO playerDTO);
    GameMongoDTO addGame(ObjectId playerID);
    PlayerMongoDTO updatePlayer(ObjectId id, PlayerMongoDTO playerData);
    PlayerMongoDTO findPlayerById(ObjectId id);
    PlayerMongoDTO findPlayerByUsername(String username);
    List<GameMongoDTO> findGamesByPlayerID(ObjectId id);
    List<PlayerMongoDTO> findAllPlayers();
    List<String> calculatePlayersRanking();
    void deletePlayerById(ObjectId id);
    void deleteGamesByPlayerId(ObjectId id);
    PlayerMongoDTO convertPlayerMongoToDTO(PlayerMongo player);
    GameMongoDTO convertGameMongoToDTO(GameMongo game);
    PlayerMongo convertDTOToPlayerMongo(PlayerMongoDTO playerDTO);
    GameMongo convertDTOToGameMongo(GameMongoDTO gameDTO);
    String beautifyRanking(List<String> ranking);
}
