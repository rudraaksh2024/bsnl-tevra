package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

public interface CTVerticalJc extends XmlObject {
    public static final DocumentFactory<CTVerticalJc> Factory;
    public static final SchemaType type;

    STVerticalJc.Enum getVal();

    void setVal(STVerticalJc.Enum enumR);

    STVerticalJc xgetVal();

    void xsetVal(STVerticalJc sTVerticalJc);

    static {
        DocumentFactory<CTVerticalJc> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctverticaljca439type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
