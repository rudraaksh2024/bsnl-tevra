package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTable;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableRow;

public class CTTableImpl extends XmlComplexContentImpl implements CTTable {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblPr"), new QName(XSSFRelation.NS_DRAWINGML, "tblGrid"), new QName(XSSFRelation.NS_DRAWINGML, "tr")};
    private static final long serialVersionUID = 1;

    public CTTableImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTableProperties getTblPr() {
        CTTableProperties cTTableProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTableProperties = (CTTableProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTableProperties == null) {
                cTTableProperties = null;
            }
        }
        return cTTableProperties;
    }

    public boolean isSetTblPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setTblPr(CTTableProperties cTTableProperties) {
        generatedSetterHelperImpl(cTTableProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTableProperties addNewTblPr() {
        CTTableProperties cTTableProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTableProperties = (CTTableProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTableProperties;
    }

    public void unsetTblPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTableGrid getTblGrid() {
        CTTableGrid cTTableGrid;
        synchronized (monitor()) {
            check_orphaned();
            cTTableGrid = (CTTableGrid) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTableGrid == null) {
                cTTableGrid = null;
            }
        }
        return cTTableGrid;
    }

    public void setTblGrid(CTTableGrid cTTableGrid) {
        generatedSetterHelperImpl(cTTableGrid, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTableGrid addNewTblGrid() {
        CTTableGrid cTTableGrid;
        synchronized (monitor()) {
            check_orphaned();
            cTTableGrid = (CTTableGrid) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTableGrid;
    }

    public List<CTTableRow> getTrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTableImpl$$ExternalSyntheticLambda0(this), new CTTableImpl$$ExternalSyntheticLambda1(this), new CTTableImpl$$ExternalSyntheticLambda2(this), new CTTableImpl$$ExternalSyntheticLambda3(this), new CTTableImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTableRow[] getTrArray() {
        return (CTTableRow[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTTableRow[0]);
    }

    public CTTableRow getTrArray(int i) {
        CTTableRow cTTableRow;
        synchronized (monitor()) {
            check_orphaned();
            cTTableRow = (CTTableRow) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTTableRow == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTableRow;
    }

    public int sizeOfTrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setTrArray(CTTableRow[] cTTableRowArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTableRowArr, PROPERTY_QNAME[2]);
    }

    public void setTrArray(int i, CTTableRow cTTableRow) {
        generatedSetterHelperImpl(cTTableRow, PROPERTY_QNAME[2], i, 2);
    }

    public CTTableRow insertNewTr(int i) {
        CTTableRow cTTableRow;
        synchronized (monitor()) {
            check_orphaned();
            cTTableRow = (CTTableRow) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTTableRow;
    }

    public CTTableRow addNewTr() {
        CTTableRow cTTableRow;
        synchronized (monitor()) {
            check_orphaned();
            cTTableRow = (CTTableRow) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTableRow;
    }

    public void removeTr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }
}
