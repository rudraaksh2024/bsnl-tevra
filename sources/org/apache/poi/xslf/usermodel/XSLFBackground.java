package org.apache.poi.xslf.usermodel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.sl.usermodel.Background;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackground;
import org.openxmlformats.schemas.presentationml.x2006.main.CTBackgroundProperties;

public class XSLFBackground extends XSLFSimpleShape implements Background<XSLFShape, XSLFTextParagraph> {
    /* access modifiers changed from: protected */
    public CTTransform2D getXfrm(boolean z) {
        return null;
    }

    XSLFBackground(CTBackground cTBackground, XSLFSheet xSLFSheet) {
        super(cTBackground, xSLFSheet);
    }

    public Rectangle2D getAnchor() {
        Dimension pageSize = getSheet().getSlideShow().getPageSize();
        return new Rectangle2D.Double(0.0d, 0.0d, pageSize.getWidth(), pageSize.getHeight());
    }

    public void setPlaceholder(Placeholder placeholder) {
        throw new POIXMLException("Can't set a placeholder for a background");
    }

    /* access modifiers changed from: protected */
    public CTBackgroundProperties getBgPr(boolean z) {
        CTBackground cTBackground = (CTBackground) getXmlObject();
        if (cTBackground.isSetBgPr() || !z) {
            return cTBackground.getBgPr();
        }
        if (cTBackground.isSetBgRef()) {
            cTBackground.unsetBgRef();
        }
        return cTBackground.addNewBgPr();
    }

    public void setFillColor(Color color) {
        CTBackgroundProperties bgPr = getBgPr(true);
        if (bgPr.isSetBlipFill()) {
            bgPr.unsetBlipFill();
        }
        if (bgPr.isSetGradFill()) {
            bgPr.unsetGradFill();
        }
        if (bgPr.isSetGrpFill()) {
            bgPr.unsetGrpFill();
        }
        if (bgPr.isSetPattFill()) {
            bgPr.unsetPattFill();
        }
        if (color == null) {
            if (bgPr.isSetSolidFill()) {
                bgPr.unsetSolidFill();
            }
            if (!bgPr.isSetNoFill()) {
                bgPr.addNewNoFill();
                return;
            }
            return;
        }
        if (bgPr.isSetNoFill()) {
            bgPr.unsetNoFill();
        }
        CTSolidColorFillProperties solidFill = bgPr.isSetSolidFill() ? bgPr.getSolidFill() : bgPr.addNewSolidFill();
        new XSLFColor(solidFill, getSheet().getTheme(), solidFill.getSchemeClr(), getSheet()).setColor(color);
    }

    /* access modifiers changed from: protected */
    public XmlObject getShapeProperties() {
        CTBackground cTBackground = (CTBackground) getXmlObject();
        if (cTBackground.isSetBgPr()) {
            return cTBackground.getBgPr();
        }
        if (cTBackground.isSetBgRef()) {
            return cTBackground.getBgRef();
        }
        return null;
    }
}
