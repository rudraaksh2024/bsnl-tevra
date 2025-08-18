package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTChartsheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.ChartsheetDocument;

public class ChartsheetDocumentImpl extends XmlComplexContentImpl implements ChartsheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "chartsheet")};
    private static final long serialVersionUID = 1;

    public ChartsheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTChartsheet getChartsheet() {
        CTChartsheet cTChartsheet;
        synchronized (monitor()) {
            check_orphaned();
            cTChartsheet = (CTChartsheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTChartsheet == null) {
                cTChartsheet = null;
            }
        }
        return cTChartsheet;
    }

    public void setChartsheet(CTChartsheet cTChartsheet) {
        generatedSetterHelperImpl(cTChartsheet, PROPERTY_QNAME[0], 0, 1);
    }

    public CTChartsheet addNewChartsheet() {
        CTChartsheet cTChartsheet;
        synchronized (monitor()) {
            check_orphaned();
            cTChartsheet = (CTChartsheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTChartsheet;
    }
}
