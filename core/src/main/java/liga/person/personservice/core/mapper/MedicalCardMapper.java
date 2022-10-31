package liga.person.personservice.core.mapper;

import liga.person.personservice.core.model.MedicalCard;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface MedicalCardMapper {

    @Select("select * from medical_card")
    List<MedicalCard> findAll();

    @Select("select * from medical_card where id=#{id}")
    MedicalCard findByID(Long id);

    @Insert("insert into medical_card (id, client_status, " +
            "med_status, registry_dt, comment) VALUES" +
            "(#{id}, #{client_status}, #{med_status}, #{registry_dt}," +
            "#{comment})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(MedicalCard medicalCard);

    @Delete("delete from medical_card where id=#{id}")
    void deleteById(Long id);
}
