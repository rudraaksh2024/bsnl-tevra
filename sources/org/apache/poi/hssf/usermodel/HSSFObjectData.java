package org.apache.poi.hssf.usermodel;

import java.io.IOException;
import org.apache.poi.ddf.DefaultEscherRecordFactory;
import org.apache.poi.ddf.EscherBSERecord;
import org.apache.poi.ddf.EscherClientDataRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord;
import org.apache.poi.hssf.record.ObjRecord;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.util.HexDump;

public final class HSSFObjectData extends HSSFPicture implements ObjectData {
    private final DirectoryEntry _root;

    public HSSFObjectData(EscherContainerRecord escherContainerRecord, ObjRecord objRecord, DirectoryEntry directoryEntry) {
        super(escherContainerRecord, objRecord);
        this._root = directoryEntry;
    }

    public String getOLE2ClassName() {
        return findObjectRecord().getOLEClassName();
    }

    public DirectoryEntry getDirectory() throws IOException {
        String str = "MBD" + HexDump.toHex(findObjectRecord().getStreamId().intValue());
        Entry entry = this._root.getEntry(str);
        if (entry instanceof DirectoryEntry) {
            return (DirectoryEntry) entry;
        }
        throw new IOException("Stream " + str + " was not an OLE2 directory");
    }

    public byte[] getObjectData() {
        return findObjectRecord().getObjectData();
    }

    public boolean hasDirectoryEntry() {
        Integer streamId = findObjectRecord().getStreamId();
        return (streamId == null || streamId.intValue() == 0) ? false : true;
    }

    /* access modifiers changed from: protected */
    public EmbeddedObjectRefSubRecord findObjectRecord() {
        for (SubRecord next : getObjRecord().getSubRecords()) {
            if (next instanceof EmbeddedObjectRefSubRecord) {
                return (EmbeddedObjectRefSubRecord) next;
            }
        }
        throw new IllegalStateException("Object data does not contain a reference to an embedded object OLE2 directory");
    }

    /* access modifiers changed from: protected */
    public EscherContainerRecord createSpContainer() {
        throw new IllegalStateException("HSSFObjectData cannot be created from scratch");
    }

    /* access modifiers changed from: protected */
    public ObjRecord createObjRecord() {
        throw new IllegalStateException("HSSFObjectData cannot be created from scratch");
    }

    /* access modifiers changed from: protected */
    public void afterRemove(HSSFPatriarch hSSFPatriarch) {
        throw new IllegalStateException("HSSFObjectData cannot be created from scratch");
    }

    /* access modifiers changed from: package-private */
    public void afterInsert(HSSFPatriarch hSSFPatriarch) {
        hSSFPatriarch.getBoundAggregate().associateShapeToObjRecord(getEscherContainer().getChildById(EscherClientDataRecord.RECORD_ID), getObjRecord());
        EscherBSERecord bSERecord = hSSFPatriarch.getSheet().getWorkbook().getWorkbook().getBSERecord(getPictureIndex());
        bSERecord.setRef(bSERecord.getRef() + 1);
    }

    /* access modifiers changed from: protected */
    public HSSFShape cloneShape() {
        EscherContainerRecord escherContainerRecord = new EscherContainerRecord();
        escherContainerRecord.fillFields(getEscherContainer().serialize(), 0, new DefaultEscherRecordFactory());
        return new HSSFObjectData(escherContainerRecord, (ObjRecord) getObjRecord().cloneViaReserialise(), this._root);
    }
}
