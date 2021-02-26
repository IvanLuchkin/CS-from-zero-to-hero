package intensive.service;

import intensive.model.Topic;
import java.util.List;

public interface TopicService {
    Topic add(Topic topic);

    Topic getById(Long id);

    List<Topic> getAll();
}
