package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class Deleted3DPxg extends OperandPtg implements Pxg {
    private int externalWorkbookNumber;
    private String sheetName;

    public byte getDefaultOperandClass() {
        return 32;
    }

    public byte getSid() {
        return -1;
    }

    public int getSize() {
        return 1;
    }

    public Deleted3DPxg(int i, String str) {
        this.externalWorkbookNumber = i;
        this.sheetName = str;
    }

    public Deleted3DPxg(Deleted3DPxg deleted3DPxg) {
        super(deleted3DPxg);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = deleted3DPxg.externalWorkbookNumber;
        this.sheetName = deleted3DPxg.sheetName;
    }

    public Deleted3DPxg(String str) {
        this(-1, str);
    }

    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public void setSheetName(String str) {
        this.sheetName = str;
    }

    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        if (this.externalWorkbookNumber >= 0) {
            sb.append('[');
            sb.append(this.externalWorkbookNumber);
            sb.append(']');
        }
        String str = this.sheetName;
        if (str != null) {
            SheetNameFormatter.appendFormat(sb, str);
        }
        sb.append('!');
        sb.append(FormulaError.REF.getString());
        return sb.toString();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    public Deleted3DPxg copy() {
        return new Deleted3DPxg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalWorkbookNumber", new Deleted3DPxg$$ExternalSyntheticLambda0(this), "sheetName", new Deleted3DPxg$$ExternalSyntheticLambda1(this), "formulaError", new Deleted3DPxg$$ExternalSyntheticLambda2());
    }
}
