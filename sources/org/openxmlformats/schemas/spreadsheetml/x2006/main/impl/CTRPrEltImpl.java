package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;

public class CTRPrEltImpl extends XmlComplexContentImpl implements CTRPrElt {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "rFont"), new QName(XSSFRelation.NS_SPREADSHEETML, "charset"), new QName(XSSFRelation.NS_SPREADSHEETML, "family"), new QName(XSSFRelation.NS_SPREADSHEETML, "b"), new QName(XSSFRelation.NS_SPREADSHEETML, Complex.DEFAULT_SUFFIX), new QName(XSSFRelation.NS_SPREADSHEETML, "strike"), new QName(XSSFRelation.NS_SPREADSHEETML, "outline"), new QName(XSSFRelation.NS_SPREADSHEETML, "shadow"), new QName(XSSFRelation.NS_SPREADSHEETML, "condense"), new QName(XSSFRelation.NS_SPREADSHEETML, "extend"), new QName(XSSFRelation.NS_SPREADSHEETML, TypedValues.Custom.S_COLOR), new QName(XSSFRelation.NS_SPREADSHEETML, "sz"), new QName(XSSFRelation.NS_SPREADSHEETML, "u"), new QName(XSSFRelation.NS_SPREADSHEETML, "vertAlign"), new QName(XSSFRelation.NS_SPREADSHEETML, "scheme")};
    private static final long serialVersionUID = 1;

    public CTRPrEltImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTFontName> getRFontList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda56(this), new CTRPrEltImpl$$ExternalSyntheticLambda57(this), new CTRPrEltImpl$$ExternalSyntheticLambda58(this), new CTRPrEltImpl$$ExternalSyntheticLambda59(this), new CTRPrEltImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTFontName[] getRFontArray() {
        return (CTFontName[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTFontName[0]);
    }

    public CTFontName getRFontArray(int i) {
        CTFontName cTFontName;
        synchronized (monitor()) {
            check_orphaned();
            cTFontName = (CTFontName) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTFontName == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFontName;
    }

    public int sizeOfRFontArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRFontArray(CTFontName[] cTFontNameArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFontNameArr, PROPERTY_QNAME[0]);
    }

    public void setRFontArray(int i, CTFontName cTFontName) {
        generatedSetterHelperImpl(cTFontName, PROPERTY_QNAME[0], i, 2);
    }

    public CTFontName insertNewRFont(int i) {
        CTFontName cTFontName;
        synchronized (monitor()) {
            check_orphaned();
            cTFontName = (CTFontName) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTFontName;
    }

    public CTFontName addNewRFont() {
        CTFontName cTFontName;
        synchronized (monitor()) {
            check_orphaned();
            cTFontName = (CTFontName) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFontName;
    }

    public void removeRFont(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTIntProperty> getCharsetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda12(this), new CTRPrEltImpl$$ExternalSyntheticLambda13(this), new CTRPrEltImpl$$ExternalSyntheticLambda14(this), new CTRPrEltImpl$$ExternalSyntheticLambda15(this), new CTRPrEltImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTIntProperty[] getCharsetArray() {
        return (CTIntProperty[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTIntProperty[0]);
    }

    public CTIntProperty getCharsetArray(int i) {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTIntProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTIntProperty;
    }

    public int sizeOfCharsetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setCharsetArray(CTIntProperty[] cTIntPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTIntPropertyArr, PROPERTY_QNAME[1]);
    }

    public void setCharsetArray(int i, CTIntProperty cTIntProperty) {
        generatedSetterHelperImpl(cTIntProperty, PROPERTY_QNAME[1], i, 2);
    }

    public CTIntProperty insertNewCharset(int i) {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTIntProperty;
    }

    public CTIntProperty addNewCharset() {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTIntProperty;
    }

    public void removeCharset(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTIntProperty> getFamilyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda34(this), new CTRPrEltImpl$$ExternalSyntheticLambda35(this), new CTRPrEltImpl$$ExternalSyntheticLambda36(this), new CTRPrEltImpl$$ExternalSyntheticLambda37(this), new CTRPrEltImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTIntProperty[] getFamilyArray() {
        return (CTIntProperty[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTIntProperty[0]);
    }

    public CTIntProperty getFamilyArray(int i) {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTIntProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTIntProperty;
    }

    public int sizeOfFamilyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setFamilyArray(CTIntProperty[] cTIntPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTIntPropertyArr, PROPERTY_QNAME[2]);
    }

    public void setFamilyArray(int i, CTIntProperty cTIntProperty) {
        generatedSetterHelperImpl(cTIntProperty, PROPERTY_QNAME[2], i, 2);
    }

    public CTIntProperty insertNewFamily(int i) {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTIntProperty;
    }

    public CTIntProperty addNewFamily() {
        CTIntProperty cTIntProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTIntProperty = (CTIntProperty) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTIntProperty;
    }

    public void removeFamily(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTBooleanProperty> getBList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda1(this), new CTRPrEltImpl$$ExternalSyntheticLambda2(this), new CTRPrEltImpl$$ExternalSyntheticLambda3(this), new CTRPrEltImpl$$ExternalSyntheticLambda4(this), new CTRPrEltImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getBArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getBArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfBArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setBArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[3]);
    }

    public void setBArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[3], i, 2);
    }

    public CTBooleanProperty insertNewB(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewB() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBooleanProperty;
    }

    public void removeB(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTBooleanProperty> getIList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda55(this), new CTRPrEltImpl$$ExternalSyntheticLambda66(this), new CTRPrEltImpl$$ExternalSyntheticLambda72(this), new CTRPrEltImpl$$ExternalSyntheticLambda73(this), new CTRPrEltImpl$$ExternalSyntheticLambda74(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getIArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getIArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfIArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setIArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[4]);
    }

    public void setIArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[4], i, 2);
    }

    public CTBooleanProperty insertNewI(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewI() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBooleanProperty;
    }

    public void removeI(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTBooleanProperty> getStrikeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda6(this), new CTRPrEltImpl$$ExternalSyntheticLambda7(this), new CTRPrEltImpl$$ExternalSyntheticLambda8(this), new CTRPrEltImpl$$ExternalSyntheticLambda9(this), new CTRPrEltImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getStrikeArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getStrikeArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfStrikeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setStrikeArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[5]);
    }

    public void setStrikeArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[5], i, 2);
    }

    public CTBooleanProperty insertNewStrike(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewStrike() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTBooleanProperty;
    }

    public void removeStrike(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTBooleanProperty> getOutlineList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda23(this), new CTRPrEltImpl$$ExternalSyntheticLambda24(this), new CTRPrEltImpl$$ExternalSyntheticLambda25(this), new CTRPrEltImpl$$ExternalSyntheticLambda26(this), new CTRPrEltImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getOutlineArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getOutlineArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfOutlineArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setOutlineArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[6]);
    }

    public void setOutlineArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[6], i, 2);
    }

    public CTBooleanProperty insertNewOutline(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewOutline() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTBooleanProperty;
    }

    public void removeOutline(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTBooleanProperty> getShadowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda50(this), new CTRPrEltImpl$$ExternalSyntheticLambda51(this), new CTRPrEltImpl$$ExternalSyntheticLambda52(this), new CTRPrEltImpl$$ExternalSyntheticLambda53(this), new CTRPrEltImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getShadowArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getShadowArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfShadowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setShadowArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[7]);
    }

    public void setShadowArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[7], i, 2);
    }

    public CTBooleanProperty insertNewShadow(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewShadow() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTBooleanProperty;
    }

    public void removeShadow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTBooleanProperty> getCondenseList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda45(this), new CTRPrEltImpl$$ExternalSyntheticLambda46(this), new CTRPrEltImpl$$ExternalSyntheticLambda47(this), new CTRPrEltImpl$$ExternalSyntheticLambda48(this), new CTRPrEltImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getCondenseArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getCondenseArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfCondenseArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setCondenseArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[8]);
    }

    public void setCondenseArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[8], i, 2);
    }

    public CTBooleanProperty insertNewCondense(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewCondense() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTBooleanProperty;
    }

    public void removeCondense(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTBooleanProperty> getExtendList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda17(this), new CTRPrEltImpl$$ExternalSyntheticLambda18(this), new CTRPrEltImpl$$ExternalSyntheticLambda19(this), new CTRPrEltImpl$$ExternalSyntheticLambda20(this), new CTRPrEltImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTBooleanProperty[] getExtendArray() {
        return (CTBooleanProperty[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTBooleanProperty[0]);
    }

    public CTBooleanProperty getExtendArray(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTBooleanProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBooleanProperty;
    }

    public int sizeOfExtendArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setExtendArray(CTBooleanProperty[] cTBooleanPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBooleanPropertyArr, PROPERTY_QNAME[9]);
    }

    public void setExtendArray(int i, CTBooleanProperty cTBooleanProperty) {
        generatedSetterHelperImpl(cTBooleanProperty, PROPERTY_QNAME[9], i, 2);
    }

    public CTBooleanProperty insertNewExtend(int i) {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTBooleanProperty;
    }

    public CTBooleanProperty addNewExtend() {
        CTBooleanProperty cTBooleanProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTBooleanProperty = (CTBooleanProperty) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTBooleanProperty;
    }

    public void removeExtend(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTColor> getColorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda0(this), new CTRPrEltImpl$$ExternalSyntheticLambda11(this), new CTRPrEltImpl$$ExternalSyntheticLambda22(this), new CTRPrEltImpl$$ExternalSyntheticLambda33(this), new CTRPrEltImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTColor[] getColorArray() {
        return (CTColor[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTColor[0]);
    }

    public CTColor getColorArray(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[10], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setColorArray(CTColor[] cTColorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorArr, PROPERTY_QNAME[10]);
    }

    public void setColorArray(int i, CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[10], i, 2);
    }

    public CTColor insertNewColor(int i) {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTColor;
    }

    public CTColor addNewColor() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTColor;
    }

    public void removeColor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTFontSize> getSzList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda67(this), new CTRPrEltImpl$$ExternalSyntheticLambda68(this), new CTRPrEltImpl$$ExternalSyntheticLambda69(this), new CTRPrEltImpl$$ExternalSyntheticLambda70(this), new CTRPrEltImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTFontSize[] getSzArray() {
        return (CTFontSize[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTFontSize[0]);
    }

    public CTFontSize getSzArray(int i) {
        CTFontSize cTFontSize;
        synchronized (monitor()) {
            check_orphaned();
            cTFontSize = (CTFontSize) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTFontSize == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFontSize;
    }

    public int sizeOfSzArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setSzArray(CTFontSize[] cTFontSizeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFontSizeArr, PROPERTY_QNAME[11]);
    }

    public void setSzArray(int i, CTFontSize cTFontSize) {
        generatedSetterHelperImpl(cTFontSize, PROPERTY_QNAME[11], i, 2);
    }

    public CTFontSize insertNewSz(int i) {
        CTFontSize cTFontSize;
        synchronized (monitor()) {
            check_orphaned();
            cTFontSize = (CTFontSize) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTFontSize;
    }

    public CTFontSize addNewSz() {
        CTFontSize cTFontSize;
        synchronized (monitor()) {
            check_orphaned();
            cTFontSize = (CTFontSize) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTFontSize;
    }

    public void removeSz(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTUnderlineProperty> getUList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda61(this), new CTRPrEltImpl$$ExternalSyntheticLambda62(this), new CTRPrEltImpl$$ExternalSyntheticLambda63(this), new CTRPrEltImpl$$ExternalSyntheticLambda64(this), new CTRPrEltImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTUnderlineProperty[] getUArray() {
        return (CTUnderlineProperty[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTUnderlineProperty[0]);
    }

    public CTUnderlineProperty getUArray(int i) {
        CTUnderlineProperty cTUnderlineProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderlineProperty = (CTUnderlineProperty) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTUnderlineProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTUnderlineProperty;
    }

    public int sizeOfUArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setUArray(CTUnderlineProperty[] cTUnderlinePropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTUnderlinePropertyArr, PROPERTY_QNAME[12]);
    }

    public void setUArray(int i, CTUnderlineProperty cTUnderlineProperty) {
        generatedSetterHelperImpl(cTUnderlineProperty, PROPERTY_QNAME[12], i, 2);
    }

    public CTUnderlineProperty insertNewU(int i) {
        CTUnderlineProperty cTUnderlineProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderlineProperty = (CTUnderlineProperty) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTUnderlineProperty;
    }

    public CTUnderlineProperty addNewU() {
        CTUnderlineProperty cTUnderlineProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTUnderlineProperty = (CTUnderlineProperty) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTUnderlineProperty;
    }

    public void removeU(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTVerticalAlignFontProperty> getVertAlignList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda28(this), new CTRPrEltImpl$$ExternalSyntheticLambda29(this), new CTRPrEltImpl$$ExternalSyntheticLambda30(this), new CTRPrEltImpl$$ExternalSyntheticLambda31(this), new CTRPrEltImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTVerticalAlignFontProperty[] getVertAlignArray() {
        return (CTVerticalAlignFontProperty[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTVerticalAlignFontProperty[0]);
    }

    public CTVerticalAlignFontProperty getVertAlignArray(int i) {
        CTVerticalAlignFontProperty cTVerticalAlignFontProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTVerticalAlignFontProperty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTVerticalAlignFontProperty;
    }

    public int sizeOfVertAlignArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setVertAlignArray(CTVerticalAlignFontProperty[] cTVerticalAlignFontPropertyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTVerticalAlignFontPropertyArr, PROPERTY_QNAME[13]);
    }

    public void setVertAlignArray(int i, CTVerticalAlignFontProperty cTVerticalAlignFontProperty) {
        generatedSetterHelperImpl(cTVerticalAlignFontProperty, PROPERTY_QNAME[13], i, 2);
    }

    public CTVerticalAlignFontProperty insertNewVertAlign(int i) {
        CTVerticalAlignFontProperty cTVerticalAlignFontProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTVerticalAlignFontProperty;
    }

    public CTVerticalAlignFontProperty addNewVertAlign() {
        CTVerticalAlignFontProperty cTVerticalAlignFontProperty;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalAlignFontProperty = (CTVerticalAlignFontProperty) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTVerticalAlignFontProperty;
    }

    public void removeVertAlign(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTFontScheme> getSchemeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRPrEltImpl$$ExternalSyntheticLambda39(this), new CTRPrEltImpl$$ExternalSyntheticLambda40(this), new CTRPrEltImpl$$ExternalSyntheticLambda41(this), new CTRPrEltImpl$$ExternalSyntheticLambda42(this), new CTRPrEltImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTFontScheme[] getSchemeArray() {
        return (CTFontScheme[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTFontScheme[0]);
    }

    public CTFontScheme getSchemeArray(int i) {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTFontScheme = (CTFontScheme) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTFontScheme == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFontScheme;
    }

    public int sizeOfSchemeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setSchemeArray(CTFontScheme[] cTFontSchemeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFontSchemeArr, PROPERTY_QNAME[14]);
    }

    public void setSchemeArray(int i, CTFontScheme cTFontScheme) {
        generatedSetterHelperImpl(cTFontScheme, PROPERTY_QNAME[14], i, 2);
    }

    public CTFontScheme insertNewScheme(int i) {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTFontScheme = (CTFontScheme) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTFontScheme;
    }

    public CTFontScheme addNewScheme() {
        CTFontScheme cTFontScheme;
        synchronized (monitor()) {
            check_orphaned();
            cTFontScheme = (CTFontScheme) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTFontScheme;
    }

    public void removeScheme(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }
}
