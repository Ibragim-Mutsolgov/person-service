package liga.person.personservice.core.dto;

import lombok.Data;

@Data
public class AddressDto {

    private Long id;

    private Long contactId;

    private Long countryId;

    private String city;

    private Integer index;

    private String street;

    private String building;

    private String flat;
}
