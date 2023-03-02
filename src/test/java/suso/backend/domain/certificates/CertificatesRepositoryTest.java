package suso.backend.domain.certificates;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CertificatesRepositoryTest {

    @Autowired
    CertificatesRepository certificatesRepository;

    @Test
    public void tableTest(){
        long count = certificatesRepository.count();

        System.out.println(count);

    }
}