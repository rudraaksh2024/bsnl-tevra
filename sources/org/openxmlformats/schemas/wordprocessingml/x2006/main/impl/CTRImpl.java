package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEmpty;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFtnEdnRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPTab;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPicture;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRuby;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSym;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTRImpl extends XmlComplexContentImpl implements CTR {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", CompressorStreamFactory.BROTLI), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "t"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "contentPart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "delText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "instrText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "delInstrText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noBreakHyphen"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "softHyphen"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dayShort"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "monthShort"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "yearShort"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dayLong"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "monthLong"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "yearLong"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "annotationRef"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnoteRef"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnoteRef"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "separator"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "continuationSeparator"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sym"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "pgNum"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tab"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "object"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", ContentTypes.EXTENSION_PICT), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldChar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ruby"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "footnoteReference"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "endnoteReference"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentReference"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "drawing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ptab"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "lastRenderedPageBreak"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR")};
    private static final long serialVersionUID = 1;

    public CTRImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTRPr getRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTRPr == null) {
                cTRPr = null;
            }
        }
        return cTRPr;
    }

    public boolean isSetRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setRPr(CTRPr cTRPr) {
        generatedSetterHelperImpl(cTRPr, PROPERTY_QNAME[0], 0, 1);
    }

    public CTRPr addNewRPr() {
        CTRPr cTRPr;
        synchronized (monitor()) {
            check_orphaned();
            cTRPr = (CTRPr) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTRPr;
    }

    public void unsetRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTBr> getBrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda0(this), new CTRImpl$$ExternalSyntheticLambda76(this), new CTRImpl$$ExternalSyntheticLambda87(this), new CTRImpl$$ExternalSyntheticLambda98(this), new CTRImpl$$ExternalSyntheticLambda109(this));
        }
        return javaListXmlObject;
    }

    public CTBr[] getBrArray() {
        return (CTBr[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTBr[0]);
    }

    public CTBr getBrArray(int i) {
        CTBr cTBr;
        synchronized (monitor()) {
            check_orphaned();
            cTBr = (CTBr) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTBr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBr;
    }

    public int sizeOfBrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setBrArray(CTBr[] cTBrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBrArr, PROPERTY_QNAME[1]);
    }

    public void setBrArray(int i, CTBr cTBr) {
        generatedSetterHelperImpl(cTBr, PROPERTY_QNAME[1], i, 2);
    }

    public CTBr insertNewBr(int i) {
        CTBr cTBr;
        synchronized (monitor()) {
            check_orphaned();
            cTBr = (CTBr) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTBr;
    }

    public CTBr addNewBr() {
        CTBr cTBr;
        synchronized (monitor()) {
            check_orphaned();
            cTBr = (CTBr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTBr;
    }

    public void removeBr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTText> getTList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda110(this), new CTRImpl$$ExternalSyntheticLambda111(this), new CTRImpl$$ExternalSyntheticLambda112(this), new CTRImpl$$ExternalSyntheticLambda113(this), new CTRImpl$$ExternalSyntheticLambda114(this));
        }
        return javaListXmlObject;
    }

    public CTText[] getTArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTText[0]);
    }

    public CTText getTArray(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTText;
    }

    public int sizeOfTArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setTArray(CTText[] cTTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextArr, PROPERTY_QNAME[2]);
    }

    public void setTArray(int i, CTText cTText) {
        generatedSetterHelperImpl(cTText, PROPERTY_QNAME[2], i, 2);
    }

    public CTText insertNewT(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTText;
    }

    public CTText addNewT() {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTText;
    }

    public void removeT(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTRel> getContentPartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda137(this), new CTRImpl$$ExternalSyntheticLambda138(this), new CTRImpl$$ExternalSyntheticLambda139(this), new CTRImpl$$ExternalSyntheticLambda140(this), new CTRImpl$$ExternalSyntheticLambda141(this));
        }
        return javaListXmlObject;
    }

    public CTRel[] getContentPartArray() {
        return (CTRel[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTRel[0]);
    }

    public CTRel getContentPartArray(int i) {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTRel == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRel;
    }

    public int sizeOfContentPartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setContentPartArray(CTRel[] cTRelArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRelArr, PROPERTY_QNAME[3]);
    }

    public void setContentPartArray(int i, CTRel cTRel) {
        generatedSetterHelperImpl(cTRel, PROPERTY_QNAME[3], i, 2);
    }

    public CTRel insertNewContentPart(int i) {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTRel;
    }

    public CTRel addNewContentPart() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTRel;
    }

    public void removeContentPart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTText> getDelTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda66(this), new CTRImpl$$ExternalSyntheticLambda72(this), new CTRImpl$$ExternalSyntheticLambda73(this), new CTRImpl$$ExternalSyntheticLambda74(this), new CTRImpl$$ExternalSyntheticLambda75(this));
        }
        return javaListXmlObject;
    }

    public CTText[] getDelTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTText[0]);
    }

    public CTText getDelTextArray(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTText;
    }

    public int sizeOfDelTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setDelTextArray(CTText[] cTTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextArr, PROPERTY_QNAME[4]);
    }

    public void setDelTextArray(int i, CTText cTText) {
        generatedSetterHelperImpl(cTText, PROPERTY_QNAME[4], i, 2);
    }

    public CTText insertNewDelText(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTText;
    }

    public CTText addNewDelText() {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTText;
    }

    public void removeDelText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTText> getInstrTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda99(this), new CTRImpl$$ExternalSyntheticLambda100(this), new CTRImpl$$ExternalSyntheticLambda101(this), new CTRImpl$$ExternalSyntheticLambda102(this), new CTRImpl$$ExternalSyntheticLambda103(this));
        }
        return javaListXmlObject;
    }

    public CTText[] getInstrTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTText[0]);
    }

    public CTText getInstrTextArray(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTText;
    }

    public int sizeOfInstrTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setInstrTextArray(CTText[] cTTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextArr, PROPERTY_QNAME[5]);
    }

    public void setInstrTextArray(int i, CTText cTText) {
        generatedSetterHelperImpl(cTText, PROPERTY_QNAME[5], i, 2);
    }

    public CTText insertNewInstrText(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTText;
    }

    public CTText addNewInstrText() {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTText;
    }

    public void removeInstrText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTText> getDelInstrTextList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda56(this), new CTRImpl$$ExternalSyntheticLambda57(this), new CTRImpl$$ExternalSyntheticLambda58(this), new CTRImpl$$ExternalSyntheticLambda59(this), new CTRImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTText[] getDelInstrTextArray() {
        return (CTText[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTText[0]);
    }

    public CTText getDelInstrTextArray(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTText == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTText;
    }

    public int sizeOfDelInstrTextArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setDelInstrTextArray(CTText[] cTTextArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextArr, PROPERTY_QNAME[6]);
    }

    public void setDelInstrTextArray(int i, CTText cTText) {
        generatedSetterHelperImpl(cTText, PROPERTY_QNAME[6], i, 2);
    }

    public CTText insertNewDelInstrText(int i) {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTText;
    }

    public CTText addNewDelInstrText() {
        CTText cTText;
        synchronized (monitor()) {
            check_orphaned();
            cTText = (CTText) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTText;
    }

    public void removeDelInstrText(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTEmpty> getNoBreakHyphenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda11(this), new CTRImpl$$ExternalSyntheticLambda22(this), new CTRImpl$$ExternalSyntheticLambda33(this), new CTRImpl$$ExternalSyntheticLambda44(this), new CTRImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getNoBreakHyphenArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getNoBreakHyphenArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfNoBreakHyphenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setNoBreakHyphenArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[7]);
    }

    public void setNoBreakHyphenArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[7], i, 2);
    }

    public CTEmpty insertNewNoBreakHyphen(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewNoBreakHyphen() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTEmpty;
    }

    public void removeNoBreakHyphen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTEmpty> getSoftHyphenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda23(this), new CTRImpl$$ExternalSyntheticLambda24(this), new CTRImpl$$ExternalSyntheticLambda25(this), new CTRImpl$$ExternalSyntheticLambda26(this), new CTRImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getSoftHyphenArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getSoftHyphenArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfSoftHyphenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setSoftHyphenArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[8]);
    }

    public void setSoftHyphenArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[8], i, 2);
    }

    public CTEmpty insertNewSoftHyphen(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewSoftHyphen() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTEmpty;
    }

    public void removeSoftHyphen(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTEmpty> getDayShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda126(this), new CTRImpl$$ExternalSyntheticLambda127(this), new CTRImpl$$ExternalSyntheticLambda128(this), new CTRImpl$$ExternalSyntheticLambda129(this), new CTRImpl$$ExternalSyntheticLambda130(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getDayShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getDayShortArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfDayShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setDayShortArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[9]);
    }

    public void setDayShortArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[9], i, 2);
    }

    public CTEmpty insertNewDayShort(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewDayShort() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTEmpty;
    }

    public void removeDayShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTEmpty> getMonthShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda67(this), new CTRImpl$$ExternalSyntheticLambda68(this), new CTRImpl$$ExternalSyntheticLambda69(this), new CTRImpl$$ExternalSyntheticLambda70(this), new CTRImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getMonthShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getMonthShortArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfMonthShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setMonthShortArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[10]);
    }

    public void setMonthShortArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[10], i, 2);
    }

    public CTEmpty insertNewMonthShort(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewMonthShort() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTEmpty;
    }

    public void removeMonthShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTEmpty> getYearShortList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda82(this), new CTRImpl$$ExternalSyntheticLambda83(this), new CTRImpl$$ExternalSyntheticLambda84(this), new CTRImpl$$ExternalSyntheticLambda85(this), new CTRImpl$$ExternalSyntheticLambda86(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getYearShortArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getYearShortArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfYearShortArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setYearShortArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[11]);
    }

    public void setYearShortArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[11], i, 2);
    }

    public CTEmpty insertNewYearShort(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewYearShort() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTEmpty;
    }

    public void removeYearShort(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTEmpty> getDayLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda6(this), new CTRImpl$$ExternalSyntheticLambda7(this), new CTRImpl$$ExternalSyntheticLambda8(this), new CTRImpl$$ExternalSyntheticLambda9(this), new CTRImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getDayLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getDayLongArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfDayLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setDayLongArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[12]);
    }

    public void setDayLongArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[12], i, 2);
    }

    public CTEmpty insertNewDayLong(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewDayLong() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTEmpty;
    }

    public void removeDayLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTEmpty> getMonthLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda115(this), new CTRImpl$$ExternalSyntheticLambda116(this), new CTRImpl$$ExternalSyntheticLambda117(this), new CTRImpl$$ExternalSyntheticLambda118(this), new CTRImpl$$ExternalSyntheticLambda119(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getMonthLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getMonthLongArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfMonthLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setMonthLongArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[13]);
    }

    public void setMonthLongArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[13], i, 2);
    }

    public CTEmpty insertNewMonthLong(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewMonthLong() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTEmpty;
    }

    public void removeMonthLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTEmpty> getYearLongList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda93(this), new CTRImpl$$ExternalSyntheticLambda94(this), new CTRImpl$$ExternalSyntheticLambda95(this), new CTRImpl$$ExternalSyntheticLambda96(this), new CTRImpl$$ExternalSyntheticLambda97(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getYearLongArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getYearLongArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfYearLongArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setYearLongArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[14]);
    }

    public void setYearLongArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[14], i, 2);
    }

    public CTEmpty insertNewYearLong(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewYearLong() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTEmpty;
    }

    public void removeYearLong(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTEmpty> getAnnotationRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda1(this), new CTRImpl$$ExternalSyntheticLambda2(this), new CTRImpl$$ExternalSyntheticLambda3(this), new CTRImpl$$ExternalSyntheticLambda4(this), new CTRImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getAnnotationRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getAnnotationRefArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfAnnotationRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setAnnotationRefArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[15]);
    }

    public void setAnnotationRefArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[15], i, 2);
    }

    public CTEmpty insertNewAnnotationRef(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewAnnotationRef() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTEmpty;
    }

    public void removeAnnotationRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTEmpty> getFootnoteRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda45(this), new CTRImpl$$ExternalSyntheticLambda46(this), new CTRImpl$$ExternalSyntheticLambda47(this), new CTRImpl$$ExternalSyntheticLambda48(this), new CTRImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getFootnoteRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getFootnoteRefArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfFootnoteRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setFootnoteRefArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[16]);
    }

    public void setFootnoteRefArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[16], i, 2);
    }

    public CTEmpty insertNewFootnoteRef(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewFootnoteRef() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTEmpty;
    }

    public void removeFootnoteRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTEmpty> getEndnoteRefList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda28(this), new CTRImpl$$ExternalSyntheticLambda29(this), new CTRImpl$$ExternalSyntheticLambda30(this), new CTRImpl$$ExternalSyntheticLambda31(this), new CTRImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getEndnoteRefArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getEndnoteRefArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfEndnoteRefArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setEndnoteRefArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[17]);
    }

    public void setEndnoteRefArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[17], i, 2);
    }

    public CTEmpty insertNewEndnoteRef(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewEndnoteRef() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTEmpty;
    }

    public void removeEndnoteRef(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTEmpty> getSeparatorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda132(this), new CTRImpl$$ExternalSyntheticLambda133(this), new CTRImpl$$ExternalSyntheticLambda134(this), new CTRImpl$$ExternalSyntheticLambda135(this), new CTRImpl$$ExternalSyntheticLambda136(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getSeparatorArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getSeparatorArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfSeparatorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setSeparatorArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[18]);
    }

    public void setSeparatorArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[18], i, 2);
    }

    public CTEmpty insertNewSeparator(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewSeparator() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTEmpty;
    }

    public void removeSeparator(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTEmpty> getContinuationSeparatorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda88(this), new CTRImpl$$ExternalSyntheticLambda89(this), new CTRImpl$$ExternalSyntheticLambda90(this), new CTRImpl$$ExternalSyntheticLambda91(this), new CTRImpl$$ExternalSyntheticLambda92(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getContinuationSeparatorArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getContinuationSeparatorArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfContinuationSeparatorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setContinuationSeparatorArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[19]);
    }

    public void setContinuationSeparatorArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[19], i, 2);
    }

    public CTEmpty insertNewContinuationSeparator(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewContinuationSeparator() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTEmpty;
    }

    public void removeContinuationSeparator(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTSym> getSymList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda148(this), new CTRImpl$$ExternalSyntheticLambda149(this), new CTRImpl$$ExternalSyntheticLambda150(this), new CTRImpl$$ExternalSyntheticLambda151(this), new CTRImpl$$ExternalSyntheticLambda152(this));
        }
        return javaListXmlObject;
    }

    public CTSym[] getSymArray() {
        return (CTSym[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTSym[0]);
    }

    public CTSym getSymArray(int i) {
        CTSym cTSym;
        synchronized (monitor()) {
            check_orphaned();
            cTSym = (CTSym) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (cTSym == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSym;
    }

    public int sizeOfSymArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setSymArray(CTSym[] cTSymArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSymArr, PROPERTY_QNAME[20]);
    }

    public void setSymArray(int i, CTSym cTSym) {
        generatedSetterHelperImpl(cTSym, PROPERTY_QNAME[20], i, 2);
    }

    public CTSym insertNewSym(int i) {
        CTSym cTSym;
        synchronized (monitor()) {
            check_orphaned();
            cTSym = (CTSym) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTSym;
    }

    public CTSym addNewSym() {
        CTSym cTSym;
        synchronized (monitor()) {
            check_orphaned();
            cTSym = (CTSym) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTSym;
    }

    public void removeSym(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTEmpty> getPgNumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda121(this), new CTRImpl$$ExternalSyntheticLambda122(this), new CTRImpl$$ExternalSyntheticLambda123(this), new CTRImpl$$ExternalSyntheticLambda124(this), new CTRImpl$$ExternalSyntheticLambda125(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getPgNumArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getPgNumArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfPgNumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setPgNumArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[21]);
    }

    public void setPgNumArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[21], i, 2);
    }

    public CTEmpty insertNewPgNum(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewPgNum() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTEmpty;
    }

    public void removePgNum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTEmpty> getCrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda154(this), new CTRImpl$$ExternalSyntheticLambda155(this), new CTRImpl$$ExternalSyntheticLambda156(this), new CTRImpl$$ExternalSyntheticLambda157(this), new CTRImpl$$ExternalSyntheticLambda158(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getCrArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getCrArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfCrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setCrArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[22]);
    }

    public void setCrArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[22], i, 2);
    }

    public CTEmpty insertNewCr(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewCr() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTEmpty;
    }

    public void removeCr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTEmpty> getTabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda34(this), new CTRImpl$$ExternalSyntheticLambda35(this), new CTRImpl$$ExternalSyntheticLambda36(this), new CTRImpl$$ExternalSyntheticLambda37(this), new CTRImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getTabArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getTabArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfTabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setTabArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[23]);
    }

    public void setTabArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[23], i, 2);
    }

    public CTEmpty insertNewTab(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewTab() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTEmpty;
    }

    public void removeTab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTObject> getObjectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda61(this), new CTRImpl$$ExternalSyntheticLambda62(this), new CTRImpl$$ExternalSyntheticLambda63(this), new CTRImpl$$ExternalSyntheticLambda64(this), new CTRImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTObject[] getObjectArray() {
        return (CTObject[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTObject[0]);
    }

    public CTObject getObjectArray(int i) {
        CTObject cTObject;
        synchronized (monitor()) {
            check_orphaned();
            cTObject = (CTObject) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (cTObject == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTObject;
    }

    public int sizeOfObjectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setObjectArray(CTObject[] cTObjectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTObjectArr, PROPERTY_QNAME[24]);
    }

    public void setObjectArray(int i, CTObject cTObject) {
        generatedSetterHelperImpl(cTObject, PROPERTY_QNAME[24], i, 2);
    }

    public CTObject insertNewObject(int i) {
        CTObject cTObject;
        synchronized (monitor()) {
            check_orphaned();
            cTObject = (CTObject) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTObject;
    }

    public CTObject addNewObject() {
        CTObject cTObject;
        synchronized (monitor()) {
            check_orphaned();
            cTObject = (CTObject) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTObject;
    }

    public void removeObject(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTPicture> getPictList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda143(this), new CTRImpl$$ExternalSyntheticLambda144(this), new CTRImpl$$ExternalSyntheticLambda145(this), new CTRImpl$$ExternalSyntheticLambda146(this), new CTRImpl$$ExternalSyntheticLambda147(this));
        }
        return javaListXmlObject;
    }

    public CTPicture[] getPictArray() {
        return (CTPicture[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTPicture[0]);
    }

    public CTPicture getPictArray(int i) {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (cTPicture == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPicture;
    }

    public int sizeOfPictArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setPictArray(CTPicture[] cTPictureArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPictureArr, PROPERTY_QNAME[25]);
    }

    public void setPictArray(int i, CTPicture cTPicture) {
        generatedSetterHelperImpl(cTPicture, PROPERTY_QNAME[25], i, 2);
    }

    public CTPicture insertNewPict(int i) {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTPicture;
    }

    public CTPicture addNewPict() {
        CTPicture cTPicture;
        synchronized (monitor()) {
            check_orphaned();
            cTPicture = (CTPicture) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTPicture;
    }

    public void removePict(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTFldChar> getFldCharList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda104(this), new CTRImpl$$ExternalSyntheticLambda105(this), new CTRImpl$$ExternalSyntheticLambda106(this), new CTRImpl$$ExternalSyntheticLambda107(this), new CTRImpl$$ExternalSyntheticLambda108(this));
        }
        return javaListXmlObject;
    }

    public CTFldChar[] getFldCharArray() {
        return (CTFldChar[]) getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTFldChar[0]);
    }

    public CTFldChar getFldCharArray(int i) {
        CTFldChar cTFldChar;
        synchronized (monitor()) {
            check_orphaned();
            cTFldChar = (CTFldChar) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (cTFldChar == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFldChar;
    }

    public int sizeOfFldCharArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setFldCharArray(CTFldChar[] cTFldCharArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFldCharArr, PROPERTY_QNAME[26]);
    }

    public void setFldCharArray(int i, CTFldChar cTFldChar) {
        generatedSetterHelperImpl(cTFldChar, PROPERTY_QNAME[26], i, 2);
    }

    public CTFldChar insertNewFldChar(int i) {
        CTFldChar cTFldChar;
        synchronized (monitor()) {
            check_orphaned();
            cTFldChar = (CTFldChar) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return cTFldChar;
    }

    public CTFldChar addNewFldChar() {
        CTFldChar cTFldChar;
        synchronized (monitor()) {
            check_orphaned();
            cTFldChar = (CTFldChar) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTFldChar;
    }

    public void removeFldChar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTRuby> getRubyList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda12(this), new CTRImpl$$ExternalSyntheticLambda13(this), new CTRImpl$$ExternalSyntheticLambda14(this), new CTRImpl$$ExternalSyntheticLambda15(this), new CTRImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTRuby[] getRubyArray() {
        return (CTRuby[]) getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTRuby[0]);
    }

    public CTRuby getRubyArray(int i) {
        CTRuby cTRuby;
        synchronized (monitor()) {
            check_orphaned();
            cTRuby = (CTRuby) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (cTRuby == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRuby;
    }

    public int sizeOfRubyArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setRubyArray(CTRuby[] cTRubyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRubyArr, PROPERTY_QNAME[27]);
    }

    public void setRubyArray(int i, CTRuby cTRuby) {
        generatedSetterHelperImpl(cTRuby, PROPERTY_QNAME[27], i, 2);
    }

    public CTRuby insertNewRuby(int i) {
        CTRuby cTRuby;
        synchronized (monitor()) {
            check_orphaned();
            cTRuby = (CTRuby) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return cTRuby;
    }

    public CTRuby addNewRuby() {
        CTRuby cTRuby;
        synchronized (monitor()) {
            check_orphaned();
            cTRuby = (CTRuby) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return cTRuby;
    }

    public void removeRuby(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public List<CTFtnEdnRef> getFootnoteReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda159(this), new CTRImpl$$ExternalSyntheticLambda160(this), new CTRImpl$$ExternalSyntheticLambda161(this), new CTRImpl$$ExternalSyntheticLambda162(this), new CTRImpl$$ExternalSyntheticLambda163(this));
        }
        return javaListXmlObject;
    }

    public CTFtnEdnRef[] getFootnoteReferenceArray() {
        return (CTFtnEdnRef[]) getXmlObjectArray(PROPERTY_QNAME[28], (T[]) new CTFtnEdnRef[0]);
    }

    public CTFtnEdnRef getFootnoteReferenceArray(int i) {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (cTFtnEdnRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFtnEdnRef;
    }

    public int sizeOfFootnoteReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    public void setFootnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFtnEdnRefArr, PROPERTY_QNAME[28]);
    }

    public void setFootnoteReferenceArray(int i, CTFtnEdnRef cTFtnEdnRef) {
        generatedSetterHelperImpl(cTFtnEdnRef, PROPERTY_QNAME[28], i, 2);
    }

    public CTFtnEdnRef insertNewFootnoteReference(int i) {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return cTFtnEdnRef;
    }

    public CTFtnEdnRef addNewFootnoteReference() {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return cTFtnEdnRef;
    }

    public void removeFootnoteReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    public List<CTFtnEdnRef> getEndnoteReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda39(this), new CTRImpl$$ExternalSyntheticLambda40(this), new CTRImpl$$ExternalSyntheticLambda41(this), new CTRImpl$$ExternalSyntheticLambda42(this), new CTRImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTFtnEdnRef[] getEndnoteReferenceArray() {
        return (CTFtnEdnRef[]) getXmlObjectArray(PROPERTY_QNAME[29], (T[]) new CTFtnEdnRef[0]);
    }

    public CTFtnEdnRef getEndnoteReferenceArray(int i) {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (cTFtnEdnRef == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTFtnEdnRef;
    }

    public int sizeOfEndnoteReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    public void setEndnoteReferenceArray(CTFtnEdnRef[] cTFtnEdnRefArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFtnEdnRefArr, PROPERTY_QNAME[29]);
    }

    public void setEndnoteReferenceArray(int i, CTFtnEdnRef cTFtnEdnRef) {
        generatedSetterHelperImpl(cTFtnEdnRef, PROPERTY_QNAME[29], i, 2);
    }

    public CTFtnEdnRef insertNewEndnoteReference(int i) {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return cTFtnEdnRef;
    }

    public CTFtnEdnRef addNewEndnoteReference() {
        CTFtnEdnRef cTFtnEdnRef;
        synchronized (monitor()) {
            check_orphaned();
            cTFtnEdnRef = (CTFtnEdnRef) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return cTFtnEdnRef;
    }

    public void removeEndnoteReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    public List<CTMarkup> getCommentReferenceList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda77(this), new CTRImpl$$ExternalSyntheticLambda78(this), new CTRImpl$$ExternalSyntheticLambda79(this), new CTRImpl$$ExternalSyntheticLambda80(this), new CTRImpl$$ExternalSyntheticLambda81(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCommentReferenceArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[30], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCommentReferenceArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[30], i);
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkup;
    }

    public int sizeOfCommentReferenceArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    public void setCommentReferenceArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[30]);
    }

    public void setCommentReferenceArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[30], i, 2);
    }

    public CTMarkup insertNewCommentReference(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCommentReference() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return cTMarkup;
    }

    public void removeCommentReference(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    public List<CTDrawing> getDrawingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda50(this), new CTRImpl$$ExternalSyntheticLambda51(this), new CTRImpl$$ExternalSyntheticLambda52(this), new CTRImpl$$ExternalSyntheticLambda53(this), new CTRImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTDrawing[] getDrawingArray() {
        return (CTDrawing[]) getXmlObjectArray(PROPERTY_QNAME[31], (T[]) new CTDrawing[0]);
    }

    public CTDrawing getDrawingArray(int i) {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (cTDrawing == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDrawing;
    }

    public int sizeOfDrawingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    public void setDrawingArray(CTDrawing[] cTDrawingArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDrawingArr, PROPERTY_QNAME[31]);
    }

    public void setDrawingArray(int i, CTDrawing cTDrawing) {
        generatedSetterHelperImpl(cTDrawing, PROPERTY_QNAME[31], i, 2);
    }

    public CTDrawing insertNewDrawing(int i) {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return cTDrawing;
    }

    public CTDrawing addNewDrawing() {
        CTDrawing cTDrawing;
        synchronized (monitor()) {
            check_orphaned();
            cTDrawing = (CTDrawing) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return cTDrawing;
    }

    public void removeDrawing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    public List<CTPTab> getPtabList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda17(this), new CTRImpl$$ExternalSyntheticLambda18(this), new CTRImpl$$ExternalSyntheticLambda19(this), new CTRImpl$$ExternalSyntheticLambda20(this), new CTRImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTPTab[] getPtabArray() {
        return (CTPTab[]) getXmlObjectArray(PROPERTY_QNAME[32], (T[]) new CTPTab[0]);
    }

    public CTPTab getPtabArray(int i) {
        CTPTab cTPTab;
        synchronized (monitor()) {
            check_orphaned();
            cTPTab = (CTPTab) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (cTPTab == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPTab;
    }

    public int sizeOfPtabArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    public void setPtabArray(CTPTab[] cTPTabArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPTabArr, PROPERTY_QNAME[32]);
    }

    public void setPtabArray(int i, CTPTab cTPTab) {
        generatedSetterHelperImpl(cTPTab, PROPERTY_QNAME[32], i, 2);
    }

    public CTPTab insertNewPtab(int i) {
        CTPTab cTPTab;
        synchronized (monitor()) {
            check_orphaned();
            cTPTab = (CTPTab) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return cTPTab;
    }

    public CTPTab addNewPtab() {
        CTPTab cTPTab;
        synchronized (monitor()) {
            check_orphaned();
            cTPTab = (CTPTab) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return cTPTab;
    }

    public void removePtab(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    public List<CTEmpty> getLastRenderedPageBreakList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRImpl$$ExternalSyntheticLambda120(this), new CTRImpl$$ExternalSyntheticLambda131(this), new CTRImpl$$ExternalSyntheticLambda142(this), new CTRImpl$$ExternalSyntheticLambda153(this), new CTRImpl$$ExternalSyntheticLambda164(this));
        }
        return javaListXmlObject;
    }

    public CTEmpty[] getLastRenderedPageBreakArray() {
        return (CTEmpty[]) getXmlObjectArray(PROPERTY_QNAME[33], (T[]) new CTEmpty[0]);
    }

    public CTEmpty getLastRenderedPageBreakArray(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (cTEmpty == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEmpty;
    }

    public int sizeOfLastRenderedPageBreakArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    public void setLastRenderedPageBreakArray(CTEmpty[] cTEmptyArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEmptyArr, PROPERTY_QNAME[33]);
    }

    public void setLastRenderedPageBreakArray(int i, CTEmpty cTEmpty) {
        generatedSetterHelperImpl(cTEmpty, PROPERTY_QNAME[33], i, 2);
    }

    public CTEmpty insertNewLastRenderedPageBreak(int i) {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return cTEmpty;
    }

    public CTEmpty addNewLastRenderedPageBreak() {
        CTEmpty cTEmpty;
        synchronized (monitor()) {
            check_orphaned();
            cTEmpty = (CTEmpty) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return cTEmpty;
    }

    public void removeLastRenderedPageBreak(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    public byte[] getRsidRPr() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidRPr() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[34]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidRPr(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 34
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.setRsidRPr(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidRPr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 34
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.xsetRsidRPr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }

    public byte[] getRsidDel() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[35]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidDel() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[35]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[35]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidDel(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 35
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.setRsidDel(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 35
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.xsetRsidDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[35]);
        }
    }

    public byte[] getRsidR() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[36]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidR() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[36]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[36]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidR(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 36
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.setRsidR(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidR(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 36
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRImpl.xsetRsidR(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[36]);
        }
    }
}
