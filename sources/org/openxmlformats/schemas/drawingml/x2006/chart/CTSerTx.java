package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STXstring;

public interface CTSerTx extends XmlObject {
    public static final DocumentFactory<CTSerTx> Factory;
    public static final SchemaType type;

    CTStrRef addNewStrRef();

    CTStrRef getStrRef();

    String getV();

    boolean isSetStrRef();

    boolean isSetV();

    void setStrRef(CTStrRef cTStrRef);

    void setV(String str);

    void unsetStrRef();

    void unsetV();

    STXstring xgetV();

    void xsetV(STXstring sTXstring);

    static {
        DocumentFactory<CTSerTx> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctsertxd722type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
