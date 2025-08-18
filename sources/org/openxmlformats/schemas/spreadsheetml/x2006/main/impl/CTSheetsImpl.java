package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheets;

public class CTSheetsImpl extends XmlComplexContentImpl implements CTSheets {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheet")};
    private static final long serialVersionUID = 1;

    public CTSheetsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSheet> getSheetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSheetsImpl$$ExternalSyntheticLambda0(this), new CTSheetsImpl$$ExternalSyntheticLambda1(this), new CTSheetsImpl$$ExternalSyntheticLambda2(this), new CTSheetsImpl$$ExternalSyntheticLambda3(this), new CTSheetsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTSheet[] getSheetArray() {
        return (CTSheet[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSheet[0]);
    }

    public CTSheet getSheetArray(int i) {
        CTSheet cTSheet;
        synchronized (monitor()) {
            check_orphaned();
            cTSheet = (CTSheet) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSheet == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSheet;
    }

    public int sizeOfSheetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSheetArray(CTSheet[] cTSheetArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSheetArr, PROPERTY_QNAME[0]);
    }

    public void setSheetArray(int i, CTSheet cTSheet) {
        generatedSetterHelperImpl(cTSheet, PROPERTY_QNAME[0], i, 2);
    }

    public CTSheet insertNewSheet(int i) {
        CTSheet cTSheet;
        synchronized (monitor()) {
            check_orphaned();
            cTSheet = (CTSheet) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSheet;
    }

    public CTSheet addNewSheet() {
        CTSheet cTSheet;
        synchronized (monitor()) {
            check_orphaned();
            cTSheet = (CTSheet) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSheet;
    }

    public void removeSheet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
