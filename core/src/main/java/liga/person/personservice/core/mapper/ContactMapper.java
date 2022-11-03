package liga.person.personservice.core.mapper;

import liga.person.personservice.core.dto.ContactDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface ContactMapper {

    @Results(value = {
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "profileLink", column = "profile_link")})
    @Select("select * from contact")
    List<ContactDto> findAll();

    @Results(value = {
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "profileLink", column = "profile_link")})
    @Select("select * from contact where id=#{id}")
    ContactDto findByID(Long id);

    @Results(value = {
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "profileLink", column = "profile_link")})
    @Insert("insert into contact (phone_number, email, profile_link)" +
            "VALUES (#{phoneNumber}, #{email}, #{profileLink})")
    void save(ContactDto contact);

    @Results(value = {
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "profileLink", column = "profile_link")})
    @Delete("delete from contact where id=#{id}")
    void deleteById(Long id);
}
