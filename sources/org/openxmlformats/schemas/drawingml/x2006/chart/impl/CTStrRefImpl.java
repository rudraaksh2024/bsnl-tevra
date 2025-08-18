package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;

public class CTStrRefImpl extends XmlComplexContentImpl implements CTStrRef {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "f"), new QName(XSSFRelation.NS_CHART, "strCache"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTStrRefImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public String getF() {
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

    public XmlString xgetF() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return xmlString;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setF(java.lang.String r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTStrRefImpl.setF(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetF(org.apache.xmlbeans.XmlString r6) {
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
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_element_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTStrRefImpl.xsetF(org.apache.xmlbeans.XmlString):void");
    }

    public CTStrData getStrCache() {
        CTStrData cTStrData;
        synchronized (monitor()) {
            check_orphaned();
            cTStrData = (CTStrData) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTStrData == null) {
                cTStrData = null;
            }
        }
        return cTStrData;
    }

    public boolean isSetStrCache() {
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

    public void setStrCache(CTStrData cTStrData) {
        generatedSetterHelperImpl(cTStrData, PROPERTY_QNAME[1], 0, 1);
    }

    public CTStrData addNewStrCache() {
        CTStrData cTStrData;
        synchronized (monitor()) {
            check_orphaned();
            cTStrData = (CTStrData) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTStrData;
    }

    public void unsetStrCache() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[2], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }
}
