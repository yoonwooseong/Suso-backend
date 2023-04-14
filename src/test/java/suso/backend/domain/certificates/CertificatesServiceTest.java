package suso.backend.domain.certificates;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.dto.CertificatesResponse;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.certificatesHashtag.CertificatesHashtagRepository;
import suso.backend.domain.hashtag.Hashtag;
import suso.backend.domain.hashtag.HashtagRepository;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static suso.backend.domain.common.CertificatesFixture.*;
import static suso.backend.domain.common.UserFixture.*;
import static suso.backend.domain.common.HashtagFixture.*;

@SpringBootTest
class CertificatesServiceTest {
    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    CertificatesService certificatesService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    CertificatesHashtagRepository certificatesHashtagRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @AfterEach
    void afterEach(){
//        userRepository.deleteAll();
//        certificatesRepository.deleteAll();
//        hashtagRepository.deleteAll();
//        certificatesHashtagRepository.deleteAll();
    }

    @Test
    public void findAllByUserId(){
        // given
        User user = createUser();
        User savedUser = userRepository.save(user);
        Certificates firstCertificates = createCertificates(savedUser);
        Certificates secondCertificates = createCertificates(savedUser);
        Certificates thirdCertificates = createCertificates(savedUser);
        certificatesRepository.save(firstCertificates);
        certificatesRepository.save(secondCertificates);
        certificatesRepository.save(thirdCertificates);

        // when
        List<CertificatesResponse> allByUserId = certificatesService.findAllByUserId(savedUser.getId());

        // then
        assertAll(
                () -> assertThat(allByUserId.size()).isEqualTo(3),
                () -> assertThat(allByUserId.get(0).getUserId()).isEqualTo(savedUser.getId()),
                () -> assertThat(allByUserId.get(1).getUserId()).isEqualTo(savedUser.getId()),
                () -> assertThat(allByUserId.get(2).getUserId()).isEqualTo(savedUser.getId())
        );
    }

    @Transactional
    @Test
    public void saveCertificate(){
        // when
        User savedUser = userRepository.save(createUser());
        CertificatesDto certificatesDto = createCertificatesDto();

        // given
        certificatesService.saveCertificates(certificatesDto);

        // then
        Certificates savedCertificates = certificatesRepository.findById(certificatesDto.getId()).orElse(null);

        assertAll(
                () -> assertThat(savedCertificates.getTitle()).isEqualTo(certificatesDto.getTitle()),
                () -> assertThat(savedCertificates.getUser().getId()).isEqualTo(certificatesDto.getUserId()),
                () -> assertThat(savedCertificates.getInstructor()).isEqualTo(certificatesDto.getInstructor()),
                () -> assertThat(savedCertificates.getAgency()).isEqualTo(certificatesDto.getAgency()),
                () -> assertThat(savedCertificates.getImageUrl()).isEqualTo(certificatesDto.getImageUrl()),
                () -> assertThat(savedCertificates.getDateOfCompletion()).isEqualTo(certificatesDto.getDateOfCompletion())
//                () -> assertThat(savedCertificates.getHashtags().size()).isEqualTo(certificatesDto.getHashtags().size())
        );

        // DB 값 확인
    }

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
        User savedUser = userRepository.save(createUser());
        Certificates certificates = createCertificates(savedUser);
        Certificates savedCertificates = certificatesRepository.save(certificates);
        List<Hashtag> hashtags = hashtagRepository.saveAll(createHashtag());
        certificatesHashtagRepository.saveAll(createCertificatesHashtag(savedCertificates, hashtags));

        Certificates findCertificates = certificatesRepository.findById(savedCertificates.getId())
                .orElseThrow(() -> new IllegalArgumentException("id : " + savedCertificates.getId() + " 수료증이 존재하지 않습니다."));

        // given
        findCertificates.update(updateCertificatesDto);
        Certificates updatedCertificates = certificatesRepository.save(findCertificates);
        List<Hashtag> updatedHashtags = hashtagRepository.saveAll(updateHashtag()); // updateHashtag() 필요
        List<CertificatesHashtag> savedUpdateCertificatesHashtags = certificatesHashtagRepository.saveAll(createCertificatesHashtag(updatedCertificates, updatedHashtags));

        // then
        assertAll(
                () -> assertThat(updatedCertificates.getId()).isEqualTo(findCertificates.getId()),
                () -> assertThat(updatedCertificates.getTitle()).isEqualTo(updateCertificatesDto.getTitle()),
                () -> assertThat(updatedCertificates.getInstructor()).isEqualTo(updateCertificatesDto.getInstructor()),
                () -> assertThat(updatedCertificates.getAgency()).isEqualTo(updateCertificatesDto.getAgency()),
                () -> assertThat(updatedCertificates.getImageUrl()).isEqualTo(updateCertificatesDto.getImageUrl()),
                () -> assertThat(updatedCertificates.getDateOfCompletion()).isEqualTo(updateCertificatesDto.getDateOfCompletion()),
                () -> assertThat(savedUpdateCertificatesHashtags.size()).isEqualTo(updatedHashtags.size())
        );
    }

    private User createUser(){
        return User.builder()
                .id(USER_ID)
                .account(ACCOUNT)
                .password(passwordEncoder.encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
                .build();
    }

    private CertificatesDto createCertificatesDto(){
        CertificatesDto createdCertificates = new CertificatesDto(
                CERTIFICATES_ID,
                USER_ID,
                CERTIFICATES_TITLE,
                CERTIFICATES_INSTRUCTOR,
                AGENCY,
                CERTIFICATES_IMAGE_URL,
                HASHTAG_EXAMPLE,
                DATE_COMPLETION
        );
        return createdCertificates;
    }

    private List<Hashtag> createHashtag(){
        return HASHTAG_EXAMPLE.stream()
                .map(tagName -> Hashtag.builder()
                        .tagName(tagName)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Hashtag> updateHashtag(){
        return UPDATE_HASHTAG_EXAMPLE.stream()
                .map(tagName -> Hashtag.builder()
                        .tagName(tagName)
                        .build())
                .collect(Collectors.toList());
    }


    private List<CertificatesHashtag> createCertificatesHashtag(Certificates certificates, List<Hashtag> hashtagList){
        return hashtagList.stream()
                .map(hashtag -> CertificatesHashtag.builder()
                        .certificates(certificates)
                        .hashtag(hashtag)
                        .build())
                .collect(Collectors.toList());

    }

    private Certificates createCertificates(User user){
        return Certificates.builder()
                .user(user)
                .title(CERTIFICATES_TITLE)
                .instructor(CERTIFICATES_INSTRUCTOR)
                .agency(AGENCY)
                .imageUrl(CERTIFICATES_IMAGE_URL)
                .dateOfCompletion(DATE_COMPLETION)
                .build();
    }
}