package liga.person.personservice.core;

import liga.person.personservice.core.model.*;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("liga.person.personservice.core.mapper")
@MappedTypes({Address.class, Contact.class, Illness.class, MedicalCard.class, PersonData.class, User.class})
@ComponentScan(basePackages = {"liga.person.personservice", "liga.medical.common.service"})
public class PersonServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }

}
