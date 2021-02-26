package intensive.controller;

import intensive.model.dto.user.UserResponseDto;
import intensive.service.UserService;
import intensive.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userMapper.mapToUserResponseDto(
                userService.findByEmail(userDetails.getUsername()).get());
    }
}
