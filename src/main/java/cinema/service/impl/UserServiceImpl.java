package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.security.util.PasswordEncoder;
import cinema.service.UserService;
import java.security.SecureRandom;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        user.setPassword(passwordEncoder.getHash(user.getPassword(), salt));
        user.setSalt(salt);
        return userDao.add(user);
    }

    @Override
    public User get(Long id) {
        return userDao.getById(id).orElseThrow(() ->
                new RuntimeException("User " + id + " does not exist"));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
