package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineEndProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinRound;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetLineDashProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineCap;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth;
import org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment;

public class CTLinePropertiesImpl extends XmlComplexContentImpl implements CTLineProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "prstDash"), new QName(XSSFRelation.NS_DRAWINGML, "custDash"), new QName(XSSFRelation.NS_DRAWINGML, "round"), new QName(XSSFRelation.NS_DRAWINGML, "bevel"), new QName(XSSFRelation.NS_DRAWINGML, "miter"), new QName(XSSFRelation.NS_DRAWINGML, "headEnd"), new QName(XSSFRelation.NS_DRAWINGML, "tailEnd"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "w"), new QName("", "cap"), new QName("", "cmpd"), new QName("", "algn")};
    private static final long serialVersionUID = 1;

    public CTLinePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTNoFillProperties == null) {
                cTNoFillProperties = null;
            }
        }
        return cTNoFillProperties;
    }

    public boolean isSetNoFill() {
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

    public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        generatedSetterHelperImpl(cTNoFillProperties, PROPERTY_QNAME[0], 0, 1);
    }

    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTNoFillProperties;
    }

    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTSolidColorFillProperties == null) {
                cTSolidColorFillProperties = null;
            }
        }
        return cTSolidColorFillProperties;
    }

    public boolean isSetSolidFill() {
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

    public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        generatedSetterHelperImpl(cTSolidColorFillProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTSolidColorFillProperties;
    }

    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTGradientFillProperties == null) {
                cTGradientFillProperties = null;
            }
        }
        return cTGradientFillProperties;
    }

    public boolean isSetGradFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        generatedSetterHelperImpl(cTGradientFillProperties, PROPERTY_QNAME[2], 0, 1);
    }

    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTGradientFillProperties;
    }

    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTPatternFillProperties == null) {
                cTPatternFillProperties = null;
            }
        }
        return cTPatternFillProperties;
    }

    public boolean isSetPattFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        generatedSetterHelperImpl(cTPatternFillProperties, PROPERTY_QNAME[3], 0, 1);
    }

    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTPatternFillProperties;
    }

    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTPresetLineDashProperties getPrstDash() {
        CTPresetLineDashProperties cTPresetLineDashProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetLineDashProperties = (CTPresetLineDashProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTPresetLineDashProperties == null) {
                cTPresetLineDashProperties = null;
            }
        }
        return cTPresetLineDashProperties;
    }

    public boolean isSetPrstDash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setPrstDash(CTPresetLineDashProperties cTPresetLineDashProperties) {
        generatedSetterHelperImpl(cTPresetLineDashProperties, PROPERTY_QNAME[4], 0, 1);
    }

    public CTPresetLineDashProperties addNewPrstDash() {
        CTPresetLineDashProperties cTPresetLineDashProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetLineDashProperties = (CTPresetLineDashProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTPresetLineDashProperties;
    }

    public void unsetPrstDash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTDashStopList getCustDash() {
        CTDashStopList cTDashStopList;
        synchronized (monitor()) {
            check_orphaned();
            cTDashStopList = (CTDashStopList) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTDashStopList == null) {
                cTDashStopList = null;
            }
        }
        return cTDashStopList;
    }

    public boolean isSetCustDash() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setCustDash(CTDashStopList cTDashStopList) {
        generatedSetterHelperImpl(cTDashStopList, PROPERTY_QNAME[5], 0, 1);
    }

    public CTDashStopList addNewCustDash() {
        CTDashStopList cTDashStopList;
        synchronized (monitor()) {
            check_orphaned();
            cTDashStopList = (CTDashStopList) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTDashStopList;
    }

    public void unsetCustDash() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTLineJoinRound getRound() {
        CTLineJoinRound cTLineJoinRound;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinRound = (CTLineJoinRound) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTLineJoinRound == null) {
                cTLineJoinRound = null;
            }
        }
        return cTLineJoinRound;
    }

    public boolean isSetRound() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setRound(CTLineJoinRound cTLineJoinRound) {
        generatedSetterHelperImpl(cTLineJoinRound, PROPERTY_QNAME[6], 0, 1);
    }

    public CTLineJoinRound addNewRound() {
        CTLineJoinRound cTLineJoinRound;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinRound = (CTLineJoinRound) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTLineJoinRound;
    }

    public void unsetRound() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTLineJoinBevel getBevel() {
        CTLineJoinBevel cTLineJoinBevel;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinBevel = (CTLineJoinBevel) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTLineJoinBevel == null) {
                cTLineJoinBevel = null;
            }
        }
        return cTLineJoinBevel;
    }

    public boolean isSetBevel() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setBevel(CTLineJoinBevel cTLineJoinBevel) {
        generatedSetterHelperImpl(cTLineJoinBevel, PROPERTY_QNAME[7], 0, 1);
    }

    public CTLineJoinBevel addNewBevel() {
        CTLineJoinBevel cTLineJoinBevel;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinBevel = (CTLineJoinBevel) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTLineJoinBevel;
    }

    public void unsetBevel() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTLineJoinMiterProperties getMiter() {
        CTLineJoinMiterProperties cTLineJoinMiterProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinMiterProperties = (CTLineJoinMiterProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTLineJoinMiterProperties == null) {
                cTLineJoinMiterProperties = null;
            }
        }
        return cTLineJoinMiterProperties;
    }

    public boolean isSetMiter() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setMiter(CTLineJoinMiterProperties cTLineJoinMiterProperties) {
        generatedSetterHelperImpl(cTLineJoinMiterProperties, PROPERTY_QNAME[8], 0, 1);
    }

    public CTLineJoinMiterProperties addNewMiter() {
        CTLineJoinMiterProperties cTLineJoinMiterProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineJoinMiterProperties = (CTLineJoinMiterProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTLineJoinMiterProperties;
    }

    public void unsetMiter() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTLineEndProperties getHeadEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineEndProperties = (CTLineEndProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTLineEndProperties == null) {
                cTLineEndProperties = null;
            }
        }
        return cTLineEndProperties;
    }

    public boolean isSetHeadEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setHeadEnd(CTLineEndProperties cTLineEndProperties) {
        generatedSetterHelperImpl(cTLineEndProperties, PROPERTY_QNAME[9], 0, 1);
    }

    public CTLineEndProperties addNewHeadEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineEndProperties = (CTLineEndProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTLineEndProperties;
    }

    public void unsetHeadEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTLineEndProperties getTailEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineEndProperties = (CTLineEndProperties) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTLineEndProperties == null) {
                cTLineEndProperties = null;
            }
        }
        return cTLineEndProperties;
    }

    public boolean isSetTailEnd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setTailEnd(CTLineEndProperties cTLineEndProperties) {
        generatedSetterHelperImpl(cTLineEndProperties, PROPERTY_QNAME[10], 0, 1);
    }

    public CTLineEndProperties addNewTailEnd() {
        CTLineEndProperties cTLineEndProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineEndProperties = (CTLineEndProperties) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTLineEndProperties;
    }

    public void unsetTailEnd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTOfficeArtExtensionList == null) {
                cTOfficeArtExtensionList = null;
            }
        }
        return cTOfficeArtExtensionList;
    }

    public boolean isSetExtLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[11], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public int getW() {
        int i;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[12]);
            if (simpleValue == null) {
                i = 0;
            } else {
                i = simpleValue.getIntValue();
            }
        }
        return i;
    }

    public STLineWidth xgetW() {
        STLineWidth sTLineWidth;
        synchronized (monitor()) {
            check_orphaned();
            sTLineWidth = (STLineWidth) get_store().find_attribute_user(PROPERTY_QNAME[12]);
        }
        return sTLineWidth;
    }

    public boolean isSetW() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[12]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setW(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 12
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setIntValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.setW(int):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetW(org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 12
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.xsetW(org.openxmlformats.schemas.drawingml.x2006.main.STLineWidth):void");
    }

    public void unsetW() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[12]);
        }
    }

    public STLineCap.Enum getCap() {
        STLineCap.Enum enumR;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (simpleValue == null) {
                enumR = null;
            } else {
                enumR = (STLineCap.Enum) simpleValue.getEnumValue();
            }
        }
        return enumR;
    }

    public STLineCap xgetCap() {
        STLineCap sTLineCap;
        synchronized (monitor()) {
            check_orphaned();
            sTLineCap = (STLineCap) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return sTLineCap;
    }

    public boolean isSetCap() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[13]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCap(org.openxmlformats.schemas.drawingml.x2006.main.STLineCap.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.setCap(org.openxmlformats.schemas.drawingml.x2006.main.STLineCap$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetCap(org.openxmlformats.schemas.drawingml.x2006.main.STLineCap r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 13
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STLineCap r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STLineCap) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STLineCap r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STLineCap) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.xsetCap(org.openxmlformats.schemas.drawingml.x2006.main.STLineCap):void");
    }

    public void unsetCap() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[13]);
        }
    }

    public STCompoundLine.Enum getCmpd() {
        STCompoundLine.Enum enumR;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[14]);
            if (simpleValue == null) {
                enumR = null;
            } else {
                enumR = (STCompoundLine.Enum) simpleValue.getEnumValue();
            }
        }
        return enumR;
    }

    public STCompoundLine xgetCmpd() {
        STCompoundLine sTCompoundLine;
        synchronized (monitor()) {
            check_orphaned();
            sTCompoundLine = (STCompoundLine) get_store().find_attribute_user(PROPERTY_QNAME[14]);
        }
        return sTCompoundLine;
    }

    public boolean isSetCmpd() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[14]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCmpd(org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 14
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.setCmpd(org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetCmpd(org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 14
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.xsetCmpd(org.openxmlformats.schemas.drawingml.x2006.main.STCompoundLine):void");
    }

    public void unsetCmpd() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[14]);
        }
    }

    public STPenAlignment.Enum getAlgn() {
        STPenAlignment.Enum enumR;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (simpleValue == null) {
                enumR = null;
            } else {
                enumR = (STPenAlignment.Enum) simpleValue.getEnumValue();
            }
        }
        return enumR;
    }

    public STPenAlignment xgetAlgn() {
        STPenAlignment sTPenAlignment;
        synchronized (monitor()) {
            check_orphaned();
            sTPenAlignment = (STPenAlignment) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return sTPenAlignment;
    }

    public boolean isSetAlgn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[15]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAlgn(org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 15
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.setAlgn(org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetAlgn(org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 15
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTLinePropertiesImpl.xsetAlgn(org.openxmlformats.schemas.drawingml.x2006.main.STPenAlignment):void");
    }

    public void unsetAlgn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }
}
