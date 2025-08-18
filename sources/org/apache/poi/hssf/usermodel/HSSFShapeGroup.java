package org.apache.poi.hssf.usermodel;

import androidx.core.view.InputDeviceCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherRecordTypes;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherSpgrRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.GroupMarkerSubRecord;
import org.apache.poi.hssf.record.ObjRecord;

public class HSSFShapeGroup extends HSSFShape implements HSSFShapeContainer {
    private final EscherSpgrRecord _spgrRecord;
    private final List<HSSFShape> shapes;

    public HSSFShapeGroup(EscherContainerRecord escherContainerRecord, ObjRecord objRecord) {
        super(escherContainerRecord, objRecord);
        this.shapes = new ArrayList();
        EscherContainerRecord escherContainerRecord2 = escherContainerRecord.getChildContainers().get(0);
        this._spgrRecord = (EscherSpgrRecord) escherContainerRecord2.getChild(0);
        Iterator<EscherRecord> it = escherContainerRecord2.iterator();
        while (it.hasNext()) {
            EscherRecord next = it.next();
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$ddf$EscherRecordTypes[EscherRecordTypes.forTypeID(next.getRecordId()).ordinal()];
            if (i == 1) {
                this.anchor = new HSSFClientAnchor((EscherClientAnchorRecord) next);
            } else if (i == 2) {
                this.anchor = new HSSFChildAnchor((EscherChildAnchorRecord) next);
            }
        }
    }

    /* renamed from: org.apache.poi.hssf.usermodel.HSSFShapeGroup$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ddf$EscherRecordTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                org.apache.poi.ddf.EscherRecordTypes[] r0 = org.apache.poi.ddf.EscherRecordTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ddf$EscherRecordTypes = r0
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.CLIENT_ANCHOR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.CHILD_ANCHOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ddf$EscherRecordTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ddf.EscherRecordTypes r1 = org.apache.poi.ddf.EscherRecordTypes.SPGR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFShapeGroup.AnonymousClass1.<clinit>():void");
        }
    }

    public HSSFShapeGroup(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        super(hSSFShape, hSSFAnchor);
        this.shapes = new ArrayList();
        this._spgrRecord = (EscherSpgrRecord) ((EscherContainerRecord) getEscherContainer().getChild(0)).getChildById(EscherSpgrRecord.RECORD_ID);
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        EscherContainerRecord escherContainerRecord2 = new EscherContainerRecord();
        EscherSpgrRecord escherSpgrRecord = new EscherSpgrRecord();
        EscherSpRecord escherSpRecord = new EscherSpRecord();
        EscherOptRecord escherOptRecord = new EscherOptRecord();
        EscherClientDataRecord escherClientDataRecord = new EscherClientDataRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        escherContainerRecord.setOptions(15);
        escherContainerRecord2.setRecordId(EscherContainerRecord.SP_CONTAINER);
        escherContainerRecord2.setOptions(15);
        escherSpgrRecord.setRecordId(EscherSpgrRecord.RECORD_ID);
        escherSpgrRecord.setOptions(1);
        escherSpgrRecord.setRectX1(0);
        escherSpgrRecord.setRectY1(0);
        escherSpgrRecord.setRectX2(IEEEDouble.EXPONENT_BIAS);
        escherSpgrRecord.setRectY2(255);
        escherSpRecord.setRecordId(EscherSpRecord.RECORD_ID);
        escherSpRecord.setOptions(2);
        if (getAnchor() instanceof HSSFClientAnchor) {
            escherSpRecord.setFlags(InputDeviceCompat.SOURCE_DPAD);
        } else {
            escherSpRecord.setFlags(515);
        }
        escherOptRecord.setRecordId(EscherOptRecord.RECORD_ID);
        escherOptRecord.setOptions(35);
        escherOptRecord.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.PROTECTION__LOCKAGAINSTGROUPING, 262148));
        escherOptRecord.addEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord escherAnchor = getAnchor().getEscherAnchor();
        escherClientDataRecord.setRecordId(EscherClientDataRecord.RECORD_ID);
        escherClientDataRecord.setOptions(0);
        escherContainerRecord.addChildRecord(escherContainerRecord2);
        escherContainerRecord2.addChildRecord(escherSpgrRecord);
        escherContainerRecord2.addChildRecord(escherSpRecord);
        escherContainerRecord2.addChildRecord(escherOptRecord);
        escherContainerRecord2.addChildRecord(escherAnchor);
        escherContainerRecord2.addChildRecord(escherClientDataRecord);
        return escherContainerRecord;
    }

    /* access modifiers changed from: protected */
    public ObjRecord createObjRecord() {
        ObjRecord objRecord = new ObjRecord();
        CommonObjectDataSubRecord commonObjectDataSubRecord = new CommonObjectDataSubRecord();
        commonObjectDataSubRecord.setObjectType(0);
        commonObjectDataSubRecord.setLocked(true);
        commonObjectDataSubRecord.setPrintable(true);
        commonObjectDataSubRecord.setAutofill(true);
        commonObjectDataSubRecord.setAutoline(true);
        GroupMarkerSubRecord groupMarkerSubRecord = new GroupMarkerSubRecord();
        EndSubRecord endSubRecord = new EndSubRecord();
        objRecord.addSubRecord(commonObjectDataSubRecord);
        objRecord.addSubRecord(groupMarkerSubRecord);
        objRecord.addSubRecord(endSubRecord);
        return objRecord;
    }

