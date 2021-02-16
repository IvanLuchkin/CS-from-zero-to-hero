package cinema.controller;

import cinema.model.dto.user.UserRequestDto;
import cinema.model.dto.user.UserResponseDto;
import cinema.security.AuthenticationService;
import cinema.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(AuthenticationService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
        return userMapper.mapToUserResponseDto(
                authService.register(userRequestDto.getEmail(), userRequestDto.getPassword()));
    }
}
