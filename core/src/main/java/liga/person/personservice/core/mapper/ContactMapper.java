package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.Contact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface ContactMapper {

    @Select("select * from contact")
    List<Contact> findAll();

    @Select("select * from contact where id=#{id}")
    Contact findByID(Long id);

    @Insert("insert into contact (id, phone_number, email, profile_link)" +
            "VALUES (#{id}, #{phone_number}, #{email}, #{profile_link})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Contact contact);

    @Delete("delete from contact where id=#{id}")
    void deleteById(Long id);
}
