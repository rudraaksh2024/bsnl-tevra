package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheetData;

public class CTSheetDataImpl extends XmlComplexContentImpl implements CTSheetData {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "row")};
    private static final long serialVersionUID = 1;

    public CTSheetDataImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTRow> getRowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSheetDataImpl$$ExternalSyntheticLambda0(this), new CTSheetDataImpl$$ExternalSyntheticLambda1(this), new CTSheetDataImpl$$ExternalSyntheticLambda2(this), new CTSheetDataImpl$$ExternalSyntheticLambda3(this), new CTSheetDataImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTRow[] getRowArray() {
        return (CTRow[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTRow[0]);
    }

    public CTRow getRowArray(int i) {
        CTRow cTRow;
        synchronized (monitor()) {
            check_orphaned();
            cTRow = (CTRow) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTRow == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRow;
    }

    public int sizeOfRowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRowArray(CTRow[] cTRowArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRowArr, PROPERTY_QNAME[0]);
    }

    public void setRowArray(int i, CTRow cTRow) {
        generatedSetterHelperImpl(cTRow, PROPERTY_QNAME[0], i, 2);
    }

    public CTRow insertNewRow(int i) {
        CTRow cTRow;
        synchronized (monitor()) {
            check_orphaned();
            cTRow = (CTRow) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTRow;
    }

    public CTRow addNewRow() {
        CTRow cTRow;
        synchronized (monitor()) {
            check_orphaned();
            cTRow = (CTRow) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRow;
    }

    public void removeRow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
