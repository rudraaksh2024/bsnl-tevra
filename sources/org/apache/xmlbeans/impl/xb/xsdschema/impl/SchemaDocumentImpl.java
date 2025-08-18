package org.apache.xmlbeans.impl.xb.xsdschema.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedAttributeGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelSimpleType;

public class SchemaDocumentImpl extends XmlComplexContentImpl implements SchemaDocument {
    private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "schema")};
    private static final long serialVersionUID = 1;

    public SchemaDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public SchemaDocument.Schema getSchema() {
        SchemaDocument.Schema schema;
        synchronized (monitor()) {
            check_orphaned();
            schema = (SchemaDocument.Schema) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (schema == null) {
                schema = null;
            }
        }
        return schema;
    }

    public void setSchema(SchemaDocument.Schema schema) {
        generatedSetterHelperImpl(schema, PROPERTY_QNAME[0], 0, 1);
    }

    public SchemaDocument.Schema addNewSchema() {
        SchemaDocument.Schema schema;
        synchronized (monitor()) {
            check_orphaned();
            schema = (SchemaDocument.Schema) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return schema;
    }

    public static class SchemaImpl extends OpenAttrsImpl implements SchemaDocument.Schema {
        private static final QName[] PROPERTY_QNAME = {new QName("http://www.w3.org/2001/XMLSchema", "include"), new QName("http://www.w3.org/2001/XMLSchema", "import"), new QName("http://www.w3.org/2001/XMLSchema", "redefine"), new QName("http://www.w3.org/2001/XMLSchema", "annotation"), new QName("http://www.w3.org/2001/XMLSchema", "simpleType"), new QName("http://www.w3.org/2001/XMLSchema", "complexType"), new QName("http://www.w3.org/2001/XMLSchema", "group"), new QName("http://www.w3.org/2001/XMLSchema", "attributeGroup"), new QName("http://www.w3.org/2001/XMLSchema", "element"), new QName("http://www.w3.org/2001/XMLSchema", "attribute"), new QName("http://www.w3.org/2001/XMLSchema", "notation"), new QName("", "targetNamespace"), new QName("", "version"), new QName("", "finalDefault"), new QName("", "blockDefault"), new QName("", "attributeFormDefault"), new QName("", "elementFormDefault"), new QName("", "id"), new QName("http://www.w3.org/XML/1998/namespace", "lang")};
        private static final long serialVersionUID = 1;

        public SchemaImpl(SchemaType schemaType) {
            super(schemaType);
        }

        public List<IncludeDocument.Include> getIncludeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda34(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda35(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda36(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda37(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda38(this));
            }
            return javaListXmlObject;
        }

        public IncludeDocument.Include[] getIncludeArray() {
            return (IncludeDocument.Include[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new IncludeDocument.Include[0]);
        }

        public IncludeDocument.Include getIncludeArray(int i) {
            IncludeDocument.Include include;
            synchronized (monitor()) {
                check_orphaned();
                include = (IncludeDocument.Include) get_store().find_element_user(PROPERTY_QNAME[0], i);
                if (include == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return include;
        }

        public int sizeOfIncludeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
            }
            return count_elements;
        }

        public void setIncludeArray(IncludeDocument.Include[] includeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) includeArr, PROPERTY_QNAME[0]);
        }

        public void setIncludeArray(int i, IncludeDocument.Include include) {
            generatedSetterHelperImpl(include, PROPERTY_QNAME[0], i, 2);
        }

        public IncludeDocument.Include insertNewInclude(int i) {
            IncludeDocument.Include include;
            synchronized (monitor()) {
                check_orphaned();
                include = (IncludeDocument.Include) get_store().insert_element_user(PROPERTY_QNAME[0], i);
            }
            return include;
        }

        public IncludeDocument.Include addNewInclude() {
            IncludeDocument.Include include;
            synchronized (monitor()) {
                check_orphaned();
                include = (IncludeDocument.Include) get_store().add_element_user(PROPERTY_QNAME[0]);
            }
            return include;
        }

        public void removeInclude(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[0], i);
            }
        }

        public List<ImportDocument.Import> getImportList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda6(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda7(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda8(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda9(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda10(this));
            }
            return javaListXmlObject;
        }

        public ImportDocument.Import[] getImportArray() {
            return (ImportDocument.Import[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new ImportDocument.Import[0]);
        }

        public ImportDocument.Import getImportArray(int i) {
            ImportDocument.Import importR;
            synchronized (monitor()) {
                check_orphaned();
                importR = (ImportDocument.Import) get_store().find_element_user(PROPERTY_QNAME[1], i);
                if (importR == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return importR;
        }

        public int sizeOfImportArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
            }
            return count_elements;
        }

        public void setImportArray(ImportDocument.Import[] importArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) importArr, PROPERTY_QNAME[1]);
        }

        public void setImportArray(int i, ImportDocument.Import importR) {
            generatedSetterHelperImpl(importR, PROPERTY_QNAME[1], i, 2);
        }

        public ImportDocument.Import insertNewImport(int i) {
            ImportDocument.Import importR;
            synchronized (monitor()) {
                check_orphaned();
                importR = (ImportDocument.Import) get_store().insert_element_user(PROPERTY_QNAME[1], i);
            }
            return importR;
        }

        public ImportDocument.Import addNewImport() {
            ImportDocument.Import importR;
            synchronized (monitor()) {
                check_orphaned();
                importR = (ImportDocument.Import) get_store().add_element_user(PROPERTY_QNAME[1]);
            }
            return importR;
        }

        public void removeImport(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[1], i);
            }
        }

        public List<RedefineDocument.Redefine> getRedefineList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda45(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda46(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda47(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda48(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda49(this));
            }
            return javaListXmlObject;
        }

        public RedefineDocument.Redefine[] getRedefineArray() {
            return (RedefineDocument.Redefine[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new RedefineDocument.Redefine[0]);
        }

        public RedefineDocument.Redefine getRedefineArray(int i) {
            RedefineDocument.Redefine redefine;
            synchronized (monitor()) {
                check_orphaned();
                redefine = (RedefineDocument.Redefine) get_store().find_element_user(PROPERTY_QNAME[2], i);
                if (redefine == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return redefine;
        }

        public int sizeOfRedefineArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
            }
            return count_elements;
        }

        public void setRedefineArray(RedefineDocument.Redefine[] redefineArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) redefineArr, PROPERTY_QNAME[2]);
        }

        public void setRedefineArray(int i, RedefineDocument.Redefine redefine) {
            generatedSetterHelperImpl(redefine, PROPERTY_QNAME[2], i, 2);
        }

        public RedefineDocument.Redefine insertNewRedefine(int i) {
            RedefineDocument.Redefine redefine;
            synchronized (monitor()) {
                check_orphaned();
                redefine = (RedefineDocument.Redefine) get_store().insert_element_user(PROPERTY_QNAME[2], i);
            }
            return redefine;
        }

        public RedefineDocument.Redefine addNewRedefine() {
            RedefineDocument.Redefine redefine;
            synchronized (monitor()) {
                check_orphaned();
                redefine = (RedefineDocument.Redefine) get_store().add_element_user(PROPERTY_QNAME[2]);
            }
            return redefine;
        }

        public void removeRedefine(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[2], i);
            }
        }

        public List<AnnotationDocument.Annotation> getAnnotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda23(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda24(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda25(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda26(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda27(this));
            }
            return javaListXmlObject;
        }

        public AnnotationDocument.Annotation[] getAnnotationArray() {
            return (AnnotationDocument.Annotation[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new AnnotationDocument.Annotation[0]);
        }

        public AnnotationDocument.Annotation getAnnotationArray(int i) {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().find_element_user(PROPERTY_QNAME[3], i);
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
                count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
            }
            return count_elements;
        }

        public void setAnnotationArray(AnnotationDocument.Annotation[] annotationArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) annotationArr, PROPERTY_QNAME[3]);
        }

        public void setAnnotationArray(int i, AnnotationDocument.Annotation annotation) {
            generatedSetterHelperImpl(annotation, PROPERTY_QNAME[3], i, 2);
        }

        public AnnotationDocument.Annotation insertNewAnnotation(int i) {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().insert_element_user(PROPERTY_QNAME[3], i);
            }
            return annotation;
        }

        public AnnotationDocument.Annotation addNewAnnotation() {
            AnnotationDocument.Annotation annotation;
            synchronized (monitor()) {
                check_orphaned();
                annotation = (AnnotationDocument.Annotation) get_store().add_element_user(PROPERTY_QNAME[3]);
            }
            return annotation;
        }

        public void removeAnnotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[3], i);
            }
        }

        public List<TopLevelSimpleType> getSimpleTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda39(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda40(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda41(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda42(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda43(this));
            }
            return javaListXmlObject;
        }

        public TopLevelSimpleType[] getSimpleTypeArray() {
            return (TopLevelSimpleType[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new TopLevelSimpleType[0]);
        }

        public TopLevelSimpleType getSimpleTypeArray(int i) {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().find_element_user(PROPERTY_QNAME[4], i);
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
                count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
            }
            return count_elements;
        }

        public void setSimpleTypeArray(TopLevelSimpleType[] topLevelSimpleTypeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelSimpleTypeArr, PROPERTY_QNAME[4]);
        }

        public void setSimpleTypeArray(int i, TopLevelSimpleType topLevelSimpleType) {
            generatedSetterHelperImpl(topLevelSimpleType, PROPERTY_QNAME[4], i, 2);
        }

        public TopLevelSimpleType insertNewSimpleType(int i) {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().insert_element_user(PROPERTY_QNAME[4], i);
            }
            return topLevelSimpleType;
        }

        public TopLevelSimpleType addNewSimpleType() {
            TopLevelSimpleType topLevelSimpleType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelSimpleType = (TopLevelSimpleType) get_store().add_element_user(PROPERTY_QNAME[4]);
            }
            return topLevelSimpleType;
        }

        public void removeSimpleType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[4], i);
            }
        }

        public List<TopLevelComplexType> getComplexTypeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda50(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda51(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda52(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda53(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda54(this));
            }
            return javaListXmlObject;
        }

        public TopLevelComplexType[] getComplexTypeArray() {
            return (TopLevelComplexType[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new TopLevelComplexType[0]);
        }

        public TopLevelComplexType getComplexTypeArray(int i) {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().find_element_user(PROPERTY_QNAME[5], i);
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
                count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
            }
            return count_elements;
        }

        public void setComplexTypeArray(TopLevelComplexType[] topLevelComplexTypeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelComplexTypeArr, PROPERTY_QNAME[5]);
        }

        public void setComplexTypeArray(int i, TopLevelComplexType topLevelComplexType) {
            generatedSetterHelperImpl(topLevelComplexType, PROPERTY_QNAME[5], i, 2);
        }

        public TopLevelComplexType insertNewComplexType(int i) {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().insert_element_user(PROPERTY_QNAME[5], i);
            }
            return topLevelComplexType;
        }

        public TopLevelComplexType addNewComplexType() {
            TopLevelComplexType topLevelComplexType;
            synchronized (monitor()) {
                check_orphaned();
                topLevelComplexType = (TopLevelComplexType) get_store().add_element_user(PROPERTY_QNAME[5]);
            }
            return topLevelComplexType;
        }

        public void removeComplexType(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[5], i);
            }
        }

        public List<NamedGroup> getGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda1(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda2(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda3(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda4(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda5(this));
            }
            return javaListXmlObject;
        }

        public NamedGroup[] getGroupArray() {
            return (NamedGroup[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new NamedGroup[0]);
        }

        public NamedGroup getGroupArray(int i) {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().find_element_user(PROPERTY_QNAME[6], i);
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
                count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
            }
            return count_elements;
        }

        public void setGroupArray(NamedGroup[] namedGroupArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) namedGroupArr, PROPERTY_QNAME[6]);
        }

        public void setGroupArray(int i, NamedGroup namedGroup) {
            generatedSetterHelperImpl(namedGroup, PROPERTY_QNAME[6], i, 2);
        }

        public NamedGroup insertNewGroup(int i) {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().insert_element_user(PROPERTY_QNAME[6], i);
            }
            return namedGroup;
        }

        public NamedGroup addNewGroup() {
            NamedGroup namedGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedGroup = (NamedGroup) get_store().add_element_user(PROPERTY_QNAME[6]);
            }
            return namedGroup;
        }

        public void removeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[6], i);
            }
        }

        public List<NamedAttributeGroup> getAttributeGroupList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda28(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda29(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda30(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda31(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda32(this));
            }
            return javaListXmlObject;
        }

        public NamedAttributeGroup[] getAttributeGroupArray() {
            return (NamedAttributeGroup[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new NamedAttributeGroup[0]);
        }

        public NamedAttributeGroup getAttributeGroupArray(int i) {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().find_element_user(PROPERTY_QNAME[7], i);
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
                count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
            }
            return count_elements;
        }

        public void setAttributeGroupArray(NamedAttributeGroup[] namedAttributeGroupArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) namedAttributeGroupArr, PROPERTY_QNAME[7]);
        }

        public void setAttributeGroupArray(int i, NamedAttributeGroup namedAttributeGroup) {
            generatedSetterHelperImpl(namedAttributeGroup, PROPERTY_QNAME[7], i, 2);
        }

        public NamedAttributeGroup insertNewAttributeGroup(int i) {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().insert_element_user(PROPERTY_QNAME[7], i);
            }
            return namedAttributeGroup;
        }

        public NamedAttributeGroup addNewAttributeGroup() {
            NamedAttributeGroup namedAttributeGroup;
            synchronized (monitor()) {
                check_orphaned();
                namedAttributeGroup = (NamedAttributeGroup) get_store().add_element_user(PROPERTY_QNAME[7]);
            }
            return namedAttributeGroup;
        }

        public void removeAttributeGroup(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[7], i);
            }
        }

        public List<TopLevelElement> getElementList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda17(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda18(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda19(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda20(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda21(this));
            }
            return javaListXmlObject;
        }

        public TopLevelElement[] getElementArray() {
            return (TopLevelElement[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new TopLevelElement[0]);
        }

        public TopLevelElement getElementArray(int i) {
            TopLevelElement topLevelElement;
            synchronized (monitor()) {
                check_orphaned();
                topLevelElement = (TopLevelElement) get_store().find_element_user(PROPERTY_QNAME[8], i);
                if (topLevelElement == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return topLevelElement;
        }

        public int sizeOfElementArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
            }
            return count_elements;
        }

        public void setElementArray(TopLevelElement[] topLevelElementArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelElementArr, PROPERTY_QNAME[8]);
        }

        public void setElementArray(int i, TopLevelElement topLevelElement) {
            generatedSetterHelperImpl(topLevelElement, PROPERTY_QNAME[8], i, 2);
        }

        public TopLevelElement insertNewElement(int i) {
            TopLevelElement topLevelElement;
            synchronized (monitor()) {
                check_orphaned();
                topLevelElement = (TopLevelElement) get_store().insert_element_user(PROPERTY_QNAME[8], i);
            }
            return topLevelElement;
        }

        public TopLevelElement addNewElement() {
            TopLevelElement topLevelElement;
            synchronized (monitor()) {
                check_orphaned();
                topLevelElement = (TopLevelElement) get_store().add_element_user(PROPERTY_QNAME[8]);
            }
            return topLevelElement;
        }

        public void removeElement(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[8], i);
            }
        }

        public List<TopLevelAttribute> getAttributeList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda12(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda13(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda14(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda15(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda16(this));
            }
            return javaListXmlObject;
        }

        public TopLevelAttribute[] getAttributeArray() {
            return (TopLevelAttribute[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new TopLevelAttribute[0]);
        }

        public TopLevelAttribute getAttributeArray(int i) {
            TopLevelAttribute topLevelAttribute;
            synchronized (monitor()) {
                check_orphaned();
                topLevelAttribute = (TopLevelAttribute) get_store().find_element_user(PROPERTY_QNAME[9], i);
                if (topLevelAttribute == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return topLevelAttribute;
        }

        public int sizeOfAttributeArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
            }
            return count_elements;
        }

        public void setAttributeArray(TopLevelAttribute[] topLevelAttributeArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) topLevelAttributeArr, PROPERTY_QNAME[9]);
        }

        public void setAttributeArray(int i, TopLevelAttribute topLevelAttribute) {
            generatedSetterHelperImpl(topLevelAttribute, PROPERTY_QNAME[9], i, 2);
        }

        public TopLevelAttribute insertNewAttribute(int i) {
            TopLevelAttribute topLevelAttribute;
            synchronized (monitor()) {
                check_orphaned();
                topLevelAttribute = (TopLevelAttribute) get_store().insert_element_user(PROPERTY_QNAME[9], i);
            }
            return topLevelAttribute;
        }

        public TopLevelAttribute addNewAttribute() {
            TopLevelAttribute topLevelAttribute;
            synchronized (monitor()) {
                check_orphaned();
                topLevelAttribute = (TopLevelAttribute) get_store().add_element_user(PROPERTY_QNAME[9]);
            }
            return topLevelAttribute;
        }

        public void removeAttribute(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[9], i);
            }
        }

        public List<NotationDocument.Notation> getNotationList() {
            JavaListXmlObject javaListXmlObject;
            synchronized (monitor()) {
                check_orphaned();
                javaListXmlObject = new JavaListXmlObject(new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda0(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda11(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda22(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda33(this), new SchemaDocumentImpl$SchemaImpl$$ExternalSyntheticLambda44(this));
            }
            return javaListXmlObject;
        }

        public NotationDocument.Notation[] getNotationArray() {
            return (NotationDocument.Notation[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new NotationDocument.Notation[0]);
        }

        public NotationDocument.Notation getNotationArray(int i) {
            NotationDocument.Notation notation;
            synchronized (monitor()) {
                check_orphaned();
                notation = (NotationDocument.Notation) get_store().find_element_user(PROPERTY_QNAME[10], i);
                if (notation == null) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return notation;
        }

        public int sizeOfNotationArray() {
            int count_elements;
            synchronized (monitor()) {
                check_orphaned();
                count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
            }
            return count_elements;
        }

        public void setNotationArray(NotationDocument.Notation[] notationArr) {
            check_orphaned();
            arraySetterHelper((XmlObject[]) notationArr, PROPERTY_QNAME[10]);
        }

        public void setNotationArray(int i, NotationDocument.Notation notation) {
            generatedSetterHelperImpl(notation, PROPERTY_QNAME[10], i, 2);
        }

        public NotationDocument.Notation insertNewNotation(int i) {
            NotationDocument.Notation notation;
            synchronized (monitor()) {
                check_orphaned();
                notation = (NotationDocument.Notation) get_store().insert_element_user(PROPERTY_QNAME[10], i);
            }
            return notation;
        }

        public NotationDocument.Notation addNewNotation() {
            NotationDocument.Notation notation;
            synchronized (monitor()) {
                check_orphaned();
                notation = (NotationDocument.Notation) get_store().add_element_user(PROPERTY_QNAME[10]);
            }
            return notation;
        }

        public void removeNotation(int i) {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_element(PROPERTY_QNAME[10], i);
            }
        }

        public String getTargetNamespace() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[11]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlAnyURI xgetTargetNamespace() {
            XmlAnyURI xmlAnyURI;
            synchronized (monitor()) {
                check_orphaned();
                xmlAnyURI = (XmlAnyURI) get_store().find_attribute_user(PROPERTY_QNAME[11]);
            }
            return xmlAnyURI;
        }

        public boolean isSetTargetNamespace() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[11]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setTargetNamespace(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 11
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setTargetNamespace(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetTargetNamespace(org.apache.xmlbeans.XmlAnyURI r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 11
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.XmlAnyURI r1 = (org.apache.xmlbeans.XmlAnyURI) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetTargetNamespace(org.apache.xmlbeans.XmlAnyURI):void");
        }

        public void unsetTargetNamespace() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[11]);
            }
        }

        public String getVersion() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
                if (simpleValue == null) {
                    str = null;
                } else {
                    str = simpleValue.getStringValue();
                }
            }
            return str;
        }

        public XmlToken xgetVersion() {
            XmlToken xmlToken;
            synchronized (monitor()) {
                check_orphaned();
                xmlToken = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            }
            return xmlToken;
        }

        public boolean isSetVersion() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setVersion(java.lang.String r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 12
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setVersion(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetVersion(org.apache.xmlbeans.XmlToken r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 12
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetVersion(org.apache.xmlbeans.XmlToken):void");
        }

        public void unsetVersion() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[12]);
            }
        }

        /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getFinalDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            L_0x0023:
                if (r1 != 0) goto L_0x0027
                r5 = 0
                goto L_0x002b
            L_0x0027:
                java.lang.Object r5 = r1.getObjectValue()     // Catch:{ all -> 0x002d }
            L_0x002b:
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return r5
            L_0x002d:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.getFinalDefault():java.lang.Object");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet xgetFinalDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet) r1     // Catch:{ all -> 0x0025 }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet) r1     // Catch:{ all -> 0x0025 }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                return r1
            L_0x0025:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xgetFinalDefault():org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet");
        }

        public boolean isSetFinalDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setFinalDefault(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setFinalDefault(java.lang.Object):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetFinalDefault(org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 13
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetFinalDefault(org.apache.xmlbeans.impl.xb.xsdschema.FullDerivationSet):void");
        }

        public void unsetFinalDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[13]);
            }
        }

        /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getBlockDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
                r3 = 14
                r4 = r2[r3]     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x002d }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            L_0x0023:
                if (r1 != 0) goto L_0x0027
                r5 = 0
                goto L_0x002b
            L_0x0027:
                java.lang.Object r5 = r1.getObjectValue()     // Catch:{ all -> 0x002d }
            L_0x002b:
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                return r5
            L_0x002d:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002d }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.getBlockDefault():java.lang.Object");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.BlockSet xgetBlockDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
                r3 = 14
                r4 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.BlockSet) r1     // Catch:{ all -> 0x0025 }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.BlockSet) r1     // Catch:{ all -> 0x0025 }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                return r1
            L_0x0025:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xgetBlockDefault():org.apache.xmlbeans.impl.xb.xsdschema.BlockSet");
        }

        public boolean isSetBlockDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setBlockDefault(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 14
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setBlockDefault(java.lang.Object):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetBlockDefault(org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 14
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.BlockSet) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.BlockSet r1 = (org.apache.xmlbeans.impl.xb.xsdschema.BlockSet) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetBlockDefault(org.apache.xmlbeans.impl.xb.xsdschema.BlockSet):void");
        }

        public void unsetBlockDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[14]);
            }
        }

        /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum getAttributeFormDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002f }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002f }
                r3 = 15
                r4 = r2[r3]     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002f }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
            L_0x0023:
                if (r1 != 0) goto L_0x0027
                r5 = 0
                goto L_0x002d
            L_0x0027:
                org.apache.xmlbeans.StringEnumAbstractBase r5 = r1.getEnumValue()     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum r5 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum) r5     // Catch:{ all -> 0x002f }
            L_0x002d:
                monitor-exit(r0)     // Catch:{ all -> 0x002f }
                return r5
            L_0x002f:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002f }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.getAttributeFormDefault():org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.FormChoice xgetAttributeFormDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
                r3 = 15
                r4 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x0025 }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x0025 }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                return r1
            L_0x0025:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xgetAttributeFormDefault():org.apache.xmlbeans.impl.xb.xsdschema.FormChoice");
        }

        public boolean isSetAttributeFormDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setAttributeFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 15
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setAttributeFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetAttributeFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 15
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetAttributeFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice):void");
        }

        public void unsetAttributeFormDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[15]);
            }
        }

        /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum getElementFormDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002f }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002f }
                r3 = 16
                r4 = r2[r3]     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002f }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
            L_0x0023:
                if (r1 != 0) goto L_0x0027
                r5 = 0
                goto L_0x002d
            L_0x0027:
                org.apache.xmlbeans.StringEnumAbstractBase r5 = r1.getEnumValue()     // Catch:{ all -> 0x002f }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum r5 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum) r5     // Catch:{ all -> 0x002f }
            L_0x002d:
                monitor-exit(r0)     // Catch:{ all -> 0x002f }
                return r5
            L_0x002f:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002f }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.getElementFormDefault():org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum");
        }

        /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public org.apache.xmlbeans.impl.xb.xsdschema.FormChoice xgetElementFormDefault() {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
                r3 = 16
                r4 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x0025 }
                if (r1 != 0) goto L_0x0023
                r1 = r2[r3]     // Catch:{ all -> 0x0025 }
                org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x0025 }
            L_0x0023:
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                return r1
            L_0x0025:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0025 }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xgetElementFormDefault():org.apache.xmlbeans.impl.xb.xsdschema.FormChoice");
        }

        public boolean isSetElementFormDefault() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[16]) != null;
            }
            return z;
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void setElementFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice.Enum r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 16
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setElementFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice$Enum):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetElementFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 16
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xsdschema.FormChoice r1 = (org.apache.xmlbeans.impl.xb.xsdschema.FormChoice) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetElementFormDefault(org.apache.xmlbeans.impl.xb.xsdschema.FormChoice):void");
        }

        public void unsetElementFormDefault() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[16]);
            }
        }

        public String getId() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[17]);
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
                xmlID = (XmlID) get_store().find_attribute_user(PROPERTY_QNAME[17]);
            }
            return xmlID;
        }

        public boolean isSetId() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[17]) != null;
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
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 17
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setId(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetId(org.apache.xmlbeans.XmlID r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 17
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.XmlID r1 = (org.apache.xmlbeans.XmlID) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetId(org.apache.xmlbeans.XmlID):void");
        }

        public void unsetId() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[17]);
            }
        }

        public String getLang() {
            String str;
            synchronized (monitor()) {
                check_orphaned();
                SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[18]);
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
                lang = (LangAttribute.Lang) get_store().find_attribute_user(PROPERTY_QNAME[18]);
            }
            return lang;
        }

        public boolean isSetLang() {
            boolean z;
            synchronized (monitor()) {
                check_orphaned();
                z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
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
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 18
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.setLang(java.lang.String):void");
        }

        /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void xsetLang(org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang r6) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.monitor()
                monitor-enter(r0)
                r5.check_orphaned()     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
                javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
                r3 = 18
                r4 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang r1 = (org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang) r1     // Catch:{ all -> 0x002c }
                if (r1 != 0) goto L_0x0027
                org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
                r1 = r2[r3]     // Catch:{ all -> 0x002c }
                org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
                r1 = r5
                org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang r1 = (org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute.Lang) r1     // Catch:{ all -> 0x002c }
            L_0x0027:
                r1.set(r6)     // Catch:{ all -> 0x002c }
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                return
            L_0x002c:
                r5 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x002c }
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xb.xsdschema.impl.SchemaDocumentImpl.SchemaImpl.xsetLang(org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute$Lang):void");
        }

        public void unsetLang() {
            synchronized (monitor()) {
                check_orphaned();
                get_store().remove_attribute(PROPERTY_QNAME[18]);
            }
        }
    }
}
