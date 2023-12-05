package com.tecnm.you2be.reports;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.tecnm.you2be.DAO.VideoDao;
import com.tecnm.you2be.models.EstadoCuenta;
import com.tecnm.you2be.models.Video;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EstadoCuentaReport {

    private Video video = new Video();

    private String tags = "";

    private VideoDao videoDao = new VideoDao();

    public void createPdf(String dest, int idUsuario) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        Table table = new Table(UnitValue.createPercentArray(new float[]{2,4,4,4,2}))
                .useAllAvailableWidth();

        addTableHeaders(table, bold);


        //Crear lista para llenar filas
        List<EstadoCuenta> oEstadoCuentaList = videoDao.estadoCuenta(idUsuario);
        List<EstadoCuenta> oTableDTOList = new ArrayList<>();

//        oTaskList.forEach( task -> {
//            List<Video> tagList = tagDao.findAllById(task.getId());
//
//            tags = "";
//            tagList.forEach(tag -> {
//
//                tags+=tag.getTag()+" ";
//            });
//            oTableDTOList.add(new TableDTO(task, tags));
//        } );


        BigDecimal total = BigDecimal.valueOf(0);

        for( EstadoCuenta fila : oEstadoCuentaList){
            addTableRow(table, fila, font);
            total = fila.getPrecioTotal();
        }


        table.addCell(new Cell().add(new Paragraph()));
        table.addCell(new Cell().add(new Paragraph()));
        table.addCell(new Cell().add(new Paragraph()));
        table.addCell(new Cell().add(new Paragraph("TOTAL:")));
        table.addCell(new Cell().add(new Paragraph(total.toString())));


        Paragraph title = new Paragraph("ESTADO DE CUENTA");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(bold);
        title.setFontSize(30);
        title.setFontColor(ColorConstants.BLACK);
        document.add(title);
        document.add(table);

        //Close document
        document.close();
    }

    private void addTableHeaders(Table table, PdfFont font) {
        table.addHeaderCell(new Cell().add(new Paragraph("ID").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("TITULO").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("DESCRIPCION").setFont(font)));
//        table.addHeaderCell(new Cell().add(new Paragraph("LINK").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("TIPO").setFont(font)));
        table.addHeaderCell(new Cell().add(new Paragraph("PRECIO").setFont(font)));
    }

    private void addTableRow(Table table, EstadoCuenta estadoCuenta, PdfFont font) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(estadoCuenta.getIdVideo())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(estadoCuenta.getTitulo())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(estadoCuenta.getDescripcion())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(estadoCuenta.getTipo())).setFont(font)));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(estadoCuenta.getPrecio())).setFont(font)));
//        table.addCell(new Cell().add(new Paragraph(String.valueOf(video.getTagString())).setFont(font)));
    }
}
