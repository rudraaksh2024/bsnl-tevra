package org.apache.poi.hssf.usermodel;

import kotlin.jvm.internal.ByteCompanionObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ddf.EscherArrayProperty;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.ddf.EscherShapePathProperty;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.LittleEndian;

public class HSSFPolygon extends HSSFSimpleShape {
    private static final Logger LOG = LogManager.getLogger((Class<?>) HSSFPolygon.class);
    public static final short OBJECT_TYPE_MICROSOFT_OFFICE_DRAWING = 30;

    /* access modifiers changed from: protected */
    public TextObjectRecord createTextObjRecord() {
        return null;
    }

    public HSSFPolygon(EscherContainerRecord escherContainerRecord, ObjRecord objRecord, TextObjectRecord textObjectRecord) {
        super(escherContainerRecord, objRecord, textObjectRecord);
    }

    public HSSFPolygon(EscherContainerRecord escherContainerRecord, ObjRecord objRecord) {
        super(escherContainerRecord, objRecord);
    }

    HSSFPolygon(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        super(hSSFShape, hSSFAnchor);
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        EscherSpRecord escherSpRecord = new EscherSpRecord();
        EscherOptRecord escherOptRecord = new EscherOptRecord();
        EscherClientDataRecord escherClientDataRecord = new EscherClientDataRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.SP_CONTAINER);
        escherContainerRecord.setOptions(15);
        escherSpRecord.setRecordId(EscherSpRecord.RECORD_ID);
        escherSpRecord.setOptions((short) ((ShapeType.NOT_PRIMITIVE.nativeId << 4) | 2));
        if (getParent() == null) {
            escherSpRecord.setFlags(2560);
        } else {
            escherSpRecord.setFlags(2562);
        }
        escherOptRecord.setRecordId(EscherOptRecord.RECORD_ID);
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.TRANSFORM__ROTATION, false, false, 0));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GEOMETRY__RIGHT, false, false, 100));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GEOMETRY__BOTTOM, false, false, 100));
        escherOptRecord.setEscherProperty(new EscherShapePathProperty(EscherPropertyTypes.GEOMETRY__SHAPEPATH, 4));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GEOMETRY__FILLOK, false, false, 65537));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINESTARTARROWHEAD, false, false, 0));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEENDARROWHEAD, false, false, 0));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEENDCAPSTYLE, false, false, 0));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, 0));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEWIDTH, 9525));
        escherOptRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, (int) HSSFShape.FILL__FILLCOLOR_DEFAULT));
        escherOptRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, (int) HSSFShape.LINESTYLE__COLOR_DEFAULT));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, 1));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        EscherRecord escherAnchor = getAnchor().getEscherAnchor();
        escherClientDataRecord.setRecordId(EscherClientDataRecord.RECORD_ID);
        escherClientDataRecord.setOptions(0);
        escherContainerRecord.addChildRecord(escherSpRecord);
        escherContainerRecord.addChildRecord(escherOptRecord);
        escherContainerRecord.addChildRecord(escherAnchor);
        escherContainerRecord.addChildRecord(escherClientDataRecord);
        return escherContainerRecord;
    }

    /* access modifiers changed from: protected */
    public ObjRecord createObjRecord() {
        ObjRecord objRecord = new ObjRecord();
        CommonObjectDataSubRecord commonObjectDataSubRecord = new CommonObjectDataSubRecord();
        commonObjectDataSubRecord.setObjectType(30);
        commonObjectDataSubRecord.setLocked(true);
        commonObjectDataSubRecord.setPrintable(true);
        commonObjectDataSubRecord.setAutofill(true);
        commonObjectDataSubRecord.setAutoline(true);
        EndSubRecord endSubRecord = new EndSubRecord();
        objRecord.addSubRecord(commonObjectDataSubRecord);
        objRecord.addSubRecord(endSubRecord);
        return objRecord;
    }

    /* access modifiers changed from: protected */
    public void afterRemove(HSSFPatriarch hSSFPatriarch) {
        hSSFPatriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID));
    }

    public int[] getXPoints() {
        EscherArrayProperty escherArrayProperty = (EscherArrayProperty) getOptRecord().lookup(EscherPropertyTypes.GEOMETRY__VERTICES);
        if (escherArrayProperty == null) {
            return new int[0];
        }
        int[] iArr = new int[(escherArrayProperty.getNumberOfElementsInArray() - 1)];
        for (int i = 0; i < escherArrayProperty.getNumberOfElementsInArray() - 1; i++) {
            iArr[i] = LittleEndian.getShort(escherArrayProperty.getElement(i), 0);
        }
        return iArr;
    }

    public int[] getYPoints() {
        EscherArrayProperty escherArrayProperty = (EscherArrayProperty) getOptRecord().lookup(EscherPropertyTypes.GEOMETRY__VERTICES);
        if (escherArrayProperty == null) {
            return new int[0];
        }
        int[] iArr = new int[(escherArrayProperty.getNumberOfElementsInArray() - 1)];
        for (int i = 0; i < escherArrayProperty.getNumberOfElementsInArray() - 1; i++) {
            iArr[i] = LittleEndian.getShort(escherArrayProperty.getElement(i), 2);
        }
        return iArr;
    }

    public void setPoints(int[] iArr, int[] iArr2) {
        if (iArr.length != iArr2.length) {
            LOG.atError().log("xPoint.length must be equal to yPoints.length");
            return;
        }
        if (iArr.length == 0) {
            LOG.atError().log("HSSFPolygon must have at least one point");
        }
        EscherArrayProperty escherArrayProperty = new EscherArrayProperty(EscherPropertyTypes.GEOMETRY__VERTICES, false, 0);
        escherArrayProperty.setNumberOfElementsInArray(iArr.length + 1);
        escherArrayProperty.setNumberOfElementsInMemory(iArr.length + 1);
        escherArrayProperty.setSizeOfElements(65520);
        for (int i = 0; i < iArr.length; i++) {
            byte[] bArr = new byte[4];
            LittleEndian.putShort(bArr, 0, (short) iArr[i]);
            LittleEndian.putShort(bArr, 2, (short) iArr2[i]);
            escherArrayProperty.setElement(i, bArr);
        }
        int length = iArr.length;
        byte[] bArr2 = new byte[4];
        LittleEndian.putShort(bArr2, 0, (short) iArr[0]);
        LittleEndian.putShort(bArr2, 2, (short) iArr2[0]);
        escherArrayProperty.setElement(length, bArr2);
        setPropertyValue(escherArrayProperty);
        EscherArrayProperty escherArrayProperty2 = new EscherArrayProperty(EscherPropertyTypes.GEOMETRY__SEGMENTINFO, false, 0);
        escherArrayProperty2.setSizeOfElements(2);
        escherArrayProperty2.setNumberOfElementsInArray((iArr.length * 2) + 4);
        escherArrayProperty2.setNumberOfElementsInMemory((iArr.length * 2) + 4);
        escherArrayProperty2.setElement(0, new byte[]{0, Ptg.CLASS_ARRAY});
        escherArrayProperty2.setElement(1, new byte[]{0, -84});
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = i2 * 2;
            escherArrayProperty2.setElement(i3 + 2, new byte[]{1, 0});
            escherArrayProperty2.setElement(i3 + 3, new byte[]{0, -84});
        }
        escherArrayProperty2.setElement(escherArrayProperty2.getNumberOfElementsInArray() - 2, new byte[]{1, 96});
        escherArrayProperty2.setElement(escherArrayProperty2.getNumberOfElementsInArray() - 1, new byte[]{0, ByteCompanionObject.MIN_VALUE});
        setPropertyValue(escherArrayProperty2);
    }

    public void setPolygonDrawArea(int i, int i2) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GEOMETRY__RIGHT, i));
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GEOMETRY__BOTTOM, i2));
    }

    public int getDrawAreaWidth() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.GEOMETRY__RIGHT);
        if (escherSimpleProperty == null) {
            return 100;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    public int getDrawAreaHeight() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.GEOMETRY__BOTTOM);
        if (escherSimpleProperty == null) {
            return 100;
        }
        return escherSimpleProperty.getPropertyValue();
    }
}
