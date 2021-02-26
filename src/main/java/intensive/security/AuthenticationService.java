package intensive.security;

import intensive.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
