package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTWorksheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.WorksheetDocument;

public class WorksheetDocumentImpl extends XmlComplexContentImpl implements WorksheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "worksheet")};
    private static final long serialVersionUID = 1;

    public WorksheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTWorksheet getWorksheet() {
        CTWorksheet cTWorksheet;
        synchronized (monitor()) {
            check_orphaned();
            cTWorksheet = (CTWorksheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTWorksheet == null) {
                cTWorksheet = null;
            }
        }
        return cTWorksheet;
    }

    public void setWorksheet(CTWorksheet cTWorksheet) {
        generatedSetterHelperImpl(cTWorksheet, PROPERTY_QNAME[0], 0, 1);
    }

    public CTWorksheet addNewWorksheet() {
        CTWorksheet cTWorksheet;
        synchronized (monitor()) {
            check_orphaned();
            cTWorksheet = (CTWorksheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTWorksheet;
    }
}
