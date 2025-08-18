package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTConnector;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGraphicalObjectFrame;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShape;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTGroupShapeNonVisual;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

public class CTGroupShapeImpl extends XmlComplexContentImpl implements CTGroupShape {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "nvGrpSpPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSpPr"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "sp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "grpSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "graphicFrame"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "cxnSp"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "pic")};
    private static final long serialVersionUID = 1;

    public CTGroupShapeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGroupShapeNonVisual getNvGrpSpPr() {
        CTGroupShapeNonVisual cTGroupShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShapeNonVisual = (CTGroupShapeNonVisual) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGroupShapeNonVisual == null) {
                cTGroupShapeNonVisual = null;
            }
        }
        return cTGroupShapeNonVisual;
    }

    public void setNvGrpSpPr(CTGroupShapeNonVisual cTGroupShapeNonVisual) {
        generatedSetterHelperImpl(cTGroupShapeNonVisual, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGroupShapeNonVisual addNewNvGrpSpPr() {
        CTGroupShapeNonVisual cTGroupShapeNonVisual;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShapeNonVisual = (CTGroupShapeNonVisual) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGroupShapeNonVisual;
    }

    public CTGroupShapeProperties getGrpSpPr() {
        CTGroupShapeProperties cTGroupShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShapeProperties = (CTGroupShapeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTGroupShapeProperties == null) {
                cTGroupShapeProperties = null;
            }
        }
        return cTGroupShapeProperties;
    }

    public void setGrpSpPr(CTGroupShapeProperties cTGroupShapeProperties) {
        generatedSetterHelperImpl(cTGroupShapeProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTGroupShapeProperties addNewGrpSpPr() {
        CTGroupShapeProperties cTGroupShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShapeProperties = (CTGroupShapeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTGroupShapeProperties;
    }

    public List<CTShape> getSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGroupShapeImpl$$ExternalSyntheticLambda6(this), new CTGroupShapeImpl$$ExternalSyntheticLambda7(this), new CTGroupShapeImpl$$ExternalSyntheticLambda8(this), new CTGroupShapeImpl$$ExternalSyntheticLambda9(this), new CTGroupShapeImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTShape[] getSpArray() {
        return (CTShape[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTShape[0]);
    }

    public CTShape getSpArray(int i) {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTShape == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTShape;
    }

    public int sizeOfSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSpArray(CTShape[] cTShapeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTShapeArr, PROPERTY_QNAME[2]);
    }

    public void setSpArray(int i, CTShape cTShape) {
        generatedSetterHelperImpl(cTShape, PROPERTY_QNAME[2], i, 2);
    }

    public CTShape insertNewSp(int i) {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTShape;
    }

    public CTShape addNewSp() {
        CTShape cTShape;
        synchronized (monitor()) {
            check_orphaned();
            cTShape = (CTShape) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTShape;
    }

    public void removeSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTGroupShape> getGrpSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGroupShapeImpl$$ExternalSyntheticLambda12(this), new CTGroupShapeImpl$$ExternalSyntheticLambda13(this), new CTGroupShapeImpl$$ExternalSyntheticLambda14(this), new CTGroupShapeImpl$$ExternalSyntheticLambda15(this), new CTGroupShapeImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTGroupShape[] getGrpSpArray() {
        return (CTGroupShape[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTGroupShape[0]);
    }

    public CTGroupShape getGrpSpArray(int i) {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShape = (CTGroupShape) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTGroupShape == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGroupShape;
    }

    public int sizeOfGrpSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setGrpSpArray(CTGroupShape[] cTGroupShapeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGroupShapeArr, PROPERTY_QNAME[3]);
    }

    public void setGrpSpArray(int i, CTGroupShape cTGroupShape) {
        generatedSetterHelperImpl(cTGroupShape, PROPERTY_QNAME[3], i, 2);
    }

    public CTGroupShape insertNewGrpSp(int i) {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShape = (CTGroupShape) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTGroupShape;
    }

    public CTGroupShape addNewGrpSp() {
        CTGroupShape cTGroupShape;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupShape = (CTGroupShape) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTGroupShape;
    }

    public void removeGrpSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTGraphicalObjectFrame> getGraphicFrameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGroupShapeImpl$$ExternalSyntheticLambda20(this), new CTGroupShapeImpl$$ExternalSyntheticLambda21(this), new CTGroupShapeImpl$$ExternalSyntheticLambda22(this), new CTGroupShapeImpl$$ExternalSyntheticLambda23(this), new CTGroupShapeImpl$$ExternalSyntheticLambda24(this));
        }
        return javaListXmlObject;
    }

    public CTGraphicalObjectFrame[] getGraphicFrameArray() {
        return (CTGraphicalObjectFrame[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTGraphicalObjectFrame[0]);
    }

    public CTGraphicalObjectFrame getGraphicFrameArray(int i) {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTGraphicalObjectFrame == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGraphicalObjectFrame;
    }

    public int sizeOfGraphicFrameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setGraphicFrameArray(CTGraphicalObjectFrame[] cTGraphicalObjectFrameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGraphicalObjectFrameArr, PROPERTY_QNAME[4]);
    }

    public void setGraphicFrameArray(int i, CTGraphicalObjectFrame cTGraphicalObjectFrame) {
        generatedSetterHelperImpl(cTGraphicalObjectFrame, PROPERTY_QNAME[4], i, 2);
    }

    public CTGraphicalObjectFrame insertNewGraphicFrame(int i) {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTGraphicalObjectFrame;
    }

    public CTGraphicalObjectFrame addNewGraphicFrame() {
        CTGraphicalObjectFrame cTGraphicalObjectFrame;
        synchronized (monitor()) {
            check_orphaned();
            cTGraphicalObjectFrame = (CTGraphicalObjectFrame) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTGraphicalObjectFrame;
    }

    public void removeGraphicFrame(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTConnector> getCxnSpList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGroupShapeImpl$$ExternalSyntheticLambda0(this), new CTGroupShapeImpl$$ExternalSyntheticLambda11(this), new CTGroupShapeImpl$$ExternalSyntheticLambda17(this), new CTGroupShapeImpl$$ExternalSyntheticLambda18(this), new CTGroupShapeImpl$$ExternalSyntheticLambda19(this));
        }
        return javaListXmlObject;
    }

    public CTConnector[] getCxnSpArray() {
        return (CTConnector[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTConnector[0]);
    }

    public CTConnector getCxnSpArray(int i) {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            cTConnector = (CTConnector) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTConnector == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTConnector;
    }

    public int sizeOfCxnSpArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setCxnSpArray(CTConnector[] cTConnectorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTConnectorArr, PROPERTY_QNAME[5]);
    }

    public void setCxnSpArray(int i, CTConnector cTConnector) {
        generatedSetterHelperImpl(cTConnector, PROPERTY_QNAME[5], i, 2);
    }

    public CTConnector insertNewCxnSp(int i) {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            cTConnector = (CTConnector) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTConnector;
    }

    public CTConnector addNewCxnSp() {
        CTConnector cTConnector;
        synchronized (monitor()) {
            check_orphaned();
            cTConnector = (CTConnector) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTConnector;
    }

    public void removeCxnSp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTPicture> getPicList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGroupShapeImpl$$ExternalSyntheticLambda1(this), new CTGroupShapeImpl$$ExternalSyntheticLambda2(this), new CTGroupShapeImpl$$ExternalSyntheticLambda3(this), new CTGroupShapeImpl$$ExternalSyntheticLambda4(this), new CTGroupShapeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTPicture[] getPicArray() {
        return (CTPicture[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTPicture[0]);
    }

    public CTPicture getPicArray(int i) {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTPicture == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPicture;
    }

    public int sizeOfPicArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setPicArray(CTPicture[] cTPictureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPictureArr, PROPERTY_QNAME[6]);
    }

    public void setPicArray(int i, CTPicture cTPicture) {
        generatedSetterHelperImpl(cTPicture, PROPERTY_QNAME[6], i, 2);
    }

    public CTPicture insertNewPic(int i) {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTPicture;
    }

    public CTPicture addNewPic() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTPicture;
    }

    public void removePic(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }
}
