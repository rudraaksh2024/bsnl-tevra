package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ElementDocument extends XmlObject {
    public static final DocumentFactory<ElementDocument> Factory;
    public static final SchemaType type;

    TopLevelElement addNewElement();

    TopLevelElement getElement();

    void setElement(TopLevelElement topLevelElement);

    static {
        DocumentFactory<ElementDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "element7f99doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
