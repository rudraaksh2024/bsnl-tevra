package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import com.microsoft.schemas.office.visio.x2012.main.SectionType;
import com.microsoft.schemas.office.visio.x2012.main.SheetType;
import com.microsoft.schemas.office.visio.x2012.main.TriggerType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class SheetTypeImpl extends XmlComplexContentImpl implements SheetType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Cell"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Trigger"), new QName("http://schemas.microsoft.com/office/visio/2012/main", "Section"), new QName("", "LineStyle"), new QName("", "FillStyle"), new QName("", "TextStyle")};
    private static final long serialVersionUID = 1;

    public SheetTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CellType> getCellList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SheetTypeImpl$$ExternalSyntheticLambda10(this), new SheetTypeImpl$$ExternalSyntheticLambda11(this), new SheetTypeImpl$$ExternalSyntheticLambda12(this), new SheetTypeImpl$$ExternalSyntheticLambda13(this), new SheetTypeImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public CellType[] getCellArray() {
        return (CellType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CellType[0]);
    }

    public CellType getCellArray(int i) {
        CellType cellType;
        synchronized (monitor()) {
            check_orphaned();
            cellType = (CellType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cellType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cellType;
    }

    public int sizeOfCellArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCellArray(CellType[] cellTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cellTypeArr, PROPERTY_QNAME[0]);
    }

    public void setCellArray(int i, CellType cellType) {
        generatedSetterHelperImpl(cellType, PROPERTY_QNAME[0], i, 2);
    }

    public CellType insertNewCell(int i) {
        CellType cellType;
        synchronized (monitor()) {
            check_orphaned();
            cellType = (CellType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cellType;
    }

    public CellType addNewCell() {
        CellType cellType;
        synchronized (monitor()) {
            check_orphaned();
            cellType = (CellType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cellType;
    }

    public void removeCell(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<TriggerType> getTriggerList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SheetTypeImpl$$ExternalSyntheticLambda0(this), new SheetTypeImpl$$ExternalSyntheticLambda6(this), new SheetTypeImpl$$ExternalSyntheticLambda7(this), new SheetTypeImpl$$ExternalSyntheticLambda8(this), new SheetTypeImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public TriggerType[] getTriggerArray() {
        return (TriggerType[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new TriggerType[0]);
    }

    public TriggerType getTriggerArray(int i) {
        TriggerType triggerType;
        synchronized (monitor()) {
            check_orphaned();
            triggerType = (TriggerType) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (triggerType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return triggerType;
    }

    public int sizeOfTriggerArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setTriggerArray(TriggerType[] triggerTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) triggerTypeArr, PROPERTY_QNAME[1]);
    }

    public void setTriggerArray(int i, TriggerType triggerType) {
        generatedSetterHelperImpl(triggerType, PROPERTY_QNAME[1], i, 2);
    }

    public TriggerType insertNewTrigger(int i) {
        TriggerType triggerType;
        synchronized (monitor()) {
            check_orphaned();
            triggerType = (TriggerType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return triggerType;
    }

    public TriggerType addNewTrigger() {
        TriggerType triggerType;
        synchronized (monitor()) {
            check_orphaned();
            triggerType = (TriggerType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return triggerType;
    }

    public void removeTrigger(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<SectionType> getSectionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new SheetTypeImpl$$ExternalSyntheticLambda1(this), new SheetTypeImpl$$ExternalSyntheticLambda2(this), new SheetTypeImpl$$ExternalSyntheticLambda3(this), new SheetTypeImpl$$ExternalSyntheticLambda4(this), new SheetTypeImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public SectionType[] getSectionArray() {
        return (SectionType[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new SectionType[0]);
    }

    public SectionType getSectionArray(int i) {
        SectionType sectionType;
        synchronized (monitor()) {
            check_orphaned();
            sectionType = (SectionType) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (sectionType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return sectionType;
    }

    public int sizeOfSectionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSectionArray(SectionType[] sectionTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) sectionTypeArr, PROPERTY_QNAME[2]);
    }

    public void setSectionArray(int i, SectionType sectionType) {
        generatedSetterHelperImpl(sectionType, PROPERTY_QNAME[2], i, 2);
    }

    public SectionType insertNewSection(int i) {
        SectionType sectionType;
        synchronized (monitor()) {
            check_orphaned();
            sectionType = (SectionType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return sectionType;
    }

    public SectionType addNewSection() {
        SectionType sectionType;
        synchronized (monitor()) {
            check_orphaned();
            sectionType = (SectionType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return sectionType;
    }

    public void removeSection(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public long getLineStyle() {
        long j;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[3]);
            if (simpleValue == null) {
                j = 0;
            } else {
                j = simpleValue.getLongValue();
            }
        }
        return j;
    }

    public XmlUnsignedInt xgetLineStyle() {
        XmlUnsignedInt xmlUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            xmlUnsignedInt = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[3]);
        }
        return xmlUnsignedInt;
    }

    public boolean isSetLineStyle() {
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
    public void setLineStyle(long r6) {
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
            r1.setLongValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.setLineStyle(long):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetLineStyle(org.apache.xmlbeans.XmlUnsignedInt r6) {
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
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.xsetLineStyle(org.apache.xmlbeans.XmlUnsignedInt):void");
    }

    public void unsetLineStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[3]);
        }
    }

    public long getFillStyle() {
        long j;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[4]);
            if (simpleValue == null) {
                j = 0;
            } else {
                j = simpleValue.getLongValue();
            }
        }
        return j;
    }

    public XmlUnsignedInt xgetFillStyle() {
        XmlUnsignedInt xmlUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            xmlUnsignedInt = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[4]);
        }
        return xmlUnsignedInt;
    }

    public boolean isSetFillStyle() {
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
    public void setFillStyle(long r6) {
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
            r1.setLongValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.setFillStyle(long):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetFillStyle(org.apache.xmlbeans.XmlUnsignedInt r6) {
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
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.xsetFillStyle(org.apache.xmlbeans.XmlUnsignedInt):void");
    }

    public void unsetFillStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    public long getTextStyle() {
        long j;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (simpleValue == null) {
                j = 0;
            } else {
                j = simpleValue.getLongValue();
            }
        }
        return j;
    }

    public XmlUnsignedInt xgetTextStyle() {
        XmlUnsignedInt xmlUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            xmlUnsignedInt = (XmlUnsignedInt) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return xmlUnsignedInt;
    }

    public boolean isSetTextStyle() {
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
    public void setTextStyle(long r6) {
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
            r1.setLongValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.setTextStyle(long):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetTextStyle(org.apache.xmlbeans.XmlUnsignedInt r6) {
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
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlUnsignedInt r1 = (org.apache.xmlbeans.XmlUnsignedInt) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.schemas.office.visio.x2012.main.impl.SheetTypeImpl.xsetTextStyle(org.apache.xmlbeans.XmlUnsignedInt):void");
    }

    public void unsetTextStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
