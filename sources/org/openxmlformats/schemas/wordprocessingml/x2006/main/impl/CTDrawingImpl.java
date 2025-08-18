package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;

public class CTDrawingImpl extends XmlComplexContentImpl implements CTDrawing {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "anchor"), new QName("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "inline")};
    private static final long serialVersionUID = 1;

    public CTDrawingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTAnchor> getAnchorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDrawingImpl$$ExternalSyntheticLambda0(this), new CTDrawingImpl$$ExternalSyntheticLambda1(this), new CTDrawingImpl$$ExternalSyntheticLambda2(this), new CTDrawingImpl$$ExternalSyntheticLambda3(this), new CTDrawingImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTAnchor[] getAnchorArray() {
        return (CTAnchor[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTAnchor[0]);
    }

    public CTAnchor getAnchorArray(int i) {
        CTAnchor cTAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAnchor = (CTAnchor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTAnchor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAnchor;
    }

    public int sizeOfAnchorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setAnchorArray(CTAnchor[] cTAnchorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAnchorArr, PROPERTY_QNAME[0]);
    }

    public void setAnchorArray(int i, CTAnchor cTAnchor) {
        generatedSetterHelperImpl(cTAnchor, PROPERTY_QNAME[0], i, 2);
    }

    public CTAnchor insertNewAnchor(int i) {
        CTAnchor cTAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAnchor = (CTAnchor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTAnchor;
    }

    public CTAnchor addNewAnchor() {
        CTAnchor cTAnchor;
        synchronized (monitor()) {
            check_orphaned();
            cTAnchor = (CTAnchor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTAnchor;
    }

    public void removeAnchor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTInline> getInlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDrawingImpl$$ExternalSyntheticLambda5(this), new CTDrawingImpl$$ExternalSyntheticLambda6(this), new CTDrawingImpl$$ExternalSyntheticLambda7(this), new CTDrawingImpl$$ExternalSyntheticLambda8(this), new CTDrawingImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTInline[] getInlineArray() {
        return (CTInline[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTInline[0]);
    }

    public CTInline getInlineArray(int i) {
        CTInline cTInline;
        synchronized (monitor()) {
            check_orphaned();
            cTInline = (CTInline) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTInline == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTInline;
    }

    public int sizeOfInlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setInlineArray(CTInline[] cTInlineArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTInlineArr, PROPERTY_QNAME[1]);
    }

    public void setInlineArray(int i, CTInline cTInline) {
        generatedSetterHelperImpl(cTInline, PROPERTY_QNAME[1], i, 2);
    }

    public CTInline insertNewInline(int i) {
        CTInline cTInline;
        synchronized (monitor()) {
            check_orphaned();
            cTInline = (CTInline) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTInline;
    }

    public CTInline addNewInline() {
        CTInline cTInline;
        synchronized (monitor()) {
            check_orphaned();
            cTInline = (CTInline) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTInline;
    }

    public void removeInline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
