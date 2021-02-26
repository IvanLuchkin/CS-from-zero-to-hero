package intensive.security;

import static org.springframework.security.core.userdetails.User.withUsername;

import intensive.model.User;
import intensive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username).orElseThrow(() ->
                 new UsernameNotFoundException("User " + username + " could not be found"));
        UserBuilder userBuilder = withUsername(username)
                .password(user.getPassword())
                .roles(user.getRoles()
                        .stream()
                        .map(role -> role.getRoleName().toString())
                        .toArray(String[]::new));
        return userBuilder.build();
    }
}
