package com.microsoft.schemas.office.x2006.digsig.impl;

import com.microsoft.schemas.office.x2006.digsig.CTSignatureInfoV1;
import com.microsoft.schemas.office.x2006.digsig.STPositiveInteger;
import com.microsoft.schemas.office.x2006.digsig.STSignatureComments;
import com.microsoft.schemas.office.x2006.digsig.STSignatureProviderUrl;
import com.microsoft.schemas.office.x2006.digsig.STSignatureText;
import com.microsoft.schemas.office.x2006.digsig.STSignatureType;
import com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces;
import com.microsoft.schemas.office.x2006.digsig.STVersion;
import javax.xml.namespace.QName;
import org.apache.poi.poifs.crypt.dsig.facets.SignatureFacet;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlBase64Binary;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CTSignatureInfoV1Impl extends XmlComplexContentImpl implements CTSignatureInfoV1 {
    private static final QName[] PROPERTY_QNAME = {new QName(SignatureFacet.MS_DIGSIG_NS, "SetupID"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureText"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureImage"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureComments"), new QName(SignatureFacet.MS_DIGSIG_NS, "WindowsVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "OfficeVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "ApplicationVersion"), new QName(SignatureFacet.MS_DIGSIG_NS, "Monitors"), new QName(SignatureFacet.MS_DIGSIG_NS, "HorizontalResolution"), new QName(SignatureFacet.MS_DIGSIG_NS, "VerticalResolution"), new QName(SignatureFacet.MS_DIGSIG_NS, "ColorDepth"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderId"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderUrl"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureProviderDetails"), new QName(SignatureFacet.MS_DIGSIG_NS, "SignatureType"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSigner"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSigner2"), new QName(SignatureFacet.MS_DIGSIG_NS, "DelegateSuggestedSignerEmail"), new QName(SignatureFacet.MS_DIGSIG_NS, "ManifestHashAlgorithm")};
    private static final long serialVersionUID = 1;

    public CTSignatureInfoV1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    public String getSetupID() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STUniqueIdentifierWithBraces xgetSetupID() {
        STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces;
        synchronized (monitor()) {
            check_orphaned();
            sTUniqueIdentifierWithBraces = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return sTUniqueIdentifierWithBraces;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSetupID(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 0
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r3)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_element_user(r1)     // Catch:{ all -> 0x002b }
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
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSetupID(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSetupID(com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 0
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r3)     // Catch:{ all -> 0x002b }
            com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r1 = (com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_element_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r1 = (com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSetupID(com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces):void");
    }

    public String getSignatureText() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STSignatureText xgetSignatureText() {
        STSignatureText find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureText(java.lang.String r7) {
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
            r1.setStringValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureText(java.lang.String):void");
    }

    public void xsetSignatureText(STSignatureText sTSignatureText) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STSignatureText find_element_user = typeStore.find_element_user(qNameArr[1], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[1]);
            }
            find_element_user.set(sTSignatureText);
        }
    }

    public byte[] getSignatureImage() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public XmlBase64Binary xgetSignatureImage() {
        XmlBase64Binary xmlBase64Binary;
        synchronized (monitor()) {
            check_orphaned();
            xmlBase64Binary = (XmlBase64Binary) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return xmlBase64Binary;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureImage(byte[] r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 2
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
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureImage(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSignatureImage(org.apache.xmlbeans.XmlBase64Binary r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 2
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlBase64Binary r1 = (org.apache.xmlbeans.XmlBase64Binary) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.apache.xmlbeans.XmlBase64Binary r1 = (org.apache.xmlbeans.XmlBase64Binary) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSignatureImage(org.apache.xmlbeans.XmlBase64Binary):void");
    }

    public String getSignatureComments() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STSignatureComments xgetSignatureComments() {
        STSignatureComments sTSignatureComments;
        synchronized (monitor()) {
            check_orphaned();
            sTSignatureComments = (STSignatureComments) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return sTSignatureComments;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureComments(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 3
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
            r1.setStringValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureComments(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSignatureComments(com.microsoft.schemas.office.x2006.digsig.STSignatureComments r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 3
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002c }
            com.microsoft.schemas.office.x2006.digsig.STSignatureComments r1 = (com.microsoft.schemas.office.x2006.digsig.STSignatureComments) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            com.microsoft.schemas.office.x2006.digsig.STSignatureComments r1 = (com.microsoft.schemas.office.x2006.digsig.STSignatureComments) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSignatureComments(com.microsoft.schemas.office.x2006.digsig.STSignatureComments):void");
    }

    public String getWindowsVersion() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STVersion xgetWindowsVersion() {
        STVersion find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setWindowsVersion(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 4
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
            r1.setStringValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setWindowsVersion(java.lang.String):void");
    }

    public void xsetWindowsVersion(STVersion sTVersion) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STVersion find_element_user = typeStore.find_element_user(qNameArr[4], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[4]);
            }
            find_element_user.set(sTVersion);
        }
    }

    public String getOfficeVersion() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STVersion xgetOfficeVersion() {
        STVersion find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setOfficeVersion(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 5
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
            r1.setStringValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setOfficeVersion(java.lang.String):void");
    }

    public void xsetOfficeVersion(STVersion sTVersion) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STVersion find_element_user = typeStore.find_element_user(qNameArr[5], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[5]);
            }
            find_element_user.set(sTVersion);
        }
    }

    public String getApplicationVersion() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STVersion xgetApplicationVersion() {
        STVersion find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setApplicationVersion(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 6
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
            r1.setStringValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setApplicationVersion(java.lang.String):void");
    }

    public void xsetApplicationVersion(STVersion sTVersion) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STVersion find_element_user = typeStore.find_element_user(qNameArr[6], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[6]);
            }
            find_element_user.set(sTVersion);
        }
    }

    public int getMonitors() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STPositiveInteger xgetMonitors() {
        STPositiveInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMonitors(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 7
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
            r1.setIntValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setMonitors(int):void");
    }

    public void xsetMonitors(STPositiveInteger sTPositiveInteger) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STPositiveInteger find_element_user = typeStore.find_element_user(qNameArr[7], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[7]);
            }
            find_element_user.set(sTPositiveInteger);
        }
    }

    public int getHorizontalResolution() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STPositiveInteger xgetHorizontalResolution() {
        STPositiveInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setHorizontalResolution(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 8
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setIntValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setHorizontalResolution(int):void");
    }

    public void xsetHorizontalResolution(STPositiveInteger sTPositiveInteger) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STPositiveInteger find_element_user = typeStore.find_element_user(qNameArr[8], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[8]);
            }
            find_element_user.set(sTPositiveInteger);
        }
    }

    public int getVerticalResolution() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STPositiveInteger xgetVerticalResolution() {
        STPositiveInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setVerticalResolution(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 9
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setIntValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setVerticalResolution(int):void");
    }

    public void xsetVerticalResolution(STPositiveInteger sTPositiveInteger) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STPositiveInteger find_element_user = typeStore.find_element_user(qNameArr[9], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[9]);
            }
            find_element_user.set(sTPositiveInteger);
        }
    }

    public int getColorDepth() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STPositiveInteger xgetColorDepth() {
        STPositiveInteger find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setColorDepth(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 10
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setIntValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setColorDepth(int):void");
    }

    public void xsetColorDepth(STPositiveInteger sTPositiveInteger) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STPositiveInteger find_element_user = typeStore.find_element_user(qNameArr[10], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[10]);
            }
            find_element_user.set(sTPositiveInteger);
        }
    }

    public String getSignatureProviderId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STUniqueIdentifierWithBraces xgetSignatureProviderId() {
        STUniqueIdentifierWithBraces sTUniqueIdentifierWithBraces;
        synchronized (monitor()) {
            check_orphaned();
            sTUniqueIdentifierWithBraces = (STUniqueIdentifierWithBraces) get_store().find_element_user(PROPERTY_QNAME[11], 0);
        }
        return sTUniqueIdentifierWithBraces;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureProviderId(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 11
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureProviderId(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSignatureProviderId(com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 11
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r1 = (com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces r1 = (com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSignatureProviderId(com.microsoft.schemas.office.x2006.digsig.STUniqueIdentifierWithBraces):void");
    }

    public String getSignatureProviderUrl() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STSignatureProviderUrl xgetSignatureProviderUrl() {
        STSignatureProviderUrl find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[12], 0);
        }
        return find_element_user;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureProviderUrl(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 12
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureProviderUrl(java.lang.String):void");
    }

    public void xsetSignatureProviderUrl(STSignatureProviderUrl sTSignatureProviderUrl) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STSignatureProviderUrl find_element_user = typeStore.find_element_user(qNameArr[12], 0);
            if (find_element_user == null) {
                find_element_user = get_store().add_element_user(qNameArr[12]);
            }
            find_element_user.set(sTSignatureProviderUrl);
        }
    }

    public int getSignatureProviderDetails() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public XmlInt xgetSignatureProviderDetails() {
        XmlInt xmlInt;
        synchronized (monitor()) {
            check_orphaned();
            xmlInt = (XmlInt) get_store().find_element_user(PROPERTY_QNAME[13], 0);
        }
        return xmlInt;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureProviderDetails(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setIntValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureProviderDetails(int):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSignatureProviderDetails(org.apache.xmlbeans.XmlInt r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlInt r1 = (org.apache.xmlbeans.XmlInt) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlInt r1 = (org.apache.xmlbeans.XmlInt) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSignatureProviderDetails(org.apache.xmlbeans.XmlInt):void");
    }

    public int getSignatureType() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STSignatureType xgetSignatureType() {
        STSignatureType sTSignatureType;
        synchronized (monitor()) {
            check_orphaned();
            sTSignatureType = (STSignatureType) get_store().find_element_user(PROPERTY_QNAME[14], 0);
        }
        return sTSignatureType;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSignatureType(int r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 14
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setIntValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setSignatureType(int):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSignatureType(com.microsoft.schemas.office.x2006.digsig.STSignatureType r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 14
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            com.microsoft.schemas.office.x2006.digsig.STSignatureType r1 = (com.microsoft.schemas.office.x2006.digsig.STSignatureType) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            com.microsoft.schemas.office.x2006.digsig.STSignatureType r1 = (com.microsoft.schemas.office.x2006.digsig.STSignatureType) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetSignatureType(com.microsoft.schemas.office.x2006.digsig.STSignatureType):void");
    }

    public String getDelegateSuggestedSigner() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetDelegateSuggestedSigner() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_element_user(PROPERTY_QNAME[15], 0);
        }
        return xmlString;
    }

    public boolean isSetDelegateSuggestedSigner() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDelegateSuggestedSigner(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 15
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setDelegateSuggestedSigner(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetDelegateSuggestedSigner(org.apache.xmlbeans.XmlString r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 15
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetDelegateSuggestedSigner(org.apache.xmlbeans.XmlString):void");
    }

    public void unsetDelegateSuggestedSigner() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public String getDelegateSuggestedSigner2() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetDelegateSuggestedSigner2() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_element_user(PROPERTY_QNAME[16], 0);
        }
        return xmlString;
    }

    public boolean isSetDelegateSuggestedSigner2() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDelegateSuggestedSigner2(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 16
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setDelegateSuggestedSigner2(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetDelegateSuggestedSigner2(org.apache.xmlbeans.XmlString r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 16
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetDelegateSuggestedSigner2(org.apache.xmlbeans.XmlString):void");
    }

    public void unsetDelegateSuggestedSigner2() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }

    public String getDelegateSuggestedSignerEmail() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[17], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetDelegateSuggestedSignerEmail() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_element_user(PROPERTY_QNAME[17], 0);
        }
        return xmlString;
    }

    public boolean isSetDelegateSuggestedSignerEmail() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDelegateSuggestedSignerEmail(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 17
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setDelegateSuggestedSignerEmail(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetDelegateSuggestedSignerEmail(org.apache.xmlbeans.XmlString r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 17
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetDelegateSuggestedSignerEmail(org.apache.xmlbeans.XmlString):void");
    }

    public void unsetDelegateSuggestedSignerEmail() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    public String getManifestHashAlgorithm() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[18], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlAnyURI xgetManifestHashAlgorithm() {
        XmlAnyURI xmlAnyURI;
        synchronized (monitor()) {
            check_orphaned();
            xmlAnyURI = (XmlAnyURI) get_store().find_element_user(PROPERTY_QNAME[18], 0);
        }
        return xmlAnyURI;
    }

    public boolean isSetManifestHashAlgorithm() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[18]) != 0;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setManifestHashAlgorithm(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 18
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.setManifestHashAlgorithm(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetManifestHashAlgorithm(org.apache.xmlbeans.XmlAnyURI r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 18
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.x2006.digsig.impl.CTSignatureInfoV1Impl.xsetManifestHashAlgorithm(org.apache.xmlbeans.XmlAnyURI):void");
    }

    public void unsetManifestHashAlgorithm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], 0);
        }
    }
}
