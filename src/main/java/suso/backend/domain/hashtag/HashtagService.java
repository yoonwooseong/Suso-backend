package suso.backend.domain.hashtag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.hashtag.dto.HashtagResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<HashtagResponse> findByTagName(String tagName){
        List<HashtagResponse> result = new ArrayList<>();
        List<CertificatesHashtag> byTagName = hashtagRepository.findByTagName(tagName);

        for (int i = 0; i < byTagName.size(); i++){
            result.add(HashtagResponse.builder()
                    .hashtagId(byTagName.get(i).getHashtag().getId())
                    .certificatesId(byTagName.get(i).getCertificates().getId())
                    .certificatesTitle(byTagName.get(i).getCertificates().getTitle())
                    .certificatesAgency(byTagName.get(i).getCertificates().getAgency())
                    .certificatesImage(byTagName.get(i).getCertificates().getImageUrl())
                    .certificatesInstructor(byTagName.get(i).getCertificates().getInstructor())
                    .build());
        }

        return result;
    }

}
