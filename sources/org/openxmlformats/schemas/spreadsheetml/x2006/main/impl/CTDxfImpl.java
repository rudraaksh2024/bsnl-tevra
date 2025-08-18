package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellAlignment;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellProtection;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTExtensionList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;

public class CTDxfImpl extends XmlComplexContentImpl implements CTDxf {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, CellUtil.FONT), new QName(XSSFRelation.NS_SPREADSHEETML, "numFmt"), new QName(XSSFRelation.NS_SPREADSHEETML, "fill"), new QName(XSSFRelation.NS_SPREADSHEETML, CellUtil.ALIGNMENT), new QName(XSSFRelation.NS_SPREADSHEETML, "border"), new QName(XSSFRelation.NS_SPREADSHEETML, "protection"), new QName(XSSFRelation.NS_SPREADSHEETML, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDxfImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTFont getFont() {
        CTFont cTFont;
        synchronized (monitor()) {
            check_orphaned();
            cTFont = (CTFont) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTFont == null) {
                cTFont = null;
            }
        }
        return cTFont;
    }

    public boolean isSetFont() {
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

    public void setFont(CTFont cTFont) {
        generatedSetterHelperImpl(cTFont, PROPERTY_QNAME[0], 0, 1);
    }

    public CTFont addNewFont() {
        CTFont cTFont;
        synchronized (monitor()) {
            check_orphaned();
            cTFont = (CTFont) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTFont;
    }

    public void unsetFont() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNumFmt == null) {
                cTNumFmt = null;
            }
        }
        return cTNumFmt;
    }

    public boolean isSetNumFmt() {
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

    public void setNumFmt(CTNumFmt cTNumFmt) {
        generatedSetterHelperImpl(cTNumFmt, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNumFmt addNewNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNumFmt;
    }

    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTFill getFill() {
        CTFill cTFill;
        synchronized (monitor()) {
            check_orphaned();
            cTFill = (CTFill) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTFill == null) {
                cTFill = null;
            }
        }
        return cTFill;
    }

    public boolean isSetFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setFill(CTFill cTFill) {
        generatedSetterHelperImpl(cTFill, PROPERTY_QNAME[2], 0, 1);
    }

    public CTFill addNewFill() {
        CTFill cTFill;
        synchronized (monitor()) {
            check_orphaned();
            cTFill = (CTFill) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTFill;
    }

    public void unsetFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTCellAlignment getAlignment() {
        CTCellAlignment cTCellAlignment;
        synchronized (monitor()) {
            check_orphaned();
            cTCellAlignment = (CTCellAlignment) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTCellAlignment == null) {
                cTCellAlignment = null;
            }
        }
        return cTCellAlignment;
    }

    public boolean isSetAlignment() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setAlignment(CTCellAlignment cTCellAlignment) {
        generatedSetterHelperImpl(cTCellAlignment, PROPERTY_QNAME[3], 0, 1);
    }

    public CTCellAlignment addNewAlignment() {
        CTCellAlignment cTCellAlignment;
        synchronized (monitor()) {
            check_orphaned();
            cTCellAlignment = (CTCellAlignment) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTCellAlignment;
    }

    public void unsetAlignment() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTBorder getBorder() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            cTBorder = (CTBorder) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTBorder == null) {
                cTBorder = null;
            }
        }
        return cTBorder;
    }

    public boolean isSetBorder() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setBorder(CTBorder cTBorder) {
        generatedSetterHelperImpl(cTBorder, PROPERTY_QNAME[4], 0, 1);
    }

    public CTBorder addNewBorder() {
        CTBorder cTBorder;
        synchronized (monitor()) {
            check_orphaned();
            cTBorder = (CTBorder) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTBorder;
    }

    public void unsetBorder() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTCellProtection getProtection() {
        CTCellProtection cTCellProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTCellProtection = (CTCellProtection) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTCellProtection == null) {
                cTCellProtection = null;
            }
        }
        return cTCellProtection;
    }

    public boolean isSetProtection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setProtection(CTCellProtection cTCellProtection) {
        generatedSetterHelperImpl(cTCellProtection, PROPERTY_QNAME[5], 0, 1);
    }

    public CTCellProtection addNewProtection() {
        CTCellProtection cTCellProtection;
        synchronized (monitor()) {
            check_orphaned();
            cTCellProtection = (CTCellProtection) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTCellProtection;
    }

    public void unsetProtection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTExtensionList == null) {
                cTExtensionList = null;
            }
        }
        return cTExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[6], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }
}
