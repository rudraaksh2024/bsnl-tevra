package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTComplementTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseGammaTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInverseTransform;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedAngle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveFixedPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositivePercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;

public class CTScRgbColorImpl extends XmlComplexContentImpl implements CTScRgbColor {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "shade"), new QName(XSSFRelation.NS_DRAWINGML, "comp"), new QName(XSSFRelation.NS_DRAWINGML, "inv"), new QName(XSSFRelation.NS_DRAWINGML, "gray"), new QName(XSSFRelation.NS_DRAWINGML, "alpha"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOff"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "hue"), new QName(XSSFRelation.NS_DRAWINGML, "hueOff"), new QName(XSSFRelation.NS_DRAWINGML, "hueMod"), new QName(XSSFRelation.NS_DRAWINGML, "sat"), new QName(XSSFRelation.NS_DRAWINGML, "satOff"), new QName(XSSFRelation.NS_DRAWINGML, "satMod"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "lumOff"), new QName(XSSFRelation.NS_DRAWINGML, "lumMod"), new QName(XSSFRelation.NS_DRAWINGML, "red"), new QName(XSSFRelation.NS_DRAWINGML, "redOff"), new QName(XSSFRelation.NS_DRAWINGML, "redMod"), new QName(XSSFRelation.NS_DRAWINGML, "green"), new QName(XSSFRelation.NS_DRAWINGML, "greenOff"), new QName(XSSFRelation.NS_DRAWINGML, "greenMod"), new QName(XSSFRelation.NS_DRAWINGML, "blue"), new QName(XSSFRelation.NS_DRAWINGML, "blueOff"), new QName(XSSFRelation.NS_DRAWINGML, "blueMod"), new QName(XSSFRelation.NS_DRAWINGML, "gamma"), new QName(XSSFRelation.NS_DRAWINGML, "invGamma"), new QName("", "r"), new QName("", "g"), new QName("", "b")};
    private static final long serialVersionUID = 1;

    public CTScRgbColorImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTPositiveFixedPercentage> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda101(this), new CTScRgbColorImpl$$ExternalSyntheticLambda102(this), new CTScRgbColorImpl$$ExternalSyntheticLambda103(this), new CTScRgbColorImpl$$ExternalSyntheticLambda104(this), new CTScRgbColorImpl$$ExternalSyntheticLambda105(this));
        }
        return javaListXmlObject;
    }

    public CTPositiveFixedPercentage[] getTintArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTPositiveFixedPercentage[0]);
    }

    public CTPositiveFixedPercentage getTintArray(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPositiveFixedPercentage;
    }

    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setTintArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositiveFixedPercentageArr, PROPERTY_QNAME[0]);
    }

    public void setTintArray(int i, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        generatedSetterHelperImpl(cTPositiveFixedPercentage, PROPERTY_QNAME[0], i, 2);
    }

    public CTPositiveFixedPercentage insertNewTint(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTPositiveFixedPercentage;
    }

    public CTPositiveFixedPercentage addNewTint() {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPositiveFixedPercentage;
    }

    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTPositiveFixedPercentage> getShadeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda68(this), new CTScRgbColorImpl$$ExternalSyntheticLambda69(this), new CTScRgbColorImpl$$ExternalSyntheticLambda70(this), new CTScRgbColorImpl$$ExternalSyntheticLambda71(this), new CTScRgbColorImpl$$ExternalSyntheticLambda72(this));
        }
        return javaListXmlObject;
    }

    public CTPositiveFixedPercentage[] getShadeArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTPositiveFixedPercentage[0]);
    }

    public CTPositiveFixedPercentage getShadeArray(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPositiveFixedPercentage;
    }

    public int sizeOfShadeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setShadeArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositiveFixedPercentageArr, PROPERTY_QNAME[1]);
    }

    public void setShadeArray(int i, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        generatedSetterHelperImpl(cTPositiveFixedPercentage, PROPERTY_QNAME[1], i, 2);
    }

    public CTPositiveFixedPercentage insertNewShade(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTPositiveFixedPercentage;
    }

    public CTPositiveFixedPercentage addNewShade() {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTPositiveFixedPercentage;
    }

    public void removeShade(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTComplementTransform> getCompList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda28(this), new CTScRgbColorImpl$$ExternalSyntheticLambda29(this), new CTScRgbColorImpl$$ExternalSyntheticLambda30(this), new CTScRgbColorImpl$$ExternalSyntheticLambda31(this), new CTScRgbColorImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTComplementTransform[] getCompArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTComplementTransform[0]);
    }

    public CTComplementTransform getCompArray(int i) {
        CTComplementTransform find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCompArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setCompArray(CTComplementTransform[] cTComplementTransformArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTComplementTransformArr, PROPERTY_QNAME[2]);
    }

    public void setCompArray(int i, CTComplementTransform cTComplementTransform) {
        generatedSetterHelperImpl(cTComplementTransform, PROPERTY_QNAME[2], i, 2);
    }

    public CTComplementTransform insertNewComp(int i) {
        CTComplementTransform insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public CTComplementTransform addNewComp() {
        CTComplementTransform add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeComp(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTInverseTransform> getInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda39(this), new CTScRgbColorImpl$$ExternalSyntheticLambda40(this), new CTScRgbColorImpl$$ExternalSyntheticLambda41(this), new CTScRgbColorImpl$$ExternalSyntheticLambda42(this), new CTScRgbColorImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTInverseTransform[] getInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTInverseTransform[0]);
    }

    public CTInverseTransform getInvArray(int i) {
        CTInverseTransform find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setInvArray(CTInverseTransform[] cTInverseTransformArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTInverseTransformArr, PROPERTY_QNAME[3]);
    }

    public void setInvArray(int i, CTInverseTransform cTInverseTransform) {
        generatedSetterHelperImpl(cTInverseTransform, PROPERTY_QNAME[3], i, 2);
    }

    public CTInverseTransform insertNewInv(int i) {
        CTInverseTransform insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public CTInverseTransform addNewInv() {
        CTInverseTransform add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTGrayscaleTransform> getGrayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda11(this), new CTScRgbColorImpl$$ExternalSyntheticLambda22(this), new CTScRgbColorImpl$$ExternalSyntheticLambda33(this), new CTScRgbColorImpl$$ExternalSyntheticLambda44(this), new CTScRgbColorImpl$$ExternalSyntheticLambda45(this));
        }
        return javaListXmlObject;
    }

    public CTGrayscaleTransform[] getGrayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTGrayscaleTransform[0]);
    }

    public CTGrayscaleTransform getGrayArray(int i) {
        CTGrayscaleTransform find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfGrayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setGrayArray(CTGrayscaleTransform[] cTGrayscaleTransformArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGrayscaleTransformArr, PROPERTY_QNAME[4]);
    }

    public void setGrayArray(int i, CTGrayscaleTransform cTGrayscaleTransform) {
        generatedSetterHelperImpl(cTGrayscaleTransform, PROPERTY_QNAME[4], i, 2);
    }

    public CTGrayscaleTransform insertNewGray(int i) {
        CTGrayscaleTransform insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return insert_element_user;
    }

    public CTGrayscaleTransform addNewGray() {
        CTGrayscaleTransform add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void removeGray(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTPositiveFixedPercentage> getAlphaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda112(this), new CTScRgbColorImpl$$ExternalSyntheticLambda113(this), new CTScRgbColorImpl$$ExternalSyntheticLambda114(this), new CTScRgbColorImpl$$ExternalSyntheticLambda115(this), new CTScRgbColorImpl$$ExternalSyntheticLambda116(this));
        }
        return javaListXmlObject;
    }

    public CTPositiveFixedPercentage[] getAlphaArray() {
        return (CTPositiveFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTPositiveFixedPercentage[0]);
    }

    public CTPositiveFixedPercentage getAlphaArray(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTPositiveFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPositiveFixedPercentage;
    }

    public int sizeOfAlphaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAlphaArray(CTPositiveFixedPercentage[] cTPositiveFixedPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositiveFixedPercentageArr, PROPERTY_QNAME[5]);
    }

    public void setAlphaArray(int i, CTPositiveFixedPercentage cTPositiveFixedPercentage) {
        generatedSetterHelperImpl(cTPositiveFixedPercentage, PROPERTY_QNAME[5], i, 2);
    }

    public CTPositiveFixedPercentage insertNewAlpha(int i) {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTPositiveFixedPercentage;
    }

    public CTPositiveFixedPercentage addNewAlpha() {
        CTPositiveFixedPercentage cTPositiveFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositiveFixedPercentage = (CTPositiveFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPositiveFixedPercentage;
    }

    public void removeAlpha(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTFixedPercentage> getAlphaOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda23(this), new CTScRgbColorImpl$$ExternalSyntheticLambda24(this), new CTScRgbColorImpl$$ExternalSyntheticLambda25(this), new CTScRgbColorImpl$$ExternalSyntheticLambda26(this), new CTScRgbColorImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTFixedPercentage[] getAlphaOffArray() {
        return (CTFixedPercentage[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTFixedPercentage[0]);
    }

    public CTFixedPercentage getAlphaOffArray(int i) {
        CTFixedPercentage cTFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTFixedPercentage = (CTFixedPercentage) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTFixedPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFixedPercentage;
    }

    public int sizeOfAlphaOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setAlphaOffArray(CTFixedPercentage[] cTFixedPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFixedPercentageArr, PROPERTY_QNAME[6]);
    }

    public void setAlphaOffArray(int i, CTFixedPercentage cTFixedPercentage) {
        generatedSetterHelperImpl(cTFixedPercentage, PROPERTY_QNAME[6], i, 2);
    }

    public CTFixedPercentage insertNewAlphaOff(int i) {
        CTFixedPercentage cTFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTFixedPercentage = (CTFixedPercentage) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTFixedPercentage;
    }

    public CTFixedPercentage addNewAlphaOff() {
        CTFixedPercentage cTFixedPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTFixedPercentage = (CTFixedPercentage) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTFixedPercentage;
    }

    public void removeAlphaOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTPositivePercentage> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda107(this), new CTScRgbColorImpl$$ExternalSyntheticLambda108(this), new CTScRgbColorImpl$$ExternalSyntheticLambda109(this), new CTScRgbColorImpl$$ExternalSyntheticLambda110(this), new CTScRgbColorImpl$$ExternalSyntheticLambda111(this));
        }
        return javaListXmlObject;
    }

    public CTPositivePercentage[] getAlphaModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTPositivePercentage[0]);
    }

    public CTPositivePercentage getAlphaModArray(int i) {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTPositivePercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPositivePercentage;
    }

    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setAlphaModArray(CTPositivePercentage[] cTPositivePercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositivePercentageArr, PROPERTY_QNAME[7]);
    }

    public void setAlphaModArray(int i, CTPositivePercentage cTPositivePercentage) {
        generatedSetterHelperImpl(cTPositivePercentage, PROPERTY_QNAME[7], i, 2);
    }

    public CTPositivePercentage insertNewAlphaMod(int i) {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTPositivePercentage;
    }

    public CTPositivePercentage addNewAlphaMod() {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPositivePercentage;
    }

    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTPositiveFixedAngle> getHueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda79(this), new CTScRgbColorImpl$$ExternalSyntheticLambda80(this), new CTScRgbColorImpl$$ExternalSyntheticLambda81(this), new CTScRgbColorImpl$$ExternalSyntheticLambda82(this), new CTScRgbColorImpl$$ExternalSyntheticLambda83(this));
        }
        return javaListXmlObject;
    }

    public CTPositiveFixedAngle[] getHueArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTPositiveFixedAngle[0]);
    }

    public CTPositiveFixedAngle getHueArray(int i) {
        CTPositiveFixedAngle find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfHueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setHueArray(CTPositiveFixedAngle[] cTPositiveFixedAngleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositiveFixedAngleArr, PROPERTY_QNAME[8]);
    }

    public void setHueArray(int i, CTPositiveFixedAngle cTPositiveFixedAngle) {
        generatedSetterHelperImpl(cTPositiveFixedAngle, PROPERTY_QNAME[8], i, 2);
    }

    public CTPositiveFixedAngle insertNewHue(int i) {
        CTPositiveFixedAngle insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTPositiveFixedAngle addNewHue() {
        CTPositiveFixedAngle add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeHue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTAngle> getHueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda95(this), new CTScRgbColorImpl$$ExternalSyntheticLambda106(this), new CTScRgbColorImpl$$ExternalSyntheticLambda117(this), new CTScRgbColorImpl$$ExternalSyntheticLambda128(this), new CTScRgbColorImpl$$ExternalSyntheticLambda139(this));
        }
        return javaListXmlObject;
    }

    public CTAngle[] getHueOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTAngle[0]);
    }

    public CTAngle getHueOffArray(int i) {
        CTAngle find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfHueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setHueOffArray(CTAngle[] cTAngleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAngleArr, PROPERTY_QNAME[9]);
    }

    public void setHueOffArray(int i, CTAngle cTAngle) {
        generatedSetterHelperImpl(cTAngle, PROPERTY_QNAME[9], i, 2);
    }

    public CTAngle insertNewHueOff(int i) {
        CTAngle insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return insert_element_user;
    }

    public CTAngle addNewHueOff() {
        CTAngle add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void removeHueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTPositivePercentage> getHueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda74(this), new CTScRgbColorImpl$$ExternalSyntheticLambda75(this), new CTScRgbColorImpl$$ExternalSyntheticLambda76(this), new CTScRgbColorImpl$$ExternalSyntheticLambda77(this), new CTScRgbColorImpl$$ExternalSyntheticLambda78(this));
        }
        return javaListXmlObject;
    }

    public CTPositivePercentage[] getHueModArray() {
        return (CTPositivePercentage[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTPositivePercentage[0]);
    }

    public CTPositivePercentage getHueModArray(int i) {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTPositivePercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPositivePercentage;
    }

    public int sizeOfHueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setHueModArray(CTPositivePercentage[] cTPositivePercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPositivePercentageArr, PROPERTY_QNAME[10]);
    }

    public void setHueModArray(int i, CTPositivePercentage cTPositivePercentage) {
        generatedSetterHelperImpl(cTPositivePercentage, PROPERTY_QNAME[10], i, 2);
    }

    public CTPositivePercentage insertNewHueMod(int i) {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTPositivePercentage;
    }

    public CTPositivePercentage addNewHueMod() {
        CTPositivePercentage cTPositivePercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPositivePercentage = (CTPositivePercentage) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTPositivePercentage;
    }

    public void removeHueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTPercentage> getSatList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda34(this), new CTScRgbColorImpl$$ExternalSyntheticLambda35(this), new CTScRgbColorImpl$$ExternalSyntheticLambda36(this), new CTScRgbColorImpl$$ExternalSyntheticLambda37(this), new CTScRgbColorImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getSatArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getSatArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfSatArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setSatArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[11]);
    }

    public void setSatArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[11], i, 2);
    }

    public CTPercentage insertNewSat(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewSat() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTPercentage;
    }

    public void removeSat(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTPercentage> getSatOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda1(this), new CTScRgbColorImpl$$ExternalSyntheticLambda2(this), new CTScRgbColorImpl$$ExternalSyntheticLambda3(this), new CTScRgbColorImpl$$ExternalSyntheticLambda4(this), new CTScRgbColorImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getSatOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getSatOffArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfSatOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setSatOffArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[12]);
    }

    public void setSatOffArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[12], i, 2);
    }

    public CTPercentage insertNewSatOff(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewSatOff() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTPercentage;
    }

    public void removeSatOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTPercentage> getSatModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda12(this), new CTScRgbColorImpl$$ExternalSyntheticLambda13(this), new CTScRgbColorImpl$$ExternalSyntheticLambda14(this), new CTScRgbColorImpl$$ExternalSyntheticLambda15(this), new CTScRgbColorImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getSatModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getSatModArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfSatModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setSatModArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[13]);
    }

    public void setSatModArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[13], i, 2);
    }

    public CTPercentage insertNewSatMod(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewSatMod() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTPercentage;
    }

    public void removeSatMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTPercentage> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda63(this), new CTScRgbColorImpl$$ExternalSyntheticLambda64(this), new CTScRgbColorImpl$$ExternalSyntheticLambda65(this), new CTScRgbColorImpl$$ExternalSyntheticLambda66(this), new CTScRgbColorImpl$$ExternalSyntheticLambda67(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getLumArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getLumArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setLumArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[14]);
    }

    public void setLumArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[14], i, 2);
    }

    public CTPercentage insertNewLum(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewLum() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTPercentage;
    }

    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTPercentage> getLumOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda118(this), new CTScRgbColorImpl$$ExternalSyntheticLambda119(this), new CTScRgbColorImpl$$ExternalSyntheticLambda120(this), new CTScRgbColorImpl$$ExternalSyntheticLambda121(this), new CTScRgbColorImpl$$ExternalSyntheticLambda122(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getLumOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getLumOffArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfLumOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setLumOffArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[15]);
    }

    public void setLumOffArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[15], i, 2);
    }

    public CTPercentage insertNewLumOff(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewLumOff() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTPercentage;
    }

    public void removeLumOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTPercentage> getLumModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda129(this), new CTScRgbColorImpl$$ExternalSyntheticLambda130(this), new CTScRgbColorImpl$$ExternalSyntheticLambda131(this), new CTScRgbColorImpl$$ExternalSyntheticLambda132(this), new CTScRgbColorImpl$$ExternalSyntheticLambda133(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getLumModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getLumModArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfLumModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setLumModArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[16]);
    }

    public void setLumModArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[16], i, 2);
    }

    public CTPercentage insertNewLumMod(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewLumMod() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTPercentage;
    }

    public void removeLumMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTPercentage> getRedList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda57(this), new CTScRgbColorImpl$$ExternalSyntheticLambda58(this), new CTScRgbColorImpl$$ExternalSyntheticLambda59(this), new CTScRgbColorImpl$$ExternalSyntheticLambda60(this), new CTScRgbColorImpl$$ExternalSyntheticLambda61(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getRedArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getRedArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfRedArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setRedArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[17]);
    }

    public void setRedArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[17], i, 2);
    }

    public CTPercentage insertNewRed(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewRed() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTPercentage;
    }

    public void removeRed(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTPercentage> getRedOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda52(this), new CTScRgbColorImpl$$ExternalSyntheticLambda53(this), new CTScRgbColorImpl$$ExternalSyntheticLambda54(this), new CTScRgbColorImpl$$ExternalSyntheticLambda55(this), new CTScRgbColorImpl$$ExternalSyntheticLambda56(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getRedOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getRedOffArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfRedOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setRedOffArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[18]);
    }

    public void setRedOffArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[18], i, 2);
    }

    public CTPercentage insertNewRedOff(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewRedOff() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTPercentage;
    }

    public void removeRedOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTPercentage> getRedModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda0(this), new CTScRgbColorImpl$$ExternalSyntheticLambda51(this), new CTScRgbColorImpl$$ExternalSyntheticLambda62(this), new CTScRgbColorImpl$$ExternalSyntheticLambda73(this), new CTScRgbColorImpl$$ExternalSyntheticLambda84(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getRedModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getRedModArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfRedModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setRedModArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[19]);
    }

    public void setRedModArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[19], i, 2);
    }

    public CTPercentage insertNewRedMod(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewRedMod() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTPercentage;
    }

    public void removeRedMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTPercentage> getGreenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda134(this), new CTScRgbColorImpl$$ExternalSyntheticLambda135(this), new CTScRgbColorImpl$$ExternalSyntheticLambda136(this), new CTScRgbColorImpl$$ExternalSyntheticLambda137(this), new CTScRgbColorImpl$$ExternalSyntheticLambda138(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getGreenArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getGreenArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfGreenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setGreenArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[20]);
    }

    public void setGreenArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[20], i, 2);
    }

    public CTPercentage insertNewGreen(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewGreen() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTPercentage;
    }

    public void removeGreen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTPercentage> getGreenOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda85(this), new CTScRgbColorImpl$$ExternalSyntheticLambda86(this), new CTScRgbColorImpl$$ExternalSyntheticLambda87(this), new CTScRgbColorImpl$$ExternalSyntheticLambda88(this), new CTScRgbColorImpl$$ExternalSyntheticLambda89(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getGreenOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getGreenOffArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfGreenOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setGreenOffArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[21]);
    }

    public void setGreenOffArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[21], i, 2);
    }

    public CTPercentage insertNewGreenOff(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewGreenOff() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTPercentage;
    }

    public void removeGreenOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTPercentage> getGreenModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda123(this), new CTScRgbColorImpl$$ExternalSyntheticLambda124(this), new CTScRgbColorImpl$$ExternalSyntheticLambda125(this), new CTScRgbColorImpl$$ExternalSyntheticLambda126(this), new CTScRgbColorImpl$$ExternalSyntheticLambda127(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getGreenModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getGreenModArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfGreenModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setGreenModArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[22]);
    }

    public void setGreenModArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[22], i, 2);
    }

    public CTPercentage insertNewGreenMod(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewGreenMod() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTPercentage;
    }

    public void removeGreenMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTPercentage> getBlueList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda17(this), new CTScRgbColorImpl$$ExternalSyntheticLambda18(this), new CTScRgbColorImpl$$ExternalSyntheticLambda19(this), new CTScRgbColorImpl$$ExternalSyntheticLambda20(this), new CTScRgbColorImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getBlueArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getBlueArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfBlueArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setBlueArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[23]);
    }

    public void setBlueArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[23], i, 2);
    }

    public CTPercentage insertNewBlue(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewBlue() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTPercentage;
    }

    public void removeBlue(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTPercentage> getBlueOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda96(this), new CTScRgbColorImpl$$ExternalSyntheticLambda97(this), new CTScRgbColorImpl$$ExternalSyntheticLambda98(this), new CTScRgbColorImpl$$ExternalSyntheticLambda99(this), new CTScRgbColorImpl$$ExternalSyntheticLambda100(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getBlueOffArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getBlueOffArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfBlueOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setBlueOffArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[24]);
    }

    public void setBlueOffArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[24], i, 2);
    }

    public CTPercentage insertNewBlueOff(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewBlueOff() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTPercentage;
    }

    public void removeBlueOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTPercentage> getBlueModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda6(this), new CTScRgbColorImpl$$ExternalSyntheticLambda7(this), new CTScRgbColorImpl$$ExternalSyntheticLambda8(this), new CTScRgbColorImpl$$ExternalSyntheticLambda9(this), new CTScRgbColorImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTPercentage[] getBlueModArray() {
        return (CTPercentage[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTPercentage[0]);
    }

    public CTPercentage getBlueModArray(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (cTPercentage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPercentage;
    }

    public int sizeOfBlueModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setBlueModArray(CTPercentage[] cTPercentageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPercentageArr, PROPERTY_QNAME[25]);
    }

    public void setBlueModArray(int i, CTPercentage cTPercentage) {
        generatedSetterHelperImpl(cTPercentage, PROPERTY_QNAME[25], i, 2);
    }

    public CTPercentage insertNewBlueMod(int i) {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTPercentage;
    }

    public CTPercentage addNewBlueMod() {
        CTPercentage cTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            cTPercentage = (CTPercentage) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTPercentage;
    }

    public void removeBlueMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTGammaTransform> getGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda90(this), new CTScRgbColorImpl$$ExternalSyntheticLambda91(this), new CTScRgbColorImpl$$ExternalSyntheticLambda92(this), new CTScRgbColorImpl$$ExternalSyntheticLambda93(this), new CTScRgbColorImpl$$ExternalSyntheticLambda94(this));
        }
        return javaListXmlObject;
    }

    public CTGammaTransform[] getGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTGammaTransform[0]);
    }

    public CTGammaTransform getGammaArray(int i) {
        CTGammaTransform find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setGammaArray(CTGammaTransform[] cTGammaTransformArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGammaTransformArr, PROPERTY_QNAME[26]);
    }

    public void setGammaArray(int i, CTGammaTransform cTGammaTransform) {
        generatedSetterHelperImpl(cTGammaTransform, PROPERTY_QNAME[26], i, 2);
    }

    public CTGammaTransform insertNewGamma(int i) {
        CTGammaTransform insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return insert_element_user;
    }

    public CTGammaTransform addNewGamma() {
        CTGammaTransform add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return add_element_user;
    }

    public void removeGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTInverseGammaTransform> getInvGammaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTScRgbColorImpl$$ExternalSyntheticLambda46(this), new CTScRgbColorImpl$$ExternalSyntheticLambda47(this), new CTScRgbColorImpl$$ExternalSyntheticLambda48(this), new CTScRgbColorImpl$$ExternalSyntheticLambda49(this), new CTScRgbColorImpl$$ExternalSyntheticLambda50(this));
        }
        return javaListXmlObject;
    }

    public CTInverseGammaTransform[] getInvGammaArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTInverseGammaTransform[0]);
    }

    public CTInverseGammaTransform getInvGammaArray(int i) {
        CTInverseGammaTransform find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfInvGammaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setInvGammaArray(CTInverseGammaTransform[] cTInverseGammaTransformArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTInverseGammaTransformArr, PROPERTY_QNAME[27]);
    }

    public void setInvGammaArray(int i, CTInverseGammaTransform cTInverseGammaTransform) {
        generatedSetterHelperImpl(cTInverseGammaTransform, PROPERTY_QNAME[27], i, 2);
    }

    public CTInverseGammaTransform insertNewInvGamma(int i) {
        CTInverseGammaTransform insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return insert_element_user;
    }

    public CTInverseGammaTransform addNewInvGamma() {
        CTInverseGammaTransform add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return add_element_user;
    }

    public void removeInvGamma(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public Object getR() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[28]);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STPercentage xgetR() {
        STPercentage sTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            sTPercentage = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[28]);
        }
        return sTPercentage;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setR(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 28
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.setR(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetR(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 28
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.xsetR(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage):void");
    }

    public Object getG() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[29]);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STPercentage xgetG() {
        STPercentage sTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            sTPercentage = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[29]);
        }
        return sTPercentage;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setG(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 29
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.setG(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetG(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 29
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.xsetG(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage):void");
    }

    public Object getB() {
        Object obj;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
            if (simpleValue == null) {
                obj = null;
            } else {
                obj = simpleValue.getObjectValue();
            }
        }
        return obj;
    }

    public STPercentage xgetB() {
        STPercentage sTPercentage;
        synchronized (monitor()) {
            check_orphaned();
            sTPercentage = (STPercentage) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return sTPercentage;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setB(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 30
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setObjectValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.setB(java.lang.Object):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetB(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 30
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPercentage r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPercentage) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTScRgbColorImpl.xsetB(org.openxmlformats.schemas.drawingml.x2006.main.STPercentage):void");
    }
}
