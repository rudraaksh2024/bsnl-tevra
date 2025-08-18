package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexRestrictionType;
import org.apache.xmlbeans.impl.xb.xsdschema.ExtensionType;

public class ComplexContentDocumentImpl extends XmlComplexContentImpl implements ComplexContentDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "complexContent")};
    private static final long serialVersionUID = 1;

    public ComplexContentDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public ComplexContentDocument.ComplexContent getComplexContent() {
        ComplexContentDocument.ComplexContent complexContent;
        synchronized (monitor()) {
            check_orphaned();
            complexContent = (ComplexContentDocument.ComplexContent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (complexContent == null) {
                complexContent = null;
            }
        }
        return complexContent;
    }

    public void setComplexContent(ComplexContentDocument.ComplexContent complexContent) {
        generatedSetterHelperImpl(complexContent, PROPERTY_QNAME[0], 0, 1);
    }

    public ComplexContentDocument.ComplexContent addNewComplexContent() {
        ComplexContentDocument.ComplexContent complexContent;
        synchronized (monitor()) {
            check_orphaned();
            complexContent = (ComplexContentDocument.ComplexContent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return complexContent;
    }

    public static class ComplexContentImpl extends AnnotatedImpl implements ComplexContentDocument.ComplexContent {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "restriction"), new QName("http://www.w3.org/2001/XMLSchema", "extension"), new QName("", "mixed")};
        private static final long serialVersionUID = 1;

        public ComplexContentImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public ComplexRestrictionType getRestriction() {
            ComplexRestrictionType complexRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                complexRestrictionType = (ComplexRestrictionType) get_store().find_element_user(PROPERTY_QNAME[0], 0);
                if (complexRestrictionType == null) {
                    complexRestrictionType = null;
                }
            }
            return complexRestrictionType;
        }

        public boolean isSetRestriction() {
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

        public void setRestriction(ComplexRestrictionType complexRestrictionType) {
            generatedSetterHelperImpl(complexRestrictionType, PROPERTY_QNAME[0], 0, 1);
        }

        public ComplexRestrictionType addNewRestriction() {
            ComplexRestrictionType complexRestrictionType;
            synchronized (monitor()) {
                check_orphaned();
                complexRestrictionType = (ComplexRestrictionType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return complexRestrictionType;
        }

        public void unsetRestriction() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], 0);
            }
        }

        public ExtensionType getExtension() {
            ExtensionType extensionType;
            synchronized (monitor()) {
                check_orphaned();
                extensionType = (ExtensionType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
                if (extensionType == null) {
                    extensionType = null;
                }
            }
            return extensionType;
        }

        public boolean isSetExtension() {
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

        public void setExtension(ExtensionType extensionType) {
            generatedSetterHelperImpl(extensionType, PROPERTY_QNAME[1], 0, 1);
        }

        public ExtensionType addNewExtension() {
            ExtensionType extensionType;
            synchronized (monitor()) {
                check_orphaned();
                extensionType = (ExtensionType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return extensionType;
        }

        public void unsetExtension() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], 0);
            }
        }

        public boolean getMixed() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[2]);
                if (simpleValue == null) {
                    z = false;
                } else {
                    z = simpleValue.getBooleanValue();
                }
            }
            return z;
        }

        public XmlBoolean xgetMixed() {
            XmlBoolean xmlBoolean;
            synchronized (monitor()) {
                check_orphaned();
                xmlBoolean = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return xmlBoolean;
        }

        public boolean isSetMixed() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[2]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setMixed(boolean r6) {
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
                r1.setBooleanValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexContentDocumentImpl.ComplexContentImpl.setMixed(boolean):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetMixed(org.apache.xmlbeans.XmlBoolean r6) {
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
                org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.ComplexContentDocumentImpl.ComplexContentImpl.xsetMixed(org.apache.xmlbeans.XmlBoolean):void");
        }

        public void unsetMixed() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }
    }
}
