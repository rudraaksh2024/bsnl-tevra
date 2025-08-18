package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class FormulaRecord extends CellRecord {
    private static final int FIXED_SIZE = 14;
    private static final BitField alwaysCalc = BitFieldFactory.getInstance(1);
    private static final BitField calcOnLoad = BitFieldFactory.getInstance(2);
    private static final BitField sharedFormula = BitFieldFactory.getInstance(8);
    public static final short sid = 6;
    private double field_4_value;
    private short field_5_options;
    private int field_6_zero;
    private Formula field_8_parsed_expr;
    private FormulaSpecialCachedValue specialCachedValue;

    /* access modifiers changed from: protected */
    public String getRecordName() {
        return "FORMULA";
    }

    public short getSid() {
        return 6;
    }

    public FormulaRecord() {
        this.field_8_parsed_expr = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    public FormulaRecord(FormulaRecord formulaRecord) {
        super((CellRecord) formulaRecord);
        this.field_4_value = formulaRecord.field_4_value;
        this.field_5_options = formulaRecord.field_5_options;
        this.field_6_zero = formulaRecord.field_6_zero;
        FormulaSpecialCachedValue formulaSpecialCachedValue = null;
        this.field_8_parsed_expr = formulaRecord.field_8_parsed_expr == null ? null : new Formula(formulaRecord.field_8_parsed_expr);
        this.specialCachedValue = formulaRecord.specialCachedValue != null ? new FormulaSpecialCachedValue(formulaRecord.specialCachedValue) : formulaSpecialCachedValue;
    }

    public FormulaRecord(RecordInputStream recordInputStream) {
        super(recordInputStream);
        long readLong = recordInputStream.readLong();
        this.field_5_options = recordInputStream.readShort();
        FormulaSpecialCachedValue create = FormulaSpecialCachedValue.create(readLong);
        this.specialCachedValue = create;
        if (create == null) {
            this.field_4_value = Double.longBitsToDouble(readLong);
        }
        this.field_6_zero = recordInputStream.readInt();
        this.field_8_parsed_expr = Formula.read(recordInputStream.readShort(), recordInputStream, recordInputStream.available());
    }

    public void setValue(double d) {
        this.field_4_value = d;
        this.specialCachedValue = null;
    }

    public void setCachedResultTypeEmptyString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedEmptyValue();
    }

    public void setCachedResultTypeString() {
        this.specialCachedValue = FormulaSpecialCachedValue.createForString();
    }

    public void setCachedResultErrorCode(int i) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedErrorCode(i);
    }

    public void setCachedResultBoolean(boolean z) {
        this.specialCachedValue = FormulaSpecialCachedValue.createCachedBoolean(z);
    }

    public boolean hasCachedResultString() {
        FormulaSpecialCachedValue formulaSpecialCachedValue = this.specialCachedValue;
        return formulaSpecialCachedValue != null && formulaSpecialCachedValue.getTypeCode() == 0;
    }

    @Deprecated
    @Removal(version = "6.0.0")
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

    public void setOptions(short s) {
        this.field_5_options = s;
    }

    public double getValue() {
        return this.field_4_value;
    }

    public short getOptions() {
        return this.field_5_options;
    }

    public boolean isSharedFormula() {
        return sharedFormula.isSet(this.field_5_options);
    }

    public void setSharedFormula(boolean z) {
        this.field_5_options = sharedFormula.setShortBoolean(this.field_5_options, z);
    }

    public boolean isAlwaysCalc() {
        return alwaysCalc.isSet(this.field_5_options);
    }

    public void setAlwaysCalc(boolean z) {
        this.field_5_options = alwaysCalc.setShortBoolean(this.field_5_options, z);
    }

    public boolean isCalcOnLoad() {
        return calcOnLoad.isSet(this.field_5_options);
    }

    public void setCalcOnLoad(boolean z) {
        this.field_5_options = calcOnLoad.setShortBoolean(this.field_5_options, z);
    }

    public Ptg[] getParsedExpression() {
        return this.field_8_parsed_expr.getTokens();
    }

    public Formula getFormula() {
        return this.field_8_parsed_expr;
    }

    public void setParsedExpression(Ptg[] ptgArr) {
        this.field_8_parsed_expr = Formula.create(ptgArr);
    }

    /* access modifiers changed from: protected */
    public int getValueDataSize() {
        return this.field_8_parsed_expr.getEncodedSize() + 14;
    }

    /* access modifiers changed from: protected */
    public void serializeValue(LittleEndianOutput littleEndianOutput) {
        FormulaSpecialCachedValue formulaSpecialCachedValue = this.specialCachedValue;
        if (formulaSpecialCachedValue == null) {
            littleEndianOutput.writeDouble(this.field_4_value);
        } else {
            formulaSpecialCachedValue.serialize(littleEndianOutput);
        }
        littleEndianOutput.writeShort(getOptions());
        littleEndianOutput.writeInt(this.field_6_zero);
        this.field_8_parsed_expr.serialize(littleEndianOutput);
    }

    public FormulaRecord copy() {
        return new FormulaRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.FORMULA;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new FormulaRecord$$ExternalSyntheticLambda0(this), "options", new FormulaRecord$$ExternalSyntheticLambda1(this), "alwaysCalc", new FormulaRecord$$ExternalSyntheticLambda2(this), "calcOnLoad", new FormulaRecord$$ExternalSyntheticLambda3(this), "shared", new FormulaRecord$$ExternalSyntheticLambda4(this), "zero", new FormulaRecord$$ExternalSyntheticLambda5(this), "value", new FormulaRecord$$ExternalSyntheticLambda6(this), "formula", new FormulaRecord$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-FormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2025lambda$getGenericProperties$0$orgapachepoihssfrecordFormulaRecord() {
        return super.getGenericProperties();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-FormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2026lambda$getGenericProperties$1$orgapachepoihssfrecordFormulaRecord() {
        return Integer.valueOf(this.field_6_zero);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$2$org-apache-poi-hssf-record-FormulaRecord  reason: not valid java name */
    public /* synthetic */ Object m2027lambda$getGenericProperties$2$orgapachepoihssfrecordFormulaRecord() {
        FormulaSpecialCachedValue formulaSpecialCachedValue = this.specialCachedValue;
        return formulaSpecialCachedValue == null ? Double.valueOf(this.field_4_value) : formulaSpecialCachedValue;
    }
}
