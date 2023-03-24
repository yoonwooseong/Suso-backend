package suso.backend.domain.hashtag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.certificates.CertificatesRepository;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.certificatesHashtag.CertificatesHashtagRepository;
import suso.backend.domain.hashtag.dto.HashtagResponse;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static suso.backend.domain.common.CertificatesFixture.*;
import static suso.backend.domain.common.HashtagFixture.HASHTAG_EXAMPLE;
import static suso.backend.domain.common.UserFixture.*;
import static suso.backend.domain.common.UserFixture.INTRODUCTION;

@SpringBootTest
class HashtagServiceTest {

    @Autowired
    HashtagService hashtagService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CertificatesRepository certificatesRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    CertificatesHashtagRepository certificatesHashtagRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByTagName(){
        // given
        User savedUser = userRepository.save(createUser());
        Certificates certificates = createCertificates(savedUser);
        Certificates savedCertificates = certificatesRepository.save(certificates);
        List<Hashtag> hashtags = hashtagRepository.saveAll(createHashtag());
        certificatesHashtagRepository.saveAll(createCertificatesHashtag(savedCertificates, hashtags));

        String tagName = HASHTAG_EXAMPLE.get(0);
        int page = 0;
        int size = 1;

        // when
        List<HashtagResponse> byTagName = hashtagService.findByTagName(tagName, page, size);

        // then
        assertAll(
                () -> assertThat(byTagName.get(0).getCertificatesTitle()).isEqualTo(savedCertificates.getTitle())
        );
    }

    private User createUser(){
        return User.builder()
                .account(ACCOUNT)
                .password(passwordEncoder.encode(PASSWORD))
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
                .build();
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

    private List<Hashtag> createHashtag(){
        List<Hashtag> resultHashEntityList = new ArrayList<>();
        for (int i = 0; i < HASHTAG_EXAMPLE.size(); i++){
            resultHashEntityList.add(Hashtag.builder()
                    .tagName(HASHTAG_EXAMPLE.get(i))
                    .build());
        }

        return resultHashEntityList;
    }

    private List<CertificatesHashtag> createCertificatesHashtag(Certificates certificates, List<Hashtag> hashtagList){
        List<CertificatesHashtag> resultEntityList = new ArrayList<>();
        for (int i = 0; i < hashtagList.size(); i++){
            CertificatesHashtag certificatesHashtag = CertificatesHashtag.builder()
                    .certificates(certificates)
                    .hashtag(hashtagList.get(i))
                    .build();

            resultEntityList.add(certificatesHashtag);
        }

        return resultEntityList;
    }
}