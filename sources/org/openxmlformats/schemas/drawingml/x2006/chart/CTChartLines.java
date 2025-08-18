package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTChartLines extends XmlObject {
    public static final DocumentFactory<CTChartLines> Factory;
    public static final SchemaType type;

    CTShapeProperties addNewSpPr();

    CTShapeProperties getSpPr();

    boolean isSetSpPr();

    void setSpPr(CTShapeProperties cTShapeProperties);

    void unsetSpPr();

    static {
        DocumentFactory<CTChartLines> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctchartlines979btype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
