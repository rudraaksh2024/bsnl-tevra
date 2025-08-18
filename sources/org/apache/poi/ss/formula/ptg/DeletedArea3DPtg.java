package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class DeletedArea3DPtg extends OperandPtg implements WorkbookDependentFormula {
    public static final byte sid = 61;
    private final int field_1_index_extern_sheet;
    private final int unused1;
    private final int unused2;

    public DeletedArea3DPtg copy() {
        return this;
    }

    public byte getDefaultOperandClass() {
        return 0;
    }

    public byte getSid() {
        return 61;
    }

    public int getSize() {
        return 11;
    }

    public DeletedArea3DPtg(int i) {
        this.field_1_index_extern_sheet = i;
        this.unused1 = 0;
        this.unused2 = 0;
    }

    public DeletedArea3DPtg(LittleEndianInput littleEndianInput) {
        this.field_1_index_extern_sheet = littleEndianInput.readUShort();
        this.unused1 = littleEndianInput.readInt();
        this.unused2 = littleEndianInput.readInt();
    }

    public String toFormulaString(FormulaRenderingWorkbook formulaRenderingWorkbook) {
        return ExternSheetNameResolver.prependSheetName(formulaRenderingWorkbook, this.field_1_index_extern_sheet, FormulaError.REF.getString());
    }

    public String toFormulaString() {
        throw new RuntimeException("3D references need a workbook to determine formula text");
    }

    public int getExternSheetIndex() {
        return this.field_1_index_extern_sheet;
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + 61);
        littleEndianOutput.writeShort(this.field_1_index_extern_sheet);
        littleEndianOutput.writeInt(this.unused1);
        littleEndianOutput.writeInt(this.unused2);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externSheetIndex", new DeletedArea3DPtg$$ExternalSyntheticLambda0(this), "unused1", new DeletedArea3DPtg$$ExternalSyntheticLambda1(this), "unused2", new DeletedArea3DPtg$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-DeletedArea3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2277lambda$getGenericProperties$0$orgapachepoissformulaptgDeletedArea3DPtg() {
        return Integer.valueOf(this.unused1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-ss-formula-ptg-DeletedArea3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2278lambda$getGenericProperties$1$orgapachepoissformulaptgDeletedArea3DPtg() {
        return Integer.valueOf(this.unused2);
    }
}
