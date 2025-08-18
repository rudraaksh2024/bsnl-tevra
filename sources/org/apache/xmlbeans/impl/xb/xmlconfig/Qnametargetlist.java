package org.apache.xmlbeans.impl.xb.xmlconfig;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.metadata.system.sXMLCONFIG.TypeSystemHolder;

public interface Qnametargetlist extends XmlAnySimpleType {
    public static final SimpleTypeFactory<Qnametargetlist> Factory;
    public static final SchemaType type;

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();

    static {
        SimpleTypeFactory<Qnametargetlist> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "qnametargetlist16actype");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
