package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRange;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTProtectedRanges;

public class CTProtectedRangesImpl extends XmlComplexContentImpl implements CTProtectedRanges {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "protectedRange")};
    private static final long serialVersionUID = 1;

    public CTProtectedRangesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTProtectedRange> getProtectedRangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTProtectedRangesImpl$$ExternalSyntheticLambda0(this), new CTProtectedRangesImpl$$ExternalSyntheticLambda1(this), new CTProtectedRangesImpl$$ExternalSyntheticLambda2(this), new CTProtectedRangesImpl$$ExternalSyntheticLambda3(this), new CTProtectedRangesImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTProtectedRange[] getProtectedRangeArray() {
        return (CTProtectedRange[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTProtectedRange[0]);
    }

    public CTProtectedRange getProtectedRangeArray(int i) {
        CTProtectedRange cTProtectedRange;
        synchronized (monitor()) {
            check_orphaned();
            cTProtectedRange = (CTProtectedRange) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTProtectedRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTProtectedRange;
    }

    public int sizeOfProtectedRangeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setProtectedRangeArray(CTProtectedRange[] cTProtectedRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTProtectedRangeArr, PROPERTY_QNAME[0]);
    }

    public void setProtectedRangeArray(int i, CTProtectedRange cTProtectedRange) {
        generatedSetterHelperImpl(cTProtectedRange, PROPERTY_QNAME[0], i, 2);
    }

    public CTProtectedRange insertNewProtectedRange(int i) {
        CTProtectedRange cTProtectedRange;
        synchronized (monitor()) {
            check_orphaned();
            cTProtectedRange = (CTProtectedRange) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTProtectedRange;
    }

    public CTProtectedRange addNewProtectedRange() {
        CTProtectedRange cTProtectedRange;
        synchronized (monitor()) {
            check_orphaned();
            cTProtectedRange = (CTProtectedRange) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTProtectedRange;
    }

    public void removeProtectedRange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
