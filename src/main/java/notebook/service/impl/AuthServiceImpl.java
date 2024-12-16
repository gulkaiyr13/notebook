package notebook.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import notebook.entity.Role;
import notebook.entity.Token;
import notebook.entity.UserEntity;
import notebook.entity.UserSession;
import notebook.exception.UserNameAlreadyTakenException;
import notebook.exception.UserNotFoundException;
import notebook.model.*;
import notebook.repository.RoleRepository;
import notebook.repository.TokenRepository;
import notebook.repository.UserRepository;
import notebook.repository.UserSessionRepository;
import notebook.security.JwtUtil;
import notebook.service.AuthService;
import notebook.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    JwtUtil jwtUtil;
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserSessionRepository userSessionRepository;
    TokenRepository tokenRepository;

    EmailService emailService;
    AuthenticationManager authManager;
    PasswordEncoder encoder;

    @Override
    public AuthResponse register(AuthRequest request) {
        validateUsername(request.getUsername());
        validateEmail(request.getEmail());

        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(encoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());

        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            throw new RuntimeException("Default role ROLE_USER not found");
        }

        newUser.setRole(userRole);

        userRepository.save(newUser);

        return new AuthResponse(request.getUsername());
    }

    private void validateUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new UserNameAlreadyTakenException("Username already in use");
        }
    }

    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserNameAlreadyTakenException("Email already in use");
        }
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        if (user.isBlocked())
            throw new RuntimeException("Account is blocked");

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String jwtToken = jwtUtil.generateToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        Token token = new Token();
        token.setUser(user);
        token.setToken(jwtToken);
        token.setTokenType(TokenType.ACCESS);
        tokenRepository.save(token);

        UserSession session = new UserSession();
        session.setUser(user);
        session.setToken(token);
        session.setCreatedAt(LocalDateTime.now());
        userSessionRepository.save(session);

        return new LoginResponse(jwtToken, refreshToken);
    }



    @Override
    @Transactional
    public TokenResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!jwtUtil.validateToken(refreshToken, user)) {
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccessToken = jwtUtil.generateToken(user);

        UserSession session = userSessionRepository.findByToken_Token(refreshToken)
                .orElseThrow(() -> new RuntimeException("Session not found for the given refresh token"));

        Token token = session.getToken();
        token.setToken(newAccessToken);
        tokenRepository.save(token);

        session.setToken(token);
        userSessionRepository.save(session);

        return new TokenResponse(newAccessToken);
    }


    @Override
    @Transactional
    public void sendResetPasswordEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String resetTokenValue = jwtUtil.generateResetPasswordToken(email);

        Token resetToken = new Token();
        resetToken.setUser(user);
        resetToken.setToken(resetTokenValue);
        resetToken.setTokenType(TokenType.PASSWORD_RESET);
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:8082/v1/auth/reset-password?token=" + resetTokenValue;
        emailService.sendEmail(email, "Password Reset", "Click the link to reset your password: " + resetLink);
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) {
        Token resetToken = tokenRepository.findByToken(resetPasswordRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid or expired reset token"));

        if (!resetToken.getTokenType().equals(TokenType.PASSWORD_RESET)) {
            throw new RuntimeException("Invalid token type for password reset");
        }

        UserEntity user = resetToken.getUser();

        user.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}


