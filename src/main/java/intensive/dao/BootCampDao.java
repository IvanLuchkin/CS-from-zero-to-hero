package intensive.dao;

import intensive.model.BootCamp;
import java.util.List;
import java.util.Optional;

public interface BootCampDao {
    BootCamp add(BootCamp bootCamp);

    Optional<BootCamp> getById(Long id);

    List<BootCamp> getAll();
}
