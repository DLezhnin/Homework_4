package lab4.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class Model {
    public String username;
    public String fio;
    public LocalDateTime accessdate;
    public String app;

    @Override
    public String toString() {
        return "Model{" +
                "username='" + username + '\'' +
                ", fio='" + fio + '\'' +
                ", accessdate=" + accessdate +
                ", app='" + app + '\'' +
                '}';
    }
}
