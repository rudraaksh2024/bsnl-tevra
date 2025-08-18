package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherComplexProperty;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherProperty;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.StringUtil;

public abstract class HSSFShape implements Shape {
    public static final int FILL__FILLCOLOR_DEFAULT = 134217737;
    public static final int LINESTYLE_DASHDOTDOTSYS = 4;
    public static final int LINESTYLE_DASHDOTGEL = 8;
    public static final int LINESTYLE_DASHDOTSYS = 3;
    public static final int LINESTYLE_DASHGEL = 6;
    public static final int LINESTYLE_DASHSYS = 1;
    public static final int LINESTYLE_DEFAULT = -1;
    public static final int LINESTYLE_DOTGEL = 5;
    public static final int LINESTYLE_DOTSYS = 2;
    public static final int LINESTYLE_LONGDASHDOTDOTGEL = 10;
    public static final int LINESTYLE_LONGDASHDOTGEL = 9;
    public static final int LINESTYLE_LONGDASHGEL = 7;
    public static final int LINESTYLE_NONE = -1;
    public static final int LINESTYLE_SOLID = 0;
    public static final int LINESTYLE__COLOR_DEFAULT = 134217792;
    public static final int LINEWIDTH_DEFAULT = 9525;
    public static final int LINEWIDTH_ONE_PT = 12700;
    public static final int NO_FILLHITTEST_FALSE = 65536;
    public static final int NO_FILLHITTEST_TRUE = 1114112;
    public static final boolean NO_FILL_DEFAULT = true;
    private final EscherContainerRecord _escherContainer;
    private final ObjRecord _objRecord;
    private final EscherOptRecord _optRecord;
    private HSSFPatriarch _patriarch;
    HSSFAnchor anchor;
    private HSSFShape parent;

    /* access modifiers changed from: package-private */
    public abstract void afterInsert(HSSFPatriarch hSSFPatriarch);

    /* access modifiers changed from: protected */
    public abstract void afterRemove(HSSFPatriarch hSSFPatriarch);

    /* access modifiers changed from: protected */
    public abstract HSSFShape cloneShape();

    public int countOfAllChildren() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public abstract ObjRecord createObjRecord();

    /* access modifiers changed from: protected */
    public abstract EscherContainerRecord createSpContainer();

    public HSSFShape(EscherContainerRecord escherContainerRecord, ObjRecord objRecord) {
        this._escherContainer = escherContainerRecord;
        this._objRecord = objRecord;
        this._optRecord = (EscherOptRecord) escherContainerRecord.getChildById(EscherOptRecord.RECORD_ID);
        this.anchor = HSSFAnchor.createAnchorFromEscher(escherContainerRecord);
    }

    public HSSFShape(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        this.parent = hSSFShape;
        this.anchor = hSSFAnchor;
        EscherContainerRecord createSpContainer = createSpContainer();
        this._escherContainer = createSpContainer;
        this._optRecord = (EscherOptRecord) createSpContainer.getChildById(EscherOptRecord.RECORD_ID);
        this._objRecord = createObjRecord();
    }

    /* access modifiers changed from: package-private */
    public void setShapeId(int i) {
        ((EscherSpRecord) this._escherContainer.getChildById(EscherSpRecord.RECORD_ID)).setShapeId(i);
        ((CommonObjectDataSubRecord) this._objRecord.getSubRecords().get(0)).setObjectId((short) (i % 1024));
    }

