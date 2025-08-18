package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableBackgroundStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTablePartStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid;

public class CTTableStyleImpl extends XmlComplexContentImpl implements CTTableStyle {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "tblBg"), new QName(XSSFRelation.NS_DRAWINGML, "wholeTbl"), new QName(XSSFRelation.NS_DRAWINGML, "band1H"), new QName(XSSFRelation.NS_DRAWINGML, "band2H"), new QName(XSSFRelation.NS_DRAWINGML, "band1V"), new QName(XSSFRelation.NS_DRAWINGML, "band2V"), new QName(XSSFRelation.NS_DRAWINGML, "lastCol"), new QName(XSSFRelation.NS_DRAWINGML, "firstCol"), new QName(XSSFRelation.NS_DRAWINGML, "lastRow"), new QName(XSSFRelation.NS_DRAWINGML, "seCell"), new QName(XSSFRelation.NS_DRAWINGML, "swCell"), new QName(XSSFRelation.NS_DRAWINGML, "firstRow"), new QName(XSSFRelation.NS_DRAWINGML, "neCell"), new QName(XSSFRelation.NS_DRAWINGML, "nwCell"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "styleId"), new QName("", "styleName")};
    private static final long serialVersionUID = 1;

    public CTTableStyleImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTableBackgroundStyle getTblBg() {
        CTTableBackgroundStyle find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetTblBg() {
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

    public void setTblBg(CTTableBackgroundStyle cTTableBackgroundStyle) {
        generatedSetterHelperImpl(cTTableBackgroundStyle, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTableBackgroundStyle addNewTblBg() {
        CTTableBackgroundStyle add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetTblBg() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTTablePartStyle getWholeTbl() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetWholeTbl() {
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

    public void setWholeTbl(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[1], 0, 1);
    }

    public CTTablePartStyle addNewWholeTbl() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTTablePartStyle;
    }

    public void unsetWholeTbl() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTTablePartStyle getBand1H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetBand1H() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setBand1H(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[2], 0, 1);
    }

    public CTTablePartStyle addNewBand1H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTTablePartStyle;
    }

    public void unsetBand1H() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTTablePartStyle getBand2H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetBand2H() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setBand2H(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[3], 0, 1);
    }

    public CTTablePartStyle addNewBand2H() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTTablePartStyle;
    }

    public void unsetBand2H() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTTablePartStyle getBand1V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetBand1V() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setBand1V(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[4], 0, 1);
    }

    public CTTablePartStyle addNewBand1V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTTablePartStyle;
    }

    public void unsetBand1V() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTTablePartStyle getBand2V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetBand2V() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setBand2V(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[5], 0, 1);
    }

    public CTTablePartStyle addNewBand2V() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTTablePartStyle;
    }

    public void unsetBand2V() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTTablePartStyle getLastCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetLastCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setLastCol(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[6], 0, 1);
    }

    public CTTablePartStyle addNewLastCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTTablePartStyle;
    }

    public void unsetLastCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTTablePartStyle getFirstCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetFirstCol() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setFirstCol(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[7], 0, 1);
    }

    public CTTablePartStyle addNewFirstCol() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTTablePartStyle;
    }

    public void unsetFirstCol() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTTablePartStyle getLastRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetLastRow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setLastRow(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[8], 0, 1);
    }

    public CTTablePartStyle addNewLastRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTTablePartStyle;
    }

    public void unsetLastRow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTTablePartStyle getSeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetSeCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setSeCell(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[9], 0, 1);
    }

    public CTTablePartStyle addNewSeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTTablePartStyle;
    }

    public void unsetSeCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTTablePartStyle getSwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetSwCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setSwCell(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[10], 0, 1);
    }

    public CTTablePartStyle addNewSwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTTablePartStyle;
    }

    public void unsetSwCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTTablePartStyle getFirstRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetFirstRow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setFirstRow(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[11], 0, 1);
    }

    public CTTablePartStyle addNewFirstRow() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTTablePartStyle;
    }

    public void unsetFirstRow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTTablePartStyle getNeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetNeCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setNeCell(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[12], 0, 1);
    }

    public CTTablePartStyle addNewNeCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTTablePartStyle;
    }

    public void unsetNeCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTTablePartStyle getNwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTTablePartStyle == null) {
                cTTablePartStyle = null;
            }
        }
        return cTTablePartStyle;
    }

    public boolean isSetNwCell() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setNwCell(CTTablePartStyle cTTablePartStyle) {
        generatedSetterHelperImpl(cTTablePartStyle, PROPERTY_QNAME[13], 0, 1);
    }

    public CTTablePartStyle addNewNwCell() {
        CTTablePartStyle cTTablePartStyle;
        synchronized (monitor()) {
            check_orphaned();
            cTTablePartStyle = (CTTablePartStyle) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTTablePartStyle;
    }

    public void unsetNwCell() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], 0);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[14], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[14]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[14], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], 0);
        }
    }

    public String getStyleId() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public STGuid xgetStyleId() {
        STGuid sTGuid;
        synchronized (monitor()) {
            check_orphaned();
            sTGuid = (STGuid) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return sTGuid;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStyleId(java.lang.String r6) {
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
            r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableStyleImpl.setStyleId(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetStyleId(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid r6) {
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
            org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid r1 = (org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid r1 = (org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableStyleImpl.xsetStyleId(org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STGuid):void");
    }

    public String getStyleName() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[16]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetStyleName() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[16]);
        }
        return xmlString;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setStyleName(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 16
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
            r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableStyleImpl.setStyleName(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetStyleName(org.apache.xmlbeans.XmlString r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 16
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlString r1 = (org.apache.xmlbeans.XmlString) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTableStyleImpl.xsetStyleName(org.apache.xmlbeans.XmlString):void");
    }
}
