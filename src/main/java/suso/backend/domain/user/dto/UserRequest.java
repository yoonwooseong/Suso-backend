package suso.backend.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String account;
    private String password;
    private String name;
    private String email;
    private String imageUrl;
    private String introduction;
}
