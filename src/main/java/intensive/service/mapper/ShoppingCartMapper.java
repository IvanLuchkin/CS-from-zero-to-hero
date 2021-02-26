package intensive.service.mapper;

import intensive.model.ShoppingCart;
import intensive.model.Ticket;
import intensive.model.dto.shoppingcart.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto mapToShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
