package com.project.springboot.fileprac.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.springboot.fileprac.entity.Supplier;
import com.project.springboot.fileprac.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final String imageName = "podpis.png";

    private final SupplierRepository supplierRepository;


    public void generatePdfFile() throws IOException, DocumentException {
        Document document = new Document();
        Path pathI = Path.of(imageName);
        Image img = Image.getInstance(pathI.toString());

        updateFile();

        log.info("Generate pdf file");
        PdfWriter.getInstance(document, new FileOutputStream("pdf-file.pdf"));

        document.open();

        Paragraph titleParagraph = new Paragraph("General Table Suppliers",
                new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD));
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        addTableHeader(table);
        addRows(table);
        document.add(table);

        img.setAlignment(Element.ALIGN_RIGHT);
        document.add(img);

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
