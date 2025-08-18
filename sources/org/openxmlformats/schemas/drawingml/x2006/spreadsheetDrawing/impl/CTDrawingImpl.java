package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTAbsoluteAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTDrawing;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTOneCellAnchor;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTTwoCellAnchor;

public class CTDrawingImpl extends XmlComplexContentImpl implements CTDrawing {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "twoCellAnchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "oneCellAnchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "absoluteAnchor")};
    private static final long serialVersionUID = 1;

    public CTDrawingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTwoCellAnchor> getTwoCellAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDrawingImpl$$ExternalSyntheticLambda1(this), new CTDrawingImpl$$ExternalSyntheticLambda2(this), new CTDrawingImpl$$ExternalSyntheticLambda3(this), new CTDrawingImpl$$ExternalSyntheticLambda4(this), new CTDrawingImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTTwoCellAnchor[] getTwoCellAnchorArray() {
        return (CTTwoCellAnchor[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTwoCellAnchor[0]);
    }

    public CTTwoCellAnchor getTwoCellAnchorArray(int i) {
        CTTwoCellAnchor cTTwoCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTTwoCellAnchor = (CTTwoCellAnchor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTwoCellAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTwoCellAnchor;
    }

    public int sizeOfTwoCellAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setTwoCellAnchorArray(CTTwoCellAnchor[] cTTwoCellAnchorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTwoCellAnchorArr, PROPERTY_QNAME[0]);
    }

    public void setTwoCellAnchorArray(int i, CTTwoCellAnchor cTTwoCellAnchor) {
        generatedSetterHelperImpl(cTTwoCellAnchor, PROPERTY_QNAME[0], i, 2);
    }

    public CTTwoCellAnchor insertNewTwoCellAnchor(int i) {
        CTTwoCellAnchor cTTwoCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTTwoCellAnchor = (CTTwoCellAnchor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTwoCellAnchor;
    }

    public CTTwoCellAnchor addNewTwoCellAnchor() {
        CTTwoCellAnchor cTTwoCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTTwoCellAnchor = (CTTwoCellAnchor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTwoCellAnchor;
    }

    public void removeTwoCellAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTOneCellAnchor> getOneCellAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDrawingImpl$$ExternalSyntheticLambda10(this), new CTDrawingImpl$$ExternalSyntheticLambda11(this), new CTDrawingImpl$$ExternalSyntheticLambda12(this), new CTDrawingImpl$$ExternalSyntheticLambda13(this), new CTDrawingImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public CTOneCellAnchor[] getOneCellAnchorArray() {
        return (CTOneCellAnchor[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTOneCellAnchor[0]);
    }

    public CTOneCellAnchor getOneCellAnchorArray(int i) {
        CTOneCellAnchor cTOneCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTOneCellAnchor = (CTOneCellAnchor) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTOneCellAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOneCellAnchor;
    }

    public int sizeOfOneCellAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setOneCellAnchorArray(CTOneCellAnchor[] cTOneCellAnchorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOneCellAnchorArr, PROPERTY_QNAME[1]);
    }

    public void setOneCellAnchorArray(int i, CTOneCellAnchor cTOneCellAnchor) {
        generatedSetterHelperImpl(cTOneCellAnchor, PROPERTY_QNAME[1], i, 2);
    }

    public CTOneCellAnchor insertNewOneCellAnchor(int i) {
        CTOneCellAnchor cTOneCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTOneCellAnchor = (CTOneCellAnchor) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTOneCellAnchor;
    }

    public CTOneCellAnchor addNewOneCellAnchor() {
        CTOneCellAnchor cTOneCellAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTOneCellAnchor = (CTOneCellAnchor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTOneCellAnchor;
    }

    public void removeOneCellAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTAbsoluteAnchor> getAbsoluteAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDrawingImpl$$ExternalSyntheticLambda0(this), new CTDrawingImpl$$ExternalSyntheticLambda6(this), new CTDrawingImpl$$ExternalSyntheticLambda7(this), new CTDrawingImpl$$ExternalSyntheticLambda8(this), new CTDrawingImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTAbsoluteAnchor[] getAbsoluteAnchorArray() {
        return (CTAbsoluteAnchor[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTAbsoluteAnchor[0]);
    }

    public CTAbsoluteAnchor getAbsoluteAnchorArray(int i) {
        CTAbsoluteAnchor cTAbsoluteAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAbsoluteAnchor = (CTAbsoluteAnchor) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTAbsoluteAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAbsoluteAnchor;
    }

    public int sizeOfAbsoluteAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setAbsoluteAnchorArray(CTAbsoluteAnchor[] cTAbsoluteAnchorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAbsoluteAnchorArr, PROPERTY_QNAME[2]);
    }

    public void setAbsoluteAnchorArray(int i, CTAbsoluteAnchor cTAbsoluteAnchor) {
        generatedSetterHelperImpl(cTAbsoluteAnchor, PROPERTY_QNAME[2], i, 2);
    }

    public CTAbsoluteAnchor insertNewAbsoluteAnchor(int i) {
        CTAbsoluteAnchor cTAbsoluteAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAbsoluteAnchor = (CTAbsoluteAnchor) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTAbsoluteAnchor;
    }

    public CTAbsoluteAnchor addNewAbsoluteAnchor() {
        CTAbsoluteAnchor cTAbsoluteAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAbsoluteAnchor = (CTAbsoluteAnchor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTAbsoluteAnchor;
    }

    public void removeAbsoluteAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
