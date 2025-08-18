package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredErrors;

public class CTIgnoredErrorsImpl extends XmlComplexContentImpl implements CTIgnoredErrors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "ignoredError"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTIgnoredErrorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTIgnoredError> getIgnoredErrorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTIgnoredErrorsImpl$$ExternalSyntheticLambda0(this), new CTIgnoredErrorsImpl$$ExternalSyntheticLambda1(this), new CTIgnoredErrorsImpl$$ExternalSyntheticLambda2(this), new CTIgnoredErrorsImpl$$ExternalSyntheticLambda3(this), new CTIgnoredErrorsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTIgnoredError[] getIgnoredErrorArray() {
        return (CTIgnoredError[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTIgnoredError[0]);
    }

    public CTIgnoredError getIgnoredErrorArray(int i) {
        CTIgnoredError cTIgnoredError;
        synchronized (monitor()) {
            check_orphaned();
            cTIgnoredError = (CTIgnoredError) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTIgnoredError == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTIgnoredError;
    }

    public int sizeOfIgnoredErrorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setIgnoredErrorArray(CTIgnoredError[] cTIgnoredErrorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTIgnoredErrorArr, PROPERTY_QNAME[0]);
    }

    public void setIgnoredErrorArray(int i, CTIgnoredError cTIgnoredError) {
        generatedSetterHelperImpl(cTIgnoredError, PROPERTY_QNAME[0], i, 2);
    }

    public CTIgnoredError insertNewIgnoredError(int i) {
        CTIgnoredError cTIgnoredError;
        synchronized (monitor()) {
            check_orphaned();
            cTIgnoredError = (CTIgnoredError) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTIgnoredError;
    }

    public CTIgnoredError addNewIgnoredError() {
        CTIgnoredError cTIgnoredError;
        synchronized (monitor()) {
            check_orphaned();
            cTIgnoredError = (CTIgnoredError) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTIgnoredError;
    }

    public void removeIgnoredError(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
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

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
