package transmatter.platform.administration.content.helper;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import transmatter.platform.administration.content.dao.ContentDao;
import transmatter.platform.administration.content.entity.Content;
import transmatter.platform.administration.content.exception.ContentNotFoundException;

@Aspect
@Component
@Slf4j
public class ContentHelper {
    @Autowired
    ContentDao contentDao;

    @Before("execution(* transmatter.platform.administration.content.service.ContentServiceImpl.*(String ))")
    public void checkError(JoinPoint joinPoint) {
        String id = joinPoint.getArgs()[0].toString();
        Content content = contentDao.getContent(id);
        if(content == null) {
            throw new ContentNotFoundException(id);
        }
    }
}
