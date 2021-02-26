package intensive.dao;

import intensive.model.Order;
import intensive.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
