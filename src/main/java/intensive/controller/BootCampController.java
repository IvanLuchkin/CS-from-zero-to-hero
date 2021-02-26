package intensive.controller;

import intensive.model.BootCamp;
import intensive.model.dto.bootcamp.BootCampRequestDto;
import intensive.model.dto.bootcamp.BootCampResponseDto;
import intensive.service.BootCampService;
import intensive.service.mapper.BootCampMapper;
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
@RequestMapping("/bootcamps")
public class BootCampController {
    private final BootCampService bootCampService;
    private final BootCampMapper bootCampMapper;

    @Autowired
    public BootCampController(BootCampService bootCampService,
                              BootCampMapper bootCampMapper) {
        this.bootCampService = bootCampService;
        this.bootCampMapper = bootCampMapper;
    }

    @PostMapping
    public void addBootCamp(@RequestBody @Valid BootCampRequestDto bootCampRequestDto) {
        BootCamp bootCamp = bootCampMapper.mapToBootCamp(bootCampRequestDto);
        bootCampService.add(bootCamp);
    }

    @GetMapping
    public List<BootCampResponseDto> getAllBootCamps() {
        return bootCampService.getAll()
                .stream()
                .map(bootCampMapper::mapToBootCampResponseDto)
                .collect(Collectors.toList());
    }
}
