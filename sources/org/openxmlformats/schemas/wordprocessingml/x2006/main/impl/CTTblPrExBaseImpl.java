package org.openxmlformats.schemas.wordprocessingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJcTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblCellMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLayoutType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblLook;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPrExBase;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;

public class CTTblPrExBaseImpl extends XmlComplexContentImpl implements CTTblPrExBase {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblW"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "jc"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellSpacing"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblInd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblBorders"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "shd"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLayout"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblCellMar"), new QName("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "tblLook")};
    private static final long serialVersionUID = 1;

    public CTTblPrExBaseImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTblWidth getTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[0], 0);
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
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setTblW(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTblWidth addNewTblW() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTblWidth;
    }

    public void unsetTblW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTJcTable getJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().find_element_user(PROPERTY_QNAME[1], 0);
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
            z = true;
            if (get_store().count_elements(PROPERTY_QNAME[1]) == 0) {
                z = false;
            }
        }
        return z;
    }

    public void setJc(CTJcTable cTJcTable) {
        generatedSetterHelperImpl(cTJcTable, PROPERTY_QNAME[1], 0, 1);
    }

    public CTJcTable addNewJc() {
        CTJcTable cTJcTable;
        synchronized (monitor()) {
            check_orphaned();
            cTJcTable = (CTJcTable) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTJcTable;
    }

    public void unsetJc() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTblWidth getTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[2], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setTblCellSpacing(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTblWidth addNewTblCellSpacing() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTblWidth;
    }

    public void unsetTblCellSpacing() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTTblWidth getTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setTblInd(CTTblWidth cTTblWidth) {
        generatedSetterHelperImpl(cTTblWidth, PROPERTY_QNAME[3], 0, 1);
    }

    public CTTblWidth addNewTblInd() {
        CTTblWidth cTTblWidth;
        synchronized (monitor()) {
            check_orphaned();
            cTTblWidth = (CTTblWidth) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTTblWidth;
    }

    public void unsetTblInd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTTblBorders getTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTblBorders = (CTTblBorders) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setTblBorders(CTTblBorders cTTblBorders) {
        generatedSetterHelperImpl(cTTblBorders, PROPERTY_QNAME[4], 0, 1);
    }

    public CTTblBorders addNewTblBorders() {
        CTTblBorders cTTblBorders;
        synchronized (monitor()) {
            check_orphaned();
            cTTblBorders = (CTTblBorders) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTblBorders;
    }

    public void unsetTblBorders() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTShd getShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().find_element_user(PROPERTY_QNAME[5], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setShd(CTShd cTShd) {
        generatedSetterHelperImpl(cTShd, PROPERTY_QNAME[5], 0, 1);
    }

    public CTShd addNewShd() {
        CTShd cTShd;
        synchronized (monitor()) {
            check_orphaned();
            cTShd = (CTShd) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTShd;
    }

    public void unsetShd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTTblLayoutType getTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLayoutType = (CTTblLayoutType) get_store().find_element_user(PROPERTY_QNAME[6], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setTblLayout(CTTblLayoutType cTTblLayoutType) {
        generatedSetterHelperImpl(cTTblLayoutType, PROPERTY_QNAME[6], 0, 1);
    }

    public CTTblLayoutType addNewTblLayout() {
        CTTblLayoutType cTTblLayoutType;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLayoutType = (CTTblLayoutType) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTTblLayoutType;
    }

    public void unsetTblLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTTblCellMar getTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTblCellMar = (CTTblCellMar) get_store().find_element_user(PROPERTY_QNAME[7], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setTblCellMar(CTTblCellMar cTTblCellMar) {
        generatedSetterHelperImpl(cTTblCellMar, PROPERTY_QNAME[7], 0, 1);
    }

    public CTTblCellMar addNewTblCellMar() {
        CTTblCellMar cTTblCellMar;
        synchronized (monitor()) {
            check_orphaned();
            cTTblCellMar = (CTTblCellMar) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTTblCellMar;
    }

    public void unsetTblCellMar() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTblLook getTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLook = (CTTblLook) get_store().find_element_user(PROPERTY_QNAME[8], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setTblLook(CTTblLook cTTblLook) {
        generatedSetterHelperImpl(cTTblLook, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTblLook addNewTblLook() {
        CTTblLook cTTblLook;
        synchronized (monitor()) {
            check_orphaned();
            cTTblLook = (CTTblLook) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTblLook;
    }

    public void unsetTblLook() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
