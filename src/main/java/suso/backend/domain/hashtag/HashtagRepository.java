package suso.backend.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    @Query(value = "select h from Hashtag as h WHERE h.tagName IN :tags")
    List<Hashtag> findByTagNames(@Param("tags") List<String> tags);

//    @Query(value = "select h from Hashtag h join fetch h.certificatesList where h.tagName = :tagName")
//    Hashtag findByTagName(@Param("tagName") String tagName);

    @Query(value = "select ch from CertificatesHashtag ch join fetch ch.certificates c join fetch ch.hashtag h where h.tagName = :tagName")
    List<CertificatesHashtag> findByTagName(@Param("tagName") String tagName);
}
