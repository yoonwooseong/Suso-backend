package suso.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String account;
    private String password;
    private String name;
    private String email;
    private String imageUrl;
    private String introduction;
}
