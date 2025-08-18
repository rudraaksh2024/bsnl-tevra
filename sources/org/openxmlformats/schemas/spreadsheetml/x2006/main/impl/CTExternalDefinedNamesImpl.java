package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalDefinedNames;

public class CTExternalDefinedNamesImpl extends XmlComplexContentImpl implements CTExternalDefinedNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "definedName")};
    private static final long serialVersionUID = 1;

    public CTExternalDefinedNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTExternalDefinedName> getDefinedNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTExternalDefinedNamesImpl$$ExternalSyntheticLambda0(this), new CTExternalDefinedNamesImpl$$ExternalSyntheticLambda1(this), new CTExternalDefinedNamesImpl$$ExternalSyntheticLambda2(this), new CTExternalDefinedNamesImpl$$ExternalSyntheticLambda3(this), new CTExternalDefinedNamesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTExternalDefinedName[] getDefinedNameArray() {
        return (CTExternalDefinedName[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExternalDefinedName[0]);
    }

    public CTExternalDefinedName getDefinedNameArray(int i) {
        CTExternalDefinedName cTExternalDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalDefinedName = (CTExternalDefinedName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTExternalDefinedName == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTExternalDefinedName;
    }

    public int sizeOfDefinedNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setDefinedNameArray(CTExternalDefinedName[] cTExternalDefinedNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTExternalDefinedNameArr, PROPERTY_QNAME[0]);
    }

    public void setDefinedNameArray(int i, CTExternalDefinedName cTExternalDefinedName) {
        generatedSetterHelperImpl(cTExternalDefinedName, PROPERTY_QNAME[0], i, 2);
    }

    public CTExternalDefinedName insertNewDefinedName(int i) {
        CTExternalDefinedName cTExternalDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalDefinedName = (CTExternalDefinedName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTExternalDefinedName;
    }

    public CTExternalDefinedName addNewDefinedName() {
        CTExternalDefinedName cTExternalDefinedName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalDefinedName = (CTExternalDefinedName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExternalDefinedName;
    }

    public void removeDefinedName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
