package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;

public class CTGeomGuideListImpl extends XmlComplexContentImpl implements CTGeomGuideList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gd")};
    private static final long serialVersionUID = 1;

    public CTGeomGuideListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTGeomGuide> getGdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTGeomGuideListImpl$$ExternalSyntheticLambda0(this), new CTGeomGuideListImpl$$ExternalSyntheticLambda1(this), new CTGeomGuideListImpl$$ExternalSyntheticLambda2(this), new CTGeomGuideListImpl$$ExternalSyntheticLambda3(this), new CTGeomGuideListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTGeomGuide[] getGdArray() {
        return (CTGeomGuide[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTGeomGuide[0]);
    }

    public CTGeomGuide getGdArray(int i) {
        CTGeomGuide cTGeomGuide;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuide = (CTGeomGuide) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTGeomGuide == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGeomGuide;
    }

    public int sizeOfGdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setGdArray(CTGeomGuide[] cTGeomGuideArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGeomGuideArr, PROPERTY_QNAME[0]);
    }

    public void setGdArray(int i, CTGeomGuide cTGeomGuide) {
        generatedSetterHelperImpl(cTGeomGuide, PROPERTY_QNAME[0], i, 2);
    }

    public CTGeomGuide insertNewGd(int i) {
        CTGeomGuide cTGeomGuide;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuide = (CTGeomGuide) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTGeomGuide;
    }

    public CTGeomGuide addNewGd() {
        CTGeomGuide cTGeomGuide;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuide = (CTGeomGuide) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGeomGuide;
    }

    public void removeGd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
