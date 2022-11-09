package liga.person.personservice.core.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "comment")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getRegistryDt() {
        return registryDt;
    }

    public void setRegistryDt(LocalDate registryDt) {
        this.registryDt = registryDt;
    }

    public String getMedStatus() {
        return medStatus;
    }

    public void setMedStatus(String medStatus) {
        this.medStatus = medStatus;
    }

    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}