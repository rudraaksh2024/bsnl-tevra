package org.apache.xmlbeans;

import java.util.List;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlIDREFS extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlIDREFS> Factory;
    public static final SchemaType type;

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();

    static {
        XmlObjectFactory<XmlIDREFS> xmlObjectFactory = new XmlObjectFactory<>("_BI_IDREFS");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
