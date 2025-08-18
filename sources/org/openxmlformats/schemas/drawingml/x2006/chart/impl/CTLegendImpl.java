package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegend;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendPos;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTLegendImpl extends XmlComplexContentImpl implements CTLegend {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "legendPos"), new QName(XSSFRelation.NS_CHART, "legendEntry"), new QName(XSSFRelation.NS_CHART, "layout"), new QName(XSSFRelation.NS_CHART, "overlay"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "txPr"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTLegendImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTLegendPos getLegendPos() {
        CTLegendPos cTLegendPos;
        synchronized (monitor()) {
            check_orphaned();
            cTLegendPos = (CTLegendPos) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTLegendPos == null) {
                cTLegendPos = null;
            }
        }
        return cTLegendPos;
    }

    public boolean isSetLegendPos() {
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

    public void setLegendPos(CTLegendPos cTLegendPos) {
        generatedSetterHelperImpl(cTLegendPos, PROPERTY_QNAME[0], 0, 1);
    }

    public CTLegendPos addNewLegendPos() {
        CTLegendPos cTLegendPos;
        synchronized (monitor()) {
            check_orphaned();
            cTLegendPos = (CTLegendPos) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTLegendPos;
    }

    public void unsetLegendPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public List<CTLegendEntry> getLegendEntryList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTLegendImpl$$ExternalSyntheticLambda0(this), new CTLegendImpl$$ExternalSyntheticLambda1(this), new CTLegendImpl$$ExternalSyntheticLambda2(this), new CTLegendImpl$$ExternalSyntheticLambda3(this), new CTLegendImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTLegendEntry[] getLegendEntryArray() {
        return (CTLegendEntry[]) getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTLegendEntry[0]);
    }

    public CTLegendEntry getLegendEntryArray(int i) {
        CTLegendEntry cTLegendEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTLegendEntry = (CTLegendEntry) get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (cTLegendEntry == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTLegendEntry;
    }

    public int sizeOfLegendEntryArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setLegendEntryArray(CTLegendEntry[] cTLegendEntryArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLegendEntryArr, PROPERTY_QNAME[1]);
    }

    public void setLegendEntryArray(int i, CTLegendEntry cTLegendEntry) {
        generatedSetterHelperImpl(cTLegendEntry, PROPERTY_QNAME[1], i, 2);
    }

    public CTLegendEntry insertNewLegendEntry(int i) {
        CTLegendEntry cTLegendEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTLegendEntry = (CTLegendEntry) get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return cTLegendEntry;
    }

    public CTLegendEntry addNewLegendEntry() {
        CTLegendEntry cTLegendEntry;
        synchronized (monitor()) {
            check_orphaned();
            cTLegendEntry = (CTLegendEntry) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTLegendEntry;
    }

    public void removeLegendEntry(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public CTLayout getLayout() {
        CTLayout cTLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTLayout = (CTLayout) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTLayout == null) {
                cTLayout = null;
            }
        }
        return cTLayout;
    }

    public boolean isSetLayout() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setLayout(CTLayout cTLayout) {
        generatedSetterHelperImpl(cTLayout, PROPERTY_QNAME[2], 0, 1);
    }

    public CTLayout addNewLayout() {
        CTLayout cTLayout;
        synchronized (monitor()) {
            check_orphaned();
            cTLayout = (CTLayout) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTLayout;
    }

    public void unsetLayout() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTBoolean getOverlay() {
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

    public boolean isSetOverlay() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setOverlay(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[3], 0, 1);
    }

    public CTBoolean addNewOverlay() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTBoolean;
    }

    public void unsetOverlay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[4], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTTextBody getTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTTextBody == null) {
                cTTextBody = null;
            }
        }
        return cTTextBody;
    }

    public boolean isSetTxPr() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setTxPr(CTTextBody cTTextBody) {
        generatedSetterHelperImpl(cTTextBody, PROPERTY_QNAME[5], 0, 1);
    }

    public CTTextBody addNewTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTTextBody;
    }

    public void unsetTxPr() {
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
