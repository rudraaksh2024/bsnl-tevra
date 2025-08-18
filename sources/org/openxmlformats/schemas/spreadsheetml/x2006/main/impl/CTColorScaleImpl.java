package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;

public class CTColorScaleImpl extends XmlComplexContentImpl implements CTColorScale {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "cfvo"), new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.Custom.S_COLOR)};
    private static final long serialVersionUID = 1;

    public CTColorScaleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCfvo> getCfvoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTColorScaleImpl$$ExternalSyntheticLambda5(this), new CTColorScaleImpl$$ExternalSyntheticLambda6(this), new CTColorScaleImpl$$ExternalSyntheticLambda7(this), new CTColorScaleImpl$$ExternalSyntheticLambda8(this), new CTColorScaleImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTCfvo[] getCfvoArray() {
        return (CTCfvo[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCfvo[0]);
    }

    public CTCfvo getCfvoArray(int i) {
        CTCfvo cTCfvo;
        synchronized (monitor()) {
            check_orphaned();
            cTCfvo = (CTCfvo) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCfvo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCfvo;
    }

    public int sizeOfCfvoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCfvoArray(CTCfvo[] cTCfvoArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCfvoArr, PROPERTY_QNAME[0]);
    }

    public void setCfvoArray(int i, CTCfvo cTCfvo) {
        generatedSetterHelperImpl(cTCfvo, PROPERTY_QNAME[0], i, 2);
    }

    public CTCfvo insertNewCfvo(int i) {
        CTCfvo cTCfvo;
        synchronized (monitor()) {
            check_orphaned();
            cTCfvo = (CTCfvo) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCfvo;
    }

    public CTCfvo addNewCfvo() {
        CTCfvo cTCfvo;
        synchronized (monitor()) {
            check_orphaned();
            cTCfvo = (CTCfvo) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCfvo;
    }

    public void removeCfvo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTColor> getColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTColorScaleImpl$$ExternalSyntheticLambda0(this), new CTColorScaleImpl$$ExternalSyntheticLambda1(this), new CTColorScaleImpl$$ExternalSyntheticLambda2(this), new CTColorScaleImpl$$ExternalSyntheticLambda3(this), new CTColorScaleImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTColor[] getColorArray() {
        return (CTColor[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTColor[0]);
    }

    public CTColor getColorArray(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTColor;
    }

    public int sizeOfColorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setColorArray(CTColor[] cTColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorArr, PROPERTY_QNAME[1]);
    }

    public void setColorArray(int i, CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[1], i, 2);
    }

    public CTColor insertNewColor(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTColor;
    }

    public CTColor addNewColor() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTColor;
    }

    public void removeColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }
}
