package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.SingleXmlCellsDocument;

public class SingleXmlCellsDocumentImpl extends XmlComplexContentImpl implements SingleXmlCellsDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "singleXmlCells")};
    private static final long serialVersionUID = 1;

    public SingleXmlCellsDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTSingleXmlCells getSingleXmlCells() {
        CTSingleXmlCells cTSingleXmlCells;
        synchronized (monitor()) {
            check_orphaned();
            cTSingleXmlCells = (CTSingleXmlCells) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTSingleXmlCells == null) {
                cTSingleXmlCells = null;
            }
        }
        return cTSingleXmlCells;
    }

    public void setSingleXmlCells(CTSingleXmlCells cTSingleXmlCells) {
        generatedSetterHelperImpl(cTSingleXmlCells, PROPERTY_QNAME[0], 0, 1);
    }

    public CTSingleXmlCells addNewSingleXmlCells() {
        CTSingleXmlCells cTSingleXmlCells;
        synchronized (monitor()) {
            check_orphaned();
            cTSingleXmlCells = (CTSingleXmlCells) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSingleXmlCells;
    }
}
