package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblOverlap;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

public class CTTblPrBaseImpl extends XmlComplexContentImpl implements CTTblPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyle"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblpPr"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblOverlap"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "bidiVisual"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyleRowBandSize"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblStyleColBandSize"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblW"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblInd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblBorders"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLayout"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellMar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLook"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCaption"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblDescription")};
    private static final long serialVersionUID = 1;

    public CTTblPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTString getTblStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetTblStyle() {
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

    public void setTblStyle(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[0], 0, 1);
    }

    public CTString addNewTblStyle() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTString;
    }

    public void unsetTblStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTblPPr getTblpPr() {
        CTTblPPr cTTblPPr;
        synchronized (monitor()) {
            check_orphaned();
            cTTblPPr = (CTTblPPr) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTblPPr == null) {
                cTTblPPr = null;
            }
        }
        return cTTblPPr;
    }

    public boolean isSetTblpPr() {
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

    public void setTblpPr(CTTblPPr cTTblPPr) {
        generatedSetterHelperImpl(cTTblPPr, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTblPPr addNewTblpPr() {
        CTTblPPr cTTblPPr;
        synchronized (monitor()) {
            check_orphaned();
            cTTblPPr = (CTTblPPr) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTblPPr;
    }

    public void unsetTblpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTblOverlap getTblOverlap() {
        CTTblOverlap cTTblOverlap;
        synchronized (monitor()) {
            check_orphaned();
            cTTblOverlap = (CTTblOverlap) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTTblOverlap == null) {
                cTTblOverlap = null;
            }
        }
        return cTTblOverlap;
    }

    public boolean isSetTblOverlap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setTblOverlap(CTTblOverlap cTTblOverlap) {
        generatedSetterHelperImpl(cTTblOverlap, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTblOverlap addNewTblOverlap() {
        CTTblOverlap cTTblOverlap;
        synchronized (monitor()) {
            check_orphaned();
            cTTblOverlap = (CTTblOverlap) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTblOverlap;
    }

    public void unsetTblOverlap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTOnOff getBidiVisual() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetBidiVisual() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setBidiVisual(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[3], 0, 1);
    }

    public CTOnOff addNewBidiVisual() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTOnOff;
    }

    public void unsetBidiVisual() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTDecimalNumber getTblStyleRowBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetTblStyleRowBandSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setTblStyleRowBandSize(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[4], 0, 1);
    }

    public CTDecimalNumber addNewTblStyleRowBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTDecimalNumber;
    }

    public void unsetTblStyleRowBandSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTDecimalNumber getTblStyleColBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetTblStyleColBandSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setTblStyleColBandSize(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[5], 0, 1);
    }

    public CTDecimalNumber addNewTblStyleColBandSize() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTDecimalNumber;
    }

    public void unsetTblStyleColBandSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTTblWidth getTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTTblWidth == null) {
                cTTblWidth = null;
            }
        }
        return cTTblWidth;
    }

    public boolean isSetTblW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setTblW(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[6], 0, 1);
    }

    public CTTblWidth addNewTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTTblWidth;
    }

    public void unsetTblW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTJcTable getJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTJcTable == null) {
                cTJcTable = null;
            }
        }
        return cTJcTable;
    }

    public boolean isSetJc() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setJc(CTJcTable cTJcTable) {
        generatedSetterHelperImpl(cTJcTable, PROPERTY_QNAME[7], 0, 1);
    }

    public CTJcTable addNewJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTJcTable;
    }

    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTblWidth getTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTblWidth == null) {
                cTTblWidth = null;
            }
        }
        return cTTblWidth;
    }

    public boolean isSetTblCellSpacing() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setTblCellSpacing(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTblWidth;
    }

    public void unsetTblCellSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTTblWidth getTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTTblWidth == null) {
                cTTblWidth = null;
            }
        }
        return cTTblWidth;
    }

    public boolean isSetTblInd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setTblInd(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[9], 0, 1);
    }

    public CTTblWidth addNewTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTblWidth;
    }

    public void unsetTblInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTTblBorders getTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTblBorders = (CTTblBorders) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTTblBorders == null) {
                cTTblBorders = null;
            }
        }
        return cTTblBorders;
    }

    public boolean isSetTblBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTblBorders(CTTblBorders cTTblBorders) {
        generatedSetterHelperImpl(cTTblBorders, PROPERTY_QNAME[10], 0, 1);
    }

    public CTTblBorders addNewTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTblBorders = (CTTblBorders) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTTblBorders;
    }

    public void unsetTblBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTShd == null) {
                cTShd = null;
            }
        }
        return cTShd;
    }

    public boolean isSetShd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setShd(CTShd cTShd) {
        generatedSetterHelperImpl(cTShd, PROPERTY_QNAME[11], 0, 1);
    }

    public CTShd addNewShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTShd;
    }

    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTTblLayoutType getTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLayoutType = (CTTblLayoutType) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTTblLayoutType == null) {
                cTTblLayoutType = null;
            }
        }
        return cTTblLayoutType;
    }

    public boolean isSetTblLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setTblLayout(CTTblLayoutType cTTblLayoutType) {
        generatedSetterHelperImpl(cTTblLayoutType, PROPERTY_QNAME[12], 0, 1);
    }

    public CTTblLayoutType addNewTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLayoutType = (CTTblLayoutType) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTTblLayoutType;
    }

    public void unsetTblLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTTblCellMar getTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTblCellMar = (CTTblCellMar) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTTblCellMar == null) {
                cTTblCellMar = null;
            }
        }
        return cTTblCellMar;
    }

    public boolean isSetTblCellMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setTblCellMar(CTTblCellMar cTTblCellMar) {
        generatedSetterHelperImpl(cTTblCellMar, PROPERTY_QNAME[13], 0, 1);
    }

    public CTTblCellMar addNewTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTblCellMar = (CTTblCellMar) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTTblCellMar;
    }

    public void unsetTblCellMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTTblLook getTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLook = (CTTblLook) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTTblLook == null) {
                cTTblLook = null;
            }
        }
        return cTTblLook;
    }

    public boolean isSetTblLook() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setTblLook(CTTblLook cTTblLook) {
        generatedSetterHelperImpl(cTTblLook, PROPERTY_QNAME[14], 0, 1);
    }

    public CTTblLook addNewTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLook = (CTTblLook) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTTblLook;
    }

    public void unsetTblLook() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTString getTblCaption() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[15], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetTblCaption() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setTblCaption(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[15], 0, 1);
    }

    public CTString addNewTblCaption() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTString;
    }

    public void unsetTblCaption() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }

    public CTString getTblDescription() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().find_element_user(PROPERTY_QNAME[16], 0);
            if (cTString == null) {
                cTString = null;
            }
        }
        return cTString;
    }

    public boolean isSetTblDescription() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[16]) != 0;
        }
        return z;
    }

    public void setTblDescription(CTString cTString) {
        generatedSetterHelperImpl(cTString, PROPERTY_QNAME[16], 0, 1);
    }

    public CTString addNewTblDescription() {
        CTString cTString;
        synchronized (monitor()) {
            check_orphaned();
            cTString = (CTString) get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return cTString;
    }

    public void unsetTblDescription() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], 0);
        }
    }
}
