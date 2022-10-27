package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long medical_card_id;

    private Long type_id;

    private String heaviness;

    private Timestamp appearance_dttm;

    @Temporal(TemporalType.DATE)
    private Date recovery_dt;
}
