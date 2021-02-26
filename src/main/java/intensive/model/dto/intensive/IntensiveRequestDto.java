package intensive.model.dto.intensive;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class IntensiveRequestDto {
    @Positive
    private Long topicId;
    @Positive
    private Long bootCampId;
    @NotNull
    private String eventTime;

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getBootCampId() {
        return bootCampId;
    }

    public void setBootCampId(Long bootCampId) {
        this.bootCampId = bootCampId;
    }
}
