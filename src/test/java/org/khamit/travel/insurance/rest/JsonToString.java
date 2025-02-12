package org.khamit.travel.insurance.rest;



import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;


import java.io.File;

import java.nio.file.Files;

@Component
public class JsonToString{
    public String read(String fileName){

        try {
            File file = ResourceUtils.getFile("classpath:" + fileName);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
