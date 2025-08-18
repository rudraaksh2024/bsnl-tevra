package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class CTTextParagraphImpl extends XmlComplexContentImpl implements CTTextParagraph {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "pPr"), new QName(XSSFRelation.NS_DRAWINGML, "r"), new QName(XSSFRelation.NS_DRAWINGML, CompressorStreamFactory.BROTLI), new QName(XSSFRelation.NS_DRAWINGML, "fld"), new QName(XSSFRelation.NS_DRAWINGML, "endParaRPr")};
    private static final long serialVersionUID = 1;

    public CTTextParagraphImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextParagraphProperties getPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextParagraphProperties == null) {
                cTTextParagraphProperties = null;
            }
        }
        return cTTextParagraphProperties;
    }

    public boolean isSetPPr() {
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

    public void setPPr(CTTextParagraphProperties cTTextParagraphProperties) {
        generatedSetterHelperImpl(cTTextParagraphProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextParagraphProperties addNewPPr() {
        CTTextParagraphProperties cTTextParagraphProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextParagraphProperties = (CTTextParagraphProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextParagraphProperties;
    }

    public void unsetPPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTRegularTextRun> getRList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTextParagraphImpl$$ExternalSyntheticLambda1(this), new CTTextParagraphImpl$$ExternalSyntheticLambda2(this), new CTTextParagraphImpl$$ExternalSyntheticLambda3(this), new CTTextParagraphImpl$$ExternalSyntheticLambda4(this), new CTTextParagraphImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTRegularTextRun[] getRArray() {
        return (CTRegularTextRun[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTRegularTextRun[0]);
    }

    public CTRegularTextRun getRArray(int i) {
        CTRegularTextRun cTRegularTextRun;
        synchronized (monitor()) {
            check_orphaned();
            cTRegularTextRun = (CTRegularTextRun) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTRegularTextRun == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTRegularTextRun;
    }

    public int sizeOfRArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setRArray(CTRegularTextRun[] cTRegularTextRunArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRegularTextRunArr, PROPERTY_QNAME[1]);
    }

    public void setRArray(int i, CTRegularTextRun cTRegularTextRun) {
        generatedSetterHelperImpl(cTRegularTextRun, PROPERTY_QNAME[1], i, 2);
    }

    public CTRegularTextRun insertNewR(int i) {
        CTRegularTextRun cTRegularTextRun;
        synchronized (monitor()) {
            check_orphaned();
            cTRegularTextRun = (CTRegularTextRun) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTRegularTextRun;
    }

    public CTRegularTextRun addNewR() {
        CTRegularTextRun cTRegularTextRun;
        synchronized (monitor()) {
            check_orphaned();
            cTRegularTextRun = (CTRegularTextRun) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTRegularTextRun;
    }

    public void removeR(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTTextLineBreak> getBrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTextParagraphImpl$$ExternalSyntheticLambda10(this), new CTTextParagraphImpl$$ExternalSyntheticLambda11(this), new CTTextParagraphImpl$$ExternalSyntheticLambda12(this), new CTTextParagraphImpl$$ExternalSyntheticLambda13(this), new CTTextParagraphImpl$$ExternalSyntheticLambda14(this));
        }
        return javaListXmlObject;
    }

    public CTTextLineBreak[] getBrArray() {
        return (CTTextLineBreak[]) getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTTextLineBreak[0]);
    }

    public CTTextLineBreak getBrArray(int i) {
        CTTextLineBreak cTTextLineBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTTextLineBreak = (CTTextLineBreak) get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (cTTextLineBreak == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTextLineBreak;
    }

    public int sizeOfBrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setBrArray(CTTextLineBreak[] cTTextLineBreakArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextLineBreakArr, PROPERTY_QNAME[2]);
    }

    public void setBrArray(int i, CTTextLineBreak cTTextLineBreak) {
        generatedSetterHelperImpl(cTTextLineBreak, PROPERTY_QNAME[2], i, 2);
    }

    public CTTextLineBreak insertNewBr(int i) {
        CTTextLineBreak cTTextLineBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTTextLineBreak = (CTTextLineBreak) get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return cTTextLineBreak;
    }

    public CTTextLineBreak addNewBr() {
        CTTextLineBreak cTTextLineBreak;
        synchronized (monitor()) {
            check_orphaned();
            cTTextLineBreak = (CTTextLineBreak) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTextLineBreak;
    }

    public void removeBr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTTextField> getFldList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTextParagraphImpl$$ExternalSyntheticLambda0(this), new CTTextParagraphImpl$$ExternalSyntheticLambda6(this), new CTTextParagraphImpl$$ExternalSyntheticLambda7(this), new CTTextParagraphImpl$$ExternalSyntheticLambda8(this), new CTTextParagraphImpl$$ExternalSyntheticLambda9(this));
        }
        return javaListXmlObject;
    }

    public CTTextField[] getFldArray() {
        return (CTTextField[]) getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTTextField[0]);
    }

    public CTTextField getFldArray(int i) {
        CTTextField cTTextField;
        synchronized (monitor()) {
            check_orphaned();
            cTTextField = (CTTextField) get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (cTTextField == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTextField;
    }

    public int sizeOfFldArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setFldArray(CTTextField[] cTTextFieldArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTextFieldArr, PROPERTY_QNAME[3]);
    }

    public void setFldArray(int i, CTTextField cTTextField) {
        generatedSetterHelperImpl(cTTextField, PROPERTY_QNAME[3], i, 2);
    }

    public CTTextField insertNewFld(int i) {
        CTTextField cTTextField;
        synchronized (monitor()) {
            check_orphaned();
            cTTextField = (CTTextField) get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return cTTextField;
    }

    public CTTextField addNewFld() {
        CTTextField cTTextField;
        synchronized (monitor()) {
            check_orphaned();
            cTTextField = (CTTextField) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTTextField;
    }

    public void removeFld(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public CTTextCharacterProperties getEndParaRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextCharacterProperties = (CTTextCharacterProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTTextCharacterProperties == null) {
                cTTextCharacterProperties = null;
            }
        }
        return cTTextCharacterProperties;
    }

    public boolean isSetEndParaRPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setEndParaRPr(CTTextCharacterProperties cTTextCharacterProperties) {
        generatedSetterHelperImpl(cTTextCharacterProperties, PROPERTY_QNAME[4], 0, 1);
    }

    public CTTextCharacterProperties addNewEndParaRPr() {
        CTTextCharacterProperties cTTextCharacterProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTTextCharacterProperties = (CTTextCharacterProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTextCharacterProperties;
    }

    public void unsetEndParaRPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
