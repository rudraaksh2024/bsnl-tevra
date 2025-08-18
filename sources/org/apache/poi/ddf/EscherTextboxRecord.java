package org.apache.poi.ddf;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RecordFormatException;

public final class EscherTextboxRecord extends EscherRecord {
    private static int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static int MAX_RECORD_LENGTH = 100000;
    private static final byte[] NO_BYTES = new byte[0];
    public static final short RECORD_ID = EscherRecordTypes.CLIENT_TEXTBOX.typeID;
    private byte[] thedata;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public EscherTextboxRecord() {
        this.thedata = NO_BYTES;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EscherTextboxRecord(org.apache.poi.ddf.EscherTextboxRecord r2) {
        /*
            r1 = this;
            r1.<init>(r2)
            byte[] r0 = NO_BYTES
            r1.thedata = r0
            byte[] r2 = r2.thedata
            if (r2 != 0) goto L_0x000c
            goto L_0x0013
        L_0x000c:
            java.lang.Object r2 = r2.clone()
            r0 = r2
            byte[] r0 = (byte[]) r0
        L_0x0013:
            r1.thedata = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ddf.EscherTextboxRecord.<init>(org.apache.poi.ddf.EscherTextboxRecord):void");
    }

    public int fillFields(byte[] bArr, int i, EscherRecordFactory escherRecordFactory) {
        int readHeader = readHeader(bArr, i);
        this.thedata = IOUtils.safelyClone(bArr, i + 8, readHeader, MAX_RECORD_LENGTH);
        return readHeader + 8;
    }

    public int serialize(int i, byte[] bArr, EscherSerializationListener escherSerializationListener) {
        escherSerializationListener.beforeRecordSerialize(i, getRecordId(), this);
        LittleEndian.putShort(bArr, i, getOptions());
        LittleEndian.putShort(bArr, i + 2, getRecordId());
        LittleEndian.putInt(bArr, i + 4, this.thedata.length);
        byte[] bArr2 = this.thedata;
        int i2 = i + 8;
        System.arraycopy(bArr2, 0, bArr, i2, bArr2.length);
        int length = i2 + this.thedata.length;
        int i3 = length - i;
        escherSerializationListener.afterRecordSerialize(length, getRecordId(), i3, this);
        if (i3 == getRecordSize()) {
            return i3;
        }
        throw new RecordFormatException(i3 + " bytes written but getRecordSize() reports " + getRecordSize());
    }

    public byte[] getData() {
        return this.thedata;
    }

    public void setData(byte[] bArr, int i, int i2) {
        this.thedata = IOUtils.safelyClone(bArr, i, i2, MAX_RECORD_LENGTH);
    }

    public void setData(byte[] bArr) {
        setData(bArr, 0, bArr.length);
    }

    public int getRecordSize() {
        return this.thedata.length + 8;
    }

    public String getRecordName() {
        return EscherRecordTypes.CLIENT_TEXTBOX.recordName;
    }

    public Enum getGenericRecordType() {
        return EscherRecordTypes.CLIENT_TEXTBOX;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new EscherTextboxRecord$$ExternalSyntheticLambda0(this), "isContainer", new EscherTextboxRecord$$ExternalSyntheticLambda1(this), "extraData", new EscherTextboxRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ddf-EscherTextboxRecord  reason: not valid java name */
    public /* synthetic */ Object m1964lambda$getGenericProperties$0$orgapachepoiddfEscherTextboxRecord() {
        return super.getGenericProperties();
    }

    public EscherTextboxRecord copy() {
        return new EscherTextboxRecord(this);
    }
}
