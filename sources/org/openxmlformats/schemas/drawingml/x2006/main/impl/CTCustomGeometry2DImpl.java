package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAdjustHandleList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSiteList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuideList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomRect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2DList;

public class CTCustomGeometry2DImpl extends XmlComplexContentImpl implements CTCustomGeometry2D {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "avLst"), new QName(XSSFRelation.NS_DRAWINGML, "gdLst"), new QName(XSSFRelation.NS_DRAWINGML, "ahLst"), new QName(XSSFRelation.NS_DRAWINGML, "cxnLst"), new QName(XSSFRelation.NS_DRAWINGML, "rect"), new QName(XSSFRelation.NS_DRAWINGML, "pathLst")};
    private static final long serialVersionUID = 1;

    public CTCustomGeometry2DImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGeomGuideList getAvLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuideList = (CTGeomGuideList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGeomGuideList == null) {
                cTGeomGuideList = null;
            }
        }
        return cTGeomGuideList;
    }

    public boolean isSetAvLst() {
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

    public void setAvLst(CTGeomGuideList cTGeomGuideList) {
        generatedSetterHelperImpl(cTGeomGuideList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGeomGuideList addNewAvLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuideList = (CTGeomGuideList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGeomGuideList;
    }

    public void unsetAvLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTGeomGuideList getGdLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuideList = (CTGeomGuideList) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTGeomGuideList == null) {
                cTGeomGuideList = null;
            }
        }
        return cTGeomGuideList;
    }

    public boolean isSetGdLst() {
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

    public void setGdLst(CTGeomGuideList cTGeomGuideList) {
        generatedSetterHelperImpl(cTGeomGuideList, PROPERTY_QNAME[1], 0, 1);
    }

    public CTGeomGuideList addNewGdLst() {
        CTGeomGuideList cTGeomGuideList;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomGuideList = (CTGeomGuideList) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTGeomGuideList;
    }

    public void unsetGdLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTAdjustHandleList getAhLst() {
        CTAdjustHandleList cTAdjustHandleList;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjustHandleList = (CTAdjustHandleList) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTAdjustHandleList == null) {
                cTAdjustHandleList = null;
            }
        }
        return cTAdjustHandleList;
    }

    public boolean isSetAhLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setAhLst(CTAdjustHandleList cTAdjustHandleList) {
        generatedSetterHelperImpl(cTAdjustHandleList, PROPERTY_QNAME[2], 0, 1);
    }

    public CTAdjustHandleList addNewAhLst() {
        CTAdjustHandleList cTAdjustHandleList;
        synchronized (monitor()) {
            check_orphaned();
            cTAdjustHandleList = (CTAdjustHandleList) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTAdjustHandleList;
    }

    public void unsetAhLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTConnectionSiteList getCxnLst() {
        CTConnectionSiteList cTConnectionSiteList;
        synchronized (monitor()) {
            check_orphaned();
            cTConnectionSiteList = (CTConnectionSiteList) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTConnectionSiteList == null) {
                cTConnectionSiteList = null;
            }
        }
        return cTConnectionSiteList;
    }

    public boolean isSetCxnLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setCxnLst(CTConnectionSiteList cTConnectionSiteList) {
        generatedSetterHelperImpl(cTConnectionSiteList, PROPERTY_QNAME[3], 0, 1);
    }

    public CTConnectionSiteList addNewCxnLst() {
        CTConnectionSiteList cTConnectionSiteList;
        synchronized (monitor()) {
            check_orphaned();
            cTConnectionSiteList = (CTConnectionSiteList) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTConnectionSiteList;
    }

    public void unsetCxnLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTGeomRect getRect() {
        CTGeomRect cTGeomRect;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomRect = (CTGeomRect) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTGeomRect == null) {
                cTGeomRect = null;
            }
        }
        return cTGeomRect;
    }

    public boolean isSetRect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setRect(CTGeomRect cTGeomRect) {
        generatedSetterHelperImpl(cTGeomRect, PROPERTY_QNAME[4], 0, 1);
    }

    public CTGeomRect addNewRect() {
        CTGeomRect cTGeomRect;
        synchronized (monitor()) {
            check_orphaned();
            cTGeomRect = (CTGeomRect) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTGeomRect;
    }

    public void unsetRect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPath2DList getPathLst() {
        CTPath2DList cTPath2DList;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DList = (CTPath2DList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTPath2DList == null) {
                cTPath2DList = null;
            }
        }
        return cTPath2DList;
    }

    public void setPathLst(CTPath2DList cTPath2DList) {
        generatedSetterHelperImpl(cTPath2DList, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPath2DList addNewPathLst() {
        CTPath2DList cTPath2DList;
        synchronized (monitor()) {
            check_orphaned();
            cTPath2DList = (CTPath2DList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTPath2DList;
    }
}
