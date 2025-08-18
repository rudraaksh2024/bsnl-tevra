package org.apache.poi.xslf.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.IOException;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.poi.sl.draw.Drawable;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.draw.SVGPOIGraphics2D;
import org.w3c.dom.DocumentType;

@Internal
public class SVGFormat implements OutputFormat {
    static final String svgNS = "http://www.w3.org/2000/svg";
    private SVGGraphics2D svgGenerator;
    private final boolean textAsShapes;

    public SVGFormat(boolean z) {
        this.textAsShapes = z;
    }

    public Graphics2D addSlide(double d, double d2) {
        SVGPOIGraphics2D sVGPOIGraphics2D = new SVGPOIGraphics2D(GenericDOMImplementation.getDOMImplementation().createDocument(svgNS, "svg", (DocumentType) null), this.textAsShapes);
        this.svgGenerator = sVGPOIGraphics2D;
        sVGPOIGraphics2D.setSVGCanvasSize(new Dimension((int) d, (int) d2));
        this.svgGenerator.setRenderingHint(Drawable.CACHE_IMAGE_SOURCE, true);
        return this.svgGenerator;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r1.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeSlide(org.apache.poi.xslf.util.MFProxy r2, java.io.File r3) throws java.io.IOException {
        /*
            r1 = this;
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter
            java.io.FileOutputStream r0 = new java.io.FileOutputStream
            java.lang.String r3 = r3.getCanonicalPath()
            r0.<init>(r3)
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            r2.<init>(r0, r3)
            org.apache.batik.svggen.SVGGraphics2D r1 = r1.svgGenerator     // Catch:{ all -> 0x001a }
            r3 = 1
            r1.stream(r2, r3)     // Catch:{ all -> 0x001a }
            r2.close()
            return
        L_0x001a:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x001c }
        L_0x001c:
            r3 = move-exception
            r2.close()     // Catch:{ all -> 0x0021 }
            goto L_0x0025
        L_0x0021:
            r2 = move-exception
            r1.addSuppressed(r2)
        L_0x0025:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.util.SVGFormat.writeSlide(org.apache.poi.xslf.util.MFProxy, java.io.File):void");
    }

    public void close() throws IOException {
        this.svgGenerator.dispose();
    }
}
