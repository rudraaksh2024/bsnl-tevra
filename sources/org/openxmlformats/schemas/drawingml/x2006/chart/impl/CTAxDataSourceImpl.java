package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAxDataSource;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMultiLvlStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrData;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;

public class CTAxDataSourceImpl extends XmlComplexContentImpl implements CTAxDataSource {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "multiLvlStrRef"), new QName(XSSFRelation.NS_CHART, "numRef"), new QName(XSSFRelation.NS_CHART, "numLit"), new QName(XSSFRelation.NS_CHART, "strRef"), new QName(XSSFRelation.NS_CHART, "strLit")};
    private static final long serialVersionUID = 1;

    public CTAxDataSourceImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMultiLvlStrRef getMultiLvlStrRef() {
        CTMultiLvlStrRef find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetMultiLvlStrRef() {
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

    public void setMultiLvlStrRef(CTMultiLvlStrRef cTMultiLvlStrRef) {
        generatedSetterHelperImpl(cTMultiLvlStrRef, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMultiLvlStrRef addNewMultiLvlStrRef() {
        CTMultiLvlStrRef add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetMultiLvlStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTNumRef getNumRef() {
        CTNumRef cTNumRef;
        synchronized (monitor()) {
            check_orphaned();
            cTNumRef = (CTNumRef) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTNumRef == null) {
                cTNumRef = null;
            }
        }
        return cTNumRef;
    }

    public boolean isSetNumRef() {
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

    public void setNumRef(CTNumRef cTNumRef) {
        generatedSetterHelperImpl(cTNumRef, PROPERTY_QNAME[1], 0, 1);
    }

    public CTNumRef addNewNumRef() {
        CTNumRef cTNumRef;
        synchronized (monitor()) {
            check_orphaned();
            cTNumRef = (CTNumRef) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTNumRef;
    }

    public void unsetNumRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTNumData getNumLit() {
        CTNumData cTNumData;
        synchronized (monitor()) {
            check_orphaned();
            cTNumData = (CTNumData) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTNumData == null) {
                cTNumData = null;
            }
        }
        return cTNumData;
    }

    public boolean isSetNumLit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setNumLit(CTNumData cTNumData) {
        generatedSetterHelperImpl(cTNumData, PROPERTY_QNAME[2], 0, 1);
    }

    public CTNumData addNewNumLit() {
        CTNumData cTNumData;
        synchronized (monitor()) {
            check_orphaned();
            cTNumData = (CTNumData) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTNumData;
    }

    public void unsetNumLit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTStrRef getStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTStrRef = (CTStrRef) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTStrRef == null) {
                cTStrRef = null;
            }
        }
        return cTStrRef;
    }

    public boolean isSetStrRef() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setStrRef(CTStrRef cTStrRef) {
        generatedSetterHelperImpl(cTStrRef, PROPERTY_QNAME[3], 0, 1);
    }

    public CTStrRef addNewStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTStrRef = (CTStrRef) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTStrRef;
    }

    public void unsetStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTStrData getStrLit() {
        CTStrData cTStrData;
        synchronized (monitor()) {
            check_orphaned();
            cTStrData = (CTStrData) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTStrData == null) {
                cTStrData = null;
            }
        }
        return cTStrData;
    }

    public boolean isSetStrLit() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setStrLit(CTStrData cTStrData) {
        generatedSetterHelperImpl(cTStrData, PROPERTY_QNAME[4], 0, 1);
    }

    public CTStrData addNewStrLit() {
        CTStrData cTStrData;
        synchronized (monitor()) {
            check_orphaned();
            cTStrData = (CTStrData) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTStrData;
    }

    public void unsetStrLit() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }
}
