package notebook.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AuthRequest {
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email should be not empty")
    private String email;
    @NotEmpty(message = "Username should be not empty")
    private String username;
    @NotEmpty(message = "Password should be not empty")
    private String password;
}
