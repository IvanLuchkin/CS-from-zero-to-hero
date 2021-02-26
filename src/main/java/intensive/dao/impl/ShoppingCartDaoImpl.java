package intensive.dao.impl;

import intensive.dao.ShoppingCartDao;
import intensive.exception.DataProcessingException;
import intensive.model.ShoppingCart;
import intensive.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select sc from ShoppingCart sc "
                            + "left join fetch sc.tickets t "
                            + "left join fetch t.movieSession ms "
                            + "left join fetch ms.movie "
                            + "left join fetch ms.cinemaHall "
                            + "where sc.user = :user", ShoppingCart.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get shopping cart by user " + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update " + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
