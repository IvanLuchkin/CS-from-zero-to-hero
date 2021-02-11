package cinema.security;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.security.util.PasswordEncoder;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
    }

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
        User user = userService.add(new User(email, password));
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
