package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarker;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerSize;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTMarkerStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTMarkerImpl extends XmlComplexContentImpl implements CTMarker {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "symbol"), new QName(XSSFRelation.NS_CHART, "size"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTMarkerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTMarkerStyle getSymbol() {
        CTMarkerStyle cTMarkerStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkerStyle = (CTMarkerStyle) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTMarkerStyle == null) {
                cTMarkerStyle = null;
            }
        }
        return cTMarkerStyle;
    }

    public boolean isSetSymbol() {
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

    public void setSymbol(CTMarkerStyle cTMarkerStyle) {
        generatedSetterHelperImpl(cTMarkerStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTMarkerStyle addNewSymbol() {
        CTMarkerStyle cTMarkerStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkerStyle = (CTMarkerStyle) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTMarkerStyle;
    }

    public void unsetSymbol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTMarkerSize getSize() {
        CTMarkerSize cTMarkerSize;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkerSize = (CTMarkerSize) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTMarkerSize == null) {
                cTMarkerSize = null;
            }
        }
        return cTMarkerSize;
    }

    public boolean isSetSize() {
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

    public void setSize(CTMarkerSize cTMarkerSize) {
        generatedSetterHelperImpl(cTMarkerSize, PROPERTY_QNAME[1], 0, 1);
    }

    public CTMarkerSize addNewSize() {
        CTMarkerSize cTMarkerSize;
        synchronized (monitor()) {
            check_orphaned();
            cTMarkerSize = (CTMarkerSize) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTMarkerSize;
    }

    public void unsetSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTShapeProperties == null) {
                cTShapeProperties = null;
            }
        }
        return cTShapeProperties;
    }

    public boolean isSetSpPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[2], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[3], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }
}
