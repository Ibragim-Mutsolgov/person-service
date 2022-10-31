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
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity(name = "Contact")
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phone_number", nullable = false, length = 32)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Lob
    @Column(name = "profile_link")
    private String profileLink;

    @OneToMany(mappedBy = "contact")
    private Set<PersonData> personData = new LinkedHashSet<>();

    @OneToMany(mappedBy = "contact")
    private Set<Address> addresses = new LinkedHashSet<>();
}