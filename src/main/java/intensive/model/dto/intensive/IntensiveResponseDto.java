package intensive.model.dto.intensive;

public class IntensiveResponseDto {
    private Long intensiveId;
    private Long topicId;
    private Long bootCampId;
    private String eventTime;

    public Long getIntensiveId() {
        return intensiveId;
    }

    public void setIntensiveId(Long intensiveId) {
        this.intensiveId = intensiveId;
    }

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
