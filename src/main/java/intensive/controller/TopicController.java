package intensive.controller;

import intensive.model.dto.topic.TopicRequestDto;
import intensive.model.dto.topic.TopicResponseDto;
import intensive.service.TopicService;
import intensive.service.mapper.TopicMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;
    private final TopicMapper topicMapper;

    @Autowired
    public TopicController(TopicService topicService, TopicMapper topicMapper) {
        this.topicService = topicService;
        this.topicMapper = topicMapper;
    }

    @PostMapping
    public void addTopic(@RequestBody @Valid TopicRequestDto topicRequestDto) {
        topicService.add(topicMapper.mapToTopic(topicRequestDto));
    }

    @GetMapping
    public List<TopicResponseDto> getAllTopics() {
        return topicService.getAll()
                .stream()
                .map(topicMapper::mapToTopicResponseDto)
                .collect(Collectors.toList());
    }
}
