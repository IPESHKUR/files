package com.project.springboot.fileprac.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;


@Service
public class FileTxtService {
    private final Logger log = Logger.getLogger(FileTxtService.class.getName());

    public File generateTxtFile() throws IOException {
        File txtFile = new File("D:\\file.txt");
        if (txtFile.createNewFile()) {
            log.info("created");
        } else {
            log.info("file is exists");
        }
        try (FileWriter writer = new FileWriter(txtFile)) {
            writer.write("Sup");

        }
        return txtFile;
    }

}
