package suso.backend.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import suso.backend.domain.user.UserDto;
import suso.backend.domain.user.UserResponse;
import suso.backend.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public UserResponse join(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
}
