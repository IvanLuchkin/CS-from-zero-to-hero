package intensive.dao.impl;

import intensive.dao.BootCampDao;
import intensive.exception.DataProcessingException;
import intensive.model.BootCamp;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BootCampDaoImpl implements BootCampDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public BootCampDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public BootCamp add(BootCamp bootCamp) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(bootCamp);
            transaction.commit();
            return bootCamp;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert bootcamp " + bootCamp, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<BootCamp> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(BootCamp.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Could not get bootcamp " + id, e);
        }
    }

    @Override
    public List<BootCamp> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from BootCamp", BootCamp.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get all bootcamps", e);
        }
    }
}
