package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.controllers.mongocontrollers;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.GameMongoDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.dto.mongo.PlayerMongoDTO;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mongo.IMongoService;
import io.swagger.v3.oas.annotations.Operation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mongoDB/diceGames")
public class MongoGameController {

    @Autowired
    private IMongoService service;

    //POST: /players: crea un jugador/a.
    @PostMapping("/addPlayer")
    @Operation(
            summary = "add new player",
            description = "It requires a player",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json player with at least String username\n")
    )
    public ResponseEntity<PlayerMongoDTO> createPlayer(@RequestBody PlayerMongoDTO playerDTO) {
        try {
            PlayerMongoDTO newPlayer = service.savePlayer(playerDTO);
            if (newPlayer == null) {
                return ResponseEntity.status( HttpStatus.NOT_ACCEPTABLE).build();
            }
            return new ResponseEntity<>(service.savePlayer(playerDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
    @PostMapping("/{id}/addGame")
    @Operation(
            summary = "add new game",
            description = "It requires the player's id as path variable"
    )
    public ResponseEntity<GameMongoDTO> createGame(@PathVariable("id") ObjectId playerID) {
        try {
            return new ResponseEntity<>(service.addGame(playerID), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //PUT /players: modifica el nom del jugador/a.
    @PutMapping("/updatePlayer/{id}")
    @Operation(
            summary = "update player",
            description = "It requires the original player's id as path variable and a player with the updated parameters",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "json player with at least String username\n")
    )
    public ResponseEntity<PlayerMongoDTO> updatePlayer(@PathVariable("id") ObjectId id, @RequestBody PlayerMongoDTO playerDTO) {
        try {
            return new ResponseEntity<>(service.updatePlayer(id, playerDTO), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.
    @GetMapping("/getAllPlayers")
    @Operation(
            summary = "get all players",
            description = "It returns all the players list"
    )
    public ResponseEntity<List<PlayerMongoDTO>> getAllPlayers() {
        try {
            List<PlayerMongoDTO> players = service.findAllPlayers();
            if (players.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
    @GetMapping("/{id}/getAllGames")
    @Operation(
            summary = "get all games",
            description = "It returns a list of all the player's games. It requires the player's id as path variable"
    )
    public ResponseEntity<List<GameMongoDTO>> getAllGames(@PathVariable("id") ObjectId id) {
        try {
            List<GameMongoDTO> games = service.findGamesByPlayerID(id);
            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
    @GetMapping("/getPlayersRanking")
    @Operation(
            summary = "get players success rate ranking",
            description = "It returns a numbered list with all players name and success rate by winning order"
    )
    public ResponseEntity<String> getPlayersRanking() {
        try {
            List<String> ranking = service.calculatePlayersRanking();
            if (ranking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            String beautifiedRanking = service.beautifyRanking(ranking);
            return new ResponseEntity<>(beautifiedRanking, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET /players/ranking/winner: retorna el jugador/a  amb millor percentatge d’èxit.
    @GetMapping("/getWinner")
    @Operation(
            summary = "get highest success rate player",
            description = "It returns the ranking's winner player"
    )
    public ResponseEntity<String> getWinnerPlayer() {
        try {
            List<String> ranking = service.calculatePlayersRanking();
            if (ranking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>("WINNER -> "+ranking.get(0), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET /players/ranking/loser: retorna el jugador amb pitjor percentatge d’èxit.
    @GetMapping("/getLoser")
    @Operation(
            summary = "get lowest success rate player",
            description = "It returns the ranking's loser player"
    )
    public ResponseEntity<String> getLoserPlayer() {
        try {
            List<String> ranking = service.calculatePlayersRanking();
            if (ranking.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>("LOSER -> "+ranking.get(ranking.size()-1), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE /players/{id}/games: elimina les tirades del jugador/a.
    @DeleteMapping("/{id}/deleteGames")
    @Operation(
            summary = "delete all games from player",
            description = "It requires a player's id path variable"
    )
    public ResponseEntity<HttpStatus> deleteGame(@PathVariable("id") ObjectId id) {
        try {
            if (service.findGamesByPlayerID(id).isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            service.deleteGamesByPlayerId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //EXTRES
    @DeleteMapping("/deletePlayer/{id}")
    @Operation(
            summary = "delete player",
            description = "It requires a player's id path variable (int playerID)"
    )
    public ResponseEntity<HttpStatus> deletePlayer(@PathVariable("id") ObjectId id) {
        try {
            if (service.findPlayerById(id) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            service.deletePlayerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPlayerByID/{id}")
    @Operation(
            summary = "get player by id",
            description = "It requires a player's id path variable"
    )
    public ResponseEntity<PlayerMongoDTO> getPlayerById(@PathVariable("id") ObjectId id) {
        try {
            PlayerMongoDTO player = service.findPlayerById(id);
            if (player == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getPlayerByUsername/{username}")
    @Operation(
            summary = "get player by username",
            description = "It requires a player's id path variable (int playerID)"
    )
    public ResponseEntity<PlayerMongoDTO> getPlayerByUsername(@PathVariable("username") String username) {
        try {
            PlayerMongoDTO player = service.findPlayerByUsername(username);
            if (player == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
