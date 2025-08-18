package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEastAsianLayout;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFitText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLanguage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPrChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextEffect;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalAlignRun;

public class CTRPrImpl extends XmlComplexContentImpl implements CTRPr {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rStyle"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rFonts"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "b"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bCs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", Complex.DEFAULT_SUFFIX), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "iCs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "caps"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smallCaps"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "strike"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dstrike"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "outline"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shadow"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "emboss"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "imprint"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noProof"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "snapToGrid"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vanish"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "webHidden"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", TypedValues.Custom.S_COLOR), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "spacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "w"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "kern"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "position"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sz"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "szCs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "highlight"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "u"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "effect"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bdr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fitText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vertAlign"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rtl"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cs"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "em"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lang"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "eastAsianLayout"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "specVanish"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "oMath"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPrChange")};
    private static final long serialVersionUID = 1;

    public CTRPrImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTString> getRStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda23(this), new CTRPrImpl$$ExternalSyntheticLambda24(this), new CTRPrImpl$$ExternalSyntheticLambda25(this), new CTRPrImpl$$ExternalSyntheticLambda26(this), new CTRPrImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTString[] getRStyleArray() {
        return (CTString[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTString[0]);
    }

    public CTString getRStyleArray(int i) {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTString == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTString;
    }

    public int sizeOfRStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRStyleArray(CTString[] cTStringArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTStringArr, PROPERTY_QNAME[0]);
    }

    public void setRStyleArray(int i, CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], i, 2);
    }

    public CTString insertNewRStyle(int i) {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTString;
    }

    public CTString addNewRStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void removeRStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTFonts> getRFontsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda56(this), new CTRPrImpl$$ExternalSyntheticLambda57(this), new CTRPrImpl$$ExternalSyntheticLambda58(this), new CTRPrImpl$$ExternalSyntheticLambda59(this), new CTRPrImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTFonts[] getRFontsArray() {
        return (CTFonts[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTFonts[0]);
    }

    public CTFonts getRFontsArray(int i) {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            cTFonts = (CTFonts) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTFonts == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFonts;
    }

    public int sizeOfRFontsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setRFontsArray(CTFonts[] cTFontsArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFontsArr, PROPERTY_QNAME[1]);
    }

    public void setRFontsArray(int i, CTFonts cTFonts) {
        generatedSetterHelperImpl(cTFonts, PROPERTY_QNAME[1], i, 2);
    }

    public CTFonts insertNewRFonts(int i) {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            cTFonts = (CTFonts) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTFonts;
    }

    public CTFonts addNewRFonts() {
        CTFonts cTFonts;
        synchronized (monitor()) {
            check_orphaned();
            cTFonts = (CTFonts) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTFonts;
    }

    public void removeRFonts(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTOnOff> getBList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda140(this), new CTRPrImpl$$ExternalSyntheticLambda141(this), new CTRPrImpl$$ExternalSyntheticLambda142(this), new CTRPrImpl$$ExternalSyntheticLambda143(this), new CTRPrImpl$$ExternalSyntheticLambda144(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getBArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getBArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfBArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setBArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[2]);
    }

    public void setBArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[2], i, 2);
    }

    public CTOnOff insertNewB(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewB() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTOnOff;
    }

    public void removeB(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTOnOff> getBCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda151(this), new CTRPrImpl$$ExternalSyntheticLambda152(this), new CTRPrImpl$$ExternalSyntheticLambda153(this), new CTRPrImpl$$ExternalSyntheticLambda154(this), new CTRPrImpl$$ExternalSyntheticLambda155(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getBCsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getBCsArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfBCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setBCsArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[3]);
    }

    public void setBCsArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[3], i, 2);
    }

    public CTOnOff insertNewBCs(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewBCs() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOnOff;
    }

    public void removeBCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTOnOff> getIList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda150(this), new CTRPrImpl$$ExternalSyntheticLambda161(this), new CTRPrImpl$$ExternalSyntheticLambda172(this), new CTRPrImpl$$ExternalSyntheticLambda183(this), new CTRPrImpl$$ExternalSyntheticLambda194(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getIArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getIArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfIArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setIArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[4]);
    }

    public void setIArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[4], i, 2);
    }

    public CTOnOff insertNewI(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewI() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTOnOff;
    }

    public void removeI(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTOnOff> getICsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda134(this), new CTRPrImpl$$ExternalSyntheticLambda135(this), new CTRPrImpl$$ExternalSyntheticLambda136(this), new CTRPrImpl$$ExternalSyntheticLambda137(this), new CTRPrImpl$$ExternalSyntheticLambda138(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getICsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getICsArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfICsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setICsArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[5]);
    }

    public void setICsArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[5], i, 2);
    }

    public CTOnOff insertNewICs(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewICs() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTOnOff;
    }

    public void removeICs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTOnOff> getCapsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda173(this), new CTRPrImpl$$ExternalSyntheticLambda174(this), new CTRPrImpl$$ExternalSyntheticLambda175(this), new CTRPrImpl$$ExternalSyntheticLambda176(this), new CTRPrImpl$$ExternalSyntheticLambda177(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getCapsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getCapsArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfCapsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setCapsArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[6]);
    }

    public void setCapsArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[6], i, 2);
    }

    public CTOnOff insertNewCaps(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewCaps() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTOnOff;
    }

    public void removeCaps(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTOnOff> getSmallCapsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda78(this), new CTRPrImpl$$ExternalSyntheticLambda79(this), new CTRPrImpl$$ExternalSyntheticLambda80(this), new CTRPrImpl$$ExternalSyntheticLambda81(this), new CTRPrImpl$$ExternalSyntheticLambda82(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getSmallCapsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getSmallCapsArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfSmallCapsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setSmallCapsArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[7]);
    }

    public void setSmallCapsArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[7], i, 2);
    }

    public CTOnOff insertNewSmallCaps(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewSmallCaps() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTOnOff;
    }

    public void removeSmallCaps(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTOnOff> getStrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda162(this), new CTRPrImpl$$ExternalSyntheticLambda163(this), new CTRPrImpl$$ExternalSyntheticLambda164(this), new CTRPrImpl$$ExternalSyntheticLambda165(this), new CTRPrImpl$$ExternalSyntheticLambda166(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getStrikeArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getStrikeArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfStrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setStrikeArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[8]);
    }

    public void setStrikeArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[8], i, 2);
    }

    public CTOnOff insertNewStrike(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewStrike() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTOnOff;
    }

    public void removeStrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTOnOff> getDstrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda167(this), new CTRPrImpl$$ExternalSyntheticLambda168(this), new CTRPrImpl$$ExternalSyntheticLambda169(this), new CTRPrImpl$$ExternalSyntheticLambda170(this), new CTRPrImpl$$ExternalSyntheticLambda171(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getDstrikeArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getDstrikeArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfDstrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setDstrikeArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[9]);
    }

    public void setDstrikeArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[9], i, 2);
    }

    public CTOnOff insertNewDstrike(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewDstrike() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTOnOff;
    }

    public void removeDstrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTOnOff> getOutlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda66(this), new CTRPrImpl$$ExternalSyntheticLambda77(this), new CTRPrImpl$$ExternalSyntheticLambda88(this), new CTRPrImpl$$ExternalSyntheticLambda99(this), new CTRPrImpl$$ExternalSyntheticLambda105(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getOutlineArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getOutlineArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfOutlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setOutlineArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[10]);
    }

    public void setOutlineArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[10], i, 2);
    }

    public CTOnOff insertNewOutline(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewOutline() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTOnOff;
    }

    public void removeOutline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTOnOff> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda189(this), new CTRPrImpl$$ExternalSyntheticLambda190(this), new CTRPrImpl$$ExternalSyntheticLambda191(this), new CTRPrImpl$$ExternalSyntheticLambda192(this), new CTRPrImpl$$ExternalSyntheticLambda193(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getShadowArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getShadowArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setShadowArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[11]);
    }

    public void setShadowArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[11], i, 2);
    }

    public CTOnOff insertNewShadow(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewShadow() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTOnOff;
    }

    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTOnOff> getEmbossList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda17(this), new CTRPrImpl$$ExternalSyntheticLambda18(this), new CTRPrImpl$$ExternalSyntheticLambda19(this), new CTRPrImpl$$ExternalSyntheticLambda20(this), new CTRPrImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getEmbossArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getEmbossArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfEmbossArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setEmbossArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[12]);
    }

    public void setEmbossArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[12], i, 2);
    }

    public CTOnOff insertNewEmboss(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewEmboss() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTOnOff;
    }

    public void removeEmboss(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTOnOff> getImprintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda61(this), new CTRPrImpl$$ExternalSyntheticLambda62(this), new CTRPrImpl$$ExternalSyntheticLambda63(this), new CTRPrImpl$$ExternalSyntheticLambda64(this), new CTRPrImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getImprintArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getImprintArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfImprintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setImprintArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[13]);
    }

    public void setImprintArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[13], i, 2);
    }

    public CTOnOff insertNewImprint(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewImprint() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTOnOff;
    }

    public void removeImprint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTOnOff> getNoProofList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda39(this), new CTRPrImpl$$ExternalSyntheticLambda40(this), new CTRPrImpl$$ExternalSyntheticLambda41(this), new CTRPrImpl$$ExternalSyntheticLambda42(this), new CTRPrImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getNoProofArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getNoProofArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfNoProofArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setNoProofArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[14]);
    }

    public void setNoProofArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[14], i, 2);
    }

    public CTOnOff insertNewNoProof(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewNoProof() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTOnOff;
    }

    public void removeNoProof(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTOnOff> getSnapToGridList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda1(this), new CTRPrImpl$$ExternalSyntheticLambda2(this), new CTRPrImpl$$ExternalSyntheticLambda3(this), new CTRPrImpl$$ExternalSyntheticLambda4(this), new CTRPrImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getSnapToGridArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getSnapToGridArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfSnapToGridArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setSnapToGridArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[15]);
    }

    public void setSnapToGridArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[15], i, 2);
    }

    public CTOnOff insertNewSnapToGrid(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewSnapToGrid() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTOnOff;
    }

    public void removeSnapToGrid(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTOnOff> getVanishList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda123(this), new CTRPrImpl$$ExternalSyntheticLambda124(this), new CTRPrImpl$$ExternalSyntheticLambda125(this), new CTRPrImpl$$ExternalSyntheticLambda126(this), new CTRPrImpl$$ExternalSyntheticLambda127(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getVanishArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getVanishArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfVanishArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setVanishArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[16]);
    }

    public void setVanishArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[16], i, 2);
    }

    public CTOnOff insertNewVanish(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewVanish() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTOnOff;
    }

    public void removeVanish(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTOnOff> getWebHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda89(this), new CTRPrImpl$$ExternalSyntheticLambda90(this), new CTRPrImpl$$ExternalSyntheticLambda91(this), new CTRPrImpl$$ExternalSyntheticLambda92(this), new CTRPrImpl$$ExternalSyntheticLambda93(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getWebHiddenArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getWebHiddenArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfWebHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setWebHiddenArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[17]);
    }

    public void setWebHiddenArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[17], i, 2);
    }

    public CTOnOff insertNewWebHidden(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewWebHidden() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTOnOff;
    }

    public void removeWebHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTColor> getColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda72(this), new CTRPrImpl$$ExternalSyntheticLambda73(this), new CTRPrImpl$$ExternalSyntheticLambda74(this), new CTRPrImpl$$ExternalSyntheticLambda75(this), new CTRPrImpl$$ExternalSyntheticLambda76(this));
        }
        return javaListXmlObject;
    }

    public CTColor[] getColorArray() {
        return (CTColor[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTColor[0]);
    }

    public CTColor getColorArray(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[18], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setColorArray(CTColor[] cTColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorArr, PROPERTY_QNAME[18]);
    }

    public void setColorArray(int i, CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[18], i, 2);
    }

    public CTColor insertNewColor(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTColor;
    }

    public CTColor addNewColor() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTColor;
    }

    public void removeColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTSignedTwipsMeasure> getSpacingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda11(this), new CTRPrImpl$$ExternalSyntheticLambda22(this), new CTRPrImpl$$ExternalSyntheticLambda33(this), new CTRPrImpl$$ExternalSyntheticLambda44(this), new CTRPrImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListXmlObject;
    }

    public CTSignedTwipsMeasure[] getSpacingArray() {
        return (CTSignedTwipsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTSignedTwipsMeasure[0]);
    }

    public CTSignedTwipsMeasure getSpacingArray(int i) {
        CTSignedTwipsMeasure cTSignedTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedTwipsMeasure = (CTSignedTwipsMeasure) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (cTSignedTwipsMeasure == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSignedTwipsMeasure;
    }

    public int sizeOfSpacingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setSpacingArray(CTSignedTwipsMeasure[] cTSignedTwipsMeasureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSignedTwipsMeasureArr, PROPERTY_QNAME[19]);
    }

    public void setSpacingArray(int i, CTSignedTwipsMeasure cTSignedTwipsMeasure) {
        generatedSetterHelperImpl(cTSignedTwipsMeasure, PROPERTY_QNAME[19], i, 2);
    }

    public CTSignedTwipsMeasure insertNewSpacing(int i) {
        CTSignedTwipsMeasure cTSignedTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedTwipsMeasure = (CTSignedTwipsMeasure) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTSignedTwipsMeasure;
    }

    public CTSignedTwipsMeasure addNewSpacing() {
        CTSignedTwipsMeasure cTSignedTwipsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedTwipsMeasure = (CTSignedTwipsMeasure) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTSignedTwipsMeasure;
    }

    public void removeSpacing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTTextScale> getWList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda94(this), new CTRPrImpl$$ExternalSyntheticLambda95(this), new CTRPrImpl$$ExternalSyntheticLambda96(this), new CTRPrImpl$$ExternalSyntheticLambda97(this), new CTRPrImpl$$ExternalSyntheticLambda98(this));
        }
        return javaListXmlObject;
    }

    public CTTextScale[] getWArray() {
        return (CTTextScale[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTTextScale[0]);
    }

    public CTTextScale getWArray(int i) {
        CTTextScale cTTextScale;
        synchronized (monitor()) {
            check_orphaned();
            cTTextScale = (CTTextScale) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (cTTextScale == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTextScale;
    }

    public int sizeOfWArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setWArray(CTTextScale[] cTTextScaleArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextScaleArr, PROPERTY_QNAME[20]);
    }

    public void setWArray(int i, CTTextScale cTTextScale) {
        generatedSetterHelperImpl(cTTextScale, PROPERTY_QNAME[20], i, 2);
    }

    public CTTextScale insertNewW(int i) {
        CTTextScale cTTextScale;
        synchronized (monitor()) {
            check_orphaned();
            cTTextScale = (CTTextScale) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTTextScale;
    }

    public CTTextScale addNewW() {
        CTTextScale cTTextScale;
        synchronized (monitor()) {
            check_orphaned();
            cTTextScale = (CTTextScale) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTTextScale;
    }

    public void removeW(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTHpsMeasure> getKernList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda156(this), new CTRPrImpl$$ExternalSyntheticLambda157(this), new CTRPrImpl$$ExternalSyntheticLambda158(this), new CTRPrImpl$$ExternalSyntheticLambda159(this), new CTRPrImpl$$ExternalSyntheticLambda160(this));
        }
        return javaListXmlObject;
    }

    public CTHpsMeasure[] getKernArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTHpsMeasure[0]);
    }

    public CTHpsMeasure getKernArray(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (cTHpsMeasure == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHpsMeasure;
    }

    public int sizeOfKernArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setKernArray(CTHpsMeasure[] cTHpsMeasureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHpsMeasureArr, PROPERTY_QNAME[21]);
    }

    public void setKernArray(int i, CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[21], i, 2);
    }

    public CTHpsMeasure insertNewKern(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTHpsMeasure;
    }

    public CTHpsMeasure addNewKern() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTHpsMeasure;
    }

    public void removeKern(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTSignedHpsMeasure> getPositionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda12(this), new CTRPrImpl$$ExternalSyntheticLambda13(this), new CTRPrImpl$$ExternalSyntheticLambda14(this), new CTRPrImpl$$ExternalSyntheticLambda15(this), new CTRPrImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTSignedHpsMeasure[] getPositionArray() {
        return (CTSignedHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTSignedHpsMeasure[0]);
    }

    public CTSignedHpsMeasure getPositionArray(int i) {
        CTSignedHpsMeasure cTSignedHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedHpsMeasure = (CTSignedHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (cTSignedHpsMeasure == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSignedHpsMeasure;
    }

    public int sizeOfPositionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setPositionArray(CTSignedHpsMeasure[] cTSignedHpsMeasureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSignedHpsMeasureArr, PROPERTY_QNAME[22]);
    }

    public void setPositionArray(int i, CTSignedHpsMeasure cTSignedHpsMeasure) {
        generatedSetterHelperImpl(cTSignedHpsMeasure, PROPERTY_QNAME[22], i, 2);
    }

    public CTSignedHpsMeasure insertNewPosition(int i) {
        CTSignedHpsMeasure cTSignedHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedHpsMeasure = (CTSignedHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTSignedHpsMeasure;
    }

    public CTSignedHpsMeasure addNewPosition() {
        CTSignedHpsMeasure cTSignedHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTSignedHpsMeasure = (CTSignedHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTSignedHpsMeasure;
    }

    public void removePosition(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTHpsMeasure> getSzList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda83(this), new CTRPrImpl$$ExternalSyntheticLambda84(this), new CTRPrImpl$$ExternalSyntheticLambda85(this), new CTRPrImpl$$ExternalSyntheticLambda86(this), new CTRPrImpl$$ExternalSyntheticLambda87(this));
        }
        return javaListXmlObject;
    }

    public CTHpsMeasure[] getSzArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTHpsMeasure[0]);
    }

    public CTHpsMeasure getSzArray(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (cTHpsMeasure == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHpsMeasure;
    }

    public int sizeOfSzArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setSzArray(CTHpsMeasure[] cTHpsMeasureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHpsMeasureArr, PROPERTY_QNAME[23]);
    }

    public void setSzArray(int i, CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[23], i, 2);
    }

    public CTHpsMeasure insertNewSz(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTHpsMeasure;
    }

    public CTHpsMeasure addNewSz() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTHpsMeasure;
    }

    public void removeSz(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTHpsMeasure> getSzCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda107(this), new CTRPrImpl$$ExternalSyntheticLambda108(this), new CTRPrImpl$$ExternalSyntheticLambda109(this), new CTRPrImpl$$ExternalSyntheticLambda110(this), new CTRPrImpl$$ExternalSyntheticLambda111(this));
        }
        return javaListXmlObject;
    }

    public CTHpsMeasure[] getSzCsArray() {
        return (CTHpsMeasure[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTHpsMeasure[0]);
    }

    public CTHpsMeasure getSzCsArray(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (cTHpsMeasure == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHpsMeasure;
    }

    public int sizeOfSzCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setSzCsArray(CTHpsMeasure[] cTHpsMeasureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHpsMeasureArr, PROPERTY_QNAME[24]);
    }

    public void setSzCsArray(int i, CTHpsMeasure cTHpsMeasure) {
        generatedSetterHelperImpl(cTHpsMeasure, PROPERTY_QNAME[24], i, 2);
    }

    public CTHpsMeasure insertNewSzCs(int i) {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTHpsMeasure;
    }

    public CTHpsMeasure addNewSzCs() {
        CTHpsMeasure cTHpsMeasure;
        synchronized (monitor()) {
            check_orphaned();
            cTHpsMeasure = (CTHpsMeasure) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTHpsMeasure;
    }

    public void removeSzCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTHighlight> getHighlightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda112(this), new CTRPrImpl$$ExternalSyntheticLambda113(this), new CTRPrImpl$$ExternalSyntheticLambda114(this), new CTRPrImpl$$ExternalSyntheticLambda115(this), new CTRPrImpl$$ExternalSyntheticLambda116(this));
        }
        return javaListXmlObject;
    }

    public CTHighlight[] getHighlightArray() {
        return (CTHighlight[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTHighlight[0]);
    }

    public CTHighlight getHighlightArray(int i) {
        CTHighlight cTHighlight;
        synchronized (monitor()) {
            check_orphaned();
            cTHighlight = (CTHighlight) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (cTHighlight == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHighlight;
    }

    public int sizeOfHighlightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setHighlightArray(CTHighlight[] cTHighlightArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHighlightArr, PROPERTY_QNAME[25]);
    }

    public void setHighlightArray(int i, CTHighlight cTHighlight) {
        generatedSetterHelperImpl(cTHighlight, PROPERTY_QNAME[25], i, 2);
    }

    public CTHighlight insertNewHighlight(int i) {
        CTHighlight cTHighlight;
        synchronized (monitor()) {
            check_orphaned();
            cTHighlight = (CTHighlight) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTHighlight;
    }

    public CTHighlight addNewHighlight() {
        CTHighlight cTHighlight;
        synchronized (monitor()) {
            check_orphaned();
            cTHighlight = (CTHighlight) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTHighlight;
    }

    public void removeHighlight(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTUnderline> getUList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda50(this), new CTRPrImpl$$ExternalSyntheticLambda51(this), new CTRPrImpl$$ExternalSyntheticLambda52(this), new CTRPrImpl$$ExternalSyntheticLambda53(this), new CTRPrImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTUnderline[] getUArray() {
        return (CTUnderline[]) getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTUnderline[0]);
    }

    public CTUnderline getUArray(int i) {
        CTUnderline cTUnderline;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderline = (CTUnderline) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (cTUnderline == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTUnderline;
    }

    public int sizeOfUArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setUArray(CTUnderline[] cTUnderlineArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnderlineArr, PROPERTY_QNAME[26]);
    }

    public void setUArray(int i, CTUnderline cTUnderline) {
        generatedSetterHelperImpl(cTUnderline, PROPERTY_QNAME[26], i, 2);
    }

    public CTUnderline insertNewU(int i) {
        CTUnderline cTUnderline;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderline = (CTUnderline) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return cTUnderline;
    }

    public CTUnderline addNewU() {
        CTUnderline cTUnderline;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderline = (CTUnderline) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTUnderline;
    }

    public void removeU(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTTextEffect> getEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda34(this), new CTRPrImpl$$ExternalSyntheticLambda35(this), new CTRPrImpl$$ExternalSyntheticLambda36(this), new CTRPrImpl$$ExternalSyntheticLambda37(this), new CTRPrImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTTextEffect[] getEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTTextEffect[0]);
    }

    public CTTextEffect getEffectArray(int i) {
        CTTextEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setEffectArray(CTTextEffect[] cTTextEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextEffectArr, PROPERTY_QNAME[27]);
    }

    public void setEffectArray(int i, CTTextEffect cTTextEffect) {
        generatedSetterHelperImpl(cTTextEffect, PROPERTY_QNAME[27], i, 2);
    }

    public CTTextEffect insertNewEffect(int i) {
        CTTextEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return insert_element_user;
    }

    public CTTextEffect addNewEffect() {
        CTTextEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return add_element_user;
    }

    public void removeEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public List<CTBorder> getBdrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda100(this), new CTRPrImpl$$ExternalSyntheticLambda101(this), new CTRPrImpl$$ExternalSyntheticLambda102(this), new CTRPrImpl$$ExternalSyntheticLambda103(this), new CTRPrImpl$$ExternalSyntheticLambda104(this));
        }
        return javaListXmlObject;
    }

    public CTBorder[] getBdrArray() {
        return (CTBorder[]) getXmlObjectArray(PROPERTY_QNAME[28], (T[]) new CTBorder[0]);
    }

    public CTBorder getBdrArray(int i) {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            cTBorder = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (cTBorder == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBorder;
    }

    public int sizeOfBdrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    public void setBdrArray(CTBorder[] cTBorderArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBorderArr, PROPERTY_QNAME[28]);
    }

    public void setBdrArray(int i, CTBorder cTBorder) {
        generatedSetterHelperImpl(cTBorder, PROPERTY_QNAME[28], i, 2);
    }

    public CTBorder insertNewBdr(int i) {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            cTBorder = (CTBorder) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return cTBorder;
    }

    public CTBorder addNewBdr() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            cTBorder = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return cTBorder;
    }

    public void removeBdr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    public List<CTShd> getShdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda28(this), new CTRPrImpl$$ExternalSyntheticLambda29(this), new CTRPrImpl$$ExternalSyntheticLambda30(this), new CTRPrImpl$$ExternalSyntheticLambda31(this), new CTRPrImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTShd[] getShdArray() {
        return (CTShd[]) getXmlObjectArray(PROPERTY_QNAME[29], (T[]) new CTShd[0]);
    }

    public CTShd getShdArray(int i) {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (cTShd == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTShd;
    }

    public int sizeOfShdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    public void setShdArray(CTShd[] cTShdArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTShdArr, PROPERTY_QNAME[29]);
    }

    public void setShdArray(int i, CTShd cTShd) {
        generatedSetterHelperImpl(cTShd, PROPERTY_QNAME[29], i, 2);
    }

    public CTShd insertNewShd(int i) {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return cTShd;
    }

    public CTShd addNewShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return cTShd;
    }

    public void removeShd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    public List<CTFitText> getFitTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda178(this), new CTRPrImpl$$ExternalSyntheticLambda179(this), new CTRPrImpl$$ExternalSyntheticLambda180(this), new CTRPrImpl$$ExternalSyntheticLambda181(this), new CTRPrImpl$$ExternalSyntheticLambda182(this));
        }
        return javaListXmlObject;
    }

    public CTFitText[] getFitTextArray() {
        return (CTFitText[]) getXmlObjectArray(PROPERTY_QNAME[30], (T[]) new CTFitText[0]);
    }

    public CTFitText getFitTextArray(int i) {
        CTFitText cTFitText;
        synchronized (monitor()) {
            check_orphaned();
            cTFitText = (CTFitText) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (cTFitText == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFitText;
    }

    public int sizeOfFitTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    public void setFitTextArray(CTFitText[] cTFitTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFitTextArr, PROPERTY_QNAME[30]);
    }

    public void setFitTextArray(int i, CTFitText cTFitText) {
        generatedSetterHelperImpl(cTFitText, PROPERTY_QNAME[30], i, 2);
    }

    public CTFitText insertNewFitText(int i) {
        CTFitText cTFitText;
        synchronized (monitor()) {
            check_orphaned();
            cTFitText = (CTFitText) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return cTFitText;
    }

    public CTFitText addNewFitText() {
        CTFitText cTFitText;
        synchronized (monitor()) {
            check_orphaned();
            cTFitText = (CTFitText) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return cTFitText;
    }

    public void removeFitText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    public List<CTVerticalAlignRun> getVertAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda184(this), new CTRPrImpl$$ExternalSyntheticLambda185(this), new CTRPrImpl$$ExternalSyntheticLambda186(this), new CTRPrImpl$$ExternalSyntheticLambda187(this), new CTRPrImpl$$ExternalSyntheticLambda188(this));
        }
        return javaListXmlObject;
    }

    public CTVerticalAlignRun[] getVertAlignArray() {
        return (CTVerticalAlignRun[]) getXmlObjectArray(PROPERTY_QNAME[31], (T[]) new CTVerticalAlignRun[0]);
    }

    public CTVerticalAlignRun getVertAlignArray(int i) {
        CTVerticalAlignRun cTVerticalAlignRun;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignRun = (CTVerticalAlignRun) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (cTVerticalAlignRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTVerticalAlignRun;
    }

    public int sizeOfVertAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    public void setVertAlignArray(CTVerticalAlignRun[] cTVerticalAlignRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTVerticalAlignRunArr, PROPERTY_QNAME[31]);
    }

    public void setVertAlignArray(int i, CTVerticalAlignRun cTVerticalAlignRun) {
        generatedSetterHelperImpl(cTVerticalAlignRun, PROPERTY_QNAME[31], i, 2);
    }

    public CTVerticalAlignRun insertNewVertAlign(int i) {
        CTVerticalAlignRun cTVerticalAlignRun;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignRun = (CTVerticalAlignRun) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return cTVerticalAlignRun;
    }

    public CTVerticalAlignRun addNewVertAlign() {
        CTVerticalAlignRun cTVerticalAlignRun;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignRun = (CTVerticalAlignRun) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return cTVerticalAlignRun;
    }

    public void removeVertAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    public List<CTOnOff> getRtlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda67(this), new CTRPrImpl$$ExternalSyntheticLambda68(this), new CTRPrImpl$$ExternalSyntheticLambda69(this), new CTRPrImpl$$ExternalSyntheticLambda70(this), new CTRPrImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getRtlArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[32], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getRtlArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfRtlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    public void setRtlArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[32]);
    }

    public void setRtlArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[32], i, 2);
    }

    public CTOnOff insertNewRtl(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewRtl() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return cTOnOff;
    }

    public void removeRtl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    public List<CTOnOff> getCsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda118(this), new CTRPrImpl$$ExternalSyntheticLambda119(this), new CTRPrImpl$$ExternalSyntheticLambda120(this), new CTRPrImpl$$ExternalSyntheticLambda121(this), new CTRPrImpl$$ExternalSyntheticLambda122(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getCsArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[33], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getCsArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfCsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    public void setCsArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[33]);
    }

    public void setCsArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[33], i, 2);
    }

    public CTOnOff insertNewCs(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewCs() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return cTOnOff;
    }

    public void removeCs(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    public List<CTEm> getEmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda45(this), new CTRPrImpl$$ExternalSyntheticLambda46(this), new CTRPrImpl$$ExternalSyntheticLambda47(this), new CTRPrImpl$$ExternalSyntheticLambda48(this), new CTRPrImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTEm[] getEmArray() {
        return (CTEm[]) getXmlObjectArray(PROPERTY_QNAME[34], (T[]) new CTEm[0]);
    }

    public CTEm getEmArray(int i) {
        CTEm cTEm;
        synchronized (monitor()) {
            check_orphaned();
            cTEm = (CTEm) get_store().find_element_user(PROPERTY_QNAME[34], i);
            if (cTEm == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEm;
    }

    public int sizeOfEmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[34]);
        }
        return count_elements;
    }

    public void setEmArray(CTEm[] cTEmArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmArr, PROPERTY_QNAME[34]);
    }

    public void setEmArray(int i, CTEm cTEm) {
        generatedSetterHelperImpl(cTEm, PROPERTY_QNAME[34], i, 2);
    }

    public CTEm insertNewEm(int i) {
        CTEm cTEm;
        synchronized (monitor()) {
            check_orphaned();
            cTEm = (CTEm) get_store().insert_element_user(PROPERTY_QNAME[34], i);
        }
        return cTEm;
    }

    public CTEm addNewEm() {
        CTEm cTEm;
        synchronized (monitor()) {
            check_orphaned();
            cTEm = (CTEm) get_store().add_element_user(PROPERTY_QNAME[34]);
        }
        return cTEm;
    }

    public void removeEm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[34], i);
        }
    }

    public List<CTLanguage> getLangList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda129(this), new CTRPrImpl$$ExternalSyntheticLambda130(this), new CTRPrImpl$$ExternalSyntheticLambda131(this), new CTRPrImpl$$ExternalSyntheticLambda132(this), new CTRPrImpl$$ExternalSyntheticLambda133(this));
        }
        return javaListXmlObject;
    }

    public CTLanguage[] getLangArray() {
        return (CTLanguage[]) getXmlObjectArray(PROPERTY_QNAME[35], (T[]) new CTLanguage[0]);
    }

    public CTLanguage getLangArray(int i) {
        CTLanguage cTLanguage;
        synchronized (monitor()) {
            check_orphaned();
            cTLanguage = (CTLanguage) get_store().find_element_user(PROPERTY_QNAME[35], i);
            if (cTLanguage == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLanguage;
    }

    public int sizeOfLangArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[35]);
        }
        return count_elements;
    }

    public void setLangArray(CTLanguage[] cTLanguageArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLanguageArr, PROPERTY_QNAME[35]);
    }

    public void setLangArray(int i, CTLanguage cTLanguage) {
        generatedSetterHelperImpl(cTLanguage, PROPERTY_QNAME[35], i, 2);
    }

    public CTLanguage insertNewLang(int i) {
        CTLanguage cTLanguage;
        synchronized (monitor()) {
            check_orphaned();
            cTLanguage = (CTLanguage) get_store().insert_element_user(PROPERTY_QNAME[35], i);
        }
        return cTLanguage;
    }

    public CTLanguage addNewLang() {
        CTLanguage cTLanguage;
        synchronized (monitor()) {
            check_orphaned();
            cTLanguage = (CTLanguage) get_store().add_element_user(PROPERTY_QNAME[35]);
        }
        return cTLanguage;
    }

    public void removeLang(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[35], i);
        }
    }

    public List<CTEastAsianLayout> getEastAsianLayoutList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda145(this), new CTRPrImpl$$ExternalSyntheticLambda146(this), new CTRPrImpl$$ExternalSyntheticLambda147(this), new CTRPrImpl$$ExternalSyntheticLambda148(this), new CTRPrImpl$$ExternalSyntheticLambda149(this));
        }
        return javaListXmlObject;
    }

    public CTEastAsianLayout[] getEastAsianLayoutArray() {
        return getXmlObjectArray(PROPERTY_QNAME[36], (T[]) new CTEastAsianLayout[0]);
    }

    public CTEastAsianLayout getEastAsianLayoutArray(int i) {
        CTEastAsianLayout find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[36], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfEastAsianLayoutArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[36]);
        }
        return count_elements;
    }

    public void setEastAsianLayoutArray(CTEastAsianLayout[] cTEastAsianLayoutArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEastAsianLayoutArr, PROPERTY_QNAME[36]);
    }

    public void setEastAsianLayoutArray(int i, CTEastAsianLayout cTEastAsianLayout) {
        generatedSetterHelperImpl(cTEastAsianLayout, PROPERTY_QNAME[36], i, 2);
    }

    public CTEastAsianLayout insertNewEastAsianLayout(int i) {
        CTEastAsianLayout insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[36], i);
        }
        return insert_element_user;
    }

    public CTEastAsianLayout addNewEastAsianLayout() {
        CTEastAsianLayout add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[36]);
        }
        return add_element_user;
    }

    public void removeEastAsianLayout(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[36], i);
        }
    }

    public List<CTOnOff> getSpecVanishList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda6(this), new CTRPrImpl$$ExternalSyntheticLambda7(this), new CTRPrImpl$$ExternalSyntheticLambda8(this), new CTRPrImpl$$ExternalSyntheticLambda9(this), new CTRPrImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getSpecVanishArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[37], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getSpecVanishArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[37], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfSpecVanishArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[37]);
        }
        return count_elements;
    }

    public void setSpecVanishArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[37]);
    }

    public void setSpecVanishArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[37], i, 2);
    }

    public CTOnOff insertNewSpecVanish(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[37], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewSpecVanish() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[37]);
        }
        return cTOnOff;
    }

    public void removeSpecVanish(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[37], i);
        }
    }

    public List<CTOnOff> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrImpl$$ExternalSyntheticLambda0(this), new CTRPrImpl$$ExternalSyntheticLambda106(this), new CTRPrImpl$$ExternalSyntheticLambda117(this), new CTRPrImpl$$ExternalSyntheticLambda128(this), new CTRPrImpl$$ExternalSyntheticLambda139(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getOMathArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[38], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getOMathArray(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[38], i);
            if (cTOnOff == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOnOff;
    }

    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[38]);
        }
        return count_elements;
    }

    public void setOMathArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[38]);
    }

    public void setOMathArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[38], i, 2);
    }

    public CTOnOff insertNewOMath(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[38], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewOMath() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[38]);
        }
        return cTOnOff;
    }

    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[38], i);
        }
    }

    public CTRPrChange getRPrChange() {
        CTRPrChange cTRPrChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRPrChange = (CTRPrChange) get_store().find_element_user(PROPERTY_QNAME[39], 0);
            if (cTRPrChange == null) {
                cTRPrChange = null;
            }
        }
        return cTRPrChange;
    }

    public boolean isSetRPrChange() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[39]) != 0;
        }
        return z;
    }

    public void setRPrChange(CTRPrChange cTRPrChange) {
        generatedSetterHelperImpl(cTRPrChange, PROPERTY_QNAME[39], 0, 1);
    }

    public CTRPrChange addNewRPrChange() {
        CTRPrChange cTRPrChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRPrChange = (CTRPrChange) get_store().add_element_user(PROPERTY_QNAME[39]);
        }
        return cTRPrChange;
    }

    public void unsetRPrChange() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[39], 0);
        }
    }
}
