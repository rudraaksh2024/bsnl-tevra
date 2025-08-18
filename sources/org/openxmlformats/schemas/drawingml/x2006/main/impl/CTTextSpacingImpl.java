package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacing;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPercent;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextSpacingPoint;

public class CTTextSpacingImpl extends XmlComplexContentImpl implements CTTextSpacing {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "spcPct"), new QName(XSSFRelation.NS_DRAWINGML, "spcPts")};
    private static final long serialVersionUID = 1;

    public CTTextSpacingImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTextSpacingPercent getSpcPct() {
        CTTextSpacingPercent cTTextSpacingPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTTextSpacingPercent = (CTTextSpacingPercent) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTextSpacingPercent == null) {
                cTTextSpacingPercent = null;
            }
        }
        return cTTextSpacingPercent;
    }

    public boolean isSetSpcPct() {
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

    public void setSpcPct(CTTextSpacingPercent cTTextSpacingPercent) {
        generatedSetterHelperImpl(cTTextSpacingPercent, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTextSpacingPercent addNewSpcPct() {
        CTTextSpacingPercent cTTextSpacingPercent;
        synchronized (monitor()) {
            check_orphaned();
            cTTextSpacingPercent = (CTTextSpacingPercent) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTextSpacingPercent;
    }

    public void unsetSpcPct() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTextSpacingPoint getSpcPts() {
        CTTextSpacingPoint cTTextSpacingPoint;
        synchronized (monitor()) {
            check_orphaned();
            cTTextSpacingPoint = (CTTextSpacingPoint) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTextSpacingPoint == null) {
                cTTextSpacingPoint = null;
            }
        }
        return cTTextSpacingPoint;
    }

    public boolean isSetSpcPts() {
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

    public void setSpcPts(CTTextSpacingPoint cTTextSpacingPoint) {
        generatedSetterHelperImpl(cTTextSpacingPoint, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTextSpacingPoint addNewSpcPts() {
        CTTextSpacingPoint cTTextSpacingPoint;
        synchronized (monitor()) {
            check_orphaned();
            cTTextSpacingPoint = (CTTextSpacingPoint) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTextSpacingPoint;
    }

    public void unsetSpcPts() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }
}
