package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface AttributeGroupDocument extends XmlObject {
    public static final DocumentFactory<AttributeGroupDocument> Factory;
    public static final SchemaType type;

    NamedAttributeGroup addNewAttributeGroup();

    NamedAttributeGroup getAttributeGroup();

    void setAttributeGroup(NamedAttributeGroup namedAttributeGroup);

    static {
        DocumentFactory<AttributeGroupDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "attributegroup4520doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
