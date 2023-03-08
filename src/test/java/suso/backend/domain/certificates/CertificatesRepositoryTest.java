package suso.backend.domain.certificates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

import static suso.backend.domain.common.CertificatesFixture.*;
import static suso.backend.domain.common.UserFixture.*;
import static suso.backend.domain.common.UserFixture.INTRODUCTION;


@SpringBootTest
class CertificatesRepositoryTest {

    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveCertificates(){
        // given
        User user = createUser();
        User savedUser = userRepository.save(user);

        Certificates certificates = createCertificates(savedUser);

        // when
        Certificates savedCertificates = certificatesRepository.save(certificates);

        // then
        assertAll(
                () -> assertThat(savedCertificates.getId()).isEqualTo(certificates.getId()),
                () -> assertThat(savedCertificates.getUser().getId()).isEqualTo(certificates.getUser().getId()),
                () -> assertThat(savedCertificates.getTitle()).isEqualTo(certificates.getTitle()),
                () -> assertThat(savedCertificates.getInstructor()).isEqualTo(certificates.getInstructor()),
                () -> assertThat(savedCertificates.getAgency()).isEqualTo(certificates.getAgency()),
                () -> assertThat(savedCertificates.getImageUrl()).isEqualTo(certificates.getImageUrl()),
                () -> assertThat(savedCertificates.getDateOfCompletion()).isEqualTo(certificates.getDateOfCompletion())
        );
    }

    @Test
    public void deleteCertificates(){
        User user = createUser();
        User savedUser = userRepository.save(user);
        Certificates certificates = createCertificates(savedUser);
        Certificates savedCertificates = certificatesRepository.save(certificates);

        certificatesRepository.deleteById(savedCertificates.getId());

        assertThat(certificatesRepository.findById(savedCertificates.getId()).orElse(null)).isNull();
    }

    private User createUser(){
        User user = User.builder()
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
                .user(user)
                .title(CERTIFICATES_TITLE)
                .agency(AGENCY)
                .imageUrl(CERTIFICATES_IMAGE_URL)
                .dateOfCompletion(DATE_COMPLETION)
                .build();

        return certificates;
    }
}