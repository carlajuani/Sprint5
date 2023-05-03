package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mongo;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.GameMongo;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mongo.PlayerMongo;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.GameMongoDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.PlayerMongoDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mongo.IPlayerMongoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MongoServiceImpl implements IMongoService {

    private final ModelMapper modelMapper;
    private final IPlayerMongoRepository playerRepository;

    @Override
    public PlayerMongoDTO savePlayer(PlayerMongoDTO playerDTO) {
        //to avoid name duplicate (except when unknown)
        if (checkDuplicatedUsername(playerDTO.getUsername())) {
            return null;
        }
        PlayerMongo player = convertDTOToPlayerMongo(playerDTO);
        playerRepository.save(player);
        return convertPlayerMongoToDTO(player);
    }

    @Override
    public GameMongoDTO addGame(ObjectId playerID) {
        Optional<PlayerMongo> optionalPlayer = playerRepository.findById(playerID);
        GameMongo game = null;
        if (optionalPlayer.isPresent()) {
            PlayerMongo player = optionalPlayer.get();
            game = new GameMongo();
            player.addGameToHistory(game);
            playerRepository.save(player);
        }
        return convertGameMongoToDTO(game);
    }

    @Override
    public PlayerMongoDTO updatePlayer(ObjectId id, PlayerMongoDTO playerData) {
        Optional<PlayerMongo> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isEmpty()) {
            return null;
        }
        PlayerMongo player = optionalPlayer.get();
        if (!checkDuplicatedUsername(playerData.getUsername())) {
            player.setUsername(playerData.getUsername());
        }
        playerRepository.save(player);
        return convertPlayerMongoToDTO(player);
    }

    @Override
    public PlayerMongoDTO findPlayerById(ObjectId iD) {
        Optional<PlayerMongo> optionalPlayer = playerRepository.findById(iD);
        if (optionalPlayer.isEmpty()) {
            return null;
        }
        return convertPlayerMongoToDTO(optionalPlayer.get());
    }

    @Override
    public PlayerMongoDTO findPlayerByUsername(String username) {
        PlayerMongo player = playerRepository.findByUsernameContaining(username);
        if (player == null) {
            return null;
        }
        return convertPlayerMongoToDTO(player);
    }

    @Override
    public List<GameMongoDTO> findGamesByPlayerID(ObjectId iD) {
        Optional<PlayerMongo> player = playerRepository.findById(iD);
        if (player.isEmpty()) {
            return null;
        }
        PlayerMongo playerFound = player.get();
        List<GameMongo> games = playerFound.getHistory();
        return games.stream()
                .map(this::convertGameMongoToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<PlayerMongoDTO> findAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::convertPlayerMongoToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> calculatePlayersRanking(){
        List<PlayerMongo> players = sortPlayersBySuccessRate(playerRepository.findAll());
        List<String> ranking = players.stream()
                .map(player ->  " "+player.getSuccessRate()+ "% - "+ player.getUsername())
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
    public void deletePlayerById(ObjectId iD) {
        playerRepository.deleteById(iD);
    }

    @Override
    public void deleteGamesByPlayerId(ObjectId iD) {
        Optional<PlayerMongo> player = playerRepository.findById(iD);
        if (player.isPresent()) {
            PlayerMongo playerFound = player.get();
            playerFound.deleteHistory();
            playerRepository.save(playerFound);
        }
    }

    @Override
    public PlayerMongoDTO convertPlayerMongoToDTO(PlayerMongo playerMongo) {
        return modelMapper.map(playerMongo, PlayerMongoDTO.class);
    }

    @Override
    public GameMongoDTO convertGameMongoToDTO(GameMongo gameMongo) {
        return modelMapper.map(gameMongo, GameMongoDTO.class);
    }

    @Override
    public PlayerMongo convertDTOToPlayerMongo(PlayerMongoDTO playerMongoDTO) {
        return modelMapper.map(playerMongoDTO, PlayerMongo.class);
    }

    @Override
    public GameMongo convertDTOToGameMongo(GameMongoDTO gameMongoDTO) {
        return modelMapper.map(gameMongoDTO, GameMongo.class);
    }

    //we convert the ranking list to a unique String with title
    public String beautifyRanking(List<String> rankingList) {
        String rankingBeautified = "RANKING BY SUCCESS RATE\n";
        for (String playerRanking : rankingList) {
            rankingBeautified += playerRanking+"\n";
        }
        return rankingBeautified;
    }

    public boolean checkDuplicatedUsername (String username) {
        boolean repeatedName = false;
        PlayerMongo playerFound = playerRepository.findByUsernameContaining(username);
        if (playerFound != null && (!playerFound.getUsername().equalsIgnoreCase("UNKNOWN"))) {
            repeatedName = true;
        };
        return repeatedName;
    }

    //We sort the players by its success rate order with a comparator, but in reverse, since it goes from smaller to bigger originally
    public List<PlayerMongo> sortPlayersBySuccessRate(List<PlayerMongo> players) {
        players.sort(Collections.reverseOrder(Comparator.comparing(PlayerMongo::getSuccessRate)));
        return players;
    }
}
