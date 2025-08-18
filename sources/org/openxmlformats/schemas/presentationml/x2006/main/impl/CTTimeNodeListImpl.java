package org.openxmlformats.schemas.presentationml.x2006.main.impl;

import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaListXmlObject;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateColorBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateEffectBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateMotionBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateRotationBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLAnimateScaleBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLCommandBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeAudio;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLMediaNodeVideo;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLSetBehavior;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeExclusive;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeParallel;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTLTimeNodeSequence;
import org.openxmlformats.schemas.presentationml.x2006.main.CTTimeNodeList;

public class CTTimeNodeListImpl extends XmlComplexContentImpl implements CTTimeNodeList {
    private static final QName[] PROPERTY_QNAME = {new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "par"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "seq"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "excl"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "anim"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "animClr"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "animEffect"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "animMotion"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "animRot"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "animScale"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "cmd"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "set"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "audio"), new QName("http://schemas.openxmlformats.org/presentationml/2006/main", "video")};
    private static final long serialVersionUID = 1;

    public CTTimeNodeListImpl(SchemaType schemaType) {
        super(schemaType);
    }

    public List<CTTLTimeNodeParallel> getParList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda50(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda51(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda52(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda53(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda54(this));
        }
        return javaListXmlObject;
    }

    public CTTLTimeNodeParallel[] getParArray() {
        return (CTTLTimeNodeParallel[]) getXmlObjectArray(PROPERTY_QNAME[0], (T[]) new CTTLTimeNodeParallel[0]);
    }

    public CTTLTimeNodeParallel getParArray(int i) {
        CTTLTimeNodeParallel cTTLTimeNodeParallel;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeNodeParallel = (CTTLTimeNodeParallel) get_store().find_element_user(PROPERTY_QNAME[0], i);
            if (cTTLTimeNodeParallel == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTLTimeNodeParallel;
    }

    public int sizeOfParArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[0]);
        }
        return count_elements;
    }

    public void setParArray(CTTLTimeNodeParallel[] cTTLTimeNodeParallelArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLTimeNodeParallelArr, PROPERTY_QNAME[0]);
    }

    public void setParArray(int i, CTTLTimeNodeParallel cTTLTimeNodeParallel) {
        generatedSetterHelperImpl(cTTLTimeNodeParallel, PROPERTY_QNAME[0], i, 2);
    }

    public CTTLTimeNodeParallel insertNewPar(int i) {
        CTTLTimeNodeParallel cTTLTimeNodeParallel;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeNodeParallel = (CTTLTimeNodeParallel) get_store().insert_element_user(PROPERTY_QNAME[0], i);
        }
        return cTTLTimeNodeParallel;
    }

    public CTTLTimeNodeParallel addNewPar() {
        CTTLTimeNodeParallel cTTLTimeNodeParallel;
        synchronized (monitor()) {
            check_orphaned();
            cTTLTimeNodeParallel = (CTTLTimeNodeParallel) get_store().add_element_user(PROPERTY_QNAME[0]);
        }
        return cTTLTimeNodeParallel;
    }

    public void removePar(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[0], i);
        }
    }

    public List<CTTLTimeNodeSequence> getSeqList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda0(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda11(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda22(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda33(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda44(this));
        }
        return javaListXmlObject;
    }

    public CTTLTimeNodeSequence[] getSeqArray() {
        return getXmlObjectArray(PROPERTY_QNAME[1], (T[]) new CTTLTimeNodeSequence[0]);
    }

    public CTTLTimeNodeSequence getSeqArray(int i) {
        CTTLTimeNodeSequence find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[1], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfSeqArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[1]);
        }
        return count_elements;
    }

    public void setSeqArray(CTTLTimeNodeSequence[] cTTLTimeNodeSequenceArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLTimeNodeSequenceArr, PROPERTY_QNAME[1]);
    }

    public void setSeqArray(int i, CTTLTimeNodeSequence cTTLTimeNodeSequence) {
        generatedSetterHelperImpl(cTTLTimeNodeSequence, PROPERTY_QNAME[1], i, 2);
    }

    public CTTLTimeNodeSequence insertNewSeq(int i) {
        CTTLTimeNodeSequence insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[1], i);
        }
        return insert_element_user;
    }

    public CTTLTimeNodeSequence addNewSeq() {
        CTTLTimeNodeSequence add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[1]);
        }
        return add_element_user;
    }

    public void removeSeq(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[1], i);
        }
    }

    public List<CTTLTimeNodeExclusive> getExclList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda6(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda7(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda8(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda9(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda10(this));
        }
        return javaListXmlObject;
    }

    public CTTLTimeNodeExclusive[] getExclArray() {
        return getXmlObjectArray(PROPERTY_QNAME[2], (T[]) new CTTLTimeNodeExclusive[0]);
    }

    public CTTLTimeNodeExclusive getExclArray(int i) {
        CTTLTimeNodeExclusive find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[2], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfExclArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[2]);
        }
        return count_elements;
    }

    public void setExclArray(CTTLTimeNodeExclusive[] cTTLTimeNodeExclusiveArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLTimeNodeExclusiveArr, PROPERTY_QNAME[2]);
    }

    public void setExclArray(int i, CTTLTimeNodeExclusive cTTLTimeNodeExclusive) {
        generatedSetterHelperImpl(cTTLTimeNodeExclusive, PROPERTY_QNAME[2], i, 2);
    }

    public CTTLTimeNodeExclusive insertNewExcl(int i) {
        CTTLTimeNodeExclusive insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[2], i);
        }
        return insert_element_user;
    }

    public CTTLTimeNodeExclusive addNewExcl() {
        CTTLTimeNodeExclusive add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[2]);
        }
        return add_element_user;
    }

    public void removeExcl(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[2], i);
        }
    }

    public List<CTTLAnimateBehavior> getAnimList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda28(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda29(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda30(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda31(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda32(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateBehavior[] getAnimArray() {
        return getXmlObjectArray(PROPERTY_QNAME[3], (T[]) new CTTLAnimateBehavior[0]);
    }

    public CTTLAnimateBehavior getAnimArray(int i) {
        CTTLAnimateBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[3], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[3]);
        }
        return count_elements;
    }

    public void setAnimArray(CTTLAnimateBehavior[] cTTLAnimateBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateBehaviorArr, PROPERTY_QNAME[3]);
    }

    public void setAnimArray(int i, CTTLAnimateBehavior cTTLAnimateBehavior) {
        generatedSetterHelperImpl(cTTLAnimateBehavior, PROPERTY_QNAME[3], i, 2);
    }

    public CTTLAnimateBehavior insertNewAnim(int i) {
        CTTLAnimateBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[3], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateBehavior addNewAnim() {
        CTTLAnimateBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[3]);
        }
        return add_element_user;
    }

    public void removeAnim(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[3], i);
        }
    }

    public List<CTTLAnimateColorBehavior> getAnimClrList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda34(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda35(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda36(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda37(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda38(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateColorBehavior[] getAnimClrArray() {
        return getXmlObjectArray(PROPERTY_QNAME[4], (T[]) new CTTLAnimateColorBehavior[0]);
    }

    public CTTLAnimateColorBehavior getAnimClrArray(int i) {
        CTTLAnimateColorBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[4], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimClrArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[4]);
        }
        return count_elements;
    }

    public void setAnimClrArray(CTTLAnimateColorBehavior[] cTTLAnimateColorBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateColorBehaviorArr, PROPERTY_QNAME[4]);
    }

    public void setAnimClrArray(int i, CTTLAnimateColorBehavior cTTLAnimateColorBehavior) {
        generatedSetterHelperImpl(cTTLAnimateColorBehavior, PROPERTY_QNAME[4], i, 2);
    }

    public CTTLAnimateColorBehavior insertNewAnimClr(int i) {
        CTTLAnimateColorBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[4], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateColorBehavior addNewAnimClr() {
        CTTLAnimateColorBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[4]);
        }
        return add_element_user;
    }

    public void removeAnimClr(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[4], i);
        }
    }

    public List<CTTLAnimateEffectBehavior> getAnimEffectList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda39(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda40(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda41(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda42(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda43(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateEffectBehavior[] getAnimEffectArray() {
        return getXmlObjectArray(PROPERTY_QNAME[5], (T[]) new CTTLAnimateEffectBehavior[0]);
    }

    public CTTLAnimateEffectBehavior getAnimEffectArray(int i) {
        CTTLAnimateEffectBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[5], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimEffectArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[5]);
        }
        return count_elements;
    }

    public void setAnimEffectArray(CTTLAnimateEffectBehavior[] cTTLAnimateEffectBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateEffectBehaviorArr, PROPERTY_QNAME[5]);
    }

    public void setAnimEffectArray(int i, CTTLAnimateEffectBehavior cTTLAnimateEffectBehavior) {
        generatedSetterHelperImpl(cTTLAnimateEffectBehavior, PROPERTY_QNAME[5], i, 2);
    }

    public CTTLAnimateEffectBehavior insertNewAnimEffect(int i) {
        CTTLAnimateEffectBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[5], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateEffectBehavior addNewAnimEffect() {
        CTTLAnimateEffectBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[5]);
        }
        return add_element_user;
    }

    public void removeAnimEffect(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[5], i);
        }
    }

    public List<CTTLAnimateMotionBehavior> getAnimMotionList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda23(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda24(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda25(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda26(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda27(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateMotionBehavior[] getAnimMotionArray() {
        return getXmlObjectArray(PROPERTY_QNAME[6], (T[]) new CTTLAnimateMotionBehavior[0]);
    }

    public CTTLAnimateMotionBehavior getAnimMotionArray(int i) {
        CTTLAnimateMotionBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[6], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimMotionArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[6]);
        }
        return count_elements;
    }

    public void setAnimMotionArray(CTTLAnimateMotionBehavior[] cTTLAnimateMotionBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateMotionBehaviorArr, PROPERTY_QNAME[6]);
    }

    public void setAnimMotionArray(int i, CTTLAnimateMotionBehavior cTTLAnimateMotionBehavior) {
        generatedSetterHelperImpl(cTTLAnimateMotionBehavior, PROPERTY_QNAME[6], i, 2);
    }

    public CTTLAnimateMotionBehavior insertNewAnimMotion(int i) {
        CTTLAnimateMotionBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[6], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateMotionBehavior addNewAnimMotion() {
        CTTLAnimateMotionBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[6]);
        }
        return add_element_user;
    }

    public void removeAnimMotion(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[6], i);
        }
    }

    public List<CTTLAnimateRotationBehavior> getAnimRotList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda45(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda46(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda47(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda48(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda49(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateRotationBehavior[] getAnimRotArray() {
        return getXmlObjectArray(PROPERTY_QNAME[7], (T[]) new CTTLAnimateRotationBehavior[0]);
    }

    public CTTLAnimateRotationBehavior getAnimRotArray(int i) {
        CTTLAnimateRotationBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[7], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimRotArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[7]);
        }
        return count_elements;
    }

    public void setAnimRotArray(CTTLAnimateRotationBehavior[] cTTLAnimateRotationBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateRotationBehaviorArr, PROPERTY_QNAME[7]);
    }

    public void setAnimRotArray(int i, CTTLAnimateRotationBehavior cTTLAnimateRotationBehavior) {
        generatedSetterHelperImpl(cTTLAnimateRotationBehavior, PROPERTY_QNAME[7], i, 2);
    }

    public CTTLAnimateRotationBehavior insertNewAnimRot(int i) {
        CTTLAnimateRotationBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[7], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateRotationBehavior addNewAnimRot() {
        CTTLAnimateRotationBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[7]);
        }
        return add_element_user;
    }

    public void removeAnimRot(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[7], i);
        }
    }

    public List<CTTLAnimateScaleBehavior> getAnimScaleList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda1(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda2(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda3(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda4(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda5(this));
        }
        return javaListXmlObject;
    }

    public CTTLAnimateScaleBehavior[] getAnimScaleArray() {
        return getXmlObjectArray(PROPERTY_QNAME[8], (T[]) new CTTLAnimateScaleBehavior[0]);
    }

    public CTTLAnimateScaleBehavior getAnimScaleArray(int i) {
        CTTLAnimateScaleBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[8], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAnimScaleArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[8]);
        }
        return count_elements;
    }

    public void setAnimScaleArray(CTTLAnimateScaleBehavior[] cTTLAnimateScaleBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLAnimateScaleBehaviorArr, PROPERTY_QNAME[8]);
    }

    public void setAnimScaleArray(int i, CTTLAnimateScaleBehavior cTTLAnimateScaleBehavior) {
        generatedSetterHelperImpl(cTTLAnimateScaleBehavior, PROPERTY_QNAME[8], i, 2);
    }

    public CTTLAnimateScaleBehavior insertNewAnimScale(int i) {
        CTTLAnimateScaleBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[8], i);
        }
        return insert_element_user;
    }

    public CTTLAnimateScaleBehavior addNewAnimScale() {
        CTTLAnimateScaleBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[8]);
        }
        return add_element_user;
    }

    public void removeAnimScale(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[8], i);
        }
    }

    public List<CTTLCommandBehavior> getCmdList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda12(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda13(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda14(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda15(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda16(this));
        }
        return javaListXmlObject;
    }

    public CTTLCommandBehavior[] getCmdArray() {
        return getXmlObjectArray(PROPERTY_QNAME[9], (T[]) new CTTLCommandBehavior[0]);
    }

    public CTTLCommandBehavior getCmdArray(int i) {
        CTTLCommandBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[9], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfCmdArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[9]);
        }
        return count_elements;
    }

    public void setCmdArray(CTTLCommandBehavior[] cTTLCommandBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLCommandBehaviorArr, PROPERTY_QNAME[9]);
    }

    public void setCmdArray(int i, CTTLCommandBehavior cTTLCommandBehavior) {
        generatedSetterHelperImpl(cTTLCommandBehavior, PROPERTY_QNAME[9], i, 2);
    }

    public CTTLCommandBehavior insertNewCmd(int i) {
        CTTLCommandBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[9], i);
        }
        return insert_element_user;
    }

    public CTTLCommandBehavior addNewCmd() {
        CTTLCommandBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[9]);
        }
        return add_element_user;
    }

    public void removeCmd(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[9], i);
        }
    }

    public List<CTTLSetBehavior> getSetList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda55(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda61(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda62(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda63(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda64(this));
        }
        return javaListXmlObject;
    }

    public CTTLSetBehavior[] getSetArray() {
        return getXmlObjectArray(PROPERTY_QNAME[10], (T[]) new CTTLSetBehavior[0]);
    }

    public CTTLSetBehavior getSetArray(int i) {
        CTTLSetBehavior find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[10], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfSetArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[10]);
        }
        return count_elements;
    }

    public void setSetArray(CTTLSetBehavior[] cTTLSetBehaviorArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLSetBehaviorArr, PROPERTY_QNAME[10]);
    }

    public void setSetArray(int i, CTTLSetBehavior cTTLSetBehavior) {
        generatedSetterHelperImpl(cTTLSetBehavior, PROPERTY_QNAME[10], i, 2);
    }

    public CTTLSetBehavior insertNewSet(int i) {
        CTTLSetBehavior insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[10], i);
        }
        return insert_element_user;
    }

    public CTTLSetBehavior addNewSet() {
        CTTLSetBehavior add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[10]);
        }
        return add_element_user;
    }

    public void removeSet(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[10], i);
        }
    }

    public List<CTTLMediaNodeAudio> getAudioList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda17(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda18(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda19(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda20(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda21(this));
        }
        return javaListXmlObject;
    }

    public CTTLMediaNodeAudio[] getAudioArray() {
        return getXmlObjectArray(PROPERTY_QNAME[11], (T[]) new CTTLMediaNodeAudio[0]);
    }

    public CTTLMediaNodeAudio getAudioArray(int i) {
        CTTLMediaNodeAudio find_element_user;
        synchronized (monitor()) {
            check_orphaned();
            find_element_user = get_store().find_element_user(PROPERTY_QNAME[11], i);
            if (find_element_user == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return find_element_user;
    }

    public int sizeOfAudioArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[11]);
        }
        return count_elements;
    }

    public void setAudioArray(CTTLMediaNodeAudio[] cTTLMediaNodeAudioArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLMediaNodeAudioArr, PROPERTY_QNAME[11]);
    }

    public void setAudioArray(int i, CTTLMediaNodeAudio cTTLMediaNodeAudio) {
        generatedSetterHelperImpl(cTTLMediaNodeAudio, PROPERTY_QNAME[11], i, 2);
    }

    public CTTLMediaNodeAudio insertNewAudio(int i) {
        CTTLMediaNodeAudio insert_element_user;
        synchronized (monitor()) {
            check_orphaned();
            insert_element_user = get_store().insert_element_user(PROPERTY_QNAME[11], i);
        }
        return insert_element_user;
    }

    public CTTLMediaNodeAudio addNewAudio() {
        CTTLMediaNodeAudio add_element_user;
        synchronized (monitor()) {
            check_orphaned();
            add_element_user = get_store().add_element_user(PROPERTY_QNAME[11]);
        }
        return add_element_user;
    }

    public void removeAudio(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[11], i);
        }
    }

    public List<CTTLMediaNodeVideo> getVideoList() {
        JavaListXmlObject javaListXmlObject;
        synchronized (monitor()) {
            check_orphaned();
            javaListXmlObject = new JavaListXmlObject(new CTTimeNodeListImpl$$ExternalSyntheticLambda56(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda57(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda58(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda59(this), new CTTimeNodeListImpl$$ExternalSyntheticLambda60(this));
        }
        return javaListXmlObject;
    }

    public CTTLMediaNodeVideo[] getVideoArray() {
        return (CTTLMediaNodeVideo[]) getXmlObjectArray(PROPERTY_QNAME[12], (T[]) new CTTLMediaNodeVideo[0]);
    }

    public CTTLMediaNodeVideo getVideoArray(int i) {
        CTTLMediaNodeVideo cTTLMediaNodeVideo;
        synchronized (monitor()) {
            check_orphaned();
            cTTLMediaNodeVideo = (CTTLMediaNodeVideo) get_store().find_element_user(PROPERTY_QNAME[12], i);
            if (cTTLMediaNodeVideo == null) {
                throw new IndexOutOfBoundsException();
            }
        }
        return cTTLMediaNodeVideo;
    }

    public int sizeOfVideoArray() {
        int count_elements;
        synchronized (monitor()) {
            check_orphaned();
            count_elements = get_store().count_elements(PROPERTY_QNAME[12]);
        }
        return count_elements;
    }

    public void setVideoArray(CTTLMediaNodeVideo[] cTTLMediaNodeVideoArr) {
        check_orphaned();
        arraySetterHelper((XmlObject[]) cTTLMediaNodeVideoArr, PROPERTY_QNAME[12]);
    }

    public void setVideoArray(int i, CTTLMediaNodeVideo cTTLMediaNodeVideo) {
        generatedSetterHelperImpl(cTTLMediaNodeVideo, PROPERTY_QNAME[12], i, 2);
    }

    public CTTLMediaNodeVideo insertNewVideo(int i) {
        CTTLMediaNodeVideo cTTLMediaNodeVideo;
        synchronized (monitor()) {
            check_orphaned();
            cTTLMediaNodeVideo = (CTTLMediaNodeVideo) get_store().insert_element_user(PROPERTY_QNAME[12], i);
        }
        return cTTLMediaNodeVideo;
    }

    public CTTLMediaNodeVideo addNewVideo() {
        CTTLMediaNodeVideo cTTLMediaNodeVideo;
        synchronized (monitor()) {
            check_orphaned();
            cTTLMediaNodeVideo = (CTTLMediaNodeVideo) get_store().add_element_user(PROPERTY_QNAME[12]);
        }
        return cTTLMediaNodeVideo;
    }

    public void removeVideo(int i) {
        synchronized (monitor()) {
            check_orphaned();
            get_store().remove_element(PROPERTY_QNAME[12], i);
        }
    }
}
