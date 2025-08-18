package org.apache.poi.xslf.usermodel;

import org.apache.poi.sl.usermodel.AutoShape;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.STShapeType;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShape;
import org.openxmlformats.schemas.presentationml.x2006.main.CTShapeNonVisual;

public class XSLFAutoShape extends XSLFTextShape implements AutoShape<XSLFShape, XSLFTextParagraph> {
    XSLFAutoShape(CTShape cTShape, XSLFSheet xSLFSheet) {
        super(cTShape, xSLFSheet);
    }

    static XSLFAutoShape create(CTShape cTShape, XSLFSheet xSLFSheet) {
        if (cTShape.getSpPr().isSetCustGeom()) {
            return new XSLFFreeformShape(cTShape, xSLFSheet);
        }
        if (cTShape.getNvSpPr().getCNvSpPr().isSetTxBox()) {
            return new XSLFTextBox(cTShape, xSLFSheet);
        }
        return new XSLFAutoShape(cTShape, xSLFSheet);
    }

    static CTShape prototype(int i) {
        CTShape newInstance = CTShape.Factory.newInstance();
        CTShapeNonVisual addNewNvSpPr = newInstance.addNewNvSpPr();
        CTNonVisualDrawingProps addNewCNvPr = addNewNvSpPr.addNewCNvPr();
        addNewCNvPr.setName("AutoShape " + i);
        addNewCNvPr.setId((long) i);
        addNewNvSpPr.addNewCNvSpPr();
        addNewNvSpPr.addNewNvPr();
        CTPresetGeometry2D addNewPrstGeom = newInstance.addNewSpPr().addNewPrstGeom();
        addNewPrstGeom.setPrst(STShapeType.RECT);
        addNewPrstGeom.addNewAvLst();
        return newInstance;
    }

    /* access modifiers changed from: protected */
    public CTTextBody getTextBody(boolean z) {
        CTShape cTShape = (CTShape) getXmlObject();
        CTTextBody txBody = cTShape.getTxBody();
        if (txBody != null || !z) {
            return txBody;
        }
        cTShape.setTxBody(new XDDFTextBody(this).getXmlObject());
        return cTShape.getTxBody();
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + "] " + getShapeName();
    }
}
