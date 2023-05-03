package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nickname;
    private String email;
    private String password;
}
