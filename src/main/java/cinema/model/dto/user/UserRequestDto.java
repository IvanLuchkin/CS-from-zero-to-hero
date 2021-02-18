package cinema.model.dto.user;

import cinema.validation.ValidateEmail;
import cinema.validation.ValidatePassword;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidatePassword.List({
        @ValidatePassword(
                field = "password",
                fieldMatch = "repeatPassword",
                message = "Passwords don't match."
        )
})
public class UserRequestDto {
    @ValidateEmail
    private String email;
    @Size(max = 50)
    @NotNull
    private String password;
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
