package suso.backend.domain.certificatesHashtag.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CertificatesHashtagRankResponse {
    private Long count;
    private Long hashtagId;
}
