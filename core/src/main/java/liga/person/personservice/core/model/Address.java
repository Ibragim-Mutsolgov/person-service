package liga.person.personservice.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Address")
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "index")
    private Integer index;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "building", nullable = false, length = 32)
    private String building;

    @Column(name = "flat", length = 32)
    private String flat;
}