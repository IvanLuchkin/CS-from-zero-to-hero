package cinema.security;

import cinema.exception.AuthenticationException;
import cinema.injections.Inject;
import cinema.injections.Service;
import cinema.model.User;
import cinema.security.util.PasswordEncoder;
import cinema.service.UserService;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword()
                .equals(passwordEncoder.getHash(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Wrong username or password");
    }

    @Override
    public User register(String email, String password) {
        return userService.add(new User(email, password));
    }
}
