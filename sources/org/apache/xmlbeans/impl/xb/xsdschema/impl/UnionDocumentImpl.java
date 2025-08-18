package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.values.XmlListImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalSimpleType;
import org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument;

public class UnionDocumentImpl extends XmlComplexContentImpl implements UnionDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", XmlErrorCodes.UNION)};
    private static final long serialVersionUID = 1;

    public UnionDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public UnionDocument.Union getUnion() {
        UnionDocument.Union union;
        synchronized (monitor()) {
            check_orphaned();
            union = (UnionDocument.Union) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (union == null) {
                union = null;
            }
        }
        return union;
    }

    public void setUnion(UnionDocument.Union union) {
        generatedSetterHelperImpl(union, PROPERTY_QNAME[0], 0, 1);
    }

    public UnionDocument.Union addNewUnion() {
        UnionDocument.Union union;
        synchronized (monitor()) {
            check_orphaned();
            union = (UnionDocument.Union) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return union;
    }

    public static class UnionImpl extends AnnotatedImpl implements UnionDocument.Union {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("", "memberTypes")};
        private static final long serialVersionUID = 1;

        public UnionImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<LocalSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda0(this), new UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda1(this), new UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda2(this), new UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda3(this), new UnionDocumentImpl$UnionImpl$$ExternalSyntheticLambda4(this));
            }
            return javaListXmlObject;
        }

        public LocalSimpleType[] getSimpleTypeArray() {
            return (LocalSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new LocalSimpleType[0]);
        }

        public LocalSimpleType getSimpleTypeArray(int i) {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (localSimpleType == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return localSimpleType;
        }

        public int sizeOfSimpleTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setSimpleTypeArray(LocalSimpleType[] localSimpleTypeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) localSimpleTypeArr, PROPERTY_QNAME[0]);
        }

        public void setSimpleTypeArray(int i, LocalSimpleType localSimpleType) {
            generatedSetterHelperImpl(localSimpleType, PROPERTY_QNAME[0], i, 2);
        }

        public LocalSimpleType insertNewSimpleType(int i) {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return localSimpleType;
        }

        public LocalSimpleType addNewSimpleType() {
            LocalSimpleType localSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                localSimpleType = (LocalSimpleType) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return localSimpleType;
        }

        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List getMemberTypes() {
            List<?> list;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[1]);
                if (simpleValue == null) {
                    list = null;
                } else {
                    list = simpleValue.getListValue();
                }
            }
            return list;
        }

        public UnionDocument.Union.MemberTypes xgetMemberTypes() {
            UnionDocument.Union.MemberTypes memberTypes;
            synchronized (monitor()) {
                check_orphaned();
                memberTypes = (UnionDocument.Union.MemberTypes) get_store().find_attribute_user(PROPERTY_QNAME[1]);
            }
            return memberTypes;
        }

        public boolean isSetMemberTypes() {
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
        public void setMemberTypes(java.util.List r6) {
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
                r1.setListValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl.UnionImpl.setMemberTypes(java.util.List):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetMemberTypes(org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union.MemberTypes r6) {
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
                org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument$Union$MemberTypes r1 = (org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union.MemberTypes) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument$Union$MemberTypes r1 = (org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument.Union.MemberTypes) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.UnionDocumentImpl.UnionImpl.xsetMemberTypes(org.apache.xmlbeans.impl.xb.xsdschema.UnionDocument$Union$MemberTypes):void");
        }

        public void unsetMemberTypes() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[1]);
            }
        }

        public static class MemberTypesImpl extends XmlListImpl implements UnionDocument.Union.MemberTypes {
            private static final long serialVersionUID = 1;

            public MemberTypesImpl(SchemaType schemaType) {
                super(schemaType, false);
            }

            protected MemberTypesImpl(SchemaType schemaType, boolean z) {
                super(schemaType, z);
            }
        }
    }
}
