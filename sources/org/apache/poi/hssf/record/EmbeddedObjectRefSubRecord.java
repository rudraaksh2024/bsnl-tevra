package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.SubRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ptg.MissingArgPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public final class EmbeddedObjectRefSubRecord extends SubRecord {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final Logger LOG = LogManager.getLogger((Class<?>) EmbeddedObjectRefSubRecord.class);
    public static final short sid = 9;
    private int field_1_unknown_int;
    private Ptg field_2_refPtg;
    private byte[] field_2_unknownFormulaData;
    private boolean field_3_unicode_flag;
    private String field_4_ole_classname;
    private Byte field_4_unknownByte;
    private Integer field_5_stream_id;
    private byte[] field_6_unknown;

    public short getSid() {
        return 9;
    }

    public EmbeddedObjectRefSubRecord() {
        this.field_2_unknownFormulaData = new byte[]{2, 108, 106, MissingArgPtg.sid, 1};
        this.field_6_unknown = EMPTY_BYTE_ARRAY;
        this.field_4_ole_classname = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EmbeddedObjectRefSubRecord(org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            int r0 = r3.field_1_unknown_int
            r2.field_1_unknown_int = r0
            org.apache.poi.ss.formula.ptg.Ptg r0 = r3.field_2_refPtg
            r1 = 0
            if (r0 != 0) goto L_0x000e
            r0 = r1
            goto L_0x0012
        L_0x000e:
            org.apache.poi.ss.formula.ptg.Ptg r0 = r0.copy()
        L_0x0012:
            r2.field_2_refPtg = r0
            byte[] r0 = r3.field_2_unknownFormulaData
            if (r0 != 0) goto L_0x001a
            r0 = r1
            goto L_0x0020
        L_0x001a:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0020:
            r2.field_2_unknownFormulaData = r0
            boolean r0 = r3.field_3_unicode_flag
            r2.field_3_unicode_flag = r0
            java.lang.String r0 = r3.field_4_ole_classname
            r2.field_4_ole_classname = r0
            java.lang.Byte r0 = r3.field_4_unknownByte
            r2.field_4_unknownByte = r0
            java.lang.Integer r0 = r3.field_5_stream_id
            r2.field_5_stream_id = r0
            byte[] r3 = r3.field_6_unknown
            if (r3 != 0) goto L_0x0037
            goto L_0x003e
        L_0x0037:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x003e:
            r2.field_6_unknown = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord.<init>(org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord):void");
    }

    public EmbeddedObjectRefSubRecord(LittleEndianInput littleEndianInput, int i) {
        this(littleEndianInput, i, -1);
    }

    EmbeddedObjectRefSubRecord(LittleEndianInput littleEndianInput, int i, int i2) {
        int i3 = i - 2;
        int readShort = i3 - littleEndianInput.readShort();
        int readUShort = littleEndianInput.readUShort();
        this.field_1_unknown_int = littleEndianInput.readInt();
        byte[] readRawData = readRawData(littleEndianInput, readUShort);
        int i4 = ((i3 - 2) - 4) - readUShort;
        Ptg readRefPtg = readRefPtg(readRawData);
        this.field_2_refPtg = readRefPtg;
        if (readRefPtg == null) {
            this.field_2_unknownFormulaData = readRawData;
        } else {
            this.field_2_unknownFormulaData = null;
        }
        boolean z = false;
        if (i4 < readShort + 3) {
            this.field_4_ole_classname = null;
        } else if (littleEndianInput.readByte() == 3) {
            int readUShort2 = littleEndianInput.readUShort();
            if (readUShort2 > 0) {
                z = (littleEndianInput.readByte() & 1) != 0 ? true : z;
                this.field_3_unicode_flag = z;
                if (z) {
                    this.field_4_ole_classname = StringUtil.readUnicodeLE(littleEndianInput, readUShort2);
                    readUShort2 *= 2;
                } else {
                    this.field_4_ole_classname = StringUtil.readCompressedUnicode(littleEndianInput, readUShort2);
                }
                z = readUShort2 + 4;
            } else {
                this.field_4_ole_classname = "";
                z = true;
            }
        } else {
            throw new RecordFormatException("Expected byte 0x03 here");
        }
        int i5 = i4 - (z ? 1 : 0);
        if ((z + readUShort) % 2 != 0) {
            byte readByte = littleEndianInput.readByte();
            i5--;
            if (this.field_2_refPtg != null && this.field_4_ole_classname == null) {
                this.field_4_unknownByte = Byte.valueOf((byte) readByte);
            }
        }
        int i6 = i5 - readShort;
        if (i6 > 0) {
            LOG.atError().log("Discarding {} unexpected padding bytes", (Object) Unbox.box(i6));
            readRawData(littleEndianInput, i6);
            i5 -= i6;
        }
        if (readShort >= 4) {
            this.field_5_stream_id = Integer.valueOf(littleEndianInput.readInt());
            i5 -= 4;
        } else {
            this.field_5_stream_id = null;
        }
        this.field_6_unknown = readRawData(littleEndianInput, i5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0049, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0052, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.poi.ss.formula.ptg.Ptg readRefPtg(byte[] r2) {
        /*
            org.apache.poi.util.LittleEndianInputStream r0 = new org.apache.poi.util.LittleEndianInputStream     // Catch:{ IOException -> 0x0053 }
            org.apache.commons.io.input.UnsynchronizedByteArrayInputStream r1 = new org.apache.commons.io.input.UnsynchronizedByteArrayInputStream     // Catch:{ IOException -> 0x0053 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0053 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0053 }
            byte r2 = r0.readByte()     // Catch:{ all -> 0x0047 }
            r1 = 36
            if (r2 == r1) goto L_0x003e
            r1 = 37
            if (r2 == r1) goto L_0x0035
            r1 = 58
            if (r2 == r1) goto L_0x002c
            r1 = 59
            if (r2 == r1) goto L_0x0023
            r0.close()     // Catch:{ IOException -> 0x0053 }
            r2 = 0
            return r2
        L_0x0023:
            org.apache.poi.ss.formula.ptg.Area3DPtg r2 = new org.apache.poi.ss.formula.ptg.Area3DPtg     // Catch:{ all -> 0x0047 }
            r2.<init>((org.apache.poi.util.LittleEndianInput) r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ IOException -> 0x0053 }
            return r2
        L_0x002c:
            org.apache.poi.ss.formula.ptg.Ref3DPtg r2 = new org.apache.poi.ss.formula.ptg.Ref3DPtg     // Catch:{ all -> 0x0047 }
            r2.<init>((org.apache.poi.util.LittleEndianInput) r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ IOException -> 0x0053 }
            return r2
        L_0x0035:
            org.apache.poi.ss.formula.ptg.AreaPtg r2 = new org.apache.poi.ss.formula.ptg.AreaPtg     // Catch:{ all -> 0x0047 }
            r2.<init>((org.apache.poi.util.LittleEndianInput) r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ IOException -> 0x0053 }
            return r2
        L_0x003e:
            org.apache.poi.ss.formula.ptg.RefPtg r2 = new org.apache.poi.ss.formula.ptg.RefPtg     // Catch:{ all -> 0x0047 }
            r2.<init>((org.apache.poi.util.LittleEndianInput) r0)     // Catch:{ all -> 0x0047 }
            r0.close()     // Catch:{ IOException -> 0x0053 }
            return r2
        L_0x0047:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ IOException -> 0x0053 }
        L_0x0052:
            throw r1     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            r2 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "Unexpected exception in readRefPtg"
            r0.<init>(r1, r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.EmbeddedObjectRefSubRecord.readRefPtg(byte[]):org.apache.poi.ss.formula.ptg.Ptg");
    }

    private static byte[] readRawData(LittleEndianInput littleEndianInput, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Negative size (" + i + ")");
        } else if (i == 0) {
            return EMPTY_BYTE_ARRAY;
        } else {
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) i, HSSFWorkbook.getMaxRecordLength());
            littleEndianInput.readFully(safelyAllocate);
            return safelyAllocate;
        }
    }

    private int getStreamIDOffset(int i) {
        int i2 = i + 6;
        String str = this.field_4_ole_classname;
        if (str != null) {
            i2 += 3;
            int length = str.length();
            if (length > 0) {
                int i3 = i2 + 1;
                if (this.field_3_unicode_flag) {
                    length *= 2;
                }
                i2 = i3 + length;
            }
        }
        return i2 % 2 != 0 ? i2 + 1 : i2;
    }

    private int getDataSize(int i) {
        int i2 = i + 2;
        if (this.field_5_stream_id != null) {
            i2 += 4;
        }
        return i2 + this.field_6_unknown.length;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        Ptg ptg = this.field_2_refPtg;
        return getDataSize(getStreamIDOffset(ptg == null ? this.field_2_unknownFormulaData.length : ptg.getSize()));
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        Ptg ptg = this.field_2_refPtg;
        int length = ptg == null ? this.field_2_unknownFormulaData.length : ptg.getSize();
        int streamIDOffset = getStreamIDOffset(length);
        int dataSize = getDataSize(streamIDOffset);
        littleEndianOutput.writeShort(9);
        littleEndianOutput.writeShort(dataSize);
        littleEndianOutput.writeShort(streamIDOffset);
        littleEndianOutput.writeShort(length);
        littleEndianOutput.writeInt(this.field_1_unknown_int);
        Ptg ptg2 = this.field_2_refPtg;
        if (ptg2 == null) {
            littleEndianOutput.write(this.field_2_unknownFormulaData);
        } else {
            ptg2.write(littleEndianOutput);
        }
        int i = length + 12;
        if (this.field_4_ole_classname != null) {
            littleEndianOutput.writeByte(3);
            int length2 = this.field_4_ole_classname.length();
            littleEndianOutput.writeShort(length2);
            i = i + 1 + 2;
            if (length2 > 0) {
                littleEndianOutput.writeByte(this.field_3_unicode_flag ? 1 : 0);
                int i2 = i + 1;
                if (this.field_3_unicode_flag) {
                    StringUtil.putUnicodeLE(this.field_4_ole_classname, littleEndianOutput);
                    length2 *= 2;
                } else {
                    StringUtil.putCompressedUnicode(this.field_4_ole_classname, littleEndianOutput);
                }
                i = i2 + length2;
            }
        }
        int i3 = streamIDOffset - (i - 6);
        if (i3 != 0) {
            if (i3 == 1) {
                Byte b = this.field_4_unknownByte;
                littleEndianOutput.writeByte(b == null ? 0 : b.intValue());
            } else {
                throw new IllegalStateException("Bad padding calculation (" + streamIDOffset + ", " + i + ")");
            }
        }
        Integer num = this.field_5_stream_id;
        if (num != null) {
            littleEndianOutput.writeInt(num.intValue());
        }
        littleEndianOutput.write(this.field_6_unknown);
    }

    public Integer getStreamId() {
        return this.field_5_stream_id;
    }

    public String getOLEClassName() {
        return this.field_4_ole_classname;
    }

    public byte[] getObjectData() {
        return this.field_6_unknown;
    }

    public EmbeddedObjectRefSubRecord copy() {
        return new EmbeddedObjectRefSubRecord(this);
    }

    public void setUnknownFormulaData(byte[] bArr) {
        this.field_2_unknownFormulaData = bArr;
    }

    public void setOleClassname(String str) {
        this.field_4_ole_classname = str;
    }

    public void setStorageId(int i) {
        this.field_5_stream_id = Integer.valueOf(i);
    }

    public SubRecord.SubRecordTypes getGenericRecordType() {
        return SubRecord.SubRecordTypes.EMBEDDED_OBJECT_REF;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("f2unknown", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda0(this), "f3unknown", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda1(this), "formula", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda2(this), "unicodeFlag", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda3(this), "oleClassname", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda4(this), "f4unknown", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda5(this), "streamId", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda6(this), "f7unknown", new EmbeddedObjectRefSubRecord$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2004lambda$getGenericProperties$0$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return Integer.valueOf(this.field_1_unknown_int);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2005lambda$getGenericProperties$1$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_2_unknownFormulaData;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2006lambda$getGenericProperties$2$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_2_refPtg;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$3$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2007lambda$getGenericProperties$3$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return Boolean.valueOf(this.field_3_unicode_flag);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$4$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2008lambda$getGenericProperties$4$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_4_ole_classname;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$5$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2009lambda$getGenericProperties$5$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_4_unknownByte;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$6$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2010lambda$getGenericProperties$6$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_5_stream_id;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$7$org-apache-poi-hssf-record-EmbeddedObjectRefSubRecord  reason: not valid java name */
    public /* synthetic */ Object m2011lambda$getGenericProperties$7$orgapachepoihssfrecordEmbeddedObjectRefSubRecord() {
        return this.field_6_unknown;
    }
}
