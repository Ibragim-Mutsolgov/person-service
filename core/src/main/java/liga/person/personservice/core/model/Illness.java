package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table(name = "illness")
public class Illness {

    @Id
    private Long id;

    @Column(name = "medical_card_id")
    private Long medicalCardId;

    @Column(name = "type_id")
    private Long typeId;

    private String heaviness;

    @Column(name = "appearance_dttm")
    private Timestamp appearanceDttm;

    @Temporal(TemporalType.DATE)
    @Column(name = "recovery_dt")
    private Date recoveryDt;
}
