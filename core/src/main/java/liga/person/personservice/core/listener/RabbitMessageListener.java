package liga.person.personservice.core.listener;

import liga.person.personservice.core.dto.NamesForQueue;
import liga.person.personservice.core.dto.RabbitMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class RabbitMessageListener {

    private final Logger logger = Logger.getLogger(RabbitMessageDto.class.getName());

    @RabbitListener(queues = NamesForQueue.ROUTER_QUEUE_DAILY)
    public void getMessageFromDailyListener(String message) {
        // Необходимо отправить логи в common-module
        logger.info("Класс: RabbitMessageListener; метод: getMessageFromDailyListener()");
    }

    @RabbitListener(queues = NamesForQueue.ROUTER_QUEUE_ALERT)
    public void getMessageFromAlertListener(String message) {
        // Необходимо отправить логи в common-module
        logger.info("Класс: RabbitMessageListener; метод: getMessageFromAlertListener()");
    }
}
