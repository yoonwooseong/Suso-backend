package suso.backend.domain.certificatesHashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import suso.backend.domain.certificatesHashtag.dto.HashtagCountInterface;

import java.util.List;

public interface CertificatesHashtagRepository extends JpaRepository<CertificatesHashtag, Long> {

    @Query(value =
            "select count(*) as count, HASHTAG_ID as hashtagId from Certificates_Hashtag as ch GROUP BY ch.HASHTAG_ID ORDER BY count DESC LIMIT 3;"
            , nativeQuery = true
    )
    List<HashtagCountInterface> findTop3HashtagRank();
}
