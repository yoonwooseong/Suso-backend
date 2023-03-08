package suso.backend.domain.certificates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import suso.backend.domain.certificates.dto.CertificatesResponse;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.certificatesHashtag.CertificatesHashtag;
import suso.backend.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CERTIFICATES_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    // 해시태그에 cascade 옵션을 사용하지 않은 이유 :
    // 1. 해시태그가 수료증의 개인 소유가 아닐 가능성이 있다.
    // 2. 같은 애그리거트 루트(aggregate root)에 속하는지 애매하다.
    // 3. 해시태그 관련된 기능이 추가 될 경우 이슈를 유발할 수 있다고 판단.
    @OneToMany(mappedBy = "certificates")
    private List<CertificatesHashtag> hashtags = new ArrayList<>();

    @Column
    private String title;

    @Column
    private String instructor;

    @Column
    private String agency;

    @Column
    private String imageUrl;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;

    public void update(CertificatesUpdateDto certificatesUpdateDto){
        this.title = certificatesUpdateDto.getTitle();
        this.instructor = certificatesUpdateDto.getInstructor();
        this.agency = certificatesUpdateDto.getAgency();
        this.imageUrl = certificatesUpdateDto.getImageUrl();
        this.dateOfCompletion = certificatesUpdateDto.getDateOfCompletion();
    }

    public CertificatesResponse toResponse(){
        return CertificatesResponse.builder()
                .id(this.id)
                .user(this.user)
                .title(this.title)
                .instructor(this.instructor)
                .agency(this.agency)
                .imageUrl(this.imageUrl)
                .dateOfCompletion(this.dateOfCompletion)
                .build();
    }
}
