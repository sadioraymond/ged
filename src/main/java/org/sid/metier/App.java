package org.sid.metier;

import java.io.FileOutputStream;

import com.qoppa.pdf.TIFFOptions;
import com.qoppa.pdfProcess.PDFDocument;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
        {
            PDFDocument pdfDoc = new PDFDocument ("C:\\Users\\Raymond SADIO\\Downloads\\Documents/polycopie.pdf", null);
            TIFFOptions options = new TIFFOptions (150, TIFFOptions.TIFF_PACKBITS);
            FileOutputStream outStream = new FileOutputStream ("C:\\Users\\Raymond SADIO\\Downloads\\Documents/polycopie.tif");
            pdfDoc.saveDocumentAsTIFF(outStream, options);
            outStream.close();
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
	}

}
