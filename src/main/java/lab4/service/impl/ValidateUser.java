package lab4.service.impl;

import lab4.model.Model;
import lab4.service.ValidateService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ValidateUser implements ValidateService {
    public ValidateUser() {}
    @Override
    public void validate(List<Model> models) {
        for (int i = 0; i < models.size(); i++) {
            Model model = models.get(i);
            StringBuilder builder = new StringBuilder(model.fio);
            if (Character.isAlphabetic(model.fio.codePointAt(0))) {
                builder.setCharAt(0, Character.toUpperCase(model.fio.charAt(0)));
            }

            for (int j = 1; j < model.fio.length(); j++) {
                if (Character.isAlphabetic(model.fio.charAt(j)) && Character.isSpaceChar(model.fio.charAt(j-1))) {
                    builder.setCharAt(j, Character.toUpperCase(model.fio.charAt(j)));
                }
            }
            model.fio = builder.toString();
        }
    }
}
