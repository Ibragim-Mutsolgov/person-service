package liga.person.personservice.core.service;

import liga.person.personservice.core.model.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAll();

    Address findById(Long id);

    Address save(Address address);

    Address savePut(Long id, Address address);

    void deleteById(Long id);
}
