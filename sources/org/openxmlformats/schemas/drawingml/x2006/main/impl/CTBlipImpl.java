package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlip;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOfficeArtExtensionList;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;

public class CTBlipImpl extends XmlComplexContentImpl implements CTBlip {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "alphaBiLevel"), new QName(XSSFRelation.NS_DRAWINGML, "alphaCeiling"), new QName(XSSFRelation.NS_DRAWINGML, "alphaFloor"), new QName(XSSFRelation.NS_DRAWINGML, "alphaInv"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "alphaModFix"), new QName(XSSFRelation.NS_DRAWINGML, "alphaRepl"), new QName(XSSFRelation.NS_DRAWINGML, "biLevel"), new QName(XSSFRelation.NS_DRAWINGML, "blur"), new QName(XSSFRelation.NS_DRAWINGML, "clrChange"), new QName(XSSFRelation.NS_DRAWINGML, "clrRepl"), new QName(XSSFRelation.NS_DRAWINGML, "duotone"), new QName(XSSFRelation.NS_DRAWINGML, "fillOverlay"), new QName(XSSFRelation.NS_DRAWINGML, "grayscl"), new QName(XSSFRelation.NS_DRAWINGML, "hsl"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "extLst"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "embed"), new QName(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "link"), new QName("", "cstate")};
    private static final long serialVersionUID = 1;

    public CTBlipImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTAlphaBiLevelEffect> getAlphaBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda39(this), new CTBlipImpl$$ExternalSyntheticLambda40(this), new CTBlipImpl$$ExternalSyntheticLambda41(this), new CTBlipImpl$$ExternalSyntheticLambda42(this), new CTBlipImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaBiLevelEffect[] getAlphaBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTAlphaBiLevelEffect[0]);
    }

    public CTAlphaBiLevelEffect getAlphaBiLevelArray(int i) {
        CTAlphaBiLevelEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaBiLevelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaBiLevelEffectArr, PROPERTY_QNAME[0]);
    }

    public void setAlphaBiLevelArray(int i, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
        generatedSetterHelperImpl(cTAlphaBiLevelEffect, PROPERTY_QNAME[0], i, 2);
    }

    public CTAlphaBiLevelEffect insertNewAlphaBiLevel(int i) {
        CTAlphaBiLevelEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return insert_element_user;
    }

    public CTAlphaBiLevelEffect addNewAlphaBiLevel() {
        CTAlphaBiLevelEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return add_element_user;
    }

    public void removeAlphaBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTAlphaCeilingEffect> getAlphaCeilingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda61(this), new CTBlipImpl$$ExternalSyntheticLambda62(this), new CTBlipImpl$$ExternalSyntheticLambda63(this), new CTBlipImpl$$ExternalSyntheticLambda64(this), new CTBlipImpl$$ExternalSyntheticLambda65(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaCeilingEffect[] getAlphaCeilingArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTAlphaCeilingEffect[0]);
    }

    public CTAlphaCeilingEffect getAlphaCeilingArray(int i) {
        CTAlphaCeilingEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaCeilingArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] cTAlphaCeilingEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaCeilingEffectArr, PROPERTY_QNAME[1]);
    }

    public void setAlphaCeilingArray(int i, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
        generatedSetterHelperImpl(cTAlphaCeilingEffect, PROPERTY_QNAME[1], i, 2);
    }

    public CTAlphaCeilingEffect insertNewAlphaCeiling(int i) {
        CTAlphaCeilingEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return insert_element_user;
    }

    public CTAlphaCeilingEffect addNewAlphaCeiling() {
        CTAlphaCeilingEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void removeAlphaCeiling(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTAlphaFloorEffect> getAlphaFloorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda56(this), new CTBlipImpl$$ExternalSyntheticLambda57(this), new CTBlipImpl$$ExternalSyntheticLambda58(this), new CTBlipImpl$$ExternalSyntheticLambda59(this), new CTBlipImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaFloorEffect[] getAlphaFloorArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTAlphaFloorEffect[0]);
    }

    public CTAlphaFloorEffect getAlphaFloorArray(int i) {
        CTAlphaFloorEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaFloorArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setAlphaFloorArray(CTAlphaFloorEffect[] cTAlphaFloorEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaFloorEffectArr, PROPERTY_QNAME[2]);
    }

    public void setAlphaFloorArray(int i, CTAlphaFloorEffect cTAlphaFloorEffect) {
        generatedSetterHelperImpl(cTAlphaFloorEffect, PROPERTY_QNAME[2], i, 2);
    }

    public CTAlphaFloorEffect insertNewAlphaFloor(int i) {
        CTAlphaFloorEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public CTAlphaFloorEffect addNewAlphaFloor() {
        CTAlphaFloorEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeAlphaFloor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTAlphaInverseEffect> getAlphaInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda23(this), new CTBlipImpl$$ExternalSyntheticLambda24(this), new CTBlipImpl$$ExternalSyntheticLambda25(this), new CTBlipImpl$$ExternalSyntheticLambda26(this), new CTBlipImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaInverseEffect[] getAlphaInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTAlphaInverseEffect[0]);
    }

    public CTAlphaInverseEffect getAlphaInvArray(int i) {
        CTAlphaInverseEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaInvArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setAlphaInvArray(CTAlphaInverseEffect[] cTAlphaInverseEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaInverseEffectArr, PROPERTY_QNAME[3]);
    }

    public void setAlphaInvArray(int i, CTAlphaInverseEffect cTAlphaInverseEffect) {
        generatedSetterHelperImpl(cTAlphaInverseEffect, PROPERTY_QNAME[3], i, 2);
    }

    public CTAlphaInverseEffect insertNewAlphaInv(int i) {
        CTAlphaInverseEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public CTAlphaInverseEffect addNewAlphaInv() {
        CTAlphaInverseEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeAlphaInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTAlphaModulateEffect> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda34(this), new CTBlipImpl$$ExternalSyntheticLambda35(this), new CTBlipImpl$$ExternalSyntheticLambda36(this), new CTBlipImpl$$ExternalSyntheticLambda37(this), new CTBlipImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaModulateEffect[] getAlphaModArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTAlphaModulateEffect[0]);
    }

    public CTAlphaModulateEffect getAlphaModArray(int i) {
        CTAlphaModulateEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaModArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setAlphaModArray(CTAlphaModulateEffect[] cTAlphaModulateEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaModulateEffectArr, PROPERTY_QNAME[4]);
    }

    public void setAlphaModArray(int i, CTAlphaModulateEffect cTAlphaModulateEffect) {
        generatedSetterHelperImpl(cTAlphaModulateEffect, PROPERTY_QNAME[4], i, 2);
    }

    public CTAlphaModulateEffect insertNewAlphaMod(int i) {
        CTAlphaModulateEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return insert_element_user;
    }

    public CTAlphaModulateEffect addNewAlphaMod() {
        CTAlphaModulateEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTAlphaModulateFixedEffect> getAlphaModFixList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda72(this), new CTBlipImpl$$ExternalSyntheticLambda73(this), new CTBlipImpl$$ExternalSyntheticLambda74(this), new CTBlipImpl$$ExternalSyntheticLambda75(this), new CTBlipImpl$$ExternalSyntheticLambda76(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaModulateFixedEffect[] getAlphaModFixArray() {
        return (CTAlphaModulateFixedEffect[]) getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTAlphaModulateFixedEffect[0]);
    }

    public CTAlphaModulateFixedEffect getAlphaModFixArray(int i) {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (cTAlphaModulateFixedEffect == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTAlphaModulateFixedEffect;
    }

    public int sizeOfAlphaModFixArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaModulateFixedEffectArr, PROPERTY_QNAME[5]);
    }

    public void setAlphaModFixArray(int i, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
        generatedSetterHelperImpl(cTAlphaModulateFixedEffect, PROPERTY_QNAME[5], i, 2);
    }

    public CTAlphaModulateFixedEffect insertNewAlphaModFix(int i) {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return cTAlphaModulateFixedEffect;
    }

    public CTAlphaModulateFixedEffect addNewAlphaModFix() {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return cTAlphaModulateFixedEffect;
    }

    public void removeAlphaModFix(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTAlphaReplaceEffect> getAlphaReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda17(this), new CTBlipImpl$$ExternalSyntheticLambda18(this), new CTBlipImpl$$ExternalSyntheticLambda19(this), new CTBlipImpl$$ExternalSyntheticLambda20(this), new CTBlipImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaReplaceEffect[] getAlphaReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTAlphaReplaceEffect[0]);
    }

    public CTAlphaReplaceEffect getAlphaReplArray(int i) {
        CTAlphaReplaceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaReplArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setAlphaReplArray(CTAlphaReplaceEffect[] cTAlphaReplaceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaReplaceEffectArr, PROPERTY_QNAME[6]);
    }

    public void setAlphaReplArray(int i, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
        generatedSetterHelperImpl(cTAlphaReplaceEffect, PROPERTY_QNAME[6], i, 2);
    }

    public CTAlphaReplaceEffect insertNewAlphaRepl(int i) {
        CTAlphaReplaceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return insert_element_user;
    }

    public CTAlphaReplaceEffect addNewAlphaRepl() {
        CTAlphaReplaceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void removeAlphaRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTBiLevelEffect> getBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda55(this), new CTBlipImpl$$ExternalSyntheticLambda66(this), new CTBlipImpl$$ExternalSyntheticLambda77(this), new CTBlipImpl$$ExternalSyntheticLambda83(this), new CTBlipImpl$$ExternalSyntheticLambda84(this));
        }
        return javaListXmlObject;
    }

    public CTBiLevelEffect[] getBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTBiLevelEffect[0]);
    }

    public CTBiLevelEffect getBiLevelArray(int i) {
        CTBiLevelEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfBiLevelArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setBiLevelArray(CTBiLevelEffect[] cTBiLevelEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBiLevelEffectArr, PROPERTY_QNAME[7]);
    }

    public void setBiLevelArray(int i, CTBiLevelEffect cTBiLevelEffect) {
        generatedSetterHelperImpl(cTBiLevelEffect, PROPERTY_QNAME[7], i, 2);
    }

    public CTBiLevelEffect insertNewBiLevel(int i) {
        CTBiLevelEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return insert_element_user;
    }

    public CTBiLevelEffect addNewBiLevel() {
        CTBiLevelEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void removeBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTBlurEffect> getBlurList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda50(this), new CTBlipImpl$$ExternalSyntheticLambda51(this), new CTBlipImpl$$ExternalSyntheticLambda52(this), new CTBlipImpl$$ExternalSyntheticLambda53(this), new CTBlipImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTBlurEffect[] getBlurArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTBlurEffect[0]);
    }

    public CTBlurEffect getBlurArray(int i) {
        CTBlurEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfBlurArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setBlurArray(CTBlurEffect[] cTBlurEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBlurEffectArr, PROPERTY_QNAME[8]);
    }

    public void setBlurArray(int i, CTBlurEffect cTBlurEffect) {
        generatedSetterHelperImpl(cTBlurEffect, PROPERTY_QNAME[8], i, 2);
    }

    public CTBlurEffect insertNewBlur(int i) {
        CTBlurEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTBlurEffect addNewBlur() {
        CTBlurEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeBlur(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTColorChangeEffect> getClrChangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda28(this), new CTBlipImpl$$ExternalSyntheticLambda29(this), new CTBlipImpl$$ExternalSyntheticLambda30(this), new CTBlipImpl$$ExternalSyntheticLambda31(this), new CTBlipImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTColorChangeEffect[] getClrChangeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTColorChangeEffect[0]);
    }

    public CTColorChangeEffect getClrChangeArray(int i) {
        CTColorChangeEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfClrChangeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setClrChangeArray(CTColorChangeEffect[] cTColorChangeEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorChangeEffectArr, PROPERTY_QNAME[9]);
    }

    public void setClrChangeArray(int i, CTColorChangeEffect cTColorChangeEffect) {
        generatedSetterHelperImpl(cTColorChangeEffect, PROPERTY_QNAME[9], i, 2);
    }

    public CTColorChangeEffect insertNewClrChange(int i) {
        CTColorChangeEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return insert_element_user;
    }

    public CTColorChangeEffect addNewClrChange() {
        CTColorChangeEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void removeClrChange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTColorReplaceEffect> getClrReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda67(this), new CTBlipImpl$$ExternalSyntheticLambda68(this), new CTBlipImpl$$ExternalSyntheticLambda69(this), new CTBlipImpl$$ExternalSyntheticLambda70(this), new CTBlipImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTColorReplaceEffect[] getClrReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTColorReplaceEffect[0]);
    }

    public CTColorReplaceEffect getClrReplArray(int i) {
        CTColorReplaceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfClrReplArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setClrReplArray(CTColorReplaceEffect[] cTColorReplaceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorReplaceEffectArr, PROPERTY_QNAME[10]);
    }

    public void setClrReplArray(int i, CTColorReplaceEffect cTColorReplaceEffect) {
        generatedSetterHelperImpl(cTColorReplaceEffect, PROPERTY_QNAME[10], i, 2);
    }

    public CTColorReplaceEffect insertNewClrRepl(int i) {
        CTColorReplaceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return insert_element_user;
    }

    public CTColorReplaceEffect addNewClrRepl() {
        CTColorReplaceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void removeClrRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTDuotoneEffect> getDuotoneList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda78(this), new CTBlipImpl$$ExternalSyntheticLambda79(this), new CTBlipImpl$$ExternalSyntheticLambda80(this), new CTBlipImpl$$ExternalSyntheticLambda81(this), new CTBlipImpl$$ExternalSyntheticLambda82(this));
        }
        return javaListXmlObject;
    }

    public CTDuotoneEffect[] getDuotoneArray() {
        return (CTDuotoneEffect[]) getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTDuotoneEffect[0]);
    }

    public CTDuotoneEffect getDuotoneArray(int i) {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (cTDuotoneEffect == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTDuotoneEffect;
    }

    public int sizeOfDuotoneArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setDuotoneArray(CTDuotoneEffect[] cTDuotoneEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDuotoneEffectArr, PROPERTY_QNAME[11]);
    }

    public void setDuotoneArray(int i, CTDuotoneEffect cTDuotoneEffect) {
        generatedSetterHelperImpl(cTDuotoneEffect, PROPERTY_QNAME[11], i, 2);
    }

    public CTDuotoneEffect insertNewDuotone(int i) {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return cTDuotoneEffect;
    }

    public CTDuotoneEffect addNewDuotone() {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return cTDuotoneEffect;
    }

    public void removeDuotone(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTFillOverlayEffect> getFillOverlayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda0(this), new CTBlipImpl$$ExternalSyntheticLambda11(this), new CTBlipImpl$$ExternalSyntheticLambda22(this), new CTBlipImpl$$ExternalSyntheticLambda33(this), new CTBlipImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTFillOverlayEffect[] getFillOverlayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTFillOverlayEffect[0]);
    }

    public CTFillOverlayEffect getFillOverlayArray(int i) {
        CTFillOverlayEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfFillOverlayArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setFillOverlayArray(CTFillOverlayEffect[] cTFillOverlayEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFillOverlayEffectArr, PROPERTY_QNAME[12]);
    }

    public void setFillOverlayArray(int i, CTFillOverlayEffect cTFillOverlayEffect) {
        generatedSetterHelperImpl(cTFillOverlayEffect, PROPERTY_QNAME[12], i, 2);
    }

    public CTFillOverlayEffect insertNewFillOverlay(int i) {
        CTFillOverlayEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return insert_element_user;
    }

    public CTFillOverlayEffect addNewFillOverlay() {
        CTFillOverlayEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return add_element_user;
    }

    public void removeFillOverlay(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTGrayscaleEffect> getGraysclList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda12(this), new CTBlipImpl$$ExternalSyntheticLambda13(this), new CTBlipImpl$$ExternalSyntheticLambda14(this), new CTBlipImpl$$ExternalSyntheticLambda15(this), new CTBlipImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTGrayscaleEffect[] getGraysclArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTGrayscaleEffect[0]);
    }

    public CTGrayscaleEffect getGraysclArray(int i) {
        CTGrayscaleEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[13], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfGraysclArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setGraysclArray(CTGrayscaleEffect[] cTGrayscaleEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGrayscaleEffectArr, PROPERTY_QNAME[13]);
    }

    public void setGraysclArray(int i, CTGrayscaleEffect cTGrayscaleEffect) {
        generatedSetterHelperImpl(cTGrayscaleEffect, PROPERTY_QNAME[13], i, 2);
    }

    public CTGrayscaleEffect insertNewGrayscl(int i) {
        CTGrayscaleEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return insert_element_user;
    }

    public CTGrayscaleEffect addNewGrayscl() {
        CTGrayscaleEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return add_element_user;
    }

    public void removeGrayscl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTHSLEffect> getHslList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda1(this), new CTBlipImpl$$ExternalSyntheticLambda2(this), new CTBlipImpl$$ExternalSyntheticLambda3(this), new CTBlipImpl$$ExternalSyntheticLambda4(this), new CTBlipImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTHSLEffect[] getHslArray() {
        return getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTHSLEffect[0]);
    }

    public CTHSLEffect getHslArray(int i) {
        CTHSLEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[14], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfHslArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setHslArray(CTHSLEffect[] cTHSLEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHSLEffectArr, PROPERTY_QNAME[14]);
    }

    public void setHslArray(int i, CTHSLEffect cTHSLEffect) {
        generatedSetterHelperImpl(cTHSLEffect, PROPERTY_QNAME[14], i, 2);
    }

    public CTHSLEffect insertNewHsl(int i) {
        CTHSLEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return insert_element_user;
    }

    public CTHSLEffect addNewHsl() {
        CTHSLEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return add_element_user;
    }

    public void removeHsl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTLuminanceEffect> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda45(this), new CTBlipImpl$$ExternalSyntheticLambda46(this), new CTBlipImpl$$ExternalSyntheticLambda47(this), new CTBlipImpl$$ExternalSyntheticLambda48(this), new CTBlipImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTLuminanceEffect[] getLumArray() {
        return getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTLuminanceEffect[0]);
    }

    public CTLuminanceEffect getLumArray(int i) {
        CTLuminanceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[15], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfLumArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setLumArray(CTLuminanceEffect[] cTLuminanceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLuminanceEffectArr, PROPERTY_QNAME[15]);
    }

    public void setLumArray(int i, CTLuminanceEffect cTLuminanceEffect) {
        generatedSetterHelperImpl(cTLuminanceEffect, PROPERTY_QNAME[15], i, 2);
    }

    public CTLuminanceEffect insertNewLum(int i) {
        CTLuminanceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return insert_element_user;
    }

    public CTLuminanceEffect addNewLum() {
        CTLuminanceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return add_element_user;
    }

    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTTintEffect> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTBlipImpl$$ExternalSyntheticLambda6(this), new CTBlipImpl$$ExternalSyntheticLambda7(this), new CTBlipImpl$$ExternalSyntheticLambda8(this), new CTBlipImpl$$ExternalSyntheticLambda9(this), new CTBlipImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTTintEffect[] getTintArray() {
        return getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTTintEffect[0]);
    }

    public CTTintEffect getTintArray(int i) {
        CTTintEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfTintArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setTintArray(CTTintEffect[] cTTintEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTintEffectArr, PROPERTY_QNAME[16]);
    }

    public void setTintArray(int i, CTTintEffect cTTintEffect) {
        generatedSetterHelperImpl(cTTintEffect, PROPERTY_QNAME[16], i, 2);
    }

    public CTTintEffect insertNewTint(int i) {
        CTTintEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return insert_element_user;
    }

    public CTTintEffect addNewTint() {
        CTTintEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return add_element_user;
    }

    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public CTOfficeArtExtensionList getExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().find_element_user(PROPERTY_QNAME[17], 0);
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
            z = get_store().count_elements(PROPERTY_QNAME[17]) != 0;
        }
        return z;
    }

    public void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList) {
        generatedSetterHelperImpl(cTOfficeArtExtensionList, PROPERTY_QNAME[17], 0, 1);
    }

    public CTOfficeArtExtensionList addNewExtLst() {
        CTOfficeArtExtensionList cTOfficeArtExtensionList;
        synchronized (monitor()) {
            check_orphaned();
            cTOfficeArtExtensionList = (CTOfficeArtExtensionList) get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return cTOfficeArtExtensionList;
    }

    public void unsetExtLst() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], 0);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getEmbed() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 18
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            java.lang.String r5 = r1.getStringValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.getEmbed():java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId xgetEmbed() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 18
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xgetEmbed():org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId");
    }

    public boolean isSetEmbed() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[18]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setEmbed(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 18
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.setEmbed(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetEmbed(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 18
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xsetEmbed(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId):void");
    }

    public void unsetEmbed() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[18]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getLink() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002d }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002d }
            r3 = 19
            r4 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002d }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002d }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002d }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002b
        L_0x0027:
            java.lang.String r5 = r1.getStringValue()     // Catch:{ all -> 0x002d }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.getLink():java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId xgetLink() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 19
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xgetLink():org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId");
    }

    public boolean isSetLink() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[19]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLink(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 19
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.setLink(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetLink(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 19
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId r1 = (org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xsetLink(org.openxmlformats.schemas.officeDocument.x2006.relationships.STRelationshipId):void");
    }

    public void unsetLink() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[19]);
        }
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression.Enum getCstate() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002f }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002f }
            r3 = 20
            r4 = r2[r3]     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x002f }
            r1 = r5
            org.apache.xmlbeans.SimpleValue r1 = (org.apache.xmlbeans.SimpleValue) r1     // Catch:{ all -> 0x002f }
        L_0x0023:
            if (r1 != 0) goto L_0x0027
            r5 = 0
            goto L_0x002d
        L_0x0027:
            org.apache.xmlbeans.StringEnumAbstractBase r5 = r1.getEnumValue()     // Catch:{ all -> 0x002f }
            org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression$Enum r5 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression.Enum) r5     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r5
        L_0x002f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.getCstate():org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression$Enum");
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression xgetCstate() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x0025 }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x0025 }
            r3 = 20
            r4 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x0025 }
            org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression) r1     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            r1 = r2[r3]     // Catch:{ all -> 0x0025 }
            org.apache.xmlbeans.XmlAnySimpleType r5 = r5.get_default_attribute_value(r1)     // Catch:{ all -> 0x0025 }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression) r1     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xgetCstate():org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression");
    }

    public boolean isSetCstate() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[20]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCstate(org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression.Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 20
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.setCstate(org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression$Enum):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetCstate(org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 20
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression r1 = (org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTBlipImpl.xsetCstate(org.openxmlformats.schemas.drawingml.x2006.main.STBlipCompression):void");
    }

    public void unsetCstate() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[20]);
        }
    }
}
