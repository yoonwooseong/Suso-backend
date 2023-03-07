package suso.backend.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.user.dto.UserDto;
import suso.backend.domain.user.dto.UserResponse;
import suso.backend.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(ApiUrl.USER_JOIN)
    public UserResponse join(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
}
