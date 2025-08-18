package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.GenericRecordUtil;

public final class OldFormulaRecord extends OldCellRecord {
    public static final short biff2_sid = 6;
    public static final short biff3_sid = 518;
    public static final short biff4_sid = 1030;
    public static final short biff5_sid = 6;
    private double field_4_value;
    private short field_5_options;
    private Formula field_6_parsed_expr;
    private FormulaSpecialCachedValue specialCachedValue;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OldFormulaRecord(RecordInputStream recordInputStream) {
        super(recordInputStream, recordInputStream.getSid() == 6);
        if (isBiff2()) {
            this.field_4_value = recordInputStream.readDouble();
        } else {
            long readLong = recordInputStream.readLong();
            FormulaSpecialCachedValue create = FormulaSpecialCachedValue.create(readLong);
            this.specialCachedValue = create;
            if (create == null) {
                this.field_4_value = Double.longBitsToDouble(readLong);
            }
        }
        if (isBiff2()) {
            this.field_5_options = (short) recordInputStream.readUByte();
        } else {
            this.field_5_options = recordInputStream.readShort();
        }
        this.field_6_parsed_expr = Formula.read(recordInputStream.readShort(), recordInputStream, recordInputStream.available());
    }

    @Deprecated
    public int getCachedResultType() {
        FormulaSpecialCachedValue formulaSpecialCachedValue = this.specialCachedValue;
        if (formulaSpecialCachedValue == null) {
            return CellType.NUMERIC.getCode();
        }
        return formulaSpecialCachedValue.getValueType();
    }

    public CellType getCachedResultTypeEnum() {
        FormulaSpecialCachedValue formulaSpecialCachedValue = this.specialCachedValue;
        if (formulaSpecialCachedValue == null) {
            return CellType.NUMERIC;
        }
        return formulaSpecialCachedValue.getValueTypeEnum();
    }

    public boolean getCachedBooleanValue() {
        return this.specialCachedValue.getBooleanValue();
    }

    public int getCachedErrorValue() {
        return this.specialCachedValue.getErrorValue();
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public Ptg[] getParsedExpression() {
        return this.field_6_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_6_parsed_expr;
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMULA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new OldFormulaRecord$$ExternalSyntheticLambda0(this), "options", new OldFormulaRecord$$ExternalSyntheticLambda1(this), "formula", new OldFormulaRecord$$ExternalSyntheticLambda2(this), "value", new OldFormulaRecord$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-OldFormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2064lambda$getGenericProperties$0$orgapachepoihssfrecordOldFormulaRecord() {
        return super.getGenericProperties();
    }
}
