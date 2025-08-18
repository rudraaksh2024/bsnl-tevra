package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFacet;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;

public class XSLFFontInfo implements FontInfo {
    final CTEmbeddedFontListEntry fontListEntry;
    final XMLSlideShow ppt;
    final String typeface;

    public XSLFFontInfo(XMLSlideShow xMLSlideShow, String str) {
        this.ppt = xMLSlideShow;
        this.typeface = str;
        CTPresentation cTPresentation = xMLSlideShow.getCTPresentation();
        CTEmbeddedFontList embeddedFontLst = cTPresentation.isSetEmbeddedFontLst() ? cTPresentation.getEmbeddedFontLst() : cTPresentation.addNewEmbeddedFontLst();
        for (CTEmbeddedFontListEntry cTEmbeddedFontListEntry : embeddedFontLst.getEmbeddedFontArray()) {
            if (str.equalsIgnoreCase(cTEmbeddedFontListEntry.getFont().getTypeface())) {
                this.fontListEntry = cTEmbeddedFontListEntry;
                return;
            }
        }
        CTEmbeddedFontListEntry addNewEmbeddedFont = embeddedFontLst.addNewEmbeddedFont();
        this.fontListEntry = addNewEmbeddedFont;
        addNewEmbeddedFont.addNewFont().setTypeface(str);
    }

    public XSLFFontInfo(XMLSlideShow xMLSlideShow, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
        this.ppt = xMLSlideShow;
        this.typeface = cTEmbeddedFontListEntry.getFont().getTypeface();
        this.fontListEntry = cTEmbeddedFontListEntry;
    }

    public String getTypeface() {
        return getFont().getTypeface();
    }

    public void setTypeface(String str) {
        getFont().setTypeface(str);
    }

    public FontCharset getCharset() {
        return FontCharset.valueOf((int) getFont().getCharset());
    }

    public void setCharset(FontCharset fontCharset) {
        getFont().setCharset((byte) fontCharset.getNativeId());
    }

    public FontFamily getFamily() {
        return FontFamily.valueOfPitchFamily(getFont().getPitchFamily());
    }

    public void setFamily(FontFamily fontFamily) {
        getFont().setPitchFamily(FontPitch.getNativeId(FontPitch.valueOfPitchFamily(getFont().getPitchFamily()), fontFamily));
    }

    public FontPitch getPitch() {
        return FontPitch.valueOfPitchFamily(getFont().getPitchFamily());
    }

    public void setPitch(FontPitch fontPitch) {
        getFont().setPitchFamily(FontPitch.getNativeId(fontPitch, FontFamily.valueOfPitchFamily(getFont().getPitchFamily())));
    }

    public byte[] getPanose() {
        return getFont().getPanose();
    }

    public List<FontFacet> getFacets() {
        ArrayList arrayList = new ArrayList();
        if (this.fontListEntry.isSetRegular()) {
            arrayList.add(new XSLFFontFacet(this.fontListEntry.getRegular()));
        }
        if (this.fontListEntry.isSetItalic()) {
            arrayList.add(new XSLFFontFacet(this.fontListEntry.getItalic()));
        }
        if (this.fontListEntry.isSetBold()) {
            arrayList.add(new XSLFFontFacet(this.fontListEntry.getBold()));
        }
        if (this.fontListEntry.isSetBoldItalic()) {
            arrayList.add(new XSLFFontFacet(this.fontListEntry.getBoldItalic()));
        }
        return arrayList;
    }

    public FontFacet addFacet(InputStream inputStream) throws IOException {
        CTEmbeddedFontDataId cTEmbeddedFontDataId;
        FontHeader fontHeader = new FontHeader();
        InputStream bufferInit = fontHeader.bufferInit(inputStream);
        CTPresentation cTPresentation = this.ppt.getCTPresentation();
        cTPresentation.setEmbedTrueTypeFonts(true);
        cTPresentation.setSaveSubsetFonts(true);
        boolean z = false;
        boolean z2 = fontHeader.getWeight() > 400;
        if (fontHeader.isItalic()) {
            z = true;
        }
        boolean z3 = z2 | z;
        if (z3) {
            if (!z3) {
                if (!z3) {
                    if (this.fontListEntry.isSetBoldItalic()) {
                        cTEmbeddedFontDataId = this.fontListEntry.getBoldItalic();
                    } else {
                        cTEmbeddedFontDataId = this.fontListEntry.addNewBoldItalic();
                    }
                } else if (this.fontListEntry.isSetItalic()) {
                    cTEmbeddedFontDataId = this.fontListEntry.getItalic();
                } else {
                    cTEmbeddedFontDataId = this.fontListEntry.addNewItalic();
                }
            } else if (this.fontListEntry.isSetBold()) {
                cTEmbeddedFontDataId = this.fontListEntry.getBold();
            } else {
                cTEmbeddedFontDataId = this.fontListEntry.addNewBold();
            }
        } else if (this.fontListEntry.isSetRegular()) {
            cTEmbeddedFontDataId = this.fontListEntry.getRegular();
        } else {
            cTEmbeddedFontDataId = this.fontListEntry.addNewRegular();
        }
        XSLFFontFacet xSLFFontFacet = new XSLFFontFacet(cTEmbeddedFontDataId);
        xSLFFontFacet.setFontData(bufferInit);
        return xSLFFontFacet;
    }

