package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.services.mysql;

import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.config.JwtService;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.AuthenticationRequest;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.AuthenticationResponse;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth.RegisterRequest;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql.PlayerSQL;
import cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.repository.mysql.IPlayerSQLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IPlayerSQLRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerPlayer(RegisterRequest request) {
        boolean playerFound = repository.existsByNickname(request.getNickname());
        if (playerFound && !request.getNickname().equals("UNKNOWN")) {
            throw new IllegalArgumentException("Name is already taken");
        };
        PlayerSQL player = PlayerSQL.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(PlayerSQL.Role.USER)
                .build();
        repository.save(player);
        String jwtToken = jwtService.generateToken(player);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticatePlayer(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        PlayerSQL user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
