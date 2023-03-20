package suso.backend.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.user.dto.UserRequest;
import suso.backend.domain.user.dto.UserResponse;
import suso.backend.domain.user.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(ApiUrl.USER_JOIN)
    public UserResponse join(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    @PostMapping(ApiUrl.USER_LOGIN)
    public UserResponse login(@RequestBody UserRequest userRequest) throws Exception {
        return userService.login(userRequest);
    }

    @GetMapping(ApiUrl.USER_GET)
    public UserResponse getUser(@RequestParam String account) throws Exception {
        return userService.getUser(account);
    }

}
