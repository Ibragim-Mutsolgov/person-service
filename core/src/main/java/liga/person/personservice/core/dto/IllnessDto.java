package liga.person.personservice.core.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class IllnessDto {

    private Long id;

    private Long medicalCardId;

    private Long typeId;

    private String heaviness;

    private Instant appearanceDttm;

    private Date recoveryDt;
}
