package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;

public class AnnotationDocumentImpl extends XmlComplexContentImpl implements AnnotationDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "annotation")};
    private static final long serialVersionUID = 1;

    public AnnotationDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public AnnotationDocument.Annotation getAnnotation() {
        AnnotationDocument.Annotation annotation;
        synchronized (monitor()) {
            check_orphaned();
            annotation = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (annotation == null) {
                annotation = null;
            }
        }
        return annotation;
    }

    public void setAnnotation(AnnotationDocument.Annotation annotation) {
        generatedSetterHelperImpl(annotation, PROPERTY_QNAME[0], 0, 1);
    }

    public AnnotationDocument.Annotation addNewAnnotation() {
        AnnotationDocument.Annotation annotation;
        synchronized (monitor()) {
            check_orphaned();
            annotation = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return annotation;
    }

    public static class AnnotationImpl extends OpenAttrsImpl implements AnnotationDocument.Annotation {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "appinfo"), new QName("http://www.w3.org/2001/XMLSchema", "documentation"), new QName("", "id")};
        private static final long serialVersionUID = 1;

        public AnnotationImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<AppinfoDocument.Appinfo> getAppinfoList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda0(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda1(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda2(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda3(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda4(this));
            }
            return javaListXmlObject;
        }

        public AppinfoDocument.Appinfo[] getAppinfoArray() {
            return (AppinfoDocument.Appinfo[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new AppinfoDocument.Appinfo[0]);
        }

        public AppinfoDocument.Appinfo getAppinfoArray(int i) {
            AppinfoDocument.Appinfo appinfo;
            synchronized (monitor()) {
                check_orphaned();
                appinfo = (AppinfoDocument.Appinfo) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (appinfo == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return appinfo;
        }

        public int sizeOfAppinfoArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setAppinfoArray(AppinfoDocument.Appinfo[] appinfoArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) appinfoArr, PROPERTY_QNAME[0]);
        }

        public void setAppinfoArray(int i, AppinfoDocument.Appinfo appinfo) {
            generatedSetterHelperImpl(appinfo, PROPERTY_QNAME[0], i, 2);
        }

        public AppinfoDocument.Appinfo insertNewAppinfo(int i) {
            AppinfoDocument.Appinfo appinfo;
            synchronized (monitor()) {
                check_orphaned();
                appinfo = (AppinfoDocument.Appinfo) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return appinfo;
        }

        public AppinfoDocument.Appinfo addNewAppinfo() {
            AppinfoDocument.Appinfo appinfo;
            synchronized (monitor()) {
                check_orphaned();
                appinfo = (AppinfoDocument.Appinfo) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return appinfo;
        }

        public void removeAppinfo(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List<DocumentationDocument.Documentation> getDocumentationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda5(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda6(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda7(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda8(this), new AnnotationDocumentImpl$AnnotationImpl$$ExternalSyntheticLambda9(this));
            }
            return javaListXmlObject;
        }

        public DocumentationDocument.Documentation[] getDocumentationArray() {
            return (DocumentationDocument.Documentation[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new DocumentationDocument.Documentation[0]);
        }

        public DocumentationDocument.Documentation getDocumentationArray(int i) {
            DocumentationDocument.Documentation documentation;
            synchronized (monitor()) {
                check_orphaned();
                documentation = (DocumentationDocument.Documentation) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (documentation == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return documentation;
        }

        public int sizeOfDocumentationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setDocumentationArray(DocumentationDocument.Documentation[] documentationArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) documentationArr, PROPERTY_QNAME[1]);
        }

        public void setDocumentationArray(int i, DocumentationDocument.Documentation documentation) {
            generatedSetterHelperImpl(documentation, PROPERTY_QNAME[1], i, 2);
        }

        public DocumentationDocument.Documentation insertNewDocumentation(int i) {
            DocumentationDocument.Documentation documentation;
            synchronized (monitor()) {
                check_orphaned();
                documentation = (DocumentationDocument.Documentation) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return documentation;
        }

        public DocumentationDocument.Documentation addNewDocumentation() {
            DocumentationDocument.Documentation documentation;
            synchronized (monitor()) {
                check_orphaned();
                documentation = (DocumentationDocument.Documentation) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return documentation;
        }

        public void removeDocumentation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public String getId() {
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

        public XmlID xgetId() {
            XmlID xmlID;
            synchronized (monitor()) {
                check_orphaned();
                xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[2]);
            }
            return xmlID;
        }

        public boolean isSetId() {
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
        public void setId(java.lang.String r6) {
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl.AnnotationImpl.setId(java.lang.String):void");
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
                r3 = 2
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.AnnotationDocumentImpl.AnnotationImpl.xsetId(org.apache.xmlbeans.XmlID):void");
        }

        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[2]);
            }
        }
    }
}
