package suso.backend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse saveUser(UserDto userDto){
        return userRepository.save(createNewUser(userDto)).toResponse();
    }

    private User createNewUser(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .imageUrl(userDto.getImageUrl())
                .introduction(userDto.getIntroduction())
                .build();
    }
}
