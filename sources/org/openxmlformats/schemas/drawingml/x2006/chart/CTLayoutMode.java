package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.chart.STLayoutMode;

public interface CTLayoutMode extends XmlObject {
    public static final DocumentFactory<CTLayoutMode> Factory;
    public static final SchemaType type;

    STLayoutMode.Enum getVal();

    boolean isSetVal();

    void setVal(STLayoutMode.Enum enumR);

    void unsetVal();

    STLayoutMode xgetVal();

    void xsetVal(STLayoutMode sTLayoutMode);

    static {
        DocumentFactory<CTLayoutMode> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctlayoutmode53eftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
