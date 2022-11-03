package liga.person.personservice.core.mapper;

import liga.person.personservice.core.dto.AddressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface AddressMapper {

    @Results(value = {
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "countryId", column = "country_id")})
    @Select("select * from address")
    List<AddressDto> findAll();

    @Results(value = {
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "countryId", column = "country_id")})
    @Select("select * from address where id=#{id}")
    AddressDto findByID(Long id);

    @Results(value = {
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "countryId", column = "country_id")})
    @Insert("insert into address (contact_id, country_id, " +
            "city, index, street, building, flat) values" +
            "(#{contactId}, #{countryId}, #{city}, #{index}," +
            "#{street}, #{building}, #{flat})")
    void save(AddressDto address);

    @Results(value = {
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "countryId", column = "country_id")})
    @Delete("delete from address where id=#{id}")
    void deleteById(Long id);
}
