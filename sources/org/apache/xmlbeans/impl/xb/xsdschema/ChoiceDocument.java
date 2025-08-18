package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface ChoiceDocument extends XmlObject {
    public static final DocumentFactory<ChoiceDocument> Factory;
    public static final SchemaType type;

    ExplicitGroup addNewChoice();

    ExplicitGroup getChoice();

    void setChoice(ExplicitGroup explicitGroup);

    static {
        DocumentFactory<ChoiceDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "choicedf82doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
