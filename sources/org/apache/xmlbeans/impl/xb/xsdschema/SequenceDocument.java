package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface SequenceDocument extends XmlObject {
    public static final DocumentFactory<SequenceDocument> Factory;
    public static final SchemaType type;

    ExplicitGroup addNewSequence();

    ExplicitGroup getSequence();

    void setSequence(ExplicitGroup explicitGroup);

    static {
        DocumentFactory<SequenceDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "sequencecba2doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
