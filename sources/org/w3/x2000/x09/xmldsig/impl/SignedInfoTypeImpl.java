package org.w3.x2000.x09.xmldsig.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.w3.x2000.x09.xmldsig.CanonicalizationMethodType;
import org.w3.x2000.x09.xmldsig.ReferenceType;
import org.w3.x2000.x09.xmldsig.SignatureMethodType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

public class SignedInfoTypeImpl extends XmlComplexContentImpl implements SignedInfoType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "CanonicalizationMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "SignatureMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "Reference"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignedInfoTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CanonicalizationMethodType getCanonicalizationMethod() {
        CanonicalizationMethodType canonicalizationMethodType;
        synchronized (monitor()) {
            check_orphaned();
            canonicalizationMethodType = (CanonicalizationMethodType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (canonicalizationMethodType == null) {
                canonicalizationMethodType = null;
            }
        }
        return canonicalizationMethodType;
    }

    public void setCanonicalizationMethod(CanonicalizationMethodType canonicalizationMethodType) {
        generatedSetterHelperImpl(canonicalizationMethodType, PROPERTY_QNAME[0], 0, 1);
    }

    public CanonicalizationMethodType addNewCanonicalizationMethod() {
        CanonicalizationMethodType canonicalizationMethodType;
        synchronized (monitor()) {
            check_orphaned();
            canonicalizationMethodType = (CanonicalizationMethodType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return canonicalizationMethodType;
    }

    public SignatureMethodType getSignatureMethod() {
        SignatureMethodType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public void setSignatureMethod(SignatureMethodType signatureMethodType) {
        generatedSetterHelperImpl(signatureMethodType, PROPERTY_QNAME[1], 0, 1);
    }

    public SignatureMethodType addNewSignatureMethod() {
        SignatureMethodType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public List<ReferenceType> getReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignedInfoTypeImpl$$ExternalSyntheticLambda0(this), new SignedInfoTypeImpl$$ExternalSyntheticLambda1(this), new SignedInfoTypeImpl$$ExternalSyntheticLambda2(this), new SignedInfoTypeImpl$$ExternalSyntheticLambda3(this), new SignedInfoTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public ReferenceType[] getReferenceArray() {
        return (ReferenceType[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new ReferenceType[0]);
    }

    public ReferenceType getReferenceArray(int i) {
        ReferenceType referenceType;
        synchronized (monitor()) {
            check_orphaned();
            referenceType = (ReferenceType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (referenceType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return referenceType;
    }

    public int sizeOfReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setReferenceArray(ReferenceType[] referenceTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) referenceTypeArr, PROPERTY_QNAME[2]);
    }

    public void setReferenceArray(int i, ReferenceType referenceType) {
        generatedSetterHelperImpl(referenceType, PROPERTY_QNAME[2], i, 2);
    }

    public ReferenceType insertNewReference(int i) {
        ReferenceType referenceType;
        synchronized (monitor()) {
            check_orphaned();
            referenceType = (ReferenceType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return referenceType;
    }

    public ReferenceType addNewReference() {
        ReferenceType referenceType;
        synchronized (monitor()) {
            check_orphaned();
            referenceType = (ReferenceType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return referenceType;
    }

    public void removeReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public String getId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlID xgetId() {
        XmlID xmlID;
        synchronized (monitor()) {
            check_orphaned();
            xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return xmlID;
    }

    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[3]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setId(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 3
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setStringValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl.setId(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetId(org.apache.xmlbeans.XmlID r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 3
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.w3.x2000.x09.xmldsig.impl.SignedInfoTypeImpl.xsetId(org.apache.xmlbeans.XmlID):void");
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
