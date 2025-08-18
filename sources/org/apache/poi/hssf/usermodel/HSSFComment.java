package org.apache.poi.hssf.usermodel;

import java.util.Objects;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherOptRecord;
import org.apache.poi.ddf.EscherPropertyTypes;
import org.apache.poi.ddf.EscherSimpleProperty;
import org.apache.poi.hssf.record.CommonObjectDataSubRecord;
import org.apache.poi.hssf.record.EndSubRecord;
import org.apache.poi.hssf.record.NoteRecord;
import org.apache.poi.hssf.record.NoteStructureSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.TextObjectRecord;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellAddress;

public class HSSFComment extends HSSFTextbox implements Comment {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int FILL_TYPE_PICTURE = 3;
    private static final int FILL_TYPE_SOLID = 0;
    private static final int GROUP_SHAPE_HIDDEN_MASK = 16777218;
    private static final int GROUP_SHAPE_NOT_HIDDEN_MASK = -16777219;
    private static final int GROUP_SHAPE_PROPERTY_DEFAULT_VALUE = 655362;
    private final NoteRecord _note;

    public /* bridge */ /* synthetic */ RichTextString getString() {
        return super.getString();
    }

    public HSSFComment(EscherContainerRecord escherContainerRecord, ObjRecord objRecord, TextObjectRecord textObjectRecord, NoteRecord noteRecord) {
        super(escherContainerRecord, objRecord, textObjectRecord);
        this._note = noteRecord;
    }

    public HSSFComment(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor) {
        this(hSSFShape, hSSFAnchor, createNoteRecord());
    }

    private HSSFComment(HSSFShape hSSFShape, HSSFAnchor hSSFAnchor, NoteRecord noteRecord) {
        super(hSSFShape, hSSFAnchor);
        this._note = noteRecord;
        setFillColor(134217808);
        setVisible(false);
        setAuthor("");
        ((CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0)).setObjectType(25);
    }

    protected HSSFComment(NoteRecord noteRecord, TextObjectRecord textObjectRecord) {
        this((HSSFShape) null, new HSSFClientAnchor(), noteRecord);
    }

