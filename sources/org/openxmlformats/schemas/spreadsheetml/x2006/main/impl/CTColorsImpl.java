package org.openxmlformats.schemas.spreadsheetml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIndexedColors;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTMRUColors;

public class CTColorsImpl extends XmlComplexContentImpl implements CTColors {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_SPREADSHEETML, "indexedColors"), new QName(XSSFRelation.NS_SPREADSHEETML, "mruColors")};
    private static final long serialVersionUID = 1;

    public CTColorsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTIndexedColors getIndexedColors() {
        CTIndexedColors cTIndexedColors;
        synchronized (monitor()) {
            check_orphaned();
            cTIndexedColors = (CTIndexedColors) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTIndexedColors == null) {
                cTIndexedColors = null;
            }
        }
        return cTIndexedColors;
    }

    public boolean isSetIndexedColors() {
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

    public void setIndexedColors(CTIndexedColors cTIndexedColors) {
        generatedSetterHelperImpl(cTIndexedColors, PROPERTY_QNAME[0], 0, 1);
    }

    public CTIndexedColors addNewIndexedColors() {
        CTIndexedColors cTIndexedColors;
        synchronized (monitor()) {
            check_orphaned();
            cTIndexedColors = (CTIndexedColors) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTIndexedColors;
    }

    public void unsetIndexedColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTMRUColors getMruColors() {
        CTMRUColors find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetMruColors() {
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

    public void setMruColors(CTMRUColors cTMRUColors) {
        generatedSetterHelperImpl(cTMRUColors, PROPERTY_QNAME[1], 0, 1);
    }

    public CTMRUColors addNewMruColors() {
        CTMRUColors add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetMruColors() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
