package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillStyleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;

public class CTFillStyleListImpl extends XmlComplexContentImpl implements CTFillStyleList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill")};
    private static final long serialVersionUID = 1;

    public CTFillStyleListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTNoFillProperties> getNoFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda25(this), new CTFillStyleListImpl$$ExternalSyntheticLambda26(this), new CTFillStyleListImpl$$ExternalSyntheticLambda27(this), new CTFillStyleListImpl$$ExternalSyntheticLambda28(this), new CTFillStyleListImpl$$ExternalSyntheticLambda29(this));
        }
        return javaListXmlObject;
    }

    public CTNoFillProperties[] getNoFillArray() {
        return (CTNoFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTNoFillProperties[0]);
    }

    public CTNoFillProperties getNoFillArray(int i) {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTNoFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTNoFillProperties;
    }

    public int sizeOfNoFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setNoFillArray(CTNoFillProperties[] cTNoFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTNoFillPropertiesArr, PROPERTY_QNAME[0]);
    }

    public void setNoFillArray(int i, CTNoFillProperties cTNoFillProperties) {
        generatedSetterHelperImpl(cTNoFillProperties, PROPERTY_QNAME[0], i, 2);
    }

    public CTNoFillProperties insertNewNoFill(int i) {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTNoFillProperties;
    }

    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNoFillProperties;
    }

    public void removeNoFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTSolidColorFillProperties> getSolidFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda1(this), new CTFillStyleListImpl$$ExternalSyntheticLambda2(this), new CTFillStyleListImpl$$ExternalSyntheticLambda3(this), new CTFillStyleListImpl$$ExternalSyntheticLambda4(this), new CTFillStyleListImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTSolidColorFillProperties[] getSolidFillArray() {
        return (CTSolidColorFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTSolidColorFillProperties[0]);
    }

    public CTSolidColorFillProperties getSolidFillArray(int i) {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTSolidColorFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSolidColorFillProperties;
    }

    public int sizeOfSolidFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSolidFillArray(CTSolidColorFillProperties[] cTSolidColorFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSolidColorFillPropertiesArr, PROPERTY_QNAME[1]);
    }

    public void setSolidFillArray(int i, CTSolidColorFillProperties cTSolidColorFillProperties) {
        generatedSetterHelperImpl(cTSolidColorFillProperties, PROPERTY_QNAME[1], i, 2);
    }

    public CTSolidColorFillProperties insertNewSolidFill(int i) {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTSolidColorFillProperties;
    }

    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSolidColorFillProperties;
    }

    public void removeSolidFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTGradientFillProperties> getGradFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda12(this), new CTFillStyleListImpl$$ExternalSyntheticLambda13(this), new CTFillStyleListImpl$$ExternalSyntheticLambda14(this), new CTFillStyleListImpl$$ExternalSyntheticLambda15(this), new CTFillStyleListImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTGradientFillProperties[] getGradFillArray() {
        return (CTGradientFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTGradientFillProperties[0]);
    }

    public CTGradientFillProperties getGradFillArray(int i) {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTGradientFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGradientFillProperties;
    }

    public int sizeOfGradFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setGradFillArray(CTGradientFillProperties[] cTGradientFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGradientFillPropertiesArr, PROPERTY_QNAME[2]);
    }

    public void setGradFillArray(int i, CTGradientFillProperties cTGradientFillProperties) {
        generatedSetterHelperImpl(cTGradientFillProperties, PROPERTY_QNAME[2], i, 2);
    }

    public CTGradientFillProperties insertNewGradFill(int i) {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTGradientFillProperties;
    }

    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTGradientFillProperties;
    }

    public void removeGradFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTBlipFillProperties> getBlipFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda0(this), new CTFillStyleListImpl$$ExternalSyntheticLambda11(this), new CTFillStyleListImpl$$ExternalSyntheticLambda22(this), new CTFillStyleListImpl$$ExternalSyntheticLambda23(this), new CTFillStyleListImpl$$ExternalSyntheticLambda24(this));
        }
        return javaListXmlObject;
    }

    public CTBlipFillProperties[] getBlipFillArray() {
        return (CTBlipFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTBlipFillProperties[0]);
    }

    public CTBlipFillProperties getBlipFillArray(int i) {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTBlipFillProperties = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTBlipFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBlipFillProperties;
    }

    public int sizeOfBlipFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setBlipFillArray(CTBlipFillProperties[] cTBlipFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBlipFillPropertiesArr, PROPERTY_QNAME[3]);
    }

    public void setBlipFillArray(int i, CTBlipFillProperties cTBlipFillProperties) {
        generatedSetterHelperImpl(cTBlipFillProperties, PROPERTY_QNAME[3], i, 2);
    }

    public CTBlipFillProperties insertNewBlipFill(int i) {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTBlipFillProperties = (CTBlipFillProperties) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTBlipFillProperties;
    }

    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTBlipFillProperties = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBlipFillProperties;
    }

    public void removeBlipFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTPatternFillProperties> getPattFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda6(this), new CTFillStyleListImpl$$ExternalSyntheticLambda7(this), new CTFillStyleListImpl$$ExternalSyntheticLambda8(this), new CTFillStyleListImpl$$ExternalSyntheticLambda9(this), new CTFillStyleListImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTPatternFillProperties[] getPattFillArray() {
        return (CTPatternFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTPatternFillProperties[0]);
    }

    public CTPatternFillProperties getPattFillArray(int i) {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTPatternFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPatternFillProperties;
    }

    public int sizeOfPattFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setPattFillArray(CTPatternFillProperties[] cTPatternFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPatternFillPropertiesArr, PROPERTY_QNAME[4]);
    }

    public void setPattFillArray(int i, CTPatternFillProperties cTPatternFillProperties) {
        generatedSetterHelperImpl(cTPatternFillProperties, PROPERTY_QNAME[4], i, 2);
    }

    public CTPatternFillProperties insertNewPattFill(int i) {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTPatternFillProperties;
    }

    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTPatternFillProperties;
    }

    public void removePattFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTGroupFillProperties> getGrpFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTFillStyleListImpl$$ExternalSyntheticLambda17(this), new CTFillStyleListImpl$$ExternalSyntheticLambda18(this), new CTFillStyleListImpl$$ExternalSyntheticLambda19(this), new CTFillStyleListImpl$$ExternalSyntheticLambda20(this), new CTFillStyleListImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTGroupFillProperties[] getGrpFillArray() {
        return (CTGroupFillProperties[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTGroupFillProperties[0]);
    }

    public CTGroupFillProperties getGrpFillArray(int i) {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupFillProperties = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTGroupFillProperties == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTGroupFillProperties;
    }

    public int sizeOfGrpFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setGrpFillArray(CTGroupFillProperties[] cTGroupFillPropertiesArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGroupFillPropertiesArr, PROPERTY_QNAME[5]);
    }

    public void setGrpFillArray(int i, CTGroupFillProperties cTGroupFillProperties) {
        generatedSetterHelperImpl(cTGroupFillProperties, PROPERTY_QNAME[5], i, 2);
    }

    public CTGroupFillProperties insertNewGrpFill(int i) {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupFillProperties = (CTGroupFillProperties) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTGroupFillProperties;
    }

    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupFillProperties = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTGroupFillProperties;
    }

    public void removeGrpFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }
}
