package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class DeletedRef3DPtg extends OperandPtg implements WorkbookDependentFormula {
    public static final byte sid = 60;
    private final int field_1_index_extern_sheet;
    private final int unused1;

    public DeletedRef3DPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 0;
    }

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 7;
    }

    public DeletedRef3DPtg(LittleEndianInput littleEndianInput) {
        this.field_1_index_extern_sheet = littleEndianInput.readUShort();
        this.unused1 = littleEndianInput.readInt();
    }

    public DeletedRef3DPtg(int i) {
        this.field_1_index_extern_sheet = i;
        this.unused1 = 0;
    }

    public String toFormulaString(FormulaRenderingWorkbook formulaRenderingWorkbook) {
        return ExternSheetNameResolver.prependSheetName(formulaRenderingWorkbook, this.field_1_index_extern_sheet, FormulaError.REF.getString());
    }

    public String toFormulaString() {
        throw new RuntimeException("3D references need a workbook to determine formula text");
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeShort(this.field_1_index_extern_sheet);
        littleEndianOutput.writeInt(this.unused1);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externSheetIndex", new DeletedRef3DPtg$$ExternalSyntheticLambda0(this), "unused1", new DeletedRef3DPtg$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-DeletedRef3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2279lambda$getGenericProperties$0$orgapachepoissformulaptgDeletedRef3DPtg() {
        return Integer.valueOf(this.field_1_index_extern_sheet);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-DeletedRef3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2280lambda$getGenericProperties$1$orgapachepoissformulaptgDeletedRef3DPtg() {
        return Integer.valueOf(this.unused1);
    }
}
