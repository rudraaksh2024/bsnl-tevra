package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextTabAlignType;

public interface CTTextTabStop extends XmlObject {
    public static final DocumentFactory<CTTextTabStop> Factory;
    public static final SchemaType type;

    STTextTabAlignType.Enum getAlgn();

    Object getPos();

    boolean isSetAlgn();

    boolean isSetPos();

    void setAlgn(STTextTabAlignType.Enum enumR);

    void setPos(Object obj);

    void unsetAlgn();

    void unsetPos();

    STTextTabAlignType xgetAlgn();

    STCoordinate32 xgetPos();

    void xsetAlgn(STTextTabAlignType sTTextTabAlignType);

    void xsetPos(STCoordinate32 sTCoordinate32);

    static {
        DocumentFactory<CTTextTabStop> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttexttabstopb57btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
