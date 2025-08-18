package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.RecordFormatException;

public final class ObjRecord extends Record {
    private static int MAX_PAD_ALIGNMENT = 4;
    private static final int NORMAL_PAD_ALIGNMENT = 2;
    public static final short sid = 93;
    private boolean _isPaddedToQuadByteMultiple;
    private final byte[] _uninterpretedData;
    private final List<SubRecord> subrecords;

    public short getSid() {
        return 93;
    }

    public ObjRecord() {
        this.subrecords = new ArrayList();
        this._uninterpretedData = null;
    }

    public ObjRecord(ObjRecord objRecord) {
        ArrayList arrayList = new ArrayList();
        this.subrecords = arrayList;
        objRecord.subrecords.stream().map(new ObjRecord$$ExternalSyntheticLambda2()).forEach(new ObjRecord$$ExternalSyntheticLambda3(arrayList));
        byte[] bArr = objRecord._uninterpretedData;
        this._uninterpretedData = bArr == null ? null : (byte[]) bArr.clone();
        this._isPaddedToQuadByteMultiple = objRecord._isPaddedToQuadByteMultiple;
    }

    public ObjRecord(RecordInputStream recordInputStream) {
        SubRecord createSubRecord;
        ArrayList arrayList = new ArrayList();
        this.subrecords = arrayList;
        byte[] readRemainder = recordInputStream.readRemainder();
        if (LittleEndian.getUShort(readRemainder, 0) != 21) {
            this._uninterpretedData = readRemainder;
            return;
        }
        LittleEndianByteArrayInputStream littleEndianByteArrayInputStream = new LittleEndianByteArrayInputStream(readRemainder);
        CommonObjectDataSubRecord commonObjectDataSubRecord = (CommonObjectDataSubRecord) SubRecord.createSubRecord(littleEndianByteArrayInputStream, 0);
        arrayList.add(commonObjectDataSubRecord);
        do {
            createSubRecord = SubRecord.createSubRecord(littleEndianByteArrayInputStream, commonObjectDataSubRecord.getObjectType());
            this.subrecords.add(createSubRecord);
        } while (!createSubRecord.isTerminating());
        int length = readRemainder.length - littleEndianByteArrayInputStream.getReadIndex();
        if (length > 0) {
            int length2 = readRemainder.length;
            int i = MAX_PAD_ALIGNMENT;
            boolean z = length2 % i == 0;
            this._isPaddedToQuadByteMultiple = z;
            if (length >= (!z ? 2 : i)) {
                if (canPaddingBeDiscarded(readRemainder, length)) {
                    this._isPaddedToQuadByteMultiple = false;
                } else {
                    throw new RecordFormatException("Leftover " + length + " bytes in subrecord data " + HexDump.toHex(readRemainder));
                }
            }
        } else {
            this._isPaddedToQuadByteMultiple = false;
        }
        this._uninterpretedData = null;
    }

    private static boolean canPaddingBeDiscarded(byte[] bArr, int i) {
        for (int length = bArr.length - i; length < bArr.length; length++) {
            if (bArr[length] != 0) {
                return false;
            }
        }
        return true;
    }

    public int getRecordSize() {
        byte[] bArr = this._uninterpretedData;
        if (bArr != null) {
            return bArr.length + 4;
        }
        int i = 0;
        for (SubRecord dataSize : this.subrecords) {
            i += dataSize.getDataSize() + 4;
        }
        if (this._isPaddedToQuadByteMultiple) {
            while (i % MAX_PAD_ALIGNMENT != 0) {
                i++;
            }
        } else {
            while (i % 2 != 0) {
                i++;
            }
        }
        return i + 4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0043, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004c, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int serialize(int r6, byte[] r7) {
        /*
            r5 = this;
            int r0 = r5.getRecordSize()
            int r1 = r0 + -4
            org.apache.poi.util.LittleEndianByteArrayOutputStream r2 = new org.apache.poi.util.LittleEndianByteArrayOutputStream     // Catch:{ IOException -> 0x004d }
            r2.<init>(r7, r6, r0)     // Catch:{ IOException -> 0x004d }
            r7 = 93
            r2.writeShort(r7)     // Catch:{ all -> 0x0041 }
            r2.writeShort(r1)     // Catch:{ all -> 0x0041 }
            byte[] r7 = r5._uninterpretedData     // Catch:{ all -> 0x0041 }
            if (r7 != 0) goto L_0x003a
            r7 = 0
            r3 = r7
        L_0x0019:
            java.util.List<org.apache.poi.hssf.record.SubRecord> r4 = r5.subrecords     // Catch:{ all -> 0x0041 }
            int r4 = r4.size()     // Catch:{ all -> 0x0041 }
            if (r3 >= r4) goto L_0x002f
            java.util.List<org.apache.poi.hssf.record.SubRecord> r4 = r5.subrecords     // Catch:{ all -> 0x0041 }
            java.lang.Object r4 = r4.get(r3)     // Catch:{ all -> 0x0041 }
            org.apache.poi.hssf.record.SubRecord r4 = (org.apache.poi.hssf.record.SubRecord) r4     // Catch:{ all -> 0x0041 }
            r4.serialize(r2)     // Catch:{ all -> 0x0041 }
            int r3 = r3 + 1
            goto L_0x0019
        L_0x002f:
            int r6 = r6 + r1
        L_0x0030:
            int r5 = r2.getWriteIndex()     // Catch:{ all -> 0x0041 }
            if (r5 >= r6) goto L_0x003d
            r2.writeByte(r7)     // Catch:{ all -> 0x0041 }
            goto L_0x0030
        L_0x003a:
            r2.write((byte[]) r7)     // Catch:{ all -> 0x0041 }
        L_0x003d:
            r2.close()     // Catch:{ IOException -> 0x004d }
            return r0
        L_0x0041:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r6 = move-exception
            r2.close()     // Catch:{ all -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch:{ IOException -> 0x004d }
        L_0x004c:
            throw r6     // Catch:{ IOException -> 0x004d }
        L_0x004d:
            r5 = move-exception
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            r6.<init>(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.ObjRecord.serialize(int, byte[]):int");
    }

    public List<SubRecord> getSubRecords() {
        return this.subrecords;
    }

    public void clearSubRecords() {
        this.subrecords.clear();
    }

    public void addSubRecord(int i, SubRecord subRecord) {
        this.subrecords.add(i, subRecord);
    }

    public boolean addSubRecord(SubRecord subRecord) {
        return this.subrecords.add(subRecord);
    }

    public ObjRecord copy() {
        return new ObjRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.OBJ;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("uninterpretedData", new ObjRecord$$ExternalSyntheticLambda0(this), "paddedToQuadByteMultiple", new ObjRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-ObjRecord  reason: not valid java name */
    public /* synthetic */ Object m2062lambda$getGenericProperties$0$orgapachepoihssfrecordObjRecord() {
        return this._uninterpretedData;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-ObjRecord  reason: not valid java name */
    public /* synthetic */ Object m2063lambda$getGenericProperties$1$orgapachepoihssfrecordObjRecord() {
        return Boolean.valueOf(this._isPaddedToQuadByteMultiple);
    }

    public List<SubRecord> getGenericChildren() {
        return getSubRecords();
    }
}
