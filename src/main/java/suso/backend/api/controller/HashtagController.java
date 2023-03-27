package suso.backend.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.hashtag.HashtagService;
import suso.backend.domain.hashtag.dto.HashtagResponse;

import java.util.List;

@Api(tags = "해시태그 api")
@RequiredArgsConstructor
@RestController
public class HashtagController {
    private final HashtagService hashtagService;

    @GetMapping(ApiUrl.HASH_CERTIFICATES)
    @ApiOperation(value = "해시태크 기준 수료증 목록 조회 API")
    public List<HashtagResponse> findCertificateByTagName(@ApiParam(value="해시태그명", required = true) @RequestParam String tagName, @ApiParam(value="현재 페이지", required = true) @RequestParam int page, @ApiParam(value="가져올 개수", required = true) @RequestParam int size){
        return hashtagService.findByTagName(tagName, page, size);
    };
}
