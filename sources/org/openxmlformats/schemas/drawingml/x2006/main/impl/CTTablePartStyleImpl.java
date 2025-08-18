package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleCellStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyleTextStyle;

public class CTTablePartStyleImpl extends XmlComplexContentImpl implements CTTablePartStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tcTxStyle"), new QName(XSSFRelation.NS_DRAWINGML, "tcStyle")};
    private static final long serialVersionUID = 1;

    public CTTablePartStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTableStyleTextStyle getTcTxStyle() {
        CTTableStyleTextStyle cTTableStyleTextStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleTextStyle = (CTTableStyleTextStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTableStyleTextStyle == null) {
                cTTableStyleTextStyle = null;
            }
        }
        return cTTableStyleTextStyle;
    }

    public boolean isSetTcTxStyle() {
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

    public void setTcTxStyle(CTTableStyleTextStyle cTTableStyleTextStyle) {
        generatedSetterHelperImpl(cTTableStyleTextStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTableStyleTextStyle addNewTcTxStyle() {
        CTTableStyleTextStyle cTTableStyleTextStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleTextStyle = (CTTableStyleTextStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTableStyleTextStyle;
    }

    public void unsetTcTxStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTableStyleCellStyle getTcStyle() {
        CTTableStyleCellStyle cTTableStyleCellStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleCellStyle = (CTTableStyleCellStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTableStyleCellStyle == null) {
                cTTableStyleCellStyle = null;
            }
        }
        return cTTableStyleCellStyle;
    }

    public boolean isSetTcStyle() {
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

    public void setTcStyle(CTTableStyleCellStyle cTTableStyleCellStyle) {
        generatedSetterHelperImpl(cTTableStyleCellStyle, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTableStyleCellStyle addNewTcStyle() {
        CTTableStyleCellStyle cTTableStyleCellStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTableStyleCellStyle = (CTTableStyleCellStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTableStyleCellStyle;
    }

    public void unsetTcStyle() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
