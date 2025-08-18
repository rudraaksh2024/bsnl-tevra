package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;

public class ListDocumentImpl extends XmlComplexContentImpl implements ListDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.LIST)};
    private static final long serialVersionUID = 1;

    public ListDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ListDocument.List getList() {
        ListDocument.List list;
        synchronized (monitor()) {
            check_orphaned();
            list = (ListDocument.List) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (list == null) {
                list = null;
            }
        }
        return list;
    }

    public void setList(ListDocument.List list) {
        generatedSetterHelperImpl(list, PROPERTY_QNAME[0], 0, 1);
    }

    public ListDocument.List addNewList() {
        ListDocument.List list;
        synchronized (monitor()) {
            check_orphaned();
            list = (ListDocument.List) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return list;
    }

    public static class ListImpl extends AnnotatedImpl implements ListDocument.List {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("", "itemType")};
        private static final long serialVersionUID = 1;

        public ListImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public LocalSimpleType getSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (localSimpleType == null) {
                    localSimpleType = null;
                }
            }
            return localSimpleType;
        }

        public boolean isSetSimpleType() {
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

        public void setSimpleType(LocalSimpleType localSimpleType) {
            generatedSetterHelperImpl(localSimpleType, PROPERTY_QNAME[0], 0, 1);
        }

        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return localSimpleType;
        }

        public void unsetSimpleType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        public QName getItemType() {
            QName qName;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (simpleValue == null) {
                    qName = null;
                } else {
                    qName = simpleValue.getQNameValue();
                }
            }
            return qName;
        }

        public XmlQName xgetItemType() {
            XmlQName xmlQName;
            synchronized (monitor()) {
                check_orphaned();
                xmlQName = (XmlQName) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return xmlQName;
        }

        public boolean isSetItemType() {
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
        public void setItemType(javax.xml.namespace.QName r6) {
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
                r1.setQNameValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.ListDocumentImpl.ListImpl.setItemType(javax.xml.namespace.QName):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetItemType(org.apache.xmlbeans.XmlQName r6) {
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
                org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlQName r1 = (org.apache.xmlbeans.XmlQName) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.ListDocumentImpl.ListImpl.xsetItemType(org.apache.xmlbeans.XmlQName):void");
        }

        public void unsetItemType() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
