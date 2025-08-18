package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.SharedFormula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class SharedFormulaRecord extends SharedValueRecordBase {
    public static final short sid = 1212;
    private int field_5_reserved;
    private Formula field_7_parsed_expr;

    public short getSid() {
        return sid;
    }

    public SharedFormulaRecord() {
        this(new CellRangeAddress8Bit(0, 0, 0, 0));
    }

    public SharedFormulaRecord(SharedFormulaRecord sharedFormulaRecord) {
        super((SharedValueRecordBase) sharedFormulaRecord);
        this.field_5_reserved = sharedFormulaRecord.field_5_reserved;
        Formula formula = sharedFormulaRecord.field_7_parsed_expr;
        this.field_7_parsed_expr = formula == null ? null : formula.copy();
    }

    private SharedFormulaRecord(CellRangeAddress8Bit cellRangeAddress8Bit) {
        super(cellRangeAddress8Bit);
        this.field_7_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    public SharedFormulaRecord(RecordInputStream recordInputStream) {
        super((LittleEndianInput) recordInputStream);
        this.field_5_reserved = recordInputStream.readShort();
        this.field_7_parsed_expr = Formula.read(recordInputStream.readShort(), recordInputStream, recordInputStream.available());
    }

    /* access modifiers changed from: protected */
    public void serializeExtraData(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_5_reserved);
        this.field_7_parsed_expr.serialize(littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public int getExtraDataSize() {
        return this.field_7_parsed_expr.getEncodedSize() + 2;
    }

    public Ptg[] getFormulaTokens(FormulaRecord formulaRecord) {
        int row = formulaRecord.getRow();
        short column = formulaRecord.getColumn();
        if (isInRange(row, column)) {
            return new SharedFormula(SpreadsheetVersion.EXCEL97).convertSharedFormulas(this.field_7_parsed_expr.getTokens(), row, column);
        }
        throw new RuntimeException("Shared Formula Conversion: Coding Error");
    }

    public SharedFormulaRecord copy() {
        return new SharedFormulaRecord(this);
    }

    public boolean isFormulaSame(SharedFormulaRecord sharedFormulaRecord) {
        return this.field_7_parsed_expr.isSame(sharedFormulaRecord.field_7_parsed_expr);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SHARED_FORMULA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("range", new SharedFormulaRecord$$ExternalSyntheticLambda0(this), "reserved", new SharedFormulaRecord$$ExternalSyntheticLambda1(this), "formula", new SharedFormulaRecord$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-SharedFormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2087lambda$getGenericProperties$0$orgapachepoihssfrecordSharedFormulaRecord() {
        return Integer.valueOf(this.field_5_reserved);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-SharedFormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2088lambda$getGenericProperties$1$orgapachepoihssfrecordSharedFormulaRecord() {
        return this.field_7_parsed_expr;
    }
}
