package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPercentage extends XmlObject {
    public static final DocumentFactory<CTPercentage> Factory;
    public static final SchemaType type;

    Object getVal();

    void setVal(Object obj);

    STPercentage xgetVal();

    void xsetVal(STPercentage sTPercentage);

    static {
        DocumentFactory<CTPercentage> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpercentage4e75type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
