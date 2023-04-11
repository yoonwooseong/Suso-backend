package suso.backend.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import suso.backend.api.security.JwtProvider;
import suso.backend.domain.user.dto.UserRequest;
import suso.backend.domain.user.dto.UserResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static suso.backend.domain.common.UserFixture.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @AfterEach
    void afterEach(){
        userRepository.deleteAll();
    }

    @Test
    void login(){
        // given
        UserRequest userRequest = LOGIN_USER_REQUEST;
        User createdUser = createUser();
        User savedUser = userRepository.findByAccount(ACCOUNT).orElse(
                userRepository.save(createdUser)
        );

        // when
        User loginedUser = userRepository.findByAccount(userRequest.getAccount()).orElseThrow();
        UserResponse userResponse = UserResponse.builder()
                .id(loginedUser.getId())
                .account(loginedUser.getAccount())
                .name(loginedUser.getName())
                .email(loginedUser.getEmail())
                .imageUrl(loginedUser.getImageUrl())
                .introduction(loginedUser.getIntroduction())
                .role(loginedUser.getRole())
                .token(jwtProvider.createToken(loginedUser.toResponse()))
                .build();

        // then
        assertAll(
                () -> assertThat(savedUser.getId()).isEqualTo(userResponse.getId()),
                () -> assertThat(savedUser.getAccount()).isEqualTo(userResponse.getAccount()),
                () -> assertThat(savedUser.getName()).isEqualTo(userResponse.getName()),
                () -> assertThat(savedUser.getEmail()).isEqualTo(userResponse.getEmail()),
                () -> assertThat(savedUser.getImageUrl()).isEqualTo(userResponse.getImageUrl()),
                () -> assertThat(savedUser.getIntroduction()).isEqualTo(userResponse.getIntroduction())
        );
    }

    private User createUser(){
        User user = User.builder()
                .account(ACCOUNT)
                .password(passwordEncoder.encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
                .role(Role.USER)
                .build();

        return user;
    }
}