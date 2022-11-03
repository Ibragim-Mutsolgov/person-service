package liga.person.personservice.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "PersonData")
@Table(name = "person_data")
public class PersonData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

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
    private Contact contactId;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "medical_card_id", nullable = false)
    private MedicalCard medicalCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private PersonData parentId;

    @OneToMany(mappedBy = "parentId")
    private Set<PersonData> personData = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getBirthDt() {
        return birthDt;
    }

    public void setBirthDt(LocalDate birthDt) {
        this.birthDt = birthDt;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Contact getContactId() {
        return contactId;
    }

    public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public PersonData getParentId() {
        return parentId;
    }

    public void setParentId(PersonData parentId) {
        this.parentId = parentId;
    }

    public Set<PersonData> getPersonData() {
        return personData;
    }

    public void setPersonData(Set<PersonData> personData) {
        this.personData = personData;
    }
}