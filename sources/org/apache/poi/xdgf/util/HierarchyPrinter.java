package org.apache.poi.xdgf.util;

import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.apache.poi.xdgf.usermodel.XDGFPage;
import org.apache.poi.xdgf.usermodel.XDGFShape;
import org.apache.poi.xdgf.usermodel.XmlVisioDocument;
import org.apache.poi.xdgf.usermodel.shape.ShapeVisitor;

public class HierarchyPrinter {
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0049, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005b, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void printHierarchy(org.apache.poi.xdgf.usermodel.XDGFPage r4, java.io.File r5) throws java.io.FileNotFoundException, java.io.UnsupportedEncodingException, java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "page"
            r1.<init>(r2)
            long r2 = r4.getPageNumber()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = "-"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = r4.getName()
            java.lang.String r2 = org.apache.poi.xdgf.util.Util.sanitizeFilename(r2)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ".txt"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r5, r1)
            java.io.FileOutputStream r5 = new java.io.FileOutputStream
            r5.<init>(r0)
            java.io.PrintStream r0 = new java.io.PrintStream     // Catch:{ all -> 0x0053 }
            java.lang.String r1 = "utf-8"
            r2 = 0
            r0.<init>(r5, r2, r1)     // Catch:{ all -> 0x0053 }
            printHierarchy((org.apache.poi.xdgf.usermodel.XDGFPage) r4, (java.io.PrintStream) r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ all -> 0x0053 }
            r5.close()
            return
        L_0x0047:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r4.addSuppressed(r0)     // Catch:{ all -> 0x0053 }
        L_0x0052:
            throw r1     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            r5.close()     // Catch:{ all -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x005e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.util.HierarchyPrinter.printHierarchy(org.apache.poi.xdgf.usermodel.XDGFPage, java.io.File):void");
    }

    public static void printHierarchy(XDGFPage xDGFPage, final PrintStream printStream) {
        xDGFPage.getContent().visitShapes(new ShapeVisitor() {
            public void visit(XDGFShape xDGFShape, AffineTransform affineTransform, int i) {
                for (int i2 = 0; i2 < i; i2++) {
                    printStream.append("  ");
                }
                printStream.println(xDGFShape + " [" + xDGFShape.getShapeType() + ", " + xDGFShape.getSymbolName() + "] " + xDGFShape.getMasterShape() + " " + xDGFShape.getTextAsString().trim());
            }
        });
    }

    public static void printHierarchy(XmlVisioDocument xmlVisioDocument, String str) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File(str);
        for (XDGFPage printHierarchy : xmlVisioDocument.getPages()) {
            printHierarchy(printHierarchy, file);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r3.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r3) throws java.lang.Exception {
        /*
            int r0 = r3.length
            r1 = 2
            r2 = 1
            if (r0 == r1) goto L_0x000f
            java.io.PrintStream r0 = java.lang.System.err
            java.lang.String r1 = "Usage: in.vsdx outdir"
            r0.println(r1)
            java.lang.System.exit(r2)
        L_0x000f:
            r0 = 0
            r0 = r3[r0]
            r3 = r3[r2]
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r0)
            org.apache.poi.xdgf.usermodel.XmlVisioDocument r0 = new org.apache.poi.xdgf.usermodel.XmlVisioDocument     // Catch:{ all -> 0x0025 }
            r0.<init>((java.io.InputStream) r1)     // Catch:{ all -> 0x0025 }
            printHierarchy((org.apache.poi.xdgf.usermodel.XmlVisioDocument) r0, (java.lang.String) r3)     // Catch:{ all -> 0x0025 }
            r1.close()
            return
        L_0x0025:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0027 }
        L_0x0027:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r1 = move-exception
            r3.addSuppressed(r1)
        L_0x0030:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xdgf.util.HierarchyPrinter.main(java.lang.String[]):void");
    }
}
