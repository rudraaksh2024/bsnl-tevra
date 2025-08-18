package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument;

public class SelectorDocumentImpl extends XmlComplexContentImpl implements SelectorDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "selector")};
    private static final long serialVersionUID = 1;

    public SelectorDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public SelectorDocument.Selector getSelector() {
        SelectorDocument.Selector selector;
        synchronized (monitor()) {
            check_orphaned();
            selector = (SelectorDocument.Selector) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (selector == null) {
                selector = null;
            }
        }
        return selector;
    }

    public void setSelector(SelectorDocument.Selector selector) {
        generatedSetterHelperImpl(selector, PROPERTY_QNAME[0], 0, 1);
    }

    public SelectorDocument.Selector addNewSelector() {
        SelectorDocument.Selector selector;
        synchronized (monitor()) {
            check_orphaned();
            selector = (SelectorDocument.Selector) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return selector;
    }

    public static class SelectorImpl extends AnnotatedImpl implements SelectorDocument.Selector {
        private static final QName[] PROPERTY_QNAME = {new QName("", "xpath")};
        private static final long serialVersionUID = 1;

        public SelectorImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public String getXpath() {
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

        public SelectorDocument.Selector.Xpath xgetXpath() {
            SelectorDocument.Selector.Xpath xpath;
            synchronized (monitor()) {
                check_orphaned();
                xpath = (SelectorDocument.Selector.Xpath) get_store().find_attribute_user(PROPERTY_QNAME[0]);
            }
            return xpath;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setXpath(java.lang.String r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SelectorDocumentImpl.SelectorImpl.setXpath(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetXpath(org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector.Xpath r6) {
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
                org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument$Selector$Xpath r1 = (org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector.Xpath) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument$Selector$Xpath r1 = (org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument.Selector.Xpath) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SelectorDocumentImpl.SelectorImpl.xsetXpath(org.apache.xmlbeans.impl.xb.xsdschema.SelectorDocument$Selector$Xpath):void");
        }

        public static class XpathImpl extends JavaStringHolderEx implements SelectorDocument.Selector.Xpath {
            private static final long serialVersionUID = 1;

            public XpathImpl(SchemaType schemaType) {
                super(schemaType, false);
            }

            protected XpathImpl(SchemaType schemaType, boolean z) {
                super(schemaType, z);
            }
        }
    }
}
