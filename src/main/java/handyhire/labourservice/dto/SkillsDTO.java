package handyhire.labourservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class SkillsDTO {

    private int id;
    @NotEmpty(message = "Skills must not be empty")
    @NotNull(message = "Skills must not be empty")
    private String skill;
}
