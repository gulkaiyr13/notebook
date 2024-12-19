package notebook.service;

import notebook.model.*;

public interface AuthService {
    AuthResponse register(AuthRequest registerRequest);

    LoginResponse login(LoginRequest request);

    TokenResponse refreshToken(String refreshToken);
    void resetPassword(ResetPasswordRequest resetPasswordRequest);
    void sendResetPasswordEmail(String email);
}
