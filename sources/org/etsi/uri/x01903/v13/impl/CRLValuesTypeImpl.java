package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLValuesType;
import org.etsi.uri.x01903.v13.EncapsulatedPKIDataType;

public class CRLValuesTypeImpl extends XmlComplexContentImpl implements CRLValuesType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "EncapsulatedCRLValue")};
    private static final long serialVersionUID = 1;

    public CRLValuesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<EncapsulatedPKIDataType> getEncapsulatedCRLValueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CRLValuesTypeImpl$$ExternalSyntheticLambda0(this), new CRLValuesTypeImpl$$ExternalSyntheticLambda1(this), new CRLValuesTypeImpl$$ExternalSyntheticLambda2(this), new CRLValuesTypeImpl$$ExternalSyntheticLambda3(this), new CRLValuesTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public EncapsulatedPKIDataType[] getEncapsulatedCRLValueArray() {
        return (EncapsulatedPKIDataType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new EncapsulatedPKIDataType[0]);
    }

    public EncapsulatedPKIDataType getEncapsulatedCRLValueArray(int i) {
        EncapsulatedPKIDataType encapsulatedPKIDataType;
        synchronized (monitor()) {
            check_orphaned();
            encapsulatedPKIDataType = (EncapsulatedPKIDataType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (encapsulatedPKIDataType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return encapsulatedPKIDataType;
    }

    public int sizeOfEncapsulatedCRLValueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setEncapsulatedCRLValueArray(EncapsulatedPKIDataType[] encapsulatedPKIDataTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) encapsulatedPKIDataTypeArr, PROPERTY_QNAME[0]);
    }

    public void setEncapsulatedCRLValueArray(int i, EncapsulatedPKIDataType encapsulatedPKIDataType) {
        generatedSetterHelperImpl(encapsulatedPKIDataType, PROPERTY_QNAME[0], i, 2);
    }

    public EncapsulatedPKIDataType insertNewEncapsulatedCRLValue(int i) {
        EncapsulatedPKIDataType encapsulatedPKIDataType;
        synchronized (monitor()) {
            check_orphaned();
            encapsulatedPKIDataType = (EncapsulatedPKIDataType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return encapsulatedPKIDataType;
    }

    public EncapsulatedPKIDataType addNewEncapsulatedCRLValue() {
        EncapsulatedPKIDataType encapsulatedPKIDataType;
        synchronized (monitor()) {
            check_orphaned();
            encapsulatedPKIDataType = (EncapsulatedPKIDataType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return encapsulatedPKIDataType;
    }

    public void removeEncapsulatedCRLValue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
