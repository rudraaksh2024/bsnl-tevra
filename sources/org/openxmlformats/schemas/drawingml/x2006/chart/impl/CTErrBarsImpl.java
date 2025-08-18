package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDouble;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBarType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrBars;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrDir;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTErrValType;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumDataSource;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public class CTErrBarsImpl extends XmlComplexContentImpl implements CTErrBars {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "errDir"), new QName(XSSFRelation.NS_CHART, "errBarType"), new QName(XSSFRelation.NS_CHART, "errValType"), new QName(XSSFRelation.NS_CHART, "noEndCap"), new QName(XSSFRelation.NS_CHART, "plus"), new QName(XSSFRelation.NS_CHART, "minus"), new QName(XSSFRelation.NS_CHART, "val"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTErrBarsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTErrDir getErrDir() {
        CTErrDir cTErrDir;
        synchronized (monitor()) {
            check_orphaned();
            cTErrDir = (CTErrDir) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTErrDir == null) {
                cTErrDir = null;
            }
        }
        return cTErrDir;
    }

    public boolean isSetErrDir() {
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

    public void setErrDir(CTErrDir cTErrDir) {
        generatedSetterHelperImpl(cTErrDir, PROPERTY_QNAME[0], 0, 1);
    }

    public CTErrDir addNewErrDir() {
        CTErrDir cTErrDir;
        synchronized (monitor()) {
            check_orphaned();
            cTErrDir = (CTErrDir) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTErrDir;
    }

    public void unsetErrDir() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTErrBarType getErrBarType() {
        CTErrBarType cTErrBarType;
        synchronized (monitor()) {
            check_orphaned();
            cTErrBarType = (CTErrBarType) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTErrBarType == null) {
                cTErrBarType = null;
            }
        }
        return cTErrBarType;
    }

    public void setErrBarType(CTErrBarType cTErrBarType) {
        generatedSetterHelperImpl(cTErrBarType, PROPERTY_QNAME[1], 0, 1);
    }

    public CTErrBarType addNewErrBarType() {
        CTErrBarType cTErrBarType;
        synchronized (monitor()) {
            check_orphaned();
            cTErrBarType = (CTErrBarType) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTErrBarType;
    }

    public CTErrValType getErrValType() {
        CTErrValType cTErrValType;
        synchronized (monitor()) {
            check_orphaned();
            cTErrValType = (CTErrValType) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTErrValType == null) {
                cTErrValType = null;
            }
        }
        return cTErrValType;
    }

    public void setErrValType(CTErrValType cTErrValType) {
        generatedSetterHelperImpl(cTErrValType, PROPERTY_QNAME[2], 0, 1);
    }

    public CTErrValType addNewErrValType() {
        CTErrValType cTErrValType;
        synchronized (monitor()) {
            check_orphaned();
            cTErrValType = (CTErrValType) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTErrValType;
    }

    public CTBoolean getNoEndCap() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetNoEndCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setNoEndCap(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[3], 0, 1);
    }

    public CTBoolean addNewNoEndCap() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBoolean;
    }

    public void unsetNoEndCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTNumDataSource getPlus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTNumDataSource == null) {
                cTNumDataSource = null;
            }
        }
        return cTNumDataSource;
    }

    public boolean isSetPlus() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setPlus(CTNumDataSource cTNumDataSource) {
        generatedSetterHelperImpl(cTNumDataSource, PROPERTY_QNAME[4], 0, 1);
    }

    public CTNumDataSource addNewPlus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTNumDataSource;
    }

    public void unsetPlus() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTNumDataSource getMinus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTNumDataSource == null) {
                cTNumDataSource = null;
            }
        }
        return cTNumDataSource;
    }

    public boolean isSetMinus() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setMinus(CTNumDataSource cTNumDataSource) {
        generatedSetterHelperImpl(cTNumDataSource, PROPERTY_QNAME[5], 0, 1);
    }

    public CTNumDataSource addNewMinus() {
        CTNumDataSource cTNumDataSource;
        synchronized (monitor()) {
            check_orphaned();
            cTNumDataSource = (CTNumDataSource) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTNumDataSource;
    }

    public void unsetMinus() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTDouble getVal() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTDouble == null) {
                cTDouble = null;
            }
        }
        return cTDouble;
    }

    public boolean isSetVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setVal(CTDouble cTDouble) {
        generatedSetterHelperImpl(cTDouble, PROPERTY_QNAME[6], 0, 1);
    }

    public CTDouble addNewVal() {
        CTDouble cTDouble;
        synchronized (monitor()) {
            check_orphaned();
            cTDouble = (CTDouble) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTDouble;
    }

    public void unsetVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[7], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[8], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[8], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }
}
