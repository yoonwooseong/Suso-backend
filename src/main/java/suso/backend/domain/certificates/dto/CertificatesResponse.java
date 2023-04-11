package suso.backend.domain.certificates.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import suso.backend.domain.user.User;

import java.time.LocalDate;

@Data
@Builder
@ApiModel(value = "수료증 정보")
public class CertificatesResponse {
    private Long id;
    private Long userId;
    private String title;
    private String instructor;
    private String agency;
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;
}
