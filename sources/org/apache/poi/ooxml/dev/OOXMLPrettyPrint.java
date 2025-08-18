package org.apache.poi.ooxml.dev;

import java.io.File;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.util.XMLHelper;
import org.w3c.dom.Document;

public class OOXMLPrettyPrint {
    private static final String XML_INDENT_AMOUNT = "{http://xml.apache.org/xslt}indent-amount";
    private final DocumentBuilder documentBuilder = DocumentHelper.newDocumentBuilder();

    public OOXMLPrettyPrint() {
        ZipSecureFile.setMinInflateRatio(1.0E-5d);
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length <= 1 || strArr.length % 2 != 0) {
            System.err.println("Use:");
            System.err.println("\tjava OOXMLPrettyPrint [<filename> <outfilename>] ...");
            System.exit(1);
        }
        for (int i = 0; i < strArr.length; i += 2) {
            File file = new File(strArr[i]);
            if (!file.exists()) {
                System.err.println("Error, file not found!");
                System.err.println("\t" + file);
                System.exit(2);
            }
            handleFile(file, new File(strArr[i + 1]));
        }
        System.out.println("Done.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
        if (r3 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0060, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void handleFile(java.io.File r3, java.io.File r4) throws java.io.IOException {
        /*
            java.io.PrintStream r0 = java.lang.System.out
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Reading zip-file "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r2 = " and writing pretty-printed XML to "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.println(r1)
            org.apache.poi.openxml4j.util.ZipSecureFile r3 = org.apache.poi.openxml4j.opc.internal.ZipHelper.openZipFile((java.io.File) r3)     // Catch:{ all -> 0x0061 }
            java.util.zip.ZipOutputStream r0 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x0053 }
            java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x0053 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x0053 }
            r2.<init>(r4)     // Catch:{ all -> 0x0053 }
            r1.<init>(r2)     // Catch:{ all -> 0x0053 }
            r0.<init>(r1)     // Catch:{ all -> 0x0053 }
            org.apache.poi.ooxml.dev.OOXMLPrettyPrint r4 = new org.apache.poi.ooxml.dev.OOXMLPrettyPrint     // Catch:{ all -> 0x0047 }
            r4.<init>()     // Catch:{ all -> 0x0047 }
            r4.handle(r3, r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ all -> 0x0053 }
            if (r3 == 0) goto L_0x0041
            r3.close()     // Catch:{ all -> 0x0061 }
        L_0x0041:
            java.io.PrintStream r3 = java.lang.System.out
            r3.println()
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
            if (r3 == 0) goto L_0x0060
            r3.close()     // Catch:{ all -> 0x005c }
            goto L_0x0060
        L_0x005c:
            r3 = move-exception
            r4.addSuppressed(r3)     // Catch:{ all -> 0x0061 }
        L_0x0060:
            throw r0     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r3 = move-exception
            java.io.PrintStream r4 = java.lang.System.out
            r4.println()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.dev.OOXMLPrettyPrint.handleFile(java.io.File, java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0055, code lost:
        if (r1 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005f, code lost:
        throw r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handle(org.apache.poi.openxml4j.util.ZipSecureFile r7, java.util.zip.ZipOutputStream r8) throws java.io.IOException {
        /*
            r6 = this;
            java.util.Enumeration r0 = r7.getEntries()
        L_0x0004:
            boolean r1 = r0.hasMoreElements()
            if (r1 == 0) goto L_0x00a2
            java.lang.Object r1 = r0.nextElement()
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r1
            java.lang.String r2 = r1.getName()
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry
            r3.<init>(r2)
            r8.putNextEntry(r3)
            java.lang.String r3 = ".xml"
            boolean r3 = r2.endsWith(r3)     // Catch:{ Exception -> 0x0084 }
            if (r3 != 0) goto L_0x0060
            java.lang.String r3 = ".rels"
            boolean r3 = r2.endsWith(r3)     // Catch:{ Exception -> 0x0084 }
            if (r3 == 0) goto L_0x002d
            goto L_0x0060
        L_0x002d:
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0084 }
            r4.<init>()     // Catch:{ Exception -> 0x0084 }
            java.lang.String r5 = "Not pretty-printing non-XML file "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Exception -> 0x0084 }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ Exception -> 0x0084 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0084 }
            r3.println(r4)     // Catch:{ Exception -> 0x0084 }
            org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream r1 = r7.getInputStream((org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r1)     // Catch:{ Exception -> 0x0084 }
            org.apache.poi.util.IOUtils.copy((java.io.InputStream) r1, (java.io.OutputStream) r8)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0077
            r1.close()     // Catch:{ Exception -> 0x0084 }
            goto L_0x0077
        L_0x0052:
            r6 = move-exception
            throw r6     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r7 = move-exception
            if (r1 == 0) goto L_0x005f
            r1.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r6.addSuppressed(r0)     // Catch:{ Exception -> 0x0084 }
        L_0x005f:
            throw r7     // Catch:{ Exception -> 0x0084 }
        L_0x0060:
            javax.xml.parsers.DocumentBuilder r3 = r6.documentBuilder     // Catch:{ Exception -> 0x0084 }
            org.xml.sax.InputSource r4 = new org.xml.sax.InputSource     // Catch:{ Exception -> 0x0084 }
            org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream r1 = r7.getInputStream((org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r1)     // Catch:{ Exception -> 0x0084 }
            r4.<init>(r1)     // Catch:{ Exception -> 0x0084 }
            org.w3c.dom.Document r1 = r3.parse(r4)     // Catch:{ Exception -> 0x0084 }
            r3 = 1
            r1.setXmlStandalone(r3)     // Catch:{ Exception -> 0x0084 }
            r3 = 2
            pretty(r1, r8, r3)     // Catch:{ Exception -> 0x0084 }
        L_0x0077:
            r8.closeEntry()
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.String r2 = "."
            r1.print(r2)
            goto L_0x0004
        L_0x0082:
            r6 = move-exception
            goto L_0x009e
        L_0x0084:
            r6 = move-exception
            java.io.IOException r7 = new java.io.IOException     // Catch:{ all -> 0x0082 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0082 }
            r0.<init>()     // Catch:{ all -> 0x0082 }
            java.lang.String r1 = "While handling entry "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0082 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0082 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0082 }
            r7.<init>(r0, r6)     // Catch:{ all -> 0x0082 }
            throw r7     // Catch:{ all -> 0x0082 }
        L_0x009e:
            r8.closeEntry()
            throw r6
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ooxml.dev.OOXMLPrettyPrint.handle(org.apache.poi.openxml4j.util.ZipSecureFile, java.util.zip.ZipOutputStream):void");
    }

    private static void pretty(Document document, OutputStream outputStream, int i) throws TransformerException {
        Transformer newTransformer = XMLHelper.newTransformer();
        if (i > 0) {
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty(XML_INDENT_AMOUNT, Integer.toString(i));
        }
        newTransformer.transform(new DOMSource(document), new StreamResult(outputStream));
    }
}
