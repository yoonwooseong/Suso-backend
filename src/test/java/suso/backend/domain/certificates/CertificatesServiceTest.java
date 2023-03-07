package suso.backend.domain.certificates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static suso.backend.domain.common.CertificatesFixture.*;
import static suso.backend.domain.common.CertificatesFixture.UPDATE_DATE_COMPLETION;
import static suso.backend.domain.common.UserFixture.*;
import static suso.backend.domain.common.UserFixture.INTRODUCTION;

@SpringBootTest
class CertificatesServiceTest {
    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void updateCertificate(){
        // when
        CertificatesUpdateDto updateCertificatesDto = new CertificatesUpdateDto(
                UPDATE_CERTIFICATES_TITLE,
                UPDATE_CERTIFICATES_INSTRUCTOR,
                UPDATE_AGENCY,
                UPDATE_CERTIFICATES_IMAGE_URL,
                UPDATE_DATE_COMPLETION
        );

        User user = createUser();
        User savedUser = userRepository.save(user);

        Certificates certificates = createCertificates(savedUser);
        Certificates savedCertificates = certificatesRepository.save(certificates);
        Certificates findCertificates = certificatesRepository.findById(savedCertificates.getId())
                .orElseThrow(() -> new IllegalArgumentException("id : " + savedCertificates.getId() + " 수료증이 존재하지 않습니다."));

        // given
        findCertificates.update(updateCertificatesDto);
        Certificates updatedCertificates = certificatesRepository.save(findCertificates);

        // then
        assertAll(
                () -> assertThat(updatedCertificates.getId()).isEqualTo(findCertificates.getId()),
                () -> assertThat(updatedCertificates.getTitle()).isEqualTo(updateCertificatesDto.getTitle()),
                () -> assertThat(updatedCertificates.getInstructor()).isEqualTo(updateCertificatesDto.getInstructor()),
                () -> assertThat(updatedCertificates.getAgency()).isEqualTo(updateCertificatesDto.getAgency()),
                () -> assertThat(updatedCertificates.getImageUrl()).isEqualTo(updateCertificatesDto.getImageUrl()),
                () -> assertThat(updatedCertificates.getDateOfCompletion()).isEqualTo(updateCertificatesDto.getDateOfCompletion())
        );
    }

    private User createUser(){
        User user = User.builder()
                .id(USER_ID)
                .username(USERNAME)
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
                .build();

        return user;
    }

    private Certificates createCertificates(User user){
        Certificates certificates = Certificates.builder()
                .id(CERTIFICATES_ID)
                .user(user)
                .title(CERTIFICATES_TITLE)
                .instructor(CERTIFICATES_INSTRUCTOR)
                .agency(AGENCY)
                .imageUrl(CERTIFICATES_IMAGE_URL)
                .dateOfCompletion(DATE_COMPLETION)
                .build();

        return certificates;
    }
}