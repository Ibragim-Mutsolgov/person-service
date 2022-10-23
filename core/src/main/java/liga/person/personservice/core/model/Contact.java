package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "contact")
public class Contact {

    @Id
    private Long id;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "profile_link")
    private String profileLink;
}
