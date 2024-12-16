    package notebook.controller;

    import jakarta.validation.Valid;
    import lombok.AccessLevel;
    import lombok.RequiredArgsConstructor;
    import lombok.experimental.FieldDefaults;
    import notebook.model.*;
    import notebook.service.AuthService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/v1/auth")
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public class AuthController {

        AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<?> register(@Valid @RequestBody AuthRequest registerRequest) {
            return ResponseEntity.ok(authService.register(registerRequest));
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
            return ResponseEntity.ok(authService.login(loginRequest));
        }

        @PostMapping("/refresh-token")
        public ResponseEntity<?> refreshToken(String refreshToken) {
            return ResponseEntity.ok(authService.refreshToken(refreshToken));
        }

        @PostMapping("/forgot-password")
        public ResponseEntity<?> sendResetPasswordEmail(@RequestParam("email") String email) {
            authService.sendResetPasswordEmail(email);
            return ResponseEntity.ok("Password reset link sent to email.");
        }

        @PostMapping("/reset-password")
        public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPasswordRequest resetRequest) {
            authService.resetPassword(resetRequest);
            return ResponseEntity.ok("Password reseted successfully.");
        }

    }