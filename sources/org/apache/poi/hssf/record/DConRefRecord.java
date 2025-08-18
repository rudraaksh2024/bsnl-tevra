package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.RecordFormatException;
import org.apache.poi.util.StringUtil;

public class DConRefRecord extends StandardRecord {
    public static final short sid = 81;
    private byte[] _unused;
    private final int charCount;
    private final int charType;
    private final int firstCol;
    private final int firstRow;
    private final int lastCol;
    private final int lastRow;
    private final byte[] path;

    public short getSid() {
        return 81;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: byte[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DConRefRecord(org.apache.poi.hssf.record.DConRefRecord r3) {
        /*
            r2 = this;
            r2.<init>(r3)
            int r0 = r3.firstCol
            r2.firstCol = r0
            int r0 = r3.firstRow
            r2.firstRow = r0
            int r0 = r3.lastCol
            r2.lastCol = r0
            int r0 = r3.lastRow
            r2.lastRow = r0
            int r0 = r3.charCount
            r2.charCount = r0
            int r0 = r3.charType
            r2.charType = r0
            byte[] r0 = r3.path
            r1 = 0
            if (r0 != 0) goto L_0x0022
            r0 = r1
            goto L_0x0028
        L_0x0022:
            java.lang.Object r0 = r0.clone()
            byte[] r0 = (byte[]) r0
        L_0x0028:
            r2.path = r0
            byte[] r3 = r3._unused
            if (r3 != 0) goto L_0x002f
            goto L_0x0036
        L_0x002f:
            java.lang.Object r3 = r3.clone()
            r1 = r3
            byte[] r1 = (byte[]) r1
        L_0x0036:
            r2._unused = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.record.DConRefRecord.<init>(org.apache.poi.hssf.record.DConRefRecord):void");
    }

    public DConRefRecord(byte[] bArr) {
        this(bytesToRIStream(bArr));
    }

    public DConRefRecord(RecordInputStream recordInputStream) {
        if (recordInputStream.getSid() == 81) {
            this.firstRow = recordInputStream.readUShort();
            this.lastRow = recordInputStream.readUShort();
            this.firstCol = recordInputStream.readUByte();
            this.lastCol = recordInputStream.readUByte();
            int readUShort = recordInputStream.readUShort();
            this.charCount = readUShort;
            int readUByte = recordInputStream.readUByte() & 1;
            this.charType = readUByte;
            byte[] safelyAllocate = IOUtils.safelyAllocate((long) (readUShort * (readUByte + 1)), HSSFWorkbook.getMaxRecordLength());
            this.path = safelyAllocate;
            recordInputStream.readFully(safelyAllocate);
            if (safelyAllocate[0] == 2) {
                this._unused = recordInputStream.readRemainder();
                return;
            }
            return;
        }
        throw new RecordFormatException("Wrong sid: " + recordInputStream.getSid());
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        byte[] bArr = this.path;
        int length = bArr.length + 9;
        return bArr[0] == 2 ? length + this._unused.length : length;
    }

    /* access modifiers changed from: protected */
    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.firstRow);
        littleEndianOutput.writeShort(this.lastRow);
        littleEndianOutput.writeByte(this.firstCol);
        littleEndianOutput.writeByte(this.lastCol);
        littleEndianOutput.writeShort(this.charCount);
        littleEndianOutput.writeByte(this.charType);
        littleEndianOutput.write(this.path);
        if (this.path[0] == 2) {
            littleEndianOutput.write(this._unused);
        }
    }

    public int getFirstColumn() {
        return this.firstCol;
    }

    public int getFirstRow() {
        return this.firstRow;
    }

    public int getLastColumn() {
        return this.lastCol;
    }

    public int getLastRow() {
        return this.lastRow;
    }

    public byte[] getPath() {
        byte[] bArr = this.path;
        return Arrays.copyOf(bArr, bArr.length);
    }

    public String getReadablePath() {
        if (this.path == null) {
            return null;
        }
        int i = 1;
        while (true) {
            byte[] bArr = this.path;
            if (i >= bArr.length || bArr[i] >= 32) {
                byte[] bArr2 = this.path;
            } else {
                i++;
            }
        }
        byte[] bArr22 = this.path;
        return new String(Arrays.copyOfRange(bArr22, i, bArr22.length), StringUtil.UTF8).replace("\u0003", PackagingURIHelper.FORWARD_SLASH_STRING);
    }

    public boolean isExternalRef() {
        return this.path[0] == 1;
    }

    public DConRefRecord copy() {
        return new DConRefRecord(this);
    }

    private static RecordInputStream bytesToRIStream(byte[] bArr) {
        RecordInputStream recordInputStream = new RecordInputStream(new UnsynchronizedByteArrayInputStream(bArr));
        recordInputStream.nextRecord();
        return recordInputStream;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DCON_REF;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("firstRow", new DConRefRecord$$ExternalSyntheticLambda0(this), "lastRow", new DConRefRecord$$ExternalSyntheticLambda1(this), "firstColumn", new DConRefRecord$$ExternalSyntheticLambda2(this), "lastColumn", new DConRefRecord$$ExternalSyntheticLambda3(this), "charCount", new DConRefRecord$$ExternalSyntheticLambda4(this), "charType", new DConRefRecord$$ExternalSyntheticLambda5(this), "path", new DConRefRecord$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DConRefRecord  reason: not valid java name */
    public /* synthetic */ Object m1989lambda$getGenericProperties$0$orgapachepoihssfrecordDConRefRecord() {
        return Integer.valueOf(this.charCount);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DConRefRecord  reason: not valid java name */
    public /* synthetic */ Object m1990lambda$getGenericProperties$1$orgapachepoihssfrecordDConRefRecord() {
        return Integer.valueOf(this.charType);
    }
}
