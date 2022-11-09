package liga.person.personservice.core.model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Illness")
@Table(name = "illness")
public class Illness {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "medical_card_id", nullable = false)
    private MedicalCard medicalCard;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "heaviness", length = 1)
    private String heaviness;

    @Column(name = "appearance_dttm", nullable = false)
    private Instant appearanceDttm;

    @Column(name = "recovery_dt")
    private LocalDate recoveryDt;

    public LocalDate getRecoveryDt() {
        return recoveryDt;
    }

    public void setRecoveryDt(LocalDate recoveryDt) {
        this.recoveryDt = recoveryDt;
    }

    public Instant getAppearanceDttm() {
        return appearanceDttm;
    }

    public void setAppearanceDttm(Instant appearanceDttm) {
        this.appearanceDttm = appearanceDttm;
    }

    public String getHeaviness() {
        return heaviness;
    }

    public void setHeaviness(String heaviness) {
        this.heaviness = heaviness;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}