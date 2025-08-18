package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTStrRef;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTTx;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTTxImpl extends XmlComplexContentImpl implements CTTx {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "strRef"), new QName(XSSFRelation.NS_CHART, "rich")};
    private static final long serialVersionUID = 1;

    public CTTxImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTStrRef getStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTStrRef = (CTStrRef) get_store().find_element_user(PROPERTY_QNAME[0], 0);
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
            z = false;
            if (get_store().count_elements(PROPERTY_QNAME[0]) != 0) {
                z = true;
            }
        }
        return z;
    }

    public void setStrRef(CTStrRef cTStrRef) {
        generatedSetterHelperImpl(cTStrRef, PROPERTY_QNAME[0], 0, 1);
    }

    public CTStrRef addNewStrRef() {
        CTStrRef cTStrRef;
        synchronized (monitor()) {
            check_orphaned();
            cTStrRef = (CTStrRef) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTStrRef;
    }

    public void unsetStrRef() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTextBody getRich() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTextBody == null) {
                cTTextBody = null;
            }
        }
        return cTTextBody;
    }

    public boolean isSetRich() {
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

    public void setRich(CTTextBody cTTextBody) {
        generatedSetterHelperImpl(cTTextBody, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTextBody addNewRich() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTextBody;
    }

    public void unsetRich() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
