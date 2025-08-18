package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHslColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScRgbColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSchemeColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSystemColor;

public class CTDuotoneEffectImpl extends XmlComplexContentImpl implements CTDuotoneEffect {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "scrgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "srgbClr"), new QName(XSSFRelation.NS_DRAWINGML, "hslClr"), new QName(XSSFRelation.NS_DRAWINGML, "sysClr"), new QName(XSSFRelation.NS_DRAWINGML, "schemeClr"), new QName(XSSFRelation.NS_DRAWINGML, "prstClr")};
    private static final long serialVersionUID = 1;

    public CTDuotoneEffectImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTScRgbColor> getScrgbClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda25(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda26(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda27(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda28(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda29(this));
        }
        return javaListXmlObject;
    }

    public CTScRgbColor[] getScrgbClrArray() {
        return (CTScRgbColor[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTScRgbColor[0]);
    }

    public CTScRgbColor getScrgbClrArray(int i) {
        CTScRgbColor cTScRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTScRgbColor = (CTScRgbColor) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTScRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTScRgbColor;
    }

    public int sizeOfScrgbClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setScrgbClrArray(CTScRgbColor[] cTScRgbColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTScRgbColorArr, PROPERTY_QNAME[0]);
    }

    public void setScrgbClrArray(int i, CTScRgbColor cTScRgbColor) {
        generatedSetterHelperImpl(cTScRgbColor, PROPERTY_QNAME[0], i, 2);
    }

    public CTScRgbColor insertNewScrgbClr(int i) {
        CTScRgbColor cTScRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTScRgbColor = (CTScRgbColor) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTScRgbColor;
    }

    public CTScRgbColor addNewScrgbClr() {
        CTScRgbColor cTScRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTScRgbColor = (CTScRgbColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTScRgbColor;
    }

    public void removeScrgbClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTSRgbColor> getSrgbClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda1(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda2(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda3(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda4(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTSRgbColor[] getSrgbClrArray() {
        return (CTSRgbColor[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTSRgbColor[0]);
    }

    public CTSRgbColor getSrgbClrArray(int i) {
        CTSRgbColor cTSRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSRgbColor = (CTSRgbColor) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTSRgbColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSRgbColor;
    }

    public int sizeOfSrgbClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSrgbClrArray(CTSRgbColor[] cTSRgbColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSRgbColorArr, PROPERTY_QNAME[1]);
    }

    public void setSrgbClrArray(int i, CTSRgbColor cTSRgbColor) {
        generatedSetterHelperImpl(cTSRgbColor, PROPERTY_QNAME[1], i, 2);
    }

    public CTSRgbColor insertNewSrgbClr(int i) {
        CTSRgbColor cTSRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSRgbColor = (CTSRgbColor) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTSRgbColor;
    }

    public CTSRgbColor addNewSrgbClr() {
        CTSRgbColor cTSRgbColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSRgbColor = (CTSRgbColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSRgbColor;
    }

    public void removeSrgbClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTHslColor> getHslClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda12(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda13(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda14(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda15(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTHslColor[] getHslClrArray() {
        return (CTHslColor[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTHslColor[0]);
    }

    public CTHslColor getHslClrArray(int i) {
        CTHslColor cTHslColor;
        synchronized (monitor()) {
            check_orphaned();
            cTHslColor = (CTHslColor) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTHslColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHslColor;
    }

    public int sizeOfHslClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setHslClrArray(CTHslColor[] cTHslColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHslColorArr, PROPERTY_QNAME[2]);
    }

    public void setHslClrArray(int i, CTHslColor cTHslColor) {
        generatedSetterHelperImpl(cTHslColor, PROPERTY_QNAME[2], i, 2);
    }

    public CTHslColor insertNewHslClr(int i) {
        CTHslColor cTHslColor;
        synchronized (monitor()) {
            check_orphaned();
            cTHslColor = (CTHslColor) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTHslColor;
    }

    public CTHslColor addNewHslClr() {
        CTHslColor cTHslColor;
        synchronized (monitor()) {
            check_orphaned();
            cTHslColor = (CTHslColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTHslColor;
    }

    public void removeHslClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTSystemColor> getSysClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda0(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda11(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda22(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda23(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda24(this));
        }
        return javaListXmlObject;
    }

    public CTSystemColor[] getSysClrArray() {
        return (CTSystemColor[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTSystemColor[0]);
    }

    public CTSystemColor getSysClrArray(int i) {
        CTSystemColor cTSystemColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSystemColor = (CTSystemColor) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTSystemColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSystemColor;
    }

    public int sizeOfSysClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setSysClrArray(CTSystemColor[] cTSystemColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSystemColorArr, PROPERTY_QNAME[3]);
    }

    public void setSysClrArray(int i, CTSystemColor cTSystemColor) {
        generatedSetterHelperImpl(cTSystemColor, PROPERTY_QNAME[3], i, 2);
    }

    public CTSystemColor insertNewSysClr(int i) {
        CTSystemColor cTSystemColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSystemColor = (CTSystemColor) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTSystemColor;
    }

    public CTSystemColor addNewSysClr() {
        CTSystemColor cTSystemColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSystemColor = (CTSystemColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTSystemColor;
    }

    public void removeSysClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTSchemeColor> getSchemeClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda6(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda7(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda8(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda9(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTSchemeColor[] getSchemeClrArray() {
        return (CTSchemeColor[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTSchemeColor[0]);
    }

    public CTSchemeColor getSchemeClrArray(int i) {
        CTSchemeColor cTSchemeColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSchemeColor = (CTSchemeColor) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTSchemeColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSchemeColor;
    }

    public int sizeOfSchemeClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setSchemeClrArray(CTSchemeColor[] cTSchemeColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSchemeColorArr, PROPERTY_QNAME[4]);
    }

    public void setSchemeClrArray(int i, CTSchemeColor cTSchemeColor) {
        generatedSetterHelperImpl(cTSchemeColor, PROPERTY_QNAME[4], i, 2);
    }

    public CTSchemeColor insertNewSchemeClr(int i) {
        CTSchemeColor cTSchemeColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSchemeColor = (CTSchemeColor) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTSchemeColor;
    }

    public CTSchemeColor addNewSchemeClr() {
        CTSchemeColor cTSchemeColor;
        synchronized (monitor()) {
            check_orphaned();
            cTSchemeColor = (CTSchemeColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTSchemeColor;
    }

    public void removeSchemeClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTPresetColor> getPrstClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDuotoneEffectImpl$$ExternalSyntheticLambda17(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda18(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda19(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda20(this), new CTDuotoneEffectImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTPresetColor[] getPrstClrArray() {
        return (CTPresetColor[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTPresetColor[0]);
    }

    public CTPresetColor getPrstClrArray(int i) {
        CTPresetColor cTPresetColor;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetColor = (CTPresetColor) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTPresetColor == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPresetColor;
    }

    public int sizeOfPrstClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setPrstClrArray(CTPresetColor[] cTPresetColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPresetColorArr, PROPERTY_QNAME[5]);
    }

    public void setPrstClrArray(int i, CTPresetColor cTPresetColor) {
        generatedSetterHelperImpl(cTPresetColor, PROPERTY_QNAME[5], i, 2);
    }

    public CTPresetColor insertNewPrstClr(int i) {
        CTPresetColor cTPresetColor;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetColor = (CTPresetColor) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTPresetColor;
    }

    public CTPresetColor addNewPrstClr() {
        CTPresetColor cTPresetColor;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetColor = (CTPresetColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPresetColor;
    }

    public void removePrstClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }
}
