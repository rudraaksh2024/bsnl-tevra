package org.openxmlformats.schemas.wordprocessingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

public interface CTTblWidth extends XmlObject {
    public static final DocumentFactory<CTTblWidth> Factory;
    public static final SchemaType type;

    STTblWidth.Enum getType();

    Object getW();

    boolean isSetType();

    boolean isSetW();

    void setType(STTblWidth.Enum enumR);

    void setW(Object obj);

    void unsetType();

    void unsetW();

    STTblWidth xgetType();

    STMeasurementOrPercent xgetW();

    void xsetType(STTblWidth sTTblWidth);

    void xsetW(STMeasurementOrPercent sTMeasurementOrPercent);

    static {
        DocumentFactory<CTTblWidth> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttblwidthec40type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
