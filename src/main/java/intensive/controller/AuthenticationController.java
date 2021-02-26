package intensive.controller;

import intensive.model.dto.user.UserRequestDto;
import intensive.model.dto.user.UserResponseDto;
import intensive.security.AuthenticationService;
import intensive.service.mapper.UserMapper;
import javax.validation.Valid;
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
    public UserResponseDto register(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userMapper.mapToUserResponseDto(
                authService.register(userRequestDto.getEmail(), userRequestDto.getPassword()));
    }
}
