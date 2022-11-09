package liga.person.personservice.core.service.serviceimpl;

import liga.person.personservice.core.model.Address;
import liga.person.personservice.core.repository.AddressRepository;
import liga.person.personservice.core.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository repository;

    private Logger logger = Logger.getLogger(AddressServiceImpl.class.getName());

    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Address> findAll() {
        return repository.findAll();
    }

    @Override
    public Address findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Address save(Address address) {
        return repository.save(address);
    }

    @Override
    public Address savePut(Long id, Address address) {
        return repository.findById(id)
                .map(addressSave -> {
                    addressSave.setId(id);
                    addressSave.setContact(address.getContact());
                    addressSave.setCountryId(address.getCountryId());
                    addressSave.setCity(address.getCity());
                    addressSave.setIndex(address.getIndex());
                    addressSave.setStreet(address.getStreet());
                    addressSave.setBuilding(address.getBuilding());
                    addressSave.setFlat(address.getFlat());
                    return repository.save(addressSave);
                }).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
