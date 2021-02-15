package cinema;

import cinema.config.AppConfig;
import cinema.exception.AuthenticationException;
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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws AuthenticationException {
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MovieService movieService = appContext.getBean(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("El Camino: Breaking Bad");
        movie.setDescription("A logical ending to the Breaking Bad series");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = appContext.getBean(CinemaHallService.class);
        CinemaHall hall = new CinemaHall();
        hall.setCapacity(50);
        hall.setDescription("test hall");
        cinemaHallService.add(hall);

        MovieSession session = new MovieSession();
        session.setMovie(movie);
        session.setCinemaHall(hall);
        session.setShowTime(LocalDateTime.now());
        MovieSessionService movieSessionService = appContext.getBean(MovieSessionService.class);
        movieSessionService.add(session);

        MovieSession session1 = new MovieSession();
        session1.setMovie(movie);
        session1.setCinemaHall(hall);
        session1.setShowTime(LocalDateTime.now().plusHours(2));
        movieSessionService.add(session1);

        LocalDate requestedDate = LocalDate.now();
        List<MovieSession> res = movieSessionService.findAvailableSessions(1L, requestedDate);
        System.out.println(res);

        AuthenticationService authService = appContext.getBean(AuthenticationService.class);
        User newUser = authService.register("test@gmail.com", "1234");
        User actualUser = authService.login("test@gmail.com", "1234");
        System.out.println(actualUser);

        ShoppingCartService shoppingCartService = appContext.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(session, newUser);
        shoppingCartService.addSession(session1, newUser);
        ShoppingCart cartFromDB = shoppingCartService.getByUser(newUser);
        System.out.println("Received SC: " + cartFromDB);

        OrderService orderService = appContext.getBean(OrderService.class);
        orderService.completeOrder(cartFromDB);
        orderService.getOrdersHistory(newUser).forEach(System.out::println);
    }
}
