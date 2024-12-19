package notebook.repository;

import notebook.entity.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    Optional<UserSession> findByToken_Token(String refreshToken);

    void deleteByUserId(Long userId);
}