package org.apache.xmlbeans.impl.xb.xmlconfig;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface JavaName extends XmlToken {
    public static final SimpleTypeFactory<JavaName> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<JavaName> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "javanamee640type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
