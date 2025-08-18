package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCalcChain;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CalcChainDocument;

public class CalcChainDocumentImpl extends XmlComplexContentImpl implements CalcChainDocument {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "calcChain")};
    private static final long serialVersionUID = 1;

    public CalcChainDocumentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCalcChain getCalcChain() {
        CTCalcChain cTCalcChain;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcChain = (CTCalcChain) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCalcChain == null) {
                cTCalcChain = null;
            }
        }
        return cTCalcChain;
    }

    public void setCalcChain(CTCalcChain cTCalcChain) {
        generatedSetterHelperImpl(cTCalcChain, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCalcChain addNewCalcChain() {
        CTCalcChain cTCalcChain;
        synchronized (monitor()) {
            check_orphaned();
            cTCalcChain = (CTCalcChain) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCalcChain;
    }
}
