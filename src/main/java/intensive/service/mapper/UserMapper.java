package intensive.service.mapper;

import intensive.model.User;
import intensive.model.dto.user.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponseDto mapToUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
