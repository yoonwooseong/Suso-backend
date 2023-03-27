package suso.backend.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.CertificatesService;
import suso.backend.domain.certificates.dto.CertificatesResponse;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;

import java.util.List;

@Api(tags = "수료증 api")
@RestController
@RequiredArgsConstructor
public class CertificatesController {
    private final CertificatesService certificatesService;

    @GetMapping(ApiUrl.CERTIFICATES)
    @ApiOperation(value = "수료증 전체 조회 API")
    public List<CertificatesResponse> getCertificates(@ApiParam(value="유저 ID", required = true) @RequestParam Long id){
        return certificatesService.findAllByUserId(id);
    }

    @PostMapping(ApiUrl.CERTIFICATES_SAVE)
    @ApiOperation(value = "수료증 저장 API")
    public void save(@ApiParam(value="저장할 수료증 정보", required = true) @RequestBody CertificatesDto certificatesDto){
        certificatesService.saveCertificates(certificatesDto);
    }

    @PutMapping(ApiUrl.CERTIFICATES_UPDATE)
    @ApiOperation(value = "수료증 수정 API")
    public void update(@ApiParam(value="수정할 수료증 ID", required = true) @PathVariable Long id,@ApiParam(value="수정할 수료증 정보", required = true) @RequestBody CertificatesUpdateDto certificatesUpdateDto){
        certificatesService.updateCertificates(id, certificatesUpdateDto);
    }

    @DeleteMapping(ApiUrl.CERTIFICATES_DELETE)
    @ApiOperation(value = "수료증 삭제 API")
    public void delete(@ApiParam(value="삭제할 수료증 ID", required = true) @RequestParam Long certificatesId){
        certificatesService.deleteCertificates(certificatesId);
    }
}
