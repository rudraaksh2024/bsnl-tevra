package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AllDocument extends XmlObject {
    public static final DocumentFactory<AllDocument> Factory;
    public static final SchemaType type;

    All addNewAll();

    All getAll();

    void setAll(All all);

    static {
        DocumentFactory<AllDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "all7214doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
