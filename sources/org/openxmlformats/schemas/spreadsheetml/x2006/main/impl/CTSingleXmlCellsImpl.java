package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCell;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSingleXmlCells;

public class CTSingleXmlCellsImpl extends XmlComplexContentImpl implements CTSingleXmlCells {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "singleXmlCell")};
    private static final long serialVersionUID = 1;

    public CTSingleXmlCellsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTSingleXmlCell> getSingleXmlCellList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTSingleXmlCellsImpl$$ExternalSyntheticLambda0(this), new CTSingleXmlCellsImpl$$ExternalSyntheticLambda1(this), new CTSingleXmlCellsImpl$$ExternalSyntheticLambda2(this), new CTSingleXmlCellsImpl$$ExternalSyntheticLambda3(this), new CTSingleXmlCellsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTSingleXmlCell[] getSingleXmlCellArray() {
        return (CTSingleXmlCell[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTSingleXmlCell[0]);
    }

    public CTSingleXmlCell getSingleXmlCellArray(int i) {
        CTSingleXmlCell cTSingleXmlCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSingleXmlCell = (CTSingleXmlCell) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTSingleXmlCell == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSingleXmlCell;
    }

    public int sizeOfSingleXmlCellArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSingleXmlCellArray(CTSingleXmlCell[] cTSingleXmlCellArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSingleXmlCellArr, PROPERTY_QNAME[0]);
    }

    public void setSingleXmlCellArray(int i, CTSingleXmlCell cTSingleXmlCell) {
        generatedSetterHelperImpl(cTSingleXmlCell, PROPERTY_QNAME[0], i, 2);
    }

    public CTSingleXmlCell insertNewSingleXmlCell(int i) {
        CTSingleXmlCell cTSingleXmlCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSingleXmlCell = (CTSingleXmlCell) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTSingleXmlCell;
    }

    public CTSingleXmlCell addNewSingleXmlCell() {
        CTSingleXmlCell cTSingleXmlCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSingleXmlCell = (CTSingleXmlCell) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTSingleXmlCell;
    }

    public void removeSingleXmlCell(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
