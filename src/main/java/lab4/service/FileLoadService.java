package lab4.service;

import lab4.model.Model;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface FileLoadService {

    void saveinfo(List<Model> logs);
}
