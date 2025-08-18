package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

public interface CTHighlight extends XmlObject {
    public static final DocumentFactory<CTHighlight> Factory;
    public static final SchemaType type;

    STHighlightColor.Enum getVal();

    void setVal(STHighlightColor.Enum enumR);

    STHighlightColor xgetVal();

    void xsetVal(STHighlightColor sTHighlightColor);

    static {
        DocumentFactory<CTHighlight> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cthighlight071etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
