package liga.person.personservice.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    private final Contact defaultContact = new Contact(
            "Binit Datta", "http://binitdatta.com",
            "binit-sample-email.com"
    );

    private final ApiInfo defaultApiInfo = new ApiInfo(
            "Запросы Rest API", "Описание Rest API", "1.0",
            "urn:tos", defaultContact, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", List.of()
    );

    private final Set<String> defaultProducesAndConsumes = new HashSet<>(
            List.of("application/json")
    );

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(defaultApiInfo)
                .produces(defaultProducesAndConsumes)
                .consumes(defaultProducesAndConsumes)
                .select().apis(RequestHandlerSelectors.basePackage("liga.person.personservice.core.restcontroller"))
                .build();
    }
}
