package handyhire.labourservice.enitiy;

import handyhire.labourservice.utils.ENUMS;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "labour")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Labour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private ENUMS.Gender gender;

    @Column(nullable = false)
    private String qualification;

    @Column(nullable = false)
    private String experience;

    @OneToMany(mappedBy = "labour", cascade = CascadeType.ALL)
    private List<Skills> skills;

    @Column(nullable = false)
    private ENUMS.Availability availability;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private ENUMS.Status status;

    @Column(nullable = false)
    private double hourlyRate;

    @Column(nullable = false)
    private ENUMS.ROLES role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
