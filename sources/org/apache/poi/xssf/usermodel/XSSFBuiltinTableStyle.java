package org.apache.poi.xssf.usermodel;

import java.util.EnumMap;
import java.util.Map;
import javax.xml.transform.TransformerException;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.ss.usermodel.TableStyleType;
import org.w3c.dom.Node;

public enum XSSFBuiltinTableStyle {
    TableStyleDark1,
    TableStyleDark2,
    TableStyleDark3,
    TableStyleDark4,
    TableStyleDark5,
    TableStyleDark6,
    TableStyleDark7,
    TableStyleDark8,
    TableStyleDark9,
    TableStyleDark10,
    TableStyleDark11,
    TableStyleLight1,
    TableStyleLight2,
    TableStyleLight3,
    TableStyleLight4,
    TableStyleLight5,
    TableStyleLight6,
    TableStyleLight7,
    TableStyleLight8,
    TableStyleLight9,
    TableStyleLight10,
    TableStyleLight11,
    TableStyleLight12,
    TableStyleLight13,
    TableStyleLight14,
    TableStyleLight15,
    TableStyleLight16,
    TableStyleLight17,
    TableStyleLight18,
    TableStyleLight19,
    TableStyleLight20,
    TableStyleLight21,
    TableStyleMedium1,
    TableStyleMedium2,
    TableStyleMedium3,
    TableStyleMedium4,
    TableStyleMedium5,
    TableStyleMedium6,
    TableStyleMedium7,
    TableStyleMedium8,
    TableStyleMedium9,
    TableStyleMedium10,
    TableStyleMedium11,
    TableStyleMedium12,
    TableStyleMedium13,
    TableStyleMedium14,
    TableStyleMedium15,
    TableStyleMedium16,
    TableStyleMedium17,
    TableStyleMedium18,
    TableStyleMedium19,
    TableStyleMedium20,
    TableStyleMedium21,
    TableStyleMedium22,
    TableStyleMedium23,
    TableStyleMedium24,
    TableStyleMedium25,
    TableStyleMedium26,
    TableStyleMedium27,
    TableStyleMedium28,
    PivotStyleMedium1,
    PivotStyleMedium2,
    PivotStyleMedium3,
    PivotStyleMedium4,
    PivotStyleMedium5,
    PivotStyleMedium6,
    PivotStyleMedium7,
    PivotStyleMedium8,
    PivotStyleMedium9,
    PivotStyleMedium10,
    PivotStyleMedium11,
    PivotStyleMedium12,
    PivotStyleMedium13,
    PivotStyleMedium14,
    PivotStyleMedium15,
    PivotStyleMedium16,
    PivotStyleMedium17,
    PivotStyleMedium18,
    PivotStyleMedium19,
    PivotStyleMedium20,
    PivotStyleMedium21,
    PivotStyleMedium22,
    PivotStyleMedium23,
    PivotStyleMedium24,
    PivotStyleMedium25,
    PivotStyleMedium26,
    PivotStyleMedium27,
    PivotStyleMedium28,
    PivotStyleLight1,
    PivotStyleLight2,
    PivotStyleLight3,
    PivotStyleLight4,
    PivotStyleLight5,
    PivotStyleLight6,
    PivotStyleLight7,
    PivotStyleLight8,
    PivotStyleLight9,
    PivotStyleLight10,
    PivotStyleLight11,
    PivotStyleLight12,
    PivotStyleLight13,
    PivotStyleLight14,
    PivotStyleLight15,
    PivotStyleLight16,
    PivotStyleLight17,
    PivotStyleLight18,
    PivotStyleLight19,
    PivotStyleLight20,
    PivotStyleLight21,
    PivotStyleLight22,
    PivotStyleLight23,
    PivotStyleLight24,
    PivotStyleLight25,
    PivotStyleLight26,
    PivotStyleLight27,
    PivotStyleLight28,
    PivotStyleDark1,
    PivotStyleDark2,
    PivotStyleDark3,
    PivotStyleDark4,
    PivotStyleDark5,
    PivotStyleDark6,
    PivotStyleDark7,
    PivotStyleDark8,
    PivotStyleDark9,
    PivotStyleDark10,
    PivotStyleDark11,
    PivotStyleDark12,
    PivotStyleDark13,
    PivotStyleDark14,
    PivotStyleDark15,
    PivotStyleDark16,
    PivotStyleDark17,
    PivotStyleDark18,
    PivotStyleDark19,
    PivotStyleDark20,
    PivotStyleDark21,
    PivotStyleDark22,
    PivotStyleDark23,
    PivotStyleDark24,
    PivotStyleDark25,
    PivotStyleDark26,
    PivotStyleDark27,
    PivotStyleDark28;
    
