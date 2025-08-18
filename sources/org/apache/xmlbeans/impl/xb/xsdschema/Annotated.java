package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlID;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.AnnotationDocument;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Annotated extends OpenAttrs {
    public static final DocumentFactory<Annotated> Factory;
    public static final SchemaType type;

    AnnotationDocument.Annotation addNewAnnotation();

    AnnotationDocument.Annotation getAnnotation();

    String getId();

    boolean isSetAnnotation();

    boolean isSetId();

    void setAnnotation(AnnotationDocument.Annotation annotation);

    void setId(String str);

    void unsetAnnotation();

    void unsetId();

    XmlID xgetId();

    void xsetId(XmlID xmlID);

    static {
        DocumentFactory<Annotated> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "annotateda52dtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
