package suso.backend.api.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "유저 등록 API")
    public UserResponse join(@RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    @PostMapping(ApiUrl.USER_LOGIN)
    @ApiOperation(value = "유저 로그인 API")
    public UserResponse login(@RequestBody UserRequest userRequest) throws Exception {
        return userService.login(userRequest);
    }

    @GetMapping(ApiUrl.USER_GET)
    @ApiOperation(value = "유저 정보 조회 API")
    public UserResponse getUser(@RequestParam String account) throws Exception {
        return userService.getUser(account);
    }

}
