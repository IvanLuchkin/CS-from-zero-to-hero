package intensive.service;

import intensive.model.Intensive;
import java.time.LocalDate;
import java.util.List;

public interface IntensiveService {
    List<Intensive> findAvailableIntensives(Long movieId, LocalDate date);

    Intensive update(Intensive intensive);

    Intensive get(Long id);

    void delete(Long id);

    Intensive add(Intensive intensive);
}
