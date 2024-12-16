package notebook.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import notebook.model.TokenType;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TOKENS")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE", nullable = false)
    TokenType tokenType;

    @Column(name = "TOKEN", nullable = false, unique = true)
    String token;

    @Column(name = "CREATED_AT", nullable = false)
    LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
