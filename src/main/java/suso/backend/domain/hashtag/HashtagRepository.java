package suso.backend.domain.hashtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {
    @Query(value = "select h from Hashtag as h WHERE h.tagName IN :tags")
    List<Hashtag> findByTagNames(@Param("tags") List<String> tags);

    Optional<Hashtag> findByTagName(String tagName);
}
