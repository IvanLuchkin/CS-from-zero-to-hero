package cinema;

import cinema.injections.Injector;
import cinema.model.Movie;
import cinema.service.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("El Camino: Breaking Bad");
        movie.setDescription("A logical ending to the Breaking Bad series");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);
    }
}
