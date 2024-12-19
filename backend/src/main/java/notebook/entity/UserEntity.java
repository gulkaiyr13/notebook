package notebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "USERS")
@FieldDefaults(level = AccessLevel.PRIVATE)
@SequenceGenerator(name = "USERS_SEQUENCE", sequenceName = "USERS_SEQ")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQUENCE")
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    String username;

    @Column(name = "EMAIL", unique = true, nullable = false)
    String email;

    @Column(name = "PASSWORD", nullable = false)
    String password;

    @Column(name = "IS_BLOCKED", nullable = false)
    boolean isBlocked = false;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !isBlocked;
    }

    @Override
    public boolean isEnabled() {
        return !isBlocked;
    }
}

