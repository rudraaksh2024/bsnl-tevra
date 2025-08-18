package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTEffectContainer extends XmlObject {
    public static final DocumentFactory<CTEffectContainer> Factory;
    public static final SchemaType type;

    CTAlphaBiLevelEffect addNewAlphaBiLevel();

    CTAlphaCeilingEffect addNewAlphaCeiling();

    CTAlphaFloorEffect addNewAlphaFloor();

    CTAlphaInverseEffect addNewAlphaInv();

    CTAlphaModulateEffect addNewAlphaMod();

    CTAlphaModulateFixedEffect addNewAlphaModFix();

    CTAlphaOutsetEffect addNewAlphaOutset();

    CTAlphaReplaceEffect addNewAlphaRepl();

    CTBiLevelEffect addNewBiLevel();

    CTBlendEffect addNewBlend();

    CTBlurEffect addNewBlur();

    CTColorChangeEffect addNewClrChange();

    CTColorReplaceEffect addNewClrRepl();

    CTEffectContainer addNewCont();

    CTDuotoneEffect addNewDuotone();

    CTEffectReference addNewEffect();

    CTFillEffect addNewFill();

    CTFillOverlayEffect addNewFillOverlay();

    CTGlowEffect addNewGlow();

    CTGrayscaleEffect addNewGrayscl();

    CTHSLEffect addNewHsl();

    CTInnerShadowEffect addNewInnerShdw();

    CTLuminanceEffect addNewLum();

    CTOuterShadowEffect addNewOuterShdw();

    CTPresetShadowEffect addNewPrstShdw();

    CTReflectionEffect addNewReflection();

    CTRelativeOffsetEffect addNewRelOff();

    CTSoftEdgesEffect addNewSoftEdge();

    CTTintEffect addNewTint();

    CTTransformEffect addNewXfrm();

    CTAlphaBiLevelEffect getAlphaBiLevelArray(int i);

    CTAlphaBiLevelEffect[] getAlphaBiLevelArray();

    List<CTAlphaBiLevelEffect> getAlphaBiLevelList();

    CTAlphaCeilingEffect getAlphaCeilingArray(int i);

    CTAlphaCeilingEffect[] getAlphaCeilingArray();

    List<CTAlphaCeilingEffect> getAlphaCeilingList();

    CTAlphaFloorEffect getAlphaFloorArray(int i);

    CTAlphaFloorEffect[] getAlphaFloorArray();

    List<CTAlphaFloorEffect> getAlphaFloorList();

    CTAlphaInverseEffect getAlphaInvArray(int i);

    CTAlphaInverseEffect[] getAlphaInvArray();

    List<CTAlphaInverseEffect> getAlphaInvList();

    CTAlphaModulateEffect getAlphaModArray(int i);

    CTAlphaModulateEffect[] getAlphaModArray();

    CTAlphaModulateFixedEffect getAlphaModFixArray(int i);

    CTAlphaModulateFixedEffect[] getAlphaModFixArray();

    List<CTAlphaModulateFixedEffect> getAlphaModFixList();

    List<CTAlphaModulateEffect> getAlphaModList();

    CTAlphaOutsetEffect getAlphaOutsetArray(int i);

    CTAlphaOutsetEffect[] getAlphaOutsetArray();

    List<CTAlphaOutsetEffect> getAlphaOutsetList();

    CTAlphaReplaceEffect getAlphaReplArray(int i);

    CTAlphaReplaceEffect[] getAlphaReplArray();

    List<CTAlphaReplaceEffect> getAlphaReplList();

    CTBiLevelEffect getBiLevelArray(int i);

    CTBiLevelEffect[] getBiLevelArray();

    List<CTBiLevelEffect> getBiLevelList();

    CTBlendEffect getBlendArray(int i);

    CTBlendEffect[] getBlendArray();

    List<CTBlendEffect> getBlendList();

    CTBlurEffect getBlurArray(int i);

    CTBlurEffect[] getBlurArray();

    List<CTBlurEffect> getBlurList();

    CTColorChangeEffect getClrChangeArray(int i);

    CTColorChangeEffect[] getClrChangeArray();

    List<CTColorChangeEffect> getClrChangeList();

    CTColorReplaceEffect getClrReplArray(int i);

    CTColorReplaceEffect[] getClrReplArray();

    List<CTColorReplaceEffect> getClrReplList();

    CTEffectContainer getContArray(int i);

    CTEffectContainer[] getContArray();

    List<CTEffectContainer> getContList();

    CTDuotoneEffect getDuotoneArray(int i);

    CTDuotoneEffect[] getDuotoneArray();

    List<CTDuotoneEffect> getDuotoneList();

    CTEffectReference getEffectArray(int i);

    CTEffectReference[] getEffectArray();

    List<CTEffectReference> getEffectList();

    CTFillEffect getFillArray(int i);

    CTFillEffect[] getFillArray();

    List<CTFillEffect> getFillList();

    CTFillOverlayEffect getFillOverlayArray(int i);

    CTFillOverlayEffect[] getFillOverlayArray();

    List<CTFillOverlayEffect> getFillOverlayList();

    CTGlowEffect getGlowArray(int i);

    CTGlowEffect[] getGlowArray();

    List<CTGlowEffect> getGlowList();

    CTGrayscaleEffect getGraysclArray(int i);

    CTGrayscaleEffect[] getGraysclArray();

    List<CTGrayscaleEffect> getGraysclList();

    CTHSLEffect getHslArray(int i);

    CTHSLEffect[] getHslArray();

    List<CTHSLEffect> getHslList();

    CTInnerShadowEffect getInnerShdwArray(int i);

    CTInnerShadowEffect[] getInnerShdwArray();

    List<CTInnerShadowEffect> getInnerShdwList();

    CTLuminanceEffect getLumArray(int i);

    CTLuminanceEffect[] getLumArray();

    List<CTLuminanceEffect> getLumList();

    String getName();

    CTOuterShadowEffect getOuterShdwArray(int i);

    CTOuterShadowEffect[] getOuterShdwArray();

    List<CTOuterShadowEffect> getOuterShdwList();

    CTPresetShadowEffect getPrstShdwArray(int i);

    CTPresetShadowEffect[] getPrstShdwArray();

    List<CTPresetShadowEffect> getPrstShdwList();

    CTReflectionEffect getReflectionArray(int i);

    CTReflectionEffect[] getReflectionArray();

    List<CTReflectionEffect> getReflectionList();

    CTRelativeOffsetEffect getRelOffArray(int i);

    CTRelativeOffsetEffect[] getRelOffArray();

    List<CTRelativeOffsetEffect> getRelOffList();

    CTSoftEdgesEffect getSoftEdgeArray(int i);

    CTSoftEdgesEffect[] getSoftEdgeArray();

    List<CTSoftEdgesEffect> getSoftEdgeList();

    CTTintEffect getTintArray(int i);

    CTTintEffect[] getTintArray();

    List<CTTintEffect> getTintList();

    STEffectContainerType$Enum getType();

    CTTransformEffect getXfrmArray(int i);

    CTTransformEffect[] getXfrmArray();

    List<CTTransformEffect> getXfrmList();

    CTAlphaBiLevelEffect insertNewAlphaBiLevel(int i);

    CTAlphaCeilingEffect insertNewAlphaCeiling(int i);

    CTAlphaFloorEffect insertNewAlphaFloor(int i);

    CTAlphaInverseEffect insertNewAlphaInv(int i);

    CTAlphaModulateEffect insertNewAlphaMod(int i);

    CTAlphaModulateFixedEffect insertNewAlphaModFix(int i);

    CTAlphaOutsetEffect insertNewAlphaOutset(int i);

    CTAlphaReplaceEffect insertNewAlphaRepl(int i);

    CTBiLevelEffect insertNewBiLevel(int i);

    CTBlendEffect insertNewBlend(int i);

    CTBlurEffect insertNewBlur(int i);

    CTColorChangeEffect insertNewClrChange(int i);

    CTColorReplaceEffect insertNewClrRepl(int i);

    CTEffectContainer insertNewCont(int i);

    CTDuotoneEffect insertNewDuotone(int i);

    CTEffectReference insertNewEffect(int i);

    CTFillEffect insertNewFill(int i);

    CTFillOverlayEffect insertNewFillOverlay(int i);

    CTGlowEffect insertNewGlow(int i);

    CTGrayscaleEffect insertNewGrayscl(int i);

    CTHSLEffect insertNewHsl(int i);

    CTInnerShadowEffect insertNewInnerShdw(int i);

    CTLuminanceEffect insertNewLum(int i);

    CTOuterShadowEffect insertNewOuterShdw(int i);

    CTPresetShadowEffect insertNewPrstShdw(int i);

    CTReflectionEffect insertNewReflection(int i);

    CTRelativeOffsetEffect insertNewRelOff(int i);

    CTSoftEdgesEffect insertNewSoftEdge(int i);

    CTTintEffect insertNewTint(int i);

    CTTransformEffect insertNewXfrm(int i);

    boolean isSetName();

    boolean isSetType();

    void removeAlphaBiLevel(int i);

    void removeAlphaCeiling(int i);

    void removeAlphaFloor(int i);

    void removeAlphaInv(int i);

    void removeAlphaMod(int i);

    void removeAlphaModFix(int i);

    void removeAlphaOutset(int i);

    void removeAlphaRepl(int i);

    void removeBiLevel(int i);

    void removeBlend(int i);

    void removeBlur(int i);

    void removeClrChange(int i);

    void removeClrRepl(int i);

    void removeCont(int i);

    void removeDuotone(int i);

    void removeEffect(int i);

    void removeFill(int i);

    void removeFillOverlay(int i);

    void removeGlow(int i);

    void removeGrayscl(int i);

    void removeHsl(int i);

    void removeInnerShdw(int i);

    void removeLum(int i);

    void removeOuterShdw(int i);

    void removePrstShdw(int i);

    void removeReflection(int i);

    void removeRelOff(int i);

    void removeSoftEdge(int i);

    void removeTint(int i);

    void removeXfrm(int i);

    void setAlphaBiLevelArray(int i, CTAlphaBiLevelEffect cTAlphaBiLevelEffect);

    void setAlphaBiLevelArray(CTAlphaBiLevelEffect[] cTAlphaBiLevelEffectArr);

    void setAlphaCeilingArray(int i, CTAlphaCeilingEffect cTAlphaCeilingEffect);

    void setAlphaCeilingArray(CTAlphaCeilingEffect[] cTAlphaCeilingEffectArr);

    void setAlphaFloorArray(int i, CTAlphaFloorEffect cTAlphaFloorEffect);

    void setAlphaFloorArray(CTAlphaFloorEffect[] cTAlphaFloorEffectArr);

    void setAlphaInvArray(int i, CTAlphaInverseEffect cTAlphaInverseEffect);

    void setAlphaInvArray(CTAlphaInverseEffect[] cTAlphaInverseEffectArr);

    void setAlphaModArray(int i, CTAlphaModulateEffect cTAlphaModulateEffect);

    void setAlphaModArray(CTAlphaModulateEffect[] cTAlphaModulateEffectArr);

    void setAlphaModFixArray(int i, CTAlphaModulateFixedEffect cTAlphaModulateFixedEffect);

    void setAlphaModFixArray(CTAlphaModulateFixedEffect[] cTAlphaModulateFixedEffectArr);

    void setAlphaOutsetArray(int i, CTAlphaOutsetEffect cTAlphaOutsetEffect);

    void setAlphaOutsetArray(CTAlphaOutsetEffect[] cTAlphaOutsetEffectArr);

    void setAlphaReplArray(int i, CTAlphaReplaceEffect cTAlphaReplaceEffect);

    void setAlphaReplArray(CTAlphaReplaceEffect[] cTAlphaReplaceEffectArr);

    void setBiLevelArray(int i, CTBiLevelEffect cTBiLevelEffect);

    void setBiLevelArray(CTBiLevelEffect[] cTBiLevelEffectArr);

    void setBlendArray(int i, CTBlendEffect cTBlendEffect);

    void setBlendArray(CTBlendEffect[] cTBlendEffectArr);

    void setBlurArray(int i, CTBlurEffect cTBlurEffect);

    void setBlurArray(CTBlurEffect[] cTBlurEffectArr);

    void setClrChangeArray(int i, CTColorChangeEffect cTColorChangeEffect);

    void setClrChangeArray(CTColorChangeEffect[] cTColorChangeEffectArr);

    void setClrReplArray(int i, CTColorReplaceEffect cTColorReplaceEffect);

    void setClrReplArray(CTColorReplaceEffect[] cTColorReplaceEffectArr);

    void setContArray(int i, CTEffectContainer cTEffectContainer);

    void setContArray(CTEffectContainer[] cTEffectContainerArr);

    void setDuotoneArray(int i, CTDuotoneEffect cTDuotoneEffect);

    void setDuotoneArray(CTDuotoneEffect[] cTDuotoneEffectArr);

    void setEffectArray(int i, CTEffectReference cTEffectReference);

    void setEffectArray(CTEffectReference[] cTEffectReferenceArr);

    void setFillArray(int i, CTFillEffect cTFillEffect);

    void setFillArray(CTFillEffect[] cTFillEffectArr);

    void setFillOverlayArray(int i, CTFillOverlayEffect cTFillOverlayEffect);

    void setFillOverlayArray(CTFillOverlayEffect[] cTFillOverlayEffectArr);

    void setGlowArray(int i, CTGlowEffect cTGlowEffect);

    void setGlowArray(CTGlowEffect[] cTGlowEffectArr);

    void setGraysclArray(int i, CTGrayscaleEffect cTGrayscaleEffect);

    void setGraysclArray(CTGrayscaleEffect[] cTGrayscaleEffectArr);

    void setHslArray(int i, CTHSLEffect cTHSLEffect);

    void setHslArray(CTHSLEffect[] cTHSLEffectArr);

    void setInnerShdwArray(int i, CTInnerShadowEffect cTInnerShadowEffect);

    void setInnerShdwArray(CTInnerShadowEffect[] cTInnerShadowEffectArr);

    void setLumArray(int i, CTLuminanceEffect cTLuminanceEffect);

    void setLumArray(CTLuminanceEffect[] cTLuminanceEffectArr);

    void setName(String str);

    void setOuterShdwArray(int i, CTOuterShadowEffect cTOuterShadowEffect);

    void setOuterShdwArray(CTOuterShadowEffect[] cTOuterShadowEffectArr);

    void setPrstShdwArray(int i, CTPresetShadowEffect cTPresetShadowEffect);

    void setPrstShdwArray(CTPresetShadowEffect[] cTPresetShadowEffectArr);

    void setReflectionArray(int i, CTReflectionEffect cTReflectionEffect);

    void setReflectionArray(CTReflectionEffect[] cTReflectionEffectArr);

    void setRelOffArray(int i, CTRelativeOffsetEffect cTRelativeOffsetEffect);

    void setRelOffArray(CTRelativeOffsetEffect[] cTRelativeOffsetEffectArr);

    void setSoftEdgeArray(int i, CTSoftEdgesEffect cTSoftEdgesEffect);

    void setSoftEdgeArray(CTSoftEdgesEffect[] cTSoftEdgesEffectArr);

    void setTintArray(int i, CTTintEffect cTTintEffect);

    void setTintArray(CTTintEffect[] cTTintEffectArr);

    void setType(STEffectContainerType$Enum sTEffectContainerType$Enum);

    void setXfrmArray(int i, CTTransformEffect cTTransformEffect);

    void setXfrmArray(CTTransformEffect[] cTTransformEffectArr);

    int sizeOfAlphaBiLevelArray();

    int sizeOfAlphaCeilingArray();

    int sizeOfAlphaFloorArray();

    int sizeOfAlphaInvArray();

    int sizeOfAlphaModArray();

    int sizeOfAlphaModFixArray();

    int sizeOfAlphaOutsetArray();

    int sizeOfAlphaReplArray();

    int sizeOfBiLevelArray();

    int sizeOfBlendArray();

    int sizeOfBlurArray();

    int sizeOfClrChangeArray();

    int sizeOfClrReplArray();

    int sizeOfContArray();

    int sizeOfDuotoneArray();

    int sizeOfEffectArray();

    int sizeOfFillArray();

    int sizeOfFillOverlayArray();

    int sizeOfGlowArray();

    int sizeOfGraysclArray();

    int sizeOfHslArray();

    int sizeOfInnerShdwArray();

    int sizeOfLumArray();

    int sizeOfOuterShdwArray();

    int sizeOfPrstShdwArray();

    int sizeOfReflectionArray();

    int sizeOfRelOffArray();

    int sizeOfSoftEdgeArray();

    int sizeOfTintArray();

    int sizeOfXfrmArray();

    void unsetName();

    void unsetType();

    XmlToken xgetName();

    STEffectContainerType xgetType();

    void xsetName(XmlToken xmlToken);

    void xsetType(STEffectContainerType sTEffectContainerType);

    static {
        DocumentFactory<CTEffectContainer> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cteffectcontainer2e21type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
