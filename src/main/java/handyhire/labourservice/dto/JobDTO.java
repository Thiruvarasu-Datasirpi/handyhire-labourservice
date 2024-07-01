package handyhire.labourservice.dto;

import handyhire.labourservice.utils.ENUMS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
    @NotNull(message = "Job ID cannot be null")
    private int id;

    @NotNull(message = "Customer ID cannot be null")
    private int customerId;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;

    @NotBlank(message = "Location is mandatory")
    @Size(max = 255, message = "Location cannot be longer than 255 characters")
    private String location;

    @Positive(message = "Hourly rate must be a positive number")
    private double hourlyRate;

    private ENUMS.JOB_STATUS status;
}
