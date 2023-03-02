package suso.backend.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String imageUrl;

    @Column
    private String introduction;

}
