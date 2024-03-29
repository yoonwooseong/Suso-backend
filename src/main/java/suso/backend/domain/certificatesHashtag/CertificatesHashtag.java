package suso.backend.domain.certificatesHashtag;

import lombok.*;
import suso.backend.domain.certificates.Certificates;
import suso.backend.domain.hashtag.Hashtag;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CertificatesHashtag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CERTIFICATES_ID")
    private Certificates certificates;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HASHTAG_ID")
    private Hashtag hashtag;

}
