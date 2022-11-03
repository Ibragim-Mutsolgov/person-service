package liga.person.personservice.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalCardDto {

    private Long id;

    private String clientStatus;

    private String medStatus;

    private LocalDate registryDt;

    private String comment;
}
