package cinema.dao;

import cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    MovieSession add(MovieSession movieSession);

    MovieSession update(MovieSession movieSession);

    Optional<MovieSession> getById(Long id);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    void delete(Long id);
}
