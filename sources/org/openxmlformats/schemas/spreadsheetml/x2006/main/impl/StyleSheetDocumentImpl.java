package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

public class StyleSheetDocumentImpl extends XmlComplexContentImpl implements StyleSheetDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "styleSheet")};
    private static final long serialVersionUID = 1;

    public StyleSheetDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTStylesheet getStyleSheet() {
        CTStylesheet cTStylesheet;
        synchronized (monitor()) {
            check_orphaned();
            cTStylesheet = (CTStylesheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTStylesheet == null) {
                cTStylesheet = null;
            }
        }
        return cTStylesheet;
    }

    public void setStyleSheet(CTStylesheet cTStylesheet) {
        generatedSetterHelperImpl(cTStylesheet, PROPERTY_QNAME[0], 0, 1);
    }

    public CTStylesheet addNewStyleSheet() {
        CTStylesheet cTStylesheet;
        synchronized (monitor()) {
            check_orphaned();
            cTStylesheet = (CTStylesheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTStylesheet;
    }
}
