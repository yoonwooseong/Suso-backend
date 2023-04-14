package suso.backend.domain.hashtag;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.hashtag.dto.HashtagResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public List<HashtagResponse> findByTagName(String tagName, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size);

        return hashtagRepository.findByTagName(tagName, pageRequest)
                .stream()
                .map(certificatesHashtag -> HashtagResponse.builder()
                        .hashtagId(certificatesHashtag.getHashtag().getId())
                        .certificatesId(certificatesHashtag.getCertificates().getId())
                        .certificatesTitle(certificatesHashtag.getCertificates().getTitle())
                        .certificatesAgency(certificatesHashtag.getCertificates().getAgency())
                        .certificatesImage(certificatesHashtag.getCertificates().getImageUrl())
                        .certificatesInstructor(certificatesHashtag.getCertificates().getInstructor())
                        .build())
                .collect(Collectors.toList());
    }

}
