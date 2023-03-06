package suso.backend.domain.certificates;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import suso.backend.domain.certificates.dto.CertificatesUpdateDto;
import suso.backend.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column
    private String title;

    @Column
    private String agency;

    @Column
    private String imageUrl;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;

    public void update(CertificatesUpdateDto certificatesUpdateDto){
        this.title = certificatesUpdateDto.getTitle();
        this.agency = certificatesUpdateDto.getAgency();
        this.imageUrl = certificatesUpdateDto.getImageUrl();
        this.dateOfCompletion = certificatesUpdateDto.getDateOfCompletion();
    }

    public CertificatesResponse toResponse(){
        return CertificatesResponse.builder()
                .id(this.id)
                .user(this.user)
                .title(this.title)
                .agency(this.agency)
                .imageUrl(this.imageUrl)
                .dateOfCompletion(this.dateOfCompletion)
                .build();
    }
}
