package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.Illness;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IllnessMapper {

    @Select("select * from illness")
    List<Illness> findAll();

    @Select("select * from illness where id=#{id}")
    Illness findByID(Long id);

    @Insert("insert into illness (id, medical_card_id, " +
            "type_id, heaviness, appearance_dttm, recovery_dt)" +
            "VALUES (#{id}, #{medical_card_id}, #{type_id}, #{heaviness}," +
            "#{appearance_dttm}, #{recovery_dt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Illness illness);

    @Delete("delete from illness where id=#{id}")
    void deleteById(Long id);
}
