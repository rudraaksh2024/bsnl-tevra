package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CertIDListType;
import org.etsi.uri.x01903.v13.CertIDType;

public class CertIDListTypeImpl extends XmlComplexContentImpl implements CertIDListType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "Cert")};
    private static final long serialVersionUID = 1;

    public CertIDListTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CertIDType> getCertList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CertIDListTypeImpl$$ExternalSyntheticLambda0(this), new CertIDListTypeImpl$$ExternalSyntheticLambda1(this), new CertIDListTypeImpl$$ExternalSyntheticLambda2(this), new CertIDListTypeImpl$$ExternalSyntheticLambda3(this), new CertIDListTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CertIDType[] getCertArray() {
        return (CertIDType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CertIDType[0]);
    }

    public CertIDType getCertArray(int i) {
        CertIDType certIDType;
        synchronized (monitor()) {
            check_orphaned();
            certIDType = (CertIDType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (certIDType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return certIDType;
    }

    public int sizeOfCertArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCertArray(CertIDType[] certIDTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) certIDTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCertArray(int i, CertIDType certIDType) {
        generatedSetterHelperImpl(certIDType, PROPERTY_QNAME[0], i, 2);
    }

    public CertIDType insertNewCert(int i) {
        CertIDType certIDType;
        synchronized (monitor()) {
            check_orphaned();
            certIDType = (CertIDType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return certIDType;
    }

    public CertIDType addNewCert() {
        CertIDType certIDType;
        synchronized (monitor()) {
            check_orphaned();
            certIDType = (CertIDType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return certIDType;
    }

    public void removeCert(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
