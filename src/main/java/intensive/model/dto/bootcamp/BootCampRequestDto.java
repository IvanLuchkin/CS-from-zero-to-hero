package intensive.model.dto.bootcamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BootCampRequestDto {
    @Positive
    private int capacity;
    @NotNull
    private String description;

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
