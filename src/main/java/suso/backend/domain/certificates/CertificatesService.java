package suso.backend.domain.certificates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.certificatesHashtag.CertificatesHashtagRepository;
import suso.backend.domain.hashtag.Hashtag;
import suso.backend.domain.hashtag.HashtagRepository;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificatesService {
    // @Transactional(readOnly = true)
    // 강제로 호출하지 않는 한 flush x
    // 변경 감지를 위한 스냅샷을 유지하지 않음
    private final CertificatesRepository certificatesRepository;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;
    private final CertificatesHashtagRepository certificatesHashtagRepository;

    @Transactional
    public void saveCertificates(CertificatesDto certificatesDto){
        Certificates savedCertificates = saveContent(certificatesDto);
        List<Hashtag> savedHashtagList = saveHashtag(certificatesDto);

        saveCertificatesHashtag(savedCertificates, savedHashtagList);
    }

    private Certificates saveContent(CertificatesDto certificatesDto){
        Certificates certificates = createNewCertificates(certificatesDto);
        return certificatesRepository.save(certificates);
    }

    private Certificates createNewCertificates(CertificatesDto certificatesDto) {
        User createdUser = userRepository.findById(certificatesDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("id : "+certificatesDto.getUserId()+ " 사용자가 존재하지 않습니다."));

        return Certificates.builder()
                .user(createdUser)
                .title(certificatesDto.getTitle())
                .instructor(certificatesDto.getInstructor())
                .agency(certificatesDto.getAgency())
                .imageUrl(certificatesDto.getImageUrl())
                .dateOfCompletion(certificatesDto.getDateOfCompletion())
                .build();
    }

    private List<Hashtag> saveHashtag(CertificatesDto certificatesDto){
        List<Hashtag> hashtagList = new ArrayList<>();
        for (int i = 0; i < certificatesDto.getHashtags().size(); i++){
            Hashtag hashtagEntity = hashtagRepository.findByTagName(certificatesDto.getHashtags().get(i)).orElse(
                    Hashtag.builder()
                            .tagName(certificatesDto.getHashtags().get(i))
                            .build()
            );
            hashtagList.add(hashtagEntity);
        }

        return hashtagRepository.saveAll(hashtagList);
    }

    private void saveCertificatesHashtag(Certificates certificates, List<Hashtag> hashtagList){
        List<CertificatesHashtag> certificatesHashtagList = new ArrayList<>();
        for (int i = 0; i < hashtagList.size(); i++){
            CertificatesHashtag certificatesHashtag = CertificatesHashtag.builder()
                    .certificates(certificates)
                    .hashtag(hashtagList.get(i))
                    .build();

            certificatesHashtagList.add(certificatesHashtag);
        }

        certificatesHashtagRepository.saveAll(certificatesHashtagList);
    }

    public void updateCertificates(Long id, CertificatesUpdateDto certificatesUpdateDto){
        Certificates certificatesEntity = certificatesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id : " + id + " 수료증이 존재하지 않습니다."));

        certificatesEntity.update(certificatesUpdateDto);
        certificatesRepository.save(certificatesEntity).toResponse();
    }

    public void deleteCertificates(Long certificatesId){
        certificatesRepository.deleteById(certificatesId);
    }
}
