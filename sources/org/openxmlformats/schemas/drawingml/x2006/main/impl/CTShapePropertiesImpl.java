package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGradientFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGroupFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNoFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPatternFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTScene3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShape3D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransform2D;
import org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode;

public class CTShapePropertiesImpl extends XmlComplexContentImpl implements CTShapeProperties {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "xfrm"), new QName(XSSFRelation.NS_DRAWINGML, "custGeom"), new QName(XSSFRelation.NS_DRAWINGML, "prstGeom"), new QName(XSSFRelation.NS_DRAWINGML, "noFill"), new QName(XSSFRelation.NS_DRAWINGML, "solidFill"), new QName(XSSFRelation.NS_DRAWINGML, "gradFill"), new QName(XSSFRelation.NS_DRAWINGML, "blipFill"), new QName(XSSFRelation.NS_DRAWINGML, "pattFill"), new QName(XSSFRelation.NS_DRAWINGML, "grpFill"), new QName(XSSFRelation.NS_DRAWINGML, "ln"), new QName(XSSFRelation.NS_DRAWINGML, "effectLst"), new QName(XSSFRelation.NS_DRAWINGML, "effectDag"), new QName(XSSFRelation.NS_DRAWINGML, "scene3d"), new QName(XSSFRelation.NS_DRAWINGML, "sp3d"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName("", "bwMode")};
    private static final long serialVersionUID = 1;

    public CTShapePropertiesImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTTransform2D getXfrm() {
        CTTransform2D cTTransform2D;
        synchronized (monitor()) {
            check_orphaned();
            cTTransform2D = (CTTransform2D) get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (cTTransform2D == null) {
                cTTransform2D = null;
            }
        }
        return cTTransform2D;
    }

    public boolean isSetXfrm() {
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

    public void setXfrm(CTTransform2D cTTransform2D) {
        generatedSetterHelperImpl(cTTransform2D, PROPERTY_QNAME[0], 0, 1);
    }

    public CTTransform2D addNewXfrm() {
        CTTransform2D cTTransform2D;
        synchronized (monitor()) {
            check_orphaned();
            cTTransform2D = (CTTransform2D) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTransform2D;
    }

    public void unsetXfrm() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTCustomGeometry2D getCustGeom() {
        CTCustomGeometry2D cTCustomGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomGeometry2D = (CTCustomGeometry2D) get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (cTCustomGeometry2D == null) {
                cTCustomGeometry2D = null;
            }
        }
        return cTCustomGeometry2D;
    }

    public boolean isSetCustGeom() {
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

    public void setCustGeom(CTCustomGeometry2D cTCustomGeometry2D) {
        generatedSetterHelperImpl(cTCustomGeometry2D, PROPERTY_QNAME[1], 0, 1);
    }

    public CTCustomGeometry2D addNewCustGeom() {
        CTCustomGeometry2D cTCustomGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            cTCustomGeometry2D = (CTCustomGeometry2D) get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return cTCustomGeometry2D;
    }

    public void unsetCustGeom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTPresetGeometry2D getPrstGeom() {
        CTPresetGeometry2D cTPresetGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetGeometry2D = (CTPresetGeometry2D) get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (cTPresetGeometry2D == null) {
                cTPresetGeometry2D = null;
            }
        }
        return cTPresetGeometry2D;
    }

    public boolean isSetPrstGeom() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setPrstGeom(CTPresetGeometry2D cTPresetGeometry2D) {
        generatedSetterHelperImpl(cTPresetGeometry2D, PROPERTY_QNAME[2], 0, 1);
    }

    public CTPresetGeometry2D addNewPrstGeom() {
        CTPresetGeometry2D cTPresetGeometry2D;
        synchronized (monitor()) {
            check_orphaned();
            cTPresetGeometry2D = (CTPresetGeometry2D) get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return cTPresetGeometry2D;
    }

    public void unsetPrstGeom() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTNoFillProperties getNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().find_element_user(PROPERTY_QNAME[3], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setNoFill(CTNoFillProperties cTNoFillProperties) {
        generatedSetterHelperImpl(cTNoFillProperties, PROPERTY_QNAME[3], 0, 1);
    }

    public CTNoFillProperties addNewNoFill() {
        CTNoFillProperties cTNoFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTNoFillProperties = (CTNoFillProperties) get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return cTNoFillProperties;
    }

    public void unsetNoFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTSolidColorFillProperties getSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().find_element_user(PROPERTY_QNAME[4], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setSolidFill(CTSolidColorFillProperties cTSolidColorFillProperties) {
        generatedSetterHelperImpl(cTSolidColorFillProperties, PROPERTY_QNAME[4], 0, 1);
    }

    public CTSolidColorFillProperties addNewSolidFill() {
        CTSolidColorFillProperties cTSolidColorFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTSolidColorFillProperties = (CTSolidColorFillProperties) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTSolidColorFillProperties;
    }

    public void unsetSolidFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTGradientFillProperties getGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().find_element_user(PROPERTY_QNAME[5], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setGradFill(CTGradientFillProperties cTGradientFillProperties) {
        generatedSetterHelperImpl(cTGradientFillProperties, PROPERTY_QNAME[5], 0, 1);
    }

    public CTGradientFillProperties addNewGradFill() {
        CTGradientFillProperties cTGradientFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGradientFillProperties = (CTGradientFillProperties) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTGradientFillProperties;
    }

    public void unsetGradFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTBlipFillProperties getBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTBlipFillProperties = (CTBlipFillProperties) get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (cTBlipFillProperties == null) {
                cTBlipFillProperties = null;
            }
        }
        return cTBlipFillProperties;
    }

    public boolean isSetBlipFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setBlipFill(CTBlipFillProperties cTBlipFillProperties) {
        generatedSetterHelperImpl(cTBlipFillProperties, PROPERTY_QNAME[6], 0, 1);
    }

    public CTBlipFillProperties addNewBlipFill() {
        CTBlipFillProperties cTBlipFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTBlipFillProperties = (CTBlipFillProperties) get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return cTBlipFillProperties;
    }

    public void unsetBlipFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTPatternFillProperties getPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().find_element_user(PROPERTY_QNAME[7], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setPattFill(CTPatternFillProperties cTPatternFillProperties) {
        generatedSetterHelperImpl(cTPatternFillProperties, PROPERTY_QNAME[7], 0, 1);
    }

    public CTPatternFillProperties addNewPattFill() {
        CTPatternFillProperties cTPatternFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTPatternFillProperties = (CTPatternFillProperties) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTPatternFillProperties;
    }

    public void unsetPattFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }

    public CTGroupFillProperties getGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupFillProperties = (CTGroupFillProperties) get_store().find_element_user(PROPERTY_QNAME[8], 0);
            if (cTGroupFillProperties == null) {
                cTGroupFillProperties = null;
            }
        }
        return cTGroupFillProperties;
    }

    public boolean isSetGrpFill() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[8]) != 0;
        }
        return z;
    }

    public void setGrpFill(CTGroupFillProperties cTGroupFillProperties) {
        generatedSetterHelperImpl(cTGroupFillProperties, PROPERTY_QNAME[8], 0, 1);
    }

    public CTGroupFillProperties addNewGrpFill() {
        CTGroupFillProperties cTGroupFillProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTGroupFillProperties = (CTGroupFillProperties) get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return cTGroupFillProperties;
    }

    public void unsetGrpFill() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], 0);
        }
    }

    public CTLineProperties getLn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineProperties = (CTLineProperties) get_store().find_element_user(PROPERTY_QNAME[9], 0);
            if (cTLineProperties == null) {
                cTLineProperties = null;
            }
        }
        return cTLineProperties;
    }

    public boolean isSetLn() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[9]) != 0;
        }
        return z;
    }

    public void setLn(CTLineProperties cTLineProperties) {
        generatedSetterHelperImpl(cTLineProperties, PROPERTY_QNAME[9], 0, 1);
    }

    public CTLineProperties addNewLn() {
        CTLineProperties cTLineProperties;
        synchronized (monitor()) {
            check_orphaned();
            cTLineProperties = (CTLineProperties) get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return cTLineProperties;
    }

    public void unsetLn() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], 0);
        }
    }

    public CTEffectList getEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectList = (CTEffectList) get_store().find_element_user(PROPERTY_QNAME[10], 0);
            if (cTEffectList == null) {
                cTEffectList = null;
            }
        }
        return cTEffectList;
    }

    public boolean isSetEffectLst() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[10]) != 0;
        }
        return z;
    }

    public void setEffectLst(CTEffectList cTEffectList) {
        generatedSetterHelperImpl(cTEffectList, PROPERTY_QNAME[10], 0, 1);
    }

    public CTEffectList addNewEffectLst() {
        CTEffectList cTEffectList;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectList = (CTEffectList) get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return cTEffectList;
    }

    public void unsetEffectLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], 0);
        }
    }

    public CTEffectContainer getEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[11], 0);
            if (cTEffectContainer == null) {
                cTEffectContainer = null;
            }
        }
        return cTEffectContainer;
    }

    public boolean isSetEffectDag() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[11]) != 0;
        }
        return z;
    }

    public void setEffectDag(CTEffectContainer cTEffectContainer) {
        generatedSetterHelperImpl(cTEffectContainer, PROPERTY_QNAME[11], 0, 1);
    }

    public CTEffectContainer addNewEffectDag() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTEffectContainer;
    }

    public void unsetEffectDag() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], 0);
        }
    }

    public CTScene3D getScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            cTScene3D = (CTScene3D) get_store().find_element_user(PROPERTY_QNAME[12], 0);
            if (cTScene3D == null) {
                cTScene3D = null;
            }
        }
        return cTScene3D;
    }

    public boolean isSetScene3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[12]) != 0;
        }
        return z;
    }

    public void setScene3D(CTScene3D cTScene3D) {
        generatedSetterHelperImpl(cTScene3D, PROPERTY_QNAME[12], 0, 1);
    }

    public CTScene3D addNewScene3D() {
        CTScene3D cTScene3D;
        synchronized (monitor()) {
            check_orphaned();
            cTScene3D = (CTScene3D) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTScene3D;
    }

    public void unsetScene3D() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], 0);
        }
    }

    public CTShape3D getSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            cTShape3D = (CTShape3D) get_store().find_element_user(PROPERTY_QNAME[13], 0);
            if (cTShape3D == null) {
                cTShape3D = null;
            }
        }
        return cTShape3D;
    }

    public boolean isSetSp3D() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[13]) != 0;
        }
        return z;
    }

    public void setSp3D(CTShape3D cTShape3D) {
        generatedSetterHelperImpl(cTShape3D, PROPERTY_QNAME[13], 0, 1);
    }

    public CTShape3D addNewSp3D() {
        CTShape3D cTShape3D;
        synchronized (monitor()) {
            check_orphaned();
            cTShape3D = (CTShape3D) get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return cTShape3D;
    }

    public void unsetSp3D() {
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

    public STBlackWhiteMode.Enum getBwMode() {
        STBlackWhiteMode.Enum enumR;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[15]);
            if (simpleValue == null) {
                enumR = null;
            } else {
                enumR = (STBlackWhiteMode.Enum) simpleValue.getEnumValue();
            }
        }
        return enumR;
    }

    public STBlackWhiteMode xgetBwMode() {
        STBlackWhiteMode sTBlackWhiteMode;
        synchronized (monitor()) {
            check_orphaned();
            sTBlackWhiteMode = (STBlackWhiteMode) get_store().find_attribute_user(PROPERTY_QNAME[15]);
        }
        return sTBlackWhiteMode;
    }

    public boolean isSetBwMode() {
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
    public void setBwMode(org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode.Enum r6) {
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTShapePropertiesImpl.setBwMode(org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetBwMode(org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode r6) {
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
            org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTShapePropertiesImpl.xsetBwMode(org.openxmlformats.schemas.drawingml.x2006.main.STBlackWhiteMode):void");
    }

    public void unsetBwMode() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[15]);
        }
    }
}
