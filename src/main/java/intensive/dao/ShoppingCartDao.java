package intensive.dao;

import intensive.model.ShoppingCart;
import intensive.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);
        
    ShoppingCart getByUser(User user);
        
    void update(ShoppingCart shoppingCart);
}
