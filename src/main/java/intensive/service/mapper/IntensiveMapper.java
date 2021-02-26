package intensive.service.mapper;

import intensive.model.Intensive;
import intensive.model.dto.intensive.IntensiveRequestDto;
import intensive.model.dto.intensive.IntensiveResponseDto;
import intensive.service.BootCampService;
import intensive.service.TopicService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntensiveMapper {
    private final TopicService topicService;
    private final BootCampService bootCampService;

    @Autowired
    public IntensiveMapper(TopicService topicService, BootCampService bootCampService) {
        this.topicService = topicService;
        this.bootCampService = bootCampService;
    }

    public Intensive mapToIntensive(IntensiveRequestDto intensiveRequestDto) {
        Intensive intensive = new Intensive();
        intensive.setMovie(topicService.getById(intensiveRequestDto.getTopicId()));
        intensive.setCinemaHall(
                bootCampService.getById(intensiveRequestDto.getBootCampId()));
        intensive.setEventTime(LocalDateTime.parse(intensiveRequestDto.getEventTime()));
        return intensive;
    }

    public IntensiveResponseDto mapToIntensiveResponseDto(Intensive intensive) {
        IntensiveResponseDto intensiveResponseDto = new IntensiveResponseDto();
        intensiveResponseDto.setIntensiveId(intensive.getId());
        intensiveResponseDto.setTopicId(intensive.getMovie().getId());
        intensiveResponseDto.setBootCampId(intensive.getCinemaHall().getId());
        intensiveResponseDto.setEventTime(intensive.getEventTime().toString());
        return intensiveResponseDto;
    }
}
