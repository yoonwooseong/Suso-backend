package suso.backend.api.controller;

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
    public List<HashtagResponse> findCertificateByTagName(@RequestParam String tagName){
        return hashtagService.findByTagName(tagName);
    };
}
