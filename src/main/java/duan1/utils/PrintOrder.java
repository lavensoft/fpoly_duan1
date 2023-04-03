package duan1.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDictionary;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfName;
import com.itextpdf.kernel.pdf.PdfObject;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfStream;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class PrintOrder {
    private static String BILL_TEMPLATE = "/duan1/assets/docs/bill.pdf";

    public static void createPDF() throws IOException, Exception {
        File file = new File(BILL_TEMPLATE);
        file.getParentFile().mkdirs();

        PdfDocument pdfDoc = new PdfDocument(new PdfReader(BILL_TEMPLATE), new PdfWriter("/duan1/assets/docs/bill_edited.bill"));
        PdfPage page = pdfDoc.getFirstPage();
        PdfDictionary dict = page.getPdfObject();

        PdfObject object = dict.get(PdfName.Contents);
        if (object instanceof PdfStream) {
            PdfStream stream = (PdfStream) object;
            byte[] data = stream.getBytes();
            String replacedData = new String(data).replace("Hello World", "HELLO WORLD");
            stream.setData(replacedData.getBytes(StandardCharsets.UTF_8));
        }

        pdfDoc.close();
    }
}
