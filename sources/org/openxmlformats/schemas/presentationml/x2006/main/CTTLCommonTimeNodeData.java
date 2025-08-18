package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.STPercentage;
import org.openxmlformats.schemas.drawingml.x2006.main.STPositiveFixedPercentage;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeFillType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeRestartType;
import org.openxmlformats.schemas.presentationml.x2006.main.STTLTimeNodeType;

public interface CTTLCommonTimeNodeData extends XmlObject {
    public static final DocumentFactory<CTTLCommonTimeNodeData> Factory;
    public static final SchemaType type;

    CTTimeNodeList addNewChildTnLst();

    CTTLTimeConditionList addNewEndCondLst();

    CTTLTimeCondition addNewEndSync();

    CTTLIterateData addNewIterate();

    CTTLTimeConditionList addNewStCondLst();

    CTTimeNodeList addNewSubTnLst();

    Object getAccel();

    boolean getAfterEffect();

    boolean getAutoRev();

    int getBldLvl();

    CTTimeNodeList getChildTnLst();

    Object getDecel();

    boolean getDisplay();

    Object getDur();

    CTTLTimeConditionList getEndCondLst();

    CTTLTimeCondition getEndSync();

    String getEvtFilter();

    STTLTimeNodeFillType.Enum getFill();

    long getGrpId();

    long getId();

    CTTLIterateData getIterate();

    STTLTimeNodeMasterRelation$Enum getMasterRel();

    boolean getNodePh();

    STTLTimeNodeType.Enum getNodeType();

    STTLTimeNodePresetClassType$Enum getPresetClass();

    int getPresetID();

    int getPresetSubtype();

    Object getRepeatCount();

    Object getRepeatDur();

    STTLTimeNodeRestartType.Enum getRestart();

    Object getSpd();

    CTTLTimeConditionList getStCondLst();

    CTTimeNodeList getSubTnLst();

    STTLTimeNodeSyncType$Enum getSyncBehavior();

    String getTmFilter();

    boolean isSetAccel();

    boolean isSetAfterEffect();

    boolean isSetAutoRev();

    boolean isSetBldLvl();

    boolean isSetChildTnLst();

    boolean isSetDecel();

    boolean isSetDisplay();

    boolean isSetDur();

    boolean isSetEndCondLst();

    boolean isSetEndSync();

    boolean isSetEvtFilter();

    boolean isSetFill();

    boolean isSetGrpId();

    boolean isSetId();

    boolean isSetIterate();

    boolean isSetMasterRel();

    boolean isSetNodePh();

    boolean isSetNodeType();

    boolean isSetPresetClass();

    boolean isSetPresetID();

    boolean isSetPresetSubtype();

    boolean isSetRepeatCount();

    boolean isSetRepeatDur();

    boolean isSetRestart();

    boolean isSetSpd();

    boolean isSetStCondLst();

    boolean isSetSubTnLst();

    boolean isSetSyncBehavior();

    boolean isSetTmFilter();

    void setAccel(Object obj);

    void setAfterEffect(boolean z);

    void setAutoRev(boolean z);

    void setBldLvl(int i);

    void setChildTnLst(CTTimeNodeList cTTimeNodeList);

    void setDecel(Object obj);

    void setDisplay(boolean z);

    void setDur(Object obj);

    void setEndCondLst(CTTLTimeConditionList cTTLTimeConditionList);

    void setEndSync(CTTLTimeCondition cTTLTimeCondition);

    void setEvtFilter(String str);

    void setFill(STTLTimeNodeFillType.Enum enumR);

    void setGrpId(long j);

    void setId(long j);

    void setIterate(CTTLIterateData cTTLIterateData);

    void setMasterRel(STTLTimeNodeMasterRelation$Enum sTTLTimeNodeMasterRelation$Enum);

    void setNodePh(boolean z);

    void setNodeType(STTLTimeNodeType.Enum enumR);

    void setPresetClass(STTLTimeNodePresetClassType$Enum sTTLTimeNodePresetClassType$Enum);

    void setPresetID(int i);

    void setPresetSubtype(int i);

    void setRepeatCount(Object obj);

    void setRepeatDur(Object obj);

