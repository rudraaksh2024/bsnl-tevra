package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientStopList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLinearShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPathShadeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeRect;

public class CTGradientFillPropertiesImpl extends XmlComplexContentImpl implements CTGradientFillProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "gsLst"), new QName(XSSFRelation.NS_DRAWINGML, "lin"), new QName(XSSFRelation.NS_DRAWINGML, "path"), new QName(XSSFRelation.NS_DRAWINGML, "tileRect"), new QName("", "flip"), new QName("", "rotWithShape")};
    private static final long serialVersionUID = 1;

    public CTGradientFillPropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTGradientStopList getGsLst() {
        CTGradientStopList cTGradientStopList;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientStopList = (CTGradientStopList) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTGradientStopList == null) {
                cTGradientStopList = null;
            }
        }
        return cTGradientStopList;
    }

    public boolean isSetGsLst() {
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

    public void setGsLst(CTGradientStopList cTGradientStopList) {
        generatedSetterHelperImpl(cTGradientStopList, PROPERTY_QNAME[0], 0, 1);
    }

    public CTGradientStopList addNewGsLst() {
        CTGradientStopList cTGradientStopList;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientStopList = (CTGradientStopList) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTGradientStopList;
    }

    public void unsetGsLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTLinearShadeProperties getLin() {
        CTLinearShadeProperties cTLinearShadeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLinearShadeProperties = (CTLinearShadeProperties) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTLinearShadeProperties == null) {
                cTLinearShadeProperties = null;
            }
        }
        return cTLinearShadeProperties;
    }

    public boolean isSetLin() {
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

    public void setLin(CTLinearShadeProperties cTLinearShadeProperties) {
        generatedSetterHelperImpl(cTLinearShadeProperties, PROPERTY_QNAME[1], 0, 1);
    }

    public CTLinearShadeProperties addNewLin() {
        CTLinearShadeProperties cTLinearShadeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLinearShadeProperties = (CTLinearShadeProperties) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTLinearShadeProperties;
    }

    public void unsetLin() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTPathShadeProperties getPath() {
        CTPathShadeProperties cTPathShadeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPathShadeProperties = (CTPathShadeProperties) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTPathShadeProperties == null) {
                cTPathShadeProperties = null;
            }
        }
        return cTPathShadeProperties;
    }

    public boolean isSetPath() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setPath(CTPathShadeProperties cTPathShadeProperties) {
        generatedSetterHelperImpl(cTPathShadeProperties, PROPERTY_QNAME[2], 0, 1);
    }

    public CTPathShadeProperties addNewPath() {
        CTPathShadeProperties cTPathShadeProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPathShadeProperties = (CTPathShadeProperties) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPathShadeProperties;
    }

    public void unsetPath() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTRelativeRect getTileRect() {
        CTRelativeRect cTRelativeRect;
        synchronized (monitor()) {
            check_orphaned();
            cTRelativeRect = (CTRelativeRect) get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (cTRelativeRect == null) {
                cTRelativeRect = null;
            }
        }
        return cTRelativeRect;
    }

    public boolean isSetTileRect() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setTileRect(CTRelativeRect cTRelativeRect) {
        generatedSetterHelperImpl(cTRelativeRect, PROPERTY_QNAME[3], 0, 1);
    }

    public CTRelativeRect addNewTileRect() {
        CTRelativeRect cTRelativeRect;
        synchronized (monitor()) {
            check_orphaned();
            cTRelativeRect = (CTRelativeRect) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTRelativeRect;
    }

    public void unsetTileRect() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode.Enum getFlip() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002e }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002e }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002e }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x002e }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002e }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002e }
        L_0x0022:
            if (r1 != 0) goto L_0x0026
            r5 = 0
            goto L_0x002c
        L_0x0026:
            org.apache.xmlbeans.StringEnumAbstractBase r5 = r1.getEnumValue()     // Catch:{ all -> 0x002e }
            org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode$Enum r5 = (org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode.Enum) r5     // Catch:{ all -> 0x002e }
        L_0x002c:
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            return r5
        L_0x002e:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002e }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.getFlip():org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode$Enum");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode xgetFlip() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0024 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0024 }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0024 }
            org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode) r1     // Catch:{ all -> 0x0024 }
            if (r1 != 0) goto L_0x0022
            r1 = r2[r3]     // Catch:{ all -> 0x0024 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0024 }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode) r1     // Catch:{ all -> 0x0024 }
        L_0x0022:
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            return r1
        L_0x0024:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0024 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.xgetFlip():org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode");
    }

    public boolean isSetFlip() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[4]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFlip(org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setEnumValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.setFlip(org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetFlip(org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 4
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.xsetFlip(org.openxmlformats.schemas.drawingml.x2006.main.STTileFlipMode):void");
    }

    public void unsetFlip() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[4]);
        }
    }

    public boolean getRotWithShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[5]);
            if (simpleValue == null) {
                z = false;
            } else {
                z = simpleValue.getBooleanValue();
            }
        }
        return z;
    }

    public XmlBoolean xgetRotWithShape() {
        XmlBoolean xmlBoolean;
        synchronized (monitor()) {
            check_orphaned();
            xmlBoolean = (XmlBoolean) get_store().find_attribute_user(PROPERTY_QNAME[5]);
        }
        return xmlBoolean;
    }

    public boolean isSetRotWithShape() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[5]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setRotWithShape(boolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 5
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.setBooleanValue(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.setRotWithShape(boolean):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetRotWithShape(org.apache.xmlbeans.XmlBoolean r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002b }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002b }
            r3 = 5
            r4 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0026
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002b }
            r1 = r2[r3]     // Catch:{ all -> 0x002b }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002b }
            r1 = r5
            org.apache.xmlbeans.XmlBoolean r1 = (org.apache.xmlbeans.XmlBoolean) r1     // Catch:{ all -> 0x002b }
        L_0x0026:
            r1.set(r6)     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTGradientFillPropertiesImpl.xsetRotWithShape(org.apache.xmlbeans.XmlBoolean):void");
    }

    public void unsetRotWithShape() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[5]);
        }
    }
}
