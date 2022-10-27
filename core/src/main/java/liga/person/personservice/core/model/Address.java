package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long contact_id;

    private Long country_id;

    private String city;

    private Integer index;

    private String street;

    private String building;

    private String flat;
}
