package intensive.service.impl;

import intensive.dao.ShoppingCartDao;
import intensive.dao.TicketDao;
import intensive.model.Intensive;
import intensive.model.ShoppingCart;
import intensive.model.Ticket;
import intensive.model.User;
import intensive.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(Intensive intensive, User user) {
        Ticket ticket = new Ticket(intensive, user);
        ShoppingCart shoppingCart = shoppingCartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        shoppingCartDao.update(shoppingCart);
    }
}
