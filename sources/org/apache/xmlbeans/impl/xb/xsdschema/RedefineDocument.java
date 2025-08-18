package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface RedefineDocument extends XmlObject {
    public static final DocumentFactory<RedefineDocument> Factory;
    public static final SchemaType type;

    Redefine addNewRedefine();

    Redefine getRedefine();

    void setRedefine(Redefine redefine);

    static {
        DocumentFactory<RedefineDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "redefine3f55doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Redefine extends OpenAttrs {
        public static final ElementFactory<Redefine> Factory;
        public static final SchemaType type;

        AnnotationDocument.Annotation addNewAnnotation();

        NamedAttributeGroup addNewAttributeGroup();

        TopLevelComplexType addNewComplexType();

        NamedGroup addNewGroup();

        TopLevelSimpleType addNewSimpleType();

        AnnotationDocument.Annotation getAnnotationArray(int i);

        AnnotationDocument.Annotation[] getAnnotationArray();

        List<AnnotationDocument.Annotation> getAnnotationList();

        NamedAttributeGroup getAttributeGroupArray(int i);

        NamedAttributeGroup[] getAttributeGroupArray();

        List<NamedAttributeGroup> getAttributeGroupList();

        TopLevelComplexType getComplexTypeArray(int i);

        TopLevelComplexType[] getComplexTypeArray();

        List<TopLevelComplexType> getComplexTypeList();

        NamedGroup getGroupArray(int i);

        NamedGroup[] getGroupArray();

        List<NamedGroup> getGroupList();

        String getId();

        String getSchemaLocation();

        TopLevelSimpleType getSimpleTypeArray(int i);

        TopLevelSimpleType[] getSimpleTypeArray();

        List<TopLevelSimpleType> getSimpleTypeList();

        AnnotationDocument.Annotation insertNewAnnotation(int i);

        NamedAttributeGroup insertNewAttributeGroup(int i);

        TopLevelComplexType insertNewComplexType(int i);

        NamedGroup insertNewGroup(int i);

        TopLevelSimpleType insertNewSimpleType(int i);

        boolean isSetId();

        void removeAnnotation(int i);

        void removeAttributeGroup(int i);

        void removeComplexType(int i);

        void removeGroup(int i);

        void removeSimpleType(int i);

        void setAnnotationArray(int i, AnnotationDocument.Annotation annotation);

        void setAnnotationArray(AnnotationDocument.Annotation[] annotationArr);

        void setAttributeGroupArray(int i, NamedAttributeGroup namedAttributeGroup);

        void setAttributeGroupArray(NamedAttributeGroup[] namedAttributeGroupArr);

        void setComplexTypeArray(int i, TopLevelComplexType topLevelComplexType);

        void setComplexTypeArray(TopLevelComplexType[] topLevelComplexTypeArr);

        void setGroupArray(int i, NamedGroup namedGroup);

        void setGroupArray(NamedGroup[] namedGroupArr);

        void setId(String str);

        void setSchemaLocation(String str);

        void setSimpleTypeArray(int i, TopLevelSimpleType topLevelSimpleType);

        void setSimpleTypeArray(TopLevelSimpleType[] topLevelSimpleTypeArr);

        int sizeOfAnnotationArray();

        int sizeOfAttributeGroupArray();

        int sizeOfComplexTypeArray();

        int sizeOfGroupArray();

        int sizeOfSimpleTypeArray();

        void unsetId();

        XmlID xgetId();

        XmlAnyURI xgetSchemaLocation();

        void xsetId(XmlID xmlID);

        void xsetSchemaLocation(XmlAnyURI xmlAnyURI);

        static {
            ElementFactory<Redefine> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "redefine9e9felemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
