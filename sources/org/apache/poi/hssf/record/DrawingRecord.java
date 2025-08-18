package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class DrawingRecord extends StandardRecord {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short sid = 236;
    private byte[] contd;
    private byte[] recordData;

    public short getSid() {
        return 236;
    }

    public DrawingRecord() {
        this.recordData = EMPTY_BYTE_ARRAY;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DrawingRecord(org.apache.poi.hssf.record.DrawingRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            byte[] r0 = r3.recordData
            r1 = 0
            if (r0 != 0) goto L_0x000a
            r0 = r1
            goto L_0x0010
        L_0x000a:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0010:
            r2.recordData = r0
            byte[] r3 = r3.contd
            if (r3 != 0) goto L_0x0017
            goto L_0x001e
        L_0x0017:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x001e:
            r2.contd = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.DrawingRecord.<init>(org.apache.poi.hssf.record.DrawingRecord):void");
    }

    public DrawingRecord(RecordInputStream recordInputStream) {
        this.recordData = recordInputStream.readRemainder();
    }

    public DrawingRecord(byte[] bArr) {
        this.recordData = (byte[]) bArr.clone();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public void processContinueRecord(byte[] bArr) {
        this.contd = bArr;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.write(this.recordData);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return this.recordData.length;
    }

    public byte[] getRecordData() {
        return this.recordData;
    }

    public void setData(byte[] bArr) {
        if (bArr != null) {
            this.recordData = bArr;
            return;
        }
        throw new IllegalArgumentException("data must not be null");
    }

    public DrawingRecord copy() {
        return new DrawingRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DRAWING;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("recordData", new DrawingRecord$$ExternalSyntheticLambda0(this), "contd", new DrawingRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DrawingRecord  reason: not valid java name */
    public /* synthetic */ Object m1995lambda$getGenericProperties$0$orgapachepoihssfrecordDrawingRecord() {
        return this.contd;
    }
}