    /* access modifiers changed from: protected */
    public void afterRemove(HSSFPatriarch hSSFPatriarch) {
        hSSFPatriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildContainers().get(0).getChildById(EscherClientDataRecord.RECORD_ID));
        EscherContainerRecord escherContainer = getEscherContainer();
        HSSFPatriarch patriarch = getPatriarch();
        for (HSSFShape next : this.shapes) {
            if (escherContainer.removeChildRecord(next.getEscherContainer())) {
                next.afterRemove(patriarch);
            }
        }
        this.shapes.clear();
    }

    private void onCreate(HSSFShape hSSFShape) {
        EscherSpRecord escherSpRecord;
        if (getPatriarch() != null) {
            EscherContainerRecord escherContainer = hSSFShape.getEscherContainer();
            hSSFShape.setShapeId(getPatriarch().newShapeId());
            getEscherContainer().addChildRecord(escherContainer);
            hSSFShape.afterInsert(getPatriarch());
            if (hSSFShape instanceof HSSFShapeGroup) {
                escherSpRecord = (EscherSpRecord) hSSFShape.getEscherContainer().getChildContainers().get(0).getChildById(EscherSpRecord.RECORD_ID);
            } else {
                escherSpRecord = (EscherSpRecord) hSSFShape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
            }
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 2);
        }
    }

    public HSSFShapeGroup createGroup(HSSFChildAnchor hSSFChildAnchor) {
        HSSFShapeGroup hSSFShapeGroup = new HSSFShapeGroup((HSSFShape) this, (HSSFAnchor) hSSFChildAnchor);
        hSSFShapeGroup.setParent(this);
        hSSFShapeGroup.setAnchor(hSSFChildAnchor);
        this.shapes.add(hSSFShapeGroup);
        onCreate(hSSFShapeGroup);
        return hSSFShapeGroup;
    }

    public void addShape(HSSFShape hSSFShape) {
        hSSFShape.setPatriarch(getPatriarch());
        hSSFShape.setParent(this);
        this.shapes.add(hSSFShape);
    }

    public HSSFSimpleShape createShape(HSSFChildAnchor hSSFChildAnchor) {
        HSSFSimpleShape hSSFSimpleShape = new HSSFSimpleShape((HSSFShape) this, (HSSFAnchor) hSSFChildAnchor);
        hSSFSimpleShape.setParent(this);
        hSSFSimpleShape.setAnchor(hSSFChildAnchor);
        this.shapes.add(hSSFSimpleShape);
        onCreate(hSSFSimpleShape);
        EscherSpRecord escherSpRecord = (EscherSpRecord) hSSFSimpleShape.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (hSSFSimpleShape.getAnchor().isHorizontallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 64);
        }
        if (hSSFSimpleShape.getAnchor().isVerticallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 128);
        }
        return hSSFSimpleShape;
    }

    public HSSFTextbox createTextbox(HSSFChildAnchor hSSFChildAnchor) {
        HSSFTextbox hSSFTextbox = new HSSFTextbox(this, hSSFChildAnchor);
        hSSFTextbox.setParent(this);
        hSSFTextbox.setAnchor(hSSFChildAnchor);
        this.shapes.add(hSSFTextbox);
        onCreate(hSSFTextbox);
        return hSSFTextbox;
    }

    public HSSFPolygon createPolygon(HSSFChildAnchor hSSFChildAnchor) {
        HSSFPolygon hSSFPolygon = new HSSFPolygon((HSSFShape) this, (HSSFAnchor) hSSFChildAnchor);
        hSSFPolygon.setParent(this);
        hSSFPolygon.setAnchor(hSSFChildAnchor);
        this.shapes.add(hSSFPolygon);
        onCreate(hSSFPolygon);
        return hSSFPolygon;
    }

    public HSSFPicture createPicture(HSSFChildAnchor hSSFChildAnchor, int i) {
        HSSFPicture hSSFPicture = new HSSFPicture((HSSFShape) this, (HSSFAnchor) hSSFChildAnchor);
        hSSFPicture.setParent(this);
        hSSFPicture.setAnchor(hSSFChildAnchor);
        hSSFPicture.setPictureIndex(i);
        this.shapes.add(hSSFPicture);
        onCreate(hSSFPicture);
        EscherSpRecord escherSpRecord = (EscherSpRecord) hSSFPicture.getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (hSSFPicture.getAnchor().isHorizontallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 64);
        }
        if (hSSFPicture.getAnchor().isVerticallyFlipped()) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 128);
        }
        return hSSFPicture;
    }

    public List<HSSFShape> getChildren() {
        return Collections.unmodifiableList(this.shapes);
    }

    public void setCoordinates(int i, int i2, int i3, int i4) {
        this._spgrRecord.setRectX1(i);
        this._spgrRecord.setRectX2(i3);
        this._spgrRecord.setRectY1(i2);
        this._spgrRecord.setRectY2(i4);
    }

    public void clear() {
        Iterator it = new ArrayList(this.shapes).iterator();
        while (it.hasNext()) {
            removeShape((HSSFShape) it.next());
        }
    }

    public int getX1() {
        return this._spgrRecord.getRectX1();
    }

    public int getY1() {
        return this._spgrRecord.getRectY1();
    }

    public int getX2() {
        return this._spgrRecord.getRectX2();
    }

    public int getY2() {
        return this._spgrRecord.getRectY2();
    }

    public int countOfAllChildren() {
        int size = this.shapes.size();
        for (HSSFShape countOfAllChildren : this.shapes) {
            size += countOfAllChildren.countOfAllChildren();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public void afterInsert(HSSFPatriarch hSSFPatriarch) {
        hSSFPatriarch.getBoundAggregate().associateShapeToObjRecord(((EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER)).getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
    }

    /* access modifiers changed from: package-private */
    public void setShapeId(int i) {
        ((EscherSpRecord) ((EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER)).getChildById(EscherSpRecord.RECORD_ID)).setShapeId(i);
        ((CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0)).setObjectId((short) (i % 1024));
    }

    /* access modifiers changed from: package-private */
    public int getShapeId() {
        return ((EscherSpRecord) ((EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER)).getChildById(EscherSpRecord.RECORD_ID)).getShapeId();
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape() {
        throw new IllegalStateException("Use method cloneShape(HSSFPatriarch patriarch)");
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape(HSSFPatriarch hSSFPatriarch) {
        HSSFShape hSSFShape;
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.SPGR_CONTAINER);
        escherContainerRecord.setOptions(15);
        EscherContainerRecord escherContainerRecord2 = new EscherContainerRecord();
        escherContainerRecord2.fillFields(((EscherContainerRecord) getEscherContainer().getChildById(EscherContainerRecord.SP_CONTAINER)).serialize(), 0, new DefaultEscherRecordFactory());
        escherContainerRecord.addChildRecord(escherContainerRecord2);
        HSSFShapeGroup hSSFShapeGroup = new HSSFShapeGroup(escherContainerRecord, getObjRecord() != null ? (ObjRecord) getObjRecord().cloneViaReserialise() : null);
        hSSFShapeGroup.setPatriarch(hSSFPatriarch);
        for (HSSFShape next : getChildren()) {
            if (next instanceof HSSFShapeGroup) {
                hSSFShape = ((HSSFShapeGroup) next).cloneShape(hSSFPatriarch);
            } else {
                hSSFShape = next.cloneShape();
            }
            hSSFShapeGroup.addShape(hSSFShape);
            hSSFShapeGroup.onCreate(hSSFShape);
        }
        return hSSFShapeGroup;
    }

    public boolean removeShape(HSSFShape hSSFShape) {
        boolean removeChildRecord = getEscherContainer().removeChildRecord(hSSFShape.getEscherContainer());
        if (removeChildRecord) {
            hSSFShape.afterRemove(getPatriarch());
            this.shapes.remove(hSSFShape);
        }
        return removeChildRecord;
    }

    public Iterator<HSSFShape> iterator() {
        return this.shapes.iterator();
    }

    public Spliterator<HSSFShape> spliterator() {
        return this.shapes.spliterator();
    }
}
