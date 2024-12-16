package notebook.model;

import lombok.Getter;

@Getter
public class ResetPasswordRequest {
    String token;
    String password;
}
