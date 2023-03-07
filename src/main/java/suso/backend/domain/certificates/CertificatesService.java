package suso.backend.domain.certificates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.dto.CertificatesResponse;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

@Service
@RequiredArgsConstructor
public class CertificatesService {
    private final CertificatesRepository certificatesRepository;
    private final UserRepository userRepository;

    public CertificatesResponse saveCertificates(CertificatesDto certificatesDto){
        return certificatesRepository.save(createNewCertificates(certificatesDto)).toResponse();
    }

    private Certificates createNewCertificates(CertificatesDto certificatesDto) {
        User createdUser = userRepository.findById(certificatesDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("id : "+certificatesDto.getUserId()+ " 사용자가 존재하지 않습니다."));

        return Certificates.builder()
                .id(certificatesDto.getId())
                .user(createdUser)
                .title(certificatesDto.getTitle())
                .instructor(certificatesDto.getInstructor())
                .agency(certificatesDto.getAgency())
                .imageUrl(certificatesDto.getImageUrl())
                .dateOfCompletion(certificatesDto.getDateOfCompletion())
                .build();
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
