package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticPr;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPhoneticRun;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;

public class CTRstImpl extends XmlComplexContentImpl implements CTRst {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "t"), new QName(XSSFRelation.NS_SPREADSHEETML, "r"), new QName(XSSFRelation.NS_SPREADSHEETML, "rPh"), new QName(XSSFRelation.NS_SPREADSHEETML, "phoneticPr")};
    private static final long serialVersionUID = 1;

    public CTRstImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public String getT() {
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

    public STXstring xgetT() {
        STXstring sTXstring;
        synchronized (monitor()) {
            check_orphaned();
            sTXstring = (STXstring) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return sTXstring;
    }

    public boolean isSetT() {
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

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setT(java.lang.String r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl.setT(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetT(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring r6) {
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
            org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring r1 = (org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_element_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring r1 = (org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTRstImpl.xsetT(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring):void");
    }

    public void unsetT() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTRElt> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRstImpl$$ExternalSyntheticLambda5(this), new CTRstImpl$$ExternalSyntheticLambda6(this), new CTRstImpl$$ExternalSyntheticLambda7(this), new CTRstImpl$$ExternalSyntheticLambda8(this), new CTRstImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTRElt[] getRArray() {
        return (CTRElt[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTRElt[0]);
    }

    public CTRElt getRArray(int i) {
        CTRElt cTRElt;
        synchronized (monitor()) {
            check_orphaned();
            cTRElt = (CTRElt) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTRElt == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRElt;
    }

    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setRArray(CTRElt[] cTREltArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTREltArr, PROPERTY_QNAME[1]);
    }

    public void setRArray(int i, CTRElt cTRElt) {
        generatedSetterHelperImpl(cTRElt, PROPERTY_QNAME[1], i, 2);
    }

    public CTRElt insertNewR(int i) {
        CTRElt cTRElt;
        synchronized (monitor()) {
            check_orphaned();
            cTRElt = (CTRElt) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTRElt;
    }

    public CTRElt addNewR() {
        CTRElt cTRElt;
        synchronized (monitor()) {
            check_orphaned();
            cTRElt = (CTRElt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTRElt;
    }

    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTPhoneticRun> getRPhList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRstImpl$$ExternalSyntheticLambda0(this), new CTRstImpl$$ExternalSyntheticLambda1(this), new CTRstImpl$$ExternalSyntheticLambda2(this), new CTRstImpl$$ExternalSyntheticLambda3(this), new CTRstImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTPhoneticRun[] getRPhArray() {
        return (CTPhoneticRun[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTPhoneticRun[0]);
    }

    public CTPhoneticRun getRPhArray(int i) {
        CTPhoneticRun cTPhoneticRun;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticRun = (CTPhoneticRun) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTPhoneticRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPhoneticRun;
    }

    public int sizeOfRPhArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setRPhArray(CTPhoneticRun[] cTPhoneticRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPhoneticRunArr, PROPERTY_QNAME[2]);
    }

    public void setRPhArray(int i, CTPhoneticRun cTPhoneticRun) {
        generatedSetterHelperImpl(cTPhoneticRun, PROPERTY_QNAME[2], i, 2);
    }

    public CTPhoneticRun insertNewRPh(int i) {
        CTPhoneticRun cTPhoneticRun;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticRun = (CTPhoneticRun) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTPhoneticRun;
    }

    public CTPhoneticRun addNewRPh() {
        CTPhoneticRun cTPhoneticRun;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticRun = (CTPhoneticRun) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPhoneticRun;
    }

    public void removeRPh(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public CTPhoneticPr getPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticPr = (CTPhoneticPr) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTPhoneticPr == null) {
                cTPhoneticPr = null;
            }
        }
        return cTPhoneticPr;
    }

    public boolean isSetPhoneticPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setPhoneticPr(CTPhoneticPr cTPhoneticPr) {
        generatedSetterHelperImpl(cTPhoneticPr, PROPERTY_QNAME[3], 0, 1);
    }

    public CTPhoneticPr addNewPhoneticPr() {
        CTPhoneticPr cTPhoneticPr;
        synchronized (monitor()) {
            check_orphaned();
            cTPhoneticPr = (CTPhoneticPr) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTPhoneticPr;
    }

    public void unsetPhoneticPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
