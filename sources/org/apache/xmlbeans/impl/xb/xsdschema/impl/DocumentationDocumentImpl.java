package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import com.google.firebase.messaging.Constants;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

public class DocumentationDocumentImpl extends XmlComplexContentImpl implements DocumentationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "documentation")};
    private static final long serialVersionUID = 1;

    public DocumentationDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public DocumentationDocument.Documentation getDocumentation() {
        DocumentationDocument.Documentation documentation;
        synchronized (monitor()) {
            check_orphaned();
            documentation = (DocumentationDocument.Documentation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (documentation == null) {
                documentation = null;
            }
        }
        return documentation;
    }

    public void setDocumentation(DocumentationDocument.Documentation documentation) {
        generatedSetterHelperImpl(documentation, PROPERTY_QNAME[0], 0, 1);
    }

    public DocumentationDocument.Documentation addNewDocumentation() {
        DocumentationDocument.Documentation documentation;
        synchronized (monitor()) {
            check_orphaned();
            documentation = (DocumentationDocument.Documentation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return documentation;
    }

    public static class DocumentationImpl extends XmlComplexContentImpl implements DocumentationDocument.Documentation {
        private static final QName[] PROPERTY_QNAME = {new QName("", Constants.ScionAnalytics.PARAM_SOURCE), new QName("http://www.w3.org/XML/1998/namespace", "lang")};
        private static final long serialVersionUID = 1;

        public DocumentationImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public String getSource() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[0]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlAnyURI xgetSource() {
            XmlAnyURI xmlAnyURI;
            synchronized (monitor()) {
                check_orphaned();
                xmlAnyURI = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return xmlAnyURI;
        }

        public boolean isSetSource() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = false;
                if (get_store().find_attribute_user(PROPERTY_QNAME[0]) != null) {
                    z = true;
                }
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setSource(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 0
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.DocumentationDocumentImpl.DocumentationImpl.setSource(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetSource(org.apache.xmlbeans.XmlAnyURI r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 0
                r4 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.DocumentationDocumentImpl.DocumentationImpl.xsetSource(org.apache.xmlbeans.XmlAnyURI):void");
        }

        public void unsetSource() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }

        public String getLang() {
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

        public LangAttribute.Lang xgetLang() {
            LangAttribute.Lang lang;
            synchronized (monitor()) {
                check_orphaned();
                lang = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return lang;
        }

        public boolean isSetLang() {
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
        public void setLang(java.lang.String r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.DocumentationDocumentImpl.DocumentationImpl.setLang(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetLang(org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang r6) {
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
                org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang r1 = (org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang r1 = (org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.DocumentationDocumentImpl.DocumentationImpl.xsetLang(org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang):void");
        }

        public void unsetLang() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
