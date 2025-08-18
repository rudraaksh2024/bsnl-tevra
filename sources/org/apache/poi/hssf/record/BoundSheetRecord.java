package org.apache.poi.hssf.record;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class BoundSheetRecord extends StandardRecord {
    private static final BitField hiddenFlag = BitFieldFactory.getInstance(1);
    public static final short sid = 133;
    private static final BitField veryHiddenFlag = BitFieldFactory.getInstance(2);
    private int field_1_position_of_BOF;
    private int field_2_option_flags;
    private int field_4_isMultibyteUnicode;
    private String field_5_sheetname;

    public short getSid() {
        return 133;
    }

    public BoundSheetRecord(String str) {
        this.field_2_option_flags = 0;
        setSheetname(str);
    }

    public BoundSheetRecord(BoundSheetRecord boundSheetRecord) {
        super(boundSheetRecord);
        this.field_1_position_of_BOF = boundSheetRecord.field_1_position_of_BOF;
        this.field_2_option_flags = boundSheetRecord.field_2_option_flags;
        this.field_4_isMultibyteUnicode = boundSheetRecord.field_4_isMultibyteUnicode;
        this.field_5_sheetname = boundSheetRecord.field_5_sheetname;
    }

    public BoundSheetRecord(RecordInputStream recordInputStream) {
        byte[] bArr = new byte[4];
        recordInputStream.readPlain(bArr, 0, 4);
        this.field_1_position_of_BOF = LittleEndian.getInt(bArr);
        this.field_2_option_flags = recordInputStream.readUShort();
        int readUByte = recordInputStream.readUByte();
        this.field_4_isMultibyteUnicode = recordInputStream.readByte();
        if (isMultibyte()) {
            this.field_5_sheetname = recordInputStream.readUnicodeLEString(readUByte);
        } else {
            this.field_5_sheetname = recordInputStream.readCompressedUnicode(readUByte);
        }
    }

    public void setPositionOfBof(int i) {
        this.field_1_position_of_BOF = i;
    }

    public void setSheetname(String str) {
        WorkbookUtil.validateSheetName(str);
        this.field_5_sheetname = str;
        this.field_4_isMultibyteUnicode = StringUtil.hasMultibyte(str) ? 1 : 0;
    }

    public int getPositionOfBof() {
        return this.field_1_position_of_BOF;
    }

    /* access modifiers changed from: private */
    public boolean isMultibyte() {
        return (this.field_4_isMultibyteUnicode & 1) != 0;
    }

    public String getSheetname() {
        return this.field_5_sheetname;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return (this.field_5_sheetname.length() * (isMultibyte() ? 2 : 1)) + 8;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(getPositionOfBof());
        littleEndianOutput.writeShort(this.field_2_option_flags);
        String str = this.field_5_sheetname;
        littleEndianOutput.writeByte(str.length());
        littleEndianOutput.writeByte(this.field_4_isMultibyteUnicode);
        if (isMultibyte()) {
            StringUtil.putUnicodeLE(str, littleEndianOutput);
        } else {
            StringUtil.putCompressedUnicode(str, littleEndianOutput);
        }
    }

    public boolean isHidden() {
        return hiddenFlag.isSet(this.field_2_option_flags);
    }

    public void setHidden(boolean z) {
        this.field_2_option_flags = hiddenFlag.setBoolean(this.field_2_option_flags, z);
    }

    public boolean isVeryHidden() {
        return veryHiddenFlag.isSet(this.field_2_option_flags);
    }

    public void setVeryHidden(boolean z) {
        this.field_2_option_flags = veryHiddenFlag.setBoolean(this.field_2_option_flags, z);
    }

    public static BoundSheetRecord[] orderByBofPosition(List<BoundSheetRecord> list) {
        BoundSheetRecord[] boundSheetRecordArr = new BoundSheetRecord[list.size()];
        list.toArray(boundSheetRecordArr);
        Arrays.sort(boundSheetRecordArr, new BoundSheetRecord$$ExternalSyntheticLambda0());
        return boundSheetRecordArr;
    }

    /* access modifiers changed from: private */
    public static int compareRecords(BoundSheetRecord boundSheetRecord, BoundSheetRecord boundSheetRecord2) {
        return boundSheetRecord.getPositionOfBof() - boundSheetRecord2.getPositionOfBof();
    }

    public BoundSheetRecord copy() {
        return new BoundSheetRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOUND_SHEET;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("bof", new BoundSheetRecord$$ExternalSyntheticLambda1(this), "optionFlags", new BoundSheetRecord$$ExternalSyntheticLambda2(this), "multiByte", new BoundSheetRecord$$ExternalSyntheticLambda3(this), "sheetName", new BoundSheetRecord$$ExternalSyntheticLambda4(this), CellUtil.HIDDEN, new BoundSheetRecord$$ExternalSyntheticLambda5(this), "veryHidden", new BoundSheetRecord$$ExternalSyntheticLambda6(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-BoundSheetRecord  reason: not valid java name */
    public /* synthetic */ Object m1972lambda$getGenericProperties$0$orgapachepoihssfrecordBoundSheetRecord() {
        return Integer.valueOf(this.field_2_option_flags);
    }
}
