package intensive.model.dto.bootcamp;

public class BootCampResponseDto {
    private Long bootCampId;
    private int capacity;
    private String description;

    public Long getBootCampId() {
        return bootCampId;
    }

    public void setBootCampId(Long bootCampId) {
        this.bootCampId = bootCampId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
