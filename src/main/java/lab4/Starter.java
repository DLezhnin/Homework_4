package lab4;

import lab4.controller.DataController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = "lab4")
@EnableTransactionManagement(proxyTargetClass = true)
public class Starter {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Starter.class);
        DataController dc = ctx.getBean("dataController", DataController.class);
        dc.make("c:\\Users\\79099\\IdeaProjects\\Homework_4\\src\\main\\resources\\Authorization");
    }
}
