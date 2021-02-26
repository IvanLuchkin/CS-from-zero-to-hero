package intensive.service.mapper;

import intensive.model.Topic;
import intensive.model.dto.topic.TopicRequestDto;
import intensive.model.dto.topic.TopicResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TopicMapper {
    public Topic mapToTopic(TopicRequestDto topicRequestDto) {
        Topic topic = new Topic();
        topic.setTitle(topicRequestDto.getTitle());
        topic.setDescription(topicRequestDto.getDescription());
        return topic;
    }

    public TopicResponseDto mapToTopicResponseDto(Topic topic) {
        TopicResponseDto topicResponseDto = new TopicResponseDto();
        topicResponseDto.setTopicId(topic.getId());
        topicResponseDto.setTitle(topic.getTitle());
        topicResponseDto.setDescription(topic.getDescription());
        return topicResponseDto;
    }
}
