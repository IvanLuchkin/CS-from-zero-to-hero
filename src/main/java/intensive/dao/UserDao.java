package intensive.dao;

import intensive.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> getById(Long id);

    Optional<User> findByEmail(String email);
}
