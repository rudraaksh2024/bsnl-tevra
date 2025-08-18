package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AnnotationDocument extends XmlObject {
    public static final DocumentFactory<AnnotationDocument> Factory;
    public static final SchemaType type;

    Annotation addNewAnnotation();

    Annotation getAnnotation();

    void setAnnotation(Annotation annotation);

    static {
        DocumentFactory<AnnotationDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "annotationb034doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }

    public interface Annotation extends OpenAttrs {
        public static final ElementFactory<Annotation> Factory;
        public static final SchemaType type;

        AppinfoDocument.Appinfo addNewAppinfo();

        DocumentationDocument.Documentation addNewDocumentation();

        AppinfoDocument.Appinfo getAppinfoArray(int i);

        AppinfoDocument.Appinfo[] getAppinfoArray();

        List<AppinfoDocument.Appinfo> getAppinfoList();

        DocumentationDocument.Documentation getDocumentationArray(int i);

        DocumentationDocument.Documentation[] getDocumentationArray();

        List<DocumentationDocument.Documentation> getDocumentationList();

        String getId();

        AppinfoDocument.Appinfo insertNewAppinfo(int i);

        DocumentationDocument.Documentation insertNewDocumentation(int i);

        boolean isSetId();

        void removeAppinfo(int i);

        void removeDocumentation(int i);

        void setAppinfoArray(int i, AppinfoDocument.Appinfo appinfo);

        void setAppinfoArray(AppinfoDocument.Appinfo[] appinfoArr);

        void setDocumentationArray(int i, DocumentationDocument.Documentation documentation);

        void setDocumentationArray(DocumentationDocument.Documentation[] documentationArr);

        void setId(String str);

        int sizeOfAppinfoArray();

        int sizeOfDocumentationArray();

        void unsetId();

        XmlID xgetId();

        void xsetId(XmlID xmlID);

        static {
            ElementFactory<Annotation> elementFactory = new ElementFactory<>(TypeSystemHolder.typeSystem, "annotation5abfelemtype");
            Factory = elementFactory;
            type = elementFactory.getType();
        }
    }
}
