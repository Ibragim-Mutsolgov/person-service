package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "person_data")
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String last_name;

    private String first_name;

    @Temporal(TemporalType.DATE)
    private Date birth_dt;

    private int age;

    private String sex;

    private Long contact_id;

    private Long medical_card_id;

    private Long parent_id;
}