    void setRestart(STTLTimeNodeRestartType.Enum enumR);

    void setSpd(Object obj);

    void setStCondLst(CTTLTimeConditionList cTTLTimeConditionList);

    void setSubTnLst(CTTimeNodeList cTTimeNodeList);

    void setSyncBehavior(STTLTimeNodeSyncType$Enum sTTLTimeNodeSyncType$Enum);

    void setTmFilter(String str);

    void unsetAccel();

    void unsetAfterEffect();

    void unsetAutoRev();

    void unsetBldLvl();

    void unsetChildTnLst();

    void unsetDecel();

    void unsetDisplay();

    void unsetDur();

    void unsetEndCondLst();

    void unsetEndSync();

    void unsetEvtFilter();

    void unsetFill();

    void unsetGrpId();

    void unsetId();

    void unsetIterate();

    void unsetMasterRel();

    void unsetNodePh();

    void unsetNodeType();

    void unsetPresetClass();

    void unsetPresetID();

    void unsetPresetSubtype();

    void unsetRepeatCount();

    void unsetRepeatDur();

    void unsetRestart();

    void unsetSpd();

    void unsetStCondLst();

    void unsetSubTnLst();

    void unsetSyncBehavior();

    void unsetTmFilter();

    STPositiveFixedPercentage xgetAccel();

    XmlBoolean xgetAfterEffect();

    XmlBoolean xgetAutoRev();

    XmlInt xgetBldLvl();

    STPositiveFixedPercentage xgetDecel();

    XmlBoolean xgetDisplay();

    STTLTime xgetDur();

    XmlString xgetEvtFilter();

    STTLTimeNodeFillType xgetFill();

    XmlUnsignedInt xgetGrpId();

    STTLTimeNodeID xgetId();

    STTLTimeNodeMasterRelation xgetMasterRel();

    XmlBoolean xgetNodePh();

    STTLTimeNodeType xgetNodeType();

    STTLTimeNodePresetClassType xgetPresetClass();

    XmlInt xgetPresetID();

    XmlInt xgetPresetSubtype();

    STTLTime xgetRepeatCount();

    STTLTime xgetRepeatDur();

    STTLTimeNodeRestartType xgetRestart();

    STPercentage xgetSpd();

    STTLTimeNodeSyncType xgetSyncBehavior();

    XmlString xgetTmFilter();

    void xsetAccel(STPositiveFixedPercentage sTPositiveFixedPercentage);

    void xsetAfterEffect(XmlBoolean xmlBoolean);

    void xsetAutoRev(XmlBoolean xmlBoolean);

    void xsetBldLvl(XmlInt xmlInt);

    void xsetDecel(STPositiveFixedPercentage sTPositiveFixedPercentage);

    void xsetDisplay(XmlBoolean xmlBoolean);

    void xsetDur(STTLTime sTTLTime);

    void xsetEvtFilter(XmlString xmlString);

    void xsetFill(STTLTimeNodeFillType sTTLTimeNodeFillType);

    void xsetGrpId(XmlUnsignedInt xmlUnsignedInt);

    void xsetId(STTLTimeNodeID sTTLTimeNodeID);

    void xsetMasterRel(STTLTimeNodeMasterRelation sTTLTimeNodeMasterRelation);

    void xsetNodePh(XmlBoolean xmlBoolean);

    void xsetNodeType(STTLTimeNodeType sTTLTimeNodeType);

    void xsetPresetClass(STTLTimeNodePresetClassType sTTLTimeNodePresetClassType);

    void xsetPresetID(XmlInt xmlInt);

    void xsetPresetSubtype(XmlInt xmlInt);

    void xsetRepeatCount(STTLTime sTTLTime);

    void xsetRepeatDur(STTLTime sTTLTime);

    void xsetRestart(STTLTimeNodeRestartType sTTLTimeNodeRestartType);

    void xsetSpd(STPercentage sTPercentage);

    void xsetSyncBehavior(STTLTimeNodeSyncType sTTLTimeNodeSyncType);

    void xsetTmFilter(XmlString xmlString);

    static {
        DocumentFactory<CTTLCommonTimeNodeData> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttlcommontimenodedatac8e9type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
