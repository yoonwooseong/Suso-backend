package suso.backend.domain.certificatesHashtag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificatesHashtag.dto.CertificatesHashtagRankResponse;
import suso.backend.domain.certificatesHashtag.dto.HashtagCountInterface;

import java.util.ArrayList;
import java.util.List;

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
        List<CertificatesHashtagRankResponse> responseList = new ArrayList<>();
        for(int i = 0; i < hashtagCountList.size(); i++){
            CertificatesHashtagRankResponse certificatesHashtagRankResponse = CertificatesHashtagRankResponse.builder()
                    .count(hashtagCountList.get(i).getCount())
                    .hashtagId(hashtagCountList.get(i).getHashtagId())
                    .build();

            responseList.add(certificatesHashtagRankResponse);
        }

        return responseList;
    }
}
