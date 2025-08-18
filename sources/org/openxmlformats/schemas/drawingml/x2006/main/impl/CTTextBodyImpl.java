package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;

public class CTTextBodyImpl extends XmlComplexContentImpl implements CTTextBody {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "bodyPr"), new QName(XSSFRelation.NS_DRAWINGML, "lstStyle"), new QName(XSSFRelation.NS_DRAWINGML, "p")};
    private static final long serialVersionUID = 1;

    public CTTextBodyImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextBodyProperties getBodyPr() {
        CTTextBodyProperties cTTextBodyProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBodyProperties = (CTTextBodyProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextBodyProperties == null) {
                cTTextBodyProperties = null;
            }
        }
        return cTTextBodyProperties;
    }

    public void setBodyPr(CTTextBodyProperties cTTextBodyProperties) {
        generatedSetterHelperImpl(cTTextBodyProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextBodyProperties addNewBodyPr() {
        CTTextBodyProperties cTTextBodyProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBodyProperties = (CTTextBodyProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextBodyProperties;
    }

    public CTTextListStyle getLstStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTextListStyle == null) {
                cTTextListStyle = null;
            }
        }
        return cTTextListStyle;
    }

    public boolean isSetLstStyle() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setLstStyle(CTTextListStyle cTTextListStyle) {
        generatedSetterHelperImpl(cTTextListStyle, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTextListStyle addNewLstStyle() {
        CTTextListStyle cTTextListStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTextListStyle = (CTTextListStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTextListStyle;
    }

    public void unsetLstStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public List<CTTextParagraph> getPList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTextBodyImpl$$ExternalSyntheticLambda0(this), new CTTextBodyImpl$$ExternalSyntheticLambda1(this), new CTTextBodyImpl$$ExternalSyntheticLambda2(this), new CTTextBodyImpl$$ExternalSyntheticLambda3(this), new CTTextBodyImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTextParagraph[] getPArray() {
        return (CTTextParagraph[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTTextParagraph[0]);
    }

    public CTTextParagraph getPArray(int i) {
        CTTextParagraph cTTextParagraph;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraph = (CTTextParagraph) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTTextParagraph == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTextParagraph;
    }

    public int sizeOfPArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setPArray(CTTextParagraph[] cTTextParagraphArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextParagraphArr, PROPERTY_QNAME[2]);
    }

    public void setPArray(int i, CTTextParagraph cTTextParagraph) {
        generatedSetterHelperImpl(cTTextParagraph, PROPERTY_QNAME[2], i, 2);
    }

    public CTTextParagraph insertNewP(int i) {
        CTTextParagraph cTTextParagraph;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraph = (CTTextParagraph) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTTextParagraph;
    }

    public CTTextParagraph addNewP() {
        CTTextParagraph cTTextParagraph;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraph = (CTTextParagraph) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTextParagraph;
    }

    public void removeP(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
