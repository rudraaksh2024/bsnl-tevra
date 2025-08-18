package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.AutoNumberingScheme;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.TabStop;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.util.Internal;
import org.apache.poi.util.Units;
import org.apache.poi.xslf.model.ParagraphPropertyFetcher;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextTabStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAlignType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAutonumberScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontAlignType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder;

public class XSLFTextParagraph implements TextParagraph<XSLFShape, XSLFTextParagraph, XSLFTextRun> {
    private final CTTextParagraph _p;
    private final List<XSLFTextRun> _runs = new ArrayList();
    private final XSLFTextShape _shape;

    @FunctionalInterface
    private interface Procedure {
        void accept();
    }

    XSLFTextParagraph(CTTextParagraph cTTextParagraph, XSLFTextShape xSLFTextShape) {
        this._p = cTTextParagraph;
        this._shape = xSLFTextShape;
        XmlCursor newCursor = cTTextParagraph.newCursor();
        try {
            if (newCursor.toFirstChild()) {
                do {
                    XmlObject object = newCursor.getObject();
                    if (object instanceof CTTextLineBreak) {
                        this._runs.add(new XSLFLineBreak((CTTextLineBreak) object, this));
                    } else if ((object instanceof CTRegularTextRun) || (object instanceof CTTextField)) {
                        this._runs.add(new XSLFTextRun(object, this));
                    }
                } while (newCursor.toNextSibling());
            }
        } finally {
            newCursor.dispose();
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (XSLFTextRun rawText : this._runs) {
            sb.append(rawText.getRawText());
        }
        return sb.toString();
    }

    @Internal
    public CTTextParagraph getXmlObject() {
        return this._p;
    }

    public XSLFTextShape getParentShape() {
        return this._shape;
    }

    public List<XSLFTextRun> getTextRuns() {
        return Collections.unmodifiableList(this._runs);
    }

    public Iterator<XSLFTextRun> iterator() {
        return getTextRuns().iterator();
    }

    public XSLFTextRun addNewTextRun() {
        CTRegularTextRun addNewR = this._p.addNewR();
        addNewR.addNewRPr().setLang("en-US");
        XSLFTextRun newTextRun = newTextRun((XmlObject) addNewR);
        this._runs.add(newTextRun);
        return newTextRun;
    }

    public boolean removeTextRun(XSLFTextRun xSLFTextRun) {
        if (this._runs.remove(xSLFTextRun)) {
            XmlObject xmlObject = xSLFTextRun.getXmlObject();
            if (xmlObject instanceof CTRegularTextRun) {
                for (int i = 0; i < getXmlObject().sizeOfRArray(); i++) {
                    if (getXmlObject().getRArray(i).equals(xmlObject)) {
                        getXmlObject().removeR(i);
                        return true;
                    }
                }
            } else if (xmlObject instanceof CTTextField) {
                for (int i2 = 0; i2 < getXmlObject().sizeOfFldArray(); i2++) {
                    if (getXmlObject().getFldArray(i2).equals(xmlObject)) {
                        getXmlObject().removeFld(i2);
                        return true;
                    }
                }
            } else if (xmlObject instanceof CTTextLineBreak) {
                for (int i3 = 0; i3 < getXmlObject().sizeOfBrArray(); i3++) {
                    if (getXmlObject().getBrArray(i3).equals(xmlObject)) {
                        getXmlObject().removeBr(i3);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public XSLFTextRun addLineBreak() {
        XSLFLineBreak xSLFLineBreak = new XSLFLineBreak(this._p.addNewBr(), this);
        CTTextCharacterProperties rPr = xSLFLineBreak.getRPr(true);
        if (!this._runs.isEmpty()) {
            List<XSLFTextRun> list = this._runs;
            rPr.set(list.get(list.size() - 1).getRPr(true));
            if (rPr.isSetHlinkClick()) {
                rPr.unsetHlinkClick();
            }
            if (rPr.isSetHlinkMouseOver()) {
                rPr.unsetHlinkMouseOver();
            }
        }
        this._runs.add(xSLFLineBreak);
        return xSLFLineBreak;
    }

    public TextParagraph.TextAlign getTextAlign() {
        return (TextParagraph.TextAlign) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda10());
    }

    static /* synthetic */ void lambda$getTextAlign$0(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetAlgn()) {
            consumer.accept(TextParagraph.TextAlign.values()[cTTextParagraphProperties.getAlgn().intValue() - 1]);
        }
    }

    public void setTextAlign(TextParagraph.TextAlign textAlign) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (textAlign != null) {
            pPr.setAlgn(STTextAlignType.Enum.forInt(textAlign.ordinal() + 1));
        } else if (pPr.isSetAlgn()) {
            pPr.unsetAlgn();
        }
    }

    public TextParagraph.FontAlign getFontAlign() {
        return (TextParagraph.FontAlign) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda2());
    }

    static /* synthetic */ void lambda$getFontAlign$1(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetFontAlgn()) {
            consumer.accept(TextParagraph.FontAlign.values()[cTTextParagraphProperties.getFontAlgn().intValue() - 1]);
        }
    }

    public void setFontAlign(TextParagraph.FontAlign fontAlign) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (fontAlign != null) {
            pPr.setFontAlgn(STTextFontAlignType.Enum.forInt(fontAlign.ordinal() + 1));
        } else if (pPr.isSetFontAlgn()) {
            pPr.unsetFontAlgn();
        }
    }

