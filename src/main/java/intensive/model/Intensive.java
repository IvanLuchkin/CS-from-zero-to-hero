package intensive.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "intensives")
public class Intensive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private BootCamp bootCamp;
    private LocalDateTime eventTime;

    public Intensive() {
    }

    public Intensive(Topic topic, BootCamp bootCamp, LocalDateTime eventTime) {
        this.topic = topic;
        this.bootCamp = bootCamp;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Topic getMovie() {
        return topic;
    }

    public void setMovie(Topic topic) {
        this.topic = topic;
    }

    public BootCamp getCinemaHall() {
        return bootCamp;
    }

    public void setCinemaHall(BootCamp bootCamp) {
        this.bootCamp = bootCamp;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalDateTime showTime) {
        this.eventTime = showTime;
    }

    @Override
    public String toString() {
        return "Intensive{"
                + "id=" + id
                + ", topic=" + topic
                + ", bootcamp=" + bootCamp
                + ", eventTime=" + eventTime
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Intensive that = (Intensive) o;
        return id.equals(that.id) && topic.equals(that.topic)
                && bootCamp.equals(that.bootCamp) && eventTime.equals(that.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, bootCamp, eventTime);
    }
}
