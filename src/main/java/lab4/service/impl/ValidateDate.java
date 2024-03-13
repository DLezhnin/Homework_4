package lab4.service.impl;

import lab4.model.Model;
import lab4.service.ValidateService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ValidateDate implements ValidateService {
    @Override
    public void validate(List<Model> models) {
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            if (model.accessdate == null) {
                models.remove(model);
            }
        }
    }
}
