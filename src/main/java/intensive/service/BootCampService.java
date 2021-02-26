package intensive.service;

import intensive.model.BootCamp;
import java.util.List;

public interface BootCampService {
    BootCamp add(BootCamp bootCamp);

    BootCamp getById(Long id);

    List<BootCamp> getAll();
}
