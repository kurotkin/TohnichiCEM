package com.kurotkin;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Vitaly on 21.05.17.
 */
public class pdfWriter {
    private static DateFormat dateFormatForName = new SimpleDateFormat("yyyyMMddHHmmss");
    private static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public static void writePdf(String prUrl, HashMap<Integer, String> p) {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 50, 50);
            // Проверка папки
            File folder = new File(prUrl);
            if (!folder.exists()) {
                folder.mkdir();
            }
            // Имя файла
            String prUrlFull = prUrl + File.separator + dateFormatForName.format(new Date()) + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(prUrlFull));
            document.open();

            // Шрифты
            BaseFont bf = BaseFont.createFont("Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontBig18 = new Font(bf, 18, Font.BOLDITALIC, new CMYKColor(0, 0, 0,255));
            Font fontBig14 = new Font(bf, 12, Font.BOLDITALIC, new CMYKColor(0, 0, 0,255));
            Font font = new Font(bf, 12);

            // Составление документа
            Paragraph title1 = new Paragraph("Протокол замеров затяжки", fontBig18);
            title1.setAlignment(Element.ALIGN_CENTER);
            Chapter chapter1 = new Chapter(title1, 1);
            chapter1.setNumberDepth(0);

            Paragraph someSectionText1 = new Paragraph("Документ составляется автоматически. \n" +
                "Дата составления документа: " + dateFormat.format(new Date()), font);
            someSectionText1.setAlignment(Element.ALIGN_CENTER);
            chapter1.add(someSectionText1);

            // Таблица
            PdfPTable t = new PdfPTable(3);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            t.addCell(new PdfPCell(new Phrase("№ п/п", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Момент фактический (Нм)", fontBig14)));
            t.addCell(new PdfPCell(new Phrase("Дата, время затяжки", fontBig14)));
            Set<Integer> keys = p.keySet();
            for(Integer i: keys) {
                t.addCell(new PdfPCell(new Phrase(Integer.toString(i), font)));
                t.addCell(new PdfPCell(new Phrase(Double.toString(Parser.getVal(p,i)), font)));
                t.addCell(new PdfPCell(new Phrase(dateFormat.format(Parser.getDate(p,i)), font)));
            }
            chapter1.add(t);
            document.add(chapter1);
            document.close();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
