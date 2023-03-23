package suso.backend.domain.certificates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificatesRepository extends JpaRepository<Certificates, Long> {

    @Query("select c from Certificates as c join fetch c.user as u where u.id = :userId")
    List<Certificates> findAllByUserId(@Param("userId") Long userId);
}
