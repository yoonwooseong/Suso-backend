package suso.backend.domain.certificates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.certificatesHashtag.CertificatesHashtagRepository;
import suso.backend.domain.hashtag.Hashtag;
import suso.backend.domain.hashtag.HashtagRepository;
import suso.backend.domain.user.User;
import suso.backend.domain.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
    UserRepository userRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    CertificatesHashtagRepository certificatesHashtagRepository;

    @Test
    public void saveCertificate(){
        // when
        User savedUser = userRepository.save(createUser());
        Certificates certificates = createCertificates(savedUser);

        // given
        Certificates savedCertificates = certificatesRepository.save(certificates);
        List<Hashtag> hashtags = hashtagRepository.saveAll(createHashtag());
        List<CertificatesHashtag> savedCertificatesHashtags = certificatesHashtagRepository.saveAll(createCertificatesHashtag(savedCertificates, hashtags));

        // then
        assertAll(
                () -> assertThat(savedCertificatesHashtags.get(0).getCertificates().getId()).isEqualTo(savedCertificates.getId()),
                () -> assertThat(savedCertificatesHashtags.size()).isEqualTo(hashtags.size())
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
                .name(NAME)
                .email(EMAIL)
                .imageUrl(IMAGE_URL)
                .introduction(INTRODUCTION)
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

    private List<Hashtag> updateHashtag(){
        List<Hashtag> resultUpdateHashEntityList = new ArrayList<>();
        for (int i = 0; i < UPDATE_HASHTAG_EXAMPLE.size(); i++){
            resultUpdateHashEntityList.add(Hashtag.builder()
                    .tagName(UPDATE_HASHTAG_EXAMPLE.get(i))
                    .build());
        }

        return resultUpdateHashEntityList;
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