package notebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USER_SESSIONS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TOKEN_ID", nullable = false, unique = true)
    Token token;

    @Column(name = "CREATED_AT", nullable = false)
    LocalDateTime createdAt;

    @Column(name = "IS_ACTIVE", nullable = false)
    boolean isActive = true;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
