package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SstDocument;

public class SstDocumentImpl extends XmlComplexContentImpl implements SstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sst")};
    private static final long serialVersionUID = 1;

    public SstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSst getSst() {
        CTSst cTSst;
        synchronized (monitor()) {
            check_orphaned();
            cTSst = (CTSst) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSst == null) {
                cTSst = null;
            }
        }
        return cTSst;
    }

    public void setSst(CTSst cTSst) {
        generatedSetterHelperImpl(cTSst, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSst addNewSst() {
        CTSst cTSst;
        synchronized (monitor()) {
            check_orphaned();
            cTSst = (CTSst) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSst;
    }
}
