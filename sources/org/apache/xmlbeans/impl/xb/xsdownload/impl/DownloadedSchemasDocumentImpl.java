package org.apache.xmlbeans.impl.xb.xsdownload.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemaEntry;
import org.apache.xmlbeans.impl.xb.xsdownload.DownloadedSchemasDocument;

public class DownloadedSchemasDocumentImpl extends XmlComplexContentImpl implements DownloadedSchemasDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "downloaded-schemas")};
    private static final long serialVersionUID = 1;

    public DownloadedSchemasDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DownloadedSchemasDocument.DownloadedSchemas getDownloadedSchemas() {
        DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas;
        synchronized (monitor()) {
            check_orphaned();
            downloadedSchemas = (DownloadedSchemasDocument.DownloadedSchemas) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (downloadedSchemas == null) {
                downloadedSchemas = null;
            }
        }
        return downloadedSchemas;
    }

    public void setDownloadedSchemas(DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas) {
        generatedSetterHelperImpl(downloadedSchemas, PROPERTY_QNAME[0], 0, 1);
    }

    public DownloadedSchemasDocument.DownloadedSchemas addNewDownloadedSchemas() {
        DownloadedSchemasDocument.DownloadedSchemas downloadedSchemas;
        synchronized (monitor()) {
            check_orphaned();
            downloadedSchemas = (DownloadedSchemasDocument.DownloadedSchemas) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return downloadedSchemas;
    }

    public static class DownloadedSchemasImpl extends XmlComplexContentImpl implements DownloadedSchemasDocument.DownloadedSchemas {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.bea.com/2003/01/xmlbean/xsdownload", "entry"), new QName("", "defaultDirectory")};
        private static final long serialVersionUID = 1;

        public DownloadedSchemasImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<DownloadedSchemaEntry> getEntryList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda0(this), new DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda1(this), new DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda2(this), new DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda3(this), new DownloadedSchemasDocumentImpl$DownloadedSchemasImpl$$ExternalSyntheticLambda4(this));
            }
            return javaListXmlObject;
        }

        public DownloadedSchemaEntry[] getEntryArray() {
            return (DownloadedSchemaEntry[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new DownloadedSchemaEntry[0]);
        }

        public DownloadedSchemaEntry getEntryArray(int i) {
            DownloadedSchemaEntry downloadedSchemaEntry;
            synchronized (monitor()) {
                check_orphaned();
                downloadedSchemaEntry = (DownloadedSchemaEntry) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (downloadedSchemaEntry == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return downloadedSchemaEntry;
        }

        public int sizeOfEntryArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setEntryArray(DownloadedSchemaEntry[] downloadedSchemaEntryArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) downloadedSchemaEntryArr, PROPERTY_QNAME[0]);
        }

        public void setEntryArray(int i, DownloadedSchemaEntry downloadedSchemaEntry) {
            generatedSetterHelperImpl(downloadedSchemaEntry, PROPERTY_QNAME[0], i, 2);
        }

        public DownloadedSchemaEntry insertNewEntry(int i) {
            DownloadedSchemaEntry downloadedSchemaEntry;
            synchronized (monitor()) {
                check_orphaned();
                downloadedSchemaEntry = (DownloadedSchemaEntry) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return downloadedSchemaEntry;
        }

        public DownloadedSchemaEntry addNewEntry() {
            DownloadedSchemaEntry downloadedSchemaEntry;
            synchronized (monitor()) {
                check_orphaned();
                downloadedSchemaEntry = (DownloadedSchemaEntry) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return downloadedSchemaEntry;
        }

        public void removeEntry(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public String getDefaultDirectory() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlToken xgetDefaultDirectory() {
            XmlToken xmlToken;
            synchronized (monitor()) {
                check_orphaned();
                xmlToken = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return xmlToken;
        }

        public boolean isSetDefaultDirectory() {
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
        public void setDefaultDirectory(java.lang.String r6) {
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
                r1.setStringValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.setDefaultDirectory(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetDefaultDirectory(org.apache.xmlbeans.XmlToken r6) {
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
                org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdownload.impl.DownloadedSchemasDocumentImpl.DownloadedSchemasImpl.xsetDefaultDirectory(org.apache.xmlbeans.XmlToken):void");
        }

        public void unsetDefaultDirectory() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
