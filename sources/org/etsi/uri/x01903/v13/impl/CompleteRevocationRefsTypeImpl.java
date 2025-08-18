package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.etsi.uri.x01903.v13.CRLRefsType;
import org.etsi.uri.x01903.v13.CompleteRevocationRefsType;
import org.etsi.uri.x01903.v13.OCSPRefsType;
import org.etsi.uri.x01903.v13.OtherCertStatusRefsType;

public class CompleteRevocationRefsTypeImpl extends XmlComplexContentImpl implements CompleteRevocationRefsType {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.XADES_132_NS, "CRLRefs"), new QName(SignatureFacet.XADES_132_NS, "OCSPRefs"), new QName(SignatureFacet.XADES_132_NS, "OtherRefs"), new QName("", PackageRelationship.ID_ATTRIBUTE_NAME)};
    private static final long serialVersionUID = 1;

    public CompleteRevocationRefsTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CRLRefsType getCRLRefs() {
        CRLRefsType cRLRefsType;
        synchronized (monitor()) {
            check_orphaned();
            cRLRefsType = (CRLRefsType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cRLRefsType == null) {
                cRLRefsType = null;
            }
        }
        return cRLRefsType;
    }

    public boolean isSetCRLRefs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setCRLRefs(CRLRefsType cRLRefsType) {
        generatedSetterHelperImpl(cRLRefsType, PROPERTY_QNAME[0], 0, 1);
    }

    public CRLRefsType addNewCRLRefs() {
        CRLRefsType cRLRefsType;
        synchronized (monitor()) {
            check_orphaned();
            cRLRefsType = (CRLRefsType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cRLRefsType;
    }

    public void unsetCRLRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public OCSPRefsType getOCSPRefs() {
        OCSPRefsType oCSPRefsType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPRefsType = (OCSPRefsType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (oCSPRefsType == null) {
                oCSPRefsType = null;
            }
        }
        return oCSPRefsType;
    }

    public boolean isSetOCSPRefs() {
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

    public void setOCSPRefs(OCSPRefsType oCSPRefsType) {
        generatedSetterHelperImpl(oCSPRefsType, PROPERTY_QNAME[1], 0, 1);
    }

    public OCSPRefsType addNewOCSPRefs() {
        OCSPRefsType oCSPRefsType;
        synchronized (monitor()) {
            check_orphaned();
            oCSPRefsType = (OCSPRefsType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return oCSPRefsType;
    }

    public void unsetOCSPRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public OtherCertStatusRefsType getOtherRefs() {
        OtherCertStatusRefsType find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetOtherRefs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setOtherRefs(OtherCertStatusRefsType otherCertStatusRefsType) {
        generatedSetterHelperImpl(otherCertStatusRefsType, PROPERTY_QNAME[2], 0, 1);
    }

    public OtherCertStatusRefsType addNewOtherRefs() {
        OtherCertStatusRefsType add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetOtherRefs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
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
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.CompleteRevocationRefsTypeImpl.setId(java.lang.String):void");
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
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.CompleteRevocationRefsTypeImpl.xsetId(org.apache.xmlbeans.XmlID):void");
    }

    public void unsetId() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }
}
