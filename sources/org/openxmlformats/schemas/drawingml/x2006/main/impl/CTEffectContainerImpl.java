package org.openxmlformats.schemas.drawingml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaCeilingEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaFloorEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaInverseEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaModulateFixedEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaOutsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTAlphaReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBiLevelEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlendEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlurEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorChangeEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTColorReplaceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDuotoneEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectContainer;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectReference;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTFillOverlayEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGlowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGrayscaleEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHSLEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTInnerShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLuminanceEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTOuterShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetShadowEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTReflectionEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRelativeOffsetEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSoftEdgesEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTintEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTransformEffect;
import org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType;

public class CTEffectContainerImpl extends XmlComplexContentImpl implements CTEffectContainer {
    private static final QName[] PROPERTY_QNAME = {new QName(XSSFRelation.NS_DRAWINGML, "cont"), new QName(XSSFRelation.NS_DRAWINGML, "effect"), new QName(XSSFRelation.NS_DRAWINGML, "alphaBiLevel"), new QName(XSSFRelation.NS_DRAWINGML, "alphaCeiling"), new QName(XSSFRelation.NS_DRAWINGML, "alphaFloor"), new QName(XSSFRelation.NS_DRAWINGML, "alphaInv"), new QName(XSSFRelation.NS_DRAWINGML, "alphaMod"), new QName(XSSFRelation.NS_DRAWINGML, "alphaModFix"), new QName(XSSFRelation.NS_DRAWINGML, "alphaOutset"), new QName(XSSFRelation.NS_DRAWINGML, "alphaRepl"), new QName(XSSFRelation.NS_DRAWINGML, "biLevel"), new QName(XSSFRelation.NS_DRAWINGML, "blend"), new QName(XSSFRelation.NS_DRAWINGML, "blur"), new QName(XSSFRelation.NS_DRAWINGML, "clrChange"), new QName(XSSFRelation.NS_DRAWINGML, "clrRepl"), new QName(XSSFRelation.NS_DRAWINGML, "duotone"), new QName(XSSFRelation.NS_DRAWINGML, "fill"), new QName(XSSFRelation.NS_DRAWINGML, "fillOverlay"), new QName(XSSFRelation.NS_DRAWINGML, "glow"), new QName(XSSFRelation.NS_DRAWINGML, "grayscl"), new QName(XSSFRelation.NS_DRAWINGML, "hsl"), new QName(XSSFRelation.NS_DRAWINGML, "innerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "lum"), new QName(XSSFRelation.NS_DRAWINGML, "outerShdw"), new QName(XSSFRelation.NS_DRAWINGML, "prstShdw"), new QName(XSSFRelation.NS_DRAWINGML, "reflection"), new QName(XSSFRelation.NS_DRAWINGML, "relOff"), new QName(XSSFRelation.NS_DRAWINGML, "softEdge"), new QName(XSSFRelation.NS_DRAWINGML, "tint"), new QName(XSSFRelation.NS_DRAWINGML, "xfrm"), new QName("", "type"), new QName("", "name")};
    private static final long serialVersionUID = 1;

    public CTEffectContainerImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTEffectContainer> getContList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda67(this), new CTEffectContainerImpl$$ExternalSyntheticLambda68(this), new CTEffectContainerImpl$$ExternalSyntheticLambda69(this), new CTEffectContainerImpl$$ExternalSyntheticLambda70(this), new CTEffectContainerImpl$$ExternalSyntheticLambda71(this));
        }
        return javaListXmlObject;
    }

    public CTEffectContainer[] getContArray() {
        return (CTEffectContainer[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTEffectContainer[0]);
    }

    public CTEffectContainer getContArray(int i) {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTEffectContainer == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTEffectContainer;
    }

    public int sizeOfContArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setContArray(CTEffectContainer[] cTEffectContainerArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEffectContainerArr, PROPERTY_QNAME[0]);
    }

    public void setContArray(int i, CTEffectContainer cTEffectContainer) {
        generatedSetterHelperImpl(cTEffectContainer, PROPERTY_QNAME[0], i, 2);
    }

    public CTEffectContainer insertNewCont(int i) {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTEffectContainer;
    }

    public CTEffectContainer addNewCont() {
        CTEffectContainer cTEffectContainer;
        synchronized (monitor()) {
            check_orphaned();
            cTEffectContainer = (CTEffectContainer) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTEffectContainer;
    }

    public void removeCont(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTEffectReference> getEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda17(this), new CTEffectContainerImpl$$ExternalSyntheticLambda18(this), new CTEffectContainerImpl$$ExternalSyntheticLambda19(this), new CTEffectContainerImpl$$ExternalSyntheticLambda20(this), new CTEffectContainerImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTEffectReference[] getEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTEffectReference[0]);
    }

    public CTEffectReference getEffectArray(int i) {
        CTEffectReference find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setEffectArray(CTEffectReference[] cTEffectReferenceArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTEffectReferenceArr, PROPERTY_QNAME[1]);
    }

    public void setEffectArray(int i, CTEffectReference cTEffectReference) {
        generatedSetterHelperImpl(cTEffectReference, PROPERTY_QNAME[1], i, 2);
    }

    public CTEffectReference insertNewEffect(int i) {
        CTEffectReference insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return insert_element_user;
    }

    public CTEffectReference addNewEffect() {
        CTEffectReference add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void removeEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTAlphaBiLevelEffect> getAlphaBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda11(this), new CTEffectContainerImpl$$ExternalSyntheticLambda22(this), new CTEffectContainerImpl$$ExternalSyntheticLambda33(this), new CTEffectContainerImpl$$ExternalSyntheticLambda44(this), new CTEffectContainerImpl$$ExternalSyntheticLambda55(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaBiLevelEffect[] getAlphaBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTAlphaBiLevelEffect[0]);
    }

    public CTAlphaBiLevelEffect getAlphaBiLevelArray(int i) {
        CTAlphaBiLevelEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaBiLevelEffectArr, PROPERTY_QNAME[2]);
    }

    public void setAlphaBiLevelArray(int i, CTAlphaBiLevelEffect cTAlphaBiLevelEffect) {
        generatedSetterHelperImpl(cTAlphaBiLevelEffect, PROPERTY_QNAME[2], i, 2);
    }

    public CTAlphaBiLevelEffect insertNewAlphaBiLevel(int i) {
        CTAlphaBiLevelEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public CTAlphaBiLevelEffect addNewAlphaBiLevel() {
        CTAlphaBiLevelEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeAlphaBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTAlphaCeilingEffect> getAlphaCeilingList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda84(this), new CTEffectContainerImpl$$ExternalSyntheticLambda85(this), new CTEffectContainerImpl$$ExternalSyntheticLambda86(this), new CTEffectContainerImpl$$ExternalSyntheticLambda87(this), new CTEffectContainerImpl$$ExternalSyntheticLambda88(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaCeilingEffect[] getAlphaCeilingArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTAlphaCeilingEffect[0]);
    }

    public CTAlphaCeilingEffect getAlphaCeilingArray(int i) {
        CTAlphaCeilingEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setAlphaCeilingArray(CTAlphaCeilingEffect[] cTAlphaCeilingEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaCeilingEffectArr, PROPERTY_QNAME[3]);
    }

    public void setAlphaCeilingArray(int i, CTAlphaCeilingEffect cTAlphaCeilingEffect) {
        generatedSetterHelperImpl(cTAlphaCeilingEffect, PROPERTY_QNAME[3], i, 2);
    }

    public CTAlphaCeilingEffect insertNewAlphaCeiling(int i) {
        CTAlphaCeilingEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public CTAlphaCeilingEffect addNewAlphaCeiling() {
        CTAlphaCeilingEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeAlphaCeiling(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTAlphaFloorEffect> getAlphaFloorList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda34(this), new CTEffectContainerImpl$$ExternalSyntheticLambda35(this), new CTEffectContainerImpl$$ExternalSyntheticLambda36(this), new CTEffectContainerImpl$$ExternalSyntheticLambda37(this), new CTEffectContainerImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaFloorEffect[] getAlphaFloorArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTAlphaFloorEffect[0]);
    }

    public CTAlphaFloorEffect getAlphaFloorArray(int i) {
        CTAlphaFloorEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setAlphaFloorArray(CTAlphaFloorEffect[] cTAlphaFloorEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaFloorEffectArr, PROPERTY_QNAME[4]);
    }

    public void setAlphaFloorArray(int i, CTAlphaFloorEffect cTAlphaFloorEffect) {
        generatedSetterHelperImpl(cTAlphaFloorEffect, PROPERTY_QNAME[4], i, 2);
    }

    public CTAlphaFloorEffect insertNewAlphaFloor(int i) {
        CTAlphaFloorEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return insert_element_user;
    }

    public CTAlphaFloorEffect addNewAlphaFloor() {
        CTAlphaFloorEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void removeAlphaFloor(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTAlphaInverseEffect> getAlphaInvList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda0(this), new CTEffectContainerImpl$$ExternalSyntheticLambda61(this), new CTEffectContainerImpl$$ExternalSyntheticLambda72(this), new CTEffectContainerImpl$$ExternalSyntheticLambda83(this), new CTEffectContainerImpl$$ExternalSyntheticLambda94(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaInverseEffect[] getAlphaInvArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTAlphaInverseEffect[0]);
    }

    public CTAlphaInverseEffect getAlphaInvArray(int i) {
        CTAlphaInverseEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAlphaInvArray(CTAlphaInverseEffect[] cTAlphaInverseEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaInverseEffectArr, PROPERTY_QNAME[5]);
    }

    public void setAlphaInvArray(int i, CTAlphaInverseEffect cTAlphaInverseEffect) {
        generatedSetterHelperImpl(cTAlphaInverseEffect, PROPERTY_QNAME[5], i, 2);
    }

    public CTAlphaInverseEffect insertNewAlphaInv(int i) {
        CTAlphaInverseEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return insert_element_user;
    }

    public CTAlphaInverseEffect addNewAlphaInv() {
        CTAlphaInverseEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void removeAlphaInv(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTAlphaModulateEffect> getAlphaModList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda133(this), new CTEffectContainerImpl$$ExternalSyntheticLambda134(this), new CTEffectContainerImpl$$ExternalSyntheticLambda135(this), new CTEffectContainerImpl$$ExternalSyntheticLambda136(this), new CTEffectContainerImpl$$ExternalSyntheticLambda137(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaModulateEffect[] getAlphaModArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTAlphaModulateEffect[0]);
    }

    public CTAlphaModulateEffect getAlphaModArray(int i) {
        CTAlphaModulateEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setAlphaModArray(CTAlphaModulateEffect[] cTAlphaModulateEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaModulateEffectArr, PROPERTY_QNAME[6]);
    }

    public void setAlphaModArray(int i, CTAlphaModulateEffect cTAlphaModulateEffect) {
        generatedSetterHelperImpl(cTAlphaModulateEffect, PROPERTY_QNAME[6], i, 2);
    }

    public CTAlphaModulateEffect insertNewAlphaMod(int i) {
        CTAlphaModulateEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return insert_element_user;
    }

    public CTAlphaModulateEffect addNewAlphaMod() {
        CTAlphaModulateEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void removeAlphaMod(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTAlphaModulateFixedEffect> getAlphaModFixList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda106(this), new CTEffectContainerImpl$$ExternalSyntheticLambda107(this), new CTEffectContainerImpl$$ExternalSyntheticLambda108(this), new CTEffectContainerImpl$$ExternalSyntheticLambda109(this), new CTEffectContainerImpl$$ExternalSyntheticLambda110(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaModulateFixedEffect[] getAlphaModFixArray() {
        return (CTAlphaModulateFixedEffect[]) getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTAlphaModulateFixedEffect[0]);
    }

    public CTAlphaModulateFixedEffect getAlphaModFixArray(int i) {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().find_element_user(PROPERTY_QNAME[7], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setAlphaModFixArray(CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaModulateFixedEffectArr, PROPERTY_QNAME[7]);
    }

    public void setAlphaModFixArray(int i, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect) {
        generatedSetterHelperImpl(cTAlphaModulateFixedEffect, PROPERTY_QNAME[7], i, 2);
    }

    public CTAlphaModulateFixedEffect insertNewAlphaModFix(int i) {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return cTAlphaModulateFixedEffect;
    }

    public CTAlphaModulateFixedEffect addNewAlphaModFix() {
        CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTAlphaModulateFixedEffect = (CTAlphaModulateFixedEffect) get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return cTAlphaModulateFixedEffect;
    }

    public void removeAlphaModFix(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTAlphaOutsetEffect> getAlphaOutsetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda56(this), new CTEffectContainerImpl$$ExternalSyntheticLambda57(this), new CTEffectContainerImpl$$ExternalSyntheticLambda58(this), new CTEffectContainerImpl$$ExternalSyntheticLambda59(this), new CTEffectContainerImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaOutsetEffect[] getAlphaOutsetArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTAlphaOutsetEffect[0]);
    }

    public CTAlphaOutsetEffect getAlphaOutsetArray(int i) {
        CTAlphaOutsetEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAlphaOutsetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setAlphaOutsetArray(CTAlphaOutsetEffect[] cTAlphaOutsetEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaOutsetEffectArr, PROPERTY_QNAME[8]);
    }

    public void setAlphaOutsetArray(int i, CTAlphaOutsetEffect cTAlphaOutsetEffect) {
        generatedSetterHelperImpl(cTAlphaOutsetEffect, PROPERTY_QNAME[8], i, 2);
    }

    public CTAlphaOutsetEffect insertNewAlphaOutset(int i) {
        CTAlphaOutsetEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTAlphaOutsetEffect addNewAlphaOutset() {
        CTAlphaOutsetEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeAlphaOutset(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTAlphaReplaceEffect> getAlphaReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda105(this), new CTEffectContainerImpl$$ExternalSyntheticLambda116(this), new CTEffectContainerImpl$$ExternalSyntheticLambda127(this), new CTEffectContainerImpl$$ExternalSyntheticLambda138(this), new CTEffectContainerImpl$$ExternalSyntheticLambda149(this));
        }
        return javaListXmlObject;
    }

    public CTAlphaReplaceEffect[] getAlphaReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTAlphaReplaceEffect[0]);
    }

    public CTAlphaReplaceEffect getAlphaReplArray(int i) {
        CTAlphaReplaceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setAlphaReplArray(CTAlphaReplaceEffect[] cTAlphaReplaceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTAlphaReplaceEffectArr, PROPERTY_QNAME[9]);
    }

    public void setAlphaReplArray(int i, CTAlphaReplaceEffect cTAlphaReplaceEffect) {
        generatedSetterHelperImpl(cTAlphaReplaceEffect, PROPERTY_QNAME[9], i, 2);
    }

    public CTAlphaReplaceEffect insertNewAlphaRepl(int i) {
        CTAlphaReplaceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return insert_element_user;
    }

    public CTAlphaReplaceEffect addNewAlphaRepl() {
        CTAlphaReplaceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void removeAlphaRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTBiLevelEffect> getBiLevelList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda111(this), new CTEffectContainerImpl$$ExternalSyntheticLambda112(this), new CTEffectContainerImpl$$ExternalSyntheticLambda113(this), new CTEffectContainerImpl$$ExternalSyntheticLambda114(this), new CTEffectContainerImpl$$ExternalSyntheticLambda115(this));
        }
        return javaListXmlObject;
    }

    public CTBiLevelEffect[] getBiLevelArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTBiLevelEffect[0]);
    }

    public CTBiLevelEffect getBiLevelArray(int i) {
        CTBiLevelEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setBiLevelArray(CTBiLevelEffect[] cTBiLevelEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBiLevelEffectArr, PROPERTY_QNAME[10]);
    }

    public void setBiLevelArray(int i, CTBiLevelEffect cTBiLevelEffect) {
        generatedSetterHelperImpl(cTBiLevelEffect, PROPERTY_QNAME[10], i, 2);
    }

    public CTBiLevelEffect insertNewBiLevel(int i) {
        CTBiLevelEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return insert_element_user;
    }

    public CTBiLevelEffect addNewBiLevel() {
        CTBiLevelEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void removeBiLevel(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTBlendEffect> getBlendList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda128(this), new CTEffectContainerImpl$$ExternalSyntheticLambda129(this), new CTEffectContainerImpl$$ExternalSyntheticLambda130(this), new CTEffectContainerImpl$$ExternalSyntheticLambda131(this), new CTEffectContainerImpl$$ExternalSyntheticLambda132(this));
        }
        return javaListXmlObject;
    }

    public CTBlendEffect[] getBlendArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTBlendEffect[0]);
    }

    public CTBlendEffect getBlendArray(int i) {
        CTBlendEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfBlendArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setBlendArray(CTBlendEffect[] cTBlendEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBlendEffectArr, PROPERTY_QNAME[11]);
    }

    public void setBlendArray(int i, CTBlendEffect cTBlendEffect) {
        generatedSetterHelperImpl(cTBlendEffect, PROPERTY_QNAME[11], i, 2);
    }

    public CTBlendEffect insertNewBlend(int i) {
        CTBlendEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return insert_element_user;
    }

    public CTBlendEffect addNewBlend() {
        CTBlendEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return add_element_user;
    }

    public void removeBlend(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTBlurEffect> getBlurList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda144(this), new CTEffectContainerImpl$$ExternalSyntheticLambda145(this), new CTEffectContainerImpl$$ExternalSyntheticLambda146(this), new CTEffectContainerImpl$$ExternalSyntheticLambda147(this), new CTEffectContainerImpl$$ExternalSyntheticLambda148(this));
        }
        return javaListXmlObject;
    }

    public CTBlurEffect[] getBlurArray() {
        return getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTBlurEffect[0]);
    }

    public CTBlurEffect getBlurArray(int i) {
        CTBlurEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[12], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setBlurArray(CTBlurEffect[] cTBlurEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTBlurEffectArr, PROPERTY_QNAME[12]);
    }

    public void setBlurArray(int i, CTBlurEffect cTBlurEffect) {
        generatedSetterHelperImpl(cTBlurEffect, PROPERTY_QNAME[12], i, 2);
    }

    public CTBlurEffect insertNewBlur(int i) {
        CTBlurEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return insert_element_user;
    }

    public CTBlurEffect addNewBlur() {
        CTBlurEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return add_element_user;
    }

    public void removeBlur(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }

    public List<CTColorChangeEffect> getClrChangeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda73(this), new CTEffectContainerImpl$$ExternalSyntheticLambda74(this), new CTEffectContainerImpl$$ExternalSyntheticLambda75(this), new CTEffectContainerImpl$$ExternalSyntheticLambda76(this), new CTEffectContainerImpl$$ExternalSyntheticLambda77(this));
        }
        return javaListXmlObject;
    }

    public CTColorChangeEffect[] getClrChangeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[13], (T[]) new CTColorChangeEffect[0]);
    }

    public CTColorChangeEffect getClrChangeArray(int i) {
        CTColorChangeEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[13], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[13]);
        }
        return count_elements;
    }

    public void setClrChangeArray(CTColorChangeEffect[] cTColorChangeEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorChangeEffectArr, PROPERTY_QNAME[13]);
    }

    public void setClrChangeArray(int i, CTColorChangeEffect cTColorChangeEffect) {
        generatedSetterHelperImpl(cTColorChangeEffect, PROPERTY_QNAME[13], i, 2);
    }

    public CTColorChangeEffect insertNewClrChange(int i) {
        CTColorChangeEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[13], i);
        }
        return insert_element_user;
    }

    public CTColorChangeEffect addNewClrChange() {
        CTColorChangeEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[13]);
        }
        return add_element_user;
    }

    public void removeClrChange(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[13], i);
        }
    }

    public List<CTColorReplaceEffect> getClrReplList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda12(this), new CTEffectContainerImpl$$ExternalSyntheticLambda13(this), new CTEffectContainerImpl$$ExternalSyntheticLambda14(this), new CTEffectContainerImpl$$ExternalSyntheticLambda15(this), new CTEffectContainerImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTColorReplaceEffect[] getClrReplArray() {
        return getXmlObjectArray(PROPERTY_QNAME[14], (T[]) new CTColorReplaceEffect[0]);
    }

    public CTColorReplaceEffect getClrReplArray(int i) {
        CTColorReplaceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[14], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[14]);
        }
        return count_elements;
    }

    public void setClrReplArray(CTColorReplaceEffect[] cTColorReplaceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTColorReplaceEffectArr, PROPERTY_QNAME[14]);
    }

    public void setClrReplArray(int i, CTColorReplaceEffect cTColorReplaceEffect) {
        generatedSetterHelperImpl(cTColorReplaceEffect, PROPERTY_QNAME[14], i, 2);
    }

    public CTColorReplaceEffect insertNewClrRepl(int i) {
        CTColorReplaceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[14], i);
        }
        return insert_element_user;
    }

    public CTColorReplaceEffect addNewClrRepl() {
        CTColorReplaceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[14]);
        }
        return add_element_user;
    }

    public void removeClrRepl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[14], i);
        }
    }

    public List<CTDuotoneEffect> getDuotoneList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda100(this), new CTEffectContainerImpl$$ExternalSyntheticLambda101(this), new CTEffectContainerImpl$$ExternalSyntheticLambda102(this), new CTEffectContainerImpl$$ExternalSyntheticLambda103(this), new CTEffectContainerImpl$$ExternalSyntheticLambda104(this));
        }
        return javaListXmlObject;
    }

    public CTDuotoneEffect[] getDuotoneArray() {
        return (CTDuotoneEffect[]) getXmlObjectArray(PROPERTY_QNAME[15], (T[]) new CTDuotoneEffect[0]);
    }

    public CTDuotoneEffect getDuotoneArray(int i) {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().find_element_user(PROPERTY_QNAME[15], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[15]);
        }
        return count_elements;
    }

    public void setDuotoneArray(CTDuotoneEffect[] cTDuotoneEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTDuotoneEffectArr, PROPERTY_QNAME[15]);
    }

    public void setDuotoneArray(int i, CTDuotoneEffect cTDuotoneEffect) {
        generatedSetterHelperImpl(cTDuotoneEffect, PROPERTY_QNAME[15], i, 2);
    }

    public CTDuotoneEffect insertNewDuotone(int i) {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().insert_element_user(PROPERTY_QNAME[15], i);
        }
        return cTDuotoneEffect;
    }

    public CTDuotoneEffect addNewDuotone() {
        CTDuotoneEffect cTDuotoneEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTDuotoneEffect = (CTDuotoneEffect) get_store().add_element_user(PROPERTY_QNAME[15]);
        }
        return cTDuotoneEffect;
    }

    public void removeDuotone(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[15], i);
        }
    }

    public List<CTFillEffect> getFillList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda62(this), new CTEffectContainerImpl$$ExternalSyntheticLambda63(this), new CTEffectContainerImpl$$ExternalSyntheticLambda64(this), new CTEffectContainerImpl$$ExternalSyntheticLambda65(this), new CTEffectContainerImpl$$ExternalSyntheticLambda66(this));
        }
        return javaListXmlObject;
    }

    public CTFillEffect[] getFillArray() {
        return getXmlObjectArray(PROPERTY_QNAME[16], (T[]) new CTFillEffect[0]);
    }

    public CTFillEffect getFillArray(int i) {
        CTFillEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[16], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfFillArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[16]);
        }
        return count_elements;
    }

    public void setFillArray(CTFillEffect[] cTFillEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFillEffectArr, PROPERTY_QNAME[16]);
    }

    public void setFillArray(int i, CTFillEffect cTFillEffect) {
        generatedSetterHelperImpl(cTFillEffect, PROPERTY_QNAME[16], i, 2);
    }

    public CTFillEffect insertNewFill(int i) {
        CTFillEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[16], i);
        }
        return insert_element_user;
    }

    public CTFillEffect addNewFill() {
        CTFillEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[16]);
        }
        return add_element_user;
    }

    public void removeFill(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[16], i);
        }
    }

    public List<CTFillOverlayEffect> getFillOverlayList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda95(this), new CTEffectContainerImpl$$ExternalSyntheticLambda96(this), new CTEffectContainerImpl$$ExternalSyntheticLambda97(this), new CTEffectContainerImpl$$ExternalSyntheticLambda98(this), new CTEffectContainerImpl$$ExternalSyntheticLambda99(this));
        }
        return javaListXmlObject;
    }

    public CTFillOverlayEffect[] getFillOverlayArray() {
        return getXmlObjectArray(PROPERTY_QNAME[17], (T[]) new CTFillOverlayEffect[0]);
    }

    public CTFillOverlayEffect getFillOverlayArray(int i) {
        CTFillOverlayEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[17], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[17]);
        }
        return count_elements;
    }

    public void setFillOverlayArray(CTFillOverlayEffect[] cTFillOverlayEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTFillOverlayEffectArr, PROPERTY_QNAME[17]);
    }

    public void setFillOverlayArray(int i, CTFillOverlayEffect cTFillOverlayEffect) {
        generatedSetterHelperImpl(cTFillOverlayEffect, PROPERTY_QNAME[17], i, 2);
    }

    public CTFillOverlayEffect insertNewFillOverlay(int i) {
        CTFillOverlayEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[17], i);
        }
        return insert_element_user;
    }

    public CTFillOverlayEffect addNewFillOverlay() {
        CTFillOverlayEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[17]);
        }
        return add_element_user;
    }

    public void removeFillOverlay(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[17], i);
        }
    }

    public List<CTGlowEffect> getGlowList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda89(this), new CTEffectContainerImpl$$ExternalSyntheticLambda90(this), new CTEffectContainerImpl$$ExternalSyntheticLambda91(this), new CTEffectContainerImpl$$ExternalSyntheticLambda92(this), new CTEffectContainerImpl$$ExternalSyntheticLambda93(this));
        }
        return javaListXmlObject;
    }

    public CTGlowEffect[] getGlowArray() {
        return getXmlObjectArray(PROPERTY_QNAME[18], (T[]) new CTGlowEffect[0]);
    }

    public CTGlowEffect getGlowArray(int i) {
        CTGlowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[18], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfGlowArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[18]);
        }
        return count_elements;
    }

    public void setGlowArray(CTGlowEffect[] cTGlowEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGlowEffectArr, PROPERTY_QNAME[18]);
    }

    public void setGlowArray(int i, CTGlowEffect cTGlowEffect) {
        generatedSetterHelperImpl(cTGlowEffect, PROPERTY_QNAME[18], i, 2);
    }

    public CTGlowEffect insertNewGlow(int i) {
        CTGlowEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[18], i);
        }
        return insert_element_user;
    }

    public CTGlowEffect addNewGlow() {
        CTGlowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[18]);
        }
        return add_element_user;
    }

    public void removeGlow(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[18], i);
        }
    }

    public List<CTGrayscaleEffect> getGraysclList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda122(this), new CTEffectContainerImpl$$ExternalSyntheticLambda123(this), new CTEffectContainerImpl$$ExternalSyntheticLambda124(this), new CTEffectContainerImpl$$ExternalSyntheticLambda125(this), new CTEffectContainerImpl$$ExternalSyntheticLambda126(this));
        }
        return javaListXmlObject;
    }

    public CTGrayscaleEffect[] getGraysclArray() {
        return getXmlObjectArray(PROPERTY_QNAME[19], (T[]) new CTGrayscaleEffect[0]);
    }

    public CTGrayscaleEffect getGraysclArray(int i) {
        CTGrayscaleEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[19], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[19]);
        }
        return count_elements;
    }

    public void setGraysclArray(CTGrayscaleEffect[] cTGrayscaleEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTGrayscaleEffectArr, PROPERTY_QNAME[19]);
    }

    public void setGraysclArray(int i, CTGrayscaleEffect cTGrayscaleEffect) {
        generatedSetterHelperImpl(cTGrayscaleEffect, PROPERTY_QNAME[19], i, 2);
    }

    public CTGrayscaleEffect insertNewGrayscl(int i) {
        CTGrayscaleEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[19], i);
        }
        return insert_element_user;
    }

    public CTGrayscaleEffect addNewGrayscl() {
        CTGrayscaleEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[19]);
        }
        return add_element_user;
    }

    public void removeGrayscl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[19], i);
        }
    }

    public List<CTHSLEffect> getHslList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda50(this), new CTEffectContainerImpl$$ExternalSyntheticLambda51(this), new CTEffectContainerImpl$$ExternalSyntheticLambda52(this), new CTEffectContainerImpl$$ExternalSyntheticLambda53(this), new CTEffectContainerImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTHSLEffect[] getHslArray() {
        return getXmlObjectArray(PROPERTY_QNAME[20], (T[]) new CTHSLEffect[0]);
    }

    public CTHSLEffect getHslArray(int i) {
        CTHSLEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[20], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[20]);
        }
        return count_elements;
    }

    public void setHslArray(CTHSLEffect[] cTHSLEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTHSLEffectArr, PROPERTY_QNAME[20]);
    }

    public void setHslArray(int i, CTHSLEffect cTHSLEffect) {
        generatedSetterHelperImpl(cTHSLEffect, PROPERTY_QNAME[20], i, 2);
    }

    public CTHSLEffect insertNewHsl(int i) {
        CTHSLEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[20], i);
        }
        return insert_element_user;
    }

    public CTHSLEffect addNewHsl() {
        CTHSLEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[20]);
        }
        return add_element_user;
    }

    public void removeHsl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[20], i);
        }
    }

    public List<CTInnerShadowEffect> getInnerShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda117(this), new CTEffectContainerImpl$$ExternalSyntheticLambda118(this), new CTEffectContainerImpl$$ExternalSyntheticLambda119(this), new CTEffectContainerImpl$$ExternalSyntheticLambda120(this), new CTEffectContainerImpl$$ExternalSyntheticLambda121(this));
        }
        return javaListXmlObject;
    }

    public CTInnerShadowEffect[] getInnerShdwArray() {
        return getXmlObjectArray(PROPERTY_QNAME[21], (T[]) new CTInnerShadowEffect[0]);
    }

    public CTInnerShadowEffect getInnerShdwArray(int i) {
        CTInnerShadowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[21], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfInnerShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[21]);
        }
        return count_elements;
    }

    public void setInnerShdwArray(CTInnerShadowEffect[] cTInnerShadowEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTInnerShadowEffectArr, PROPERTY_QNAME[21]);
    }

    public void setInnerShdwArray(int i, CTInnerShadowEffect cTInnerShadowEffect) {
        generatedSetterHelperImpl(cTInnerShadowEffect, PROPERTY_QNAME[21], i, 2);
    }

    public CTInnerShadowEffect insertNewInnerShdw(int i) {
        CTInnerShadowEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[21], i);
        }
        return insert_element_user;
    }

    public CTInnerShadowEffect addNewInnerShdw() {
        CTInnerShadowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[21]);
        }
        return add_element_user;
    }

    public void removeInnerShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[21], i);
        }
    }

    public List<CTLuminanceEffect> getLumList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda6(this), new CTEffectContainerImpl$$ExternalSyntheticLambda7(this), new CTEffectContainerImpl$$ExternalSyntheticLambda8(this), new CTEffectContainerImpl$$ExternalSyntheticLambda9(this), new CTEffectContainerImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTLuminanceEffect[] getLumArray() {
        return getXmlObjectArray(PROPERTY_QNAME[22], (T[]) new CTLuminanceEffect[0]);
    }

    public CTLuminanceEffect getLumArray(int i) {
        CTLuminanceEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[22], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[22]);
        }
        return count_elements;
    }

    public void setLumArray(CTLuminanceEffect[] cTLuminanceEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTLuminanceEffectArr, PROPERTY_QNAME[22]);
    }

    public void setLumArray(int i, CTLuminanceEffect cTLuminanceEffect) {
        generatedSetterHelperImpl(cTLuminanceEffect, PROPERTY_QNAME[22], i, 2);
    }

    public CTLuminanceEffect insertNewLum(int i) {
        CTLuminanceEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[22], i);
        }
        return insert_element_user;
    }

    public CTLuminanceEffect addNewLum() {
        CTLuminanceEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[22]);
        }
        return add_element_user;
    }

    public void removeLum(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[22], i);
        }
    }

    public List<CTOuterShadowEffect> getOuterShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda1(this), new CTEffectContainerImpl$$ExternalSyntheticLambda2(this), new CTEffectContainerImpl$$ExternalSyntheticLambda3(this), new CTEffectContainerImpl$$ExternalSyntheticLambda4(this), new CTEffectContainerImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTOuterShadowEffect[] getOuterShdwArray() {
        return (CTOuterShadowEffect[]) getXmlObjectArray(PROPERTY_QNAME[23], (T[]) new CTOuterShadowEffect[0]);
    }

    public CTOuterShadowEffect getOuterShdwArray(int i) {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTOuterShadowEffect = (CTOuterShadowEffect) get_store().find_element_user(PROPERTY_QNAME[23], i);
            if (cTOuterShadowEffect == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTOuterShadowEffect;
    }

    public int sizeOfOuterShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[23]);
        }
        return count_elements;
    }

    public void setOuterShdwArray(CTOuterShadowEffect[] cTOuterShadowEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTOuterShadowEffectArr, PROPERTY_QNAME[23]);
    }

    public void setOuterShdwArray(int i, CTOuterShadowEffect cTOuterShadowEffect) {
        generatedSetterHelperImpl(cTOuterShadowEffect, PROPERTY_QNAME[23], i, 2);
    }

    public CTOuterShadowEffect insertNewOuterShdw(int i) {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTOuterShadowEffect = (CTOuterShadowEffect) get_store().insert_element_user(PROPERTY_QNAME[23], i);
        }
        return cTOuterShadowEffect;
    }

    public CTOuterShadowEffect addNewOuterShdw() {
        CTOuterShadowEffect cTOuterShadowEffect;
        synchronized (monitor()) {
            check_orphaned();
            cTOuterShadowEffect = (CTOuterShadowEffect) get_store().add_element_user(PROPERTY_QNAME[23]);
        }
        return cTOuterShadowEffect;
    }

    public void removeOuterShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[23], i);
        }
    }

    public List<CTPresetShadowEffect> getPrstShdwList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda78(this), new CTEffectContainerImpl$$ExternalSyntheticLambda79(this), new CTEffectContainerImpl$$ExternalSyntheticLambda80(this), new CTEffectContainerImpl$$ExternalSyntheticLambda81(this), new CTEffectContainerImpl$$ExternalSyntheticLambda82(this));
        }
        return javaListXmlObject;
    }

    public CTPresetShadowEffect[] getPrstShdwArray() {
        return getXmlObjectArray(PROPERTY_QNAME[24], (T[]) new CTPresetShadowEffect[0]);
    }

    public CTPresetShadowEffect getPrstShdwArray(int i) {
        CTPresetShadowEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[24], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfPrstShdwArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[24]);
        }
        return count_elements;
    }

    public void setPrstShdwArray(CTPresetShadowEffect[] cTPresetShadowEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTPresetShadowEffectArr, PROPERTY_QNAME[24]);
    }

    public void setPrstShdwArray(int i, CTPresetShadowEffect cTPresetShadowEffect) {
        generatedSetterHelperImpl(cTPresetShadowEffect, PROPERTY_QNAME[24], i, 2);
    }

    public CTPresetShadowEffect insertNewPrstShdw(int i) {
        CTPresetShadowEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[24], i);
        }
        return insert_element_user;
    }

    public CTPresetShadowEffect addNewPrstShdw() {
        CTPresetShadowEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[24]);
        }
        return add_element_user;
    }

    public void removePrstShdw(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[24], i);
        }
    }

    public List<CTReflectionEffect> getReflectionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda39(this), new CTEffectContainerImpl$$ExternalSyntheticLambda40(this), new CTEffectContainerImpl$$ExternalSyntheticLambda41(this), new CTEffectContainerImpl$$ExternalSyntheticLambda42(this), new CTEffectContainerImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTReflectionEffect[] getReflectionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[25], (T[]) new CTReflectionEffect[0]);
    }

    public CTReflectionEffect getReflectionArray(int i) {
        CTReflectionEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[25], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfReflectionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[25]);
        }
        return count_elements;
    }

    public void setReflectionArray(CTReflectionEffect[] cTReflectionEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTReflectionEffectArr, PROPERTY_QNAME[25]);
    }

    public void setReflectionArray(int i, CTReflectionEffect cTReflectionEffect) {
        generatedSetterHelperImpl(cTReflectionEffect, PROPERTY_QNAME[25], i, 2);
    }

    public CTReflectionEffect insertNewReflection(int i) {
        CTReflectionEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[25], i);
        }
        return insert_element_user;
    }

    public CTReflectionEffect addNewReflection() {
        CTReflectionEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[25]);
        }
        return add_element_user;
    }

    public void removeReflection(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[25], i);
        }
    }

    public List<CTRelativeOffsetEffect> getRelOffList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda139(this), new CTEffectContainerImpl$$ExternalSyntheticLambda140(this), new CTEffectContainerImpl$$ExternalSyntheticLambda141(this), new CTEffectContainerImpl$$ExternalSyntheticLambda142(this), new CTEffectContainerImpl$$ExternalSyntheticLambda143(this));
        }
        return javaListXmlObject;
    }

    public CTRelativeOffsetEffect[] getRelOffArray() {
        return getXmlObjectArray(PROPERTY_QNAME[26], (T[]) new CTRelativeOffsetEffect[0]);
    }

    public CTRelativeOffsetEffect getRelOffArray(int i) {
        CTRelativeOffsetEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[26], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfRelOffArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[26]);
        }
        return count_elements;
    }

    public void setRelOffArray(CTRelativeOffsetEffect[] cTRelativeOffsetEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTRelativeOffsetEffectArr, PROPERTY_QNAME[26]);
    }

    public void setRelOffArray(int i, CTRelativeOffsetEffect cTRelativeOffsetEffect) {
        generatedSetterHelperImpl(cTRelativeOffsetEffect, PROPERTY_QNAME[26], i, 2);
    }

    public CTRelativeOffsetEffect insertNewRelOff(int i) {
        CTRelativeOffsetEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[26], i);
        }
        return insert_element_user;
    }

    public CTRelativeOffsetEffect addNewRelOff() {
        CTRelativeOffsetEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[26]);
        }
        return add_element_user;
    }

    public void removeRelOff(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[26], i);
        }
    }

    public List<CTSoftEdgesEffect> getSoftEdgeList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda23(this), new CTEffectContainerImpl$$ExternalSyntheticLambda24(this), new CTEffectContainerImpl$$ExternalSyntheticLambda25(this), new CTEffectContainerImpl$$ExternalSyntheticLambda26(this), new CTEffectContainerImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTSoftEdgesEffect[] getSoftEdgeArray() {
        return getXmlObjectArray(PROPERTY_QNAME[27], (T[]) new CTSoftEdgesEffect[0]);
    }

    public CTSoftEdgesEffect getSoftEdgeArray(int i) {
        CTSoftEdgesEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[27], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfSoftEdgeArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[27]);
        }
        return count_elements;
    }

    public void setSoftEdgeArray(CTSoftEdgesEffect[] cTSoftEdgesEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTSoftEdgesEffectArr, PROPERTY_QNAME[27]);
    }

    public void setSoftEdgeArray(int i, CTSoftEdgesEffect cTSoftEdgesEffect) {
        generatedSetterHelperImpl(cTSoftEdgesEffect, PROPERTY_QNAME[27], i, 2);
    }

    public CTSoftEdgesEffect insertNewSoftEdge(int i) {
        CTSoftEdgesEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[27], i);
        }
        return insert_element_user;
    }

    public CTSoftEdgesEffect addNewSoftEdge() {
        CTSoftEdgesEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[27]);
        }
        return add_element_user;
    }

    public void removeSoftEdge(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[27], i);
        }
    }

    public List<CTTintEffect> getTintList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda28(this), new CTEffectContainerImpl$$ExternalSyntheticLambda29(this), new CTEffectContainerImpl$$ExternalSyntheticLambda30(this), new CTEffectContainerImpl$$ExternalSyntheticLambda31(this), new CTEffectContainerImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTTintEffect[] getTintArray() {
        return getXmlObjectArray(PROPERTY_QNAME[28], (T[]) new CTTintEffect[0]);
    }

    public CTTintEffect getTintArray(int i) {
        CTTintEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[28], i);
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
            count_elements = get_store().count_elements(PROPERTY_QNAME[28]);
        }
        return count_elements;
    }

    public void setTintArray(CTTintEffect[] cTTintEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTintEffectArr, PROPERTY_QNAME[28]);
    }

    public void setTintArray(int i, CTTintEffect cTTintEffect) {
        generatedSetterHelperImpl(cTTintEffect, PROPERTY_QNAME[28], i, 2);
    }

    public CTTintEffect insertNewTint(int i) {
        CTTintEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[28], i);
        }
        return insert_element_user;
    }

    public CTTintEffect addNewTint() {
        CTTintEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[28]);
        }
        return add_element_user;
    }

    public void removeTint(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[28], i);
        }
    }

    public List<CTTransformEffect> getXfrmList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTEffectContainerImpl$$ExternalSyntheticLambda45(this), new CTEffectContainerImpl$$ExternalSyntheticLambda46(this), new CTEffectContainerImpl$$ExternalSyntheticLambda47(this), new CTEffectContainerImpl$$ExternalSyntheticLambda48(this), new CTEffectContainerImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTTransformEffect[] getXfrmArray() {
        return getXmlObjectArray(PROPERTY_QNAME[29], (T[]) new CTTransformEffect[0]);
    }

    public CTTransformEffect getXfrmArray(int i) {
        CTTransformEffect find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[29], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfXfrmArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[29]);
        }
        return count_elements;
    }

    public void setXfrmArray(CTTransformEffect[] cTTransformEffectArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTransformEffectArr, PROPERTY_QNAME[29]);
    }

    public void setXfrmArray(int i, CTTransformEffect cTTransformEffect) {
        generatedSetterHelperImpl(cTTransformEffect, PROPERTY_QNAME[29], i, 2);
    }

    public CTTransformEffect insertNewXfrm(int i) {
        CTTransformEffect insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[29], i);
        }
        return insert_element_user;
    }

    public CTTransformEffect addNewXfrm() {
        CTTransformEffect add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[29]);
        }
        return add_element_user;
    }

    public void removeXfrm(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[29], i);
        }
    }

    /* JADX WARNING: type inference failed for: r5v6, types: [org.apache.xmlbeans.XmlAnySimpleType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum getType() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002f }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002f }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002f }
            r3 = 30
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
            org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum r5 = (org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum) r5     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return r5
        L_0x002f:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl.getType():org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum");
    }

    public STEffectContainerType xgetType() {
        STEffectContainerType find_attribute_user;
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            find_attribute_user = typeStore.find_attribute_user(qNameArr[30]);
            if (find_attribute_user == null) {
                find_attribute_user = get_default_attribute_value(qNameArr[30]);
            }
        }
        return find_attribute_user;
    }

    public boolean isSetType() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[30]) != null;
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setType(org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 30
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl.setType(org.openxmlformats.schemas.drawingml.x2006.main.STEffectContainerType$Enum):void");
    }

    public void xsetType(STEffectContainerType sTEffectContainerType) {
        synchronized (monitor()) {
            check_orphaned();
            TypeStore typeStore = get_store();
            QName[] qNameArr = PROPERTY_QNAME;
            STEffectContainerType find_attribute_user = typeStore.find_attribute_user(qNameArr[30]);
            if (find_attribute_user == null) {
                find_attribute_user = get_store().add_attribute_user(qNameArr[30]);
            }
            find_attribute_user.set(sTEffectContainerType);
        }
    }

    public void unsetType() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[30]);
        }
    }

    public String getName() {
        String str;
        synchronized (monitor()) {
            check_orphaned();
            SimpleValue simpleValue = (SimpleValue) get_store().find_attribute_user(PROPERTY_QNAME[31]);
            if (simpleValue == null) {
                str = null;
            } else {
                str = simpleValue.getStringValue();
            }
        }
        return str;
    }

    public XmlToken xgetName() {
        XmlToken xmlToken;
        synchronized (monitor()) {
            check_orphaned();
            xmlToken = (XmlToken) get_store().find_attribute_user(PROPERTY_QNAME[31]);
        }
        return xmlToken;
    }

    public boolean isSetName() {
        boolean z;
        synchronized (monitor()) {
            check_orphaned();
            z = get_store().find_attribute_user(PROPERTY_QNAME[31]) != null;
        }
        return z;
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
            r3 = 31
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
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl.setName(java.lang.String):void");
    }

    /* JADX WARNING: type inference failed for: r5v3, types: [org.apache.xmlbeans.impl.values.TypeStoreUser] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void xsetName(org.apache.xmlbeans.XmlToken r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.monitor()
            monitor-enter(r0)
            r5.check_orphaned()     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStore r1 = r5.get_store()     // Catch:{ all -> 0x002c }
            javax.xml.namespace.QName[] r2 = PROPERTY_QNAME     // Catch:{ all -> 0x002c }
            r3 = 31
            r4 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r1 = r1.find_attribute_user(r4)     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0027
            org.apache.xmlbeans.impl.values.TypeStore r5 = r5.get_store()     // Catch:{ all -> 0x002c }
            r1 = r2[r3]     // Catch:{ all -> 0x002c }
            org.apache.xmlbeans.impl.values.TypeStoreUser r5 = r5.add_attribute_user(r1)     // Catch:{ all -> 0x002c }
            r1 = r5
            org.apache.xmlbeans.XmlToken r1 = (org.apache.xmlbeans.XmlToken) r1     // Catch:{ all -> 0x002c }
        L_0x0027:
            r1.set(r6)     // Catch:{ all -> 0x002c }
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.openxmlformats.schemas.drawingml.x2006.main.impl.CTEffectContainerImpl.xsetName(org.apache.xmlbeans.XmlToken):void");
    }

    public void unsetName() {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_attribute(PROPERTY_QNAME[31]);
        }
    }
}
