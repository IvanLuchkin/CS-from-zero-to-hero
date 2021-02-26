package intensive.service.impl;

import intensive.dao.BootCampDao;
import intensive.model.BootCamp;
import intensive.service.BootCampService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootCampServiceImpl implements BootCampService {
    private final BootCampDao bootCampDao;

    @Autowired
    public BootCampServiceImpl(BootCampDao bootCampDao) {
        this.bootCampDao = bootCampDao;
    }

    @Override
    public BootCamp add(BootCamp bootCamp) {
        return bootCampDao.add(bootCamp);
    }

    @Override
    public BootCamp getById(Long id) {
        return bootCampDao.getById(id).orElseThrow(() ->
                 new RuntimeException("Bootcamp " + id + " not found"));
    }

    @Override
    public List<BootCamp> getAll() {
        return bootCampDao.getAll();
    }
}
