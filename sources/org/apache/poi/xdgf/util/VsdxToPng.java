package org.apache.poi.xdgf.util;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeRenderer;

public class VsdxToPng {
    public static void renderToPng(XDGFPage xDGFPage, String str, double d, ShapeRenderer shapeRenderer) throws IOException {
        renderToPng(xDGFPage, new File(str), d, shapeRenderer);
    }

    public static void renderToPngDir(XDGFPage xDGFPage, File file, double d, ShapeRenderer shapeRenderer) throws IOException {
        File file2 = new File(file, "page" + xDGFPage.getPageNumber() + ProcessIdUtil.DEFAULT_PROCESSID + Util.sanitizeFilename(xDGFPage.getName()) + ".png");
        System.out.println("** Writing image to " + file2);
        renderToPng(xDGFPage, file2, d, shapeRenderer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0071, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0072, code lost:
        r7.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0075, code lost:
        throw r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x006c, code lost:
        r8 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void renderToPng(org.apache.poi.xdgf.usermodel.XDGFPage r6, java.io.File r7, double r8, org.apache.poi.xdgf.usermodel.shape.ShapeRenderer r10) throws java.io.IOException {
        /*
            org.apache.poi.util.Dimension2DDouble r0 = r6.getPageSize()
            double r1 = r0.getWidth()
            double r1 = r1 * r8
            int r1 = (int) r1
            double r2 = r0.getHeight()
            double r2 = r2 * r8
            int r0 = (int) r2
            java.awt.image.BufferedImage r2 = new java.awt.image.BufferedImage
            r3 = 1
            r2.<init>(r1, r0, r3)
            java.awt.Graphics2D r3 = r2.createGraphics()
            java.awt.RenderingHints$Key r4 = java.awt.RenderingHints.KEY_ANTIALIASING
            java.lang.Object r5 = java.awt.RenderingHints.VALUE_ANTIALIAS_ON
            r3.setRenderingHint(r4, r5)
            java.awt.RenderingHints$Key r4 = java.awt.RenderingHints.KEY_RENDERING
            java.lang.Object r5 = java.awt.RenderingHints.VALUE_RENDER_QUALITY
            r3.setRenderingHint(r4, r5)
            java.awt.RenderingHints$Key r4 = java.awt.RenderingHints.KEY_INTERPOLATION
            java.lang.Object r5 = java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC
            r3.setRenderingHint(r4, r5)
            java.awt.RenderingHints$Key r4 = java.awt.RenderingHints.KEY_FRACTIONALMETRICS
            java.lang.Object r5 = java.awt.RenderingHints.VALUE_FRACTIONALMETRICS_ON
            r3.setRenderingHint(r4, r5)
            java.awt.Color r4 = java.awt.Color.black
            r3.setColor(r4)
            java.awt.Color r4 = java.awt.Color.white
            r3.setBackground(r4)
            r4 = 0
            r3.clearRect(r4, r4, r1, r0)
            int r0 = r2.getHeight()
            r3.translate(r4, r0)
            double r0 = -r8
            r3.scale(r8, r0)
            r10.setGraphics(r3)
            org.apache.poi.xdgf.usermodel.XDGFPageContents r6 = r6.getContent()
            r6.visitShapes(r10)
            r3.dispose()
            java.io.FileOutputStream r6 = new java.io.FileOutputStream
            r6.<init>(r7)
            java.lang.String r7 = "png"
            javax.imageio.ImageIO.write(r2, r7, r6)     // Catch:{ all -> 0x006a }
            r6.close()
            return
        L_0x006a:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x006c }
        L_0x006c:
            r8 = move-exception
            r6.close()     // Catch:{ all -> 0x0071 }
            goto L_0x0075
        L_0x0071:
            r6 = move-exception
            r7.addSuppressed(r6)
        L_0x0075:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.util.VsdxToPng.renderToPng(org.apache.poi.xdgf.usermodel.XDGFPage, java.io.File, double, org.apache.poi.xdgf.usermodel.shape.ShapeRenderer):void");
    }

    public static void renderToPng(XmlVisioDocument xmlVisioDocument, String str, double d, ShapeRenderer shapeRenderer) throws IOException {
        File file = new File(str);
        for (XDGFPage renderToPngDir : xmlVisioDocument.getPages()) {
            renderToPngDir(renderToPngDir, file, d, shapeRenderer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0048, code lost:
        r0.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004b, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r6) throws java.lang.Exception {
        /*
            int r0 = r6.length
            r1 = 1
            r2 = 2
            if (r0 <= r2) goto L_0x000f
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r3 = "Usage: [--debug] in.vsdx outdir"
            r0.println(r3)
            java.lang.System.exit(r1)
        L_0x000f:
            org.apache.poi.xdgf.usermodel.shape.ShapeRenderer r0 = new org.apache.poi.xdgf.usermodel.shape.ShapeRenderer
            r0.<init>()
            r3 = 0
            r3 = r6[r3]
            r4 = r6[r1]
            java.lang.String r5 = "--debug"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L_0x002a
            r3 = r6[r1]
            r4 = r6[r2]
            org.apache.poi.xdgf.usermodel.shape.ShapeDebuggerRenderer r0 = new org.apache.poi.xdgf.usermodel.shape.ShapeDebuggerRenderer
            r0.<init>()
        L_0x002a:
            java.io.FileInputStream r6 = new java.io.FileInputStream
            r6.<init>(r3)
            org.apache.poi.xdgf.usermodel.XmlVisioDocument r1 = new org.apache.poi.xdgf.usermodel.XmlVisioDocument     // Catch:{ all -> 0x0040 }
            r1.<init>((java.io.InputStream) r6)     // Catch:{ all -> 0x0040 }
            r2 = 4640601175125846202(0x4066ba2e8ba2e8ba, double:181.8181818181818)
            renderToPng((org.apache.poi.xdgf.usermodel.XmlVisioDocument) r1, (java.lang.String) r4, (double) r2, (org.apache.poi.xdgf.usermodel.shape.ShapeRenderer) r0)     // Catch:{ all -> 0x0040 }
            r6.close()
            return
        L_0x0040:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r1 = move-exception
            r6.close()     // Catch:{ all -> 0x0047 }
            goto L_0x004b
        L_0x0047:
            r6 = move-exception
            r0.addSuppressed(r6)
        L_0x004b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.util.VsdxToPng.main(java.lang.String[]):void");
    }
}
