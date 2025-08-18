package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STPathShadeType;

public interface CTPathShadeProperties extends XmlObject {
    public static final DocumentFactory<CTPathShadeProperties> Factory;
    public static final SchemaType type;

    CTRelativeRect addNewFillToRect();

    CTRelativeRect getFillToRect();

    STPathShadeType.Enum getPath();

    boolean isSetFillToRect();

    boolean isSetPath();

    void setFillToRect(CTRelativeRect cTRelativeRect);

    void setPath(STPathShadeType.Enum enumR);

    void unsetFillToRect();

    void unsetPath();

    STPathShadeType xgetPath();

    void xsetPath(STPathShadeType sTPathShadeType);

    static {
        DocumentFactory<CTPathShadeProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpathshadeproperties7ccctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
