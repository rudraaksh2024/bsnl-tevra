package org.apache.poi.xslf.util;

import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2D;
import de.rototor.pdfbox.graphics2d.PdfBoxGraphics2DFontTextDrawer;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.poi.util.Internal;

@Internal
public class PDFFormat implements OutputFormat {
    private PDPageContentStream contentStream;
    private final PDDocument document;
    private PdfBoxGraphics2DFontTextDrawer fontTextDrawer;
    private PdfBoxGraphics2D pdfBoxGraphics2D;

    public PDFFormat(boolean z, String str, String str2) {
        if (!z) {
            this.fontTextDrawer = new PDFFontMapper(str, str2);
        }
        this.document = new PDDocument();
    }

    public Graphics2D addSlide(double d, double d2) throws IOException {
        float f = (float) d;
        float f2 = (float) d2;
        PDPage pDPage = new PDPage(new PDRectangle(f, f2));
        this.document.addPage(pDPage);
        this.contentStream = new PDPageContentStream(this.document, pDPage);
        PdfBoxGraphics2D pdfBoxGraphics2D2 = new PdfBoxGraphics2D(this.document, f, f2);
        this.pdfBoxGraphics2D = pdfBoxGraphics2D2;
        PdfBoxGraphics2DFontTextDrawer pdfBoxGraphics2DFontTextDrawer = this.fontTextDrawer;
        if (pdfBoxGraphics2DFontTextDrawer != null) {
            pdfBoxGraphics2D2.setFontTextDrawer(pdfBoxGraphics2DFontTextDrawer);
        }
        return this.pdfBoxGraphics2D;
    }

    public void writeSlide(MFProxy mFProxy, File file) throws IOException {
        try {
            this.pdfBoxGraphics2D.dispose();
            this.contentStream.drawForm(this.pdfBoxGraphics2D.getXFormObject());
        } finally {
            this.contentStream.close();
        }
    }

    public void writeDocument(MFProxy mFProxy, File file) throws IOException {
        this.document.save(new File(file.getCanonicalPath()));
    }

    public void close() throws IOException {
        try {
            this.document.close();
        } finally {
            PdfBoxGraphics2DFontTextDrawer pdfBoxGraphics2DFontTextDrawer = this.fontTextDrawer;
            if (pdfBoxGraphics2DFontTextDrawer != null) {
                pdfBoxGraphics2DFontTextDrawer.close();
            }
        }
    }
}
