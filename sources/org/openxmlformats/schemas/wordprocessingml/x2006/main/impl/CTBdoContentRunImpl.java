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
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBdoContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCustomXmlRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDirContentRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkup;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMoveBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPerm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPermStart;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTProofErr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRel;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRunTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSmartTagRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrackChange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection;

public class CTBdoContentRunImpl extends XmlComplexContentImpl implements CTBdoContentRun {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXml"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "smartTag"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "sdt"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "dir"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bdo"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "r"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "proofErr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "permEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bookmarkEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "commentRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlInsRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlDelRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveFromRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeStart"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "customXmlMoveToRangeEnd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "ins"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "del"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveFrom"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "moveTo"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMathPara"), new QName("http://schemas.openxmlformats.org/officeDocument/2006/math", "oMath"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "fldSimple"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hyperlink"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "subDoc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "val")};
    private static final long serialVersionUID = 1;

    public CTBdoContentRunImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTCustomXmlRun> getCustomXmlList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda93(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda94(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda95(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda96(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda97(this));
        }
        return javaListXmlObject;
    }

    public CTCustomXmlRun[] getCustomXmlArray() {
        return (CTCustomXmlRun[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTCustomXmlRun[0]);
    }

    public CTCustomXmlRun getCustomXmlArray(int i) {
        CTCustomXmlRun cTCustomXmlRun;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomXmlRun = (CTCustomXmlRun) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTCustomXmlRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTCustomXmlRun;
    }

    public int sizeOfCustomXmlArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setCustomXmlArray(CTCustomXmlRun[] cTCustomXmlRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTCustomXmlRunArr, PROPERTY_QNAME[0]);
    }

    public void setCustomXmlArray(int i, CTCustomXmlRun cTCustomXmlRun) {
        generatedSetterHelperImpl(cTCustomXmlRun, PROPERTY_QNAME[0], i, 2);
    }

    public CTCustomXmlRun insertNewCustomXml(int i) {
        CTCustomXmlRun cTCustomXmlRun;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomXmlRun = (CTCustomXmlRun) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTCustomXmlRun;
    }

    public CTCustomXmlRun addNewCustomXml() {
        CTCustomXmlRun cTCustomXmlRun;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomXmlRun = (CTCustomXmlRun) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCustomXmlRun;
    }

    public void removeCustomXml(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTSmartTagRun> getSmartTagList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda82(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda83(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda84(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda85(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda86(this));
        }
        return javaListXmlObject;
    }

    public CTSmartTagRun[] getSmartTagArray() {
        return (CTSmartTagRun[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTSmartTagRun[0]);
    }

    public CTSmartTagRun getSmartTagArray(int i) {
        CTSmartTagRun cTSmartTagRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSmartTagRun = (CTSmartTagRun) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTSmartTagRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSmartTagRun;
    }

    public int sizeOfSmartTagArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSmartTagArray(CTSmartTagRun[] cTSmartTagRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSmartTagRunArr, PROPERTY_QNAME[1]);
    }

    public void setSmartTagArray(int i, CTSmartTagRun cTSmartTagRun) {
        generatedSetterHelperImpl(cTSmartTagRun, PROPERTY_QNAME[1], i, 2);
    }

    public CTSmartTagRun insertNewSmartTag(int i) {
        CTSmartTagRun cTSmartTagRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSmartTagRun = (CTSmartTagRun) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTSmartTagRun;
    }

    public CTSmartTagRun addNewSmartTag() {
        CTSmartTagRun cTSmartTagRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSmartTagRun = (CTSmartTagRun) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSmartTagRun;
    }

    public void removeSmartTag(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTSdtRun> getSdtList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda115(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda116(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda117(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda118(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda119(this));
        }
        return javaListXmlObject;
    }

    public CTSdtRun[] getSdtArray() {
        return (CTSdtRun[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTSdtRun[0]);
    }

    public CTSdtRun getSdtArray(int i) {
        CTSdtRun cTSdtRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtRun = (CTSdtRun) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTSdtRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSdtRun;
    }

    public int sizeOfSdtArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setSdtArray(CTSdtRun[] cTSdtRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSdtRunArr, PROPERTY_QNAME[2]);
    }

    public void setSdtArray(int i, CTSdtRun cTSdtRun) {
        generatedSetterHelperImpl(cTSdtRun, PROPERTY_QNAME[2], i, 2);
    }

    public CTSdtRun insertNewSdt(int i) {
        CTSdtRun cTSdtRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtRun = (CTSdtRun) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTSdtRun;
    }

    public CTSdtRun addNewSdt() {
        CTSdtRun cTSdtRun;
        synchronized (monitor()) {
            check_orphaned();
            cTSdtRun = (CTSdtRun) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTSdtRun;
    }

    public void removeSdt(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTDirContentRun> getDirList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda12(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda13(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda14(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda15(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTDirContentRun[] getDirArray() {
        return (CTDirContentRun[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTDirContentRun[0]);
    }

    public CTDirContentRun getDirArray(int i) {
        CTDirContentRun cTDirContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTDirContentRun = (CTDirContentRun) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTDirContentRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDirContentRun;
    }

    public int sizeOfDirArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setDirArray(CTDirContentRun[] cTDirContentRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDirContentRunArr, PROPERTY_QNAME[3]);
    }

    public void setDirArray(int i, CTDirContentRun cTDirContentRun) {
        generatedSetterHelperImpl(cTDirContentRun, PROPERTY_QNAME[3], i, 2);
    }

    public CTDirContentRun insertNewDir(int i) {
        CTDirContentRun cTDirContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTDirContentRun = (CTDirContentRun) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTDirContentRun;
    }

    public CTDirContentRun addNewDir() {
        CTDirContentRun cTDirContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTDirContentRun = (CTDirContentRun) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTDirContentRun;
    }

    public void removeDir(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTBdoContentRun> getBdoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda34(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda35(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda36(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda37(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTBdoContentRun[] getBdoArray() {
        return (CTBdoContentRun[]) getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTBdoContentRun[0]);
    }

    public CTBdoContentRun getBdoArray(int i) {
        CTBdoContentRun cTBdoContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTBdoContentRun = (CTBdoContentRun) get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (cTBdoContentRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTBdoContentRun;
    }

    public int sizeOfBdoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setBdoArray(CTBdoContentRun[] cTBdoContentRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBdoContentRunArr, PROPERTY_QNAME[4]);
    }

    public void setBdoArray(int i, CTBdoContentRun cTBdoContentRun) {
        generatedSetterHelperImpl(cTBdoContentRun, PROPERTY_QNAME[4], i, 2);
    }

    public CTBdoContentRun insertNewBdo(int i) {
        CTBdoContentRun cTBdoContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTBdoContentRun = (CTBdoContentRun) get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return cTBdoContentRun;
    }

    public CTBdoContentRun addNewBdo() {
        CTBdoContentRun cTBdoContentRun;
        synchronized (monitor()) {
            check_orphaned();
            cTBdoContentRun = (CTBdoContentRun) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBdoContentRun;
    }

    public void removeBdo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTR> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda66(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda77(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda78(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda79(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda80(this));
        }
        return javaListXmlObject;
    }

    public CTR[] getRArray() {
        return (CTR[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTR[0]);
    }

    public CTR getRArray(int i) {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().find_element_user(PROPERTY_QNAME[5], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setRArray(CTR[] ctrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) ctrArr, PROPERTY_QNAME[5]);
    }

    public void setRArray(int i, CTR ctr) {
        generatedSetterHelperImpl(ctr, PROPERTY_QNAME[5], i, 2);
    }

    public CTR insertNewR(int i) {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return ctr;
    }

    public CTR addNewR() {
        CTR ctr;
        synchronized (monitor()) {
            check_orphaned();
            ctr = (CTR) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return ctr;
    }

    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTProofErr> getProofErrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda11(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda22(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda33(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda44(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListXmlObject;
    }

    public CTProofErr[] getProofErrArray() {
        return (CTProofErr[]) getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTProofErr[0]);
    }

    public CTProofErr getProofErrArray(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().find_element_user(PROPERTY_QNAME[6], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setProofErrArray(CTProofErr[] cTProofErrArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTProofErrArr, PROPERTY_QNAME[6]);
    }

    public void setProofErrArray(int i, CTProofErr cTProofErr) {
        generatedSetterHelperImpl(cTProofErr, PROPERTY_QNAME[6], i, 2);
    }

    public CTProofErr insertNewProofErr(int i) {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return cTProofErr;
    }

    public CTProofErr addNewProofErr() {
        CTProofErr cTProofErr;
        synchronized (monitor()) {
            check_orphaned();
            cTProofErr = (CTProofErr) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTProofErr;
    }

    public void removeProofErr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTPermStart> getPermStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda1(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda2(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda3(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda4(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTPermStart[] getPermStartArray() {
        return (CTPermStart[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTPermStart[0]);
    }

    public CTPermStart getPermStartArray(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().find_element_user(PROPERTY_QNAME[7], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setPermStartArray(CTPermStart[] cTPermStartArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermStartArr, PROPERTY_QNAME[7]);
    }

    public void setPermStartArray(int i, CTPermStart cTPermStart) {
        generatedSetterHelperImpl(cTPermStart, PROPERTY_QNAME[7], i, 2);
    }

    public CTPermStart insertNewPermStart(int i) {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTPermStart;
    }

    public CTPermStart addNewPermStart() {
        CTPermStart cTPermStart;
        synchronized (monitor()) {
            check_orphaned();
            cTPermStart = (CTPermStart) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPermStart;
    }

    public void removePermStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTPerm> getPermEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda45(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda46(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda47(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda48(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTPerm[] getPermEndArray() {
        return (CTPerm[]) getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTPerm[0]);
    }

    public CTPerm getPermEndArray(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().find_element_user(PROPERTY_QNAME[8], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setPermEndArray(CTPerm[] cTPermArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPermArr, PROPERTY_QNAME[8]);
    }

    public void setPermEndArray(int i, CTPerm cTPerm) {
        generatedSetterHelperImpl(cTPerm, PROPERTY_QNAME[8], i, 2);
    }

    public CTPerm insertNewPermEnd(int i) {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return cTPerm;
    }

    public CTPerm addNewPermEnd() {
        CTPerm cTPerm;
        synchronized (monitor()) {
            check_orphaned();
            cTPerm = (CTPerm) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTPerm;
    }

    public void removePermEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTBookmark> getBookmarkStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda67(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda68(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda69(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda70(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTBookmark[] getBookmarkStartArray() {
        return (CTBookmark[]) getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTBookmark[0]);
    }

    public CTBookmark getBookmarkStartArray(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().find_element_user(PROPERTY_QNAME[9], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setBookmarkStartArray(CTBookmark[] cTBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBookmarkArr, PROPERTY_QNAME[9]);
    }

    public void setBookmarkStartArray(int i, CTBookmark cTBookmark) {
        generatedSetterHelperImpl(cTBookmark, PROPERTY_QNAME[9], i, 2);
    }

    public CTBookmark insertNewBookmarkStart(int i) {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return cTBookmark;
    }

    public CTBookmark addNewBookmarkStart() {
        CTBookmark cTBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTBookmark = (CTBookmark) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTBookmark;
    }

    public void removeBookmarkStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTMarkupRange> getBookmarkEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda126(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda127(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda128(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda129(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda130(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getBookmarkEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getBookmarkEndArray(int i) {
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

    public int sizeOfBookmarkEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setBookmarkEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[10]);
    }

    public void setBookmarkEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[10], i, 2);
    }

    public CTMarkupRange insertNewBookmarkEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewBookmarkEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTMarkupRange;
    }

    public void removeBookmarkEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTMoveBookmark> getMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda148(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda149(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda150(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda151(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda152(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveFromRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveFromRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[11], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setMoveFromRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[11]);
    }

    public void setMoveFromRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[11], i, 2);
    }

    public CTMoveBookmark insertNewMoveFromRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveFromRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTMarkupRange> getMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda0(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda81(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda92(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda103(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda114(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveFromRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveFromRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[12], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setMoveFromRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[12]);
    }

    public void setMoveFromRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[12], i, 2);
    }

    public CTMarkupRange insertNewMoveFromRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveFromRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTMarkupRange;
    }

    public void removeMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTMoveBookmark> getMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda98(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda99(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda100(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda101(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda102(this));
        }
        return javaListXmlObject;
    }

    public CTMoveBookmark[] getMoveToRangeStartArray() {
        return (CTMoveBookmark[]) getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTMoveBookmark[0]);
    }

    public CTMoveBookmark getMoveToRangeStartArray(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().find_element_user(PROPERTY_QNAME[13], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setMoveToRangeStartArray(CTMoveBookmark[] cTMoveBookmarkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMoveBookmarkArr, PROPERTY_QNAME[13]);
    }

    public void setMoveToRangeStartArray(int i, CTMoveBookmark cTMoveBookmark) {
        generatedSetterHelperImpl(cTMoveBookmark, PROPERTY_QNAME[13], i, 2);
    }

    public CTMoveBookmark insertNewMoveToRangeStart(int i) {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return cTMoveBookmark;
    }

    public CTMoveBookmark addNewMoveToRangeStart() {
        CTMoveBookmark cTMoveBookmark;
        synchronized (monitor()) {
            check_orphaned();
            cTMoveBookmark = (CTMoveBookmark) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTMoveBookmark;
    }

    public void removeMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTMarkupRange> getMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda159(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda160(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda161(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda162(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda163(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getMoveToRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getMoveToRangeEndArray(int i) {
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

    public int sizeOfMoveToRangeEndArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setMoveToRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[14]);
    }

    public void setMoveToRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[14], i, 2);
    }

    public CTMarkupRange insertNewMoveToRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewMoveToRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTMarkupRange;
    }

    public void removeMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda72(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda73(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda74(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda75(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda76(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeStartArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeStartArray(int i) {
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

    public int sizeOfCommentRangeStartArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setCommentRangeStartArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[15]);
    }

    public void setCommentRangeStartArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[15], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeStart(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeStart() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTMarkupRange> getCommentRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda6(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda7(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda8(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda9(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTMarkupRange[] getCommentRangeEndArray() {
        return (CTMarkupRange[]) getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTMarkupRange[0]);
    }

    public CTMarkupRange getCommentRangeEndArray(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().find_element_user(PROPERTY_QNAME[16], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setCommentRangeEndArray(CTMarkupRange[] cTMarkupRangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupRangeArr, PROPERTY_QNAME[16]);
    }

    public void setCommentRangeEndArray(int i, CTMarkupRange cTMarkupRange) {
        generatedSetterHelperImpl(cTMarkupRange, PROPERTY_QNAME[16], i, 2);
    }

    public CTMarkupRange insertNewCommentRangeEnd(int i) {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return cTMarkupRange;
    }

    public CTMarkupRange addNewCommentRangeEnd() {
        CTMarkupRange cTMarkupRange;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkupRange = (CTMarkupRange) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTMarkupRange;
    }

    public void removeCommentRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTTrackChange> getCustomXmlInsRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda50(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda51(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda52(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda53(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlInsRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlInsRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[17], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[17]);
    }

    public void setCustomXmlInsRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[17], i, 2);
    }

    public CTTrackChange insertNewCustomXmlInsRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlInsRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlInsRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTMarkup> getCustomXmlInsRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda125(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda136(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda147(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda158(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda169(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlInsRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlInsRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[18], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setCustomXmlInsRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[18]);
    }

    public void setCustomXmlInsRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[18], i, 2);
    }

    public CTMarkup insertNewCustomXmlInsRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlInsRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlInsRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTTrackChange> getCustomXmlDelRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda17(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda18(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda19(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda20(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlDelRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlDelRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[19], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[19]);
    }

    public void setCustomXmlDelRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[19], i, 2);
    }

    public CTTrackChange insertNewCustomXmlDelRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlDelRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlDelRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTMarkup> getCustomXmlDelRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda153(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda154(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda155(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda156(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda157(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlDelRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlDelRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[20], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setCustomXmlDelRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[20]);
    }

    public void setCustomXmlDelRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[20], i, 2);
    }

    public CTMarkup insertNewCustomXmlDelRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlDelRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlDelRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveFromRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda28(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda29(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda30(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda31(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveFromRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveFromRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[21], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[21]);
    }

    public void setCustomXmlMoveFromRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[21], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveFromRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveFromRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveFromRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveFromRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda137(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda138(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda139(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda140(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda141(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveFromRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveFromRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[22], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveFromRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[22]);
    }

    public void setCustomXmlMoveFromRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[22], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveFromRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveFromRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveFromRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTTrackChange> getCustomXmlMoveToRangeStartList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda56(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda57(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda58(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda59(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTTrackChange[] getCustomXmlMoveToRangeStartArray() {
        return (CTTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTTrackChange[0]);
    }

    public CTTrackChange getCustomXmlMoveToRangeStartArray(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().find_element_user(PROPERTY_QNAME[23], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeStartArray(CTTrackChange[] cTTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTrackChangeArr, PROPERTY_QNAME[23]);
    }

    public void setCustomXmlMoveToRangeStartArray(int i, CTTrackChange cTTrackChange) {
        generatedSetterHelperImpl(cTTrackChange, PROPERTY_QNAME[23], i, 2);
    }

    public CTTrackChange insertNewCustomXmlMoveToRangeStart(int i) {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTTrackChange;
    }

    public CTTrackChange addNewCustomXmlMoveToRangeStart() {
        CTTrackChange cTTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTTrackChange = (CTTrackChange) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTTrackChange;
    }

    public void removeCustomXmlMoveToRangeStart(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTMarkup> getCustomXmlMoveToRangeEndList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda142(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda143(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda144(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda145(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda146(this));
        }
        return javaListXmlObject;
    }

    public CTMarkup[] getCustomXmlMoveToRangeEndArray() {
        return (CTMarkup[]) getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTMarkup[0]);
    }

    public CTMarkup getCustomXmlMoveToRangeEndArray(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().find_element_user(PROPERTY_QNAME[24], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setCustomXmlMoveToRangeEndArray(CTMarkup[] cTMarkupArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTMarkupArr, PROPERTY_QNAME[24]);
    }

    public void setCustomXmlMoveToRangeEndArray(int i, CTMarkup cTMarkup) {
        generatedSetterHelperImpl(cTMarkup, PROPERTY_QNAME[24], i, 2);
    }

    public CTMarkup insertNewCustomXmlMoveToRangeEnd(int i) {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return cTMarkup;
    }

    public CTMarkup addNewCustomXmlMoveToRangeEnd() {
        CTMarkup cTMarkup;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkup = (CTMarkup) get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return cTMarkup;
    }

    public void removeCustomXmlMoveToRangeEnd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTRunTrackChange> getInsList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda104(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda105(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda106(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda107(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda108(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getInsArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getInsArray(int i) {
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

    public int sizeOfInsArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setInsArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[25]);
    }

    public void setInsArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[25], i, 2);
    }

    public CTRunTrackChange insertNewIns(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewIns() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return cTRunTrackChange;
    }

    public void removeIns(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTRunTrackChange> getDelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda164(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda165(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda166(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda167(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda168(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getDelArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getDelArray(int i) {
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

    public int sizeOfDelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setDelArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[26]);
    }

    public void setDelArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[26], i, 2);
    }

    public CTRunTrackChange insertNewDel(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewDel() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return cTRunTrackChange;
    }

    public void removeDel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTRunTrackChange> getMoveFromList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda23(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda24(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda25(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda26(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveFromArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveFromArray(int i) {
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

    public int sizeOfMoveFromArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setMoveFromArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[27]);
    }

    public void setMoveFromArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[27], i, 2);
    }

    public CTRunTrackChange insertNewMoveFrom(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveFrom() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveFrom(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public List<CTRunTrackChange> getMoveToList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda131(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda132(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda133(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda134(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda135(this));
        }
        return javaListXmlObject;
    }

    public CTRunTrackChange[] getMoveToArray() {
        return (CTRunTrackChange[]) getXmlObjectArray(PROPERTY_QNAME[28], (T[]) new CTRunTrackChange[0]);
    }

    public CTRunTrackChange getMoveToArray(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().find_element_user(PROPERTY_QNAME[28], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    public void setMoveToArray(CTRunTrackChange[] cTRunTrackChangeArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRunTrackChangeArr, PROPERTY_QNAME[28]);
    }

    public void setMoveToArray(int i, CTRunTrackChange cTRunTrackChange) {
        generatedSetterHelperImpl(cTRunTrackChange, PROPERTY_QNAME[28], i, 2);
    }

    public CTRunTrackChange insertNewMoveTo(int i) {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return cTRunTrackChange;
    }

    public CTRunTrackChange addNewMoveTo() {
        CTRunTrackChange cTRunTrackChange;
        synchronized (monitor()) {
            check_orphaned();
            cTRunTrackChange = (CTRunTrackChange) get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return cTRunTrackChange;
    }

    public void removeMoveTo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    public List<CTOMathPara> getOMathParaList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda61(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda62(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda63(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda64(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTOMathPara[] getOMathParaArray() {
        return (CTOMathPara[]) getXmlObjectArray(PROPERTY_QNAME[29], (T[]) new CTOMathPara[0]);
    }

    public CTOMathPara getOMathParaArray(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().find_element_user(PROPERTY_QNAME[29], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    public void setOMathParaArray(CTOMathPara[] cTOMathParaArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathParaArr, PROPERTY_QNAME[29]);
    }

    public void setOMathParaArray(int i, CTOMathPara cTOMathPara) {
        generatedSetterHelperImpl(cTOMathPara, PROPERTY_QNAME[29], i, 2);
    }

    public CTOMathPara insertNewOMathPara(int i) {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return cTOMathPara;
    }

    public CTOMathPara addNewOMathPara() {
        CTOMathPara cTOMathPara;
        synchronized (monitor()) {
            check_orphaned();
            cTOMathPara = (CTOMathPara) get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return cTOMathPara;
    }

    public void removeOMathPara(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    public List<CTOMath> getOMathList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda120(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda121(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda122(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda123(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda124(this));
        }
        return javaListXmlObject;
    }

    public CTOMath[] getOMathArray() {
        return (CTOMath[]) getXmlObjectArray(PROPERTY_QNAME[30], (T[]) new CTOMath[0]);
    }

    public CTOMath getOMathArray(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().find_element_user(PROPERTY_QNAME[30], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[30]);
        }
        return count_elements;
    }

    public void setOMathArray(CTOMath[] cTOMathArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOMathArr, PROPERTY_QNAME[30]);
    }

    public void setOMathArray(int i, CTOMath cTOMath) {
        generatedSetterHelperImpl(cTOMath, PROPERTY_QNAME[30], i, 2);
    }

    public CTOMath insertNewOMath(int i) {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().insert_element_user(PROPERTY_QNAME[30], i);
        }
        return cTOMath;
    }

    public CTOMath addNewOMath() {
        CTOMath cTOMath;
        synchronized (monitor()) {
            check_orphaned();
            cTOMath = (CTOMath) get_store().add_element_user(PROPERTY_QNAME[30]);
        }
        return cTOMath;
    }

    public void removeOMath(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[30], i);
        }
    }

    public List<CTSimpleField> getFldSimpleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda87(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda88(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda89(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda90(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda91(this));
        }
        return javaListXmlObject;
    }

    public CTSimpleField[] getFldSimpleArray() {
        return (CTSimpleField[]) getXmlObjectArray(PROPERTY_QNAME[31], (T[]) new CTSimpleField[0]);
    }

    public CTSimpleField getFldSimpleArray(int i) {
        CTSimpleField cTSimpleField;
        synchronized (monitor()) {
            check_orphaned();
            cTSimpleField = (CTSimpleField) get_store().find_element_user(PROPERTY_QNAME[31], i);
            if (cTSimpleField == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTSimpleField;
    }

    public int sizeOfFldSimpleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[31]);
        }
        return count_elements;
    }

    public void setFldSimpleArray(CTSimpleField[] cTSimpleFieldArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSimpleFieldArr, PROPERTY_QNAME[31]);
    }

    public void setFldSimpleArray(int i, CTSimpleField cTSimpleField) {
        generatedSetterHelperImpl(cTSimpleField, PROPERTY_QNAME[31], i, 2);
    }

    public CTSimpleField insertNewFldSimple(int i) {
        CTSimpleField cTSimpleField;
        synchronized (monitor()) {
            check_orphaned();
            cTSimpleField = (CTSimpleField) get_store().insert_element_user(PROPERTY_QNAME[31], i);
        }
        return cTSimpleField;
    }

    public CTSimpleField addNewFldSimple() {
        CTSimpleField cTSimpleField;
        synchronized (monitor()) {
            check_orphaned();
            cTSimpleField = (CTSimpleField) get_store().add_element_user(PROPERTY_QNAME[31]);
        }
        return cTSimpleField;
    }

    public void removeFldSimple(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[31], i);
        }
    }

    public List<CTHyperlink> getHyperlinkList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda109(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda110(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda111(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda112(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda113(this));
        }
        return javaListXmlObject;
    }

    public CTHyperlink[] getHyperlinkArray() {
        return (CTHyperlink[]) getXmlObjectArray(PROPERTY_QNAME[32], (T[]) new CTHyperlink[0]);
    }

    public CTHyperlink getHyperlinkArray(int i) {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().find_element_user(PROPERTY_QNAME[32], i);
            if (cTHyperlink == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTHyperlink;
    }

    public int sizeOfHyperlinkArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[32]);
        }
        return count_elements;
    }

    public void setHyperlinkArray(CTHyperlink[] cTHyperlinkArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHyperlinkArr, PROPERTY_QNAME[32]);
    }

    public void setHyperlinkArray(int i, CTHyperlink cTHyperlink) {
        generatedSetterHelperImpl(cTHyperlink, PROPERTY_QNAME[32], i, 2);
    }

    public CTHyperlink insertNewHyperlink(int i) {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().insert_element_user(PROPERTY_QNAME[32], i);
        }
        return cTHyperlink;
    }

    public CTHyperlink addNewHyperlink() {
        CTHyperlink cTHyperlink;
        synchronized (monitor()) {
            check_orphaned();
            cTHyperlink = (CTHyperlink) get_store().add_element_user(PROPERTY_QNAME[32]);
        }
        return cTHyperlink;
    }

    public void removeHyperlink(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[32], i);
        }
    }

    public List<CTRel> getSubDocList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBdoContentRunImpl$$ExternalSyntheticLambda39(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda40(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda41(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda42(this), new CTBdoContentRunImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTRel[] getSubDocArray() {
        return (CTRel[]) getXmlObjectArray(PROPERTY_QNAME[33], (T[]) new CTRel[0]);
    }

    public CTRel getSubDocArray(int i) {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().find_element_user(PROPERTY_QNAME[33], i);
            if (cTRel == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRel;
    }

    public int sizeOfSubDocArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[33]);
        }
        return count_elements;
    }

    public void setSubDocArray(CTRel[] cTRelArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRelArr, PROPERTY_QNAME[33]);
    }

    public void setSubDocArray(int i, CTRel cTRel) {
        generatedSetterHelperImpl(cTRel, PROPERTY_QNAME[33], i, 2);
    }

    public CTRel insertNewSubDoc(int i) {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().insert_element_user(PROPERTY_QNAME[33], i);
        }
        return cTRel;
    }

    public CTRel addNewSubDoc() {
        CTRel cTRel;
        synchronized (monitor()) {
            check_orphaned();
            cTRel = (CTRel) get_store().add_element_user(PROPERTY_QNAME[33]);
        }
        return cTRel;
    }

    public void removeSubDoc(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[33], i);
        }
    }

    public STDirection.Enum getVal() {
        STDirection.Enum enumR;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[34]);
            if (simpleValue == null) {
                enumR = null;
            } else {
                enumR = (STDirection.Enum) simpleValue.getEnumValue();
            }
        }
        return enumR;
    }

    public STDirection xgetVal() {
        STDirection sTDirection;
        synchronized (monitor()) {
            check_orphaned();
            sTDirection = (STDirection) get_store().find_attribute_user(PROPERTY_QNAME[34]);
        }
        return sTDirection;
    }

    public boolean isSetVal() {
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
    public void setVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection.Enum r6) {
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
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBdoContentRunImpl.setVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection r6) {
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
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection r1 = (org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.wordprocessingml.x2006.main.impl.CTBdoContentRunImpl.xsetVal(org.openxmlformats.schemas.wordprocessingml.x2006.main.STDirection):void");
    }

    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[34]);
        }
    }
}
