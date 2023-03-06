package suso.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import suso.backend.domain.user.dto.UserDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse saveUser(UserDto userDto){
        return userRepository.save(createNewUser(userDto)).toResponse();
    }

    private User createNewUser(UserDto userDto){
        return User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .imageUrl(userDto.getImageUrl())
                .introduction(userDto.getIntroduction())
                .build();
    }
}
