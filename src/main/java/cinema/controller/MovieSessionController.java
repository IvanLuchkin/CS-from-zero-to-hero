package cinema.controller;

import cinema.model.MovieSession;
import cinema.model.dto.moviesession.MovieSessionRequestDto;
import cinema.model.dto.moviesession.MovieSessionResponseDto;
import cinema.service.MovieSessionService;
import cinema.service.mapper.MovieSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService,
                                  MovieSessionMapper movieSessionMapper) {
        this.movieSessionService = movieSessionService;
        this.movieSessionMapper = movieSessionMapper;
    }

    @PostMapping
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = movieSessionMapper.mapToMovieSession(movieSessionRequestDto);
        movieSessionService.add(movieSession);
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAvailableSessions(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return movieSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(movieSessionMapper::mapToMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateMovieSession(@PathVariable("id") Long movieSessionId,
                                   @RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = movieSessionMapper.mapToMovieSession(movieSessionRequestDto);
        movieSession.setId(movieSessionId);
        movieSessionService.update(movieSession);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieSession(@PathVariable("id") Long movieSessionId) {
        movieSessionService.delete(movieSessionId);
    }
}
