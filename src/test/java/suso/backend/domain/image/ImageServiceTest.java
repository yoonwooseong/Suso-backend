package suso.backend.domain.image;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

@SpringBootTest
class ImageServiceTest {
    @Autowired
    ImageService imageService;


    @Test
    void uploadImage() throws IOException {
        // given
        URL resource = getClass().getResource("/image/test.png");
        MockMultipartFile multipartFile = new MockMultipartFile(
                "image",
                "test.png",
                "image/png",
                new FileInputStream(resource.getFile())
        );

        // when
        String savedPath = imageService.upload(multipartFile);

        // then

    }
}