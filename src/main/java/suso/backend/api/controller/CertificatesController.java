package suso.backend.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import suso.backend.api.util.ApiUrl;
import suso.backend.domain.certificates.dto.CertificatesDto;
import suso.backend.domain.certificates.CertificatesService;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;

@RequiredArgsConstructor
@RestController
public class CertificatesController {
    private final CertificatesService certificatesService;

    @GetMapping(ApiUrl.CERTIFICATES_SAVE)
    public void save(@RequestBody CertificatesDto certificatesDto){
        certificatesService.saveCertificates(certificatesDto);
    }

    @PostMapping(ApiUrl.CERTIFICATES_UPDATE)
    public void update(@PathVariable Long id, @RequestBody CertificatesUpdateDto certificatesUpdateDto){
        certificatesService.updateCertificates(id, certificatesUpdateDto);
    }

    @PostMapping(ApiUrl.CERTIFICATES_DELETE)
    public void delete(@RequestParam Long certificatesId){
        certificatesService.deleteCertificates(certificatesId);
    }
}
