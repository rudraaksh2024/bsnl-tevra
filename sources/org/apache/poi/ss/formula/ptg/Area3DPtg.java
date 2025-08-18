package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.ExternSheetReferenceToken;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.WorkbookDependentFormula;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

public final class Area3DPtg extends AreaPtgBase implements WorkbookDependentFormula, ExternSheetReferenceToken {
    private static final int SIZE = 11;
    public static final byte sid = 59;
    private int field_1_index_extern_sheet;

    public byte getSid() {
        return sid;
    }

    public int getSize() {
        return 11;
    }

    public Area3DPtg(String str, int i) {
        super(new AreaReference(str, SpreadsheetVersion.EXCEL97));
        setExternSheetIndex(i);
    }

    public Area3DPtg(Area3DPtg area3DPtg) {
        super((AreaPtgBase) area3DPtg);
        this.field_1_index_extern_sheet = area3DPtg.field_1_index_extern_sheet;
    }

    public Area3DPtg(LittleEndianInput littleEndianInput) {
        this.field_1_index_extern_sheet = littleEndianInput.readShort();
        readCoordinates(littleEndianInput);
    }

    public Area3DPtg(int i, int i2, int i3, int i4, boolean z, boolean z2, boolean z3, boolean z4, int i5) {
        super(i, i2, i3, i4, z, z2, z3, z4);
        setExternSheetIndex(i5);
    }

    public Area3DPtg(AreaReference areaReference, int i) {
        super(areaReference);
        setExternSheetIndex(i);
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeByte(getPtgClass() + sid);
        littleEndianOutput.writeShort(this.field_1_index_extern_sheet);
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

    public Area3DPtg copy() {
        return new Area3DPtg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Area3DPtg$$ExternalSyntheticLambda0(this), "externSheetIndex", new Area3DPtg$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Area3DPtg  reason: not valid java name */
    public /* synthetic */ Object m2263lambda$getGenericProperties$0$orgapachepoissformulaptgArea3DPtg() {
        return super.getGenericProperties();
    }
}
