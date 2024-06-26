package com.project.springboot.fileprac.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.springboot.fileprac.entity.Supplier;
import com.project.springboot.fileprac.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FilePdfService {

    private final Logger log = Logger.getLogger(FilePdfService.class.getName());

    private final String filePdfName = "pdf-file.pdf";

    private final SupplierRepository supplierRepository;


    public void generatePdfFile() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        updateFile();
        log.info("Generate pdf file");
        PdfWriter.getInstance(document, new FileOutputStream("pdf-file.pdf"));

        document.open();

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        addRows(table);
        document.add(table);

        document.close();
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("name", "Type-Working", "city")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell(new Phrase(columnTitle));
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        List<Supplier> suppliers = supplierRepository.findAll();
        for (Supplier supplier : suppliers) {
            table.addCell(new PdfPCell(new Phrase(supplier.getName())));
            table.addCell(new PdfPCell(new Phrase(supplier.getTypeWorking())));
            table.addCell(new PdfPCell(new Phrase(supplier.getCity())));
        }
    }

    private void updateFile() {
        Path filePath = Path.of(filePdfName);
        if (Files.exists(filePath)) {
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                log.info("Ошибка при удалении файла: " + e.getMessage());
            }
        }
    }
}
