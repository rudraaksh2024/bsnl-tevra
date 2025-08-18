package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.Calendar;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCalendarType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLang;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtDateMappingType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime;

public class CTSdtDateImpl extends XmlComplexContentImpl implements CTSdtDate {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dateFormat"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lid"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "storeMappedDataAs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "calendar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fullDate")};
    private static final long serialVersionUID = 1;

    public CTSdtDateImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTString getDateFormat() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetDateFormat() {
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

    public void setDateFormat(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], 0, 1);
    }

    public CTString addNewDateFormat() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void unsetDateFormat() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTLang getLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            cTLang = (CTLang) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTLang == null) {
                cTLang = null;
            }
        }
        return cTLang;
    }

    public boolean isSetLid() {
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

    public void setLid(CTLang cTLang) {
        generatedSetterHelperImpl(cTLang, PROPERTY_QNAME[1], 0, 1);
    }

    public CTLang addNewLid() {
        CTLang cTLang;
        synchronized (monitor()) {
            check_orphaned();
            cTLang = (CTLang) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTLang;
    }

    public void unsetLid() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTSdtDateMappingType getStoreMappedDataAs() {
        CTSdtDateMappingType cTSdtDateMappingType;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDateMappingType = (CTSdtDateMappingType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTSdtDateMappingType == null) {
                cTSdtDateMappingType = null;
            }
        }
        return cTSdtDateMappingType;
    }

    public boolean isSetStoreMappedDataAs() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setStoreMappedDataAs(CTSdtDateMappingType cTSdtDateMappingType) {
        generatedSetterHelperImpl(cTSdtDateMappingType, PROPERTY_QNAME[2], 0, 1);
    }

    public CTSdtDateMappingType addNewStoreMappedDataAs() {
        CTSdtDateMappingType cTSdtDateMappingType;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtDateMappingType = (CTSdtDateMappingType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSdtDateMappingType;
    }

    public void unsetStoreMappedDataAs() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTCalendarType getCalendar() {
        CTCalendarType cTCalendarType;
        synchronized (monitor()) {
            check_orphaned();
            cTCalendarType = (CTCalendarType) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTCalendarType == null) {
                cTCalendarType = null;
            }
        }
        return cTCalendarType;
    }

    public boolean isSetCalendar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCalendar(CTCalendarType cTCalendarType) {
        generatedSetterHelperImpl(cTCalendarType, PROPERTY_QNAME[3], 0, 1);
    }

    public CTCalendarType addNewCalendar() {
        CTCalendarType cTCalendarType;
        synchronized (monitor()) {
            check_orphaned();
            cTCalendarType = (CTCalendarType) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTCalendarType;
    }

    public void unsetCalendar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public Calendar getFullDate() {
        Calendar calendar;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (simpleValue == null) {
                calendar = null;
            } else {
                calendar = simpleValue.getCalendarValue();
            }
        }
        return calendar;
    }

    public STDateTime xgetFullDate() {
        STDateTime sTDateTime;
        synchronized (monitor()) {
            check_orphaned();
            sTDateTime = (STDateTime) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return sTDateTime;
    }

    public boolean isSetFullDate() {
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
    public void setFullDate(java.util.Calendar r6) {
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
            r1.setCalendarValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDateImpl.setFullDate(java.util.Calendar):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetFullDate(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime r6) {
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
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTSdtDateImpl.xsetFullDate(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDateTime):void");
    }

    public void unsetFullDate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }
}
