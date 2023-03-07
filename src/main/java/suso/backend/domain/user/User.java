package suso.backend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import suso.backend.domain.user.dto.UserResponse;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column
    private String introduction;

    public UserResponse toResponse() {
        return UserResponse.builder()
                .username(this.username)
                .name(this.name)
                .email(this.email)
                .imageUrl(this.imageUrl)
                .introduction(this.introduction)
                .build();
    }
}
