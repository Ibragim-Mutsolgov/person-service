package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity(name = "PersonData")
@Table(name = "person_data")
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private PersonData parent;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "birth_dt", nullable = false)
    private LocalDate birthDt;

    @Column(name = "age")
    private Integer age;

    @Column(name = "sex", nullable = false, length = 1)
    private String sex;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_card_id", nullable = false)
    private MedicalCard medicalCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PersonData parent;

    @OneToMany(mappedBy = "parent")
    private Set<PersonData> personData = new LinkedHashSet<>();
}