package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTGradientFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTPatternFill;

public class CTFillImpl extends XmlComplexContentImpl implements CTFill {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "patternFill"), new QName(XSSFRelation.NS_SPREADSHEETML, "gradientFill")};
    private static final long serialVersionUID = 1;

    public CTFillImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTPatternFill getPatternFill() {
        CTPatternFill cTPatternFill;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFill = (CTPatternFill) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTPatternFill == null) {
                cTPatternFill = null;
            }
        }
        return cTPatternFill;
    }

    public boolean isSetPatternFill() {
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

    public void setPatternFill(CTPatternFill cTPatternFill) {
        generatedSetterHelperImpl(cTPatternFill, PROPERTY_QNAME[0], 0, 1);
    }

    public CTPatternFill addNewPatternFill() {
        CTPatternFill cTPatternFill;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFill = (CTPatternFill) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTPatternFill;
    }

    public void unsetPatternFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTGradientFill getGradientFill() {
        CTGradientFill find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetGradientFill() {
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

    public void setGradientFill(CTGradientFill cTGradientFill) {
        generatedSetterHelperImpl(cTGradientFill, PROPERTY_QNAME[1], 0, 1);
    }

    public CTGradientFill addNewGradientFill() {
        CTGradientFill add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetGradientFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
