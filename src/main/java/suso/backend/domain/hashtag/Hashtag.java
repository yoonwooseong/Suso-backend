package suso.backend.domain.hashtag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HASHTAG_ID")
    private Long id;

    @Column
    private String tagName;

    @OneToMany(mappedBy = "hashtag")
    private List<CertificatesHashtag> certificatesList = new ArrayList<>();
}
