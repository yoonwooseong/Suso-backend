package suso.backend.domain.certificatesHashtag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificatesHashtag.dto.CertificatesHashtagRankResponse;
import suso.backend.domain.certificatesHashtag.dto.HashtagCountInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificatesHashtagService {

    private final CertificatesHashtagRepository certificatesHashtagRepository;

    public List<CertificatesHashtagRankResponse> findTop3HashtagRank(){
        List<HashtagCountInterface> hashtagCountList = certificatesHashtagRepository.findTop3HashtagRank();

        return toResponse(hashtagCountList);
    }

    private List<CertificatesHashtagRankResponse> toResponse(List<HashtagCountInterface> hashtagCountList){
        return hashtagCountList.stream()
                .map(hashtagCount -> CertificatesHashtagRankResponse.builder()
                    .count(hashtagCount.getCount())
                    .hashtagId(hashtagCount.getHashtagId())
                    .build())
                .collect(Collectors.toList());
    }
}
