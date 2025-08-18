package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDefinedNames;

public class CTDefinedNamesImpl extends XmlComplexContentImpl implements CTDefinedNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "definedName")};
    private static final long serialVersionUID = 1;

    public CTDefinedNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTDefinedName> getDefinedNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDefinedNamesImpl$$ExternalSyntheticLambda0(this), new CTDefinedNamesImpl$$ExternalSyntheticLambda1(this), new CTDefinedNamesImpl$$ExternalSyntheticLambda2(this), new CTDefinedNamesImpl$$ExternalSyntheticLambda3(this), new CTDefinedNamesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTDefinedName[] getDefinedNameArray() {
        return (CTDefinedName[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTDefinedName[0]);
    }

    public CTDefinedName getDefinedNameArray(int i) {
        CTDefinedName cTDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTDefinedName = (CTDefinedName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTDefinedName == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDefinedName;
    }

    public int sizeOfDefinedNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setDefinedNameArray(CTDefinedName[] cTDefinedNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDefinedNameArr, PROPERTY_QNAME[0]);
    }

    public void setDefinedNameArray(int i, CTDefinedName cTDefinedName) {
        generatedSetterHelperImpl(cTDefinedName, PROPERTY_QNAME[0], i, 2);
    }

    public CTDefinedName insertNewDefinedName(int i) {
        CTDefinedName cTDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTDefinedName = (CTDefinedName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTDefinedName;
    }

    public CTDefinedName addNewDefinedName() {
        CTDefinedName cTDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTDefinedName = (CTDefinedName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTDefinedName;
    }

    public void removeDefinedName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
