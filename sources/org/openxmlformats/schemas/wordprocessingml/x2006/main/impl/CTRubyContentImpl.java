package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMath;
import org.openxmlformats.schemas.officeDocument.x2006.math.CTOMathPara;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRubyContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;

public class CTRubyContentImpl extends XmlComplexContentImpl implements CTRubyContent {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "r"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath")};
    private static final long serialVersionUID = 1;

    public CTRubyContentImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTR> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda12(this), new CTRubyContentImpl$$ExternalSyntheticLambda13(this), new CTRubyContentImpl$$ExternalSyntheticLambda14(this), new CTRubyContentImpl$$ExternalSyntheticLambda15(this), new CTRubyContentImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTR[] getRArray() {
        return (CTR[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTR[0]);
    }

    public CTR getRArray(int i) {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (ctr == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return ctr;
    }

    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setRArray(CTR[] ctrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ctrArr, PROPERTY_QNAME[0]);
    }

    public void setRArray(int i, CTR ctr) {
        generatedSetterHelperImpl(ctr, PROPERTY_QNAME[0], i, 2);
    }

    public CTR insertNewR(int i) {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return ctr;
    }

    public CTR addNewR() {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return ctr;
    }

    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda86(this), new CTRubyContentImpl$$ExternalSyntheticLambda87(this), new CTRubyContentImpl$$ExternalSyntheticLambda88(this), new CTRubyContentImpl$$ExternalSyntheticLambda89(this), new CTRubyContentImpl$$ExternalSyntheticLambda90(this));
        }
        return javaListXmlObject;
    }

    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTProofErr[0]);
    }

    public CTProofErr getProofErrArray(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[1], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setProofErrArray(CTProofErr[] cTProofErrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTProofErrArr, PROPERTY_QNAME[1]);
    }

    public void setProofErrArray(int i, CTProofErr cTProofErr) {
        generatedSetterHelperImpl(cTProofErr, PROPERTY_QNAME[1], i, 2);
    }

    public CTProofErr insertNewProofErr(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTProofErr;
    }

    public CTProofErr addNewProofErr() {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTProofErr;
    }

    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda80(this), new CTRubyContentImpl$$ExternalSyntheticLambda81(this), new CTRubyContentImpl$$ExternalSyntheticLambda82(this), new CTRubyContentImpl$$ExternalSyntheticLambda83(this), new CTRubyContentImpl$$ExternalSyntheticLambda84(this));
        }
        return javaListXmlObject;
    }

    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTPermStart[0]);
    }

    public CTPermStart getPermStartArray(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[2], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setPermStartArray(CTPermStart[] cTPermStartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermStartArr, PROPERTY_QNAME[2]);
    }

    public void setPermStartArray(int i, CTPermStart cTPermStart) {
        generatedSetterHelperImpl(cTPermStart, PROPERTY_QNAME[2], i, 2);
    }

    public CTPermStart insertNewPermStart(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTPermStart;
    }

    public CTPermStart addNewPermStart() {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPermStart;
    }

    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda28(this), new CTRubyContentImpl$$ExternalSyntheticLambda29(this), new CTRubyContentImpl$$ExternalSyntheticLambda30(this), new CTRubyContentImpl$$ExternalSyntheticLambda31(this), new CTRubyContentImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTPerm[0]);
    }

    public CTPerm getPermEndArray(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[3], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setPermEndArray(CTPerm[] cTPermArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermArr, PROPERTY_QNAME[3]);
    }

    public void setPermEndArray(int i, CTPerm cTPerm) {
        generatedSetterHelperImpl(cTPerm, PROPERTY_QNAME[3], i, 2);
    }

    public CTPerm insertNewPermEnd(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTPerm;
    }

    public CTPerm addNewPermEnd() {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTPerm;
    }

    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda85(this), new CTRubyContentImpl$$ExternalSyntheticLambda96(this), new CTRubyContentImpl$$ExternalSyntheticLambda107(this), new CTRubyContentImpl$$ExternalSyntheticLambda118(this), new CTRubyContentImpl$$ExternalSyntheticLambda129(this));
        }
        return javaListXmlObject;
    }

    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTBookmark[0]);
    }

    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[4], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBookmarkArr, PROPERTY_QNAME[4]);
    }

    public void setBookmarkStartArray(int i, CTBookmark cTBookmark) {
        generatedSetterHelperImpl(cTBookmark, PROPERTY_QNAME[4], i, 2);
    }

    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTBookmark;
    }

    public CTBookmark addNewBookmarkStart() {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBookmark;
    }

    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda1(this), new CTRubyContentImpl$$ExternalSyntheticLambda2(this), new CTRubyContentImpl$$ExternalSyntheticLambda3(this), new CTRubyContentImpl$$ExternalSyntheticLambda4(this), new CTRubyContentImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getBookmarkEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[5], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[5]);
    }

    public void setBookmarkEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[5], i, 2);
    }

    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTMarkupRange;
    }

    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda97(this), new CTRubyContentImpl$$ExternalSyntheticLambda98(this), new CTRubyContentImpl$$ExternalSyntheticLambda99(this), new CTRubyContentImpl$$ExternalSyntheticLambda100(this), new CTRubyContentImpl$$ExternalSyntheticLambda101(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[6], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[6]);
    }

    public void setMoveFromRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[6], i, 2);
    }

    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda36(this), new CTRubyContentImpl$$ExternalSyntheticLambda37(this), new CTRubyContentImpl$$ExternalSyntheticLambda38(this), new CTRubyContentImpl$$ExternalSyntheticLambda39(this), new CTRubyContentImpl$$ExternalSyntheticLambda40(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[7], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[7]);
    }

    public void setMoveFromRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[7], i, 2);
    }

    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTMarkupRange;
    }

    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda11(this), new CTRubyContentImpl$$ExternalSyntheticLambda22(this), new CTRubyContentImpl$$ExternalSyntheticLambda33(this), new CTRubyContentImpl$$ExternalSyntheticLambda34(this), new CTRubyContentImpl$$ExternalSyntheticLambda35(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[8], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[8]);
    }

    public void setMoveToRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[8], i, 2);
    }

    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda75(this), new CTRubyContentImpl$$ExternalSyntheticLambda76(this), new CTRubyContentImpl$$ExternalSyntheticLambda77(this), new CTRubyContentImpl$$ExternalSyntheticLambda78(this), new CTRubyContentImpl$$ExternalSyntheticLambda79(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveToRangeEndArray(int i) {
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

    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[9]);
    }

    public void setMoveToRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[9], i, 2);
    }

    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTMarkupRange;
    }

    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda17(this), new CTRubyContentImpl$$ExternalSyntheticLambda18(this), new CTRubyContentImpl$$ExternalSyntheticLambda19(this), new CTRubyContentImpl$$ExternalSyntheticLambda20(this), new CTRubyContentImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeStartArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[10], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[10]);
    }

    public void setCommentRangeStartArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[10], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda58(this), new CTRubyContentImpl$$ExternalSyntheticLambda59(this), new CTRubyContentImpl$$ExternalSyntheticLambda60(this), new CTRubyContentImpl$$ExternalSyntheticLambda61(this), new CTRubyContentImpl$$ExternalSyntheticLambda62(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeEndArray(int i) {
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

    public int sizeOfCommentRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[11]);
    }

    public void setCommentRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[11], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda64(this), new CTRubyContentImpl$$ExternalSyntheticLambda65(this), new CTRubyContentImpl$$ExternalSyntheticLambda66(this), new CTRubyContentImpl$$ExternalSyntheticLambda67(this), new CTRubyContentImpl$$ExternalSyntheticLambda68(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[12], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[12]);
    }

    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[12], i, 2);
    }

    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda23(this), new CTRubyContentImpl$$ExternalSyntheticLambda24(this), new CTRubyContentImpl$$ExternalSyntheticLambda25(this), new CTRubyContentImpl$$ExternalSyntheticLambda26(this), new CTRubyContentImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[13], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[13]);
    }

    public void setCustomXmlInsRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[13], i, 2);
    }

    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda91(this), new CTRubyContentImpl$$ExternalSyntheticLambda92(this), new CTRubyContentImpl$$ExternalSyntheticLambda93(this), new CTRubyContentImpl$$ExternalSyntheticLambda94(this), new CTRubyContentImpl$$ExternalSyntheticLambda95(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[14], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[14]);
    }

    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[14], i, 2);
    }

    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda124(this), new CTRubyContentImpl$$ExternalSyntheticLambda125(this), new CTRubyContentImpl$$ExternalSyntheticLambda126(this), new CTRubyContentImpl$$ExternalSyntheticLambda127(this), new CTRubyContentImpl$$ExternalSyntheticLambda128(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[15], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[15]);
    }

    public void setCustomXmlDelRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[15], i, 2);
    }

    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda53(this), new CTRubyContentImpl$$ExternalSyntheticLambda54(this), new CTRubyContentImpl$$ExternalSyntheticLambda55(this), new CTRubyContentImpl$$ExternalSyntheticLambda56(this), new CTRubyContentImpl$$ExternalSyntheticLambda57(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
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

    public int sizeOfCustomXmlMoveFromRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[16]);
    }

    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[16], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda47(this), new CTRubyContentImpl$$ExternalSyntheticLambda48(this), new CTRubyContentImpl$$ExternalSyntheticLambda49(this), new CTRubyContentImpl$$ExternalSyntheticLambda50(this), new CTRubyContentImpl$$ExternalSyntheticLambda51(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
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

    public int sizeOfCustomXmlMoveFromRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[17]);
    }

    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[17], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda69(this), new CTRubyContentImpl$$ExternalSyntheticLambda70(this), new CTRubyContentImpl$$ExternalSyntheticLambda71(this), new CTRubyContentImpl$$ExternalSyntheticLambda72(this), new CTRubyContentImpl$$ExternalSyntheticLambda73(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
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

    public int sizeOfCustomXmlMoveToRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[18]);
    }

    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[18], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda42(this), new CTRubyContentImpl$$ExternalSyntheticLambda43(this), new CTRubyContentImpl$$ExternalSyntheticLambda44(this), new CTRubyContentImpl$$ExternalSyntheticLambda45(this), new CTRubyContentImpl$$ExternalSyntheticLambda46(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
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

    public int sizeOfCustomXmlMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[19]);
    }

    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[19], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda113(this), new CTRubyContentImpl$$ExternalSyntheticLambda114(this), new CTRubyContentImpl$$ExternalSyntheticLambda115(this), new CTRubyContentImpl$$ExternalSyntheticLambda116(this), new CTRubyContentImpl$$ExternalSyntheticLambda117(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getInsArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[20], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[20]);
    }

    public void setInsArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[20], i, 2);
    }

    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewIns() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTRunTrackChange;
    }

    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda108(this), new CTRubyContentImpl$$ExternalSyntheticLambda109(this), new CTRubyContentImpl$$ExternalSyntheticLambda110(this), new CTRubyContentImpl$$ExternalSyntheticLambda111(this), new CTRubyContentImpl$$ExternalSyntheticLambda112(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getDelArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[21], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[21]);
    }

    public void setDelArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[21], i, 2);
    }

    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewDel() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTRunTrackChange;
    }

    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda6(this), new CTRubyContentImpl$$ExternalSyntheticLambda7(this), new CTRubyContentImpl$$ExternalSyntheticLambda8(this), new CTRubyContentImpl$$ExternalSyntheticLambda9(this), new CTRubyContentImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveFromArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[22], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[22]);
    }

    public void setMoveFromArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[22], i, 2);
    }

    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda102(this), new CTRubyContentImpl$$ExternalSyntheticLambda103(this), new CTRubyContentImpl$$ExternalSyntheticLambda104(this), new CTRubyContentImpl$$ExternalSyntheticLambda105(this), new CTRubyContentImpl$$ExternalSyntheticLambda106(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[23], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[23]);
    }

    public void setMoveToArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[23], i, 2);
    }

    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda119(this), new CTRubyContentImpl$$ExternalSyntheticLambda120(this), new CTRubyContentImpl$$ExternalSyntheticLambda121(this), new CTRubyContentImpl$$ExternalSyntheticLambda122(this), new CTRubyContentImpl$$ExternalSyntheticLambda123(this));
        }
        return javaListXmlObject;
    }

    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTOMathPara[0]);
    }

    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[24], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setOMathParaArray(CTOMathPara[] cTOMathParaArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathParaArr, PROPERTY_QNAME[24]);
    }

    public void setOMathParaArray(int i, CTOMathPara cTOMathPara) {
        generatedSetterHelperImpl(cTOMathPara, PROPERTY_QNAME[24], i, 2);
    }

    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTOMathPara;
    }

    public CTOMathPara addNewOMathPara() {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTOMathPara;
    }

    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTRubyContentImpl$$ExternalSyntheticLambda0(this), new CTRubyContentImpl$$ExternalSyntheticLambda41(this), new CTRubyContentImpl$$ExternalSyntheticLambda52(this), new CTRubyContentImpl$$ExternalSyntheticLambda63(this), new CTRubyContentImpl$$ExternalSyntheticLambda74(this));
        }
        return javaListXmlObject;
    }

    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTOMath[0]);
    }

    public CTOMath getOMathArray(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[25], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setOMathArray(CTOMath[] cTOMathArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathArr, PROPERTY_QNAME[25]);
    }

    public void setOMathArray(int i, CTOMath cTOMath) {
        generatedSetterHelperImpl(cTOMath, PROPERTY_QNAME[25], i, 2);
    }

    public CTOMath insertNewOMath(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTOMath;
    }

    public CTOMath addNewOMath() {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTOMath;
    }

    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }
}
