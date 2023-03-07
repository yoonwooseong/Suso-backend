package suso.backend.domain.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static suso.backend.domain.common.UserFixture.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void join(){
        // given
        User createdUser = createUser();

        // when
        User savedUser = userRepository.save(createdUser);

        // then
        assertAll(
                () -> assertThat(savedUser.getId()).isEqualTo(createdUser.getId()),
                () -> assertThat(savedUser.getUsername()).isEqualTo(createdUser.getUsername()),
                () -> assertThat(savedUser.getName()).isEqualTo(createdUser.getName()),
                () -> assertThat(savedUser.getEmail()).isEqualTo(createdUser.getEmail()),
                () -> assertThat(savedUser.getImageUrl()).isEqualTo(createdUser.getImageUrl()),
                () -> assertThat(savedUser.getIntroduction()).isEqualTo(createdUser.getIntroduction())
        );

    }

    private User createUser(){
        User user = User.builder()
            .id(USER_ID)
            .username(USERNAME)
            .name(NAME)
            .email(EMAIL)
            .imageUrl(IMAGE_URL)
            .introduction(INTRODUCTION)
            .build();

        return user;
    }
}