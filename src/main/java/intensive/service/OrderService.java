package intensive.service;

import intensive.model.Order;
import intensive.model.ShoppingCart;
import intensive.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);
            
    List<Order> getOrdersHistory(User user);
}
