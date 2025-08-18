package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;

public class CTEffectListImpl extends XmlComplexContentImpl implements CTEffectList {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "blur"), new QName(XSSFRelation.NS_DRAWINGML, "fillOverlay"), new QName(XSSFRelation.NS_DRAWINGML, "glow"), new QName(XSSFRelation.NS_DRAWINGML, "innerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "outerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "prstShdw"), new QName(XSSFRelation.NS_DRAWINGML, "reflection"), new QName(XSSFRelation.NS_DRAWINGML, "softEdge")};
    private static final long serialVersionUID = 1;

    public CTEffectListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public CTBlurEffect getBlur() {
        CTBlurEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetBlur() {
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

    public void setBlur(CTBlurEffect cTBlurEffect) {
        generatedSetterHelperImpl(cTBlurEffect, PROPERTY_QNAME[0], 0, 1);
    }

    public CTBlurEffect addNewBlur() {
        CTBlurEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void unsetBlur() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], 0);
        }
    }

    public CTFillOverlayEffect getFillOverlay() {
        CTFillOverlayEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetFillOverlay() {
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

    public void setFillOverlay(CTFillOverlayEffect cTFillOverlayEffect) {
        generatedSetterHelperImpl(cTFillOverlayEffect, PROPERTY_QNAME[1], 0, 1);
    }

    public CTFillOverlayEffect addNewFillOverlay() {
        CTFillOverlayEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void unsetFillOverlay() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], 0);
        }
    }

    public CTGlowEffect getGlow() {
        CTGlowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetGlow() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[2]) != 0;
        }
        return z;
    }

    public void setGlow(CTGlowEffect cTGlowEffect) {
        generatedSetterHelperImpl(cTGlowEffect, PROPERTY_QNAME[2], 0, 1);
    }

    public CTGlowEffect addNewGlow() {
        CTGlowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void unsetGlow() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], 0);
        }
    }

    public CTInnerShadowEffect getInnerShdw() {
        CTInnerShadowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetInnerShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[3]) != 0;
        }
        return z;
    }

    public void setInnerShdw(CTInnerShadowEffect cTInnerShadowEffect) {
        generatedSetterHelperImpl(cTInnerShadowEffect, PROPERTY_QNAME[3], 0, 1);
    }

    public CTInnerShadowEffect addNewInnerShdw() {
        CTInnerShadowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void unsetInnerShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], 0);
        }
    }

    public CTOuterShadowEffect getOuterShdw() {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTOuterShadowEffect = (CTOuterShadowEffect) get_store().find_element_user(PROPERTY_QNAME[4], 0);
            if (cTOuterShadowEffect == null) {
                cTOuterShadowEffect = null;
            }
        }
        return cTOuterShadowEffect;
    }

    public boolean isSetOuterShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[4]) != 0;
        }
        return z;
    }

    public void setOuterShdw(CTOuterShadowEffect cTOuterShadowEffect) {
        generatedSetterHelperImpl(cTOuterShadowEffect, PROPERTY_QNAME[4], 0, 1);
    }

    public CTOuterShadowEffect addNewOuterShdw() {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTOuterShadowEffect = (CTOuterShadowEffect) get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return cTOuterShadowEffect;
    }

    public void unsetOuterShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], 0);
        }
    }

    public CTPresetShadowEffect getPrstShdw() {
        CTPresetShadowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetPrstShdw() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[5]) != 0;
        }
        return z;
    }

    public void setPrstShdw(CTPresetShadowEffect cTPresetShadowEffect) {
        generatedSetterHelperImpl(cTPresetShadowEffect, PROPERTY_QNAME[5], 0, 1);
    }

    public CTPresetShadowEffect addNewPrstShdw() {
        CTPresetShadowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void unsetPrstShdw() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], 0);
        }
    }

    public CTReflectionEffect getReflection() {
        CTReflectionEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetReflection() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[6]) != 0;
        }
        return z;
    }

    public void setReflection(CTReflectionEffect cTReflectionEffect) {
        generatedSetterHelperImpl(cTReflectionEffect, PROPERTY_QNAME[6], 0, 1);
    }

    public CTReflectionEffect addNewReflection() {
        CTReflectionEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void unsetReflection() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], 0);
        }
    }

    public CTSoftEdgesEffect getSoftEdge() {
        CTSoftEdgesEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], 0);
            if (find_element_user == null) {
                find_element_user = null;
            }
        }
        return find_element_user;
    }

    public boolean isSetSoftEdge() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().count_elements(PROPERTY_QNAME[7]) != 0;
        }
        return z;
    }

    public void setSoftEdge(CTSoftEdgesEffect cTSoftEdgesEffect) {
        generatedSetterHelperImpl(cTSoftEdgesEffect, PROPERTY_QNAME[7], 0, 1);
    }

    public CTSoftEdgesEffect addNewSoftEdge() {
        CTSoftEdgesEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void unsetSoftEdge() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], 0);
        }
    }
}
