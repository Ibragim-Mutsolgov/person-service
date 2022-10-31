package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity(name = "MedicalCard")
@Table(name = "medical_card")
public class MedicalCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "client_status", nullable = false, length = 1)
    private String clientStatus;

    @Column(name = "med_status", nullable = false, length = 1)
    private String medStatus;

    @Column(name = "registry_dt", nullable = false)
    private LocalDate registryDt;

    @Lob
    @Column(name = "comment")
    private String comment;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "medicalCard")
    private PersonData personDatum;

    @OneToMany(mappedBy = "medicalCard")
    private Set<Illness> illnesses = new LinkedHashSet<>();
}