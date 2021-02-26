package intensive.dao.impl;

import intensive.dao.TopicDao;
import intensive.exception.DataProcessingException;
import intensive.model.Topic;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TopicDaoImpl implements TopicDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TopicDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Topic add(Topic topic) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(topic);
            transaction.commit();
            return topic;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert " + topic, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Topic> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Topic.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Could not get topic by id " + id, e);
        }
    }

    @Override
    public List<Topic> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Topic", Topic.class).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not get all topics", e);
        }
    }
}
