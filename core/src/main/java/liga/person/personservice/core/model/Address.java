package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "address")
public class Address {

    @Id
    private Long id;

    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "country_id")
    private Long countryId;

    private String city;

    private Integer index;

    private String street;

    private String building;

    private String flat;
}
