package suso.backend.api.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.image.ImageService;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(ApiUrl.IMAGE_UPLOAD)
    @ApiOperation(value = "이미지 저장 API")
    public String upload(MultipartFile file){
        if(file == null) return "file is null";

        if(file.getContentType().startsWith("image") == false) return "file is not image";

        return imageService.upload(file);
    }

}
