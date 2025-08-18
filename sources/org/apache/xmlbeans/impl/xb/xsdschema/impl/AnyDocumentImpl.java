package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import javax.xml.namespace.QName;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnyDocument;

public class AnyDocumentImpl extends XmlComplexContentImpl implements AnyDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", Languages.ANY)};
    private static final long serialVersionUID = 1;

    public AnyDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public AnyDocument.Any getAny() {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            any = (AnyDocument.Any) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (any == null) {
                any = null;
            }
        }
        return any;
    }

    public void setAny(AnyDocument.Any any) {
        generatedSetterHelperImpl(any, PROPERTY_QNAME[0], 0, 1);
    }

    public AnyDocument.Any addNewAny() {
        AnyDocument.Any any;
        synchronized (monitor()) {
            check_orphaned();
            any = (AnyDocument.Any) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return any;
    }

    public static class AnyImpl extends WildcardImpl implements AnyDocument.Any {
        private static final QName[] PROPERTY_QNAME = {new QName("", "minOccurs"), new QName("", "maxOccurs")};
        private static final long serialVersionUID = 1;

        public AnyImpl(SchemaType schemaType) {
            super(schemaType);
        }

        /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.math.BigInteger getMinOccurs() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 0
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0022
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0022:
                if (r1 != 0) goto L_0x0026
                r5 = 0
                goto L_0x002a
            L_0x0026:
                java.math.BigInteger r5 = r1.getBigIntegerValue()     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return r5
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.getMinOccurs():java.math.BigInteger");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.XmlNonNegativeInteger xgetMinOccurs() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
                r3 = 0
                r4 = r2[r3]     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x0024 }
                if (r1 != 0) goto L_0x0022
                r1 = r2[r3]     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
                r1 = r5
                org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x0024 }
            L_0x0022:
                monitor-exit(r0)     // Catch:{ all -> 0x0024 }
                return r1
            L_0x0024:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0024 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.xgetMinOccurs():org.apache.xmlbeans.XmlNonNegativeInteger");
        }

        public boolean isSetMinOccurs() {
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
        public void setMinOccurs(java.math.BigInteger r6) {
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
                r1.setBigIntegerValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.setMinOccurs(java.math.BigInteger):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetMinOccurs(org.apache.xmlbeans.XmlNonNegativeInteger r6) {
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
                org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlNonNegativeInteger r1 = (org.apache.xmlbeans.XmlNonNegativeInteger) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.xsetMinOccurs(org.apache.xmlbeans.XmlNonNegativeInteger):void");
        }

        public void unsetMinOccurs() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[0]);
            }
        }

        /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getMaxOccurs() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 1
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0022
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0022:
                if (r1 != 0) goto L_0x0026
                r5 = 0
                goto L_0x002a
            L_0x0026:
                java.lang.Object r5 = r1.getObjectValue()     // Catch:{ all -> 0x002c }
            L_0x002a:
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return r5
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.getMaxOccurs():java.lang.Object");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.AllNNI xgetMaxOccurs() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
                r3 = 1
                r4 = r2[r3]     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x0024 }
                if (r1 != 0) goto L_0x0022
                r1 = r2[r3]     // Catch:{ all -> 0x0024 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x0024 }
            L_0x0022:
                monitor-exit(r0)     // Catch:{ all -> 0x0024 }
                return r1
            L_0x0024:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0024 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.xgetMaxOccurs():org.apache.xmlbeans.impl.xb.xsdschema.AllNNI");
        }

        public boolean isSetMaxOccurs() {
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
        public void setMaxOccurs(java.lang.Object r6) {
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
                r1.setObjectValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.setMaxOccurs(java.lang.Object):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetMaxOccurs(org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r6) {
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
                org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.AllNNI r1 = (org.apache.xmlbeans.impl.xb.xsdschema.AllNNI) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnyDocumentImpl.AnyImpl.xsetMaxOccurs(org.apache.xmlbeans.impl.xb.xsdschema.AllNNI):void");
        }

        public void unsetMaxOccurs() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }
    }
}
