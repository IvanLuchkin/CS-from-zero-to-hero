package cinema;

import cinema.injections.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final Injector injector = Injector.getInstance("cinema");
    private static final MovieService movieService =
            (MovieService) injector.getInstance(MovieService.class);
    private static final MovieSessionService movieSessionService =
            (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static final CinemaHallService cinemaHallService =
            (CinemaHallService) injector.getInstance(CinemaHallService.class);

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("El Camino: Breaking Bad");
        movie.setDescription("A logical ending to the Breaking Bad series");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall hall = new CinemaHall();
        hall.setCapacity(50);
        hall.setDescription("test hall");
        cinemaHallService.add(hall);

        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(hall);
        LocalDateTime timeOfSession = LocalDateTime.now();
        System.out.println("Time of session: " + timeOfSession);
        session.setShowTime(timeOfSession);
        movieSessionService.add(session);

        LocalDate requestedDate = LocalDate.now();
        System.out.println("Requested date: " + requestedDate);
        List<MovieSession> res = movieSessionService.findAvailableSessions(1L, requestedDate);
        System.out.println(res);
    }
}
