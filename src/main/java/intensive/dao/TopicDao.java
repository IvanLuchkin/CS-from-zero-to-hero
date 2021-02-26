package intensive.dao;

import intensive.model.Topic;
import java.util.List;
import java.util.Optional;

public interface TopicDao {
    Topic add(Topic topic);

    Optional<Topic> getById(Long id);

    List<Topic> getAll();
}
