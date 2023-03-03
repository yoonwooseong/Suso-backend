package suso.backend.domain.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private String imageUrl;
    private String introduction;
}
