package com.project.springboot.fileprac.controller;


import com.project.springboot.fileprac.service.FileCsvService;
import com.project.springboot.fileprac.service.FileTxtService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController()
@AllArgsConstructor
public class FileController {
    private final FileTxtService fileService;
    private final FileCsvService fileCsvService;

    @GetMapping("/getTxt")
    public ResponseEntity<File> create() throws IOException {
        fileService.generateTxtFile();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/getCsv")
    public ResponseEntity<File> createCsv() {
        fileCsvService.createCsvFile();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
