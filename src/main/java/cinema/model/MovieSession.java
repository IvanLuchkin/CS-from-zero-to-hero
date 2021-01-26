package cinema.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class MovieSession {
    private Long id;
    private Movie movie;
    private CinemaHall cinemaHall;
    private LocalDateTime showTime;

    public MovieSession(Movie movie, CinemaHall cinemaHall, LocalDateTime showTime) {
        this.movie = movie;
        this.cinemaHall = cinemaHall;
        this.showTime = showTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "MovieSession{"
                + "id=" + id
                + ", movie=" + movie
                + ", cinemaHall=" + cinemaHall
                + ", showTime=" + showTime
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieSession that = (MovieSession) o;
        return id.equals(that.id) && movie.equals(that.movie)
                && cinemaHall.equals(that.cinemaHall) && showTime.equals(that.showTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie, cinemaHall, showTime);
    }
}
