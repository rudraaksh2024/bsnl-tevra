package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineStyleList;

public class CTLineStyleListImpl extends XmlComplexContentImpl implements CTLineStyleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "ln")};
    private static final long serialVersionUID = 1;

    public CTLineStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTLineProperties> getLnList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTLineStyleListImpl$$ExternalSyntheticLambda0(this), new CTLineStyleListImpl$$ExternalSyntheticLambda1(this), new CTLineStyleListImpl$$ExternalSyntheticLambda2(this), new CTLineStyleListImpl$$ExternalSyntheticLambda3(this), new CTLineStyleListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTLineProperties[] getLnArray() {
        return (CTLineProperties[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTLineProperties[0]);
    }

    public CTLineProperties getLnArray(int i) {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineProperties = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTLineProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLineProperties;
    }

    public int sizeOfLnArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setLnArray(CTLineProperties[] cTLinePropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLinePropertiesArr, PROPERTY_QNAME[0]);
    }

    public void setLnArray(int i, CTLineProperties cTLineProperties) {
        generatedSetterHelperImpl(cTLineProperties, PROPERTY_QNAME[0], i, 2);
    }

    public CTLineProperties insertNewLn(int i) {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineProperties = (CTLineProperties) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTLineProperties;
    }

    public CTLineProperties addNewLn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineProperties = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTLineProperties;
    }

    public void removeLn(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
