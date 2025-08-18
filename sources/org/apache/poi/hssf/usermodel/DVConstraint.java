package org.apache.poi.hssf.usermodel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.DVRecord;
import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.LocaleUtil;

public class DVConstraint implements DataValidationConstraint {
    private String[] _explicitListValues;
    private String _formula1;
    private String _formula2;
    private int _operator;
    private final int _validationType;
    private Double _value1;
    private Double _value2;

    static final class FormulaPair {
        private final Ptg[] _formula1;
        private final Ptg[] _formula2;

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: org.apache.poi.ss.formula.ptg.Ptg[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        FormulaPair(org.apache.poi.ss.formula.ptg.Ptg[] r2, org.apache.poi.ss.formula.ptg.Ptg[] r3) {
            /*
                r1 = this;
                r1.<init>()
                r0 = 0
                if (r2 != 0) goto L_0x0008
                r2 = r0
                goto L_0x000e
            L_0x0008:
                java.lang.Object r2 = r2.clone()
                org.apache.poi.ss.formula.ptg.Ptg[] r2 = (org.apache.poi.ss.formula.ptg.Ptg[]) r2
            L_0x000e:
                r1._formula1 = r2
                if (r3 != 0) goto L_0x0013
                goto L_0x001a
            L_0x0013:
                java.lang.Object r2 = r3.clone()
                r0 = r2
                org.apache.poi.ss.formula.ptg.Ptg[] r0 = (org.apache.poi.ss.formula.ptg.Ptg[]) r0
            L_0x001a:
                r1._formula2 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.DVConstraint.FormulaPair.<init>(org.apache.poi.ss.formula.ptg.Ptg[], org.apache.poi.ss.formula.ptg.Ptg[]):void");
        }

        public Ptg[] getFormula1() {
            return this._formula1;
        }

        public Ptg[] getFormula2() {
            return this._formula2;
        }
    }

    private DVConstraint(int i, int i2, String str, String str2, Double d, Double d2, String[] strArr) {
        String[] strArr2;
        this._validationType = i;
        this._operator = i2;
        this._formula1 = str;
        this._formula2 = str2;
        this._value1 = d;
        this._value2 = d2;
        if (strArr == null) {
            strArr2 = null;
        } else {
            strArr2 = (String[]) strArr.clone();
        }
        this._explicitListValues = strArr2;
    }

    private DVConstraint(String str, String[] strArr) {
        this(3, 0, str, (String) null, (Double) null, (Double) null, strArr);
    }

    public static DVConstraint createNumericConstraint(int i, int i2, String str, String str2) {
        if (i != 0) {
            if (i != 1 && i != 2 && i != 6) {
                throw new IllegalArgumentException("Validation Type (" + i + ") not supported with this method");
            } else if (str != null) {
                DataValidationConstraint.OperatorType.validateSecondArg(i2, str2);
            } else {
                throw new IllegalArgumentException("expr1 must be supplied");
            }
        } else if (!(str == null && str2 == null)) {
            throw new IllegalArgumentException("expr1 and expr2 must be null for validation type 'any'");
        }
        String formulaFromTextExpression = getFormulaFromTextExpression(str);
        Double convertNumber = formulaFromTextExpression == null ? convertNumber(str) : null;
        String formulaFromTextExpression2 = getFormulaFromTextExpression(str2);
        return new DVConstraint(i, i2, formulaFromTextExpression, formulaFromTextExpression2, convertNumber, formulaFromTextExpression2 == null ? convertNumber(str2) : null, (String[]) null);
    }

    public static DVConstraint createFormulaListConstraint(String str) {
        return new DVConstraint(str, (String[]) null);
    }

    public static DVConstraint createExplicitListConstraint(String[] strArr) {
        return new DVConstraint((String) null, strArr);
    }

    public static DVConstraint createTimeConstraint(int i, String str, String str2) {
        if (str != null) {
            DataValidationConstraint.OperatorType.validateSecondArg(i, str);
            String formulaFromTextExpression = getFormulaFromTextExpression(str);
            Double convertTime = formulaFromTextExpression == null ? convertTime(str) : null;
            String formulaFromTextExpression2 = getFormulaFromTextExpression(str2);
            return new DVConstraint(5, i, formulaFromTextExpression, formulaFromTextExpression2, convertTime, formulaFromTextExpression2 == null ? convertTime(str2) : null, (String[]) null);
        }
        throw new IllegalArgumentException("expr1 must be supplied");
    }

    public static DVConstraint createDateConstraint(int i, String str, String str2, String str3) {
        SimpleDateFormat simpleDateFormat;
        if (str != null) {
            DataValidationConstraint.OperatorType.validateSecondArg(i, str2);
            Double d = null;
            if (str3 != null) {
                simpleDateFormat = new SimpleDateFormat(str3, LocaleUtil.getUserLocale());
                simpleDateFormat.setTimeZone(LocaleUtil.getUserTimeZone());
            } else {
                simpleDateFormat = null;
            }
            String formulaFromTextExpression = getFormulaFromTextExpression(str);
            Double convertDate = formulaFromTextExpression == null ? convertDate(str, simpleDateFormat) : null;
            String formulaFromTextExpression2 = getFormulaFromTextExpression(str2);
            if (formulaFromTextExpression2 == null) {
                d = convertDate(str2, simpleDateFormat);
            }
            return new DVConstraint(4, i, formulaFromTextExpression, formulaFromTextExpression2, convertDate, d, (String[]) null);
        }
        throw new IllegalArgumentException("expr1 must be supplied");
    }

    private static String getFormulaFromTextExpression(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() < 1) {
            throw new IllegalArgumentException("Empty string is not a valid formula/value expression");
        } else if (str.charAt(0) == '=') {
            return str.substring(1);
        } else {
            return null;
        }
    }

    private static Double convertNumber(String str) {
        if (str == null) {
            return null;
        }
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException unused) {
            throw new RuntimeException("The supplied text '" + str + "' could not be parsed as a number");
        }
    }

    private static Double convertTime(String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(DateUtil.convertTime(str));
    }

    private static Double convertDate(String str, SimpleDateFormat simpleDateFormat) {
        Date date;
        if (str == null) {
            return null;
        }
        if (simpleDateFormat == null) {
            date = DateUtil.parseYYYYMMDDDate(str);
        } else {
            try {
                date = simpleDateFormat.parse(str);
            } catch (ParseException e) {
                throw new RuntimeException("Failed to parse date '" + str + "' using specified format '" + simpleDateFormat + "'", e);
            }
        }
        return Double.valueOf(DateUtil.getExcelDate(date));
    }

    public static DVConstraint createCustomFormulaConstraint(String str) {
        if (str != null) {
            return new DVConstraint(7, 0, str, (String) null, (Double) null, (Double) null, (String[]) null);
        }
        throw new IllegalArgumentException("formula must be supplied");
    }

    public int getValidationType() {
        return this._validationType;
    }

    public boolean isListValidationType() {
        return this._validationType == 3;
    }

    public boolean isExplicitList() {
        return this._validationType == 3 && this._explicitListValues != null;
    }

    public int getOperator() {
        return this._operator;
    }

    public void setOperator(int i) {
        this._operator = i;
    }

    public String[] getExplicitListValues() {
        return this._explicitListValues;
    }

    public void setExplicitListValues(String[] strArr) {
        if (this._validationType == 3) {
            this._formula1 = null;
            this._explicitListValues = strArr;
            return;
        }
        throw new RuntimeException("Cannot setExplicitListValues on non-list constraint");
    }

    public String getFormula1() {
        return this._formula1;
    }

    public void setFormula1(String str) {
        this._value1 = null;
        this._explicitListValues = null;
        this._formula1 = str;
    }

    public String getFormula2() {
        return this._formula2;
    }

    public void setFormula2(String str) {
        this._value2 = null;
        this._formula2 = str;
    }

    public Double getValue1() {
        return this._value1;
    }

    public void setValue1(double d) {
        this._formula1 = null;
        this._value1 = Double.valueOf(d);
    }

    public Double getValue2() {
        return this._value2;
    }

    public void setValue2(double d) {
        this._formula2 = null;
        this._value2 = Double.valueOf(d);
    }

    /* access modifiers changed from: package-private */
    public FormulaPair createFormulas(HSSFSheet hSSFSheet) {
        Ptg[] ptgArr;
        Ptg[] ptgArr2;
        if (isListValidationType()) {
            ptgArr2 = createListFormula(hSSFSheet);
            ptgArr = Ptg.EMPTY_PTG_ARRAY;
        } else {
            Ptg[] convertDoubleFormula = convertDoubleFormula(this._formula1, this._value1, hSSFSheet);
            ptgArr = convertDoubleFormula(this._formula2, this._value2, hSSFSheet);
            ptgArr2 = convertDoubleFormula;
        }
        return new FormulaPair(ptgArr2, ptgArr);
    }

    private Ptg[] createListFormula(HSSFSheet hSSFSheet) {
        if (this._explicitListValues == null) {
            HSSFWorkbook workbook = hSSFSheet.getWorkbook();
            return HSSFFormulaParser.parse(this._formula1, workbook, FormulaType.DATAVALIDATION_LIST, workbook.getSheetIndex((Sheet) hSSFSheet));
        }
        StringBuilder sb = new StringBuilder(this._explicitListValues.length * 16);
        for (int i = 0; i < this._explicitListValues.length; i++) {
            if (i > 0) {
                sb.append(0);
            }
            sb.append(this._explicitListValues[i]);
        }
        return new Ptg[]{new StringPtg(sb.toString())};
    }

    private static Ptg[] convertDoubleFormula(String str, Double d, HSSFSheet hSSFSheet) {
        if (str == null) {
            if (d == null) {
                return Ptg.EMPTY_PTG_ARRAY;
            }
            return new Ptg[]{new NumberPtg(d.doubleValue())};
        } else if (d == null) {
            HSSFWorkbook workbook = hSSFSheet.getWorkbook();
            return HSSFFormulaParser.parse(str, workbook, FormulaType.CELL, workbook.getSheetIndex((Sheet) hSSFSheet));
        } else {
            throw new IllegalStateException("Both formula and value cannot be present");
        }
    }

    static DVConstraint createDVConstraint(DVRecord dVRecord, FormulaRenderingWorkbook formulaRenderingWorkbook) {
        FormulaRenderingWorkbook formulaRenderingWorkbook2 = formulaRenderingWorkbook;
        switch (dVRecord.getDataType()) {
            case 0:
                return new DVConstraint(0, dVRecord.getConditionOperator(), (String) null, (String) null, (Double) null, (Double) null, (String[]) null);
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
                FormulaValuePair formulaString = toFormulaString(dVRecord.getFormula1(), formulaRenderingWorkbook2);
                FormulaValuePair formulaString2 = toFormulaString(dVRecord.getFormula2(), formulaRenderingWorkbook2);
                return new DVConstraint(dVRecord.getDataType(), dVRecord.getConditionOperator(), formulaString.formula(), formulaString2.formula(), formulaString.value(), formulaString2.value(), (String[]) null);
            case 3:
                if (!dVRecord.getListExplicitFormula()) {
                    return createFormulaListConstraint(toFormulaString(dVRecord.getFormula1(), formulaRenderingWorkbook2).string());
                }
                String string = toFormulaString(dVRecord.getFormula1(), formulaRenderingWorkbook2).string();
                if (string.startsWith("\"")) {
                    string = string.substring(1);
                }
                if (string.endsWith("\"")) {
                    string = string.substring(0, string.length() - 1);
                }
                return createExplicitListConstraint(string.split(Pattern.quote("\u0000")));
            case 7:
                return createCustomFormulaConstraint(toFormulaString(dVRecord.getFormula1(), formulaRenderingWorkbook2).string());
            default:
                throw new UnsupportedOperationException("validationType=" + dVRecord.getDataType());
        }
    }

    private static class FormulaValuePair {
        /* access modifiers changed from: private */
        public String _formula;
        /* access modifiers changed from: private */
        public String _value;

        private FormulaValuePair() {
        }

        public String formula() {
            return this._formula;
        }

        public Double value() {
            String str = this._value;
            if (str == null) {
                return null;
            }
            return Double.valueOf(str);
        }

        public String string() {
            String str = this._formula;
            if (str != null) {
                return str;
            }
            String str2 = this._value;
            if (str2 != null) {
                return str2;
            }
            return null;
        }
    }

    private static FormulaValuePair toFormulaString(Ptg[] ptgArr, FormulaRenderingWorkbook formulaRenderingWorkbook) {
        FormulaValuePair formulaValuePair = new FormulaValuePair();
        if (ptgArr != null && ptgArr.length > 0) {
            String formulaString = FormulaRenderer.toFormulaString(formulaRenderingWorkbook, ptgArr);
            if (ptgArr.length == 1 && ptgArr[0].getClass() == NumberPtg.class) {
                String unused = formulaValuePair._value = formulaString;
            } else {
                String unused2 = formulaValuePair._formula = formulaString;
            }
        }
        return formulaValuePair;
    }
}
