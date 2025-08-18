package org.apache.poi.ooxml.util;

import java.util.Locale;
import kotlinx.coroutines.DebugKt;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.openxmlformats.schemas.drawingml.x2006.chart.STDepthPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.STGapAmount;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHPercent;
import org.openxmlformats.schemas.drawingml.x2006.chart.STHoleSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.STOverlap;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate32;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextBulletSizePercent;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextFontScalePercentOrPercentString;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextPoint;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextSpacingPercentOrPercentString;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumberOrPercent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMeasurementOrPercent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTextScale;

public class POIXMLUnits {
    public static int parsePercent(STPositivePercentage sTPositivePercentage) {
        return parsePercentInner(sTPositivePercentage, 1);
    }

    public static int parsePercent(STPositiveFixedPercentage sTPositiveFixedPercentage) {
        return parsePercentInner(sTPositiveFixedPercentage, 1);
    }

    public static int parsePercent(STPercentage sTPercentage) {
        return parsePercentInner(sTPercentage, 1);
    }

    public static int parsePercent(STTextBulletSizePercent sTTextBulletSizePercent) {
        return parsePercentInner(sTTextBulletSizePercent, 1);
    }

    public static int parsePercent(STTextSpacingPercentOrPercentString sTTextSpacingPercentOrPercentString) {
        return parsePercentInner(sTTextSpacingPercentOrPercentString, 1);
    }

    public static int parsePercent(STTextFontScalePercentOrPercentString sTTextFontScalePercentOrPercentString) {
        return parsePercentInner(sTTextFontScalePercentOrPercentString, 1);
    }

    public static int parsePercent(STDecimalNumberOrPercent sTDecimalNumberOrPercent) {
        return parsePercentInner(sTDecimalNumberOrPercent, 1000);
    }

    public static int parsePercent(STTextScale sTTextScale) {
        return parsePercentInner(sTTextScale, 1000);
    }

    public static int parsePercent(STGapAmount sTGapAmount) {
        return parsePercentInner(sTGapAmount, 1000);
    }

    public static int parsePercent(STOverlap sTOverlap) {
        return parsePercentInner(sTOverlap, 1000);
    }

    public static int parsePercent(STDepthPercent sTDepthPercent) {
        return parsePercentInner(sTDepthPercent, 1000);
    }

    public static int parsePercent(STHPercent sTHPercent) {
        return parsePercentInner(sTHPercent, 1000);
    }

    public static int parsePercent(STHoleSize sTHoleSize) {
        return parsePercentInner(sTHoleSize, 1);
    }

    private static int parsePercentInner(XmlAnySimpleType xmlAnySimpleType, int i) {
        String stringValue = xmlAnySimpleType.getStringValue();
        if (stringValue.endsWith("%")) {
            return Integer.parseInt(stringValue.substring(0, stringValue.length() - 1)) * 1000;
        }
        return Integer.parseInt(stringValue) * i;
    }

    public static long parseLength(STCoordinate32 sTCoordinate32) {
        return parseLengthInner(sTCoordinate32, 1.0d);
    }

    public static long parseLength(STCoordinate sTCoordinate) {
        return parseLengthInner(sTCoordinate, 1.0d);
    }

    public static long parseLength(STTextPoint sTTextPoint) {
        return parseLengthInner(sTTextPoint, 127.0d);
    }

    public static long parseLength(STTwipsMeasure sTTwipsMeasure) {
        return parseLengthInner(sTTwipsMeasure, 635.0d);
    }

    public static long parseLength(STSignedTwipsMeasure sTSignedTwipsMeasure) {
        return parseLengthInner(sTSignedTwipsMeasure, 635.0d);
    }

    public static long parseLength(STHpsMeasure sTHpsMeasure) {
        return parseLengthInner(sTHpsMeasure, 25400.0d);
    }

    public static long parseLength(STSignedHpsMeasure sTSignedHpsMeasure) {
        return parseLengthInner(sTSignedHpsMeasure, 25400.0d);
    }

    public static long parseLength(STMeasurementOrPercent sTMeasurementOrPercent) {
        if (sTMeasurementOrPercent.getStringValue().endsWith("%")) {
            return -1;
        }
        return parseLengthInner(sTMeasurementOrPercent, 635.0d);
    }

    private static long parseLengthInner(XmlAnySimpleType xmlAnySimpleType, double d) {
        double d2;
        String lowerCase = xmlAnySimpleType.getStringValue().toLowerCase(Locale.ROOT);
        double parseDouble = Double.parseDouble(lowerCase.replaceAll("(mm|cm|in|pt|pc|pi)", ""));
        if (lowerCase.endsWith("mm")) {
            parseDouble /= 10.0d;
        } else if (!lowerCase.endsWith("cm")) {
            if (!lowerCase.endsWith("in")) {
                if (lowerCase.endsWith("pc") || lowerCase.endsWith("pi")) {
                    parseDouble *= 0.16599999368190765d;
                } else {
                    d2 = lowerCase.endsWith("pt") ? parseDouble * 12700.0d : parseDouble * d;
                    return (long) d2;
                }
            }
            d2 = parseDouble * 914400.0d;
            return (long) d2;
        }
        parseDouble /= 2.5399999618530273d;
        d2 = parseDouble * 914400.0d;
        return (long) d2;
    }

    public static boolean parseOnOff(CTOnOff cTOnOff) {
        if (cTOnOff == null) {
            return false;
        }
        if (!cTOnOff.isSetVal()) {
            return true;
        }
        return parseOnOff(cTOnOff.xgetVal());
    }

    public static boolean parseOnOff(STOnOff sTOnOff) {
        if (sTOnOff == null) {
            return false;
        }
        String stringValue = sTOnOff.getStringValue();
        if ("true".equalsIgnoreCase(stringValue) || DebugKt.DEBUG_PROPERTY_VALUE_ON.equalsIgnoreCase(stringValue) || "x".equalsIgnoreCase(stringValue) || "1".equals(stringValue)) {
            return true;
        }
        return false;
    }
}
