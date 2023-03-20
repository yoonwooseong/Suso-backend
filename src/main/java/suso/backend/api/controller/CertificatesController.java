package suso.backend.api.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.CertificatesService;
import suso.backend.domain.certificates.dto.CertificatesResponse;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CertificatesController {
    private final CertificatesService certificatesService;

//    @GetMapping(ApiUrl.CERTIFICATES)
//    @ApiOperation(value = "수료증 전체 조회 API")
//    public List<CertificatesResponse> getCertificates(@RequestParam Long id){
//        certificatesService.findByUserId(id);
//        return null;
//    }


    @PostMapping(ApiUrl.CERTIFICATES_SAVE)
    @ApiOperation(value = "수료증 저장 API")
    public void save(@RequestBody CertificatesDto certificatesDto){
        certificatesService.saveCertificates(certificatesDto);
    }

    @PutMapping(ApiUrl.CERTIFICATES_UPDATE)
    @ApiOperation(value = "수료증 수정 API")
    public void update(@PathVariable Long id, @RequestBody CertificatesUpdateDto certificatesUpdateDto){
        certificatesService.updateCertificates(id, certificatesUpdateDto);
    }

    @DeleteMapping(ApiUrl.CERTIFICATES_DELETE)
    @ApiOperation(value = "수료증 삭제 API")
    public void delete(@RequestParam Long certificatesId){
        certificatesService.deleteCertificates(certificatesId);
    }
}
