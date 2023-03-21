package suso.backend.domain.certificates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import suso.backend.domain.certificates.dto.CertificatesResponse;

import java.util.List;
import java.util.Optional;

public interface CertificatesRepository extends JpaRepository<Certificates, Long> {

    @Query("select c from Certificates as c join fetch c.user as u where u.id = :userId")
    List<Certificates> findAllByUserId(@Param("userId") Long userId);
}
