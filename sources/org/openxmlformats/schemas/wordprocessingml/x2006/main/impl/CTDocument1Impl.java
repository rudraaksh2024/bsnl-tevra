package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;

public class CTDocument1Impl extends CTDocumentBaseImpl implements CTDocument1 {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "body"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "conformance")};
    private static final long serialVersionUID = 1;

    public CTDocument1Impl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBody getBody() {
        CTBody cTBody;
        synchronized (monitor()) {
            check_orphaned();
            cTBody = (CTBody) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTBody == null) {
                cTBody = null;
            }
        }
        return cTBody;
    }

    public boolean isSetBody() {
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

    public void setBody(CTBody cTBody) {
        generatedSetterHelperImpl(cTBody, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBody addNewBody() {
        CTBody cTBody;
        synchronized (monitor()) {
            check_orphaned();
            cTBody = (CTBody) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTBody;
    }

    public void unsetBody() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public STConformanceClass$Enum getConformance() {
        STConformanceClass$Enum sTConformanceClass$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            if (simpleValue == null) {
                sTConformanceClass$Enum = null;
            } else {
                sTConformanceClass$Enum = (STConformanceClass$Enum) simpleValue.getEnumValue();
            }
        }
        return sTConformanceClass$Enum;
    }

    public STConformanceClass xgetConformance() {
        STConformanceClass find_attribute_user;
        synchronized (monitor()) {
            check_orphaned();
            find_attribute_user = get_store().find_attribute_user(PROPERTY_QNAME[1]);
        }
        return find_attribute_user;
    }

    public boolean isSetConformance() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().find_attribute_user(PROPERTY_QNAME[1]) == null) {
                z = false;
            }
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setConformance(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 1
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTDocument1Impl.setConformance(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STConformanceClass$Enum):void");
    }

    public void xsetConformance(STConformanceClass sTConformanceClass) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STConformanceClass find_attribute_user = typeStore.find_attribute_user(qNameArr[1]);
            if (find_attribute_user == null) {
                find_attribute_user = get_store().add_attribute_user(qNameArr[1]);
            }
            find_attribute_user.set(sTConformanceClass);
        }
    }

    public void unsetConformance() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[1]);
        }
    }
}
