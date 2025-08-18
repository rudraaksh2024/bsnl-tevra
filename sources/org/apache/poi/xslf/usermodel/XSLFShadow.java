package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.DrawPaint;
import org.apache.poi.sl.usermodel.PaintStyle;
import org.apache.poi.sl.usermodel.Shadow;
import org.apache.poi.util.Units;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;

public class XSLFShadow extends XSLFShape implements Shadow<XSLFShape, XSLFTextParagraph> {
    private XSLFSimpleShape _parent;

    XSLFShadow(CTOuterShadowEffect cTOuterShadowEffect, XSLFSimpleShape xSLFSimpleShape) {
        super(cTOuterShadowEffect, xSLFSimpleShape.getSheet());
        this._parent = xSLFSimpleShape;
    }

    public XSLFSimpleShape getShadowParent() {
        return this._parent;
    }

    public Rectangle2D getAnchor() {
        return this._parent.getAnchor();
    }

    public void setAnchor(Rectangle2D rectangle2D) {
        throw new IllegalStateException("You can't set anchor of a shadow");
    }

    public double getDistance() {
        CTOuterShadowEffect cTOuterShadowEffect = (CTOuterShadowEffect) getXmlObject();
        if (cTOuterShadowEffect.isSetDist()) {
            return Units.toPoints(cTOuterShadowEffect.getDist());
        }
        return 0.0d;
    }

    public double getAngle() {
        CTOuterShadowEffect cTOuterShadowEffect = (CTOuterShadowEffect) getXmlObject();
        if (cTOuterShadowEffect.isSetDir()) {
            return ((double) cTOuterShadowEffect.getDir()) / 60000.0d;
        }
        return 0.0d;
    }

    public double getBlur() {
        CTOuterShadowEffect cTOuterShadowEffect = (CTOuterShadowEffect) getXmlObject();
        if (cTOuterShadowEffect.isSetBlurRad()) {
            return Units.toPoints(cTOuterShadowEffect.getBlurRad());
        }
        return 0.0d;
    }

    public Color getFillColor() {
        PaintStyle.SolidPaint fillStyle = getFillStyle();
        if (fillStyle == null) {
            return null;
        }
        return DrawPaint.applyColorTransform(fillStyle.getSolidColor());
    }

    public PaintStyle.SolidPaint getFillStyle() {
        XSLFTheme theme = getSheet().getTheme();
        CTOuterShadowEffect cTOuterShadowEffect = (CTOuterShadowEffect) getXmlObject();
        if (cTOuterShadowEffect == null) {
            return null;
        }
        return DrawPaint.createSolidPaint(new XSLFColor(cTOuterShadowEffect, theme, cTOuterShadowEffect.getSchemeClr(), getSheet()).getColorStyle());
    }
}
