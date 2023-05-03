package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mysql;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.GameSQL;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.PlayerSQL;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql.GameSQLDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mysql.PlayerSQLDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mysql.IGameSQLRepository;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mysql.IPlayerSQLRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SQLServiceImpl implements ISQLService {

    private final ModelMapper modelMapper;
    private final IGameSQLRepository gameRepository;
    private final IPlayerSQLRepository playerRepository;

    @Override
    public GameSQLDTO addGame(int playerID) {
        Optional<PlayerSQL> optionalPlayer = playerRepository.findById(playerID);
        GameSQL game = null;
        if (optionalPlayer.isPresent()) {
            PlayerSQL player = optionalPlayer.get();
            game = new GameSQL(player);
            player.addGameToHistory(game);
            gameRepository.save(game);
        }
        return convertGameSQLToDTO(game);
    }

    @Override
    public PlayerSQLDTO updatePlayer(int id, PlayerSQLDTO playerData) {
        Optional<PlayerSQL> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isEmpty()) {
            return null;
        }
        PlayerSQL player = optionalPlayer.get();
        if (!checkDuplicatedNickname(playerData.getNickname())) {
            player.setNickname(playerData.getNickname());
        }
        playerRepository.save(player);
        return convertPlayerSQLToDTO(player);
    }

    @Override
    public PlayerSQLDTO findPlayerById(int iD) {
        Optional<PlayerSQL> optionalPlayer = playerRepository.findById(iD);
        PlayerSQLDTO playerDTO = null;
        if (optionalPlayer.isPresent()) {
            playerDTO = convertPlayerSQLToDTO(optionalPlayer.get());
        }
        return playerDTO;
    }

    @Override
    public PlayerSQLDTO findPlayerByUsername(String nickname) {
        PlayerSQL player = playerRepository.findByNicknameContaining(nickname);
        return convertPlayerSQLToDTO(player);
    }

    @Override
    public List<GameSQLDTO> findGamesByPlayerID(int iD) {
        Optional<PlayerSQL> player = playerRepository.findById(iD);
        if (player.isEmpty()) {
            return null;
        }
        PlayerSQL playerSQLFound = player.get();
        List<GameSQL> game = playerSQLFound.getHistory();
        return game.stream()
                .map(this::convertGameSQLToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlayerSQLDTO> findAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::convertPlayerSQLToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> calculatePlayersRanking(){
        List<PlayerSQL> players = playerRepository.findAll();
        //We sort the players by its success rate order, but in reverse, since it goes from smaller to bigger originally
        players.sort(Collections.reverseOrder(Comparator.comparing(PlayerSQL::getSuccessRate)));
        List<String> ranking = players.stream()
                .map(player -> " "+player.getSuccessRate()+ "% - "+ player.getUsername())
                .collect(Collectors.toList());
        int rankingPositions = 1;
        List<String> finalRanking = new ArrayList<>();
        for (String playerSuccessRate: ranking) {
            finalRanking.add(rankingPositions+". "+playerSuccessRate);
            rankingPositions++;
        }
        return finalRanking;
    }

    @Override
    public void deletePlayerById(int iD) {
        playerRepository.deleteById(iD);
    }

    @Override
    public void deleteGamesByPlayerId(int iD) {
        Optional<PlayerSQL> player = playerRepository.findById(iD);
        if (player.isPresent()) {
            PlayerSQL playerFound = player.get();
            playerFound.deleteHistory();
            playerRepository.save(playerFound);
        }
    }

    @Override
    public PlayerSQLDTO convertPlayerSQLToDTO(PlayerSQL player) {
        return modelMapper.map(player, PlayerSQLDTO.class);
    }

    @Override
    public GameSQLDTO convertGameSQLToDTO(GameSQL game) {
        return modelMapper.map(game, GameSQLDTO.class);
    }

    @Override
    public PlayerSQL convertDTOToPlayerSQL(PlayerSQLDTO playerSQLDTO) {
        return modelMapper.map(playerSQLDTO, PlayerSQL.class);
    }

    @Override
    public GameSQL convertDTOToGameSQL(GameSQLDTO gameSQLDTO) {
        return modelMapper.map(gameSQLDTO, GameSQL.class);
    }

    public String beautifyRanking(List<String> rankingList) {
        String rankingBeautified = "RANKING BY SUCCESS RATE\n";
        for (String playerRanking : rankingList) {
            rankingBeautified += playerRanking+"\n";
        }
        return rankingBeautified;
    }

    private boolean checkDuplicatedNickname (String nickname) {
        boolean repeatedName = false;
        PlayerSQL playerFound = playerRepository.findByNicknameContaining(nickname);
        if (playerFound != null && (!playerFound.getNickname().equalsIgnoreCase("UNKNOWN"))) {
            repeatedName = true;
        };
        return repeatedName;
    }

    //We sort the players by its success rate order with a comparator, but in reverse, since it goes from smaller to bigger originally
    public List<PlayerSQL> sortPlayersBySuccessRate(List<PlayerSQL> players) {
        players.sort(Collections.reverseOrder(Comparator.comparing(PlayerSQL::getSuccessRate)));
        return players;
    }
}
