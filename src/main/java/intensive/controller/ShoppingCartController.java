package intensive.controller;

import intensive.model.dto.shoppingcart.ShoppingCartResponseDto;
import intensive.service.IntensiveService;
import intensive.service.ShoppingCartService;
import intensive.service.UserService;
import intensive.service.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final IntensiveService intensiveService;
    private final ShoppingCartMapper shoppingCartMapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  IntensiveService intensiveService,
                                  ShoppingCartMapper shoppingCartMapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.intensiveService = intensiveService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication,
                                @RequestParam("movieSessionId") Long sessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        shoppingCartService.addSession(intensiveService.get(sessionId),
                userService.findByEmail(userDetails.getUsername()).get());
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getUsersShoppingCart(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return shoppingCartMapper.mapToShoppingCartResponseDto(
                shoppingCartService.getByUser(
                        userService.findByEmail(userDetails.getUsername()).get()));
    }
}
