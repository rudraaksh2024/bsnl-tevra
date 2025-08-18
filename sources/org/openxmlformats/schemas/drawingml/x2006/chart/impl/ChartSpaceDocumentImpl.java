package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartSpace;
import org.openxmlformats.schemas.drawingml.x2006.chart.ChartSpaceDocument;

public class ChartSpaceDocumentImpl extends XmlComplexContentImpl implements ChartSpaceDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "chartSpace")};
    private static final long serialVersionUID = 1;

    public ChartSpaceDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTChartSpace getChartSpace() {
        CTChartSpace cTChartSpace;
        synchronized (monitor()) {
            check_orphaned();
            cTChartSpace = (CTChartSpace) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTChartSpace == null) {
                cTChartSpace = null;
            }
        }
        return cTChartSpace;
    }

    public void setChartSpace(CTChartSpace cTChartSpace) {
        generatedSetterHelperImpl(cTChartSpace, PROPERTY_QNAME[0], 0, 1);
    }

    public CTChartSpace addNewChartSpace() {
        CTChartSpace cTChartSpace;
        synchronized (monitor()) {
            check_orphaned();
            cTChartSpace = (CTChartSpace) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTChartSpace;
    }
}
