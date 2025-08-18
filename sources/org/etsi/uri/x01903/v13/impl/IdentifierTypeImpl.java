package org.etsi.uri.x01903.v13.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaUriHolderEx;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.etsi.uri.x01903.v13.IdentifierType;
import org.etsi.uri.x01903.v13.QualifierType;
import org.etsi.uri.x01903.v13.QualifierType$Enum;

public class IdentifierTypeImpl extends JavaUriHolderEx implements IdentifierType {
    private static final QName[] PROPERTY_QNAME = {new QName("", "Qualifier")};
    private static final long serialVersionUID = 1;

    public IdentifierTypeImpl(SchemaType schemaType) {
        super(schemaType, true);
    }

    protected IdentifierTypeImpl(SchemaType schemaType, boolean z) {
        super(schemaType, z);
    }

    public QualifierType$Enum getQualifier() {
        QualifierType$Enum qualifierType$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            if (simpleValue == null) {
                qualifierType$Enum = null;
            } else {
                qualifierType$Enum = (QualifierType$Enum) simpleValue.getEnumValue();
            }
        }
        return qualifierType$Enum;
    }

    public QualifierType xgetQualifier() {
        QualifierType find_attribute_user;
        synchronized (monitor()) {
            check_orphaned();
            find_attribute_user = get_store().find_attribute_user(PROPERTY_QNAME[0]);
        }
        return find_attribute_user;
    }

    public boolean isSetQualifier() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().find_attribute_user(PROPERTY_QNAME[0]) != null) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setQualifier(org.etsi.uri.x01903.v13.QualifierType$Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 0
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
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.etsi.uri.x01903.v13.impl.IdentifierTypeImpl.setQualifier(org.etsi.uri.x01903.v13.QualifierType$Enum):void");
    }

    public void xsetQualifier(QualifierType qualifierType) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            QualifierType find_attribute_user = typeStore.find_attribute_user(qNameArr[0]);
            if (find_attribute_user == null) {
                find_attribute_user = get_store().add_attribute_user(qNameArr[0]);
            }
            find_attribute_user.set(qualifierType);
        }
    }

    public void unsetQualifier() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[0]);
        }
    }
}
