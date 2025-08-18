package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.ExternSheetReferenceToken;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class Ref3DPtg extends RefPtgBase implements WorkbookDependentFormula, ExternSheetReferenceToken {
    private static final int SIZE = 7;
    public static final byte sid = 58;
    private int field_1_index_extern_sheet;

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 7;
    }

    public Ref3DPtg(Ref3DPtg ref3DPtg) {
        super((RefPtgBase) ref3DPtg);
        this.field_1_index_extern_sheet = ref3DPtg.field_1_index_extern_sheet;
    }

    public Ref3DPtg(LittleEndianInput littleEndianInput) {
        this.field_1_index_extern_sheet = littleEndianInput.readShort();
        readCoordinates(littleEndianInput);
    }

    public Ref3DPtg(String str, int i) {
        this(new CellReference(str), i);
    }

    public Ref3DPtg(CellReference cellReference, int i) {
        super(cellReference);
        setExternSheetIndex(i);
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeShort(getExternSheetIndex());
        writeCoordinates(littleEndianOutput);
    }

    public int getExternSheetIndex() {
        return this.field_1_index_extern_sheet;
    }

    public void setExternSheetIndex(int i) {
        this.field_1_index_extern_sheet = i;
    }

    public String format2DRefAsString() {
        return formatReferenceAsString();
    }

    public String toFormulaString(FormulaRenderingWorkbook formulaRenderingWorkbook) {
        return ExternSheetNameResolver.prependSheetName(formulaRenderingWorkbook, this.field_1_index_extern_sheet, formatReferenceAsString());
    }

    public String toFormulaString() {
        throw new RuntimeException("3D references need a workbook to determine formula text");
    }

    public Ref3DPtg copy() {
        return new Ref3DPtg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Ref3DPtg$$ExternalSyntheticLambda0(this), "externSheetIndex", new Ref3DPtg$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Ref3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2283lambda$getGenericProperties$0$orgapachepoissformulaptgRef3DPtg() {
        return super.getGenericProperties();
    }
}
