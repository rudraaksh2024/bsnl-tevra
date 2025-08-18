package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ChartsheetDocument extends XmlObject {
    public static final DocumentFactory<ChartsheetDocument> Factory;
    public static final SchemaType type;

    CTChartsheet addNewChartsheet();

    CTChartsheet getChartsheet();

    void setChartsheet(CTChartsheet cTChartsheet);

    static {
        DocumentFactory<ChartsheetDocument> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "chartsheet99dedoctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
