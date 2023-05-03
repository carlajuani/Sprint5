package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.controllers.sqlcontrollers;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.AuthenticationRequest;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.AuthenticationResponse;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.RegisterRequest;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mysql.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mySQL/diceGames/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerPlayer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticatePlayer(request));
    }
}
