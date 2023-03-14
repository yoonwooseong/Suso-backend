package suso.backend.domain.hashtag;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HASHTAG_ID")
    private Long id;

    @Column(unique = true)
    private String tagName;

    @BatchSize(size = 100)
    @OneToMany(mappedBy = "hashtag")
    private List<CertificatesHashtag> certificatesList = new ArrayList<>();
}
