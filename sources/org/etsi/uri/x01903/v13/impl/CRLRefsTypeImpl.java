package org.etsi.uri.x01903.v13.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLRefType;
import org.etsi.uri.x01903.v13.CRLRefsType;

public class CRLRefsTypeImpl extends XmlComplexContentImpl implements CRLRefsType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CRLRef")};
    private static final long serialVersionUID = 1;

    public CRLRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CRLRefType> getCRLRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CRLRefsTypeImpl$$ExternalSyntheticLambda0(this), new CRLRefsTypeImpl$$ExternalSyntheticLambda1(this), new CRLRefsTypeImpl$$ExternalSyntheticLambda2(this), new CRLRefsTypeImpl$$ExternalSyntheticLambda3(this), new CRLRefsTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CRLRefType[] getCRLRefArray() {
        return (CRLRefType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CRLRefType[0]);
    }

    public CRLRefType getCRLRefArray(int i) {
        CRLRefType cRLRefType;
        synchronized (monitor()) {
            check_orphaned();
            cRLRefType = (CRLRefType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cRLRefType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cRLRefType;
    }

    public int sizeOfCRLRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCRLRefArray(CRLRefType[] cRLRefTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cRLRefTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCRLRefArray(int i, CRLRefType cRLRefType) {
        generatedSetterHelperImpl(cRLRefType, PROPERTY_QNAME[0], i, 2);
    }

    public CRLRefType insertNewCRLRef(int i) {
        CRLRefType cRLRefType;
        synchronized (monitor()) {
            check_orphaned();
            cRLRefType = (CRLRefType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cRLRefType;
    }

    public CRLRefType addNewCRLRef() {
        CRLRefType cRLRefType;
        synchronized (monitor()) {
            check_orphaned();
            cRLRefType = (CRLRefType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cRLRefType;
    }

    public void removeCRLRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
