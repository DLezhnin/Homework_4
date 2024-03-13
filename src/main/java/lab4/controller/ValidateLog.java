package lab4.controller;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
@Component
public class ValidateLog {
    public void write(String filename,String str){
        try(FileWriter writer = new FileWriter(filename,true)){
            writer.write(str);
            writer.flush();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
