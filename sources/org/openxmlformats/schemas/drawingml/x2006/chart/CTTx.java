package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public interface CTTx extends XmlObject {
    public static final DocumentFactory<CTTx> Factory;
    public static final SchemaType type;

    CTTextBody addNewRich();

    CTStrRef addNewStrRef();

    CTTextBody getRich();

    CTStrRef getStrRef();

    boolean isSetRich();

    boolean isSetStrRef();

    void setRich(CTTextBody cTTextBody);

    void setStrRef(CTStrRef cTStrRef);

    void unsetRich();

    void unsetStrRef();

    static {
        DocumentFactory<CTTx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttx9678type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
