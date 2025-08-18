package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.util.function.Consumer;
import kotlin.UByte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.openxml4j.exceptions.OpenXML4JRuntimeException;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.model.CharacterPropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFPropertiesDelegate;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontCollection;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public class XSLFTextRun implements TextRun {
    private static final Logger LOG = LogManager.getLogger((Class<?>) XSLFTextRun.class);
    /* access modifiers changed from: private */
    public final XSLFTextParagraph _p;
    private final XmlObject _r;

    protected XSLFTextRun(XmlObject xmlObject, XSLFTextParagraph xSLFTextParagraph) {
        this._r = xmlObject;
        this._p = xSLFTextParagraph;
        if (!(xmlObject instanceof CTRegularTextRun) && !(xmlObject instanceof CTTextLineBreak) && !(xmlObject instanceof CTTextField)) {
            throw new OpenXML4JRuntimeException("unsupported text run of type " + xmlObject.getClass());
        }
    }

    public String getRawText() {
        XmlObject xmlObject = this._r;
        if (xmlObject instanceof CTTextField) {
            return ((CTTextField) xmlObject).getT();
        }
        if (xmlObject instanceof CTTextLineBreak) {
            return "\n";
        }
        return ((CTRegularTextRun) xmlObject).getT();
    }

    public void setText(String str) {
        XmlObject xmlObject = this._r;
        if (xmlObject instanceof CTTextField) {
            ((CTTextField) xmlObject).setT(str);
        } else if (!(xmlObject instanceof CTTextLineBreak)) {
            ((CTRegularTextRun) xmlObject).setT(str);
        }
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._r;
    }

    public void setFontColor(Color color) {
        setFontColor((PaintStyle) DrawPaint.createSolidPaint(color));
    }

    public void setFontColor(PaintStyle paintStyle) {
        if (!(paintStyle instanceof PaintStyle.SolidPaint)) {
            LOG.atWarn().log("Currently only SolidPaint is supported!");
            return;
        }
        Color applyColorTransform = DrawPaint.applyColorTransform(((PaintStyle.SolidPaint) paintStyle).getSolidColor());
        CTTextCharacterProperties rPr = getRPr(true);
        CTSolidColorFillProperties solidFill = rPr.isSetSolidFill() ? rPr.getSolidFill() : rPr.addNewSolidFill();
        XSLFSheet sheet = getParagraph().getParentShape().getSheet();
        new XSLFColor(solidFill, sheet.getTheme(), solidFill.getSchemeClr(), sheet).setColor(applyColorTransform);
    }

    public PaintStyle getFontColor() {
        XSLFTextShape parentShape = getParagraph().getParentShape();
        return (PaintStyle) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda5(parentShape, parentShape.getPlaceholder() != null));
    }

    /* access modifiers changed from: private */
    public static void fetchFontColor(CTTextCharacterProperties cTTextCharacterProperties, Consumer<PaintStyle> consumer, XSLFShape xSLFShape, boolean z) {
        if (cTTextCharacterProperties != null) {
            CTShapeStyle spStyle = xSLFShape.getSpStyle();
            CTSchemeColor schemeClr = (spStyle == null || spStyle.getFontRef() == null) ? null : spStyle.getFontRef().getSchemeClr();
            XSLFPropertiesDelegate.XSLFFillProperties fillDelegate = XSLFPropertiesDelegate.getFillDelegate(cTTextCharacterProperties);
            XSLFSheet sheet = xSLFShape.getSheet();
            PaintStyle selectPaint = xSLFShape.selectPaint(fillDelegate, schemeClr, sheet.getPackagePart(), sheet.getTheme(), z);
            if (selectPaint != null) {
                consumer.accept(selectPaint);
            }
        }
    }

    public void setFontSize(Double d) {
        CTTextCharacterProperties rPr = getRPr(true);
        if (d == null) {
            if (rPr.isSetSz()) {
                rPr.unsetSz();
            }
        } else if (d.doubleValue() >= 1.0d) {
            rPr.setSz((int) (d.doubleValue() * 100.0d));
        } else {
            throw new IllegalArgumentException("Minimum font size is 1pt but was " + d);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = (r0 = r0.getTextBodyPr()).getNormAutofit();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Double getFontSize() {
        /*
            r4 = this;
            org.apache.poi.xslf.usermodel.XSLFTextParagraph r0 = r4.getParagraph()
            org.apache.poi.xslf.usermodel.XSLFTextShape r0 = r0.getParentShape()
            if (r0 == 0) goto L_0x002c
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties r0 = r0.getTextBodyPr()
            if (r0 == 0) goto L_0x002c
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit r0 = r0.getNormAutofit()
            if (r0 == 0) goto L_0x002c
            boolean r1 = r0.isSetFontScale()
            if (r1 == 0) goto L_0x002c
            org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString r0 = r0.xgetFontScale()
            int r0 = org.apache.poi.ooxml.util.POIXMLUnits.parsePercent((org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString) r0)
            double r0 = (double) r0
            r2 = 4681608360884174848(0x40f86a0000000000, double:100000.0)
            double r0 = r0 / r2
            goto L_0x002e
        L_0x002c:
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x002e:
            org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda3 r2 = new org.apache.poi.xslf.usermodel.XSLFTextRun$$ExternalSyntheticLambda3
            r2.<init>()
            java.lang.Object r4 = r4.fetchCharacterProperty(r2)
            java.lang.Double r4 = (java.lang.Double) r4
            if (r4 != 0) goto L_0x003d
            r4 = 0
            goto L_0x0046
        L_0x003d:
            double r2 = r4.doubleValue()
            double r2 = r2 * r0
            java.lang.Double r4 = java.lang.Double.valueOf(r2)
        L_0x0046:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextRun.getFontSize():java.lang.Double");
    }

    static /* synthetic */ void lambda$getFontSize$1(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetSz()) {
            consumer.accept(Double.valueOf(((double) cTTextCharacterProperties.getSz()) * 0.01d));
        }
    }

    public double getCharacterSpacing() {
        Double d = (Double) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda2());
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    static /* synthetic */ void lambda$getCharacterSpacing$2(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetSpc()) {
            consumer.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextCharacterProperties.xgetSpc()))));
        }
    }

    public void setCharacterSpacing(double d) {
        CTTextCharacterProperties rPr = getRPr(true);
        if (d != 0.0d) {
            rPr.setSpc(Integer.valueOf((int) (d * 100.0d)));
        } else if (rPr.isSetSpc()) {
            rPr.unsetSpc();
        }
    }

    public void setFontFamily(String str) {
        new XSLFFontInfo(this, FontGroup.getFontGroupFirst(getRawText()), (AnonymousClass1) null).setTypeface(str);
    }

    public void setFontFamily(String str, FontGroup fontGroup) {
        new XSLFFontInfo(this, fontGroup, (AnonymousClass1) null).setTypeface(str);
    }

    public void setFontInfo(FontInfo fontInfo, FontGroup fontGroup) {
        new XSLFFontInfo(this, fontGroup, (AnonymousClass1) null).copyFrom(fontInfo);
    }

    public String getFontFamily() {
        return new XSLFFontInfo(this, FontGroup.getFontGroupFirst(getRawText()), (AnonymousClass1) null).getTypeface();
    }

    public String getFontFamily(FontGroup fontGroup) {
        return new XSLFFontInfo(this, fontGroup, (AnonymousClass1) null).getTypeface();
    }

    public FontInfo getFontInfo(FontGroup fontGroup) {
        XSLFFontInfo xSLFFontInfo = new XSLFFontInfo(this, fontGroup, (AnonymousClass1) null);
        if (xSLFFontInfo.getTypeface() != null) {
            return xSLFFontInfo;
        }
        return null;
    }

    public byte getPitchAndFamily() {
        XSLFFontInfo xSLFFontInfo = new XSLFFontInfo(this, FontGroup.getFontGroupFirst(getRawText()), (AnonymousClass1) null);
        FontPitch pitch = xSLFFontInfo.getPitch();
        if (pitch == null) {
            pitch = FontPitch.VARIABLE;
        }
        FontFamily family = xSLFFontInfo.getFamily();
        if (family == null) {
            family = FontFamily.FF_SWISS;
        }
        return FontPitch.getNativeId(pitch, family);
    }

    public void setStrikethrough(boolean z) {
        getRPr(true).setStrike(z ? STTextStrikeType.SNG_STRIKE : STTextStrikeType.NO_STRIKE);
    }

    public boolean isStrikethrough() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda9());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isStrikethrough$3(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetStrike()) {
            consumer.accept(Boolean.valueOf(cTTextCharacterProperties.getStrike() != STTextStrikeType.NO_STRIKE));
        }
    }

    public boolean isSuperscript() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda1());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isSuperscript$4(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetBaseline()) {
            consumer.accept(Boolean.valueOf(POIXMLUnits.parsePercent(cTTextCharacterProperties.xgetBaseline()) > 0));
        }
    }

    public void setBaselineOffset(double d) {
        getRPr(true).setBaseline(Integer.valueOf(((int) d) * 1000));
    }

    public void setSuperscript(boolean z) {
        setBaselineOffset(z ? 30.0d : 0.0d);
    }

    public void setSubscript(boolean z) {
        setBaselineOffset(z ? -25.0d : 0.0d);
    }

    public boolean isSubscript() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda8());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isSubscript$5(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetBaseline()) {
            consumer.accept(Boolean.valueOf(POIXMLUnits.parsePercent(cTTextCharacterProperties.xgetBaseline()) < 0));
        }
    }

    public TextRun.TextCap getTextCap() {
        TextRun.TextCap textCap = (TextRun.TextCap) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda4());
        return textCap == null ? TextRun.TextCap.NONE : textCap;
    }

    static /* synthetic */ void lambda$getTextCap$6(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetCap()) {
            consumer.accept(TextRun.TextCap.values()[cTTextCharacterProperties.getCap().intValue() - 1]);
        }
    }

    public void setBold(boolean z) {
        getRPr(true).setB(z);
    }

    public boolean isBold() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda7());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isBold$7(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetB()) {
            consumer.accept(Boolean.valueOf(cTTextCharacterProperties.getB()));
        }
    }

    public void setItalic(boolean z) {
        getRPr(true).setI(z);
    }

    public boolean isItalic() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda0());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isItalic$8(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetI()) {
            consumer.accept(Boolean.valueOf(cTTextCharacterProperties.getI()));
        }
    }

    public void setUnderlined(boolean z) {
        getRPr(true).setU(z ? STTextUnderlineType.SNG : STTextUnderlineType.NONE);
    }

    public boolean isUnderlined() {
        Boolean bool = (Boolean) fetchCharacterProperty(new XSLFTextRun$$ExternalSyntheticLambda6());
        return bool != null && bool.booleanValue();
    }

    static /* synthetic */ void lambda$isUnderlined$9(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
        if (cTTextCharacterProperties.isSetU()) {
            consumer.accept(Boolean.valueOf(cTTextCharacterProperties.getU() != STTextUnderlineType.NONE));
        }
    }

    @Internal
    public CTTextCharacterProperties getRPr(boolean z) {
        XmlObject xmlObject = this._r;
        if (xmlObject instanceof CTTextField) {
            CTTextField cTTextField = (CTTextField) xmlObject;
            if (cTTextField.isSetRPr()) {
                return cTTextField.getRPr();
            }
            if (z) {
                return cTTextField.addNewRPr();
            }
        } else if (xmlObject instanceof CTTextLineBreak) {
            CTTextLineBreak cTTextLineBreak = (CTTextLineBreak) xmlObject;
            if (cTTextLineBreak.isSetRPr()) {
                return cTTextLineBreak.getRPr();
            }
            if (z) {
                return cTTextLineBreak.addNewRPr();
            }
        } else {
            CTRegularTextRun cTRegularTextRun = (CTRegularTextRun) xmlObject;
            if (cTRegularTextRun.isSetRPr()) {
                return cTRegularTextRun.getRPr();
            }
            if (z) {
                return cTRegularTextRun.addNewRPr();
            }
        }
        if (!this._p.getXmlObject().isSetPPr() || !this._p.getXmlObject().getPPr().isSetDefRPr()) {
            return null;
        }
        return this._p.getXmlObject().getPPr().getDefRPr();
    }

    public String toString() {
        return "[" + getClass() + "]" + getRawText();
    }

    public XSLFHyperlink createHyperlink() {
        XSLFHyperlink hyperlink = getHyperlink();
        if (hyperlink != null) {
            return hyperlink;
        }
        return new XSLFHyperlink(getRPr(true).addNewHlinkClick(), this._p.getParentShape().getSheet());
    }

    public XSLFHyperlink getHyperlink() {
        CTHyperlink hlinkClick;
        CTTextCharacterProperties rPr = getRPr(false);
        if (rPr == null || (hlinkClick = rPr.getHlinkClick()) == null) {
            return null;
        }
        return new XSLFHyperlink(hlinkClick, this._p.getParentShape().getSheet());
    }

    /* access modifiers changed from: private */
    public <T> T fetchCharacterProperty(CharacterPropertyFetcher.CharPropFetcher<T> charPropFetcher) {
        return new CharacterPropertyFetcher(this, charPropFetcher).fetchProperty(this._p.getParentShape());
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFTextRun xSLFTextRun) {
        String fontFamily = xSLFTextRun.getFontFamily();
        if (fontFamily != null && !fontFamily.equals(getFontFamily())) {
            setFontFamily(fontFamily);
        }
        PaintStyle fontColor = xSLFTextRun.getFontColor();
        if (fontColor != null && !fontColor.equals(getFontColor())) {
            setFontColor(fontColor);
        }
        Double fontSize = xSLFTextRun.getFontSize();
        if (fontSize == null) {
            if (getFontSize() != null) {
                setFontSize((Double) null);
            }
        } else if (!fontSize.equals(getFontSize())) {
            setFontSize(fontSize);
        }
        boolean isBold = xSLFTextRun.isBold();
        if (isBold != isBold()) {
            setBold(isBold);
        }
        boolean isItalic = xSLFTextRun.isItalic();
        if (isItalic != isItalic()) {
            setItalic(isItalic);
        }
        boolean isUnderlined = xSLFTextRun.isUnderlined();
        if (isUnderlined != isUnderlined()) {
            setUnderlined(isUnderlined);
        }
        boolean isStrikethrough = xSLFTextRun.isStrikethrough();
        if (isStrikethrough != isStrikethrough()) {
            setStrikethrough(isStrikethrough);
        }
        XSLFHyperlink hyperlink = xSLFTextRun.getHyperlink();
        if (hyperlink != null) {
            getHyperlink().copy(hyperlink);
        }
    }

    public TextRun.FieldType getFieldType() {
        XmlObject xmlObject = this._r;
        if (!(xmlObject instanceof CTTextField) || !"slidenum".equals(((CTTextField) xmlObject).getType())) {
            return null;
        }
        return TextRun.FieldType.SLIDE_NUMBER;
    }

    private final class XSLFFontInfo implements FontInfo {
        private final FontGroup fontGroup;

        /* synthetic */ XSLFFontInfo(XSLFTextRun xSLFTextRun, FontGroup fontGroup2, AnonymousClass1 r3) {
            this(fontGroup2);
        }

        private XSLFFontInfo(FontGroup fontGroup2) {
            this.fontGroup = fontGroup2 == null ? FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText()) : fontGroup2;
        }

        /* access modifiers changed from: package-private */
        public void copyFrom(FontInfo fontInfo) {
            CTTextFont xmlObject = getXmlObject(true);
            if (xmlObject != null) {
                setTypeface(fontInfo.getTypeface());
                setCharset(fontInfo.getCharset());
                FontPitch pitch = fontInfo.getPitch();
                FontFamily family = fontInfo.getFamily();
                if (pitch != null || family != null) {
                    setPitch(pitch);
                    setFamily(family);
                } else if (xmlObject.isSetPitchFamily()) {
                    xmlObject.unsetPitchFamily();
                }
            }
        }

        public String getTypeface() {
            CTTextFont xmlObject = getXmlObject(false);
            if (xmlObject != null) {
                return xmlObject.getTypeface();
            }
            return null;
        }

        public void setTypeface(String str) {
            if (str != null) {
                CTTextFont xmlObject = getXmlObject(true);
                if (xmlObject != null) {
                    xmlObject.setTypeface(str);
                    return;
                }
                return;
            }
            CTTextCharacterProperties rPr = XSLFTextRun.this.getRPr(false);
            if (rPr != null) {
                int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup[FontGroup.getFontGroupFirst(XSLFTextRun.this.getRawText()).ordinal()];
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (rPr.isSetLatin()) {
                                rPr.unsetLatin();
                            }
                        } else if (rPr.isSetSym()) {
                            rPr.unsetSym();
                        }
                    } else if (rPr.isSetCs()) {
                        rPr.unsetCs();
                    }
                } else if (rPr.isSetEa()) {
                    rPr.unsetEa();
                }
            }
        }

        public FontCharset getCharset() {
            CTTextFont xmlObject = getXmlObject(false);
            if (xmlObject == null || !xmlObject.isSetCharset()) {
                return null;
            }
            return FontCharset.valueOf((int) xmlObject.getCharset() & UByte.MAX_VALUE);
        }

        public void setCharset(FontCharset fontCharset) {
            CTTextFont xmlObject = getXmlObject(true);
            if (xmlObject != null) {
                if (fontCharset != null) {
                    xmlObject.setCharset((byte) fontCharset.getNativeId());
                } else if (xmlObject.isSetCharset()) {
                    xmlObject.unsetCharset();
                }
            }
        }

        public FontFamily getFamily() {
            CTTextFont xmlObject = getXmlObject(false);
            if (xmlObject == null || !xmlObject.isSetPitchFamily()) {
                return null;
            }
            return FontFamily.valueOfPitchFamily(xmlObject.getPitchFamily());
        }

        public void setFamily(FontFamily fontFamily) {
            CTTextFont xmlObject = getXmlObject(true);
            if (xmlObject == null) {
                return;
            }
            if (fontFamily != null || xmlObject.isSetPitchFamily()) {
                FontPitch valueOfPitchFamily = xmlObject.isSetPitchFamily() ? FontPitch.valueOfPitchFamily(xmlObject.getPitchFamily()) : FontPitch.VARIABLE;
                if (fontFamily == null) {
                    fontFamily = FontFamily.FF_SWISS;
                }
                xmlObject.setPitchFamily(FontPitch.getNativeId(valueOfPitchFamily, fontFamily));
            }
        }

        public FontPitch getPitch() {
            CTTextFont xmlObject = getXmlObject(false);
            if (xmlObject == null || !xmlObject.isSetPitchFamily()) {
                return null;
            }
            return FontPitch.valueOfPitchFamily(xmlObject.getPitchFamily());
        }

        public void setPitch(FontPitch fontPitch) {
            CTTextFont xmlObject = getXmlObject(true);
            if (xmlObject == null) {
                return;
            }
            if (fontPitch != null || xmlObject.isSetPitchFamily()) {
                FontFamily valueOfPitchFamily = xmlObject.isSetPitchFamily() ? FontFamily.valueOfPitchFamily(xmlObject.getPitchFamily()) : FontFamily.FF_SWISS;
                if (fontPitch == null) {
                    fontPitch = FontPitch.VARIABLE;
                }
                xmlObject.setPitchFamily(FontPitch.getNativeId(fontPitch, valueOfPitchFamily));
            }
        }

        private CTTextFont getXmlObject(boolean z) {
            if (z) {
                return getCTTextFont(XSLFTextRun.this.getRPr(true), true);
            }
            return (CTTextFont) XSLFTextRun.this.fetchCharacterProperty(new XSLFTextRun$XSLFFontInfo$$ExternalSyntheticLambda0(this));
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$getXmlObject$0$org-apache-poi-xslf-usermodel-XSLFTextRun$XSLFFontInfo  reason: not valid java name */
        public /* synthetic */ void m2309lambda$getXmlObject$0$orgapachepoixslfusermodelXSLFTextRun$XSLFFontInfo(CTTextCharacterProperties cTTextCharacterProperties, Consumer consumer) {
            CTTextFont cTTextFont = getCTTextFont(cTTextCharacterProperties, false);
            if (cTTextFont != null) {
                consumer.accept(cTTextFont);
            }
        }

        private CTTextFont getCTTextFont(CTTextCharacterProperties cTTextCharacterProperties, boolean z) {
            CTTextFont cTTextFont;
            CTTextFont latin;
            if (cTTextCharacterProperties == null) {
                return null;
            }
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup[this.fontGroup.ordinal()];
            if (i == 2) {
                cTTextFont = cTTextCharacterProperties.getEa();
                if (cTTextFont == null && z) {
                    cTTextFont = cTTextCharacterProperties.addNewEa();
                }
            } else if (i == 3) {
                cTTextFont = cTTextCharacterProperties.getCs();
                if (cTTextFont == null && z) {
                    cTTextFont = cTTextCharacterProperties.addNewCs();
                }
            } else if (i != 4) {
                cTTextFont = cTTextCharacterProperties.getLatin();
                if (cTTextFont == null && z) {
                    cTTextFont = cTTextCharacterProperties.addNewLatin();
                }
            } else {
                cTTextFont = cTTextCharacterProperties.getSym();
                if (cTTextFont == null && z) {
                    cTTextFont = cTTextCharacterProperties.addNewSym();
                }
            }
            if (cTTextFont == null) {
                return null;
            }
            String typeface = cTTextFont.getTypeface();
            if (typeface == null) {
                typeface = "";
            }
            if (typeface.startsWith("+mj-") || typeface.startsWith("+mn-")) {
                CTFontScheme fontScheme = XSLFTextRun.this._p.getParentShape().getSheet().getTheme().getXmlObject().getThemeElements().getFontScheme();
                CTFontCollection majorFont = typeface.startsWith("+mj-") ? fontScheme.getMajorFont() : fontScheme.getMinorFont();
                String substring = typeface.substring(4);
                if ("ea".equals(substring)) {
                    latin = majorFont.getEa();
                } else if ("cs".equals(substring)) {
                    latin = majorFont.getCs();
                } else {
                    latin = majorFont.getLatin();
                }
                cTTextFont = latin;
                if (cTTextFont == null || cTTextFont.getTypeface() == null || "".equals(cTTextFont.getTypeface())) {
                    return null;
                }
            }
            return cTTextFont;
        }
    }

    /* renamed from: org.apache.poi.xslf.usermodel.XSLFTextRun$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.common.usermodel.fonts.FontGroup[] r0 = org.apache.poi.common.usermodel.fonts.FontGroup.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup = r0
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.LATIN     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.EAST_ASIAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.COMPLEX_SCRIPT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$common$usermodel$fonts$FontGroup     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.common.usermodel.fonts.FontGroup r1 = org.apache.poi.common.usermodel.fonts.FontGroup.SYMBOL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextRun.AnonymousClass1.<clinit>():void");
        }
    }

    public XSLFTextParagraph getParagraph() {
        return this._p;
    }
}
