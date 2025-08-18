package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlNCName;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface TopLevelElement extends Element {
    public static final DocumentFactory<TopLevelElement> Factory;
    public static final SchemaType type;

    String getName();

    boolean isSetName();

    void setName(String str);

    void unsetName();

    XmlNCName xgetName();

    void xsetName(XmlNCName xmlNCName);

    static {
        DocumentFactory<TopLevelElement> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "toplevelelement98d8type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
