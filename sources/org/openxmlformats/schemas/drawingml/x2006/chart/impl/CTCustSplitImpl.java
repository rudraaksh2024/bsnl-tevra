package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTCustSplit;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTUnsignedInt;

public class CTCustSplitImpl extends XmlComplexContentImpl implements CTCustSplit {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "secondPiePt")};
    private static final long serialVersionUID = 1;

    public CTCustSplitImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTUnsignedInt> getSecondPiePtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTCustSplitImpl$$ExternalSyntheticLambda0(this), new CTCustSplitImpl$$ExternalSyntheticLambda1(this), new CTCustSplitImpl$$ExternalSyntheticLambda2(this), new CTCustSplitImpl$$ExternalSyntheticLambda3(this), new CTCustSplitImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTUnsignedInt[] getSecondPiePtArray() {
        return (CTUnsignedInt[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTUnsignedInt[0]);
    }

    public CTUnsignedInt getSecondPiePtArray(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTUnsignedInt == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTUnsignedInt;
    }

    public int sizeOfSecondPiePtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setSecondPiePtArray(CTUnsignedInt[] cTUnsignedIntArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnsignedIntArr, PROPERTY_QNAME[0]);
    }

    public void setSecondPiePtArray(int i, CTUnsignedInt cTUnsignedInt) {
        generatedSetterHelperImpl(cTUnsignedInt, PROPERTY_QNAME[0], i, 2);
    }

    public CTUnsignedInt insertNewSecondPiePt(int i) {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTUnsignedInt;
    }

    public CTUnsignedInt addNewSecondPiePt() {
        CTUnsignedInt cTUnsignedInt;
        synchronized (monitor()) {
            check_orphaned();
            cTUnsignedInt = (CTUnsignedInt) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTUnsignedInt;
    }

    public void removeSecondPiePt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
