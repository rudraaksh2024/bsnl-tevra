package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeStyleSheet;
import org.openxmlformats.schemas.drawingml.x2006.main.ThemeDocument;

public class ThemeDocumentImpl extends XmlComplexContentImpl implements ThemeDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "theme")};
    private static final long serialVersionUID = 1;

    public ThemeDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTOfficeStyleSheet getTheme() {
        CTOfficeStyleSheet cTOfficeStyleSheet;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeStyleSheet = (CTOfficeStyleSheet) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTOfficeStyleSheet == null) {
                cTOfficeStyleSheet = null;
            }
        }
        return cTOfficeStyleSheet;
    }

    public void setTheme(CTOfficeStyleSheet cTOfficeStyleSheet) {
        generatedSetterHelperImpl(cTOfficeStyleSheet, PROPERTY_QNAME[0], 0, 1);
    }

    public CTOfficeStyleSheet addNewTheme() {
        CTOfficeStyleSheet cTOfficeStyleSheet;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeStyleSheet = (CTOfficeStyleSheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTOfficeStyleSheet;
    }
}
