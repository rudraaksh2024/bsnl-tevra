package org.apache.xmlbeans;

import java.util.List;
import org.apache.xmlbeans.impl.schema.XmlObjectFactory;

public interface XmlNMTOKENS extends XmlAnySimpleType {
    public static final XmlObjectFactory<XmlNMTOKENS> Factory;
    public static final SchemaType type;

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();

    static {
        XmlObjectFactory<XmlNMTOKENS> xmlObjectFactory = new XmlObjectFactory<>("_BI_NMTOKENS");
        Factory = xmlObjectFactory;
        type = xmlObjectFactory.getType();
    }
}
