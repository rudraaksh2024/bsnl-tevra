package org.apache.poi.hssf.record;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.LittleEndianOutput;

public final class CFRuleRecord extends CFRuleBase {
    public static final short sid = 433;

    public short getSid() {
        return sid;
    }

    public CFRuleRecord(CFRuleRecord cFRuleRecord) {
        super(cFRuleRecord);
    }

    private CFRuleRecord(byte b, byte b2, Ptg[] ptgArr, Ptg[] ptgArr2) {
        super(b, b2, ptgArr, ptgArr2);
        setDefaults();
    }

    private void setDefaults() {
        this.formatting_options = modificationBits.setValue(this.formatting_options, -1);
        this.formatting_options = fmtBlockBits.setValue(this.formatting_options, 0);
        this.formatting_options = undocumented.clear(this.formatting_options);
        this.formatting_not_used = -32766;
        this._fontFormatting = null;
        this._borderFormatting = null;
        this._patternFormatting = null;
    }

    public static CFRuleRecord create(HSSFSheet hSSFSheet, String str) {
        return new CFRuleRecord((byte) 2, (byte) 0, parseFormula(str, hSSFSheet), (Ptg[]) null);
    }

    public static CFRuleRecord create(HSSFSheet hSSFSheet, byte b, String str, String str2) {
        return new CFRuleRecord((byte) 1, b, parseFormula(str, hSSFSheet), parseFormula(str2, hSSFSheet));
    }

    public CFRuleRecord(RecordInputStream recordInputStream) {
        setConditionType(recordInputStream.readByte());
        setComparisonOperation(recordInputStream.readByte());
        int readUShort = recordInputStream.readUShort();
        int readUShort2 = recordInputStream.readUShort();
        readFormatOptions(recordInputStream);
        setFormula1(Formula.read(readUShort, recordInputStream));
        setFormula2(Formula.read(readUShort2, recordInputStream));
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        int formulaSize = getFormulaSize(getFormula1());
        int formulaSize2 = getFormulaSize(getFormula2());
        littleEndianOutput.writeByte(getConditionType());
        littleEndianOutput.writeByte(getComparisonOperation());
        littleEndianOutput.writeShort(formulaSize);
        littleEndianOutput.writeShort(formulaSize2);
        serializeFormattingBlock(littleEndianOutput);
        getFormula1().serializeTokens(littleEndianOutput);
        getFormula2().serializeTokens(littleEndianOutput);
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return getFormattingBlockSize() + 6 + getFormulaSize(getFormula1()) + getFormulaSize(getFormula2());
    }

    public CFRuleRecord copy() {
        return new CFRuleRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.CF_RULE;
    }
}
