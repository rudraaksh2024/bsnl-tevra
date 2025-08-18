package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STSqref extends XmlAnySimpleType {
    public static final SimpleTypeFactory<STSqref> Factory;
    public static final SchemaType type;

    List getListValue();

    void setListValue(List<?> list);

    List xgetListValue();

    static {
        SimpleTypeFactory<STSqref> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stsqrefb044type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
