package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorMappingOverride;
import org.openxmlformats.schemas.presentationml.x2006.main.CTCommonSlideData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTExtensionListModify;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlide;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTiming;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideTransition;

public class CTSlideImpl extends XmlComplexContentImpl implements CTSlide {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cSld"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "clrMapOvr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "transition"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "timing"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "extLst"), new QName("", "showMasterSp"), new QName("", "showMasterPhAnim"), new QName("", "show")};
    private static final long serialVersionUID = 1;

    public CTSlideImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCommonSlideData getCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            cTCommonSlideData = (CTCommonSlideData) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCommonSlideData == null) {
                cTCommonSlideData = null;
            }
        }
        return cTCommonSlideData;
    }

    public void setCSld(CTCommonSlideData cTCommonSlideData) {
        generatedSetterHelperImpl(cTCommonSlideData, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCommonSlideData addNewCSld() {
        CTCommonSlideData cTCommonSlideData;
        synchronized (monitor()) {
            check_orphaned();
            cTCommonSlideData = (CTCommonSlideData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCommonSlideData;
    }

    public CTColorMappingOverride getClrMapOvr() {
        CTColorMappingOverride cTColorMappingOverride;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMappingOverride = (CTColorMappingOverride) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTColorMappingOverride == null) {
                cTColorMappingOverride = null;
            }
        }
        return cTColorMappingOverride;
    }

    public boolean isSetClrMapOvr() {
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

    public void setClrMapOvr(CTColorMappingOverride cTColorMappingOverride) {
        generatedSetterHelperImpl(cTColorMappingOverride, PROPERTY_QNAME[1], 0, 1);
    }

    public CTColorMappingOverride addNewClrMapOvr() {
        CTColorMappingOverride cTColorMappingOverride;
        synchronized (monitor()) {
            check_orphaned();
            cTColorMappingOverride = (CTColorMappingOverride) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTColorMappingOverride;
    }

    public void unsetClrMapOvr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSlideTransition getTransition() {
        CTSlideTransition find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTransition() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setTransition(CTSlideTransition cTSlideTransition) {
        generatedSetterHelperImpl(cTSlideTransition, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSlideTransition addNewTransition() {
        CTSlideTransition add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetTransition() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTSlideTiming getTiming() {
        CTSlideTiming cTSlideTiming;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideTiming = (CTSlideTiming) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTSlideTiming == null) {
                cTSlideTiming = null;
            }
        }
        return cTSlideTiming;
    }

    public boolean isSetTiming() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setTiming(CTSlideTiming cTSlideTiming) {
        generatedSetterHelperImpl(cTSlideTiming, PROPERTY_QNAME[3], 0, 1);
    }

    public CTSlideTiming addNewTiming() {
        CTSlideTiming cTSlideTiming;
        synchronized (monitor()) {
            check_orphaned();
            cTSlideTiming = (CTSlideTiming) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTSlideTiming;
    }

    public void unsetTiming() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTExtensionListModify getExtLst() {
        CTExtensionListModify find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionListModify cTExtensionListModify) {
        generatedSetterHelperImpl(cTExtensionListModify, PROPERTY_QNAME[4], 0, 1);
    }

    public CTExtensionListModify addNewExtLst() {
        CTExtensionListModify add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getShowMasterSp() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 5
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0022:
            if (r1 != 0) goto L_0x0026
            r5 = 0
            goto L_0x002a
        L_0x0026:
            boolean r5 = r1.getBooleanValue()     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r5
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.getShowMasterSp():boolean");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlBoolean xgetShowMasterSp() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 5
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xgetShowMasterSp():org.apache.xmlbeans.XmlBoolean");
    }

    public boolean isSetShowMasterSp() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setShowMasterSp(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 5
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
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.setShowMasterSp(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetShowMasterSp(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 5
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xsetShowMasterSp(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetShowMasterSp() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getShowMasterPhAnim() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0022:
            if (r1 != 0) goto L_0x0026
            r5 = 0
            goto L_0x002a
        L_0x0026:
            boolean r5 = r1.getBooleanValue()     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r5
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.getShowMasterPhAnim():boolean");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlBoolean xgetShowMasterPhAnim() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xgetShowMasterPhAnim():org.apache.xmlbeans.XmlBoolean");
    }

    public boolean isSetShowMasterPhAnim() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setShowMasterPhAnim(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
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
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.setShowMasterPhAnim(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetShowMasterPhAnim(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 6
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xsetShowMasterPhAnim(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetShowMasterPhAnim() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[6]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getShow() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0022:
            if (r1 != 0) goto L_0x0026
            r5 = 0
            goto L_0x002a
        L_0x0026:
            boolean r5 = r1.getBooleanValue()     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return r5
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.getShow():boolean");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.xmlbeans.XmlBoolean xgetShow() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xgetShow():org.apache.xmlbeans.XmlBoolean");
    }

    public boolean isSetShow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[7]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setShow(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
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
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.setShow(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetShow(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 7
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.presentationml.x2006.main.impl.CTSlideImpl.xsetShow(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetShow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[7]);
        }
    }
}
