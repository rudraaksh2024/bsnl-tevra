package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableCol;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableGrid;

public class CTTableGridImpl extends XmlComplexContentImpl implements CTTableGrid {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gridCol")};
    private static final long serialVersionUID = 1;

    public CTTableGridImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTableCol> getGridColList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTableGridImpl$$ExternalSyntheticLambda0(this), new CTTableGridImpl$$ExternalSyntheticLambda1(this), new CTTableGridImpl$$ExternalSyntheticLambda2(this), new CTTableGridImpl$$ExternalSyntheticLambda3(this), new CTTableGridImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTableCol[] getGridColArray() {
        return (CTTableCol[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTableCol[0]);
    }

    public CTTableCol getGridColArray(int i) {
        CTTableCol cTTableCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTableCol = (CTTableCol) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTableCol == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTableCol;
    }

    public int sizeOfGridColArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setGridColArray(CTTableCol[] cTTableColArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTableColArr, PROPERTY_QNAME[0]);
    }

    public void setGridColArray(int i, CTTableCol cTTableCol) {
        generatedSetterHelperImpl(cTTableCol, PROPERTY_QNAME[0], i, 2);
    }

    public CTTableCol insertNewGridCol(int i) {
        CTTableCol cTTableCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTableCol = (CTTableCol) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTableCol;
    }

    public CTTableCol addNewGridCol() {
        CTTableCol cTTableCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTableCol = (CTTableCol) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTableCol;
    }

    public void removeGridCol(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
