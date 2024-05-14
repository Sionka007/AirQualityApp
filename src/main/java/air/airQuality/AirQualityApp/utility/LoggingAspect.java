package air.airQuality.AirQualityApp.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    public static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution(* air.airQuality.AirQualityApp.Services..*(..))", throwing = "exception")
    public void logServiceAirQualityException(AirQualityException exception) {
        LOGGER.error(exception.getErrorMsg(), exception);
    }

    @AfterThrowing(pointcut = "execution(* air.airQuality.AirQualityApp.Services..*(..))", throwing = "exception")
    public void logServiceException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }
}
