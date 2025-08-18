package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

public interface Public extends XmlToken {
    public static final SimpleTypeFactory<Public> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<Public> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "publicf3catype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
