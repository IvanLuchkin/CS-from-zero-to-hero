package intensive.service.impl;

import intensive.dao.TopicDao;
import intensive.model.Topic;
import intensive.service.TopicService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService {
    private TopicDao topicDao;

    @Autowired
    public TopicServiceImpl(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public Topic add(Topic topic) {
        return topicDao.add(topic);
    }

    @Override
    public Topic getById(Long id) {
        return topicDao.getById(id).orElseThrow(() ->
                new RuntimeException("Topic " + id + " not found"));
    }

    @Override
    public List<Topic> getAll() {
        return topicDao.getAll();
    }
}
