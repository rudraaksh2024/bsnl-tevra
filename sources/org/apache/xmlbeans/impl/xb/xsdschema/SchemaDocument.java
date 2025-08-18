package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xmlschema.LangAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.ImportDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.IncludeDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.NotationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.RedefineDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SchemaDocument extends XmlObject {
    public static final DocumentFactory<SchemaDocument> Factory;
    public static final SchemaType type;

    Schema addNewSchema();

    Schema getSchema();

    void setSchema(Schema schema);

    static {
        DocumentFactory<SchemaDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "schema0782doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Schema extends OpenAttrs {
        public static final ElementFactory<Schema> Factory;
        public static final SchemaType type;

        AnnotationDocument.Annotation addNewAnnotation();

        TopLevelAttribute addNewAttribute();

        NamedAttributeGroup addNewAttributeGroup();

        TopLevelComplexType addNewComplexType();

        TopLevelElement addNewElement();

        NamedGroup addNewGroup();

        ImportDocument.Import addNewImport();

        IncludeDocument.Include addNewInclude();

        NotationDocument.Notation addNewNotation();

        RedefineDocument.Redefine addNewRedefine();

        TopLevelSimpleType addNewSimpleType();

        AnnotationDocument.Annotation getAnnotationArray(int i);

        AnnotationDocument.Annotation[] getAnnotationArray();

        List<AnnotationDocument.Annotation> getAnnotationList();

        TopLevelAttribute getAttributeArray(int i);

        TopLevelAttribute[] getAttributeArray();

        FormChoice.Enum getAttributeFormDefault();

        NamedAttributeGroup getAttributeGroupArray(int i);

        NamedAttributeGroup[] getAttributeGroupArray();

        List<NamedAttributeGroup> getAttributeGroupList();

        List<TopLevelAttribute> getAttributeList();

        Object getBlockDefault();

        TopLevelComplexType getComplexTypeArray(int i);

        TopLevelComplexType[] getComplexTypeArray();

        List<TopLevelComplexType> getComplexTypeList();

        TopLevelElement getElementArray(int i);

        TopLevelElement[] getElementArray();

        FormChoice.Enum getElementFormDefault();

        List<TopLevelElement> getElementList();

        Object getFinalDefault();

        NamedGroup getGroupArray(int i);

        NamedGroup[] getGroupArray();

        List<NamedGroup> getGroupList();

        String getId();

        ImportDocument.Import getImportArray(int i);

        ImportDocument.Import[] getImportArray();

        List<ImportDocument.Import> getImportList();

        IncludeDocument.Include getIncludeArray(int i);

        IncludeDocument.Include[] getIncludeArray();

        List<IncludeDocument.Include> getIncludeList();

        String getLang();

        NotationDocument.Notation getNotationArray(int i);

        NotationDocument.Notation[] getNotationArray();

        List<NotationDocument.Notation> getNotationList();

        RedefineDocument.Redefine getRedefineArray(int i);

        RedefineDocument.Redefine[] getRedefineArray();

        List<RedefineDocument.Redefine> getRedefineList();

        TopLevelSimpleType getSimpleTypeArray(int i);

        TopLevelSimpleType[] getSimpleTypeArray();

        List<TopLevelSimpleType> getSimpleTypeList();

        String getTargetNamespace();

        String getVersion();

        AnnotationDocument.Annotation insertNewAnnotation(int i);

        TopLevelAttribute insertNewAttribute(int i);

        NamedAttributeGroup insertNewAttributeGroup(int i);

        TopLevelComplexType insertNewComplexType(int i);

        TopLevelElement insertNewElement(int i);

        NamedGroup insertNewGroup(int i);

        ImportDocument.Import insertNewImport(int i);

        IncludeDocument.Include insertNewInclude(int i);

        NotationDocument.Notation insertNewNotation(int i);

        RedefineDocument.Redefine insertNewRedefine(int i);

        TopLevelSimpleType insertNewSimpleType(int i);

        boolean isSetAttributeFormDefault();

        boolean isSetBlockDefault();

        boolean isSetElementFormDefault();

        boolean isSetFinalDefault();

        boolean isSetId();

        boolean isSetLang();

        boolean isSetTargetNamespace();

        boolean isSetVersion();

        void removeAnnotation(int i);

        void removeAttribute(int i);

        void removeAttributeGroup(int i);

        void removeComplexType(int i);

        void removeElement(int i);

        void removeGroup(int i);

        void removeImport(int i);

        void removeInclude(int i);

        void removeNotation(int i);

        void removeRedefine(int i);

        void removeSimpleType(int i);

        void setAnnotationArray(int i, AnnotationDocument.Annotation annotation);

        void setAnnotationArray(AnnotationDocument.Annotation[] annotationArr);

        void setAttributeArray(int i, TopLevelAttribute topLevelAttribute);

        void setAttributeArray(TopLevelAttribute[] topLevelAttributeArr);

        void setAttributeFormDefault(FormChoice.Enum enumR);

        void setAttributeGroupArray(int i, NamedAttributeGroup namedAttributeGroup);

        void setAttributeGroupArray(NamedAttributeGroup[] namedAttributeGroupArr);

        void setBlockDefault(Object obj);

        void setComplexTypeArray(int i, TopLevelComplexType topLevelComplexType);

        void setComplexTypeArray(TopLevelComplexType[] topLevelComplexTypeArr);

        void setElementArray(int i, TopLevelElement topLevelElement);

        void setElementArray(TopLevelElement[] topLevelElementArr);

        void setElementFormDefault(FormChoice.Enum enumR);

        void setFinalDefault(Object obj);

        void setGroupArray(int i, NamedGroup namedGroup);

        void setGroupArray(NamedGroup[] namedGroupArr);

        void setId(String str);

        void setImportArray(int i, ImportDocument.Import importR);

        void setImportArray(ImportDocument.Import[] importArr);

        void setIncludeArray(int i, IncludeDocument.Include include);

        void setIncludeArray(IncludeDocument.Include[] includeArr);

        void setLang(String str);

        void setNotationArray(int i, NotationDocument.Notation notation);

        void setNotationArray(NotationDocument.Notation[] notationArr);

        void setRedefineArray(int i, RedefineDocument.Redefine redefine);

        void setRedefineArray(RedefineDocument.Redefine[] redefineArr);

        void setSimpleTypeArray(int i, TopLevelSimpleType topLevelSimpleType);

        void setSimpleTypeArray(TopLevelSimpleType[] topLevelSimpleTypeArr);

        void setTargetNamespace(String str);

        void setVersion(String str);

        int sizeOfAnnotationArray();

        int sizeOfAttributeArray();

        int sizeOfAttributeGroupArray();

        int sizeOfComplexTypeArray();

        int sizeOfElementArray();

        int sizeOfGroupArray();

        int sizeOfImportArray();

        int sizeOfIncludeArray();

        int sizeOfNotationArray();

        int sizeOfRedefineArray();

        int sizeOfSimpleTypeArray();

        void unsetAttributeFormDefault();

        void unsetBlockDefault();

        void unsetElementFormDefault();

        void unsetFinalDefault();

        void unsetId();

        void unsetLang();

        void unsetTargetNamespace();

        void unsetVersion();

        FormChoice xgetAttributeFormDefault();

        BlockSet xgetBlockDefault();

        FormChoice xgetElementFormDefault();

        FullDerivationSet xgetFinalDefault();

        XmlID xgetId();

        LangAttribute.Lang xgetLang();

        XmlAnyURI xgetTargetNamespace();

        XmlToken xgetVersion();

        void xsetAttributeFormDefault(FormChoice formChoice);

        void xsetBlockDefault(BlockSet blockSet);

        void xsetElementFormDefault(FormChoice formChoice);

        void xsetFinalDefault(FullDerivationSet fullDerivationSet);

        void xsetId(XmlID xmlID);

        void xsetLang(LangAttribute.Lang lang);

        void xsetTargetNamespace(XmlAnyURI xmlAnyURI);

        void xsetVersion(XmlToken xmlToken);

        static {
            ElementFactory<Schema> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "schemad77felemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
