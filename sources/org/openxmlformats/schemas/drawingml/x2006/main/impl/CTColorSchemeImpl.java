package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColor;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorScheme;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;

public class CTColorSchemeImpl extends XmlComplexContentImpl implements CTColorScheme {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "dk1"), new QName(XSSFRelation.NS_DRAWINGML, "lt1"), new QName(XSSFRelation.NS_DRAWINGML, "dk2"), new QName(XSSFRelation.NS_DRAWINGML, "lt2"), new QName(XSSFRelation.NS_DRAWINGML, "accent1"), new QName(XSSFRelation.NS_DRAWINGML, "accent2"), new QName(XSSFRelation.NS_DRAWINGML, "accent3"), new QName(XSSFRelation.NS_DRAWINGML, "accent4"), new QName(XSSFRelation.NS_DRAWINGML, "accent5"), new QName(XSSFRelation.NS_DRAWINGML, "accent6"), new QName(XSSFRelation.NS_DRAWINGML, "hlink"), new QName(XSSFRelation.NS_DRAWINGML, "folHlink"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTColorSchemeImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTColor getDk1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setDk1(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[0], 0, 1);
    }

    public CTColor addNewDk1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTColor;
    }

    public CTColor getLt1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setLt1(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[1], 0, 1);
    }

    public CTColor addNewLt1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTColor;
    }

    public CTColor getDk2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setDk2(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[2], 0, 1);
    }

    public CTColor addNewDk2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTColor;
    }

    public CTColor getLt2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setLt2(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[3], 0, 1);
    }

    public CTColor addNewLt2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTColor;
    }

    public CTColor getAccent1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent1(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[4], 0, 1);
    }

    public CTColor addNewAccent1() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTColor;
    }

    public CTColor getAccent2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent2(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[5], 0, 1);
    }

    public CTColor addNewAccent2() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTColor;
    }

    public CTColor getAccent3() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent3(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[6], 0, 1);
    }

    public CTColor addNewAccent3() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTColor;
    }

    public CTColor getAccent4() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent4(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[7], 0, 1);
    }

    public CTColor addNewAccent4() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTColor;
    }

    public CTColor getAccent5() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent5(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[8], 0, 1);
    }

    public CTColor addNewAccent5() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTColor;
    }

    public CTColor getAccent6() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setAccent6(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[9], 0, 1);
    }

    public CTColor addNewAccent6() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTColor;
    }

    public CTColor getHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setHlink(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[10], 0, 1);
    }

    public CTColor addNewHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTColor;
    }

    public CTColor getFolHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTColor == null) {
                cTColor = null;
            }
        }
        return cTColor;
    }

    public void setFolHlink(CTColor cTColor) {
        generatedSetterHelperImpl(cTColor, PROPERTY_QNAME[11], 0, 1);
    }

    public CTColor addNewFolHlink() {
        CTColor cTColor;
        synchronized (monitor()) {
            check_orphaned();
            cTColor = (CTColor) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTColor;
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[12], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[12], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public String getName() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[13]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlString xgetName() {
        XmlString xmlString;
        synchronized (monitor()) {
            check_orphaned();
            xmlString = (XmlString) get_store().find_attribute_user(PROPERTY_QNAME[13]);
        }
        return xmlString;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setName(java.lang.String r6) {
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
            r1.setStringValue(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTColorSchemeImpl.setName(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetName(org.apache.xmlbeans.XmlString r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTColorSchemeImpl.xsetName(org.apache.xmlbeans.XmlString):void");
    }
}
