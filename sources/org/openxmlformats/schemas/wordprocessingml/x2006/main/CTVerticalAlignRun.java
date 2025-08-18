package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;

public interface CTVerticalAlignRun extends XmlObject {
    public static final DocumentFactory<CTVerticalAlignRun> Factory;
    public static final SchemaType type;

    STVerticalAlignRun.Enum getVal();

    void setVal(STVerticalAlignRun.Enum enumR);

    STVerticalAlignRun xgetVal();

    void xsetVal(STVerticalAlignRun sTVerticalAlignRun);

    static {
        DocumentFactory<CTVerticalAlignRun> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctverticalalignruncb8ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
