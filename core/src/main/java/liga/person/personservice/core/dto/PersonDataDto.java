package liga.person.personservice.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDataDto {

    private Long id;

    private String lastName;

    private String firstName;

    private LocalDate birthDt;

    private Integer age;

    private String sex;

    private Long contactId;

    private Long medicalCardId;

    private Long parentId;
}
