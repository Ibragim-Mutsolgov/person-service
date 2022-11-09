package liga.person.personservice.core.dto;

import lombok.Data;

@Data
public class RabbitMessageDto {

    private Long id;

    private Type type;

    private String description;
}
