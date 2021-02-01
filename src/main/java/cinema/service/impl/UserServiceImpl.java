package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.injections.Inject;
import cinema.injections.Service;
import cinema.model.User;
import cinema.security.util.PasswordEncoder;
import cinema.service.UserService;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;
    @Inject
    private PasswordEncoder passwordEncoder;

    @Override
    public User add(User user) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        user.setPassword(passwordEncoder.getHash(user.getPassword(), salt));
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
