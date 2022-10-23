package liga.person.personservice.core.model;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.util.Date;

@Data
@Table(name = "medical_card")
public class MedicalCard {

    @Id
    private Long id;

    @Column(name = "client_status")
    private String clientStatus;

    @Column(name = "med_status")
    private String medStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "registry_dt")
    private Date registryDt;

    private String comment;
}
