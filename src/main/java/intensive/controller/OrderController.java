package intensive.controller;

import intensive.model.dto.order.OrderResponseDto;
import intensive.service.OrderService;
import intensive.service.ShoppingCartService;
import intensive.service.UserService;
import intensive.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return orderMapper.mapToOrderResponseDto(
                orderService.completeOrder(
                        shoppingCartService.getByUser(
                                userService.findByEmail(userDetails.getUsername()).get())));
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistoryByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return orderService.getOrdersHistory(
                userService.findByEmail(userDetails.getUsername()).get())
                .stream()
                .map(orderMapper::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }
}
