package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIndexedColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRgbColor;

public class CTIndexedColorsImpl extends XmlComplexContentImpl implements CTIndexedColors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "rgbColor")};
    private static final long serialVersionUID = 1;

    public CTIndexedColorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTRgbColor> getRgbColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTIndexedColorsImpl$$ExternalSyntheticLambda0(this), new CTIndexedColorsImpl$$ExternalSyntheticLambda1(this), new CTIndexedColorsImpl$$ExternalSyntheticLambda2(this), new CTIndexedColorsImpl$$ExternalSyntheticLambda3(this), new CTIndexedColorsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTRgbColor[] getRgbColorArray() {
        return (CTRgbColor[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTRgbColor[0]);
    }

    public CTRgbColor getRgbColorArray(int i) {
        CTRgbColor cTRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTRgbColor = (CTRgbColor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRgbColor;
    }

    public int sizeOfRgbColorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRgbColorArray(CTRgbColor[] cTRgbColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRgbColorArr, PROPERTY_QNAME[0]);
    }

    public void setRgbColorArray(int i, CTRgbColor cTRgbColor) {
        generatedSetterHelperImpl(cTRgbColor, PROPERTY_QNAME[0], i, 2);
    }

    public CTRgbColor insertNewRgbColor(int i) {
        CTRgbColor cTRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTRgbColor = (CTRgbColor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTRgbColor;
    }

    public CTRgbColor addNewRgbColor() {
        CTRgbColor cTRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTRgbColor = (CTRgbColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRgbColor;
    }

    public void removeRgbColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }
}
