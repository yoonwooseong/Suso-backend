package suso.backend.domain.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${upload.path}")
    private String locationPath;

    public String upload(MultipartFile file){
        String originName = file.getOriginalFilename();
        String fileName = originName.substring(originName.lastIndexOf("\\") + 1);
        String uuid = UUID.randomUUID().toString();
        String folderPath = makeFolder();

        String path = locationPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
        Path savePath = Paths.get(path);
        try{
            file.transferTo(savePath);
        }catch (IOException e){

        }

        return path;
    }

    private String makeFolder(){
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = date.replace("/", File.separator);

        File uploadPathFolder = new File(locationPath, folderPath);

        if(uploadPathFolder.exists() == false) uploadPathFolder.mkdirs();

        return folderPath;
    }
}
