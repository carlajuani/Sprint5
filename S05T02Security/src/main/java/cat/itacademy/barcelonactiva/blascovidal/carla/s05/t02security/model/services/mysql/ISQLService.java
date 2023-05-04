package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mysql;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.GameSQL;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.PlayerSQL;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql.GameSQLDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql.PlayerSQLDTO;

import java.util.List;

public interface ISQLService {
    GameSQLDTO addGame(int playerID);
    PlayerSQLDTO updatePlayer(int id, PlayerSQLDTO playerData);
    PlayerSQLDTO findPlayerById(int iD);
    PlayerSQLDTO findPlayerByNickname(String username);
    List<GameSQLDTO> findGamesByPlayerID(int iD);
    List<PlayerSQLDTO> findAllPlayers();
    List<String> calculatePlayersRanking();
    void deletePlayerById(int iD);
    void deleteGamesByPlayerId(int iD);
    PlayerSQLDTO convertPlayerSQLToDTO(PlayerSQL playerSQL);
    GameSQLDTO convertGameSQLToDTO(GameSQL gameSQL);
    PlayerSQL convertDTOToPlayerSQL(PlayerSQLDTO playerSQLDTO);

    GameSQL convertDTOToGameSQL(GameSQLDTO gameSQLDTO);

    String beautifyRanking(List<String> ranking);
}
