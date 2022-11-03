package liga.person.personservice.core.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import java.time.Instant;
import java.time.LocalDate;

@Entity(name = "Illness")
@Table(name = "illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getHeaviness() {
        return heaviness;
    }

    public void setHeaviness(String heaviness) {
        this.heaviness = heaviness;
    }

    public Instant getAppearanceDttm() {
        return appearanceDttm;
    }

    public void setAppearanceDttm(Instant appearanceDttm) {
        this.appearanceDttm = appearanceDttm;
    }

    public LocalDate getRecoveryDt() {
        return recoveryDt;
    }

    public void setRecoveryDt(LocalDate recoveryDt) {
        this.recoveryDt = recoveryDt;
    }
}