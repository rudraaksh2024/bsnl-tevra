package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.TblStyleLstDocument;

public class TblStyleLstDocumentImpl extends XmlComplexContentImpl implements TblStyleLstDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblStyleLst")};
    private static final long serialVersionUID = 1;

    public TblStyleLstDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTableStyleList getTblStyleLst() {
        CTTableStyleList cTTableStyleList;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleList = (CTTableStyleList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTableStyleList == null) {
                cTTableStyleList = null;
            }
        }
        return cTTableStyleList;
    }

    public void setTblStyleLst(CTTableStyleList cTTableStyleList) {
        generatedSetterHelperImpl(cTTableStyleList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTableStyleList addNewTblStyleLst() {
        CTTableStyleList cTTableStyleList;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleList = (CTTableStyleList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTableStyleList;
    }
}
