package org.openxmlformats.schemas.presentationml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTimeNodeList extends XmlObject {
    public static final DocumentFactory<CTTimeNodeList> Factory;
    public static final SchemaType type;

    CTTLAnimateBehavior addNewAnim();

    CTTLAnimateColorBehavior addNewAnimClr();

    CTTLAnimateEffectBehavior addNewAnimEffect();

    CTTLAnimateMotionBehavior addNewAnimMotion();

    CTTLAnimateRotationBehavior addNewAnimRot();

    CTTLAnimateScaleBehavior addNewAnimScale();

    CTTLMediaNodeAudio addNewAudio();

    CTTLCommandBehavior addNewCmd();

    CTTLTimeNodeExclusive addNewExcl();

    CTTLTimeNodeParallel addNewPar();

    CTTLTimeNodeSequence addNewSeq();

    CTTLSetBehavior addNewSet();

    CTTLMediaNodeVideo addNewVideo();

    CTTLAnimateBehavior getAnimArray(int i);

    CTTLAnimateBehavior[] getAnimArray();

    CTTLAnimateColorBehavior getAnimClrArray(int i);

    CTTLAnimateColorBehavior[] getAnimClrArray();

    List<CTTLAnimateColorBehavior> getAnimClrList();

    CTTLAnimateEffectBehavior getAnimEffectArray(int i);

    CTTLAnimateEffectBehavior[] getAnimEffectArray();

    List<CTTLAnimateEffectBehavior> getAnimEffectList();

    List<CTTLAnimateBehavior> getAnimList();

    CTTLAnimateMotionBehavior getAnimMotionArray(int i);

    CTTLAnimateMotionBehavior[] getAnimMotionArray();

    List<CTTLAnimateMotionBehavior> getAnimMotionList();

    CTTLAnimateRotationBehavior getAnimRotArray(int i);

    CTTLAnimateRotationBehavior[] getAnimRotArray();

    List<CTTLAnimateRotationBehavior> getAnimRotList();

    CTTLAnimateScaleBehavior getAnimScaleArray(int i);

    CTTLAnimateScaleBehavior[] getAnimScaleArray();

    List<CTTLAnimateScaleBehavior> getAnimScaleList();

    CTTLMediaNodeAudio getAudioArray(int i);

    CTTLMediaNodeAudio[] getAudioArray();

    List<CTTLMediaNodeAudio> getAudioList();

    CTTLCommandBehavior getCmdArray(int i);

    CTTLCommandBehavior[] getCmdArray();

    List<CTTLCommandBehavior> getCmdList();

    CTTLTimeNodeExclusive getExclArray(int i);

    CTTLTimeNodeExclusive[] getExclArray();

    List<CTTLTimeNodeExclusive> getExclList();

    CTTLTimeNodeParallel getParArray(int i);

    CTTLTimeNodeParallel[] getParArray();

    List<CTTLTimeNodeParallel> getParList();

    CTTLTimeNodeSequence getSeqArray(int i);

    CTTLTimeNodeSequence[] getSeqArray();

    List<CTTLTimeNodeSequence> getSeqList();

    CTTLSetBehavior getSetArray(int i);

    CTTLSetBehavior[] getSetArray();

    List<CTTLSetBehavior> getSetList();

    CTTLMediaNodeVideo getVideoArray(int i);

    CTTLMediaNodeVideo[] getVideoArray();

    List<CTTLMediaNodeVideo> getVideoList();

    CTTLAnimateBehavior insertNewAnim(int i);

    CTTLAnimateColorBehavior insertNewAnimClr(int i);

    CTTLAnimateEffectBehavior insertNewAnimEffect(int i);

    CTTLAnimateMotionBehavior insertNewAnimMotion(int i);

    CTTLAnimateRotationBehavior insertNewAnimRot(int i);

    CTTLAnimateScaleBehavior insertNewAnimScale(int i);

    CTTLMediaNodeAudio insertNewAudio(int i);

    CTTLCommandBehavior insertNewCmd(int i);

    CTTLTimeNodeExclusive insertNewExcl(int i);

    CTTLTimeNodeParallel insertNewPar(int i);

    CTTLTimeNodeSequence insertNewSeq(int i);

    CTTLSetBehavior insertNewSet(int i);

    CTTLMediaNodeVideo insertNewVideo(int i);

    void removeAnim(int i);

    void removeAnimClr(int i);

    void removeAnimEffect(int i);

    void removeAnimMotion(int i);

    void removeAnimRot(int i);

    void removeAnimScale(int i);

    void removeAudio(int i);

    void removeCmd(int i);

    void removeExcl(int i);

    void removePar(int i);

    void removeSeq(int i);

    void removeSet(int i);

    void removeVideo(int i);

    void setAnimArray(int i, CTTLAnimateBehavior cTTLAnimateBehavior);

    void setAnimArray(CTTLAnimateBehavior[] cTTLAnimateBehaviorArr);

    void setAnimClrArray(int i, CTTLAnimateColorBehavior cTTLAnimateColorBehavior);

    void setAnimClrArray(CTTLAnimateColorBehavior[] cTTLAnimateColorBehaviorArr);

    void setAnimEffectArray(int i, CTTLAnimateEffectBehavior cTTLAnimateEffectBehavior);

    void setAnimEffectArray(CTTLAnimateEffectBehavior[] cTTLAnimateEffectBehaviorArr);

    void setAnimMotionArray(int i, CTTLAnimateMotionBehavior cTTLAnimateMotionBehavior);

    void setAnimMotionArray(CTTLAnimateMotionBehavior[] cTTLAnimateMotionBehaviorArr);

    void setAnimRotArray(int i, CTTLAnimateRotationBehavior cTTLAnimateRotationBehavior);

    void setAnimRotArray(CTTLAnimateRotationBehavior[] cTTLAnimateRotationBehaviorArr);

    void setAnimScaleArray(int i, CTTLAnimateScaleBehavior cTTLAnimateScaleBehavior);

    void setAnimScaleArray(CTTLAnimateScaleBehavior[] cTTLAnimateScaleBehaviorArr);

    void setAudioArray(int i, CTTLMediaNodeAudio cTTLMediaNodeAudio);

    void setAudioArray(CTTLMediaNodeAudio[] cTTLMediaNodeAudioArr);

    void setCmdArray(int i, CTTLCommandBehavior cTTLCommandBehavior);

    void setCmdArray(CTTLCommandBehavior[] cTTLCommandBehaviorArr);

    void setExclArray(int i, CTTLTimeNodeExclusive cTTLTimeNodeExclusive);

    void setExclArray(CTTLTimeNodeExclusive[] cTTLTimeNodeExclusiveArr);

    void setParArray(int i, CTTLTimeNodeParallel cTTLTimeNodeParallel);

    void setParArray(CTTLTimeNodeParallel[] cTTLTimeNodeParallelArr);

    void setSeqArray(int i, CTTLTimeNodeSequence cTTLTimeNodeSequence);

    void setSeqArray(CTTLTimeNodeSequence[] cTTLTimeNodeSequenceArr);

    void setSetArray(int i, CTTLSetBehavior cTTLSetBehavior);

    void setSetArray(CTTLSetBehavior[] cTTLSetBehaviorArr);

    void setVideoArray(int i, CTTLMediaNodeVideo cTTLMediaNodeVideo);

    void setVideoArray(CTTLMediaNodeVideo[] cTTLMediaNodeVideoArr);

    int sizeOfAnimArray();

    int sizeOfAnimClrArray();

    int sizeOfAnimEffectArray();

    int sizeOfAnimMotionArray();

    int sizeOfAnimRotArray();

    int sizeOfAnimScaleArray();

    int sizeOfAudioArray();

    int sizeOfCmdArray();

    int sizeOfExclArray();

    int sizeOfParArray();

    int sizeOfSeqArray();

    int sizeOfSetArray();

    int sizeOfVideoArray();

    static {
        DocumentFactory<CTTimeNodeList> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttimenodelist0258type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
