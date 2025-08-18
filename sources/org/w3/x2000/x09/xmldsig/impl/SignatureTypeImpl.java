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
import org.w3.x2000.x09.xmldsig.KeyInfoType;
import org.w3.x2000.x09.xmldsig.ObjectType;
import org.w3.x2000.x09.xmldsig.SignatureType;
import org.w3.x2000.x09.xmldsig.SignatureValueType;
import org.w3.x2000.x09.xmldsig.SignedInfoType;

public class SignatureTypeImpl extends XmlComplexContentImpl implements SignatureType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "SignedInfo"), new QName(SignatureFacet.XML_DIGSIG_NS, "SignatureValue"), new QName(SignatureFacet.XML_DIGSIG_NS, "KeyInfo"), new QName(SignatureFacet.XML_DIGSIG_NS, "Object"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public SignatureTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public SignedInfoType getSignedInfo() {
        SignedInfoType signedInfoType;
        synchronized (monitor()) {
            check_orphaned();
            signedInfoType = (SignedInfoType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (signedInfoType == null) {
                signedInfoType = null;
            }
        }
        return signedInfoType;
    }

    public void setSignedInfo(SignedInfoType signedInfoType) {
        generatedSetterHelperImpl(signedInfoType, PROPERTY_QNAME[0], 0, 1);
    }

    public SignedInfoType addNewSignedInfo() {
        SignedInfoType signedInfoType;
        synchronized (monitor()) {
            check_orphaned();
            signedInfoType = (SignedInfoType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return signedInfoType;
    }

    public SignatureValueType getSignatureValue() {
        SignatureValueType signatureValueType;
        synchronized (monitor()) {
            check_orphaned();
            signatureValueType = (SignatureValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (signatureValueType == null) {
                signatureValueType = null;
            }
        }
        return signatureValueType;
    }

    public void setSignatureValue(SignatureValueType signatureValueType) {
        generatedSetterHelperImpl(signatureValueType, PROPERTY_QNAME[1], 0, 1);
    }

    public SignatureValueType addNewSignatureValue() {
        SignatureValueType signatureValueType;
        synchronized (monitor()) {
            check_orphaned();
            signatureValueType = (SignatureValueType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return signatureValueType;
    }

    public KeyInfoType getKeyInfo() {
        KeyInfoType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetKeyInfo() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setKeyInfo(KeyInfoType keyInfoType) {
        generatedSetterHelperImpl(keyInfoType, PROPERTY_QNAME[2], 0, 1);
    }

    public KeyInfoType addNewKeyInfo() {
        KeyInfoType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetKeyInfo() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public List<ObjectType> getObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SignatureTypeImpl$$ExternalSyntheticLambda0(this), new SignatureTypeImpl$$ExternalSyntheticLambda1(this), new SignatureTypeImpl$$ExternalSyntheticLambda2(this), new SignatureTypeImpl$$ExternalSyntheticLambda3(this), new SignatureTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public ObjectType[] getObjectArray() {
        return (ObjectType[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new ObjectType[0]);
    }

    public ObjectType getObjectArray(int i) {
        ObjectType objectType;
        synchronized (monitor()) {
            check_orphaned();
            objectType = (ObjectType) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (objectType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return objectType;
    }

    public int sizeOfObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setObjectArray(ObjectType[] objectTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) objectTypeArr, PROPERTY_QNAME[3]);
    }

    public void setObjectArray(int i, ObjectType objectType) {
        generatedSetterHelperImpl(objectType, PROPERTY_QNAME[3], i, 2);
    }

    public ObjectType insertNewObject(int i) {
        ObjectType objectType;
        synchronized (monitor()) {
            check_orphaned();
            objectType = (ObjectType) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return objectType;
    }

    public ObjectType addNewObject() {
        ObjectType objectType;
        synchronized (monitor()) {
            check_orphaned();
            objectType = (ObjectType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return objectType;
    }

    public void removeObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public String getId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
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
            xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return xmlID;
    }

    public boolean isSetId() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
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
            r3 = 4
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
        throw new UnsupportedOperationException("Method not decompiled: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl.setId(java.lang.String):void");
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
            r3 = 4
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
        throw new UnsupportedOperationException("Method not decompiled: org.w3.x2000.x09.xmldsig.impl.SignatureTypeImpl.xsetId(org.apache.xmlbeans.XmlID):void");
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
