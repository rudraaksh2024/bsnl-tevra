package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBaseStyles;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;

public class XSLFTheme extends POIXMLDocumentPart {
    private CTOfficeStyleSheet _theme;

    XSLFTheme() {
        this._theme = CTOfficeStyleSheet.Factory.newInstance();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002b, code lost:
        r2.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        if (r3 != null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XSLFTheme(org.apache.poi.openxml4j.opc.PackagePart r3) throws java.io.IOException, org.apache.xmlbeans.XmlException {
        /*
            r2 = this;
            r2.<init>((org.apache.poi.openxml4j.opc.PackagePart) r3)
            org.apache.poi.openxml4j.opc.PackagePart r3 = r2.getPackagePart()
            java.io.InputStream r3 = r3.getInputStream()
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument> r0 = org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument.Factory     // Catch:{ all -> 0x0021 }
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.parse((java.io.InputStream) r3, (org.apache.xmlbeans.XmlOptions) r1)     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument r0 = (org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument) r0     // Catch:{ all -> 0x0021 }
            org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet r0 = r0.getTheme()     // Catch:{ all -> 0x0021 }
            r2._theme = r0     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x0020
            r3.close()
        L_0x0020:
            return
        L_0x0021:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0023 }
        L_0x0023:
            r0 = move-exception
            if (r3 == 0) goto L_0x002e
            r3.close()     // Catch:{ all -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r3 = move-exception
            r2.addSuppressed(r3)
        L_0x002e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTheme.<init>(org.apache.poi.openxml4j.opc.PackagePart):void");
    }

    public void importTheme(XSLFTheme xSLFTheme) {
        this._theme = xSLFTheme.getXmlObject();
    }

    public String getName() {
        return this._theme.getName();
    }

    public void setName(String str) {
        this._theme.setName(str);
    }

    @Internal
    public CTColor getCTColor(String str) {
        CTColorScheme cTColorScheme;
        CTBaseStyles themeElements = this._theme.getThemeElements();
        if (themeElements == null) {
            cTColorScheme = null;
        } else {
            cTColorScheme = themeElements.getClrScheme();
        }
        return getMapColor(str, cTColorScheme);
    }

    private static CTColor getMapColor(String str, CTColorScheme cTColorScheme) {
        if (str == null || cTColorScheme == null) {
            return null;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1177623385:
                if (str.equals("accent1")) {
                    c = 0;
                    break;
                }
                break;
            case -1177623384:
                if (str.equals("accent2")) {
                    c = 1;
                    break;
                }
                break;
            case -1177623383:
                if (str.equals("accent3")) {
                    c = 2;
                    break;
                }
                break;
            case -1177623382:
                if (str.equals("accent4")) {
                    c = 3;
                    break;
                }
                break;
            case -1177623381:
                if (str.equals("accent5")) {
                    c = 4;
                    break;
                }
                break;
            case -1177623380:
                if (str.equals("accent6")) {
                    c = 5;
                    break;
                }
                break;
            case 99466:
                if (str.equals("dk1")) {
                    c = 6;
                    break;
                }
                break;
            case 99467:
                if (str.equals("dk2")) {
                    c = 7;
                    break;
                }
                break;
            case 107433:
                if (str.equals("lt1")) {
                    c = 8;
                    break;
                }
                break;
            case 107434:
                if (str.equals("lt2")) {
                    c = 9;
                    break;
                }
                break;
            case 99368034:
                if (str.equals("hlink")) {
                    c = 10;
                    break;
                }
                break;
            case 268452191:
                if (str.equals("folHlink")) {
                    c = 11;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return cTColorScheme.getAccent1();
            case 1:
                return cTColorScheme.getAccent2();
            case 2:
                return cTColorScheme.getAccent3();
            case 3:
                return cTColorScheme.getAccent4();
            case 4:
                return cTColorScheme.getAccent5();
            case 5:
                return cTColorScheme.getAccent6();
            case 6:
                return cTColorScheme.getDk1();
            case 7:
                return cTColorScheme.getDk2();
            case 8:
                return cTColorScheme.getLt1();
            case 9:
                return cTColorScheme.getLt2();
            case 10:
                return cTColorScheme.getHlink();
            case 11:
                return cTColorScheme.getFolHlink();
            default:
                return null;
        }
    }

    @Internal
    public CTOfficeStyleSheet getXmlObject() {
        return this._theme;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0031, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002b, code lost:
        if (r1 != null) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            java.lang.String r2 = "http://schemas.openxmlformats.org/drawingml/2006/main"
            java.lang.String r3 = "theme"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet r4 = r4.getXmlObject()     // Catch:{ all -> 0x0028 }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0028 }
            if (r1 == 0) goto L_0x0027
            r1.close()
        L_0x0027:
            return
        L_0x0028:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x002a }
        L_0x002a:
            r0 = move-exception
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ all -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x0035:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTheme.commit():void");
    }

    public String getMajorFont() {
        return this._theme.getThemeElements().getFontScheme().getMajorFont().getLatin().getTypeface();
    }

    public String getMinorFont() {
        return this._theme.getThemeElements().getFontScheme().getMinorFont().getLatin().getTypeface();
    }
}
