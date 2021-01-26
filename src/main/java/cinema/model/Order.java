package cinema.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private Long id;
    private List<Ticket> tickets;
    private LocalDateTime orderDate;
    private User user;

    public Order(List<Ticket> tickets, LocalDateTime orderDate, User user) {
        this.tickets = tickets;
        this.orderDate = orderDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", tickets=" + tickets
                + ", orderDate=" + orderDate
                + ", user=" + user
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
        Order order = (Order) o;
        return id.equals(order.id) && tickets.equals(order.tickets)
                && orderDate.equals(order.orderDate) && user.equals(order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tickets, orderDate, user);
    }
}
