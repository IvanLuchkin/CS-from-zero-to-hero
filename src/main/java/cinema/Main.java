package cinema;

import cinema.exception.AuthenticationException;
import cinema.injections.Injector;
import cinema.model.CinemaHall;
import cinema.model.Movie;
import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.security.AuthenticationService;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.MovieSessionService;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;
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
    private static final AuthenticationService authService =
            (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static final ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static final OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);

    public static void main(String[] args) throws AuthenticationException {
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

        User newUser = authService.register("test@gmail.com", "1234");
        User actualUser = authService.login("test@gmail.com", "1234");
        System.out.println(actualUser);

        shoppingCartService.addSession(session, newUser);
        ShoppingCart cartFromDB = shoppingCartService.getByUser(newUser);
        System.out.println("Received SC: " + cartFromDB);

        orderService.completeOrder(cartFromDB);
        System.out.println(orderService.getOrdersHistory(newUser));
    }
}
