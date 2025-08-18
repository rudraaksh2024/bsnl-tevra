package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;

public interface CTLineEndProperties extends XmlObject {
    public static final DocumentFactory<CTLineEndProperties> Factory;
    public static final SchemaType type;

    STLineEndLength.Enum getLen();

    STLineEndType.Enum getType();

    STLineEndWidth.Enum getW();

    boolean isSetLen();

    boolean isSetType();

    boolean isSetW();

    void setLen(STLineEndLength.Enum enumR);

    void setType(STLineEndType.Enum enumR);

    void setW(STLineEndWidth.Enum enumR);

    void unsetLen();

    void unsetType();

    void unsetW();

    STLineEndLength xgetLen();

    STLineEndType xgetType();

    STLineEndWidth xgetW();

    void xsetLen(STLineEndLength sTLineEndLength);

    void xsetType(STLineEndType sTLineEndType);

    void xsetW(STLineEndWidth sTLineEndWidth);

    static {
        DocumentFactory<CTLineEndProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlineendproperties8acbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
