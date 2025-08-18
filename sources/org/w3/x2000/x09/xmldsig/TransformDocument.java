package org.w3.x2000.x09.xmldsig;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface TransformDocument extends XmlObject {
    public static final DocumentFactory<TransformDocument> Factory;
    public static final SchemaType type;

    TransformType addNewTransform();

    TransformType getTransform();

    void setTransform(TransformType transformType);

    static {
        DocumentFactory<TransformDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "transforme335doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
