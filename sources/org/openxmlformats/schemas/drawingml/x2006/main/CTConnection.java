package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTConnection extends XmlObject {
    public static final DocumentFactory<CTConnection> Factory;
    public static final SchemaType type;

    long getId();

    long getIdx();

    void setId(long j);

    void setIdx(long j);

    STDrawingElementId xgetId();

    XmlUnsignedInt xgetIdx();

    void xsetId(STDrawingElementId sTDrawingElementId);

    void xsetIdx(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTConnection> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnection7fb9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
