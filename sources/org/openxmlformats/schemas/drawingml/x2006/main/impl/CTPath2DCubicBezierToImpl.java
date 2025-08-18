package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DCubicBezierTo;

public class CTPath2DCubicBezierToImpl extends XmlComplexContentImpl implements CTPath2DCubicBezierTo {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pt")};
    private static final long serialVersionUID = 1;

    public CTPath2DCubicBezierToImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTAdjPoint2D> getPtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda0(this), new CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda1(this), new CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda2(this), new CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda3(this), new CTPath2DCubicBezierToImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTAdjPoint2D[] getPtArray() {
        return (CTAdjPoint2D[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTAdjPoint2D[0]);
    }

    public CTAdjPoint2D getPtArray(int i) {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjPoint2D = (CTAdjPoint2D) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTAdjPoint2D == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAdjPoint2D;
    }

    public int sizeOfPtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setPtArray(CTAdjPoint2D[] cTAdjPoint2DArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAdjPoint2DArr, PROPERTY_QNAME[0]);
    }

    public void setPtArray(int i, CTAdjPoint2D cTAdjPoint2D) {
        generatedSetterHelperImpl(cTAdjPoint2D, PROPERTY_QNAME[0], i, 2);
    }

    public CTAdjPoint2D insertNewPt(int i) {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjPoint2D = (CTAdjPoint2D) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTAdjPoint2D;
    }

    public CTAdjPoint2D addNewPt() {
        CTAdjPoint2D cTAdjPoint2D;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjPoint2D = (CTAdjPoint2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTAdjPoint2D;
    }

    public void removePt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
