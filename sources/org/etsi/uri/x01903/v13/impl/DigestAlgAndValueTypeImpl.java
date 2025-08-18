package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.DigestAlgAndValueType;
import org.w3.x2000.x09.xmldsig.DigestMethodType;
import org.w3.x2000.x09.xmldsig.DigestValueType;

public class DigestAlgAndValueTypeImpl extends XmlComplexContentImpl implements DigestAlgAndValueType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XML_DIGSIG_NS, "DigestMethod"), new QName(SignatureFacet.XML_DIGSIG_NS, "DigestValue")};
    private static final long serialVersionUID = 1;

    public DigestAlgAndValueTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DigestMethodType getDigestMethod() {
        DigestMethodType digestMethodType;
        synchronized (monitor()) {
            check_orphaned();
            digestMethodType = (DigestMethodType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (digestMethodType == null) {
                digestMethodType = null;
            }
        }
        return digestMethodType;
    }

    public void setDigestMethod(DigestMethodType digestMethodType) {
        generatedSetterHelperImpl(digestMethodType, PROPERTY_QNAME[0], 0, 1);
    }

    public DigestMethodType addNewDigestMethod() {
        DigestMethodType digestMethodType;
        synchronized (monitor()) {
            check_orphaned();
            digestMethodType = (DigestMethodType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return digestMethodType;
    }

    public byte[] getDigestValue() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public DigestValueType xgetDigestValue() {
        DigestValueType digestValueType;
        synchronized (monitor()) {
            check_orphaned();
            digestValueType = (DigestValueType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return digestValueType;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDigestValue(byte[] r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 1
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setByteArrayValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.DigestAlgAndValueTypeImpl.setDigestValue(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetDigestValue(org.w3.x2000.x09.xmldsig.DigestValueType r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 1
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002c }
            org.w3.x2000.x09.xmldsig.DigestValueType r1 = (org.w3.x2000.x09.xmldsig.DigestValueType) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.w3.x2000.x09.xmldsig.DigestValueType r1 = (org.w3.x2000.x09.xmldsig.DigestValueType) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.DigestAlgAndValueTypeImpl.xsetDigestValue(org.w3.x2000.x09.xmldsig.DigestValueType):void");
    }
}