    private final class XSLFFontFacet implements FontFacet {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final CTEmbeddedFontDataId fontEntry;
        private final FontHeader header;

        static {
            Class<XSLFFontInfo> cls = XSLFFontInfo.class;
        }

        private XSLFFontFacet(CTEmbeddedFontDataId cTEmbeddedFontDataId) {
            this.header = new FontHeader();
            this.fontEntry = cTEmbeddedFontDataId;
        }

        public int getWeight() {
            init();
            return this.header.getWeight();
        }

        public boolean isItalic() {
            init();
            return this.header.isItalic();
        }

        public XSLFFontData getFontData() {
            return (XSLFFontData) XSLFFontInfo.this.ppt.getRelationPartById(this.fontEntry.getId()).getDocumentPart();
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x005d, code lost:
            if (r5 != null) goto L_0x005f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r5.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0063, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0064, code lost:
            r6.addSuppressed(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0067, code lost:
            throw r0;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setFontData(java.io.InputStream r6) throws java.io.IOException {
            /*
                r5 = this;
                org.apache.poi.xslf.usermodel.XSLFRelation r0 = org.apache.poi.xslf.usermodel.XSLFRelation.FONT
                org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId r1 = r5.fontEntry
                java.lang.String r1 = r1.getId()
                if (r1 == 0) goto L_0x001c
                boolean r2 = r1.isEmpty()
                if (r2 == 0) goto L_0x0011
                goto L_0x001c
            L_0x0011:
                org.apache.poi.xslf.usermodel.XSLFFontInfo r5 = org.apache.poi.xslf.usermodel.XSLFFontInfo.this
                org.apache.poi.xslf.usermodel.XMLSlideShow r5 = r5.ppt
                org.apache.poi.ooxml.POIXMLDocumentPart r5 = r5.getRelationById(r1)
                org.apache.poi.xslf.usermodel.XSLFFontData r5 = (org.apache.poi.xslf.usermodel.XSLFFontData) r5
                goto L_0x004d
            L_0x001c:
                org.apache.poi.xslf.usermodel.XSLFFontInfo r1 = org.apache.poi.xslf.usermodel.XSLFFontInfo.this     // Catch:{ InvalidFormatException -> 0x0068 }
                org.apache.poi.xslf.usermodel.XMLSlideShow r1 = r1.ppt     // Catch:{ InvalidFormatException -> 0x0068 }
                org.apache.poi.openxml4j.opc.OPCPackage r1 = r1.getPackage()     // Catch:{ InvalidFormatException -> 0x0068 }
                java.lang.String r2 = r0.getDefaultFileName()     // Catch:{ InvalidFormatException -> 0x0068 }
                int r1 = r1.getUnusedPartIndex(r2)     // Catch:{ InvalidFormatException -> 0x0068 }
                org.apache.poi.xslf.usermodel.XSLFFontInfo r2 = org.apache.poi.xslf.usermodel.XSLFFontInfo.this
                org.apache.poi.xslf.usermodel.XMLSlideShow r2 = r2.ppt
                org.apache.poi.xslf.usermodel.XSLFFactory r3 = org.apache.poi.xslf.usermodel.XSLFFactory.getInstance()
                r4 = 0
                org.apache.poi.ooxml.POIXMLDocumentPart$RelationPart r0 = r2.createRelationship(r0, r3, r1, r4)
                org.apache.poi.ooxml.POIXMLDocumentPart r1 = r0.getDocumentPart()
                org.apache.poi.xslf.usermodel.XSLFFontData r1 = (org.apache.poi.xslf.usermodel.XSLFFontData) r1
                org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId r5 = r5.fontEntry
                org.apache.poi.openxml4j.opc.PackageRelationship r0 = r0.getRelationship()
                java.lang.String r0 = r0.getId()
                r5.setId(r0)
                r5 = r1
            L_0x004d:
                java.io.OutputStream r5 = r5.getOutputStream()
                org.apache.poi.util.IOUtils.copy((java.io.InputStream) r6, (java.io.OutputStream) r5)     // Catch:{ all -> 0x005a }
                if (r5 == 0) goto L_0x0059
                r5.close()
            L_0x0059:
                return
            L_0x005a:
                r6 = move-exception
                throw r6     // Catch:{ all -> 0x005c }
            L_0x005c:
                r0 = move-exception
                if (r5 == 0) goto L_0x0067
                r5.close()     // Catch:{ all -> 0x0063 }
                goto L_0x0067
            L_0x0063:
                r5 = move-exception
                r6.addSuppressed(r5)
            L_0x0067:
                throw r0
            L_0x0068:
                r5 = move-exception
                java.lang.RuntimeException r6 = new java.lang.RuntimeException
                r6.<init>(r5)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFFontInfo.XSLFFontFacet.setFontData(java.io.InputStream):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
            if (r0 != null) goto L_0x0028;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r0.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
            throw r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void init() {
            /*
                r4 = this;
                org.apache.poi.common.usermodel.fonts.FontHeader r0 = r4.header
                java.lang.String r0 = r0.getFamilyName()
                if (r0 != 0) goto L_0x0038
                org.apache.poi.xslf.usermodel.XSLFFontData r0 = r4.getFontData()     // Catch:{ IOException -> 0x0031 }
                java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x0031 }
                r1 = 1000(0x3e8, float:1.401E-42)
                byte[] r1 = org.apache.poi.util.IOUtils.toByteArray((java.io.InputStream) r0, (int) r1)     // Catch:{ all -> 0x0023 }
                org.apache.poi.common.usermodel.fonts.FontHeader r4 = r4.header     // Catch:{ all -> 0x0023 }
                int r2 = r1.length     // Catch:{ all -> 0x0023 }
                r3 = 0
                r4.init(r1, r3, r2)     // Catch:{ all -> 0x0023 }
                if (r0 == 0) goto L_0x0038
                r0.close()     // Catch:{ IOException -> 0x0031 }
                goto L_0x0038
            L_0x0023:
                r4 = move-exception
                throw r4     // Catch:{ all -> 0x0025 }
            L_0x0025:
                r1 = move-exception
                if (r0 == 0) goto L_0x0030
                r0.close()     // Catch:{ all -> 0x002c }
                goto L_0x0030
            L_0x002c:
                r0 = move-exception
                r4.addSuppressed(r0)     // Catch:{ IOException -> 0x0031 }
            L_0x0030:
                throw r1     // Catch:{ IOException -> 0x0031 }
            L_0x0031:
                r4 = move-exception
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                r0.<init>(r4)
                throw r0
            L_0x0038:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFFontInfo.XSLFFontFacet.init():void");
        }
    }

    private CTTextFont getFont() {
        return this.fontListEntry.getFont();
    }

    public static XSLFFontInfo addFontToSlideShow(XMLSlideShow xMLSlideShow, InputStream inputStream) throws IOException {
        FontHeader fontHeader = new FontHeader();
        InputStream bufferInit = fontHeader.bufferInit(inputStream);
        XSLFFontInfo xSLFFontInfo = new XSLFFontInfo(xMLSlideShow, fontHeader.getFamilyName());
        xSLFFontInfo.addFacet(bufferInit);
        return xSLFFontInfo;
    }

    public static List<XSLFFontInfo> getFonts(XMLSlideShow xMLSlideShow) {
        CTPresentation cTPresentation = xMLSlideShow.getCTPresentation();
        if (cTPresentation.isSetEmbeddedFontLst()) {
            return (List) Stream.of(cTPresentation.getEmbeddedFontLst().getEmbeddedFontArray()).map(new XSLFFontInfo$$ExternalSyntheticLambda0(xMLSlideShow)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    static /* synthetic */ XSLFFontInfo lambda$getFonts$0(XMLSlideShow xMLSlideShow, CTEmbeddedFontListEntry cTEmbeddedFontListEntry) {
        return new XSLFFontInfo(xMLSlideShow, cTEmbeddedFontListEntry);
    }
}
