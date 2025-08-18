package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPrBase;

public class CTTrPrBaseImpl extends XmlComplexContentImpl implements CTTrPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "divId"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridBefore"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridAfter"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wBefore"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "wAfter"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cantSplit"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trHeight"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblHeader"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", CellUtil.HIDDEN)};
    private static final long serialVersionUID = 1;

    public CTTrPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCnf> getCnfStyleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda0(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda11(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda22(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda33(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTCnf[] getCnfStyleArray() {
        return (CTCnf[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCnf[0]);
    }

    public CTCnf getCnfStyleArray(int i) {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCnf == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCnf;
    }

    public int sizeOfCnfStyleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCnfStyleArray(CTCnf[] cTCnfArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCnfArr, PROPERTY_QNAME[0]);
    }

    public void setCnfStyleArray(int i, CTCnf cTCnf) {
        generatedSetterHelperImpl(cTCnf, PROPERTY_QNAME[0], i, 2);
    }

    public CTCnf insertNewCnfStyle(int i) {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCnf;
    }

    public CTCnf addNewCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCnf;
    }

    public void removeCnfStyle(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTDecimalNumber> getDivIdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda45(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda46(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda47(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda48(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTDecimalNumber[] getDivIdArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTDecimalNumber[0]);
    }

    public CTDecimalNumber getDivIdArray(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDecimalNumber;
    }

    public int sizeOfDivIdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setDivIdArray(CTDecimalNumber[] cTDecimalNumberArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDecimalNumberArr, PROPERTY_QNAME[1]);
    }

    public void setDivIdArray(int i, CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[1], i, 2);
    }

    public CTDecimalNumber insertNewDivId(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTDecimalNumber;
    }

    public CTDecimalNumber addNewDivId() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTDecimalNumber;
    }

    public void removeDivId(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTDecimalNumber> getGridBeforeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda55(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda56(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda57(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda58(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda59(this));
        }
        return javaListXmlObject;
    }

    public CTDecimalNumber[] getGridBeforeArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTDecimalNumber[0]);
    }

    public CTDecimalNumber getGridBeforeArray(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDecimalNumber;
    }

    public int sizeOfGridBeforeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setGridBeforeArray(CTDecimalNumber[] cTDecimalNumberArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDecimalNumberArr, PROPERTY_QNAME[2]);
    }

    public void setGridBeforeArray(int i, CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[2], i, 2);
    }

    public CTDecimalNumber insertNewGridBefore(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTDecimalNumber;
    }

    public CTDecimalNumber addNewGridBefore() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTDecimalNumber;
    }

    public void removeGridBefore(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTDecimalNumber> getGridAfterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda12(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda13(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda14(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda15(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTDecimalNumber[] getGridAfterArray() {
        return (CTDecimalNumber[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTDecimalNumber[0]);
    }

    public CTDecimalNumber getGridAfterArray(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTDecimalNumber == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDecimalNumber;
    }

    public int sizeOfGridAfterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setGridAfterArray(CTDecimalNumber[] cTDecimalNumberArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDecimalNumberArr, PROPERTY_QNAME[3]);
    }

    public void setGridAfterArray(int i, CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[3], i, 2);
    }

    public CTDecimalNumber insertNewGridAfter(int i) {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTDecimalNumber;
    }

    public CTDecimalNumber addNewGridAfter() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDecimalNumber;
    }

    public void removeGridAfter(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTTblWidth> getWBeforeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda50(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda51(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda52(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda53(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTTblWidth[] getWBeforeArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTTblWidth[0]);
    }

    public CTTblWidth getWBeforeArray(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTblWidth;
    }

    public int sizeOfWBeforeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setWBeforeArray(CTTblWidth[] cTTblWidthArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTblWidthArr, PROPERTY_QNAME[4]);
    }

    public void setWBeforeArray(int i, CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[4], i, 2);
    }

    public CTTblWidth insertNewWBefore(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTTblWidth;
    }

    public CTTblWidth addNewWBefore() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTblWidth;
    }

    public void removeWBefore(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTTblWidth> getWAfterList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda6(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda7(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda8(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda9(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTTblWidth[] getWAfterArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTTblWidth[0]);
    }

    public CTTblWidth getWAfterArray(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTblWidth;
    }

    public int sizeOfWAfterArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setWAfterArray(CTTblWidth[] cTTblWidthArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTblWidthArr, PROPERTY_QNAME[5]);
    }

    public void setWAfterArray(int i, CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[5], i, 2);
    }

    public CTTblWidth insertNewWAfter(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTTblWidth;
    }

    public CTTblWidth addNewWAfter() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTTblWidth;
    }

    public void removeWAfter(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTOnOff> getCantSplitList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda39(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda40(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda41(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda42(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getCantSplitArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getCantSplitArray(int i) {
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

    public int sizeOfCantSplitArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setCantSplitArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[6]);
    }

    public void setCantSplitArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[6], i, 2);
    }

    public CTOnOff insertNewCantSplit(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewCantSplit() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTOnOff;
    }

    public void removeCantSplit(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTHeight> getTrHeightList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda28(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda29(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda30(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda31(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTHeight[] getTrHeightArray() {
        return (CTHeight[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTHeight[0]);
    }

    public CTHeight getTrHeightArray(int i) {
        CTHeight cTHeight;
        synchronized (monitor()) {
            check_orphaned();
            cTHeight = (CTHeight) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTHeight == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHeight;
    }

    public int sizeOfTrHeightArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setTrHeightArray(CTHeight[] cTHeightArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHeightArr, PROPERTY_QNAME[7]);
    }

    public void setTrHeightArray(int i, CTHeight cTHeight) {
        generatedSetterHelperImpl(cTHeight, PROPERTY_QNAME[7], i, 2);
    }

    public CTHeight insertNewTrHeight(int i) {
        CTHeight cTHeight;
        synchronized (monitor()) {
            check_orphaned();
            cTHeight = (CTHeight) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTHeight;
    }

    public CTHeight addNewTrHeight() {
        CTHeight cTHeight;
        synchronized (monitor()) {
            check_orphaned();
            cTHeight = (CTHeight) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTHeight;
    }

    public void removeTrHeight(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTOnOff> getTblHeaderList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda34(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda35(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda36(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda37(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getTblHeaderArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getTblHeaderArray(int i) {
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

    public int sizeOfTblHeaderArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setTblHeaderArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[8]);
    }

    public void setTblHeaderArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[8], i, 2);
    }

    public CTOnOff insertNewTblHeader(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewTblHeader() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTOnOff;
    }

    public void removeTblHeader(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTTblWidth> getTblCellSpacingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda1(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda2(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda3(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda4(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTTblWidth[] getTblCellSpacingArray() {
        return (CTTblWidth[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTTblWidth[0]);
    }

    public CTTblWidth getTblCellSpacingArray(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTTblWidth == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTblWidth;
    }

    public int sizeOfTblCellSpacingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setTblCellSpacingArray(CTTblWidth[] cTTblWidthArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTblWidthArr, PROPERTY_QNAME[9]);
    }

    public void setTblCellSpacingArray(int i, CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[9], i, 2);
    }

    public CTTblWidth insertNewTblCellSpacing(int i) {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTTblWidth;
    }

    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTblWidth;
    }

    public void removeTblCellSpacing(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTJcTable> getJcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda17(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda18(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda19(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda20(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTJcTable[] getJcArray() {
        return (CTJcTable[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTJcTable[0]);
    }

    public CTJcTable getJcArray(int i) {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTJcTable == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTJcTable;
    }

    public int sizeOfJcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setJcArray(CTJcTable[] cTJcTableArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTJcTableArr, PROPERTY_QNAME[10]);
    }

    public void setJcArray(int i, CTJcTable cTJcTable) {
        generatedSetterHelperImpl(cTJcTable, PROPERTY_QNAME[10], i, 2);
    }

    public CTJcTable insertNewJc(int i) {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTJcTable;
    }

    public CTJcTable addNewJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTJcTable;
    }

    public void removeJc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTOnOff> getHiddenList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTrPrBaseImpl$$ExternalSyntheticLambda23(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda24(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda25(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda26(this), new CTTrPrBaseImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTOnOff[] getHiddenArray() {
        return (CTOnOff[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTOnOff[0]);
    }

    public CTOnOff getHiddenArray(int i) {
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

    public int sizeOfHiddenArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setHiddenArray(CTOnOff[] cTOnOffArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOnOffArr, PROPERTY_QNAME[11]);
    }

    public void setHiddenArray(int i, CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[11], i, 2);
    }

    public CTOnOff insertNewHidden(int i) {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTOnOff;
    }

    public CTOnOff addNewHidden() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTOnOff;
    }

    public void removeHidden(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }
}
