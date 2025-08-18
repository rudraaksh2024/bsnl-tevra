package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMap;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMapInfo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSchema;

public class CTMapInfoImpl extends XmlComplexContentImpl implements CTMapInfo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "Schema"), new QName(XSSFRelation.NS_SPREADSHEETML, "Map"), new QName("", "SelectionNamespaces")};
    private static final long serialVersionUID = 1;

    public CTMapInfoImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSchema> getSchemaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTMapInfoImpl$$ExternalSyntheticLambda5(this), new CTMapInfoImpl$$ExternalSyntheticLambda6(this), new CTMapInfoImpl$$ExternalSyntheticLambda7(this), new CTMapInfoImpl$$ExternalSyntheticLambda8(this), new CTMapInfoImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTSchema[] getSchemaArray() {
        return (CTSchema[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSchema[0]);
    }

    public CTSchema getSchemaArray(int i) {
        CTSchema cTSchema;
        synchronized (monitor()) {
            check_orphaned();
            cTSchema = (CTSchema) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSchema == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSchema;
    }

    public int sizeOfSchemaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSchemaArray(CTSchema[] cTSchemaArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSchemaArr, PROPERTY_QNAME[0]);
    }

    public void setSchemaArray(int i, CTSchema cTSchema) {
        generatedSetterHelperImpl(cTSchema, PROPERTY_QNAME[0], i, 2);
    }

    public CTSchema insertNewSchema(int i) {
        CTSchema cTSchema;
        synchronized (monitor()) {
            check_orphaned();
            cTSchema = (CTSchema) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSchema;
    }

    public CTSchema addNewSchema() {
        CTSchema cTSchema;
        synchronized (monitor()) {
            check_orphaned();
            cTSchema = (CTSchema) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSchema;
    }

    public void removeSchema(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTMap> getMapList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTMapInfoImpl$$ExternalSyntheticLambda0(this), new CTMapInfoImpl$$ExternalSyntheticLambda1(this), new CTMapInfoImpl$$ExternalSyntheticLambda2(this), new CTMapInfoImpl$$ExternalSyntheticLambda3(this), new CTMapInfoImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTMap[] getMapArray() {
        return (CTMap[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTMap[0]);
    }

    public CTMap getMapArray(int i) {
        CTMap cTMap;
        synchronized (monitor()) {
            check_orphaned();
            cTMap = (CTMap) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTMap == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMap;
    }

    public int sizeOfMapArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setMapArray(CTMap[] cTMapArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMapArr, PROPERTY_QNAME[1]);
    }

    public void setMapArray(int i, CTMap cTMap) {
        generatedSetterHelperImpl(cTMap, PROPERTY_QNAME[1], i, 2);
    }

    public CTMap insertNewMap(int i) {
        CTMap cTMap;
        synchronized (monitor()) {
            check_orphaned();
            cTMap = (CTMap) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTMap;
    }

    public CTMap addNewMap() {
        CTMap cTMap;
        synchronized (monitor()) {
            check_orphaned();
            cTMap = (CTMap) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTMap;
    }

    public void removeMap(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public String getSelectionNamespaces() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetSelectionNamespaces() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[2]);
        }
        return xmlString;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSelectionNamespaces(java.lang.String r6) {
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
            r1.setStringValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTMapInfoImpl.setSelectionNamespaces(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSelectionNamespaces(org.apache.xmlbeans.XmlString r6) {
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
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.spreadsheetml.x2006.main.impl.CTMapInfoImpl.xsetSelectionNamespaces(org.apache.xmlbeans.XmlString):void");
    }
}
