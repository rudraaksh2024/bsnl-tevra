package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ChartSpaceDocument extends XmlObject {
    public static final DocumentFactory<ChartSpaceDocument> Factory;
    public static final SchemaType type;

    CTChartSpace addNewChartSpace();

    CTChartSpace getChartSpace();

    void setChartSpace(CTChartSpace cTChartSpace);

    static {
        DocumentFactory<ChartSpaceDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "chartspace36e0doctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
