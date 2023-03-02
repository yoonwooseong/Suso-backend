package suso.backend.domain.certificates;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
public class Certificates {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="USER_ID")
    private String userId;

    @Column
    private String title;

    @Column
    private String agency;

    @Column
    private String imageUrl;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfCompletion;
}
