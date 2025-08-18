package org.apache.poi.ddf;

import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;

public final class UnknownEscherRecord extends EscherRecord {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static int MAX_RECORD_LENGTH = 100000000;
    private static final byte[] NO_BYTES = new byte[0];
    private final List<EscherRecord> _childRecords;
    private byte[] thedata;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public UnknownEscherRecord() {
        this.thedata = NO_BYTES;
        this._childRecords = new ArrayList();
    }

    public UnknownEscherRecord(UnknownEscherRecord unknownEscherRecord) {
        super(unknownEscherRecord);
        this.thedata = NO_BYTES;
        ArrayList arrayList = new ArrayList();
        this._childRecords = arrayList;
        unknownEscherRecord._childRecords.stream().map(new EscherContainerRecord$$ExternalSyntheticLambda1()).forEach(new EscherContainerRecord$$ExternalSyntheticLambda2(arrayList));
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        int i2 = 8;
        int i3 = i + 8;
        int length = bArr.length - i3;
        if (readHeader > length) {
            readHeader = length;
        }
        if (isContainerRecord()) {
            this.thedata = new byte[0];
            while (readHeader > 0) {
                EscherRecord createRecord = escherRecordFactory.createRecord(bArr, i3);
                int fillFields = createRecord.fillFields(bArr, i3, escherRecordFactory);
                i2 += fillFields;
                i3 += fillFields;
                readHeader -= fillFields;
                getChildRecords().add(createRecord);
            }
            return i2;
        }
        if (readHeader < 0) {
            readHeader = 0;
        }
        this.thedata = IOUtils.safelyClone(bArr, i3, readHeader, MAX_RECORD_LENGTH);
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        int length = this.thedata.length;
        for (EscherRecord recordSize : this._childRecords) {
            length += recordSize.getRecordSize();
        }
        LittleEndian.putInt(bArr, i + 4, length);
        byte[] bArr2 = this.thedata;
        int i2 = i + 8;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        int length2 = i2 + this.thedata.length;
        for (EscherRecord serialize : this._childRecords) {
            length2 += serialize.serialize(length2, bArr, escherSerializationListener);
        }
        int i3 = length2 - i;
        escherSerializationListener.afterRecordSerialize(length2, getRecordId(), i3, this);
        return i3;
    }

    public byte[] getData() {
        return this.thedata;
    }

    public int getRecordSize() {
        return this.thedata.length + 8;
    }

    public List<EscherRecord> getChildRecords() {
        return this._childRecords;
    }

    public void setChildRecords(List<EscherRecord> list) {
        List<EscherRecord> list2 = this._childRecords;
        if (list != list2) {
            list2.clear();
            this._childRecords.addAll(list);
        }
    }

    public String getRecordName() {
        return "Unknown 0x" + HexDump.toHex(getRecordId());
    }

    public void addChildRecord(EscherRecord escherRecord) {
        getChildRecords().add(escherRecord);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new UnknownEscherRecord$$ExternalSyntheticLambda0(this), Constants.ScionAnalytics.MessageType.DATA_MESSAGE, new UnknownEscherRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-UnknownEscherRecord  reason: not valid java name */
    public /* synthetic */ Object m1965lambda$getGenericProperties$0$orgapachepoiddfUnknownEscherRecord() {
        return super.getGenericProperties();
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.UNKNOWN;
    }

    public UnknownEscherRecord copy() {
        return new UnknownEscherRecord(this);
    }
}
