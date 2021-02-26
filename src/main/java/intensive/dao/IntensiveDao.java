package intensive.dao;

import intensive.model.Intensive;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IntensiveDao {
    Intensive add(Intensive intensive);

    Intensive update(Intensive intensive);

    Optional<Intensive> getById(Long id);

    List<Intensive> findAvailableIntensives(Long movieId, LocalDate date);

    void delete(Long id);
}
