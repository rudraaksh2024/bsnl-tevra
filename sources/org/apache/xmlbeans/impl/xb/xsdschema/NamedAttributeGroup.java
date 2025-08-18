package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface NamedAttributeGroup extends AttributeGroup {
    public static final DocumentFactory<NamedAttributeGroup> Factory;
    public static final SchemaType type;

    String getName();

    boolean isSetName();

    void setName(String str);

    void unsetName();

    XmlNCName xgetName();

    void xsetName(XmlNCName xmlNCName);

    static {
        DocumentFactory<NamedAttributeGroup> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "namedattributegroup2e29type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
