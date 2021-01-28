package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exception.DataProcessingException;
import cinema.injections.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Could not insert movie session " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select ms from MovieSession ms "
                    + "left join fetch ms.cinemaHall left join fetch ms.movie "
                    + "where ms.movie.id = :movieId and TO_CHAR(ms.showTime, 'YYYY-MM-DD') = :date",
                    MovieSession.class)
            .setParameter("movieId", movieId)
            .setParameter("date", DateTimeFormatter.ISO_LOCAL_DATE.format(date))
            .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Could not find available sessions for movie "
                    + movieId + " and date " + date, e);
        }
    }
}
