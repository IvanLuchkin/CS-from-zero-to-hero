package cinema.controller;

import cinema.model.dto.user.UserResponseDto;
import cinema.service.UserService;
import cinema.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public UserResponseDto getUserByEmail(@RequestParam("email") String email) {
        return userMapper.mapToUserResponseDto(userService.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User with email " + email + " does not exist")));
    }
}
