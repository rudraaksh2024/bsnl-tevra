package org.apache.poi.ss.formula.ptg;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.ss.formula.SheetNameFormatter;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class NameXPxg extends OperandPtg implements Pxg {
    private int externalWorkbookNumber;
    private String nameName;
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

    public NameXPxg(int i, String str, String str2) {
        this.externalWorkbookNumber = i;
        this.sheetName = str;
        this.nameName = str2;
    }

    public NameXPxg(NameXPxg nameXPxg) {
        super(nameXPxg);
        this.externalWorkbookNumber = -1;
        this.externalWorkbookNumber = nameXPxg.externalWorkbookNumber;
        this.sheetName = nameXPxg.sheetName;
        this.nameName = nameXPxg.nameName;
    }

    public NameXPxg(String str, String str2) {
        this(-1, str, str2);
    }

    public NameXPxg(String str) {
        this(-1, (String) null, str);
    }

    public int getExternalWorkbookNumber() {
        return this.externalWorkbookNumber;
    }

    public String getSheetName() {
        return this.sheetName;
    }

    public String getNameName() {
        return this.nameName;
    }

    public void setSheetName(String str) {
        this.sheetName = str;
    }

    public String toFormulaString() {
        boolean z;
        StringBuilder sb = new StringBuilder(64);
        boolean z2 = true;
        if (this.externalWorkbookNumber >= 0) {
            sb.append('[');
            sb.append(this.externalWorkbookNumber);
            sb.append(']');
            z = true;
        } else {
            z = false;
        }
        String str = this.sheetName;
        if (str != null) {
            SheetNameFormatter.appendFormat(sb, str);
        } else {
            z2 = z;
        }
        if (z2) {
            sb.append('!');
        }
        sb.append(this.nameName);
        return sb.toString();
    }

    public void write(LittleEndianOutput littleEndianOutput) {
        throw new IllegalStateException("XSSF-only Ptg, should not be serialised");
    }

    public NameXPxg copy() {
        return new NameXPxg(this);
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalWorkbookNumber", new NameXPxg$$ExternalSyntheticLambda0(this), "sheetName", new NameXPxg$$ExternalSyntheticLambda1(this), "nameName", new NameXPxg$$ExternalSyntheticLambda2(this));
    }
}
