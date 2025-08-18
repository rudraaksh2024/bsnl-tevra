package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STVerticalAlignRun;

public interface CTVerticalAlignFontProperty extends XmlObject {
    public static final DocumentFactory<CTVerticalAlignFontProperty> Factory;
    public static final SchemaType type;

    STVerticalAlignRun.Enum getVal();

    void setVal(STVerticalAlignRun.Enum enumR);

    STVerticalAlignRun xgetVal();

    void xsetVal(STVerticalAlignRun sTVerticalAlignRun);

    static {
        DocumentFactory<CTVerticalAlignFontProperty> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctverticalalignfontproperty89f2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
