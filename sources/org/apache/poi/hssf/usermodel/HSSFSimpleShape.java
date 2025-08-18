package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBoolProperty;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherRGBProperty;
import org.apache.poi.ddf.EscherShapePathProperty;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.ddf.EscherSpRecord;
import org.apache.poi.ddf.EscherTextboxRecord;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.EscherAggregate;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.SimpleShape;

public class HSSFSimpleShape extends HSSFShape implements SimpleShape {
    public static final short OBJECT_TYPE_ARC = 19;
    public static final short OBJECT_TYPE_COMBO_BOX = 201;
    public static final short OBJECT_TYPE_COMMENT = 202;
    public static final short OBJECT_TYPE_LINE = 20;
    public static final short OBJECT_TYPE_MICROSOFT_OFFICE_DRAWING = 30;
    public static final short OBJECT_TYPE_OVAL = 3;
    public static final short OBJECT_TYPE_PICTURE = 75;
    public static final short OBJECT_TYPE_RECTANGLE = 1;
    public static final int WRAP_BY_POINTS = 1;
    public static final int WRAP_NONE = 2;
    public static final int WRAP_SQUARE = 0;
    private TextObjectRecord _textObjectRecord;

    public HSSFSimpleShape(EscherContainerRecord escherContainerRecord, ObjRecord objRecord, TextObjectRecord textObjectRecord) {
        super(escherContainerRecord, objRecord);
        this._textObjectRecord = textObjectRecord;
    }

    public HSSFSimpleShape(EscherContainerRecord escherContainerRecord, ObjRecord objRecord) {
        super(escherContainerRecord, objRecord);
    }

    public HSSFSimpleShape(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        super(hSSFShape, hSSFAnchor);
        this._textObjectRecord = createTextObjRecord();
    }

    /* access modifiers changed from: protected */
    public TextObjectRecord getTextObjectRecord() {
        return this._textObjectRecord;
    }

    /* access modifiers changed from: protected */
    public TextObjectRecord createTextObjRecord() {
        TextObjectRecord textObjectRecord = new TextObjectRecord();
        textObjectRecord.setHorizontalTextAlignment(2);
        textObjectRecord.setVerticalTextAlignment(2);
        textObjectRecord.setTextLocked(true);
        textObjectRecord.setTextOrientation(0);
        textObjectRecord.setStr(new HSSFRichTextString(""));
        return textObjectRecord;
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.setRecordId(EscherContainerRecord.SP_CONTAINER);
        escherContainerRecord.setOptions(15);
        EscherSpRecord escherSpRecord = new EscherSpRecord();
        escherSpRecord.setRecordId(EscherSpRecord.RECORD_ID);
        escherSpRecord.setFlags(2560);
        escherSpRecord.setVersion(2);
        EscherClientDataRecord escherClientDataRecord = new EscherClientDataRecord();
        escherClientDataRecord.setRecordId(EscherClientDataRecord.RECORD_ID);
        escherClientDataRecord.setOptions(0);
        EscherOptRecord escherOptRecord = new EscherOptRecord();
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.LINESTYLE__LINEDASHING, 0));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        escherOptRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.FILL__FILLCOLOR, (int) HSSFShape.FILL__FILLCOLOR_DEFAULT));
        escherOptRecord.setEscherProperty(new EscherRGBProperty(EscherPropertyTypes.LINESTYLE__COLOR, (int) HSSFShape.LINESTYLE__COLOR_DEFAULT));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.FILL__NOFILLHITTEST, 65536));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.LINESTYLE__NOLINEDRAWDASH, 524296));
        escherOptRecord.setEscherProperty(new EscherShapePathProperty(EscherPropertyTypes.GEOMETRY__SHAPEPATH, 4));
        escherOptRecord.setEscherProperty(new EscherBoolProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, 524288));
        escherOptRecord.setRecordId(EscherOptRecord.RECORD_ID);
        EscherTextboxRecord escherTextboxRecord = new EscherTextboxRecord();
        escherTextboxRecord.setRecordId(EscherTextboxRecord.RECORD_ID);
        escherTextboxRecord.setOptions(0);
        escherContainerRecord.addChildRecord(escherSpRecord);
        escherContainerRecord.addChildRecord(escherOptRecord);
        escherContainerRecord.addChildRecord(getAnchor().getEscherAnchor());
        escherContainerRecord.addChildRecord(escherClientDataRecord);
        escherContainerRecord.addChildRecord(escherTextboxRecord);
        return escherContainerRecord;
    }

    /* access modifiers changed from: protected */
    public ObjRecord createObjRecord() {
        ObjRecord objRecord = new ObjRecord();
        CommonObjectDataSubRecord commonObjectDataSubRecord = new CommonObjectDataSubRecord();
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
        if (getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID) != null) {
            hSSFPatriarch.getBoundAggregate().removeShapeToObjRecord(getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID));
        }
    }

    public HSSFRichTextString getString() {
        return this._textObjectRecord.getStr();
    }

    public void setString(RichTextString richTextString) {
        if (getShapeType() == 0 || getShapeType() == 20) {
            throw new IllegalStateException("Cannot set text for shape type: " + getShapeType());
        }
        HSSFRichTextString hSSFRichTextString = (HSSFRichTextString) richTextString;
        if (hSSFRichTextString.numFormattingRuns() == 0) {
            hSSFRichTextString.applyFont(0);
        }
        getOrCreateTextObjRecord().setStr(hSSFRichTextString);
        if (richTextString.getString() != null) {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__TEXTID, richTextString.getString().hashCode()));
        }
    }

    /* access modifiers changed from: package-private */
    public void afterInsert(HSSFPatriarch hSSFPatriarch) {
        EscherAggregate boundAggregate = hSSFPatriarch.getBoundAggregate();
        boundAggregate.associateShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
        if (getTextObjectRecord() != null) {
            boundAggregate.associateShapeToObjRecord(getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID), getTextObjectRecord());
        }
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.fillFields(getEscherContainer().serialize(), 0, new DefaultEscherRecordFactory());
        return new HSSFSimpleShape(escherContainerRecord, (ObjRecord) getObjRecord().cloneViaReserialise(), (getTextObjectRecord() == null || getString() == null || getString().getString() == null) ? null : (TextObjectRecord) getTextObjectRecord().cloneViaReserialise());
    }

    public int getShapeType() {
        return ((EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID)).getShapeType();
    }

    public int getWrapText() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.TEXT__WRAPTEXT);
        if (escherSimpleProperty == null) {
            return 0;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    public void setWrapText(int i) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.TEXT__WRAPTEXT, false, false, i));
    }

    public void setShapeType(int i) {
        ((CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0)).setObjectType(30);
        ((EscherSpRecord) getEscherContainer().getChildById(EscherSpRecord.RECORD_ID)).setShapeType((short) i);
    }

    private TextObjectRecord getOrCreateTextObjRecord() {
        if (getTextObjectRecord() == null) {
            this._textObjectRecord = createTextObjRecord();
        }
        if (((EscherTextboxRecord) getEscherContainer().getChildById(EscherTextboxRecord.RECORD_ID)) == null) {
            EscherTextboxRecord escherTextboxRecord = new EscherTextboxRecord();
            escherTextboxRecord.setRecordId(EscherTextboxRecord.RECORD_ID);
            escherTextboxRecord.setOptions(0);
            getEscherContainer().addChildRecord(escherTextboxRecord);
            getPatriarch().getBoundAggregate().associateShapeToObjRecord(escherTextboxRecord, this._textObjectRecord);
        }
        return this._textObjectRecord;
    }

    public int getShapeId() {
        return super.getShapeId();
    }
}
