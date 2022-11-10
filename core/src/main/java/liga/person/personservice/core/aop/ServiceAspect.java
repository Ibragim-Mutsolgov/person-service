package liga.person.personservice.core.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.common.core.model.Debug;
import liga.medical.common.core.model.Exception;
import liga.medical.common.core.service.DebugService;
import liga.medical.common.core.service.ExceptionService;
import liga.person.personservice.core.dto.SystemTypeId;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Aspect
@Component
@AllArgsConstructor
public class ServiceAspect {

    private DebugService debugService;

    private ExceptionService exceptionService;

    @Pointcut(value = "execution(* liga.person.personservice.core.service.serviceimpl.*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object logger(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
        Logger logger = Logger.getLogger(ServiceAspect.class.getName());
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object object = null;
        String text = "Класс: " + className + "; метод: " + methodName + "()";
        Debug debug = new Debug();
        debug.setUuid(UUID.randomUUID());
        debug.setSystemTypeId(SystemTypeId.systemTypeId);
        debug.setMethodParams(text);
        debugService.save(debug);
        logger.info(text);
        try {
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            Exception exception = new Exception();
            exception.setUuid(UUID.randomUUID());
            exception.setSystemTypeId(SystemTypeId.systemTypeId);
            exception.setMethodParams(text);
            exceptionService.save(exception);
            logger.info(text);
            throwable.printStackTrace();
        }
        return object;
    }
}
