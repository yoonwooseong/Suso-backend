package suso.backend.api.controller;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "인기 해시태크 조회 API")
    public List<CertificatesHashtagRankResponse> rankT0P3(){
        return certificatesHashtagService.findTop3HashtagRank();
    }
}
