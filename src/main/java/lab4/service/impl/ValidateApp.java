package lab4.service.impl;

import lab4.annotation.LogTransormation;
import lab4.model.Model;
import lab4.service.ValidateService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@LogTransormation("log.txt")
public class ValidateApp implements ValidateService {
    private final List<String> appType;
    public ValidateApp() {
        this.appType = List.of("web","mobile");
    }
    @Override
    public void validate(List<Model> models) {
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            if (!appType.contains(model.app)) {
                model.app = "other: " + model.app;
            }
        }
    }
}
