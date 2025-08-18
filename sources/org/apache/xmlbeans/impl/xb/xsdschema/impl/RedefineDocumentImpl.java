package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

public class RedefineDocumentImpl extends XmlComplexContentImpl implements RedefineDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "redefine")};
    private static final long serialVersionUID = 1;

    public RedefineDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public RedefineDocument.Redefine getRedefine() {
        RedefineDocument.Redefine redefine;
        synchronized (monitor()) {
            check_orphaned();
            redefine = (RedefineDocument.Redefine) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (redefine == null) {
                redefine = null;
            }
        }
        return redefine;
    }

    public void setRedefine(RedefineDocument.Redefine redefine) {
        generatedSetterHelperImpl(redefine, PROPERTY_QNAME[0], 0, 1);
    }

    public RedefineDocument.Redefine addNewRedefine() {
        RedefineDocument.Redefine redefine;
        synchronized (monitor()) {
            check_orphaned();
            redefine = (RedefineDocument.Redefine) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return redefine;
    }

    public static class RedefineImpl extends OpenAttrsImpl implements RedefineDocument.Redefine {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "annotation"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "complexType"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("", "schemaLocation"), new QName("", "id")};
        private static final long serialVersionUID = 1;

        public RedefineImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<AnnotationDocument.Annotation> getAnnotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda20(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda21(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda22(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda23(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda24(this));
            }
            return javaListXmlObject;
        }

        public AnnotationDocument.Annotation[] getAnnotationArray() {
            return (AnnotationDocument.Annotation[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new AnnotationDocument.Annotation[0]);
        }

        public AnnotationDocument.Annotation getAnnotationArray(int i) {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (annotation == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return annotation;
        }

        public int sizeOfAnnotationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) annotationArr, PROPERTY_QNAME[0]);
        }

        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            generatedSetterHelperImpl(annotation, PROPERTY_QNAME[0], i, 2);
        }

        public AnnotationDocument.Annotation insertNewAnnotation(int i) {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return annotation;
        }

        public AnnotationDocument.Annotation addNewAnnotation() {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return annotation;
        }

        public void removeAnnotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List<TopLevelSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda12(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda13(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda14(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda15(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda16(this));
            }
            return javaListXmlObject;
        }

        public TopLevelSimpleType[] getSimpleTypeArray() {
            return (TopLevelSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new TopLevelSimpleType[0]);
        }

        public TopLevelSimpleType getSimpleTypeArray(int i) {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (topLevelSimpleType == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return topLevelSimpleType;
        }

        public int sizeOfSimpleTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setSimpleTypeArray(TopLevelSimpleType[] topLevelSimpleTypeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelSimpleTypeArr, PROPERTY_QNAME[1]);
        }

        public void setSimpleTypeArray(int i, TopLevelSimpleType topLevelSimpleType) {
            generatedSetterHelperImpl(topLevelSimpleType, PROPERTY_QNAME[1], i, 2);
        }

        public TopLevelSimpleType insertNewSimpleType(int i) {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return topLevelSimpleType;
        }

        public TopLevelSimpleType addNewSimpleType() {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return topLevelSimpleType;
        }

        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public List<TopLevelComplexType> getComplexTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda0(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda11(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda17(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda18(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda19(this));
            }
            return javaListXmlObject;
        }

        public TopLevelComplexType[] getComplexTypeArray() {
            return (TopLevelComplexType[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new TopLevelComplexType[0]);
        }

        public TopLevelComplexType getComplexTypeArray(int i) {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (topLevelComplexType == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return topLevelComplexType;
        }

        public int sizeOfComplexTypeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        public void setComplexTypeArray(TopLevelComplexType[] topLevelComplexTypeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelComplexTypeArr, PROPERTY_QNAME[2]);
        }

        public void setComplexTypeArray(int i, TopLevelComplexType topLevelComplexType) {
            generatedSetterHelperImpl(topLevelComplexType, PROPERTY_QNAME[2], i, 2);
        }

        public TopLevelComplexType insertNewComplexType(int i) {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return topLevelComplexType;
        }

        public TopLevelComplexType addNewComplexType() {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return topLevelComplexType;
        }

        public void removeComplexType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        public List<NamedGroup> getGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda6(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda7(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda8(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda9(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda10(this));
            }
            return javaListXmlObject;
        }

        public NamedGroup[] getGroupArray() {
            return (NamedGroup[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new NamedGroup[0]);
        }

        public NamedGroup getGroupArray(int i) {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().find_element_user(PROPERTY_QNAME[3], i);
                if (namedGroup == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return namedGroup;
        }

        public int sizeOfGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        public void setGroupArray(NamedGroup[] namedGroupArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) namedGroupArr, PROPERTY_QNAME[3]);
        }

        public void setGroupArray(int i, NamedGroup namedGroup) {
            generatedSetterHelperImpl(namedGroup, PROPERTY_QNAME[3], i, 2);
        }

        public NamedGroup insertNewGroup(int i) {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return namedGroup;
        }

        public NamedGroup addNewGroup() {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return namedGroup;
        }

        public void removeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        public List<NamedAttributeGroup> getAttributeGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda1(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda2(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda3(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda4(this), new RedefineDocumentImpl$RedefineImpl$$ExternalSyntheticLambda5(this));
            }
            return javaListXmlObject;
        }

        public NamedAttributeGroup[] getAttributeGroupArray() {
            return (NamedAttributeGroup[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new NamedAttributeGroup[0]);
        }

        public NamedAttributeGroup getAttributeGroupArray(int i) {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[4], i);
                if (namedAttributeGroup == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return namedAttributeGroup;
        }

        public int sizeOfAttributeGroupArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        public void setAttributeGroupArray(NamedAttributeGroup[] namedAttributeGroupArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) namedAttributeGroupArr, PROPERTY_QNAME[4]);
        }

        public void setAttributeGroupArray(int i, NamedAttributeGroup namedAttributeGroup) {
            generatedSetterHelperImpl(namedAttributeGroup, PROPERTY_QNAME[4], i, 2);
        }

        public NamedAttributeGroup insertNewAttributeGroup(int i) {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return namedAttributeGroup;
        }

        public NamedAttributeGroup addNewAttributeGroup() {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return namedAttributeGroup;
        }

        public void removeAttributeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        public String getSchemaLocation() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlAnyURI xgetSchemaLocation() {
            XmlAnyURI xmlAnyURI;
            synchronized (monitor()) {
                check_orphaned();
                xmlAnyURI = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            }
            return xmlAnyURI;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setSchemaLocation(java.lang.String r6) {
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
                r1.setStringValue(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl.RedefineImpl.setSchemaLocation(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetSchemaLocation(org.apache.xmlbeans.XmlAnyURI r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl.RedefineImpl.xsetSchemaLocation(org.apache.xmlbeans.XmlAnyURI):void");
        }

        public String getId() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[6]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlID xgetId() {
            XmlID xmlID;
            synchronized (monitor()) {
                check_orphaned();
                xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[6]);
            }
            return xmlID;
        }

        public boolean isSetId() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[6]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setId(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 6
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl.RedefineImpl.setId(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetId(org.apache.xmlbeans.XmlID r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
                r3 = 6
                r4 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
                if (r1 != 0) goto L_0x0026
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
                r1 = r2[r3]     // Catch:{ all -> 0x002b }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
                r1 = r5
                org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002b }
            L_0x0026:
                r1.set(r6)     // Catch:{ all -> 0x002b }
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                return
            L_0x002b:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002b }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.RedefineDocumentImpl.RedefineImpl.xsetId(org.apache.xmlbeans.XmlID):void");
        }

        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[6]);
            }
        }
    }
}
