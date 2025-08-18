package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeAndWorkbookIndexFormatter;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class Ref3DPxg extends RefPtgBase implements Pxg3D {
    private int externalWorkbookNumber;
    private String firstSheetName;
    private String lastSheetName;

    public byte getSid() {
        return -1;
    }

    public int getSize() {
        return 1;
    }

    public Ref3DPxg(Ref3DPxg ref3DPxg) {
        super((RefPtgBase) ref3DPxg);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = ref3DPxg.externalWorkbookNumber;
        this.firstSheetName = ref3DPxg.firstSheetName;
        this.lastSheetName = ref3DPxg.lastSheetName;
    }

    public Ref3DPxg(int i, SheetIdentifier sheetIdentifier, String str) {
        this(i, sheetIdentifier, new CellReference(str));
    }

    public Ref3DPxg(int i, SheetIdentifier sheetIdentifier, CellReference cellReference) {
        super(cellReference);
        this.externalWorkbookNumber = i;
        this.firstSheetName = sheetIdentifier.getSheetIdentifier().getName();
        if (sheetIdentifier instanceof SheetRangeIdentifier) {
            this.lastSheetName = ((SheetRangeIdentifier) sheetIdentifier).getLastSheetIdentifier().getName();
        } else {
            this.lastSheetName = null;
        }
    }

    public Ref3DPxg(SheetIdentifier sheetIdentifier, String str) {
        this(sheetIdentifier, new CellReference(str));
    }

    public Ref3DPxg(SheetIdentifier sheetIdentifier, CellReference cellReference) {
        this(-1, sheetIdentifier, cellReference);
    }

    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    public String getSheetName() {
        return this.firstSheetName;
    }

    public String getLastSheetName() {
        return this.lastSheetName;
    }

    public void setSheetName(String str) {
        this.firstSheetName = str;
    }

    public void setLastSheetName(String str) {
        this.lastSheetName = str;
    }

    public String format2DRefAsString() {
        return formatReferenceAsString();
    }

    public String toFormulaString() {
        StringBuilder sb = new StringBuilder(64);
        SheetRangeAndWorkbookIndexFormatter.format(sb, this.externalWorkbookNumber, this.firstSheetName, this.lastSheetName);
        sb.append('!');
        sb.append(formatReferenceAsString());
        return sb.toString();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    public Ref3DPxg copy() {
        return new Ref3DPxg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Ref3DPxg$$ExternalSyntheticLambda0(this), "externalWorkbookNumber", new Ref3DPxg$$ExternalSyntheticLambda1(this), "firstSheetName", new Ref3DPxg$$ExternalSyntheticLambda2(this), "lastSheetName", new Ref3DPxg$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Ref3DPxg  reason: not valid java name */
    public /* synthetic */ Object m2284lambda$getGenericProperties$0$orgapachepoissformulaptgRef3DPxg() {
        return super.getGenericProperties();
    }
}
