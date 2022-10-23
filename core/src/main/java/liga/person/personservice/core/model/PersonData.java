package liga.person.personservice.core.model;

import lombok.Data;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import java.util.Date;

@Data
@Table(name = "person_data")
public class PersonData {

    @Id
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birth_dt")
    @Temporal(TemporalType.DATE)
    private Date birthDt;

    private int age;

    private String sex;

    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "medical_card_id")
    private Long medicalCardId;

    @Column(name = "parent_id")
    private Long parentId;
}
