package org.openxmlformats.schemas.drawingml.x2006.chart.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTChartLines;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbl;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLblPos;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTNumFmt;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;

public class CTDLblsImpl extends XmlComplexContentImpl implements CTDLbls {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_CHART, "dLbl"), new QName(XSSFRelation.NS_CHART, "delete"), new QName(XSSFRelation.NS_CHART, "numFmt"), new QName(XSSFRelation.NS_CHART, "spPr"), new QName(XSSFRelation.NS_CHART, "txPr"), new QName(XSSFRelation.NS_CHART, "dLblPos"), new QName(XSSFRelation.NS_CHART, "showLegendKey"), new QName(XSSFRelation.NS_CHART, "showVal"), new QName(XSSFRelation.NS_CHART, "showCatName"), new QName(XSSFRelation.NS_CHART, "showSerName"), new QName(XSSFRelation.NS_CHART, "showPercent"), new QName(XSSFRelation.NS_CHART, "showBubbleSize"), new QName(XSSFRelation.NS_CHART, "separator"), new QName(XSSFRelation.NS_CHART, "showLeaderLines"), new QName(XSSFRelation.NS_CHART, "leaderLines"), new QName(XSSFRelation.NS_CHART, "extLst")};
    private static final long serialVersionUID = 1;

    public CTDLblsImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTDLbl> getDLblList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTDLblsImpl$$ExternalSyntheticLambda0(this), new CTDLblsImpl$$ExternalSyntheticLambda1(this), new CTDLblsImpl$$ExternalSyntheticLambda2(this), new CTDLblsImpl$$ExternalSyntheticLambda3(this), new CTDLblsImpl$$ExternalSyntheticLambda4(this));
        }
        return javaListXmlObject;
    }

    public CTDLbl[] getDLblArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTDLbl[0]);
    }

    public CTDLbl getDLblArray(int i) {
        CTDLbl find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfDLblArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setDLblArray(CTDLbl[] cTDLblArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDLblArr, PROPERTY_QNAME[0]);
    }

    public void setDLblArray(int i, CTDLbl cTDLbl) {
        generatedSetterHelperImpl(cTDLbl, PROPERTY_QNAME[0], i, 2);
    }

    public CTDLbl insertNewDLbl(int i) {
        CTDLbl insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CTDLbl addNewDLbl() {
        CTDLbl add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeDLbl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public CTBoolean getDelete() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetDelete() {
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

    public void setDelete(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[1], 0, 1);
    }

    public CTBoolean addNewDelete() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTBoolean;
    }

    public void unsetDelete() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTNumFmt getNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTNumFmt == null) {
                cTNumFmt = null;
            }
        }
        return cTNumFmt;
    }

    public boolean isSetNumFmt() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setNumFmt(CTNumFmt cTNumFmt) {
        generatedSetterHelperImpl(cTNumFmt, PROPERTY_QNAME[2], 0, 1);
    }

    public CTNumFmt addNewNumFmt() {
        CTNumFmt cTNumFmt;
        synchronized (monitor()) {
            check_orphaned();
            cTNumFmt = (CTNumFmt) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTNumFmt;
    }

    public void unsetNumFmt() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTShapeProperties getSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setSpPr(CTShapeProperties cTShapeProperties) {
        generatedSetterHelperImpl(cTShapeProperties, PROPERTY_QNAME[3], 0, 1);
    }

    public CTShapeProperties addNewSpPr() {
        CTShapeProperties cTShapeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTShapeProperties = (CTShapeProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTShapeProperties;
    }

    public void unsetSpPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTTextBody getTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setTxPr(CTTextBody cTTextBody) {
        generatedSetterHelperImpl(cTTextBody, PROPERTY_QNAME[4], 0, 1);
    }

    public CTTextBody addNewTxPr() {
        CTTextBody cTTextBody;
        synchronized (monitor()) {
            check_orphaned();
            cTTextBody = (CTTextBody) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTextBody;
    }

    public void unsetTxPr() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTDLblPos getDLblPos() {
        CTDLblPos find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetDLblPos() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setDLblPos(CTDLblPos cTDLblPos) {
        generatedSetterHelperImpl(cTDLblPos, PROPERTY_QNAME[5], 0, 1);
    }

    public CTDLblPos addNewDLblPos() {
        CTDLblPos add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetDLblPos() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTBoolean getShowLegendKey() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowLegendKey() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setShowLegendKey(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[6], 0, 1);
    }

    public CTBoolean addNewShowLegendKey() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTBoolean;
    }

    public void unsetShowLegendKey() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTBoolean getShowVal() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowVal() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setShowVal(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[7], 0, 1);
    }

    public CTBoolean addNewShowVal() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTBoolean;
    }

    public void unsetShowVal() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTBoolean getShowCatName() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowCatName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setShowCatName(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[8], 0, 1);
    }

    public CTBoolean addNewShowCatName() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTBoolean;
    }

    public void unsetShowCatName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTBoolean getShowSerName() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowSerName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setShowSerName(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[9], 0, 1);
    }

    public CTBoolean addNewShowSerName() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTBoolean;
    }

    public void unsetShowSerName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTBoolean getShowPercent() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowPercent() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setShowPercent(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[10], 0, 1);
    }

    public CTBoolean addNewShowPercent() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTBoolean;
    }

    public void unsetShowPercent() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTBoolean getShowBubbleSize() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowBubbleSize() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setShowBubbleSize(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[11], 0, 1);
    }

    public CTBoolean addNewShowBubbleSize() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTBoolean;
    }

    public void unsetShowBubbleSize() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public String getSeparator() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetSeparator() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_element_user(PROPERTY_QNAME[12], 0);
        }
        return xmlString;
    }

    public boolean isSetSeparator() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setSeparator(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 12
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.setStringValue(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTDLblsImpl.setSeparator(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetSeparator(org.apache.xmlbeans.XmlString r7) {
        /*
            r6 = this;
            java.lang.Object r0 = r6.monitor()
            monitor-enter(r0)
            r6.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r6.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 12
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            r5 = 0
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_element_user((javax.xml.namespace.QName) r4, (int) r5)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0028
            org.apache.xmlbeans.impl.values.TypeStore r6 = r6.get_store()     // Catch:{ all -> 0x002d }
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r6 = r6.add_element_user(r1)     // Catch:{ all -> 0x002d }
            r1 = r6
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002d }
        L_0x0028:
            r1.set(r7)     // Catch:{ all -> 0x002d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return
        L_0x002d:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTDLblsImpl.xsetSeparator(org.apache.xmlbeans.XmlString):void");
    }

    public void unsetSeparator() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTBoolean getShowLeaderLines() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTBoolean == null) {
                cTBoolean = null;
            }
        }
        return cTBoolean;
    }

    public boolean isSetShowLeaderLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setShowLeaderLines(CTBoolean cTBoolean) {
        generatedSetterHelperImpl(cTBoolean, PROPERTY_QNAME[13], 0, 1);
    }

    public CTBoolean addNewShowLeaderLines() {
        CTBoolean cTBoolean;
        synchronized (monitor()) {
            check_orphaned();
            cTBoolean = (CTBoolean) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTBoolean;
    }

    public void unsetShowLeaderLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTChartLines getLeaderLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().find_element_user(PROPERTY_QNAME[14], 0);
            if (cTChartLines == null) {
                cTChartLines = null;
            }
        }
        return cTChartLines;
    }

    public boolean isSetLeaderLines() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setLeaderLines(CTChartLines cTChartLines) {
        generatedSetterHelperImpl(cTChartLines, PROPERTY_QNAME[14], 0, 1);
    }

    public CTChartLines addNewLeaderLines() {
        CTChartLines cTChartLines;
        synchronized (monitor()) {
            check_orphaned();
            cTChartLines = (CTChartLines) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTChartLines;
    }

    public void unsetLeaderLines() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public CTExtensionList getExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().find_element_user(PROPERTY_QNAME[15], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[15]) != 0;
        }
        return z;
    }

    public void setExtLst(CTExtensionList cTExtensionList) {
        generatedSetterHelperImpl(cTExtensionList, PROPERTY_QNAME[15], 0, 1);
    }

    public CTExtensionList addNewExtLst() {
        CTExtensionList cTExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTExtensionList = (CTExtensionList) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], 0);
        }
    }
}
