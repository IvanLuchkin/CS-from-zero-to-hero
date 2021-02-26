package intensive.dao.impl;

import intensive.dao.UserDao;
import intensive.exception.DataProcessingException;
import intensive.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert " + user, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Could not get user by id " + id, e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u join fetch u.roles r "
                    + "where u.email = :email", User.class)
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find user by email " + email, e);
        }
    }
}
