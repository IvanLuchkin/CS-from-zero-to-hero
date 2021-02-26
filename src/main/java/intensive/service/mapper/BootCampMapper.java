package intensive.service.mapper;

import intensive.model.BootCamp;
import intensive.model.dto.bootcamp.BootCampRequestDto;
import intensive.model.dto.bootcamp.BootCampResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BootCampMapper {
    public BootCamp mapToBootCamp(BootCampRequestDto bootCampRequestDto) {
        BootCamp bootCamp = new BootCamp();
        bootCamp.setCapacity(bootCampRequestDto.getCapacity());
        bootCamp.setDescription(bootCampRequestDto.getDescription());
        return bootCamp;
    }

    public BootCampResponseDto mapToBootCampResponseDto(BootCamp bootCamp) {
        BootCampResponseDto bootCampResponseDto = new BootCampResponseDto();
        bootCampResponseDto.setBootCampId(bootCamp.getId());
        bootCampResponseDto.setCapacity(bootCamp.getCapacity());
        bootCampResponseDto.setDescription(bootCamp.getDescription());
        return bootCampResponseDto;
    }
}
