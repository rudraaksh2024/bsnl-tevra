package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTOleObjects;

public class CTOleObjectsImpl extends XmlComplexContentImpl implements CTOleObjects {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "oleObject")};
    private static final long serialVersionUID = 1;

    public CTOleObjectsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTOleObject> getOleObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTOleObjectsImpl$$ExternalSyntheticLambda0(this), new CTOleObjectsImpl$$ExternalSyntheticLambda1(this), new CTOleObjectsImpl$$ExternalSyntheticLambda2(this), new CTOleObjectsImpl$$ExternalSyntheticLambda3(this), new CTOleObjectsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTOleObject[] getOleObjectArray() {
        return (CTOleObject[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTOleObject[0]);
    }

    public CTOleObject getOleObjectArray(int i) {
        CTOleObject cTOleObject;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObject = (CTOleObject) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTOleObject == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOleObject;
    }

    public int sizeOfOleObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setOleObjectArray(CTOleObject[] cTOleObjectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOleObjectArr, PROPERTY_QNAME[0]);
    }

    public void setOleObjectArray(int i, CTOleObject cTOleObject) {
        generatedSetterHelperImpl(cTOleObject, PROPERTY_QNAME[0], i, 2);
    }

    public CTOleObject insertNewOleObject(int i) {
        CTOleObject cTOleObject;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObject = (CTOleObject) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTOleObject;
    }

    public CTOleObject addNewOleObject() {
        CTOleObject cTOleObject;
        synchronized (monitor()) {
            check_orphaned();
            cTOleObject = (CTOleObject) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTOleObject;
    }

    public void removeOleObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
