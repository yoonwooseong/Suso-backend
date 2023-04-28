package suso.backend.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.user.dto.UserRequest;
import suso.backend.domain.user.dto.UserResponse;
import suso.backend.domain.user.UserService;

@Api(tags = "유저 api")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping(ApiUrl.USER_JOIN)
    @ApiOperation(value = "유저 등록 API")
    public UserResponse join(@ApiParam(value="회원가입 정보", required = true) @RequestBody UserRequest userRequest){
        return userService.saveUser(userRequest);
    }

    @PostMapping(ApiUrl.USER_DUPLICATE_ID)
    @ApiOperation(value = "중복확인 API")
    public Boolean duplicateId(@ApiParam(value="아이디", required = true) @RequestBody String account){
        return userService.isDuplicate(account);
    }

    @PostMapping(ApiUrl.USER_LOGIN)
    @ApiOperation(value = "유저 로그인 API")
    public UserResponse login(@ApiParam(value="로그인 요청 정보", required = true) @RequestBody UserRequest userRequest) throws Exception {
        return userService.login(userRequest);
    }

    @GetMapping(ApiUrl.USER_GET)
    @ApiOperation(value = "유저 정보 조회 API")
    public UserResponse getUser(@ApiParam(value="조회할 유저 계정", required = true) @RequestParam String account) throws Exception {
        return userService.getUser(account);
    }

}
