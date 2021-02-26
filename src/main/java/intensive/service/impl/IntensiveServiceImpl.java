package intensive.service.impl;

import intensive.dao.IntensiveDao;
import intensive.model.Intensive;
import intensive.service.IntensiveService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntensiveServiceImpl implements IntensiveService {
    private final IntensiveDao intensiveDao;

    @Autowired
    public IntensiveServiceImpl(IntensiveDao intensiveDao) {
        this.intensiveDao = intensiveDao;
    }

    @Override
    public List<Intensive> findAvailableIntensives(Long movieId, LocalDate date) {
        return intensiveDao.findAvailableIntensives(movieId, date);
    }

    @Override
    public Intensive update(Intensive intensive) {
        return intensiveDao.update(intensive);
    }

    @Override
    public Intensive get(Long id) {
        return intensiveDao.getById(id).orElseThrow(() ->
                new RuntimeException("Intensive " + id + " does not exist"));
    }

    @Override
    public void delete(Long id) {
        intensiveDao.delete(id);
    }

    @Override
    public Intensive add(Intensive intensive) {
        return intensiveDao.add(intensive);
    }
}
