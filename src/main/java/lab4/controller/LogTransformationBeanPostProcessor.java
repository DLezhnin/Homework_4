package lab4.controller;

import lab4.annotation.LogTransormation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogTransformationBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    ValidateLog log;
    Map<String, Class> cls = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean.getClass().isAnnotationPresent(LogTransormation.class)){
            cls.put(beanName,bean.getClass());
        };
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = cls.get(beanName);
        if(beanClass!=null){
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(),
            bean.getClass().getInterfaces()
                    ,(proxy,method,args) ->
                      {
                        Object value;
                        StringBuilder sb = new StringBuilder();
                        sb.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                                .append(";").append(beanClass.getName()).append(";[");
                           for (int i = 0; i < args.length; i++) {
                               sb.append(args[i].toString()).append(";");
                           }
                        sb.append(("]"));
                        value = method.invoke(bean, args);
                           if (value != null) {
                              sb.append(value.toString());
                           }
                        log.write(bean.getClass().getAnnotation(LogTransormation.class).value(), sb.toString());
                        return value;
                      }
                    );
        }
        return bean;
    }
}
