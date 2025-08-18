package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STUnqualifiedPercentage extends XmlInteger {
    public static final SimpleTypeFactory<STUnqualifiedPercentage> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STUnqualifiedPercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stunqualifiedpercentage47e5type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
