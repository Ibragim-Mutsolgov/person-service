package liga.person.personservice.core.mapper;

import liga.person.personservice.core.dto.IllnessDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface IllnessMapper {

    @Results(value = {
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "appearanceDttm", column = "appearance_dttm"),
            @Result(property = "recoveryDt", column = "recovery_dt")})
    @Select("select * from illness")
    List<IllnessDto> findAll();

    @Results(value = {
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "appearanceDttm", column = "appearance_dttm"),
            @Result(property = "recoveryDt", column = "recovery_dt")})
    @Select("select * from illness where id=#{id}")
    IllnessDto findByID(Long id);

    @Results(value = {
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "appearanceDttm", column = "appearance_dttm"),
            @Result(property = "recoveryDt", column = "recovery_dt")})
    @Insert("insert into illness (medical_card_id, " +
            "type_id, heaviness, appearance_dttm, recovery_dt)" +
            "VALUES (#{medicalCardId}, #{typeId}, #{heaviness}," +
            "#{appearanceDttm}, #{recoveryDt})")
    void save(IllnessDto illness);

    @Results(value = {
            @Result(property = "medicalCardId", column = "medical_card_id"),
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "appearanceDttm", column = "appearance_dttm"),
            @Result(property = "recoveryDt", column = "recovery_dt")})
    @Delete("delete from illness where id=#{id}")
    void deleteById(Long id);
}