    public String getBulletFont() {
        return (String) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda19());
    }

    static /* synthetic */ void lambda$getBulletFont$2(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetBuFont()) {
            consumer.accept(cTTextParagraphProperties.getBuFont().getTypeface());
        }
    }

    public void setBulletFont(String str) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetBuFont() ? pPr.getBuFont() : pPr.addNewBuFont()).setTypeface(str);
    }

    public String getBulletCharacter() {
        return (String) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda33());
    }

    static /* synthetic */ void lambda$getBulletCharacter$3(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetBuChar()) {
            consumer.accept(cTTextParagraphProperties.getBuChar().getChar());
        }
    }

    public void setBulletCharacter(String str) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetBuChar() ? pPr.getBuChar() : pPr.addNewBuChar()).setChar(str);
    }

    public PaintStyle getBulletFontColor() {
        Color color = (Color) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda24(this));
        if (color == null) {
            return null;
        }
        return DrawPaint.createSolidPaint(color);
    }

    /* access modifiers changed from: private */
    public void fetchBulletFontColor(CTTextParagraphProperties cTTextParagraphProperties, Consumer<Color> consumer) {
        XSLFSheet sheet = getParentShape().getSheet();
        XSLFTheme theme = sheet.getTheme();
        if (cTTextParagraphProperties.isSetBuClr()) {
            consumer.accept(new XSLFColor(cTTextParagraphProperties.getBuClr(), theme, (CTSchemeColor) null, sheet).getColor());
        }
    }

    public void setBulletFontColor(Color color) {
        setBulletFontColor((PaintStyle) DrawPaint.createSolidPaint(color));
    }

    public void setBulletFontColor(PaintStyle paintStyle) {
        if (paintStyle instanceof PaintStyle.SolidPaint) {
            Color applyColorTransform = DrawPaint.applyColorTransform(((PaintStyle.SolidPaint) paintStyle).getSolidColor());
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            CTColor buClr = pPr.isSetBuClr() ? pPr.getBuClr() : pPr.addNewBuClr();
            (buClr.isSetSrgbClr() ? buClr.getSrgbClr() : buClr.addNewSrgbClr()).setVal(new byte[]{(byte) applyColorTransform.getRed(), (byte) applyColorTransform.getGreen(), (byte) applyColorTransform.getBlue()});
            return;
        }
        throw new IllegalArgumentException("Currently XSLF only supports SolidPaint");
    }

    public Double getBulletFontSize() {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda28());
    }

    /* access modifiers changed from: private */
    public static void fetchBulletFontSize(CTTextParagraphProperties cTTextParagraphProperties, Consumer<Double> consumer) {
        if (cTTextParagraphProperties.isSetBuSzPct()) {
            consumer.accept(Double.valueOf(((double) POIXMLUnits.parsePercent(cTTextParagraphProperties.getBuSzPct().xgetVal())) * 0.001d));
        }
        if (cTTextParagraphProperties.isSetBuSzPts()) {
            consumer.accept(Double.valueOf(((double) (-cTTextParagraphProperties.getBuSzPts().getVal())) * 0.01d));
        }
    }

    public void setBulletFontSize(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        if (d >= 0.0d) {
            (pPr.isSetBuSzPct() ? pPr.getBuSzPct() : pPr.addNewBuSzPct()).setVal(Integer.toString((int) (d * 1000.0d)));
            if (pPr.isSetBuSzPts()) {
                pPr.unsetBuSzPts();
                return;
            }
            return;
        }
        (pPr.isSetBuSzPts() ? pPr.getBuSzPts() : pPr.addNewBuSzPts()).setVal((int) ((-d) * 100.0d));
        if (pPr.isSetBuSzPct()) {
            pPr.unsetBuSzPct();
        }
    }

    public AutoNumberingScheme getAutoNumberingScheme() {
        return (AutoNumberingScheme) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda21());
    }

    /* access modifiers changed from: private */
    public static void fetchAutoNumberingScheme(CTTextParagraphProperties cTTextParagraphProperties, Consumer<AutoNumberingScheme> consumer) {
        AutoNumberingScheme forOoxmlID;
        if (cTTextParagraphProperties.isSetBuAutoNum() && (forOoxmlID = AutoNumberingScheme.forOoxmlID(cTTextParagraphProperties.getBuAutoNum().getType().intValue())) != null) {
            consumer.accept(forOoxmlID);
        }
    }

    public Integer getAutoNumberingStartAt() {
        return (Integer) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda25());
    }

    static /* synthetic */ void lambda$getAutoNumberingStartAt$4(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetBuAutoNum() && cTTextParagraphProperties.getBuAutoNum().isSetStartAt()) {
            consumer.accept(Integer.valueOf(cTTextParagraphProperties.getBuAutoNum().getStartAt()));
        }
    }

    public void setIndent(Double d) {
        if (d != null || this._p.isSetPPr()) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            if (d != null) {
                pPr.setIndent(Units.toEMU(d.doubleValue()));
            } else if (pPr.isSetIndent()) {
                pPr.unsetIndent();
            }
        }
    }

    public Double getIndent() {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda12());
    }

    static /* synthetic */ void lambda$getIndent$5(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetIndent()) {
            consumer.accept(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getIndent())));
        }
    }

    public void setLeftMargin(Double d) {
        if (d != null || this._p.isSetPPr()) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            if (d != null) {
                pPr.setMarL(Units.toEMU(d.doubleValue()));
            } else if (pPr.isSetMarL()) {
                pPr.unsetMarL();
            }
        }
    }

    public Double getLeftMargin() {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda11());
    }

    static /* synthetic */ void lambda$getLeftMargin$6(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetMarL()) {
            consumer.accept(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getMarL())));
        }
    }

    public void setRightMargin(Double d) {
        if (d != null || this._p.isSetPPr()) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            if (d != null) {
                pPr.setMarR(Units.toEMU(d.doubleValue()));
            } else if (pPr.isSetMarR()) {
                pPr.unsetMarR();
            }
        }
    }

    public Double getRightMargin() {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda27());
    }

    static /* synthetic */ void lambda$getRightMargin$7(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetMarR()) {
            consumer.accept(Double.valueOf(Units.toPoints((long) cTTextParagraphProperties.getMarR())));
        }
    }

    public Double getDefaultTabSize() {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda9());
    }

    static /* synthetic */ void lambda$getDefaultTabSize$8(CTTextParagraphProperties cTTextParagraphProperties, Consumer consumer) {
        if (cTTextParagraphProperties.isSetDefTabSz()) {
            consumer.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(cTTextParagraphProperties.xgetDefTabSz()))));
        }
    }

    public double getTabStop(int i) {
        Double d = (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda15(i));
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    /* access modifiers changed from: private */
    public static void fetchTabStop(int i, CTTextParagraphProperties cTTextParagraphProperties, Consumer<Double> consumer) {
        if (cTTextParagraphProperties.isSetTabLst()) {
            CTTextTabStopList tabLst = cTTextParagraphProperties.getTabLst();
            if (i < tabLst.sizeOfTabArray()) {
                consumer.accept(Double.valueOf(Units.toPoints(POIXMLUnits.parseLength(tabLst.getTabArray(i).xgetPos()))));
            }
        }
    }

    public void addTabStop(double d) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
        (pPr.isSetTabLst() ? pPr.getTabLst() : pPr.addNewTabLst()).addNewTab().setPos(Integer.valueOf(Units.toEMU(d)));
    }

    public void setLineSpacing(Double d) {
        setSpacing(d, new XSLFTextParagraph$$ExternalSyntheticLambda35(), new XSLFTextParagraph$$ExternalSyntheticLambda36(), new XSLFTextParagraph$$ExternalSyntheticLambda1());
    }

    public Double getLineSpacing() {
        CTTextNormalAutofit normAutofit;
        Double spacing = getSpacing(new XSLFTextParagraph$$ExternalSyntheticLambda14());
        if (spacing == null || spacing.doubleValue() <= 0.0d || (normAutofit = getParentShape().getTextBodyPr().getNormAutofit()) == null) {
            return spacing;
        }
        return Double.valueOf(spacing.doubleValue() * (1.0d - (((double) POIXMLUnits.parsePercent(normAutofit.xgetLnSpcReduction())) / 100000.0d)));
    }

    public void setSpaceBefore(Double d) {
        setSpacing(d, new XSLFTextParagraph$$ExternalSyntheticLambda5(), new XSLFTextParagraph$$ExternalSyntheticLambda6(), new XSLFTextParagraph$$ExternalSyntheticLambda7());
    }

    public Double getSpaceBefore() {
        return getSpacing(new XSLFTextParagraph$$ExternalSyntheticLambda29());
    }

    public void setSpaceAfter(Double d) {
        setSpacing(d, new XSLFTextParagraph$$ExternalSyntheticLambda16(), new XSLFTextParagraph$$ExternalSyntheticLambda17(), new XSLFTextParagraph$$ExternalSyntheticLambda18());
    }

    public Double getSpaceAfter() {
        return getSpacing(new XSLFTextParagraph$$ExternalSyntheticLambda30());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setSpacing(java.lang.Double r3, java.util.function.Function<org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties, java.util.function.Supplier<org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing>> r4, java.util.function.Function<org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties, java.util.function.Supplier<org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing>> r5, java.util.function.Function<org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties, org.apache.poi.xslf.usermodel.XSLFTextParagraph.Procedure> r6) {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x0012
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph r0 = r2._p
            boolean r0 = r0.isSetPPr()
            if (r0 == 0) goto L_0x000b
            goto L_0x0012
        L_0x000b:
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph r2 = r2._p
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties r2 = r2.addNewPPr()
            goto L_0x0018
        L_0x0012:
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph r2 = r2._p
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties r2 = r2.getPPr()
        L_0x0018:
            if (r2 != 0) goto L_0x001b
            return
        L_0x001b:
            java.lang.Object r4 = r4.apply(r2)
            java.util.function.Supplier r4 = (java.util.function.Supplier) r4
            java.lang.Object r4 = r4.get()
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing r4 = (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) r4
            if (r3 != 0) goto L_0x0035
            if (r4 == 0) goto L_0x0034
            java.lang.Object r2 = r6.apply(r2)
            org.apache.poi.xslf.usermodel.XSLFTextParagraph$Procedure r2 = (org.apache.poi.xslf.usermodel.XSLFTextParagraph.Procedure) r2
            r2.accept()
        L_0x0034:
            return
        L_0x0035:
            if (r4 != 0) goto L_0x0044
            java.lang.Object r2 = r5.apply(r2)
            java.util.function.Supplier r2 = (java.util.function.Supplier) r2
            java.lang.Object r2 = r2.get()
            r4 = r2
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing r4 = (org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing) r4
        L_0x0044:
            double r5 = r3.doubleValue()
            r0 = 0
            int r2 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0079
            boolean r2 = r4.isSetSpcPts()
            if (r2 == 0) goto L_0x0057
            r4.unsetSpcPts()
        L_0x0057:
            boolean r2 = r4.isSetSpcPct()
            if (r2 == 0) goto L_0x0062
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent r2 = r4.getSpcPct()
            goto L_0x0066
        L_0x0062:
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent r2 = r4.addNewSpcPct()
        L_0x0066:
            double r3 = r3.doubleValue()
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r3 = r3 * r5
            int r3 = (int) r3
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r2.setVal(r3)
            goto L_0x009d
        L_0x0079:
            boolean r2 = r4.isSetSpcPct()
            if (r2 == 0) goto L_0x0082
            r4.unsetSpcPct()
        L_0x0082:
            boolean r2 = r4.isSetSpcPts()
            if (r2 == 0) goto L_0x008d
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint r2 = r4.getSpcPts()
            goto L_0x0091
        L_0x008d:
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint r2 = r4.addNewSpcPts()
        L_0x0091:
            double r3 = r3.doubleValue()
            double r3 = -r3
            r5 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r3 = r3 * r5
            int r3 = (int) r3
            r2.setVal(r3)
        L_0x009d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextParagraph.setSpacing(java.lang.Double, java.util.function.Function, java.util.function.Function, java.util.function.Function):void");
    }

    private Double getSpacing(Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> function) {
        return (Double) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda13(function));
    }

    /* access modifiers changed from: private */
    public static void fetchSpacing(Function<CTTextParagraphProperties, Supplier<CTTextSpacing>> function, CTTextParagraphProperties cTTextParagraphProperties, Consumer<Double> consumer) {
        CTTextSpacing cTTextSpacing = (CTTextSpacing) function.apply(cTTextParagraphProperties).get();
        if (cTTextSpacing == null) {
            return;
        }
        if (cTTextSpacing.isSetSpcPct()) {
            consumer.accept(Double.valueOf(((double) POIXMLUnits.parsePercent(cTTextSpacing.getSpcPct().xgetVal())) * 0.001d));
        } else if (cTTextSpacing.isSetSpcPts()) {
            consumer.accept(Double.valueOf(((double) (-cTTextSpacing.getSpcPts().getVal())) * 0.01d));
        }
    }

    public void setIndentLevel(int i) {
        boolean isSetPPr = this._p.isSetPPr();
        CTTextParagraph cTTextParagraph = this._p;
        (isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr()).setLvl(i);
    }

    public int getIndentLevel() {
        CTTextParagraphProperties pPr = this._p.getPPr();
        if (pPr == null || !pPr.isSetLvl()) {
            return 0;
        }
        return pPr.getLvl();
    }

    public boolean isBullet() {
        Boolean bool = (Boolean) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda34());
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public static void fetchIsBullet(CTTextParagraphProperties cTTextParagraphProperties, Consumer<Boolean> consumer) {
        if (cTTextParagraphProperties.isSetBuNone()) {
            consumer.accept(false);
        } else if (cTTextParagraphProperties.isSetBuFont() || cTTextParagraphProperties.isSetBuChar()) {
            consumer.accept(true);
        }
    }

    public void setBullet(boolean z) {
        if (isBullet() != z) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            if (z) {
                pPr.addNewBuFont().setTypeface(HSSFFont.FONT_ARIAL);
                pPr.addNewBuChar().setChar("•");
                return;
            }
            if (pPr.isSetBuFont()) {
                pPr.unsetBuFont();
            }
            if (pPr.isSetBuChar()) {
                pPr.unsetBuChar();
            }
            if (pPr.isSetBuAutoNum()) {
                pPr.unsetBuAutoNum();
            }
            if (pPr.isSetBuBlip()) {
                pPr.unsetBuBlip();
            }
            if (pPr.isSetBuClr()) {
                pPr.unsetBuClr();
            }
            if (pPr.isSetBuClrTx()) {
                pPr.unsetBuClrTx();
            }
            if (pPr.isSetBuFont()) {
                pPr.unsetBuFont();
            }
            if (pPr.isSetBuFontTx()) {
                pPr.unsetBuFontTx();
            }
            if (pPr.isSetBuSzPct()) {
                pPr.unsetBuSzPct();
            }
            if (pPr.isSetBuSzPts()) {
                pPr.unsetBuSzPts();
            }
            if (pPr.isSetBuSzTx()) {
                pPr.unsetBuSzTx();
            }
            pPr.addNewBuNone();
        }
    }

    public void setBulletAutoNumber(AutoNumberingScheme autoNumberingScheme, int i) {
        if (i >= 1) {
            boolean isSetPPr = this._p.isSetPPr();
            CTTextParagraph cTTextParagraph = this._p;
            CTTextParagraphProperties pPr = isSetPPr ? cTTextParagraph.getPPr() : cTTextParagraph.addNewPPr();
            CTTextAutonumberBullet buAutoNum = pPr.isSetBuAutoNum() ? pPr.getBuAutoNum() : pPr.addNewBuAutoNum();
            buAutoNum.setType(STTextAutonumberScheme.Enum.forInt(autoNumberingScheme.ooxmlId));
            buAutoNum.setStartAt(i);
            return;
        }
        throw new IllegalArgumentException("Start Number must be greater or equal that 1");
    }

    public String toString() {
        return "[" + getClass() + "]" + getText();
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    @org.apache.poi.util.Internal
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties getDefaultMasterStyle() {
        /*
            r7 = this;
            java.lang.String r0 = "http://schemas.openxmlformats.org/presentationml/2006/main"
            org.apache.poi.xslf.usermodel.XSLFTextShape r1 = r7._shape
            org.apache.poi.xslf.usermodel.XSLFPlaceholderDetails r1 = r1.getPlaceholderDetails()
            r2 = 0
            org.openxmlformats.schemas.presentationml.x2006.main.CTPlaceholder r1 = r1.getCTPlaceholder(r2)
            r2 = -1
            if (r1 != 0) goto L_0x0012
            r1 = r2
            goto L_0x001a
        L_0x0012:
            org.openxmlformats.schemas.presentationml.x2006.main.STPlaceholderType$Enum r1 = r1.getType()
            int r1 = r1.intValue()
        L_0x001a:
            if (r1 == r2) goto L_0x0031
            r2 = 1
            if (r1 == r2) goto L_0x002e
            r2 = 3
            if (r1 == r2) goto L_0x002e
            r2 = 5
            if (r1 == r2) goto L_0x0031
            r2 = 6
            if (r1 == r2) goto L_0x0031
            r2 = 7
            if (r1 == r2) goto L_0x0031
            java.lang.String r1 = "bodyStyle"
            goto L_0x0033
        L_0x002e:
            java.lang.String r1 = "titleStyle"
            goto L_0x0033
        L_0x0031:
            java.lang.String r1 = "otherStyle"
        L_0x0033:
            int r2 = r7.getIndentLevel()
            org.apache.poi.xslf.usermodel.XSLFTextShape r7 = r7._shape
            org.apache.poi.xslf.usermodel.XSLFSheet r7 = r7.getSheet()
        L_0x003d:
            if (r7 == 0) goto L_0x00ad
            org.apache.xmlbeans.XmlObject r3 = r7.getXmlObject()
            org.apache.xmlbeans.XmlCursor r3 = r3.newCursor()
            r3.push()     // Catch:{ all -> 0x00a8 }
            java.lang.String r4 = "txStyles"
            boolean r4 = r3.toChild((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x0058
            boolean r4 = r3.toChild((java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x00a8 }
            if (r4 != 0) goto L_0x0066
        L_0x0058:
            boolean r4 = r3.pop()     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x009e
            java.lang.String r4 = "notesStyle"
            boolean r4 = r3.toChild((java.lang.String) r0, (java.lang.String) r4)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x009e
        L_0x0066:
            if (r2 < 0) goto L_0x009e
            r3.push()     // Catch:{ all -> 0x00a8 }
            java.lang.String r4 = "http://schemas.openxmlformats.org/drawingml/2006/main"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            r5.<init>()     // Catch:{ all -> 0x00a8 }
            java.lang.String r6 = "lvl"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00a8 }
            int r6 = r2 + 1
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00a8 }
            java.lang.String r6 = "pPr"
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x00a8 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x00a8 }
            boolean r4 = r3.toChild((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x0098
            org.apache.xmlbeans.XmlObject r7 = r3.getObject()     // Catch:{ all -> 0x00a8 }
            org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties r7 = (org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties) r7     // Catch:{ all -> 0x00a8 }
            r3.dispose()
            return r7
        L_0x0098:
            r3.pop()     // Catch:{ all -> 0x00a8 }
            int r2 = r2 + -1
            goto L_0x0066
        L_0x009e:
            r3.dispose()
            org.apache.poi.sl.usermodel.MasterSheet r7 = r7.getMasterSheet()
            org.apache.poi.xslf.usermodel.XSLFSheet r7 = (org.apache.poi.xslf.usermodel.XSLFSheet) r7
            goto L_0x003d
        L_0x00a8:
            r7 = move-exception
            r3.dispose()
            throw r7
        L_0x00ad:
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.xslf.usermodel.XSLFTextParagraph.getDefaultMasterStyle():org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties");
    }

    private <T> T fetchParagraphProperty(ParagraphPropertyFetcher.ParaPropFetcher<T> paraPropFetcher) {
        return new ParagraphPropertyFetcher(this, paraPropFetcher).fetchProperty(getParentShape());
    }

    /* access modifiers changed from: package-private */
    public void copy(XSLFTextParagraph xSLFTextParagraph) {
        XSLFTextRun xSLFTextRun;
        if (xSLFTextParagraph != this) {
            CTTextParagraph xmlObject = getXmlObject();
            CTTextParagraph xmlObject2 = xSLFTextParagraph.getXmlObject();
            if (xmlObject.isSetPPr()) {
                xmlObject.unsetPPr();
            }
            if (xmlObject.isSetEndParaRPr()) {
                xmlObject.unsetEndParaRPr();
            }
            this._runs.clear();
            for (int sizeOfBrArray = xmlObject.sizeOfBrArray(); sizeOfBrArray > 0; sizeOfBrArray--) {
                xmlObject.removeBr(sizeOfBrArray - 1);
            }
            for (int sizeOfRArray = xmlObject.sizeOfRArray(); sizeOfRArray > 0; sizeOfRArray--) {
                xmlObject.removeR(sizeOfRArray - 1);
            }
            for (int sizeOfFldArray = xmlObject.sizeOfFldArray(); sizeOfFldArray > 0; sizeOfFldArray--) {
                xmlObject.removeFld(sizeOfFldArray - 1);
            }
            XmlCursor newCursor = xmlObject.newCursor();
            try {
                newCursor.toEndToken();
                newCursor = xmlObject2.newCursor();
                newCursor.copyXmlContents(newCursor);
                newCursor.dispose();
                for (XSLFTextRun next : xSLFTextParagraph.getTextRuns()) {
                    XmlObject xmlObject3 = next.getXmlObject();
                    if (xmlObject3 instanceof CTTextLineBreak) {
                        xSLFTextRun = newTextRun((CTTextLineBreak) xmlObject3);
                    } else {
                        xSLFTextRun = newTextRun(xmlObject3);
                    }
                    xSLFTextRun.copy(next);
                    this._runs.add(xSLFTextRun);
                }
                TextParagraph.TextAlign textAlign = xSLFTextParagraph.getTextAlign();
                if (textAlign != getTextAlign()) {
                    setTextAlign(textAlign);
                }
                boolean isBullet = xSLFTextParagraph.isBullet();
                if (isBullet != isBullet()) {
                    setBullet(isBullet);
                    if (isBullet) {
                        String bulletFont = xSLFTextParagraph.getBulletFont();
                        if (bulletFont != null && !bulletFont.equals(getBulletFont())) {
                            setBulletFont(bulletFont);
                        }
                        String bulletCharacter = xSLFTextParagraph.getBulletCharacter();
                        if (bulletCharacter != null && !bulletCharacter.equals(getBulletCharacter())) {
                            setBulletCharacter(bulletCharacter);
                        }
                        PaintStyle bulletFontColor = xSLFTextParagraph.getBulletFontColor();
                        if (bulletFontColor != null && !bulletFontColor.equals(getBulletFontColor())) {
                            setBulletFontColor(bulletFontColor);
                        }
                        Double bulletFontSize = xSLFTextParagraph.getBulletFontSize();
                        if (doubleNotEquals(bulletFontSize, getBulletFontSize())) {
                            setBulletFontSize(bulletFontSize.doubleValue());
                        }
                    }
                }
                Double leftMargin = xSLFTextParagraph.getLeftMargin();
                if (doubleNotEquals(leftMargin, getLeftMargin())) {
                    setLeftMargin(leftMargin);
                }
                Double indent = xSLFTextParagraph.getIndent();
                if (doubleNotEquals(indent, getIndent())) {
                    setIndent(indent);
                }
                Double spaceAfter = xSLFTextParagraph.getSpaceAfter();
                if (doubleNotEquals(spaceAfter, getSpaceAfter())) {
                    setSpaceAfter(spaceAfter);
                }
                Double spaceBefore = xSLFTextParagraph.getSpaceBefore();
                if (doubleNotEquals(spaceBefore, getSpaceBefore())) {
                    setSpaceBefore(spaceBefore);
                }
                Double lineSpacing = xSLFTextParagraph.getLineSpacing();
                if (doubleNotEquals(lineSpacing, getLineSpacing())) {
                    setLineSpacing(lineSpacing);
                }
            } catch (Throwable th) {
                throw th;
            } finally {
                newCursor.dispose();
            }
        }
    }

    private static boolean doubleNotEquals(Double d, Double d2) {
        return !Objects.equals(d, d2);
    }

    public Double getDefaultFontSize() {
        CTTextParagraphProperties defaultMasterStyle;
        CTTextCharacterProperties endParaRPr = this._p.getEndParaRPr();
        if ((endParaRPr == null || !endParaRPr.isSetSz()) && (defaultMasterStyle = getDefaultMasterStyle()) != null) {
            endParaRPr = defaultMasterStyle.getDefRPr();
        }
        return Double.valueOf((endParaRPr == null || !endParaRPr.isSetSz()) ? 12.0d : ((double) endParaRPr.getSz()) / 100.0d);
    }

    public String getDefaultFontFamily() {
        String fontFamily = this._runs.isEmpty() ? null : this._runs.get(0).getFontFamily();
        return fontFamily == null ? HSSFFont.FONT_ARIAL : fontFamily;
    }

    public TextParagraph.BulletStyle getBulletStyle() {
        if (!isBullet()) {
            return null;
        }
        return new TextParagraph.BulletStyle() {
            public String getBulletCharacter() {
                return XSLFTextParagraph.this.getBulletCharacter();
            }

            public String getBulletFont() {
                return XSLFTextParagraph.this.getBulletFont();
            }

            public Double getBulletFontSize() {
                return XSLFTextParagraph.this.getBulletFontSize();
            }

            public PaintStyle getBulletFontColor() {
                return XSLFTextParagraph.this.getBulletFontColor();
            }

            public void setBulletFontColor(Color color) {
                setBulletFontColor((PaintStyle) DrawPaint.createSolidPaint(color));
            }

            public void setBulletFontColor(PaintStyle paintStyle) {
                XSLFTextParagraph.this.setBulletFontColor(paintStyle);
            }

            public AutoNumberingScheme getAutoNumberingScheme() {
                return XSLFTextParagraph.this.getAutoNumberingScheme();
            }

            public Integer getAutoNumberingStartAt() {
                return XSLFTextParagraph.this.getAutoNumberingStartAt();
            }
        };
    }

    public void setBulletStyle(Object... objArr) {
        if (objArr.length == 0) {
            setBullet(false);
            return;
        }
        setBullet(true);
        for (Number number : objArr) {
            if (number instanceof Number) {
                setBulletFontSize(number.doubleValue());
            } else if (number instanceof Color) {
                setBulletFontColor((Color) number);
            } else if (number instanceof Character) {
                setBulletCharacter(number.toString());
            } else if (number instanceof String) {
                setBulletFont((String) number);
            } else if (number instanceof AutoNumberingScheme) {
                setBulletAutoNumber((AutoNumberingScheme) number, 0);
            }
        }
    }

    public List<XSLFTabStop> getTabStops() {
        return (List) fetchParagraphProperty(new XSLFTextParagraph$$ExternalSyntheticLambda0());
    }

    /* access modifiers changed from: private */
    public static void fetchTabStops(CTTextParagraphProperties cTTextParagraphProperties, Consumer<List<XSLFTabStop>> consumer) {
        if (cTTextParagraphProperties.isSetTabLst()) {
            ArrayList arrayList = new ArrayList();
            for (CTTextTabStop xSLFTabStop : cTTextParagraphProperties.getTabLst().getTabArray()) {
                arrayList.add(new XSLFTabStop(xSLFTabStop));
            }
            consumer.accept(arrayList);
        }
    }

    public void addTabStops(double d, TabStop.TabStopType tabStopType) {
        CTTextParagraphProperties cTTextParagraphProperties;
        if (getParentShape().getSheet() instanceof XSLFSlideMaster) {
            cTTextParagraphProperties = getDefaultMasterStyle();
        } else {
            CTTextParagraph xmlObject = getXmlObject();
            cTTextParagraphProperties = xmlObject.isSetPPr() ? xmlObject.getPPr() : xmlObject.addNewPPr();
        }
        if (cTTextParagraphProperties != null) {
            XSLFTabStop xSLFTabStop = new XSLFTabStop((cTTextParagraphProperties.isSetTabLst() ? cTTextParagraphProperties.getTabLst() : cTTextParagraphProperties.addNewTabLst()).addNewTab());
            xSLFTabStop.setPositionInPoints(d);
            xSLFTabStop.setType(tabStopType);
        }
    }

    public void clearTabStops() {
        CTTextParagraphProperties defaultMasterStyle = getParentShape().getSheet() instanceof XSLFSlideMaster ? getDefaultMasterStyle() : getXmlObject().getPPr();
        if (defaultMasterStyle != null && defaultMasterStyle.isSetTabLst()) {
            defaultMasterStyle.unsetTabLst();
        }
    }

    /* access modifiers changed from: package-private */
    public void clearButKeepProperties() {
        CTTextParagraph xmlObject = getXmlObject();
        for (int sizeOfBrArray = xmlObject.sizeOfBrArray(); sizeOfBrArray > 0; sizeOfBrArray--) {
            xmlObject.removeBr(sizeOfBrArray - 1);
        }
        for (int sizeOfFldArray = xmlObject.sizeOfFldArray(); sizeOfFldArray > 0; sizeOfFldArray--) {
            xmlObject.removeFld(sizeOfFldArray - 1);
        }
        if (!this._runs.isEmpty()) {
            int size = this._runs.size();
            CTTextCharacterProperties rPr = this._runs.get(size - 1).getRPr(false);
            if (rPr != null) {
                if (xmlObject.isSetEndParaRPr()) {
                    xmlObject.unsetEndParaRPr();
                }
                xmlObject.addNewEndParaRPr().set(rPr);
            }
            while (size > 0) {
                xmlObject.removeR(size - 1);
                size--;
            }
            this._runs.clear();
        }
    }

    public boolean isHeaderOrFooter() {
        int i;
        CTPlaceholder cTPlaceholder = this._shape.getPlaceholderDetails().getCTPlaceholder(false);
        if (cTPlaceholder == null) {
            i = -1;
        } else {
            i = cTPlaceholder.getType().intValue();
        }
        if (i == 5 || i == 6 || i == 7 || i == 8) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public XSLFTextRun newTextRun(XmlObject xmlObject) {
        return new XSLFTextRun(xmlObject, this);
    }

    /* access modifiers changed from: protected */
    public XSLFTextRun newTextRun(CTTextLineBreak cTTextLineBreak) {
        return new XSLFLineBreak(cTTextLineBreak, this);
    }
}
