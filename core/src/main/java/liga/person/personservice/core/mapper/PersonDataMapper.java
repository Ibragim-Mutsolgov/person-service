package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.PersonData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface PersonDataMapper {

    @Select("select * from person_data")
    List<PersonData> findAll();

    @Select("select * from person_data where id=#{id}")
    PersonData findByID(Long id);

    @Insert("insert into person_data (id, last_name, first_name, " +
            "birth_dt, age, sex, contact_id, medical_card_id, " +
            "parent_id) values (#{id}, #{last_name}, #{first_name}, " +
            "#{birth_dt}, #{age}, #{sex}, #{contact_id}," +
            "#{medical_card_id}, #{parent_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(PersonData personData);

    @Delete("delete from person_data where id=#{id}")
    void deleteById(Long id);
}
