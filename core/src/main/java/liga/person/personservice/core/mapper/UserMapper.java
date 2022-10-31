package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users")
    List<User> findAll();

    @Select("select * from users where id=#{id}")
    User findByID(Long id);

    @Insert("insert into users (username, password) values" +
            "(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

    @Delete("delete from users where id=#{id}")
    void deleteById(Long id);
}
