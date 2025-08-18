package org.apache.poi.hssf.record;

import java.io.IOException;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.RecordFormatException;

public final class OldSheetRecord implements GenericRecord {
    public static final short sid = 133;
    private CodepageRecord codepage;
    private final int field_1_position_of_BOF;
    private final int field_2_visibility;
    private final int field_3_type;
    private final byte[] field_5_sheetname;

    public short getSid() {
        return 133;
    }

    public OldSheetRecord(RecordInputStream recordInputStream) {
        this.field_1_position_of_BOF = recordInputStream.readInt();
        this.field_2_visibility = recordInputStream.readUByte();
        this.field_3_type = recordInputStream.readUByte();
        int readUByte = recordInputStream.readUByte();
        if (readUByte > 0) {
            recordInputStream.mark(1);
            if (recordInputStream.readByte() != 0) {
                try {
                    recordInputStream.reset();
                } catch (IOException e) {
                    throw new RecordFormatException((Throwable) e);
                }
            }
        }
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) readUByte, HSSFWorkbook.getMaxRecordLength());
        this.field_5_sheetname = safelyAllocate;
        recordInputStream.read(safelyAllocate, 0, readUByte);
    }

    public void setCodePage(CodepageRecord codepageRecord) {
        this.codepage = codepageRecord;
    }

    public int getPositionOfBof() {
        return this.field_1_position_of_BOF;
    }

    public String getSheetname() {
        return OldStringRecord.getString(this.field_5_sheetname, this.codepage);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOUND_SHEET;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("bof", new OldSheetRecord$$ExternalSyntheticLambda0(this), "visibility", new OldSheetRecord$$ExternalSyntheticLambda1(this), "type", new OldSheetRecord$$ExternalSyntheticLambda2(this), "sheetName", new OldSheetRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldSheetRecord  reason: not valid java name */
    public /* synthetic */ Object m2066lambda$getGenericProperties$0$orgapachepoihssfrecordOldSheetRecord() {
        return Integer.valueOf(this.field_2_visibility);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-OldSheetRecord  reason: not valid java name */
    public /* synthetic */ Object m2067lambda$getGenericProperties$1$orgapachepoihssfrecordOldSheetRecord() {
        return Integer.valueOf(this.field_3_type);
    }

    public String toString() {
        return GenericRecordJsonWriter.marshal(this);
    }
}
