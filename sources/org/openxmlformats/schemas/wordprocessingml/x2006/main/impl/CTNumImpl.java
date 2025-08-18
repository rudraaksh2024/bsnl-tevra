package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.math.BigInteger;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber;

public class CTNumImpl extends XmlComplexContentImpl implements CTNum {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "abstractNumId"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lvlOverride"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "numId")};
    private static final long serialVersionUID = 1;

    public CTNumImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTDecimalNumber getAbstractNumId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public void setAbstractNumId(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[0], 0, 1);
    }

    public CTDecimalNumber addNewAbstractNumId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTDecimalNumber;
    }

    public List<CTNumLvl> getLvlOverrideList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTNumImpl$$ExternalSyntheticLambda0(this), new CTNumImpl$$ExternalSyntheticLambda1(this), new CTNumImpl$$ExternalSyntheticLambda2(this), new CTNumImpl$$ExternalSyntheticLambda3(this), new CTNumImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTNumLvl[] getLvlOverrideArray() {
        return (CTNumLvl[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTNumLvl[0]);
    }

    public CTNumLvl getLvlOverrideArray(int i) {
        CTNumLvl cTNumLvl;
        synchronized (monitor()) {
            check_orphaned();
            cTNumLvl = (CTNumLvl) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTNumLvl == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTNumLvl;
    }

    public int sizeOfLvlOverrideArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setLvlOverrideArray(CTNumLvl[] cTNumLvlArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTNumLvlArr, PROPERTY_QNAME[1]);
    }

    public void setLvlOverrideArray(int i, CTNumLvl cTNumLvl) {
        generatedSetterHelperImpl(cTNumLvl, PROPERTY_QNAME[1], i, 2);
    }

    public CTNumLvl insertNewLvlOverride(int i) {
        CTNumLvl cTNumLvl;
        synchronized (monitor()) {
            check_orphaned();
            cTNumLvl = (CTNumLvl) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTNumLvl;
    }

    public CTNumLvl addNewLvlOverride() {
        CTNumLvl cTNumLvl;
        synchronized (monitor()) {
            check_orphaned();
            cTNumLvl = (CTNumLvl) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNumLvl;
    }

    public void removeLvlOverride(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public BigInteger getNumId() {
        BigInteger bigInteger;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (simpleValue == null) {
                bigInteger = null;
            } else {
                bigInteger = simpleValue.getBigIntegerValue();
            }
        }
        return bigInteger;
    }

    public STDecimalNumber xgetNumId() {
        STDecimalNumber sTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTDecimalNumber = (STDecimalNumber) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return sTDecimalNumber;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setNumId(java.math.BigInteger r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 2
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
            r1.setBigIntegerValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTNumImpl.setNumId(java.math.BigInteger):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetNumId(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 2
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTNumImpl.xsetNumId(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDecimalNumber):void");
    }
}
