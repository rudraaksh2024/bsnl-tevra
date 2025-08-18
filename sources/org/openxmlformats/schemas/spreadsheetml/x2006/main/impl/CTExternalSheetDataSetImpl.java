package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetData;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExternalSheetDataSet;

public class CTExternalSheetDataSetImpl extends XmlComplexContentImpl implements CTExternalSheetDataSet {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "sheetData")};
    private static final long serialVersionUID = 1;

    public CTExternalSheetDataSetImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTExternalSheetData> getSheetDataList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTExternalSheetDataSetImpl$$ExternalSyntheticLambda0(this), new CTExternalSheetDataSetImpl$$ExternalSyntheticLambda1(this), new CTExternalSheetDataSetImpl$$ExternalSyntheticLambda2(this), new CTExternalSheetDataSetImpl$$ExternalSyntheticLambda3(this), new CTExternalSheetDataSetImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTExternalSheetData[] getSheetDataArray() {
        return (CTExternalSheetData[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTExternalSheetData[0]);
    }

    public CTExternalSheetData getSheetDataArray(int i) {
        CTExternalSheetData cTExternalSheetData;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetData = (CTExternalSheetData) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTExternalSheetData == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTExternalSheetData;
    }

    public int sizeOfSheetDataArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSheetDataArray(CTExternalSheetData[] cTExternalSheetDataArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTExternalSheetDataArr, PROPERTY_QNAME[0]);
    }

    public void setSheetDataArray(int i, CTExternalSheetData cTExternalSheetData) {
        generatedSetterHelperImpl(cTExternalSheetData, PROPERTY_QNAME[0], i, 2);
    }

    public CTExternalSheetData insertNewSheetData(int i) {
        CTExternalSheetData cTExternalSheetData;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetData = (CTExternalSheetData) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTExternalSheetData;
    }

    public CTExternalSheetData addNewSheetData() {
        CTExternalSheetData cTExternalSheetData;
        synchronized (monitor()) {
            check_orphaned();
            cTExternalSheetData = (CTExternalSheetData) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTExternalSheetData;
    }

    public void removeSheetData(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
