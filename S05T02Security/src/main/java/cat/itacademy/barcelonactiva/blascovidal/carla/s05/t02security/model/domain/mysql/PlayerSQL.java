package cat.itacademy.barcelonactiva.blascovidal.carla.s05.t02security.model.domain.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerSQL implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerID;

    @Column(name = "nickname")
    @Builder.Default
    private String nickname = "UNKNOWN";

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "registerDate")
    @CreationTimestamp
    private LocalDateTime registerDate;

    @Column(name = "successRate")
    @Builder.Default
    private double successRate = 0.00;

    @OneToMany(mappedBy = "playerSQL", fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = GameSQL.class, cascade = CascadeType.ALL)
    private final List<GameSQL> history = new ArrayList<>();

    public PlayerSQL(String nickname) {
        this.nickname = nickname;
    }

    public enum Role {
        USER, ADMIN
    }
    private void setSuccessRate() {
        Double wins = 0.0;
        for (GameSQL gameSQL : history) {
            if (gameSQL.getGameResult() == GameSQL.GameResult.WINNER) {
                wins++;
            }
        }
        Double winningRate = (double) (Math.round(wins/history.size()*100.0))/100.0;
        this.successRate = winningRate;
    }

    public void addGameToHistory(GameSQL gameSQL) {
        history.add(gameSQL);
        setSuccessRate();
    }

    public void deleteHistory() {
        history.clear();
        this.successRate = 0.0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
