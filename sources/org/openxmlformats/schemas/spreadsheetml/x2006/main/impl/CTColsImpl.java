package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCol;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCols;

public class CTColsImpl extends XmlComplexContentImpl implements CTCols {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "col")};
    private static final long serialVersionUID = 1;

    public CTColsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCol> getColList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTColsImpl$$ExternalSyntheticLambda0(this), new CTColsImpl$$ExternalSyntheticLambda1(this), new CTColsImpl$$ExternalSyntheticLambda2(this), new CTColsImpl$$ExternalSyntheticLambda3(this), new CTColsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTCol[] getColArray() {
        return (CTCol[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCol[0]);
    }

    public CTCol getColArray(int i) {
        CTCol cTCol;
        synchronized (monitor()) {
            check_orphaned();
            cTCol = (CTCol) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCol == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCol;
    }

    public int sizeOfColArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setColArray(CTCol[] cTColArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColArr, PROPERTY_QNAME[0]);
    }

    public void setColArray(int i, CTCol cTCol) {
        generatedSetterHelperImpl(cTCol, PROPERTY_QNAME[0], i, 2);
    }

    public CTCol insertNewCol(int i) {
        CTCol cTCol;
        synchronized (monitor()) {
            check_orphaned();
            cTCol = (CTCol) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCol;
    }

    public CTCol addNewCol() {
        CTCol cTCol;
        synchronized (monitor()) {
            check_orphaned();
            cTCol = (CTCol) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCol;
    }

    public void removeCol(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
