package org.apache.poi.xwpf.usermodel;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocDefaults;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrDefault;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;

public class XWPFStyles extends POIXMLDocumentPart {
    private CTStyles ctStyles;
    private XWPFDefaultParagraphStyle defaultParaStyle;
    private XWPFDefaultRunStyle defaultRunStyle;
    private XWPFLatentStyles latentStyles;
    private final List<XWPFStyle> listStyle = new ArrayList();

    public XWPFStyles(PackagePart packagePart) {
        super(packagePart);
    }

    public XWPFStyles() {
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        if (r0 != null) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDocumentRead() throws java.io.IOException {
        /*
            r3 = this;
            org.apache.poi.openxml4j.opc.PackagePart r0 = r3.getPackagePart()     // Catch:{ XmlException -> 0x003a }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ XmlException -> 0x003a }
            org.apache.xmlbeans.impl.schema.DocumentFactory<org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument> r1 = org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument.Factory     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlOptions r2 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS     // Catch:{ all -> 0x002c }
            java.lang.Object r1 = r1.parse((java.io.InputStream) r0, (org.apache.xmlbeans.XmlOptions) r2)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.StylesDocument) r1     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles r1 = r1.getStyles()     // Catch:{ all -> 0x002c }
            r3.setStyles(r1)     // Catch:{ all -> 0x002c }
            org.apache.poi.xwpf.usermodel.XWPFLatentStyles r1 = new org.apache.poi.xwpf.usermodel.XWPFLatentStyles     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles r2 = r3.ctStyles     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles r2 = r2.getLatentStyles()     // Catch:{ all -> 0x002c }
            r1.<init>(r2, r3)     // Catch:{ all -> 0x002c }
            r3.latentStyles = r1     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x002b
            r0.close()     // Catch:{ XmlException -> 0x003a }
        L_0x002b:
            return
        L_0x002c:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x002e }
        L_0x002e:
            r1 = move-exception
            if (r0 == 0) goto L_0x0039
            r0.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r0 = move-exception
            r3.addSuppressed(r0)     // Catch:{ XmlException -> 0x003a }
        L_0x0039:
            throw r1     // Catch:{ XmlException -> 0x003a }
        L_0x003a:
            r3 = move-exception
            org.apache.poi.ooxml.POIXMLException r0 = new org.apache.poi.ooxml.POIXMLException
            java.lang.String r1 = "Unable to read styles"
            r0.<init>(r1, r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFStyles.onDocumentRead():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0034, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r1 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r4.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003f, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void commit() throws java.io.IOException {
        /*
            r4 = this;
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles r0 = r4.ctStyles
            if (r0 == 0) goto L_0x0040
            org.apache.xmlbeans.XmlOptions r0 = new org.apache.xmlbeans.XmlOptions
            org.apache.xmlbeans.XmlOptions r1 = org.apache.poi.ooxml.POIXMLTypeLoader.DEFAULT_XML_OPTIONS
            r0.<init>(r1)
            javax.xml.namespace.QName r1 = new javax.xml.namespace.QName
            org.apache.xmlbeans.SchemaType r2 = org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles.type
            javax.xml.namespace.QName r2 = r2.getName()
            java.lang.String r2 = r2.getNamespaceURI()
            java.lang.String r3 = "styles"
            r1.<init>(r2, r3)
            r0.setSaveSyntheticDocumentElement(r1)
            org.apache.poi.openxml4j.opc.PackagePart r1 = r4.getPackagePart()
            java.io.OutputStream r1 = r1.getOutputStream()
            org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles r4 = r4.ctStyles     // Catch:{ all -> 0x0032 }
            r4.save((java.io.OutputStream) r1, (org.apache.xmlbeans.XmlOptions) r0)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            return
        L_0x0032:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0034 }
        L_0x0034:
            r0 = move-exception
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ all -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r1 = move-exception
            r4.addSuppressed(r1)
        L_0x003f:
            throw r0
        L_0x0040:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Unable to write out styles that were never read in!"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xwpf.usermodel.XWPFStyles.commit():void");
    }

    /* access modifiers changed from: protected */
    public void ensureDocDefaults() {
        if (!this.ctStyles.isSetDocDefaults()) {
            this.ctStyles.addNewDocDefaults();
        }
        CTDocDefaults docDefaults = this.ctStyles.getDocDefaults();
        if (!docDefaults.isSetPPrDefault()) {
            docDefaults.addNewPPrDefault();
        }
        if (!docDefaults.isSetRPrDefault()) {
            docDefaults.addNewRPrDefault();
        }
        CTPPrDefault pPrDefault = docDefaults.getPPrDefault();
        CTRPrDefault rPrDefault = docDefaults.getRPrDefault();
        if (!pPrDefault.isSetPPr()) {
            pPrDefault.addNewPPr();
        }
        if (!rPrDefault.isSetRPr()) {
            rPrDefault.addNewRPr();
        }
        this.defaultRunStyle = new XWPFDefaultRunStyle(rPrDefault.getRPr());
        this.defaultParaStyle = new XWPFDefaultParagraphStyle(pPrDefault.getPPr());
    }

    public void setStyles(CTStyles cTStyles) {
        this.ctStyles = cTStyles;
        for (CTStyle xWPFStyle : cTStyles.getStyleArray()) {
            this.listStyle.add(new XWPFStyle(xWPFStyle, this));
        }
        if (this.ctStyles.isSetDocDefaults()) {
            CTDocDefaults docDefaults = this.ctStyles.getDocDefaults();
            if (docDefaults.isSetRPrDefault() && docDefaults.getRPrDefault().isSetRPr()) {
                this.defaultRunStyle = new XWPFDefaultRunStyle(docDefaults.getRPrDefault().getRPr());
            }
            if (docDefaults.isSetPPrDefault() && docDefaults.getPPrDefault().isSetPPr()) {
                this.defaultParaStyle = new XWPFDefaultParagraphStyle(docDefaults.getPPrDefault().getPPr());
            }
        }
    }

    public boolean styleExist(String str) {
        return getStyle(str) != null;
    }

    public void addStyle(XWPFStyle xWPFStyle) {
        this.listStyle.add(xWPFStyle);
        this.ctStyles.addNewStyle();
        this.ctStyles.setStyleArray(this.ctStyles.sizeOfStyleArray() - 1, xWPFStyle.getCTStyle());
    }

    public XWPFStyle getStyle(String str) {
        for (XWPFStyle next : this.listStyle) {
            if (next.getStyleId() != null && next.getStyleId().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public int getNumberOfStyles() {
        return this.listStyle.size();
    }

    public List<XWPFStyle> getUsedStyleList(XWPFStyle xWPFStyle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(xWPFStyle);
        return getUsedStyleList(xWPFStyle, arrayList);
    }

    private List<XWPFStyle> getUsedStyleList(XWPFStyle xWPFStyle, List<XWPFStyle> list) {
        XWPFStyle style = getStyle(xWPFStyle.getBasisStyleID());
        if (style != null && !list.contains(style)) {
            list.add(style);
            getUsedStyleList(style, list);
        }
        XWPFStyle style2 = getStyle(xWPFStyle.getLinkStyleID());
        if (style2 != null && !list.contains(style2)) {
            list.add(style2);
            getUsedStyleList(style2, list);
        }
        XWPFStyle style3 = getStyle(xWPFStyle.getNextStyleID());
        if (style3 != null && !list.contains(style3)) {
            list.add(style3);
            getUsedStyleList(style3, list);
        }
        return list;
    }

    /* access modifiers changed from: protected */
    public CTLanguage getCTLanguage() {
        ensureDocDefaults();
        if (this.defaultRunStyle.getRPr().sizeOfLangArray() > 0) {
            return this.defaultRunStyle.getRPr().getLangArray(0);
        }
        return this.defaultRunStyle.getRPr().addNewLang();
    }

    public void setSpellingLanguage(String str) {
        CTLanguage cTLanguage = getCTLanguage();
        cTLanguage.setVal(str);
        cTLanguage.setBidi(str);
    }

    public void setEastAsia(String str) {
        getCTLanguage().setEastAsia(str);
    }

    public void setDefaultFonts(CTFonts cTFonts) {
        ensureDocDefaults();
        CTRPr rPr = this.defaultRunStyle.getRPr();
        if (rPr.sizeOfRFontsArray() == 0) {
            rPr.addNewRFonts();
        }
        rPr.setRFontsArray(0, cTFonts);
    }

    public XWPFStyle getStyleWithSameName(XWPFStyle xWPFStyle) {
        for (XWPFStyle next : this.listStyle) {
            if (next.hasSameName(xWPFStyle)) {
                return next;
            }
        }
        return null;
    }

    public XWPFDefaultRunStyle getDefaultRunStyle() {
        ensureDocDefaults();
        return this.defaultRunStyle;
    }

    public XWPFDefaultParagraphStyle getDefaultParagraphStyle() {
        ensureDocDefaults();
        return this.defaultParaStyle;
    }

    public XWPFLatentStyles getLatentStyles() {
        return this.latentStyles;
    }

    public XWPFStyle getStyleWithName(String str) {
        for (XWPFStyle next : this.listStyle) {
            if (str.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }
}
