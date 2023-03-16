package suso.backend.domain.user;

import lombok.*;
import suso.backend.domain.user.dto.UserResponse;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column
    private String introduction;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserResponse toResponse() {
        return UserResponse.builder()
                .account(this.account)
                .name(this.name)
                .email(this.email)
                .imageUrl(this.imageUrl)
                .introduction(this.introduction)
                .role(this.role)
                .build();
    }
}
