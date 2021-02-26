package intensive.controller;

import intensive.model.Intensive;
import intensive.model.dto.intensive.IntensiveRequestDto;
import intensive.model.dto.intensive.IntensiveResponseDto;
import intensive.service.IntensiveService;
import intensive.service.mapper.IntensiveMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/intensives")
public class IntensiveController {
    private final IntensiveService intensiveService;
    private final IntensiveMapper intensiveMapper;

    @Autowired
    public IntensiveController(IntensiveService intensiveService,
                               IntensiveMapper intensiveMapper) {
        this.intensiveService = intensiveService;
        this.intensiveMapper = intensiveMapper;
    }

    @PostMapping
    public void addIntensive(@RequestBody @Valid IntensiveRequestDto intensiveRequestDto) {
        Intensive intensive = intensiveMapper.mapToIntensive(intensiveRequestDto);
        intensiveService.add(intensive);
    }

    @GetMapping("/available")
    public List<IntensiveResponseDto> getAvailableIntensives(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return intensiveService.findAvailableIntensives(movieId, date)
                .stream()
                .map(intensiveMapper::mapToIntensiveResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateIntensive(
            @PathVariable("id") Long intensiveId,
            @RequestBody @Valid IntensiveRequestDto intensiveRequestDto) {
        Intensive intensive = intensiveMapper.mapToIntensive(intensiveRequestDto);
        intensive.setId(intensiveId);
        intensiveService.update(intensive);
    }

    @DeleteMapping("/{id}")
    public void deleteIntensive(@PathVariable("id") Long intensiveId) {
        intensiveService.delete(intensiveId);
    }
}
