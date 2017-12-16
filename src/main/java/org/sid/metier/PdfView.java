package org.sid.metier;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> revenueData = new HashMap<String, String>();
        revenueData.put("1/20/2010", "$100,000");
        revenueData.put("1/21/2010", "$200,000");
        revenueData.put("1/22/2010", "$300,000");
        revenueData.put("1/23/2010", "$400,000");
        revenueData.put("1/24/2010", "$500,000");

        Table table = new Table(2);
        table.addCell("Month");
        table.addCell("Revenue");

        for (Map.Entry<String, String> entry : revenueData.entrySet()) {
            table.addCell(entry.getKey());
            table.addCell(entry.getValue());
        }

        document.add(table);
    }
}
