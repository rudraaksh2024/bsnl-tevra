package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeCondition;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeTargetElement;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerRuntimeNode;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTriggerTimeNodeID;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTime;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent$Enum;

public class CTTLTimeConditionImpl extends XmlComplexContentImpl implements CTTLTimeCondition {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "tgtEl"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "tn"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "rtn"), new QName("", "evt"), new QName("", "delay")};
    private static final long serialVersionUID = 1;

    public CTTLTimeConditionImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTLTimeTargetElement getTgtEl() {
        CTTLTimeTargetElement cTTLTimeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeTargetElement = (CTTLTimeTargetElement) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTLTimeTargetElement == null) {
                cTTLTimeTargetElement = null;
            }
        }
        return cTTLTimeTargetElement;
    }

    public boolean isSetTgtEl() {
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

    public void setTgtEl(CTTLTimeTargetElement cTTLTimeTargetElement) {
        generatedSetterHelperImpl(cTTLTimeTargetElement, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTLTimeTargetElement addNewTgtEl() {
        CTTLTimeTargetElement cTTLTimeTargetElement;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeTargetElement = (CTTLTimeTargetElement) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTLTimeTargetElement;
    }

    public void unsetTgtEl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTLTriggerTimeNodeID getTn() {
        CTTLTriggerTimeNodeID find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTn() {
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

    public void setTn(CTTLTriggerTimeNodeID cTTLTriggerTimeNodeID) {
        generatedSetterHelperImpl(cTTLTriggerTimeNodeID, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTLTriggerTimeNodeID addNewTn() {
        CTTLTriggerTimeNodeID add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetTn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTLTriggerRuntimeNode getRtn() {
        CTTLTriggerRuntimeNode find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetRtn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setRtn(CTTLTriggerRuntimeNode cTTLTriggerRuntimeNode) {
        generatedSetterHelperImpl(cTTLTriggerRuntimeNode, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTLTriggerRuntimeNode addNewRtn() {
        CTTLTriggerRuntimeNode add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetRtn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public STTLTriggerEvent$Enum getEvt() {
        STTLTriggerEvent$Enum sTTLTriggerEvent$Enum;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (simpleValue == null) {
                sTTLTriggerEvent$Enum = null;
            } else {
                sTTLTriggerEvent$Enum = (STTLTriggerEvent$Enum) simpleValue.getEnumValue();
            }
        }
        return sTTLTriggerEvent$Enum;
    }

    public STTLTriggerEvent xgetEvt() {
        STTLTriggerEvent find_attribute_user;
        synchronized (monitor()) {
            check_orphaned();
            find_attribute_user = get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return find_attribute_user;
    }

    public boolean isSetEvt() {
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
    public void setEvt(org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent$Enum r6) {
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
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionImpl.setEvt(org.openxmlformats.schemas.presentationml.x2006.main.STTLTriggerEvent$Enum):void");
    }

    public void xsetEvt(STTLTriggerEvent sTTLTriggerEvent) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STTLTriggerEvent find_attribute_user = typeStore.find_attribute_user(qNameArr[3]);
            if (find_attribute_user == null) {
                find_attribute_user = get_store().add_attribute_user(qNameArr[3]);
            }
            find_attribute_user.set(sTTLTriggerEvent);
        }
    }

    public void unsetEvt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    public Object getDelay() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STTLTime xgetDelay() {
        STTLTime sTTLTime;
        synchronized (monitor()) {
            check_orphaned();
            sTTLTime = (STTLTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return sTTLTime;
    }

    public boolean isSetDelay() {
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
    public void setDelay(java.lang.Object r6) {
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
            r1.setObjectValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionImpl.setDelay(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetDelay(org.openxmlformats.schemas.presentationml.x2006.main.STTLTime r6) {
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
            org.openxmlformats.schemas.presentationml.x2006.main.STTLTime r1 = (org.openxmlformats.schemas.presentationml.x2006.main.STTLTime) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.presentationml.x2006.main.STTLTime r1 = (org.openxmlformats.schemas.presentationml.x2006.main.STTLTime) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTTLTimeConditionImpl.xsetDelay(org.openxmlformats.schemas.presentationml.x2006.main.STTLTime):void");
    }

    public void unsetDelay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
