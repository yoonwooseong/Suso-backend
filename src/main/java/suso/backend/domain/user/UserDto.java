package suso.backend.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String imageUrl;
    private String introduction;
}
