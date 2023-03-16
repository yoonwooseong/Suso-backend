package suso.backend.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import suso.backend.domain.user.Role;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String account;
    private String name;
    private String email;
    private String imageUrl;
    private String introduction;
    private Role role;
    private String token;
}
