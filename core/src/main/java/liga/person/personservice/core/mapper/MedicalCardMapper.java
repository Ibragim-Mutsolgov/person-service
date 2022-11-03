package liga.person.personservice.core.mapper;

import liga.person.personservice.core.dto.MedicalCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface MedicalCardMapper {

    @Results(value = {
                    @Result(property = "clientStatus", column = "client_status"),
                    @Result(property = "medStatus", column = "med_status"),
                    @Result(property = "registryDt", column = "registry_dt")})
    @Select("select * from medical_card")
    List<MedicalCardDto> findAll();

    @Results(value = {
                    @Result(property = "clientStatus", column = "client_status"),
                    @Result(property = "medStatus", column = "med_status"),
                    @Result(property = "registryDt", column = "registry_dt")})
    @Select("select * from medical_card where id=#{id}")
    MedicalCardDto findByID(Long id);

    @Results(value = {
                    @Result(property = "clientStatus", column = "client_status"),
                    @Result(property = "medStatus", column = "med_status"),
                    @Result(property = "registryDt", column = "registry_dt")})
    @Insert("insert into medical_card (client_status, " +
            "med_status, registry_dt, comment) VALUES" +
            "(#{clientStatus}, #{medStatus}, #{registryDt}," +
            "#{comment})")
    void save(MedicalCardDto medicalCard);

    @Results(value = {
                    @Result(property = "clientStatus", column = "client_status"),
                    @Result(property = "medStatus", column = "med_status"),
                    @Result(property = "registryDt", column = "registry_dt")})
    @Delete("delete from medical_card where id=#{id}")
    void deleteById(Long id);
}
