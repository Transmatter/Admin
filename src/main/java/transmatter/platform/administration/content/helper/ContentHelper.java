package transmatter.platform.administration.content.helper;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ContentHelper {
    @Before("execution(* transmatter.platform.administration.content.service.ContentServiceImpl.*(..))")
    public void logBefore() {
        log.info("calling function");
    }
}
