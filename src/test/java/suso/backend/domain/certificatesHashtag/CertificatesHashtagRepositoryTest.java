package suso.backend.domain.certificatesHashtag;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.certificates.CertificatesRepository;
import suso.backend.domain.certificates.CertificatesService;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificatesHashtag.dto.CertificatesHashtagRankResponse;
import suso.backend.domain.certificatesHashtag.dto.HashtagCountInterface;
import suso.backend.domain.user.Role;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static suso.backend.domain.common.CertificatesFixture.*;
import static suso.backend.domain.common.CertificatesFixture.DATE_COMPLETION;
import static suso.backend.domain.common.UserFixture.*;
import static suso.backend.domain.common.UserFixture.INTRODUCTION;

@SpringBootTest
class CertificatesHashtagRepositoryTest {

    @Autowired
    CertificatesHashtagRepository certificatesHashtagRepository;

    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    CertificatesService certificatesService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findRank(){
        // given
        User user = createUser();
        User savedUser = userRepository.save(user);

        certificatesService.saveCertificates(createCertificatesDtoCustomHashtag(user, Lists.list("Java", "Python", "C")));
        certificatesService.saveCertificates(createCertificatesDtoCustomHashtag(user, Lists.list("Java", "Python")));
        certificatesService.saveCertificates(createCertificatesDtoCustomHashtag(user, Lists.list("Java")));

        // when
        List<HashtagCountInterface> hashtagCountList = certificatesHashtagRepository.findTop3HashtagRank();

        // then (테스트 선행 조건 : spring.jpa.hibernate.ddl-auto=create)
        assertAll(
                () -> assertThat(hashtagCountList.size()).isEqualTo(3),
                () -> assertThat(hashtagCountList.get(0).getHashtagId()).isEqualTo(1L)
        );

    }

    private User createUser(){
        User user = User.builder()
                .account(ACCOUNT)
                .password(passwordEncoder.encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
                .role(Role.USER)
                .build();

        return user;
    }

    private CertificatesDto createCertificatesDtoCustomHashtag(User user, List<String> hashtags){
        return CertificatesDto.builder()
                .userId(user.getId())
                .title(CERTIFICATES_TITLE)
                .agency(AGENCY)
                .imageUrl(CERTIFICATES_IMAGE_URL)
                .hashtags(hashtags)
                .dateOfCompletion(DATE_COMPLETION)
                .build();
    }
}