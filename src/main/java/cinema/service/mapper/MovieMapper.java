package cinema.service.mapper;

import cinema.model.Movie;
import cinema.model.dto.movie.MovieRequestDto;
import cinema.model.dto.movie.MovieResponseDto;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie mapToMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieResponseDto mapToMovieResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
