package suso.backend.domain.certificatesHashtag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CertificatesHashtagRepositoryTest {

    @Autowired
    CertificatesHashtagRepository certificatesHashtagRepository;

    @Test
    public void findRank(){
//        List<CertificatesHashtagRankResponse> hashtagRank = certificatesHashtagRepository.findHashtagRank();
//        System.out.println(" "+ hashtagRank.get(0));
    }
}