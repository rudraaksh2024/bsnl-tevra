package org.apache.poi.ss.usermodel;

public class ExcelNumberFormat {
    private final String format;
    private final int idx;

    public static ExcelNumberFormat from(CellStyle cellStyle) {
        if (cellStyle == null) {
            return null;
        }
        return new ExcelNumberFormat(cellStyle.getDataFormat(), cellStyle.getDataFormatString());
    }

    /* JADX WARNING: Removed duplicated region for block: B:5:0x000e A[LOOP:0: B:5:0x000e->B:8:0x001e, LOOP_START, PHI: r0 
      PHI: (r0v3 org.apache.poi.ss.usermodel.ExcelNumberFormat) = (r0v0 org.apache.poi.ss.usermodel.ExcelNumberFormat), (r0v6 org.apache.poi.ss.usermodel.ExcelNumberFormat) binds: [B:4:0x0006, B:8:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.poi.ss.usermodel.ExcelNumberFormat from(org.apache.poi.ss.usermodel.Cell r2, org.apache.poi.ss.formula.ConditionalFormattingEvaluator r3) {
        /*
            r0 = 0
            if (r2 != 0) goto L_0x0004
            return r0
        L_0x0004:
            if (r3 == 0) goto L_0x0020
            java.util.List r3 = r3.getConditionalFormattingForCell((org.apache.poi.ss.usermodel.Cell) r2)
            java.util.Iterator r3 = r3.iterator()
        L_0x000e:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L_0x0020
            java.lang.Object r0 = r3.next()
            org.apache.poi.ss.formula.EvaluationConditionalFormatRule r0 = (org.apache.poi.ss.formula.EvaluationConditionalFormatRule) r0
            org.apache.poi.ss.usermodel.ExcelNumberFormat r0 = r0.getNumberFormat()
            if (r0 == 0) goto L_0x000e
        L_0x0020:
            if (r0 != 0) goto L_0x002a
            org.apache.poi.ss.usermodel.CellStyle r2 = r2.getCellStyle()
            org.apache.poi.ss.usermodel.ExcelNumberFormat r0 = from(r2)
        L_0x002a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.usermodel.ExcelNumberFormat.from(org.apache.poi.ss.usermodel.Cell, org.apache.poi.ss.formula.ConditionalFormattingEvaluator):org.apache.poi.ss.usermodel.ExcelNumberFormat");
    }

    public ExcelNumberFormat(int i, String str) {
        this.idx = i;
        this.format = str;
    }

    public int getIdx() {
        return this.idx;
    }

    public String getFormat() {
        return this.format;
    }
}
