package lab4.service.impl;

import lab4.model.Model;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
public class ReadServiceImpl implements Function<String, List<Model>> {
    private Model parse(String line) throws ParseException {
        String[] elements = line.split(";");

        Model model = new Model();
        model.username = elements[0];
        model.fio = elements[1];
        if(!elements[2].isBlank()){
            try{
                model.accessdate = LocalDateTime.parse(elements[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }catch (DateTimeException e){
                throw new RuntimeException();
            }
        }
        model.app = elements[3];
        return model;
    }

    @Override
    public List<Model> apply(String path) {
        List<Model> models = new ArrayList<>();
        File folder = new File(path);
        for (File file : folder.listFiles()){
            if (file.isFile()) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line = br.readLine();
                    while (line != null) {
                        models.add(parse(line));
                        line = br.readLine();
                    }
                    br.close();
                    fr.close();
                } catch (FileNotFoundException e) {
                    continue;
                } catch (IOException e) {
                    continue;
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return models;
    }
}
