package com.web.controller;

import org.apache.poi.ss.usermodel.Row;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.RGBColor;
import com.web.model.ProductoEntity;
import com.web.service.ReporteService;
import java.util.Date;
import java.util.Locale;

@Controller
public class ExportarReporteController {
	
	@Autowired
    private ReporteService objreporteservice;
	
	@GetMapping("/exportexcel")
    public ResponseEntity<ByteArrayResource> exportToExcel() {
        List<ProductoEntity> productos = objreporteservice.generarReporteTodosProductos();
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            XSSFSheet sheet = workbook.createSheet("Productos");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Código");
            header.createCell(2).setCellValue("Nombre");
            header.createCell(3).setCellValue("Categoría");
            header.createCell(4).setCellValue("Costo");
            header.createCell(5).setCellValue("Precio");
            header.createCell(6).setCellValue("Stock");
            header.createCell(7).setCellValue("Observación");

            // Populate rows
            int rowNum = 1;
            for (ProductoEntity producto : productos) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(producto.getIdproducto());
                row.createCell(1).setCellValue(producto.getCodigo());
                row.createCell(2).setCellValue(producto.getNombre());
                row.createCell(3).setCellValue(producto.getCategoria().getNombre());
                row.createCell(4).setCellValue(producto.getCosto());
                row.createCell(5).setCellValue(producto.getPrecio());
                row.createCell(6).setCellValue(producto.getStock());
                row.createCell(7).setCellValue(producto.getObservacion());
            }

            workbook.write(out);
            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
	
	

	
	@GetMapping("/exportpdf")
    public ResponseEntity<ByteArrayResource> exportToPDF() {
        List<ProductoEntity> productos = objreporteservice.generarReporteTodosProductos();
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

         // Cabecera
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100); // Ancho de la tabla al 100%
            headerTable.setSpacingAfter(10f); // Espacio después del encabezado

            // Rectángulo con color de fondo
            PdfPCell headerCell = new PdfPCell();
            headerCell.setColspan(2);
            headerCell.setBackgroundColor(new RGBColor(0, 0, 0)); // Negro
            headerCell.setPadding(5);
            headerTable.addCell(headerCell);

            // Logo
            Image logo = Image.getInstance("src/main/resources/static/img/logoreporte.png"); 
            logo.scaleToFit(150, 75); 
            PdfPCell logoCell = new PdfPCell(logo);
            logoCell.setBorder(Rectangle.NO_BORDER);
            logoCell.setPadding(5);
            headerTable.addCell(logoCell);
            
            headerTable.completeRow();

         // Título del reporte
            Font titleFont = new Font(Font.TIMES_ROMAN, 20, Font.BOLD);
            PdfPCell titleCell = new PdfPCell(new Paragraph("Reporte de Productos", titleFont));
            titleCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            titleCell.setBorder(Rectangle.NO_BORDER);
            titleCell.setPadding(5);
            headerTable.addCell(titleCell);

         // Información de la empresa
            Font companyFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
            PdfPCell companyCell = new PdfPCell(new Paragraph("Metrópoli\nChiclayo, 1024 La Central.", companyFont));
            companyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            companyCell.setBorder(Rectangle.NO_BORDER);
            companyCell.setPadding(5);
            headerTable.addCell(companyCell);

            document.add(headerTable);

         // Cuerpo del reporte
            PdfPTable table = new PdfPTable(8);
            table.setWidthPercentage(100); // Ancho de la tabla al 100%
            table.setSpacingBefore(10f); // Espacio antes de la tabla
            table.setSpacingAfter(10f);  // Espacio después de la tabla

            // Fuente para el texto del reporte
            Font cellFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL);

            // Encabezados de la tabla
            String[] headers = {"ID", "Código", "Nombre", "Categoría", "Costo", "Precio", "Stock", "Observación"};
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header, cellFont));
                cell.setPadding(5);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new RGBColor(200, 200, 200)); // Gris claro
                cell.setBorderWidth(1);
                table.addCell(cell);
            }

            // Datos de productos
            for (ProductoEntity producto : productos) {
                table.addCell(new Paragraph(String.valueOf(producto.getIdproducto()), cellFont));
                table.addCell(new Paragraph(producto.getCodigo(), cellFont));
                table.addCell(new Paragraph(producto.getNombre(), cellFont));
                table.addCell(new Paragraph(producto.getCategoria().getNombre(), cellFont));
                table.addCell(new Paragraph(String.valueOf(producto.getCosto()), cellFont));
                table.addCell(new Paragraph(String.valueOf(producto.getPrecio()), cellFont));
                table.addCell(new Paragraph(String.valueOf(producto.getStock()), cellFont));
                table.addCell(new Paragraph(producto.getObservacion(), cellFont));
            }

            document.add(table);

            // Pie de página
            PdfPTable footerTable = new PdfPTable(1);
            footerTable.setWidthPercentage(100); // Ancho de la tabla al 100%
            footerTable.setSpacingBefore(10f); // Espacio antes del pie de página

            // Rectángulo con color de fondo
            PdfPCell footerCell = new PdfPCell();
            footerCell.setBackgroundColor(new RGBColor(0, 0, 0)); // Negro
            footerCell.setPadding(5);
            footerTable.addCell(footerCell);

            // Fecha y hora de generación del reporte
            Font footerFont = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy, HH:mm:ss", new Locale("es", "ES"));
            String fechaHora = dateFormat.format(new Date());
            PdfPCell footerDateCell = new PdfPCell(new Paragraph("Fecha y hora de generación: " + fechaHora, footerFont));
           
            
            footerDateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            footerDateCell.setBorder(Rectangle.NO_BORDER);
            footerDateCell.setPadding(5);
            footerTable.addCell(footerDateCell);

            document.add(footerTable);

            document.close();


            ByteArrayResource resource = new ByteArrayResource(out.toByteArray());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=productos.pdf")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(resource.contentLength())
                    .body(resource);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