    private static final Map<XSSFBuiltinTableStyle, TableStyle> styleMap = null;

    static {
        styleMap = new EnumMap(XSSFBuiltinTableStyle.class);
    }

    public TableStyle getStyle() {
        init();
        return styleMap.get(this);
    }

    public static boolean isBuiltinStyle(TableStyle tableStyle) {
        if (tableStyle == null) {
            return false;
        }
        try {
            valueOf(tableStyle.getName());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0080, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0085, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0089, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0090, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0093, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0094, code lost:
        if (r1 != null) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void init() {
        /*
            java.lang.Class<org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle> r0 = org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle.class
            monitor-enter(r0)
            java.util.Map<org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle, org.apache.poi.ss.usermodel.TableStyle> r1 = styleMap     // Catch:{ all -> 0x00a6 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x00a6 }
            if (r1 != 0) goto L_0x000d
            monitor-exit(r0)
            return
        L_0x000d:
            java.lang.Class<org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle> r1 = org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle.class
            java.lang.String r2 = "presetTableStyles.xml"
            java.io.InputStream r1 = r1.getResourceAsStream(r2)     // Catch:{ Exception -> 0x009f }
            org.w3c.dom.Document r2 = org.apache.poi.ooxml.util.DocumentHelper.readDocument((java.io.InputStream) r1)     // Catch:{ all -> 0x0091 }
            org.w3c.dom.Element r2 = r2.getDocumentElement()     // Catch:{ all -> 0x0091 }
            org.w3c.dom.NodeList r2 = r2.getChildNodes()     // Catch:{ all -> 0x0091 }
            r3 = 0
            r4 = r3
        L_0x0023:
            int r5 = r2.getLength()     // Catch:{ all -> 0x0091 }
            if (r4 >= r5) goto L_0x008a
            org.w3c.dom.Node r5 = r2.item(r4)     // Catch:{ all -> 0x0091 }
            short r6 = r5.getNodeType()     // Catch:{ all -> 0x0091 }
            r7 = 1
            if (r6 == r7) goto L_0x0035
            goto L_0x007b
        L_0x0035:
            org.w3c.dom.Element r5 = (org.w3c.dom.Element) r5     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = r5.getTagName()     // Catch:{ all -> 0x0091 }
            org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle r7 = valueOf(r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r8 = "dxfs"
            org.w3c.dom.NodeList r8 = r5.getElementsByTagName(r8)     // Catch:{ all -> 0x0091 }
            org.w3c.dom.Node r8 = r8.item(r3)     // Catch:{ all -> 0x0091 }
            java.lang.String r9 = "tableStyles"
            org.w3c.dom.NodeList r5 = r5.getElementsByTagName(r9)     // Catch:{ all -> 0x0091 }
            org.w3c.dom.Node r5 = r5.item(r3)     // Catch:{ all -> 0x0091 }
            org.apache.poi.xssf.model.StylesTable r9 = new org.apache.poi.xssf.model.StylesTable     // Catch:{ all -> 0x0091 }
            r9.<init>()     // Catch:{ all -> 0x0091 }
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r10 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream     // Catch:{ all -> 0x0091 }
            java.lang.String r5 = styleXML(r8, r5)     // Catch:{ all -> 0x0091 }
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ all -> 0x0091 }
            byte[] r5 = r5.getBytes(r8)     // Catch:{ all -> 0x0091 }
            r10.<init>(r5)     // Catch:{ all -> 0x0091 }
            r9.readFrom(r10)     // Catch:{ all -> 0x007e }
            r10.close()     // Catch:{ all -> 0x0091 }
            java.util.Map<org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle, org.apache.poi.ss.usermodel.TableStyle> r5 = styleMap     // Catch:{ all -> 0x0091 }
            org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle$XSSFBuiltinTypeStyleStyle r8 = new org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle$XSSFBuiltinTypeStyleStyle     // Catch:{ all -> 0x0091 }
            org.apache.poi.ss.usermodel.TableStyle r6 = r9.getExplicitTableStyle(r6)     // Catch:{ all -> 0x0091 }
            r8.<init>(r7, r6)     // Catch:{ all -> 0x0091 }
            r5.put(r7, r8)     // Catch:{ all -> 0x0091 }
        L_0x007b:
            int r4 = r4 + 1
            goto L_0x0023
        L_0x007e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r3 = move-exception
            r10.close()     // Catch:{ all -> 0x0085 }
            goto L_0x0089
        L_0x0085:
            r4 = move-exception
            r2.addSuppressed(r4)     // Catch:{ all -> 0x0091 }
        L_0x0089:
            throw r3     // Catch:{ all -> 0x0091 }
        L_0x008a:
            if (r1 == 0) goto L_0x008f
            r1.close()     // Catch:{ Exception -> 0x009f }
        L_0x008f:
            monitor-exit(r0)
            return
        L_0x0091:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r3 = move-exception
            if (r1 == 0) goto L_0x009e
            r1.close()     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r1 = move-exception
            r2.addSuppressed(r1)     // Catch:{ Exception -> 0x009f }
        L_0x009e:
            throw r3     // Catch:{ Exception -> 0x009f }
        L_0x009f:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException     // Catch:{ all -> 0x00a6 }
            r2.<init>(r1)     // Catch:{ all -> 0x00a6 }
            throw r2     // Catch:{ all -> 0x00a6 }
        L_0x00a6:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle.init():void");
    }

    private static String styleXML(Node node, Node node2) throws TransformerException {
        node.insertBefore(node.getOwnerDocument().createElement("dxf"), node.getFirstChild());
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:x14ac=\"http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac\" xmlns:x16r2=\"http://schemas.microsoft.com/office/spreadsheetml/2015/02/main\" mc:Ignorable=\"x14ac x16r2\">\n" + writeToString(node) + writeToString(node2) + "</styleSheet>";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String writeToString(org.w3c.dom.Node r4) throws javax.xml.transform.TransformerException {
        /*
            org.apache.commons.io.output.StringBuilderWriter r0 = new org.apache.commons.io.output.StringBuilderWriter
            r1 = 1024(0x400, float:1.435E-42)
            r0.<init>((int) r1)
            javax.xml.transform.Transformer r1 = org.apache.poi.util.XMLHelper.newTransformer()     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "omit-xml-declaration"
            java.lang.String r3 = "yes"
            r1.setOutputProperty(r2, r3)     // Catch:{ all -> 0x0027 }
            javax.xml.transform.dom.DOMSource r2 = new javax.xml.transform.dom.DOMSource     // Catch:{ all -> 0x0027 }
            r2.<init>(r4)     // Catch:{ all -> 0x0027 }
            javax.xml.transform.stream.StreamResult r4 = new javax.xml.transform.stream.StreamResult     // Catch:{ all -> 0x0027 }
            r4.<init>(r0)     // Catch:{ all -> 0x0027 }
            r1.transform(r2, r4)     // Catch:{ all -> 0x0027 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x0027 }
            r0.close()
            return r4
        L_0x0027:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x002e }
            goto L_0x0032
        L_0x002e:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0032:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle.writeToString(org.w3c.dom.Node):java.lang.String");
    }

    protected static class XSSFBuiltinTypeStyleStyle implements TableStyle {
        private final XSSFBuiltinTableStyle builtIn;
        private final TableStyle style;

        public boolean isBuiltin() {
            return true;
        }

        protected XSSFBuiltinTypeStyleStyle(XSSFBuiltinTableStyle xSSFBuiltinTableStyle, TableStyle tableStyle) {
            this.builtIn = xSSFBuiltinTableStyle;
            this.style = tableStyle;
        }

        public String getName() {
            return this.style.getName();
        }

        public int getIndex() {
            return this.builtIn.ordinal();
        }

        public DifferentialStyleProvider getStyle(TableStyleType tableStyleType) {
            return this.style.getStyle(tableStyleType);
        }
    }
}
