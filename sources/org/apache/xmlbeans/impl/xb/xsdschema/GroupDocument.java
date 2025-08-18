package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface GroupDocument extends XmlObject {
    public static final DocumentFactory<GroupDocument> Factory;
    public static final SchemaType type;

    NamedGroup addNewGroup();

    NamedGroup getGroup();

    void setGroup(NamedGroup namedGroup);

    static {
        DocumentFactory<GroupDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "group6eb6doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
