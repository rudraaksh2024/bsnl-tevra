package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;

public class CTTblGridBaseImpl extends XmlComplexContentImpl implements CTTblGridBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridCol")};
    private static final long serialVersionUID = 1;

    public CTTblGridBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTblGridCol> getGridColList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTblGridBaseImpl$$ExternalSyntheticLambda0(this), new CTTblGridBaseImpl$$ExternalSyntheticLambda1(this), new CTTblGridBaseImpl$$ExternalSyntheticLambda2(this), new CTTblGridBaseImpl$$ExternalSyntheticLambda3(this), new CTTblGridBaseImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTTblGridCol[] getGridColArray() {
        return (CTTblGridCol[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTblGridCol[0]);
    }

    public CTTblGridCol getGridColArray(int i) {
        CTTblGridCol cTTblGridCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTblGridCol = (CTTblGridCol) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTblGridCol == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTblGridCol;
    }

    public int sizeOfGridColArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setGridColArray(CTTblGridCol[] cTTblGridColArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTblGridColArr, PROPERTY_QNAME[0]);
    }

    public void setGridColArray(int i, CTTblGridCol cTTblGridCol) {
        generatedSetterHelperImpl(cTTblGridCol, PROPERTY_QNAME[0], i, 2);
    }

    public CTTblGridCol insertNewGridCol(int i) {
        CTTblGridCol cTTblGridCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTblGridCol = (CTTblGridCol) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTblGridCol;
    }

    public CTTblGridCol addNewGridCol() {
        CTTblGridCol cTTblGridCol;
        synchronized (monitor()) {
            check_orphaned();
            cTTblGridCol = (CTTblGridCol) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTblGridCol;
    }

    public void removeGridCol(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
