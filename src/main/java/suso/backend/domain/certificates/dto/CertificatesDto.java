package suso.backend.domain.certificates.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import suso.backend.domain.user.User;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificatesDto {
    private Long id;
    private Long userId;
    private String title;
    private String instructor;
    private String agency;
    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;
}
