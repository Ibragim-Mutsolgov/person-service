package liga.person.personservice.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"liga.person.personservice", "liga.medical.common.service", "liga.medical.common.core"})
public class PersonServiceApplication {

    // Необходимо сделать:
    // 1. Логирование работу методов в common-module
    // 2. Отправить сообщения в common-module
    public static void main(String[] args) {
        SpringApplication.run(PersonServiceApplication.class, args);
    }

}
