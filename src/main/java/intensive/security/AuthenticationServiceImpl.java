package intensive.security;

import intensive.model.User;
import intensive.service.RoleService;
import intensive.service.ShoppingCartService;
import intensive.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final RoleService roleService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     ShoppingCartService shoppingCartService,
                                     RoleService roleService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.roleService = roleService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        user.setRoles(List.of(roleService.getRoleByName("USER")));
        User savedUser = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return savedUser;
    }
}
