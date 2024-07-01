package handyhire.labourservice.dto;

import handyhire.labourservice.utils.ENUMS;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class LabourDTO {

    private int id;

    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotBlank(message = "Username must not be blank")
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Date of birth must not be null")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    private ENUMS.Gender gender;

    @NotBlank(message = "Qualification must not be blank")
    private String qualification;

    @NotBlank(message = "Experience must not be blank")
    private String experience;

    @NotEmpty(message = "Skills must not be empty")
    @NotNull(message = "Skills must not be empty")
    private List<SkillsDTO> skills;

    private ENUMS.Availability availability;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private ENUMS.Status status;

    private ENUMS.ROLES role;

    @PositiveOrZero(message = "Hourly rate must be positive or zero")
    private double hourlyRate;

}
