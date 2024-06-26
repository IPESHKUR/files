package com.project.springboot.fileprac.service;

import com.project.springboot.fileprac.entity.Supplier;
import com.project.springboot.fileprac.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class FileCsvService {

    private final Logger log = Logger.getLogger(FileCsvService.class.getName());

    private final String fileName = "example.csv";

    private final SupplierRepository supplierRepository;


    public void createCsvFile() {
        updateCsvFile();
        List<Supplier> supplierList = supplierRepository.findAll();
        log.info("create a csv document");

        try (FileWriter writer = new FileWriter(fileName);
             CSVPrinter printer = new CSVPrinter(writer,
                     CSVFormat.DEFAULT.withHeader("Name", "Type-Working", "City"))) {
            for (Supplier sup : supplierList) {
                printer.printRecord(sup.getName(), sup.getTypeWorking(), sup.getCity());
            }
        } catch (IOException e) {
            log.info("Не удалось записать данные" + e.getMessage());
        }
    }

    private void updateCsvFile() {
        Path filePath = Path.of(fileName);
        if (Files.exists(filePath)) {
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.info("Ошибка при удалении файла: " + e.getMessage());
            }
        }
    }
}
