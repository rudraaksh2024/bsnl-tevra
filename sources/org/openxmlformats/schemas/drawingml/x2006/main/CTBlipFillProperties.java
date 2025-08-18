package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBlipFillProperties extends XmlObject {
    public static final DocumentFactory<CTBlipFillProperties> Factory;
    public static final SchemaType type;

    CTBlip addNewBlip();

    CTRelativeRect addNewSrcRect();

    CTStretchInfoProperties addNewStretch();

    CTTileInfoProperties addNewTile();

    CTBlip getBlip();

    long getDpi();

    boolean getRotWithShape();

    CTRelativeRect getSrcRect();

    CTStretchInfoProperties getStretch();

    CTTileInfoProperties getTile();

    boolean isSetBlip();

    boolean isSetDpi();

    boolean isSetRotWithShape();

    boolean isSetSrcRect();

    boolean isSetStretch();

    boolean isSetTile();

    void setBlip(CTBlip cTBlip);

    void setDpi(long j);

    void setRotWithShape(boolean z);

    void setSrcRect(CTRelativeRect cTRelativeRect);

    void setStretch(CTStretchInfoProperties cTStretchInfoProperties);

    void setTile(CTTileInfoProperties cTTileInfoProperties);

    void unsetBlip();

    void unsetDpi();

    void unsetRotWithShape();

    void unsetSrcRect();

    void unsetStretch();

    void unsetTile();

    XmlUnsignedInt xgetDpi();

    XmlBoolean xgetRotWithShape();

    void xsetDpi(XmlUnsignedInt xmlUnsignedInt);

    void xsetRotWithShape(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTBlipFillProperties> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctblipfillproperties0382type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
