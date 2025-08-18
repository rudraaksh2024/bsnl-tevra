package com.microsoft.schemas.office.visio.x2012.main.impl;

import com.microsoft.schemas.office.visio.x2012.main.ShapeSheetType;
import com.microsoft.schemas.office.visio.x2012.main.ShapesType;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ShapesTypeImpl extends XmlComplexContentImpl implements ShapesType {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.microsoft.com/office/visio/2012/main", "Shape")};
    private static final long serialVersionUID = 1;

    public ShapesTypeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<ShapeSheetType> getShapeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new ShapesTypeImpl$$ExternalSyntheticLambda0(this), new ShapesTypeImpl$$ExternalSyntheticLambda1(this), new ShapesTypeImpl$$ExternalSyntheticLambda2(this), new ShapesTypeImpl$$ExternalSyntheticLambda3(this), new ShapesTypeImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public ShapeSheetType[] getShapeArray() {
        return (ShapeSheetType[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new ShapeSheetType[0]);
    }

    public ShapeSheetType getShapeArray(int i) {
        ShapeSheetType shapeSheetType;
        synchronized (monitor()) {
            check_orphaned();
            shapeSheetType = (ShapeSheetType) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (shapeSheetType == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return shapeSheetType;
    }

    public int sizeOfShapeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setShapeArray(ShapeSheetType[] shapeSheetTypeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) shapeSheetTypeArr, PROPERTY_QNAME[0]);
    }

    public void setShapeArray(int i, ShapeSheetType shapeSheetType) {
        generatedSetterHelperImpl(shapeSheetType, PROPERTY_QNAME[0], i, 2);
    }

    public ShapeSheetType insertNewShape(int i) {
        ShapeSheetType shapeSheetType;
        synchronized (monitor()) {
            check_orphaned();
            shapeSheetType = (ShapeSheetType) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return shapeSheetType;
    }

    public ShapeSheetType addNewShape() {
        ShapeSheetType shapeSheetType;
        synchronized (monitor()) {
            check_orphaned();
            shapeSheetType = (ShapeSheetType) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return shapeSheetType;
    }

    public void removeShape(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