    /* access modifiers changed from: package-private */
    public void afterInsert(HSSFPatriarch hSSFPatriarch) {
        super.afterInsert(hSSFPatriarch);
        hSSFPatriarch.getBoundAggregate().addTailRecord(getNoteRecord());
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        EscherContainerRecord createSpContainer = super.createSpContainer();
        EscherOptRecord escherOptRecord = (EscherOptRecord) createSpContainer.getChildById(EscherOptRecord.RECORD_ID);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTLEFT);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTRIGHT);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTTOP);
        escherOptRecord.removeEscherProperty(EscherPropertyTypes.TEXT__TEXTBOTTOM);
        escherOptRecord.setEscherProperty(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, (int) GROUP_SHAPE_PROPERTY_DEFAULT_VALUE));
        return createSpContainer;
    }

    /* access modifiers changed from: protected */
    public ObjRecord createObjRecord() {
        ObjRecord objRecord = new ObjRecord();
        CommonObjectDataSubRecord commonObjectDataSubRecord = new CommonObjectDataSubRecord();
        commonObjectDataSubRecord.setObjectType(202);
        commonObjectDataSubRecord.setLocked(true);
        commonObjectDataSubRecord.setPrintable(true);
        commonObjectDataSubRecord.setAutofill(false);
        commonObjectDataSubRecord.setAutoline(true);
        NoteStructureSubRecord noteStructureSubRecord = new NoteStructureSubRecord();
        EndSubRecord endSubRecord = new EndSubRecord();
        objRecord.addSubRecord(commonObjectDataSubRecord);
        objRecord.addSubRecord(noteStructureSubRecord);
        objRecord.addSubRecord(endSubRecord);
        return objRecord;
    }

    private static NoteRecord createNoteRecord() {
        NoteRecord noteRecord = new NoteRecord();
        noteRecord.setFlags(0);
        noteRecord.setAuthor("");
        return noteRecord;
    }

    /* access modifiers changed from: package-private */
    public void setShapeId(int i) {
        if (i <= 65535) {
            super.setShapeId(i);
            ((CommonObjectDataSubRecord) getObjRecord().getSubRecords().get(0)).setObjectId(i);
            this._note.setShapeId(i);
            return;
        }
        throw new IllegalArgumentException("Cannot add more than 65535 shapes");
    }

    public void setVisible(boolean z) {
        this._note.setFlags(z ? (short) 2 : 0);
        setHidden(!z);
    }

    public boolean isVisible() {
        return this._note.getFlags() == 2;
    }

    public CellAddress getAddress() {
        return new CellAddress(getRow(), getColumn());
    }

    public void setAddress(CellAddress cellAddress) {
        setRow(cellAddress.getRow());
        setColumn(cellAddress.getColumn());
    }

    public void setAddress(int i, int i2) {
        setRow(i);
        setColumn(i2);
    }

    public int getRow() {
        return this._note.getRow();
    }

    public void setRow(int i) {
        this._note.setRow(i);
    }

    public int getColumn() {
        return this._note.getColumn();
    }

    public void setColumn(int i) {
        this._note.setColumn(i);
    }

    public String getAuthor() {
        return this._note.getAuthor();
    }

    public void setAuthor(String str) {
        NoteRecord noteRecord = this._note;
        if (noteRecord != null) {
            noteRecord.setAuthor(str);
        }
    }

    /* access modifiers changed from: protected */
    public NoteRecord getNoteRecord() {
        return this._note;
    }

    public boolean hasPosition() {
        return this._note != null && getColumn() >= 0 && getRow() >= 0;
    }

    public ClientAnchor getClientAnchor() {
        HSSFAnchor anchor = super.getAnchor();
        if (anchor instanceof ClientAnchor) {
            return (ClientAnchor) anchor;
        }
        Class<ClientAnchor> cls = ClientAnchor.class;
        throw new IllegalStateException("Anchor can not be changed in ClientAnchor");
    }

    public void setShapeType(int i) {
        throw new IllegalStateException("Shape type can not be changed in " + getClass().getSimpleName());
    }

    public void afterRemove(HSSFPatriarch hSSFPatriarch) {
        super.afterRemove(hSSFPatriarch);
        hSSFPatriarch.getBoundAggregate().removeTailRecord(getNoteRecord());
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.fillFields(getEscherContainer().serialize(), 0, new DefaultEscherRecordFactory());
        return new HSSFComment(escherContainerRecord, (ObjRecord) getObjRecord().cloneViaReserialise(), (TextObjectRecord) getTextObjectRecord().cloneViaReserialise(), (NoteRecord) getNoteRecord().cloneViaReserialise());
    }

    public void setBackgroundImage(int i) {
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__PATTERNTEXTURE, false, true, i));
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__FILLTYPE, false, false, 3));
        EscherBSERecord bSERecord = getPatriarch().getSheet().getWorkbook().getWorkbook().getBSERecord(i);
        bSERecord.setRef(bSERecord.getRef() + 1);
    }

    public void resetBackgroundImage() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        if (escherSimpleProperty != null) {
            EscherBSERecord bSERecord = getPatriarch().getSheet().getWorkbook().getWorkbook().getBSERecord(escherSimpleProperty.getPropertyValue());
            bSERecord.setRef(bSERecord.getRef() - 1);
            getOptRecord().removeEscherProperty(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        }
        setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.FILL__FILLTYPE, false, false, 0));
    }

    public int getBackgroundImageId() {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.FILL__PATTERNTEXTURE);
        if (escherSimpleProperty == null) {
            return 0;
        }
        return escherSimpleProperty.getPropertyValue();
    }

    private void setHidden(boolean z) {
        EscherSimpleProperty escherSimpleProperty = (EscherSimpleProperty) getOptRecord().lookup(EscherPropertyTypes.GROUPSHAPE__FLAGS);
        if (z) {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, escherSimpleProperty.getPropertyValue() | GROUP_SHAPE_HIDDEN_MASK));
        } else {
            setPropertyValue(new EscherSimpleProperty(EscherPropertyTypes.GROUPSHAPE__FLAGS, false, false, escherSimpleProperty.getPropertyValue() & GROUP_SHAPE_NOT_HIDDEN_MASK));
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HSSFComment)) {
            return false;
        }
        return getNoteRecord().equals(((HSSFComment) obj).getNoteRecord());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(getRow()), Integer.valueOf(getColumn())});
    }
}
