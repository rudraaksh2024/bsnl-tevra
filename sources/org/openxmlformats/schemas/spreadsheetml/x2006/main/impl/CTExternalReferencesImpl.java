package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReference;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalReferences;

public class CTExternalReferencesImpl extends XmlComplexContentImpl implements CTExternalReferences {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "externalReference")};
    private static final long serialVersionUID = 1;

    public CTExternalReferencesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTExternalReference> getExternalReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTExternalReferencesImpl$$ExternalSyntheticLambda0(this), new CTExternalReferencesImpl$$ExternalSyntheticLambda1(this), new CTExternalReferencesImpl$$ExternalSyntheticLambda2(this), new CTExternalReferencesImpl$$ExternalSyntheticLambda3(this), new CTExternalReferencesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTExternalReference[] getExternalReferenceArray() {
        return (CTExternalReference[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExternalReference[0]);
    }

    public CTExternalReference getExternalReferenceArray(int i) {
        CTExternalReference cTExternalReference;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalReference = (CTExternalReference) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTExternalReference == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTExternalReference;
    }

    public int sizeOfExternalReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setExternalReferenceArray(CTExternalReference[] cTExternalReferenceArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTExternalReferenceArr, PROPERTY_QNAME[0]);
    }

    public void setExternalReferenceArray(int i, CTExternalReference cTExternalReference) {
        generatedSetterHelperImpl(cTExternalReference, PROPERTY_QNAME[0], i, 2);
    }

    public CTExternalReference insertNewExternalReference(int i) {
        CTExternalReference cTExternalReference;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalReference = (CTExternalReference) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTExternalReference;
    }

    public CTExternalReference addNewExternalReference() {
        CTExternalReference cTExternalReference;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalReference = (CTExternalReference) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExternalReference;
    }

    public void removeExternalReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