    /* access modifiers changed from: package-private */
    public int getShapeId() {
        return ((EscherSpRecord) this._escherContainer.getChildById(EscherSpRecord.RECORD_ID)).getShapeId();
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord getEscherContainer() {
        return this._escherContainer;
    }

    /* access modifiers changed from: protected */
    public ObjRecord getObjRecord() {
        return this._objRecord;
    }

    public EscherOptRecord getOptRecord() {
        return this._optRecord;
    }

    public HSSFShape getParent() {
        return this.parent;
    }

    public HSSFAnchor getAnchor() {
        return this.anchor;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setAnchor(org.apache.poi.hssf.usermodel.HSSFAnchor r7) {
        /*
            r6 = this;
            org.apache.poi.hssf.usermodel.HSSFShape r0 = r6.parent
            r1 = 0
            r2 = -1
            if (r0 != 0) goto L_0x0054
            boolean r0 = r7 instanceof org.apache.poi.hssf.usermodel.HSSFChildAnchor
            if (r0 != 0) goto L_0x004c
            org.apache.poi.ddf.EscherContainerRecord r0 = r6._escherContainer
            short r3 = org.apache.poi.ddf.EscherClientAnchorRecord.RECORD_ID
            org.apache.poi.ddf.EscherRecord r0 = r0.getChildById(r3)
            org.apache.poi.ddf.EscherClientAnchorRecord r0 = (org.apache.poi.ddf.EscherClientAnchorRecord) r0
            if (r0 == 0) goto L_0x009a
            r3 = r2
        L_0x0017:
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            int r4 = r4.getChildCount()
            if (r1 >= r4) goto L_0x0046
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            org.apache.poi.ddf.EscherRecord r4 = r4.getChild(r1)
            short r4 = r4.getRecordId()
            short r5 = org.apache.poi.ddf.EscherClientAnchorRecord.RECORD_ID
            if (r4 != r5) goto L_0x0043
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            int r4 = r4.getChildCount()
            int r4 = r4 + -1
            if (r1 == r4) goto L_0x0043
            org.apache.poi.ddf.EscherContainerRecord r3 = r6._escherContainer
            int r4 = r1 + 1
            org.apache.poi.ddf.EscherRecord r3 = r3.getChild(r4)
            short r3 = r3.getRecordId()
        L_0x0043:
            int r1 = r1 + 1
            goto L_0x0017
        L_0x0046:
            org.apache.poi.ddf.EscherContainerRecord r1 = r6._escherContainer
            r1.removeChildRecord(r0)
            goto L_0x009b
        L_0x004c:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Must use client anchors for shapes directly attached to sheet."
            r6.<init>(r7)
            throw r6
        L_0x0054:
            boolean r0 = r7 instanceof org.apache.poi.hssf.usermodel.HSSFClientAnchor
            if (r0 != 0) goto L_0x00b3
            org.apache.poi.ddf.EscherContainerRecord r0 = r6._escherContainer
            short r3 = org.apache.poi.ddf.EscherChildAnchorRecord.RECORD_ID
            org.apache.poi.ddf.EscherRecord r0 = r0.getChildById(r3)
            org.apache.poi.ddf.EscherChildAnchorRecord r0 = (org.apache.poi.ddf.EscherChildAnchorRecord) r0
            if (r0 == 0) goto L_0x009a
            r3 = r2
        L_0x0065:
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            int r4 = r4.getChildCount()
            if (r1 >= r4) goto L_0x0094
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            org.apache.poi.ddf.EscherRecord r4 = r4.getChild(r1)
            short r4 = r4.getRecordId()
            short r5 = org.apache.poi.ddf.EscherChildAnchorRecord.RECORD_ID
            if (r4 != r5) goto L_0x0091
            org.apache.poi.ddf.EscherContainerRecord r4 = r6._escherContainer
            int r4 = r4.getChildCount()
            int r4 = r4 + -1
            if (r1 == r4) goto L_0x0091
            org.apache.poi.ddf.EscherContainerRecord r3 = r6._escherContainer
            int r4 = r1 + 1
            org.apache.poi.ddf.EscherRecord r3 = r3.getChild(r4)
            short r3 = r3.getRecordId()
        L_0x0091:
            int r1 = r1 + 1
            goto L_0x0065
        L_0x0094:
            org.apache.poi.ddf.EscherContainerRecord r1 = r6._escherContainer
            r1.removeChildRecord(r0)
            goto L_0x009b
        L_0x009a:
            r3 = r2
        L_0x009b:
            if (r2 != r3) goto L_0x00a7
            org.apache.poi.ddf.EscherContainerRecord r0 = r6._escherContainer
            org.apache.poi.ddf.EscherRecord r1 = r7.getEscherAnchor()
            r0.addChildRecord(r1)
            goto L_0x00b0
        L_0x00a7:
            org.apache.poi.ddf.EscherContainerRecord r0 = r6._escherContainer
            org.apache.poi.ddf.EscherRecord r1 = r7.getEscherAnchor()
            r0.addChildBefore(r1, r3)
        L_0x00b0:
            r6.anchor = r7
            return
        L_0x00b3:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "Must use child anchors for shapes attached to groups."
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.HSSFShape.setAnchor(org.apache.poi.hssf.usermodel.HSSFAnchor):void");
    }

    public int getLineStyleColor() {
        EscherRGBProperty escherRGBProperty = (EscherRGBProperty) this._optRecord.lookup(EscherPropertyTypes.LINESTYLE__COLOR);
        if (escherRGBProperty == null) {
            return LINESTYLE__COLOR_DEFAULT;
        }
        return escherRGBProperty.getRgbColor();
    }

    public void setLineStyleColor(int i) {
        setPropertyValue(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, i));
    }

    public void setLineStyleColor(int i, int i2, int i3) {
        EscherPropertyTypes escherPropertyTypes = EscherPropertyTypes.LINESTYLE__COLOR;
        setPropertyValue(new EscherRGBProperty(escherPropertyTypes, i | (i2 << 8) | (i3 << 16)));
    }

    public int getFillColor() {
        EscherRGBProperty escherRGBProperty = (EscherRGBProperty) this._optRecord.lookup(EscherPropertyTypes.FILL__FILLCOLOR);
        if (escherRGBProperty == null) {
            return FILL__FILLCOLOR_DEFAULT;
        }
        return escherRGBProperty.getRgbColor();
    }

    public void setFillColor(int i) {
        setPropertyValue(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, i));
    }

    public void setFillColor(int i, int i2, int i3) {
        EscherPropertyTypes escherPropertyTypes = EscherPropertyTypes.FILL__FILLCOLOR;
        setPropertyValue(new EscherRGBProperty(escherPropertyTypes, i | (i2 << 8) | (i3 << 16)));
    }

    public int getLineWidth() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) this._optRecord.lookup(EscherPropertyTypes.LINESTYLE__LINEWIDTH);
        if (escherSimpleProperty == null) {
            return 9525;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    public void setLineWidth(int i) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEWIDTH, i));
    }

    public int getLineStyle() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) this._optRecord.lookup(EscherPropertyTypes.LINESTYLE__LINEDASHING);
        if (escherSimpleProperty == null) {
            return -1;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    public void setLineStyle(int i) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, i));
        if (getLineStyle() != 0) {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEENDCAPSTYLE, 0));
            if (getLineStyle() == -1) {
                setPropertyValue(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524288));
            } else {
                setPropertyValue(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
            }
        }
    }

    public boolean isNoFill() {
        EscherBoolProperty escherBoolProperty = (EscherBoolProperty) this._optRecord.lookup(EscherPropertyTypes.FILL__NOFILLHITTEST);
        if (escherBoolProperty == null || escherBoolProperty.getPropertyValue() == 1114112) {
            return true;
        }
        return false;
    }

    public void setNoFill(boolean z) {
        setPropertyValue(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, z ? NO_FILLHITTEST_TRUE : 65536));
    }

    /* access modifiers changed from: protected */
    public void setPropertyValue(EscherProperty escherProperty) {
        this._optRecord.setEscherProperty(escherProperty);
    }

    public void setFlipVertical(boolean z) {
        EscherSpRecord escherSpRecord = (EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (z) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 128);
        } else {
            escherSpRecord.setFlags(escherSpRecord.getFlags() & 2147483519);
        }
    }

    public void setFlipHorizontal(boolean z) {
        EscherSpRecord escherSpRecord = (EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID);
        if (z) {
            escherSpRecord.setFlags(escherSpRecord.getFlags() | 64);
        } else {
            escherSpRecord.setFlags(escherSpRecord.getFlags() & 2147483583);
        }
    }

    public boolean isFlipVertical() {
        return (((EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID)).getFlags() & 128) != 0;
    }

    public boolean isFlipHorizontal() {
        return (((EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID)).getFlags() & 64) != 0;
    }

    public int getRotationDegree() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TRANSFORM__ROTATION);
        if (escherSimpleProperty == null) {
            return 0;
        }
        byte[] bArr = new byte[4];
        LittleEndian.putInt(bArr, 0, escherSimpleProperty.getPropertyValue());
        return LittleEndian.getShort(bArr, 2);
    }

    public void setRotationDegree(short s) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TRANSFORM__ROTATION, s << 16));
    }

    /* access modifiers changed from: protected */
    public void setPatriarch(HSSFPatriarch hSSFPatriarch) {
        this._patriarch = hSSFPatriarch;
    }

    public HSSFPatriarch getPatriarch() {
        return this._patriarch;
    }

    /* access modifiers changed from: protected */
    public void setParent(HSSFShape hSSFShape) {
        this.parent = hSSFShape;
    }

    public String getShapeName() {
        EscherOptRecord optRecord = getOptRecord();
        if (optRecord == null) {
            return null;
        }
        EscherProperty lookup = optRecord.lookup(EscherPropertyTypes.GROUPSHAPE__SHAPENAME);
        if (lookup instanceof EscherComplexProperty) {
            return StringUtil.getFromUnicodeLE(((EscherComplexProperty) lookup).getComplexData());
        }
        return null;
    }
}
