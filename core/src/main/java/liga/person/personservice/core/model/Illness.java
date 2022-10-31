package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Entity(name = "Illness")
@Table(name = "illness")
public class Illness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}