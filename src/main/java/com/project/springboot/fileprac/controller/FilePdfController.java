package com.project.springboot.fileprac.controller;

import com.itextpdf.text.DocumentException;
import com.project.springboot.fileprac.service.FilePdfService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;

@RestController
@AllArgsConstructor
public class FilePdfController {

    private final FilePdfService filePdfService;

    @GetMapping("/getPdf")
    public ResponseEntity<File> createPdf() throws DocumentException, FileNotFoundException {
        filePdfService.generatePdfFile();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
