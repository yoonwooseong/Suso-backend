package suso.backend.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static suso.backend.domain.common.UserFixture.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    void join(){
        // given
        User createdUser = createUser();

        // when
        User savedUser = userRepository.save(createdUser);

        // then
        assertAll(
                () -> assertThat(savedUser.getId()).isEqualTo(createdUser.getId()),
                () -> assertThat(savedUser.getAccount()).isEqualTo(createdUser.getAccount()),
                () -> assertThat(savedUser.getName()).isEqualTo(createdUser.getName()),
                () -> assertThat(savedUser.getEmail()).isEqualTo(createdUser.getEmail()),
                () -> assertThat(savedUser.getImageUrl()).isEqualTo(createdUser.getImageUrl()),
                () -> assertThat(savedUser.getIntroduction()).isEqualTo(createdUser.getIntroduction())
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