package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;

public class CTPath2DListImpl extends XmlComplexContentImpl implements CTPath2DList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "path")};
    private static final long serialVersionUID = 1;

    public CTPath2DListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTPath2D> getPathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DListImpl$$ExternalSyntheticLambda0(this), new CTPath2DListImpl$$ExternalSyntheticLambda1(this), new CTPath2DListImpl$$ExternalSyntheticLambda2(this), new CTPath2DListImpl$$ExternalSyntheticLambda3(this), new CTPath2DListImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTPath2D[] getPathArray() {
        return (CTPath2D[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTPath2D[0]);
    }

    public CTPath2D getPathArray(int i) {
        CTPath2D cTPath2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2D = (CTPath2D) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTPath2D == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPath2D;
    }

    public int sizeOfPathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setPathArray(CTPath2D[] cTPath2DArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPath2DArr, PROPERTY_QNAME[0]);
    }

    public void setPathArray(int i, CTPath2D cTPath2D) {
        generatedSetterHelperImpl(cTPath2D, PROPERTY_QNAME[0], i, 2);
    }

    public CTPath2D insertNewPath(int i) {
        CTPath2D cTPath2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2D = (CTPath2D) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTPath2D;
    }

    public CTPath2D addNewPath() {
        CTPath2D cTPath2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2D = (CTPath2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPath2D;
    }

    public void removePath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
