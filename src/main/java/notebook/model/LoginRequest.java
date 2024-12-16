package notebook.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotEmpty(message = "Username should be not empty")
    private String username;
    @NotEmpty(message = "Password should be not empty")
    private String password;
}
