package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Select("select * from address")
    List<Address> findAll();

    @Select("select * from address where id=#{id}")
    Address findByID(Long id);

    @Insert("insert into address (id, contact_id, country_id, " +
            "city, index, street, building, flat) values" +
            "(#{id}, #{contact_id}, #{country_id}, #{city}, #{index}," +
            "#{street}, #{building}, #{flat})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Address address);

    @Delete("delete from address where id=#{id}")
    void deleteById(Long id);
}
