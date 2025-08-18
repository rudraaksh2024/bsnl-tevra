package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTStrVal extends XmlObject {
    public static final DocumentFactory<CTStrVal> Factory;
    public static final SchemaType type;

    long getIdx();

    String getV();

    void setIdx(long j);

    void setV(String str);

    XmlUnsignedInt xgetIdx();

    STXstring xgetV();

    void xsetIdx(XmlUnsignedInt xmlUnsignedInt);

    void xsetV(STXstring sTXstring);

    static {
        DocumentFactory<CTStrVal> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctstrval86cctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
