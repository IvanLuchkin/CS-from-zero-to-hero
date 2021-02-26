package intensive.service;

import intensive.model.Intensive;
import intensive.model.ShoppingCart;
import intensive.model.User;

public interface ShoppingCartService {
    void addSession(Intensive intensive, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
