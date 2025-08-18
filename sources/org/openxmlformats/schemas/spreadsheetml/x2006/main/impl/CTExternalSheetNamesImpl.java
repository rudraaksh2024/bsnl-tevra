package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetNames;

public class CTExternalSheetNamesImpl extends XmlComplexContentImpl implements CTExternalSheetNames {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetName")};
    private static final long serialVersionUID = 1;

    public CTExternalSheetNamesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTExternalSheetName> getSheetNameList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTExternalSheetNamesImpl$$ExternalSyntheticLambda0(this), new CTExternalSheetNamesImpl$$ExternalSyntheticLambda1(this), new CTExternalSheetNamesImpl$$ExternalSyntheticLambda2(this), new CTExternalSheetNamesImpl$$ExternalSyntheticLambda3(this), new CTExternalSheetNamesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTExternalSheetName[] getSheetNameArray() {
        return (CTExternalSheetName[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExternalSheetName[0]);
    }

    public CTExternalSheetName getSheetNameArray(int i) {
        CTExternalSheetName cTExternalSheetName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetName = (CTExternalSheetName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTExternalSheetName == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTExternalSheetName;
    }

    public int sizeOfSheetNameArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSheetNameArray(CTExternalSheetName[] cTExternalSheetNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTExternalSheetNameArr, PROPERTY_QNAME[0]);
    }

    public void setSheetNameArray(int i, CTExternalSheetName cTExternalSheetName) {
        generatedSetterHelperImpl(cTExternalSheetName, PROPERTY_QNAME[0], i, 2);
    }

    public CTExternalSheetName insertNewSheetName(int i) {
        CTExternalSheetName cTExternalSheetName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetName = (CTExternalSheetName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTExternalSheetName;
    }

    public CTExternalSheetName addNewSheetName() {
        CTExternalSheetName cTExternalSheetName;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetName = (CTExternalSheetName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExternalSheetName;
    }

    public void removeSheetName(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
