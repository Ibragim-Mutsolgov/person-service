package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "medical_card")
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String client_status;

    private String med_status;

    @Temporal(TemporalType.DATE)
    private Date registry_dt;

    private String comment;
}
