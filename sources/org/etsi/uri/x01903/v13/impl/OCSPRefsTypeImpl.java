package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.OCSPRefType;
import org.etsi.uri.x01903.v13.OCSPRefsType;

public class OCSPRefsTypeImpl extends XmlComplexContentImpl implements OCSPRefsType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "OCSPRef")};
    private static final long serialVersionUID = 1;

    public OCSPRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<OCSPRefType> getOCSPRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new OCSPRefsTypeImpl$$ExternalSyntheticLambda0(this), new OCSPRefsTypeImpl$$ExternalSyntheticLambda1(this), new OCSPRefsTypeImpl$$ExternalSyntheticLambda2(this), new OCSPRefsTypeImpl$$ExternalSyntheticLambda3(this), new OCSPRefsTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public OCSPRefType[] getOCSPRefArray() {
        return (OCSPRefType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new OCSPRefType[0]);
    }

    public OCSPRefType getOCSPRefArray(int i) {
        OCSPRefType oCSPRefType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPRefType = (OCSPRefType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (oCSPRefType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return oCSPRefType;
    }

    public int sizeOfOCSPRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setOCSPRefArray(OCSPRefType[] oCSPRefTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) oCSPRefTypeArr, PROPERTY_QNAME[0]);
    }

    public void setOCSPRefArray(int i, OCSPRefType oCSPRefType) {
        generatedSetterHelperImpl(oCSPRefType, PROPERTY_QNAME[0], i, 2);
    }

    public OCSPRefType insertNewOCSPRef(int i) {
        OCSPRefType oCSPRefType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPRefType = (OCSPRefType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return oCSPRefType;
    }

    public OCSPRefType addNewOCSPRef() {
        OCSPRefType oCSPRefType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPRefType = (OCSPRefType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return oCSPRefType;
    }

    public void removeOCSPRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
