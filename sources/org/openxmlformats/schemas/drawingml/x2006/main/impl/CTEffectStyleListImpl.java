package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleList;

public class CTEffectStyleListImpl extends XmlComplexContentImpl implements CTEffectStyleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "effectStyle")};
    private static final long serialVersionUID = 1;

    public CTEffectStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTEffectStyleItem> getEffectStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectStyleListImpl$$ExternalSyntheticLambda0(this), new CTEffectStyleListImpl$$ExternalSyntheticLambda1(this), new CTEffectStyleListImpl$$ExternalSyntheticLambda2(this), new CTEffectStyleListImpl$$ExternalSyntheticLambda3(this), new CTEffectStyleListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTEffectStyleItem[] getEffectStyleArray() {
        return (CTEffectStyleItem[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTEffectStyleItem[0]);
    }

    public CTEffectStyleItem getEffectStyleArray(int i) {
        CTEffectStyleItem cTEffectStyleItem;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectStyleItem = (CTEffectStyleItem) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTEffectStyleItem == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEffectStyleItem;
    }

    public int sizeOfEffectStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setEffectStyleArray(CTEffectStyleItem[] cTEffectStyleItemArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEffectStyleItemArr, PROPERTY_QNAME[0]);
    }

    public void setEffectStyleArray(int i, CTEffectStyleItem cTEffectStyleItem) {
        generatedSetterHelperImpl(cTEffectStyleItem, PROPERTY_QNAME[0], i, 2);
    }

    public CTEffectStyleItem insertNewEffectStyle(int i) {
        CTEffectStyleItem cTEffectStyleItem;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectStyleItem = (CTEffectStyleItem) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTEffectStyleItem;
    }

    public CTEffectStyleItem addNewEffectStyle() {
        CTEffectStyleItem cTEffectStyleItem;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectStyleItem = (CTEffectStyleItem) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEffectStyleItem;
    }

    public void removeEffectStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
