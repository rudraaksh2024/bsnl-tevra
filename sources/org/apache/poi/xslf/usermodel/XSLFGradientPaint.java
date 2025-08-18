package org.apache.poi.xslf.usermodel;

import java.util.Arrays;
import org.apache.poi.ooxml.util.POIXMLUnits;
import org.apache.poi.sl.usermodel.ColorStyle;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStop;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

@Internal
public class XSLFGradientPaint implements PaintStyle.GradientPaint {
    final ColorStyle[] cs;
    final float[] fractions;
    private final CTGradientFillProperties gradFill;

    public XSLFGradientPaint(CTGradientFillProperties cTGradientFillProperties, CTSchemeColor cTSchemeColor, XSLFTheme xSLFTheme, XSLFSheet xSLFSheet) {
        CTGradientStop[] cTGradientStopArr;
        this.gradFill = cTGradientFillProperties;
        if (cTGradientFillProperties.getGsLst() == null) {
            cTGradientStopArr = new CTGradientStop[0];
        } else {
            cTGradientStopArr = cTGradientFillProperties.getGsLst().getGsArray();
        }
        Arrays.sort(cTGradientStopArr, new XSLFGradientPaint$$ExternalSyntheticLambda0());
        this.cs = new ColorStyle[cTGradientStopArr.length];
        this.fractions = new float[cTGradientStopArr.length];
        int i = 0;
        for (CTGradientStop cTGradientStop : cTGradientStopArr) {
            this.cs[i] = new XSLFColor(cTGradientStop, xSLFTheme, (cTSchemeColor != null || !cTGradientStop.isSetSchemeClr()) ? cTSchemeColor : cTGradientStop.getSchemeClr(), xSLFSheet).getColorStyle();
            this.fractions[i] = ((float) POIXMLUnits.parsePercent(cTGradientStop.xgetPos())) / 100000.0f;
            i++;
        }
    }

    public double getGradientAngle() {
        if (this.gradFill.isSetLin()) {
            return ((double) this.gradFill.getLin().getAng()) / 60000.0d;
        }
        return 0.0d;
    }

    public ColorStyle[] getGradientColors() {
        return this.cs;
    }

    public float[] getGradientFractions() {
        return this.fractions;
    }

    public boolean isRotatedWithShape() {
        return this.gradFill.getRotWithShape();
    }

    public PaintStyle.GradientPaint.GradientType getGradientType() {
        if (this.gradFill.isSetLin()) {
            return PaintStyle.GradientPaint.GradientType.linear;
        }
        if (this.gradFill.isSetPath()) {
            STPathShadeType.Enum path = this.gradFill.getPath().getPath();
            if (path == STPathShadeType.CIRCLE) {
                return PaintStyle.GradientPaint.GradientType.circular;
            }
            if (path == STPathShadeType.SHAPE) {
                return PaintStyle.GradientPaint.GradientType.shape;
            }
            if (path == STPathShadeType.RECT) {
                return PaintStyle.GradientPaint.GradientType.rectangular;
            }
        }
        return PaintStyle.GradientPaint.GradientType.linear;
    }

    public Insets2D getFillToInsets() {
        if (!this.gradFill.isSetPath() || !this.gradFill.getPath().isSetFillToRect()) {
            return null;
        }
        CTRelativeRect fillToRect = this.gradFill.getPath().getFillToRect();
        return new Insets2D(((double) POIXMLUnits.parsePercent(fillToRect.xgetT())) / 100000.0d, ((double) POIXMLUnits.parsePercent(fillToRect.xgetL())) / 100000.0d, ((double) POIXMLUnits.parsePercent(fillToRect.xgetB())) / 100000.0d, ((double) POIXMLUnits.parsePercent(fillToRect.xgetR())) / 100000.0d);
    }
}
