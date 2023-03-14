package suso.backend.domain.hashtag.dto;

import lombok.Builder;
import lombok.Data;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;

import java.util.List;

@Data
@Builder
public class HashtagResponse {
    private Long hashtagId;
    private Long certificatesId;
    private String certificatesTitle;
    private String certificatesAgency;
    private String certificatesImage;
    private String certificatesInstructor;
}
