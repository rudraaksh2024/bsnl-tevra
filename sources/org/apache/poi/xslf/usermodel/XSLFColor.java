package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import javax.xml.namespace.QName;
import kotlin.UByte;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.AbstractColorStyle;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.PresetColor;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.Angles;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFontReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

@Internal
public class XSLFColor {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) XSLFColor.class);
    private static final QName VAL_ATTR = new QName("val");
    private final Color _color;
    private final CTSchemeColor _phClr;
    private final XSLFSheet _sheet;
    private final XmlObject _xmlObject;

    public XSLFColor(XmlObject xmlObject, XSLFTheme xSLFTheme, CTSchemeColor cTSchemeColor, XSLFSheet xSLFSheet) {
        this._xmlObject = xmlObject;
        this._phClr = cTSchemeColor;
        this._sheet = xSLFSheet;
        this._color = toColor(xmlObject, xSLFTheme);
    }

    @Internal
    public XmlObject getXmlObject() {
        return this._xmlObject;
    }

    public Color getColor() {
        return DrawPaint.applyColorTransform(getColorStyle());
    }

    public ColorStyle getColorStyle() {
        return new XSLFColorStyle(this._xmlObject, this._color, this._phClr);
    }

    private Color toColor(CTHslColor cTHslColor) {
        return DrawPaint.HSL2RGB(((double) cTHslColor.getHue2()) / 60000.0d, ((double) POIXMLUnits.parsePercent(cTHslColor.xgetSat2())) / 1000.0d, ((double) POIXMLUnits.parsePercent(cTHslColor.xgetLum2())) / 1000.0d, 1.0d);
    }

    private Color toColor(CTPresetColor cTPresetColor) {
        PresetColor valueOfOoxmlId = PresetColor.valueOfOoxmlId(cTPresetColor.getVal().toString());
        if (valueOfOoxmlId != null) {
            return valueOfOoxmlId.color;
        }
        return null;
    }

    private Color toColor(CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme) {
        CTColor cTColor;
        String str = cTSchemeColor.getVal().toString();
        CTSchemeColor cTSchemeColor2 = this._phClr;
        if (cTSchemeColor2 != null) {
            str = cTSchemeColor2.getVal().toString();
        }
        if (xSLFTheme == null) {
            cTColor = null;
        } else {
            cTColor = xSLFTheme.getCTColor(this._sheet.mapSchemeColor(str));
        }
        if (cTColor != null) {
            return toColor((XmlObject) cTColor, (XSLFTheme) null);
        }
        return null;
    }

    private Color toColor(CTScRgbColor cTScRgbColor) {
        return DrawPaint.SCRGB2RGB(((double) POIXMLUnits.parsePercent(cTScRgbColor.xgetR())) / 100000.0d, ((double) POIXMLUnits.parsePercent(cTScRgbColor.xgetG())) / 100000.0d, ((double) POIXMLUnits.parsePercent(cTScRgbColor.xgetB())) / 100000.0d);
    }

    private Color toColor(CTSRgbColor cTSRgbColor) {
        byte[] val = cTSRgbColor.getVal();
        return new Color(val[0] & UByte.MAX_VALUE, val[1] & UByte.MAX_VALUE, val[2] & UByte.MAX_VALUE);
    }

    private Color toColor(CTSystemColor cTSystemColor) {
        if (cTSystemColor.isSetLastClr()) {
            byte[] lastClr = cTSystemColor.getLastClr();
            return new Color(lastClr[0] & UByte.MAX_VALUE, lastClr[1] & UByte.MAX_VALUE, lastClr[2] & UByte.MAX_VALUE);
        }
        PresetColor valueOfOoxmlId = PresetColor.valueOfOoxmlId(cTSystemColor.getVal().toString());
        return (valueOfOoxmlId == null || valueOfOoxmlId.color == null) ? Color.black : valueOfOoxmlId.color;
    }

    private Color toColor(XmlObject xmlObject, XSLFTheme xSLFTheme) {
        Color color = null;
        if (xmlObject == null) {
            CTSchemeColor cTSchemeColor = this._phClr;
            if (cTSchemeColor == null) {
                return null;
            }
            return toColor(cTSchemeColor, xSLFTheme);
        }
        XmlCursor newCursor = xmlObject.newCursor();
        int i = 0;
        while (color == null) {
            try {
                XmlObject nextObject = nextObject(xmlObject, newCursor, i);
                if (nextObject == null) {
                    break;
                }
                if (nextObject instanceof CTHslColor) {
                    color = toColor((CTHslColor) nextObject);
                } else if (nextObject instanceof CTPresetColor) {
                    color = toColor((CTPresetColor) nextObject);
                } else if (nextObject instanceof CTSchemeColor) {
                    color = toColor((CTSchemeColor) nextObject, xSLFTheme);
                } else if (nextObject instanceof CTScRgbColor) {
                    color = toColor((CTScRgbColor) nextObject);
                } else if (nextObject instanceof CTSRgbColor) {
                    color = toColor((CTSRgbColor) nextObject);
                } else if (nextObject instanceof CTSystemColor) {
                    color = toColor((CTSystemColor) nextObject);
                } else if (nextObject instanceof CTFontReference) {
                    continue;
                } else if (i > 0) {
                    throw new IllegalArgumentException("Unexpected color choice: " + nextObject.getClass());
                }
                i++;
            } catch (Throwable th) {
                newCursor.dispose();
                throw th;
            }
        }
        newCursor.dispose();
        return color;
    }

    private static XmlObject nextObject(XmlObject xmlObject, XmlCursor xmlCursor, int i) {
        if (i == 0) {
            return xmlObject;
        }
        if (i != 1) {
            if (xmlCursor.toNextSibling()) {
                return xmlCursor.getObject();
            }
            return null;
        } else if (xmlCursor.toFirstChild()) {
            return xmlCursor.getObject();
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Internal
    public void setColor(Color color) {
        XmlObject xmlObject = this._xmlObject;
        if (!(xmlObject instanceof CTSolidColorFillProperties)) {
            LOGGER.atError().log("XSLFColor.setColor currently only supports CTSolidColorFillProperties");
            return;
        }
        CTSolidColorFillProperties cTSolidColorFillProperties = (CTSolidColorFillProperties) xmlObject;
        if (cTSolidColorFillProperties.isSetSrgbClr()) {
            cTSolidColorFillProperties.unsetSrgbClr();
        }
        if (cTSolidColorFillProperties.isSetScrgbClr()) {
            cTSolidColorFillProperties.unsetScrgbClr();
        }
        if (cTSolidColorFillProperties.isSetHslClr()) {
            cTSolidColorFillProperties.unsetHslClr();
        }
        if (cTSolidColorFillProperties.isSetPrstClr()) {
            cTSolidColorFillProperties.unsetPrstClr();
        }
        if (cTSolidColorFillProperties.isSetSchemeClr()) {
            cTSolidColorFillProperties.unsetSchemeClr();
        }
        if (cTSolidColorFillProperties.isSetSysClr()) {
            cTSolidColorFillProperties.unsetSysClr();
        }
        CTPositiveFixedPercentage cTPositiveFixedPercentage = null;
        float[] rGBComponents = color.getRGBComponents((float[]) null);
        boolean z = rGBComponents.length == 4 && rGBComponents[3] < 1.0f;
        if (!isInt(rGBComponents[0]) || !isInt(rGBComponents[1]) || !isInt(rGBComponents[2])) {
            CTScRgbColor addNewScrgbClr = cTSolidColorFillProperties.addNewScrgbClr();
            double[] RGB2SCRGB = DrawPaint.RGB2SCRGB(color);
            addNewScrgbClr.setR(Integer.valueOf((int) Math.rint(RGB2SCRGB[0] * 100000.0d)));
            addNewScrgbClr.setG(Integer.valueOf((int) Math.rint(RGB2SCRGB[1] * 100000.0d)));
            addNewScrgbClr.setB(Integer.valueOf((int) Math.rint(RGB2SCRGB[2] * 100000.0d)));
            if (z) {
                cTPositiveFixedPercentage = addNewScrgbClr.addNewAlpha();
            }
        } else {
            CTSRgbColor addNewSrgbClr = cTSolidColorFillProperties.addNewSrgbClr();
            addNewSrgbClr.setVal(new byte[]{(byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue()});
            if (z) {
                cTPositiveFixedPercentage = addNewSrgbClr.addNewAlpha();
            }
        }
        if (cTPositiveFixedPercentage != null) {
            cTPositiveFixedPercentage.setVal(Integer.valueOf((int) Math.rint((double) (rGBComponents[3] * 100000.0f))));
        }
    }

    private static boolean isInt(float f) {
        double d = ((double) f) * 255.0d;
        return Math.abs(d - Math.rint(d)) < 1.0E-5d;
    }

    /* access modifiers changed from: private */
    public static int getRawValue(CTSchemeColor cTSchemeColor, XmlObject xmlObject, String str) {
        XmlObject[] xmlObjectArr = {xmlObject, cTSchemeColor};
        for (int i = 0; i < 2; i++) {
            XmlObject xmlObject2 = xmlObjectArr[i];
            if (xmlObject2 != null) {
                XmlCursor newCursor = xmlObject2.newCursor();
                try {
                    if (newCursor.toChild(XSSFRelation.NS_DRAWINGML, str) || (newCursor.toFirstChild() && newCursor.toChild(XSSFRelation.NS_DRAWINGML, str))) {
                        String attributeText = newCursor.getAttributeText(VAL_ATTR);
                        if (attributeText != null && !"".equals(attributeText)) {
                            int parseInt = Integer.parseInt(attributeText);
                            newCursor.dispose();
                            return parseInt;
                        }
                    }
                } finally {
                    newCursor.dispose();
                }
            }
        }
        return -1;
    }

    private int getPercentageValue(String str) {
        int rawValue = getRawValue(this._phClr, this._xmlObject, str);
        return rawValue == -1 ? rawValue : rawValue / 1000;
    }

    /* access modifiers changed from: package-private */
    public int getAlpha() {
        return getPercentageValue("alpha");
    }

    /* access modifiers changed from: package-private */
    public int getAlphaMod() {
        return getPercentageValue("alphaMod");
    }

    /* access modifiers changed from: package-private */
    public int getAlphaOff() {
        return getPercentageValue("alphaOff");
    }

    /* access modifiers changed from: package-private */
    public int getHue() {
        int rawValue = getRawValue(this._phClr, this._xmlObject, "hue");
        return rawValue == -1 ? rawValue : rawValue / Angles.OOXML_DEGREE;
    }

    /* access modifiers changed from: package-private */
    public int getHueMod() {
        return getPercentageValue("hueMod");
    }

    /* access modifiers changed from: package-private */
    public int getHueOff() {
        return getPercentageValue("hueOff");
    }

    /* access modifiers changed from: package-private */
    public int getLum() {
        return getPercentageValue("lum");
    }

    /* access modifiers changed from: package-private */
    public int getLumMod() {
        return getPercentageValue("lumMod");
    }

    /* access modifiers changed from: package-private */
    public int getLumOff() {
        return getPercentageValue("lumOff");
    }

    /* access modifiers changed from: package-private */
    public int getSat() {
        return getPercentageValue("sat");
    }

    /* access modifiers changed from: package-private */
    public int getSatMod() {
        return getPercentageValue("satMod");
    }

    /* access modifiers changed from: package-private */
    public int getSatOff() {
        return getPercentageValue("satOff");
    }

    /* access modifiers changed from: package-private */
    public int getRed() {
        return getPercentageValue("red");
    }

    /* access modifiers changed from: package-private */
    public int getRedMod() {
        return getPercentageValue("redMod");
    }

    /* access modifiers changed from: package-private */
    public int getRedOff() {
        return getPercentageValue("redOff");
    }

    /* access modifiers changed from: package-private */
    public int getGreen() {
        return getPercentageValue("green");
    }

    /* access modifiers changed from: package-private */
    public int getGreenMod() {
        return getPercentageValue("greenMod");
    }

    /* access modifiers changed from: package-private */
    public int getGreenOff() {
        return getPercentageValue("greenOff");
    }

    /* access modifiers changed from: package-private */
    public int getBlue() {
        return getPercentageValue("blue");
    }

    /* access modifiers changed from: package-private */
    public int getBlueMod() {
        return getPercentageValue("blueMod");
    }

    /* access modifiers changed from: package-private */
    public int getBlueOff() {
        return getPercentageValue("blueOff");
    }

    public int getShade() {
        return getPercentageValue("shade");
    }

    public int getTint() {
        return getPercentageValue("tint");
    }

    private static class XSLFColorStyle extends AbstractColorStyle {
        private final Color color;
        private final CTSchemeColor phClr;
        private final XmlObject xmlObject;

        XSLFColorStyle(XmlObject xmlObject2, Color color2, CTSchemeColor cTSchemeColor) {
            this.xmlObject = xmlObject2;
            this.color = color2;
            this.phClr = cTSchemeColor;
        }

        public Color getColor() {
            return this.color;
        }

        public int getAlpha() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "alpha");
        }

        public int getHueOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueOff");
        }

        public int getHueMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "hueMod");
        }

        public int getSatOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satOff");
        }

        public int getSatMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "satMod");
        }

        public int getLumOff() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumOff");
        }

        public int getLumMod() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "lumMod");
        }

        public int getShade() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "shade");
        }

        public int getTint() {
            return XSLFColor.getRawValue(this.phClr, this.xmlObject, "tint");
        }
    }
}
