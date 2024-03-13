package lab4.controller;

import lab4.model.Model;
import lab4.service.FileLoadService;
import lab4.service.ValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class DataController {
    @Autowired
    private final Function<String, List<Model>> dataReader;
    @Autowired
    private final FileLoadService dataSaver;
    @Autowired
    private final List<ValidateService> validateList;

    public DataController(Function<String, List<Model>> dataReader, FileLoadService dataSaver, List<ValidateService> validateList) {
        this.dataReader = dataReader;
        this.dataSaver = dataSaver;
        this.validateList = validateList;
    }

    public void make(String path) {
        List<Model> models = dataReader.apply(path);

        for (int i=0; i < validateList.size(); i++) {
            validateList.get(i).validate(models);
        }
        dataSaver.saveinfo(models);
    }
}
