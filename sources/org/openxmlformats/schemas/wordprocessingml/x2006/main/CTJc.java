package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

public interface CTJc extends XmlObject {
    public static final DocumentFactory<CTJc> Factory;
    public static final SchemaType type;

    STJc.Enum getVal();

    void setVal(STJc.Enum enumR);

    STJc xgetVal();

    void xsetVal(STJc sTJc);

    static {
        DocumentFactory<CTJc> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctjc158ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
