package suso.backend.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.certificatesHashtag.CertificatesHashtagService;
import suso.backend.domain.certificatesHashtag.dto.CertificatesHashtagRankResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CertificatesHashtagController {
    private final CertificatesHashtagService certificatesHashtagService;

    @GetMapping(ApiUrl.HASH_RANK)
    public List<CertificatesHashtagRankResponse> rank(){
        return certificatesHashtagService.findHashtagRank();
    }
}
