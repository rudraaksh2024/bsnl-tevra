package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID;

public class CTMarkerImpl extends XmlComplexContentImpl implements CTMarker {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "col"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "colOff"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "row"), new QName("http://schemas.openxmlformats.org/drawingml/2006/spreadsheetDrawing", "rowOff")};
    private static final long serialVersionUID = 1;

    public CTMarkerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public int getCol() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STColID xgetCol() {
        STColID sTColID;
        synchronized (monitor()) {
            check_orphaned();
            sTColID = (STColID) get_store().find_element_user(PROPERTY_QNAME[0], 0);
        }
        return sTColID;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCol(int r6) {
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
            r1.setIntValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.setCol(int):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetCol(org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID r6) {
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
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID r1 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_element_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID r1 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.xsetCol(org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STColID):void");
    }

    public Object getColOff() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STCoordinate xgetColOff() {
        STCoordinate sTCoordinate;
        synchronized (monitor()) {
            check_orphaned();
            sTCoordinate = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[1], 0);
        }
        return sTCoordinate;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setColOff(java.lang.Object r7) {
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
            r1.setObjectValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.setColOff(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetColOff(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r7) {
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
            org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.xsetColOff(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate):void");
    }

    public int getRow() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            i = 0;
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (simpleValue != null) {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STRowID xgetRow() {
        STRowID sTRowID;
        synchronized (monitor()) {
            check_orphaned();
            sTRowID = (STRowID) get_store().find_element_user(PROPERTY_QNAME[2], 0);
        }
        return sTRowID;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRow(int r7) {
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
            r1.setIntValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.setRow(int):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRow(org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID r7) {
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
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID r1 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID r1 = (org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.xsetRow(org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STRowID):void");
    }

    public Object getRowOff() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STCoordinate xgetRowOff() {
        STCoordinate sTCoordinate;
        synchronized (monitor()) {
            check_orphaned();
            sTCoordinate = (STCoordinate) get_store().find_element_user(PROPERTY_QNAME[3], 0);
        }
        return sTCoordinate;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRowOff(java.lang.Object r7) {
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
            r1.setObjectValue(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.setRowOff(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRowOff(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r7) {
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
            org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r6
            org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r7)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.impl.CTMarkerImpl.xsetRowOff(org.openxmlformats.schemas.drawingml.x2006.main.STCoordinate):void");
    }
}
