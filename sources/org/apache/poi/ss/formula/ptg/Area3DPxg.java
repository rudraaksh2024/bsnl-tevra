package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.SheetIdentifier;
import org.apache.poi.ss.formula.SheetRangeAndWorkbookIndexFormatter;
import org.apache.poi.ss.formula.SheetRangeIdentifier;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class Area3DPxg extends AreaPtgBase implements Pxg3D {
    private int externalWorkbookNumber;
    private String firstSheetName;
    private String lastSheetName;

    public byte getSid() {
        return -1;
    }

    public int getSize() {
        return 1;
    }

    public Area3DPxg(Area3DPxg area3DPxg) {
        super((AreaPtgBase) area3DPxg);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = area3DPxg.externalWorkbookNumber;
        this.firstSheetName = area3DPxg.firstSheetName;
        this.lastSheetName = area3DPxg.lastSheetName;
    }

    public Area3DPxg(int i, SheetIdentifier sheetIdentifier, String str) {
        this(i, sheetIdentifier, new AreaReference(str, SpreadsheetVersion.EXCEL2007));
    }

    public Area3DPxg(int i, SheetIdentifier sheetIdentifier, AreaReference areaReference) {
        super(areaReference);
        this.externalWorkbookNumber = i;
        this.firstSheetName = sheetIdentifier.getSheetIdentifier().getName();
        if (sheetIdentifier instanceof SheetRangeIdentifier) {
            this.lastSheetName = ((SheetRangeIdentifier) sheetIdentifier).getLastSheetIdentifier().getName();
        } else {
            this.lastSheetName = null;
        }
    }

    public Area3DPxg(SheetIdentifier sheetIdentifier, String str) {
        this(sheetIdentifier, new AreaReference(str, SpreadsheetVersion.EXCEL2007));
    }

    public Area3DPxg(SheetIdentifier sheetIdentifier, AreaReference areaReference) {
        this(-1, sheetIdentifier, areaReference);
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

    public Area3DPxg copy() {
        return new Area3DPxg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("base", new Area3DPxg$$ExternalSyntheticLambda0(this), "externalWorkbookNumber", new Area3DPxg$$ExternalSyntheticLambda1(this), "sheetName", new Area3DPxg$$ExternalSyntheticLambda2(this), "lastSheetName", new Area3DPxg$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-ss-formula-ptg-Area3DPxg  reason: not valid java name */
    public /* synthetic */ Object m2264lambda$getGenericProperties$0$orgapachepoissformulaptgArea3DPxg() {
        return super.getGenericProperties();
    }
}
