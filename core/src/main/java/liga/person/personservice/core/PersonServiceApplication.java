package liga.person.personservice.core;

import liga.person.personservice.core.model.Address;
import liga.person.personservice.core.model.Contact;
import liga.person.personservice.core.model.MedicalCard;
import liga.person.personservice.core.model.Illness;
import liga.person.personservice.core.model.PersonData;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("liga.person.personservice.core.mapper")
@MappedTypes({Address.class, Contact.class, Illness.class, MedicalCard.class, PersonData.class})
@ComponentScan(basePackages = {"liga.person.personservice", "liga.medical.common.service"})
public class PersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }

}
