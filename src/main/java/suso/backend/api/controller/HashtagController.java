package suso.backend.api.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.hashtag.HashtagService;
import suso.backend.domain.hashtag.dto.HashtagResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HashtagController {
    private final HashtagService hashtagService;

    @GetMapping(ApiUrl.HASH_CERTIFICATES)
    @ApiOperation(value = "해시태크 기준 수료증 목록 조회 API")
    public List<HashtagResponse> findCertificateByTagName(@RequestParam String tagName){
        return hashtagService.findByTagName(tagName);
    };
}
