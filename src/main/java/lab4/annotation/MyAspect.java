package lab4.annotation;

import lab4.controller.ValidateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Aspect
@Component
public class MyAspect {
    @Autowired
    ValidateLog log;
    @Around("@annotation(LogExecute)")
    public Object logExecute(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = joinPoint.proceed();
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append(";").append(joinPoint.getSignature()).append(";[");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            sb.append(joinPoint.getArgs()[i].toString()).append(";");
        }
        sb.append(("]"));
        log.write("log.txt", sb.toString());
        return joinPoint.proceed();
    }
}
