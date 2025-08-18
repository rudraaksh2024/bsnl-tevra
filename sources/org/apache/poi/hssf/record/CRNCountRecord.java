package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CRNCountRecord extends StandardRecord {
    private static final short DATA_SIZE = 4;
    public static final short sid = 89;
    private int field_1_number_crn_records;
    private int field_2_sheet_table_index;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return 89;
    }

    public CRNCountRecord(CRNCountRecord cRNCountRecord) {
        super(cRNCountRecord);
        this.field_1_number_crn_records = cRNCountRecord.field_1_number_crn_records;
        this.field_2_sheet_table_index = cRNCountRecord.field_2_sheet_table_index;
    }

    public CRNCountRecord(RecordInputStream recordInputStream) {
        short readShort = recordInputStream.readShort();
        this.field_1_number_crn_records = readShort;
        if (readShort < 0) {
            this.field_1_number_crn_records = (short) (-readShort);
        }
        this.field_2_sheet_table_index = recordInputStream.readShort();
    }

    public int getNumberOfCRNs() {
        return this.field_1_number_crn_records;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort((short) this.field_1_number_crn_records);
        littleEndianOutput.writeShort((short) this.field_2_sheet_table_index);
    }

    public CRNCountRecord copy() {
        return new CRNCountRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CRN_COUNT;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("numberOfCRNs", new CRNCountRecord$$ExternalSyntheticLambda0(this), "sheetTableIndex", new CRNCountRecord$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-CRNCountRecord  reason: not valid java name */
    public /* synthetic */ Object m1981lambda$getGenericProperties$0$orgapachepoihssfrecordCRNCountRecord() {
        return Integer.valueOf(this.field_2_sheet_table_index);
    }
}
