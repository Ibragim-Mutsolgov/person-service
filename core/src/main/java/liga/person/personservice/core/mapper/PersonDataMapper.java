package liga.person.personservice.core.mapper;

import liga.person.personservice.core.dto.PersonDataDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface PersonDataMapper {

    @Results(value = {
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "birthDt", column = "birth_dt"),
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "parentId", column = "parent_id")})
    @Select("select * from person_data")
    List<PersonDataDto> findAll();

    @Results(value = {
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "birthDt", column = "birth_dt"),
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "parentId", column = "parent_id")})
    @Select("select * from person_data where id=#{id}")
    PersonDataDto findByID(Long id);

    @Results(value = {
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "birthDt", column = "birth_dt"),
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "parentId", column = "parent_id")})
    @Insert("insert into person_data (last_name, first_name, " +
            "birth_dt, age, sex, contact_id, medical_card_id, " +
            "parent_id) values (#{lastName}, #{firstName}, " +
            "#{birthDt}, #{age}, #{sex}, #{contactId}," +
            "#{medicalCardId}, #{parentId})")
    void save(PersonDataDto personData);

    @Results(value = {
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "birthDt", column = "birth_dt"),
            @Result(property = "contactId", column = "contact_id"),
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "parentId", column = "parent_id")})
    @Delete("delete from person_data where id=#{id}")
    void deleteById(Long id);
}
