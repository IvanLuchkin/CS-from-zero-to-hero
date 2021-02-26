package intensive.dao.impl;

import intensive.dao.IntensiveDao;
import intensive.exception.DataProcessingException;
import intensive.model.Intensive;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IntensiveDaoImpl implements IntensiveDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public IntensiveDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Intensive add(Intensive intensive) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(intensive);
            transaction.commit();
            return intensive;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert intensive " + intensive, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Intensive update(Intensive intensive) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(intensive);
            transaction.commit();
            return intensive;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not update intensive " + intensive, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Intensive> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Intensive.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Could not get intensive by id " + id, e);
        }
    }

    @Override
    public List<Intensive> findAvailableIntensives(Long topicId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select i from Intensive i "
                    + "left join fetch i.bootCamp left join fetch i.topic "
                    + "where i.topic.id = :topicId and TO_CHAR(i.eventTime, 'YYYY-MM-DD') = :date",
                    Intensive.class)
            .setParameter("topicId", topicId)
            .setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date))
            .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find available intensives for topic "
                    + topicId + " and date " + date, e);
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from Intensive i where i.id = :id")
                    .setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not delete intensive " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
