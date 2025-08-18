package org.apache.poi.ss.usermodel;

import org.apache.poi.ss.formula.eval.ErrorEval;

public final class CellValue {
    public static final CellValue FALSE = new CellValue(CellType.BOOLEAN, 0.0d, false, (String) null, 0);
    public static final CellValue TRUE = new CellValue(CellType.BOOLEAN, 0.0d, true, (String) null, 0);
    private final boolean _booleanValue;
    private final CellType _cellType;
    private final int _errorCode;
    private final double _numberValue;
    private final String _textValue;

    private CellValue(CellType cellType, double d, boolean z, String str, int i) {
        this._cellType = cellType;
        this._numberValue = d;
        this._booleanValue = z;
        this._textValue = str;
        this._errorCode = i;
    }

    public CellValue(double d) {
        this(CellType.NUMERIC, d, false, (String) null, 0);
    }

    public static CellValue valueOf(boolean z) {
        return z ? TRUE : FALSE;
    }

    public CellValue(String str) {
        this(CellType.STRING, 0.0d, false, str, 0);
    }

    public static CellValue getError(int i) {
        return new CellValue(CellType.ERROR, 0.0d, false, (String) null, i);
    }

    public boolean getBooleanValue() {
        return this._booleanValue;
    }

    public double getNumberValue() {
        return this._numberValue;
    }

    public String getStringValue() {
        return this._textValue;
    }

    public CellType getCellType() {
        return this._cellType;
    }

    public byte getErrorValue() {
        return (byte) this._errorCode;
    }

    public String toString() {
        return getClass().getName() + " [" + formatAsString() + "]";
    }

    /* renamed from: org.apache.poi.ss.usermodel.CellValue$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.poi.ss.usermodel.CellType r1 = org.apache.poi.ss.usermodel.CellType.ERROR     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.usermodel.CellValue.AnonymousClass1.<clinit>():void");
        }
    }

    public String formatAsString() {
        int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[this._cellType.ordinal()];
        if (i == 1) {
            return String.valueOf(this._numberValue);
        }
        if (i == 2) {
            return "\"" + this._textValue + '\"';
        }
        if (i == 3) {
            return this._booleanValue ? "TRUE" : "FALSE";
        }
        if (i != 4) {
            return "<error unexpected cell type " + this._cellType + ">";
        }
        return ErrorEval.getText(this._errorCode);
    }
}
