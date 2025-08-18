package org.openxmlformats.schemas.officeDocument.x2006.sharedTypes;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;

public interface STPercentage extends XmlString {
    public static final SimpleTypeFactory<STPercentage> Factory;
    public static final SchemaType type;

    static {
        SimpleTypeFactory<STPercentage> simpleTypeFactory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "stpercentagea094type");
        Factory = simpleTypeFactory;
        type = simpleTypeFactory.getType();
    }
}
