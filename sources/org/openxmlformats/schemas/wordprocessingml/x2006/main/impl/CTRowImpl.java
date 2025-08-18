package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrEx;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber;

public class CTRowImpl extends XmlComplexContentImpl implements CTRow {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblPrEx"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "trPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXml"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidRPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidR"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidDel"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "rsidTr")};
    private static final long serialVersionUID = 1;

    public CTRowImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTblPrEx getTblPrEx() {
        CTTblPrEx cTTblPrEx;
        synchronized (monitor()) {
            check_orphaned();
            cTTblPrEx = (CTTblPrEx) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTblPrEx == null) {
                cTTblPrEx = null;
            }
        }
        return cTTblPrEx;
    }

    public boolean isSetTblPrEx() {
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

    public void setTblPrEx(CTTblPrEx cTTblPrEx) {
        generatedSetterHelperImpl(cTTblPrEx, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTblPrEx addNewTblPrEx() {
        CTTblPrEx cTTblPrEx;
        synchronized (monitor()) {
            check_orphaned();
            cTTblPrEx = (CTTblPrEx) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTblPrEx;
    }

    public void unsetTblPrEx() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTrPr getTrPr() {
        CTTrPr cTTrPr;
        synchronized (monitor()) {
            check_orphaned();
            cTTrPr = (CTTrPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTrPr == null) {
                cTTrPr = null;
            }
        }
        return cTTrPr;
    }

    public boolean isSetTrPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setTrPr(CTTrPr cTTrPr) {
        generatedSetterHelperImpl(cTTrPr, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTrPr addNewTrPr() {
        CTTrPr cTTrPr;
        synchronized (monitor()) {
            check_orphaned();
            cTTrPr = (CTTrPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTrPr;
    }

    public void unsetTrPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public List<CTTc> getTcList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda57(this), new CTRowImpl$$ExternalSyntheticLambda58(this), new CTRowImpl$$ExternalSyntheticLambda59(this), new CTRowImpl$$ExternalSyntheticLambda60(this), new CTRowImpl$$ExternalSyntheticLambda61(this));
        }
        return javaListXmlObject;
    }

    public CTTc[] getTcArray() {
        return (CTTc[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTTc[0]);
    }

    public CTTc getTcArray(int i) {
        CTTc cTTc;
        synchronized (monitor()) {
            check_orphaned();
            cTTc = (CTTc) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTTc == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTc;
    }

    public int sizeOfTcArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setTcArray(CTTc[] cTTcArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTcArr, PROPERTY_QNAME[2]);
    }

    public void setTcArray(int i, CTTc cTTc) {
        generatedSetterHelperImpl(cTTc, PROPERTY_QNAME[2], i, 2);
    }

    public CTTc insertNewTc(int i) {
        CTTc cTTc;
        synchronized (monitor()) {
            check_orphaned();
            cTTc = (CTTc) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTTc;
    }

    public CTTc addNewTc() {
        CTTc cTTc;
        synchronized (monitor()) {
            check_orphaned();
            cTTc = (CTTc) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTc;
    }

    public void removeTc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTCustomXmlCell> getCustomXmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda95(this), new CTRowImpl$$ExternalSyntheticLambda106(this), new CTRowImpl$$ExternalSyntheticLambda117(this), new CTRowImpl$$ExternalSyntheticLambda128(this), new CTRowImpl$$ExternalSyntheticLambda139(this));
        }
        return javaListXmlObject;
    }

    public CTCustomXmlCell[] getCustomXmlArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTCustomXmlCell[0]);
    }

    public CTCustomXmlCell getCustomXmlArray(int i) {
        CTCustomXmlCell find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCustomXmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setCustomXmlArray(CTCustomXmlCell[] cTCustomXmlCellArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomXmlCellArr, PROPERTY_QNAME[3]);
    }

    public void setCustomXmlArray(int i, CTCustomXmlCell cTCustomXmlCell) {
        generatedSetterHelperImpl(cTCustomXmlCell, PROPERTY_QNAME[3], i, 2);
    }

    public CTCustomXmlCell insertNewCustomXml(int i) {
        CTCustomXmlCell insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public CTCustomXmlCell addNewCustomXml() {
        CTCustomXmlCell add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeCustomXml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTSdtCell> getSdtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda39(this), new CTRowImpl$$ExternalSyntheticLambda40(this), new CTRowImpl$$ExternalSyntheticLambda41(this), new CTRowImpl$$ExternalSyntheticLambda42(this), new CTRowImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTSdtCell[] getSdtArray() {
        return (CTSdtCell[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTSdtCell[0]);
    }

    public CTSdtCell getSdtArray(int i) {
        CTSdtCell cTSdtCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtCell = (CTSdtCell) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTSdtCell == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSdtCell;
    }

    public int sizeOfSdtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setSdtArray(CTSdtCell[] cTSdtCellArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSdtCellArr, PROPERTY_QNAME[4]);
    }

    public void setSdtArray(int i, CTSdtCell cTSdtCell) {
        generatedSetterHelperImpl(cTSdtCell, PROPERTY_QNAME[4], i, 2);
    }

    public CTSdtCell insertNewSdt(int i) {
        CTSdtCell cTSdtCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtCell = (CTSdtCell) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTSdtCell;
    }

    public CTSdtCell addNewSdt() {
        CTSdtCell cTSdtCell;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtCell = (CTSdtCell) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTSdtCell;
    }

    public void removeSdt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda134(this), new CTRowImpl$$ExternalSyntheticLambda135(this), new CTRowImpl$$ExternalSyntheticLambda136(this), new CTRowImpl$$ExternalSyntheticLambda137(this), new CTRowImpl$$ExternalSyntheticLambda138(this));
        }
        return javaListXmlObject;
    }

    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTProofErr[0]);
    }

    public CTProofErr getProofErrArray(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTProofErr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTProofErr;
    }

    public int sizeOfProofErrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setProofErrArray(CTProofErr[] cTProofErrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTProofErrArr, PROPERTY_QNAME[5]);
    }

    public void setProofErrArray(int i, CTProofErr cTProofErr) {
        generatedSetterHelperImpl(cTProofErr, PROPERTY_QNAME[5], i, 2);
    }

    public CTProofErr insertNewProofErr(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTProofErr;
    }

    public CTProofErr addNewProofErr() {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTProofErr;
    }

    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda28(this), new CTRowImpl$$ExternalSyntheticLambda29(this), new CTRowImpl$$ExternalSyntheticLambda30(this), new CTRowImpl$$ExternalSyntheticLambda31(this), new CTRowImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTPermStart[0]);
    }

    public CTPermStart getPermStartArray(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (cTPermStart == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPermStart;
    }

    public int sizeOfPermStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setPermStartArray(CTPermStart[] cTPermStartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermStartArr, PROPERTY_QNAME[6]);
    }

    public void setPermStartArray(int i, CTPermStart cTPermStart) {
        generatedSetterHelperImpl(cTPermStart, PROPERTY_QNAME[6], i, 2);
    }

    public CTPermStart insertNewPermStart(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTPermStart;
    }

    public CTPermStart addNewPermStart() {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTPermStart;
    }

    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda1(this), new CTRowImpl$$ExternalSyntheticLambda2(this), new CTRowImpl$$ExternalSyntheticLambda3(this), new CTRowImpl$$ExternalSyntheticLambda4(this), new CTRowImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTPerm[0]);
    }

    public CTPerm getPermEndArray(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (cTPerm == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTPerm;
    }

    public int sizeOfPermEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setPermEndArray(CTPerm[] cTPermArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermArr, PROPERTY_QNAME[7]);
    }

    public void setPermEndArray(int i, CTPerm cTPerm) {
        generatedSetterHelperImpl(cTPerm, PROPERTY_QNAME[7], i, 2);
    }

    public CTPerm insertNewPermEnd(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTPerm;
    }

    public CTPerm addNewPermEnd() {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPerm;
    }

    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda12(this), new CTRowImpl$$ExternalSyntheticLambda13(this), new CTRowImpl$$ExternalSyntheticLambda14(this), new CTRowImpl$$ExternalSyntheticLambda15(this), new CTRowImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTBookmark[0]);
    }

    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (cTBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBookmark;
    }

    public int sizeOfBookmarkStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBookmarkArr, PROPERTY_QNAME[8]);
    }

    public void setBookmarkStartArray(int i, CTBookmark cTBookmark) {
        generatedSetterHelperImpl(cTBookmark, PROPERTY_QNAME[8], i, 2);
    }

    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTBookmark;
    }

    public CTBookmark addNewBookmarkStart() {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTBookmark;
    }

    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda129(this), new CTRowImpl$$ExternalSyntheticLambda130(this), new CTRowImpl$$ExternalSyntheticLambda131(this), new CTRowImpl$$ExternalSyntheticLambda132(this), new CTRowImpl$$ExternalSyntheticLambda133(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getBookmarkEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkupRange;
    }

    public int sizeOfBookmarkEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[9]);
    }

    public void setBookmarkEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[9], i, 2);
    }

    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTMarkupRange;
    }

    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda63(this), new CTRowImpl$$ExternalSyntheticLambda64(this), new CTRowImpl$$ExternalSyntheticLambda65(this), new CTRowImpl$$ExternalSyntheticLambda66(this), new CTRowImpl$$ExternalSyntheticLambda67(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (cTMoveBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMoveBookmark;
    }

    public int sizeOfMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[10]);
    }

    public void setMoveFromRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[10], i, 2);
    }

    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda107(this), new CTRowImpl$$ExternalSyntheticLambda108(this), new CTRowImpl$$ExternalSyntheticLambda109(this), new CTRowImpl$$ExternalSyntheticLambda110(this), new CTRowImpl$$ExternalSyntheticLambda111(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkupRange;
    }

    public int sizeOfMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[11]);
    }

    public void setMoveFromRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[11], i, 2);
    }

    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTMarkupRange;
    }

    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda46(this), new CTRowImpl$$ExternalSyntheticLambda47(this), new CTRowImpl$$ExternalSyntheticLambda48(this), new CTRowImpl$$ExternalSyntheticLambda49(this), new CTRowImpl$$ExternalSyntheticLambda50(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTMoveBookmark == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMoveBookmark;
    }

    public int sizeOfMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[12]);
    }

    public void setMoveToRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[12], i, 2);
    }

    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda101(this), new CTRowImpl$$ExternalSyntheticLambda102(this), new CTRowImpl$$ExternalSyntheticLambda103(this), new CTRowImpl$$ExternalSyntheticLambda104(this), new CTRowImpl$$ExternalSyntheticLambda105(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveToRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkupRange;
    }

    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[13]);
    }

    public void setMoveToRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[13], i, 2);
    }

    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTMarkupRange;
    }

    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda96(this), new CTRowImpl$$ExternalSyntheticLambda97(this), new CTRowImpl$$ExternalSyntheticLambda98(this), new CTRowImpl$$ExternalSyntheticLambda99(this), new CTRowImpl$$ExternalSyntheticLambda100(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeStartArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkupRange;
    }

    public int sizeOfCommentRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[14]);
    }

    public void setCommentRangeStartArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[14], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda79(this), new CTRowImpl$$ExternalSyntheticLambda80(this), new CTRowImpl$$ExternalSyntheticLambda81(this), new CTRowImpl$$ExternalSyntheticLambda82(this), new CTRowImpl$$ExternalSyntheticLambda83(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (cTMarkupRange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkupRange;
    }

    public int sizeOfCommentRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[15]);
    }

    public void setCommentRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[15], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda17(this), new CTRowImpl$$ExternalSyntheticLambda18(this), new CTRowImpl$$ExternalSyntheticLambda19(this), new CTRowImpl$$ExternalSyntheticLambda20(this), new CTRowImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTrackChange;
    }

    public int sizeOfCustomXmlInsRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[16]);
    }

    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[16], i, 2);
    }

    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda68(this), new CTRowImpl$$ExternalSyntheticLambda69(this), new CTRowImpl$$ExternalSyntheticLambda70(this), new CTRowImpl$$ExternalSyntheticLambda71(this), new CTRowImpl$$ExternalSyntheticLambda72(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[17], i);
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkup;
    }

    public int sizeOfCustomXmlInsRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[17]);
    }

    public void setCustomXmlInsRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[17], i, 2);
    }

    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda90(this), new CTRowImpl$$ExternalSyntheticLambda91(this), new CTRowImpl$$ExternalSyntheticLambda92(this), new CTRowImpl$$ExternalSyntheticLambda93(this), new CTRowImpl$$ExternalSyntheticLambda94(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTrackChange;
    }

    public int sizeOfCustomXmlDelRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[18]);
    }

    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[18], i, 2);
    }

    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda34(this), new CTRowImpl$$ExternalSyntheticLambda35(this), new CTRowImpl$$ExternalSyntheticLambda36(this), new CTRowImpl$$ExternalSyntheticLambda37(this), new CTRowImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[19], i);
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkup;
    }

    public int sizeOfCustomXmlDelRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[19]);
    }

    public void setCustomXmlDelRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[19], i, 2);
    }

    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda74(this), new CTRowImpl$$ExternalSyntheticLambda75(this), new CTRowImpl$$ExternalSyntheticLambda76(this), new CTRowImpl$$ExternalSyntheticLambda77(this), new CTRowImpl$$ExternalSyntheticLambda78(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[20], i);
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTrackChange;
    }

    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[20]);
    }

    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[20], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda52(this), new CTRowImpl$$ExternalSyntheticLambda53(this), new CTRowImpl$$ExternalSyntheticLambda54(this), new CTRowImpl$$ExternalSyntheticLambda55(this), new CTRowImpl$$ExternalSyntheticLambda56(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkup;
    }

    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[21]);
    }

    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[21], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda118(this), new CTRowImpl$$ExternalSyntheticLambda119(this), new CTRowImpl$$ExternalSyntheticLambda120(this), new CTRowImpl$$ExternalSyntheticLambda121(this), new CTRowImpl$$ExternalSyntheticLambda122(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[22], i);
            if (cTTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTrackChange;
    }

    public int sizeOfCustomXmlMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[22]);
    }

    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[22], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda23(this), new CTRowImpl$$ExternalSyntheticLambda24(this), new CTRowImpl$$ExternalSyntheticLambda25(this), new CTRowImpl$$ExternalSyntheticLambda26(this), new CTRowImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (cTMarkup == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTMarkup;
    }

    public int sizeOfCustomXmlMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[23]);
    }

    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[23], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda112(this), new CTRowImpl$$ExternalSyntheticLambda113(this), new CTRowImpl$$ExternalSyntheticLambda114(this), new CTRowImpl$$ExternalSyntheticLambda115(this), new CTRowImpl$$ExternalSyntheticLambda116(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getInsArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRunTrackChange;
    }

    public int sizeOfInsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[24]);
    }

    public void setInsArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[24], i, 2);
    }

    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewIns() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTRunTrackChange;
    }

    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda123(this), new CTRowImpl$$ExternalSyntheticLambda124(this), new CTRowImpl$$ExternalSyntheticLambda125(this), new CTRowImpl$$ExternalSyntheticLambda126(this), new CTRowImpl$$ExternalSyntheticLambda127(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getDelArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRunTrackChange;
    }

    public int sizeOfDelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[25]);
    }

    public void setDelArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[25], i, 2);
    }

    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewDel() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTRunTrackChange;
    }

    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda6(this), new CTRowImpl$$ExternalSyntheticLambda7(this), new CTRowImpl$$ExternalSyntheticLambda8(this), new CTRowImpl$$ExternalSyntheticLambda9(this), new CTRowImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveFromArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRunTrackChange;
    }

    public int sizeOfMoveFromArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[26]);
    }

    public void setMoveFromArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[26], i, 2);
    }

    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda11(this), new CTRowImpl$$ExternalSyntheticLambda22(this), new CTRowImpl$$ExternalSyntheticLambda33(this), new CTRowImpl$$ExternalSyntheticLambda44(this), new CTRowImpl$$ExternalSyntheticLambda45(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (cTRunTrackChange == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRunTrackChange;
    }

    public int sizeOfMoveToArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[27]);
    }

    public void setMoveToArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[27], i, 2);
    }

    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda0(this), new CTRowImpl$$ExternalSyntheticLambda51(this), new CTRowImpl$$ExternalSyntheticLambda62(this), new CTRowImpl$$ExternalSyntheticLambda73(this), new CTRowImpl$$ExternalSyntheticLambda84(this));
        }
        return javaListXmlObject;
    }

    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[28], (T[]) new CTOMathPara[0]);
    }

    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[28], i);
            if (cTOMathPara == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOMathPara;
    }

    public int sizeOfOMathParaArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    public void setOMathParaArray(CTOMathPara[] cTOMathParaArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathParaArr, PROPERTY_QNAME[28]);
    }

    public void setOMathParaArray(int i, CTOMathPara cTOMathPara) {
        generatedSetterHelperImpl(cTOMathPara, PROPERTY_QNAME[28], i, 2);
    }

    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return cTOMathPara;
    }

    public CTOMathPara addNewOMathPara() {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return cTOMathPara;
    }

    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRowImpl$$ExternalSyntheticLambda85(this), new CTRowImpl$$ExternalSyntheticLambda86(this), new CTRowImpl$$ExternalSyntheticLambda87(this), new CTRowImpl$$ExternalSyntheticLambda88(this), new CTRowImpl$$ExternalSyntheticLambda89(this));
        }
        return javaListXmlObject;
    }

    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[29], (T[]) new CTOMath[0]);
    }

    public CTOMath getOMathArray(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (cTOMath == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOMath;
    }

    public int sizeOfOMathArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    public void setOMathArray(CTOMath[] cTOMathArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathArr, PROPERTY_QNAME[29]);
    }

    public void setOMathArray(int i, CTOMath cTOMath) {
        generatedSetterHelperImpl(cTOMath, PROPERTY_QNAME[29], i, 2);
    }

    public CTOMath insertNewOMath(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return cTOMath;
    }

    public CTOMath addNewOMath() {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return cTOMath;
    }

    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    public byte[] getRsidRPr() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[30]);
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
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[30]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
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
            r1.setByteArrayValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.setRsidRPr(byte[]):void");
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
            r3 = 30
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.xsetRsidRPr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    public byte[] getRsidR() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
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
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidR() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
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
            r3 = 31
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.setRsidR(byte[]):void");
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
            r3 = 31
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.xsetRsidR(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidR() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }

    public byte[] getRsidDel() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[32]);
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
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[32]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidDel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[32]) != null;
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
            r3 = 32
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.setRsidDel(byte[]):void");
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
            r3 = 32
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.xsetRsidDel(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidDel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[32]);
        }
    }

    public byte[] getRsidTr() {
        byte[] bArr;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[33]);
            if (simpleValue == null) {
                bArr = null;
            } else {
                bArr = simpleValue.getByteArrayValue();
            }
        }
        return bArr;
    }

    public STLongHexNumber xgetRsidTr() {
        STLongHexNumber sTLongHexNumber;
        synchronized (monitor()) {
            check_orphaned();
            sTLongHexNumber = (STLongHexNumber) get_store().find_attribute_user(PROPERTY_QNAME[33]);
        }
        return sTLongHexNumber;
    }

    public boolean isSetRsidTr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[33]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRsidTr(byte[] r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 33
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.setRsidTr(byte[]):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRsidTr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 33
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTRowImpl.xsetRsidTr(org.openxmlformats.schemas.wordprocessingml.x2006.main.STLongHexNumber):void");
    }

    public void unsetRsidTr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[33]);
        }
    }
}
