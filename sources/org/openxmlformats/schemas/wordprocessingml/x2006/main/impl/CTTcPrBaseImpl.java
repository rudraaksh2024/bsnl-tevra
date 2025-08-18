package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTCnf;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeaders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPrBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextDirection;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;

public class CTTcPrBaseImpl extends XmlComplexContentImpl implements CTTcPrBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "cnfStyle"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcW"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "gridSpan"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hMerge"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vMerge"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcBorders"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "noWrap"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcMar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "textDirection"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tcFitText"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "vAlign"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "hideMark"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "headers")};
    private static final long serialVersionUID = 1;

    public CTTcPrBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTCnf getCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTCnf == null) {
                cTCnf = null;
            }
        }
        return cTCnf;
    }

    public boolean isSetCnfStyle() {
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

    public void setCnfStyle(CTCnf cTCnf) {
        generatedSetterHelperImpl(cTCnf, PROPERTY_QNAME[0], 0, 1);
    }

    public CTCnf addNewCnfStyle() {
        CTCnf cTCnf;
        synchronized (monitor()) {
            check_orphaned();
            cTCnf = (CTCnf) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTCnf;
    }

    public void unsetCnfStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTblWidth getTcW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTblWidth == null) {
                cTTblWidth = null;
            }
        }
        return cTTblWidth;
    }

    public boolean isSetTcW() {
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

    public void setTcW(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTblWidth addNewTcW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTblWidth;
    }

    public void unsetTcW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTDecimalNumber getGridSpan() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTDecimalNumber == null) {
                cTDecimalNumber = null;
            }
        }
        return cTDecimalNumber;
    }

    public boolean isSetGridSpan() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setGridSpan(CTDecimalNumber cTDecimalNumber) {
        generatedSetterHelperImpl(cTDecimalNumber, PROPERTY_QNAME[2], 0, 1);
    }

    public CTDecimalNumber addNewGridSpan() {
        CTDecimalNumber cTDecimalNumber;
        synchronized (monitor()) {
            check_orphaned();
            cTDecimalNumber = (CTDecimalNumber) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTDecimalNumber;
    }

    public void unsetGridSpan() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTHMerge getHMerge() {
        CTHMerge cTHMerge;
        synchronized (monitor()) {
            check_orphaned();
            cTHMerge = (CTHMerge) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTHMerge == null) {
                cTHMerge = null;
            }
        }
        return cTHMerge;
    }

    public boolean isSetHMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setHMerge(CTHMerge cTHMerge) {
        generatedSetterHelperImpl(cTHMerge, PROPERTY_QNAME[3], 0, 1);
    }

    public CTHMerge addNewHMerge() {
        CTHMerge cTHMerge;
        synchronized (monitor()) {
            check_orphaned();
            cTHMerge = (CTHMerge) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTHMerge;
    }

    public void unsetHMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTVMerge getVMerge() {
        CTVMerge cTVMerge;
        synchronized (monitor()) {
            check_orphaned();
            cTVMerge = (CTVMerge) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTVMerge == null) {
                cTVMerge = null;
            }
        }
        return cTVMerge;
    }

    public boolean isSetVMerge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setVMerge(CTVMerge cTVMerge) {
        generatedSetterHelperImpl(cTVMerge, PROPERTY_QNAME[4], 0, 1);
    }

    public CTVMerge addNewVMerge() {
        CTVMerge cTVMerge;
        synchronized (monitor()) {
            check_orphaned();
            cTVMerge = (CTVMerge) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTVMerge;
    }

    public void unsetVMerge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTTcBorders getTcBorders() {
        CTTcBorders cTTcBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTcBorders = (CTTcBorders) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTTcBorders == null) {
                cTTcBorders = null;
            }
        }
        return cTTcBorders;
    }

    public boolean isSetTcBorders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setTcBorders(CTTcBorders cTTcBorders) {
        generatedSetterHelperImpl(cTTcBorders, PROPERTY_QNAME[5], 0, 1);
    }

    public CTTcBorders addNewTcBorders() {
        CTTcBorders cTTcBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTcBorders = (CTTcBorders) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTTcBorders;
    }

    public void unsetTcBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setShd(CTShd cTShd) {
        generatedSetterHelperImpl(cTShd, PROPERTY_QNAME[6], 0, 1);
    }

    public CTShd addNewShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTShd;
    }

    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTOnOff getNoWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetNoWrap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setNoWrap(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[7], 0, 1);
    }

    public CTOnOff addNewNoWrap() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTOnOff;
    }

    public void unsetNoWrap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTcMar getTcMar() {
        CTTcMar cTTcMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTcMar = (CTTcMar) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTcMar == null) {
                cTTcMar = null;
            }
        }
        return cTTcMar;
    }

    public boolean isSetTcMar() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setTcMar(CTTcMar cTTcMar) {
        generatedSetterHelperImpl(cTTcMar, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTcMar addNewTcMar() {
        CTTcMar cTTcMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTcMar = (CTTcMar) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTcMar;
    }

    public void unsetTcMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTTextDirection getTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTTextDirection == null) {
                cTTextDirection = null;
            }
        }
        return cTTextDirection;
    }

    public boolean isSetTextDirection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setTextDirection(CTTextDirection cTTextDirection) {
        generatedSetterHelperImpl(cTTextDirection, PROPERTY_QNAME[9], 0, 1);
    }

    public CTTextDirection addNewTextDirection() {
        CTTextDirection cTTextDirection;
        synchronized (monitor()) {
            check_orphaned();
            cTTextDirection = (CTTextDirection) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTextDirection;
    }

    public void unsetTextDirection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTOnOff getTcFitText() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetTcFitText() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTcFitText(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[10], 0, 1);
    }

    public CTOnOff addNewTcFitText() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTOnOff;
    }

    public void unsetTcFitText() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTVerticalJc getVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalJc = (CTVerticalJc) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTVerticalJc == null) {
                cTVerticalJc = null;
            }
        }
        return cTVerticalJc;
    }

    public boolean isSetVAlign() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setVAlign(CTVerticalJc cTVerticalJc) {
        generatedSetterHelperImpl(cTVerticalJc, PROPERTY_QNAME[11], 0, 1);
    }

    public CTVerticalJc addNewVAlign() {
        CTVerticalJc cTVerticalJc;
        synchronized (monitor()) {
            check_orphaned();
            cTVerticalJc = (CTVerticalJc) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTVerticalJc;
    }

    public void unsetVAlign() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTOnOff getHideMark() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTOnOff == null) {
                cTOnOff = null;
            }
        }
        return cTOnOff;
    }

    public boolean isSetHideMark() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setHideMark(CTOnOff cTOnOff) {
        generatedSetterHelperImpl(cTOnOff, PROPERTY_QNAME[12], 0, 1);
    }

    public CTOnOff addNewHideMark() {
        CTOnOff cTOnOff;
        synchronized (monitor()) {
            check_orphaned();
            cTOnOff = (CTOnOff) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTOnOff;
    }

    public void unsetHideMark() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTHeaders getHeaders() {
        CTHeaders find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetHeaders() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setHeaders(CTHeaders cTHeaders) {
        generatedSetterHelperImpl(cTHeaders, PROPERTY_QNAME[13], 0, 1);
    }

    public CTHeaders addNewHeaders() {
        CTHeaders add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return add_element_user;
    }

    public void unsetHeaders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }
}
