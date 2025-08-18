package org.apache.poi.ss.formula;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.AggregateFunction;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.LocaleUtil;

public class EvaluationConditionalFormatRule implements Comparable<EvaluationConditionalFormatRule> {
    private final DecimalFormat decimalTextFormat;
    private final ConditionalFormatting formatting;
    private final int formattingIndex;
    private final String formula1;
    private final String formula2;
    private final String lowerText;
    private final Map<CellRangeAddress, Set<ValueAndFormat>> meaningfulRegionValues = new HashMap();
    private final ExcelNumberFormat numberFormat;
    private final OperatorEnum operator;
    private final int priority;
    private final CellRangeAddress[] regions;
    private final ConditionalFormattingRule rule;
    private final int ruleIndex;
    private final Sheet sheet;
    private final String text;
    private CellRangeAddress topLeftRegion;
    private final ConditionType type;
    private final WorkbookEvaluator workbookEvaluator;

    public EvaluationConditionalFormatRule(WorkbookEvaluator workbookEvaluator2, Sheet sheet2, ConditionalFormatting conditionalFormatting, int i, ConditionalFormattingRule conditionalFormattingRule, int i2, CellRangeAddress[] cellRangeAddressArr) {
        String str;
        this.workbookEvaluator = workbookEvaluator2;
        this.sheet = sheet2;
        this.formatting = conditionalFormatting;
        this.rule = conditionalFormattingRule;
        this.formattingIndex = i;
        this.ruleIndex = i2;
        this.priority = conditionalFormattingRule.getPriority();
        this.regions = cellRangeAddressArr;
        for (CellRangeAddress cellRangeAddress : cellRangeAddressArr) {
            if (this.topLeftRegion == null) {
                this.topLeftRegion = cellRangeAddress;
            } else if (cellRangeAddress.getFirstColumn() < this.topLeftRegion.getFirstColumn() || cellRangeAddress.getFirstRow() < this.topLeftRegion.getFirstRow()) {
                this.topLeftRegion = cellRangeAddress;
            }
        }
        this.formula1 = conditionalFormattingRule.getFormula1();
        this.formula2 = conditionalFormattingRule.getFormula2();
        String text2 = conditionalFormattingRule.getText();
        this.text = text2;
        if (text2 == null) {
            str = null;
        } else {
            str = text2.toLowerCase(LocaleUtil.getUserLocale());
        }
        this.lowerText = str;
        this.numberFormat = conditionalFormattingRule.getNumberFormat();
        this.operator = OperatorEnum.values()[conditionalFormattingRule.getComparisonOperation()];
        this.type = conditionalFormattingRule.getConditionType();
        DecimalFormat decimalFormat = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        this.decimalTextFormat = decimalFormat;
        decimalFormat.setMaximumFractionDigits(340);
    }

    public Sheet getSheet() {
        return this.sheet;
    }

    public ConditionalFormatting getFormatting() {
        return this.formatting;
    }

    public int getFormattingIndex() {
        return this.formattingIndex;
    }

    public ExcelNumberFormat getNumberFormat() {
        return this.numberFormat;
    }

    public ConditionalFormattingRule getRule() {
        return this.rule;
    }

    public int getRuleIndex() {
        return this.ruleIndex;
    }

    public CellRangeAddress[] getRegions() {
        return this.regions;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getFormula1() {
        return this.formula1;
    }

    public String getFormula2() {
        return this.formula2;
    }

    public String getText() {
        return this.text;
    }

    public OperatorEnum getOperator() {
        return this.operator;
    }

    public ConditionType getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        EvaluationConditionalFormatRule evaluationConditionalFormatRule = (EvaluationConditionalFormatRule) obj;
        if (getSheet().getSheetName().equalsIgnoreCase(evaluationConditionalFormatRule.getSheet().getSheetName()) && getFormattingIndex() == evaluationConditionalFormatRule.getFormattingIndex() && getRuleIndex() == evaluationConditionalFormatRule.getRuleIndex()) {
            return true;
        }
        return false;
    }

    public int compareTo(EvaluationConditionalFormatRule evaluationConditionalFormatRule) {
        int compareToIgnoreCase = getSheet().getSheetName().compareToIgnoreCase(evaluationConditionalFormatRule.getSheet().getSheetName());
        if (compareToIgnoreCase != 0) {
            return compareToIgnoreCase;
        }
        int compare = Integer.compare(getPriority(), evaluationConditionalFormatRule.getPriority());
        if (compare != 0) {
            return compare;
        }
        int compare2 = Integer.compare(getFormattingIndex(), evaluationConditionalFormatRule.getFormattingIndex());
        if (compare2 != 0) {
            return compare2;
        }
        return Integer.compare(getRuleIndex(), evaluationConditionalFormatRule.getRuleIndex());
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.sheet.getSheetName(), Integer.valueOf(this.formattingIndex), Integer.valueOf(this.ruleIndex)});
    }

    /* access modifiers changed from: package-private */
    public boolean matches(CellReference cellReference) {
        Cell cell;
        CellRangeAddress cellRangeAddress;
        CellRangeAddress[] cellRangeAddressArr = this.regions;
        int length = cellRangeAddressArr.length;
        int i = 0;
        while (true) {
            cell = null;
            if (i >= length) {
                cellRangeAddress = null;
                break;
            }
            cellRangeAddress = cellRangeAddressArr[i];
            if (cellRangeAddress.isInRange(cellReference)) {
                break;
            }
            i++;
        }
        if (cellRangeAddress == null) {
            return false;
        }
        ConditionType conditionType = getRule().getConditionType();
        if (conditionType.equals(ConditionType.COLOR_SCALE) || conditionType.equals(ConditionType.DATA_BAR) || conditionType.equals(ConditionType.ICON_SET)) {
            return true;
        }
        Row row = this.sheet.getRow(cellReference.getRow());
        if (row != null) {
            cell = row.getCell(cellReference.getCol());
        }
        if (conditionType.equals(ConditionType.CELL_VALUE_IS)) {
            if (cell == null) {
                return false;
            }
            return checkValue(cell, this.topLeftRegion);
        } else if (conditionType.equals(ConditionType.FORMULA)) {
            return checkFormula(cellReference, this.topLeftRegion);
        } else {
            if (conditionType.equals(ConditionType.FILTER)) {
                return checkFilter(cell, cellReference, this.topLeftRegion);
            }
            return false;
        }
    }

    private boolean checkValue(Cell cell, CellRangeAddress cellRangeAddress) {
        if (cell == null || DataValidationEvaluator.isType(cell, CellType.BLANK) || DataValidationEvaluator.isType(cell, CellType.ERROR)) {
            return false;
        }
        if (DataValidationEvaluator.isType(cell, CellType.STRING) && (cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty())) {
            return false;
        }
        ValueEval unwrapEval = unwrapEval(this.workbookEvaluator.evaluate(this.rule.getFormula1(), ConditionalFormattingEvaluator.getRef(cell), cellRangeAddress));
        String formula22 = this.rule.getFormula2();
        Object obj = BlankEval.instance;
        if (formula22 != null && formula22.length() > 0) {
            obj = unwrapEval(this.workbookEvaluator.evaluate(formula22, ConditionalFormattingEvaluator.getRef(cell), cellRangeAddress));
        }
        Comparable comparable = null;
        if (DataValidationEvaluator.isType(cell, CellType.BOOLEAN) && ((unwrapEval == BlankEval.instance || (unwrapEval instanceof BoolEval)) && (obj == BlankEval.instance || (obj instanceof BoolEval)))) {
            OperatorEnum operatorEnum = this.operator;
            Boolean valueOf = Boolean.valueOf(cell.getBooleanCellValue());
            Boolean valueOf2 = unwrapEval == BlankEval.instance ? null : Boolean.valueOf(((BoolEval) unwrapEval).getBooleanValue());
            if (obj != BlankEval.instance) {
                comparable = Boolean.valueOf(((BoolEval) obj).getBooleanValue());
            }
            return operatorEnum.isValid(valueOf, valueOf2, comparable);
        } else if (DataValidationEvaluator.isType(cell, CellType.NUMERIC) && ((unwrapEval == BlankEval.instance || (unwrapEval instanceof NumberEval)) && (obj == BlankEval.instance || (obj instanceof NumberEval)))) {
            OperatorEnum operatorEnum2 = this.operator;
            Double valueOf3 = Double.valueOf(cell.getNumericCellValue());
            Double valueOf4 = unwrapEval == BlankEval.instance ? null : Double.valueOf(((NumberEval) unwrapEval).getNumberValue());
            if (obj != BlankEval.instance) {
                comparable = Double.valueOf(((NumberEval) obj).getNumberValue());
            }
            return operatorEnum2.isValid(valueOf3, valueOf4, comparable);
        } else if (!DataValidationEvaluator.isType(cell, CellType.STRING) || ((unwrapEval != BlankEval.instance && !(unwrapEval instanceof StringEval)) || (obj != BlankEval.instance && !(obj instanceof StringEval)))) {
            return this.operator.isValidForIncompatibleTypes();
        } else {
            OperatorEnum operatorEnum3 = this.operator;
            String stringCellValue = cell.getStringCellValue();
            String stringValue = unwrapEval == BlankEval.instance ? null : ((StringEval) unwrapEval).getStringValue();
            if (obj != BlankEval.instance) {
                comparable = ((StringEval) obj).getStringValue();
            }
            return operatorEnum3.isValid(stringCellValue, stringValue, comparable);
        }
    }

    private ValueEval unwrapEval(ValueEval valueEval) {
        while (valueEval instanceof RefEval) {
            RefEval refEval = (RefEval) valueEval;
            valueEval = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
        }
        return valueEval;
    }

    private boolean checkFormula(CellReference cellReference, CellRangeAddress cellRangeAddress) {
        ValueEval unwrapEval = unwrapEval(this.workbookEvaluator.evaluate(this.rule.getFormula1(), cellReference, cellRangeAddress));
        if (unwrapEval instanceof BlankEval) {
            return true;
        }
        if (unwrapEval instanceof ErrorEval) {
            return false;
        }
        if (unwrapEval instanceof BoolEval) {
            return ((BoolEval) unwrapEval).getBooleanValue();
        }
        if (!(unwrapEval instanceof NumberEval)) {
            return false;
        }
        if (((NumberEval) unwrapEval).getNumberValue() != 0.0d) {
            return true;
        }
        return false;
    }

    private boolean checkFilter(Cell cell, CellReference cellReference, CellRangeAddress cellRangeAddress) {
        OperatorEnum operatorEnum;
        ConditionFilterType conditionFilterType = this.rule.getConditionFilterType();
        if (conditionFilterType == null) {
            return false;
        }
        ValueAndFormat cellValue = getCellValue(cell);
        int i = 1;
        switch (conditionFilterType) {
            case TOP_10:
                if (!cellValue.isNumber()) {
                    return false;
                }
                return getMeaningfulValues(cellRangeAddress, false, new EvaluationConditionalFormatRule$$ExternalSyntheticLambda0(this)).contains(cellValue);
            case UNIQUE_VALUES:
                return getMeaningfulValues(cellRangeAddress, true, new EvaluationConditionalFormatRule$$ExternalSyntheticLambda1(this)).contains(cellValue);
            case DUPLICATE_VALUES:
                return getMeaningfulValues(cellRangeAddress, true, new EvaluationConditionalFormatRule$$ExternalSyntheticLambda2(this)).contains(cellValue);
            case ABOVE_AVERAGE:
                ConditionFilterData filterConfiguration = this.rule.getFilterConfiguration();
                ArrayList arrayList = new ArrayList(getMeaningfulValues(cellRangeAddress, false, new EvaluationConditionalFormatRule$$ExternalSyntheticLambda3(this)));
                Double value = cellValue.isNumber() ? cellValue.getValue() : null;
                if (value == null) {
                    return false;
                }
                double doubleValue = ((ValueAndFormat) arrayList.get(0)).value.doubleValue();
                double doubleValue2 = ((ValueAndFormat) arrayList.get(1)).value.doubleValue();
                if (filterConfiguration.getStdDev() > 0) {
                    if (!filterConfiguration.getAboveAverage()) {
                        i = -1;
                    }
                    doubleValue += ((double) i) * doubleValue2 * ((double) filterConfiguration.getStdDev());
                }
                Double valueOf = Double.valueOf(doubleValue);
                if (filterConfiguration.getAboveAverage()) {
                    if (filterConfiguration.getEqualAverage()) {
                        operatorEnum = OperatorEnum.GREATER_OR_EQUAL;
                    } else {
                        operatorEnum = OperatorEnum.GREATER_THAN;
                    }
                } else if (filterConfiguration.getEqualAverage()) {
                    operatorEnum = OperatorEnum.LESS_OR_EQUAL;
                } else {
                    operatorEnum = OperatorEnum.LESS_THAN;
                }
                return operatorEnum.isValid(value, valueOf, null);
            case CONTAINS_TEXT:
                if (this.text == null || !cellValue.toString().toLowerCase(LocaleUtil.getUserLocale()).contains(this.lowerText)) {
                    return false;
                }
                return true;
            case NOT_CONTAINS_TEXT:
                if (this.text == null || !cellValue.toString().toLowerCase(LocaleUtil.getUserLocale()).contains(this.lowerText)) {
                    return true;
                }
                return false;
            case BEGINS_WITH:
                return cellValue.toString().toLowerCase(LocaleUtil.getUserLocale()).startsWith(this.lowerText);
            case ENDS_WITH:
                return cellValue.toString().toLowerCase(LocaleUtil.getUserLocale()).endsWith(this.lowerText);
            case CONTAINS_BLANKS:
                try {
                    String string = cellValue.getString();
                    if (string == null || string.trim().length() == 0) {
                        return true;
                    }
                    return false;
                } catch (Exception unused) {
                    return false;
                }
            case NOT_CONTAINS_BLANKS:
                try {
                    String string2 = cellValue.getString();
                    if (string2 == null || string2.trim().length() <= 0) {
                        return false;
                    }
                    return true;
                } catch (Exception unused2) {
                    return true;
                }
            case CONTAINS_ERRORS:
                if (cell == null || !DataValidationEvaluator.isType(cell, CellType.ERROR)) {
                    return false;
                }
                return true;
            case NOT_CONTAINS_ERRORS:
                if (cell == null || !DataValidationEvaluator.isType(cell, CellType.ERROR)) {
                    return true;
                }
                return false;
            case TIME_PERIOD:
                return checkFormula(cellReference, cellRangeAddress);
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateTop10(List<ValueAndFormat> list) {
        ConditionFilterData filterConfiguration = this.rule.getFilterConfiguration();
        if (!filterConfiguration.getBottom()) {
            list.sort(Collections.reverseOrder());
        } else {
            Collections.sort(list);
        }
        int intExact = Math.toIntExact(filterConfiguration.getRank());
        if (filterConfiguration.getPercent()) {
            intExact = (list.size() * intExact) / 100;
        }
        if (list.size() <= intExact) {
            return new HashSet(list);
        }
        return new HashSet(list.subList(0, intExact));
    }

    /* access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateUniqueValues(List<ValueAndFormat> list) {
        Collections.sort(list);
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < list.size()) {
            ValueAndFormat valueAndFormat = list.get(i);
            if ((i >= list.size() - 1 || !valueAndFormat.equals(list.get(i + 1))) && (i <= 0 || i != list.size() - 1 || !valueAndFormat.equals(list.get(i - 1)))) {
                hashSet.add(valueAndFormat);
            } else {
                i++;
            }
            i++;
        }
        return hashSet;
    }

    public Set<ValueAndFormat> evaluateDuplicateValues(List<ValueAndFormat> list) {
        Collections.sort(list);
        HashSet hashSet = new HashSet();
        int i = 0;
        while (i < list.size()) {
            ValueAndFormat valueAndFormat = list.get(i);
            if ((i < list.size() - 1 && valueAndFormat.equals(list.get(i + 1))) || (i > 0 && i == list.size() - 1 && valueAndFormat.equals(list.get(i - 1)))) {
                hashSet.add(valueAndFormat);
                i++;
            }
            i++;
        }
        return hashSet;
    }

    /* access modifiers changed from: private */
    public Set<ValueAndFormat> evaluateAboveAverage(List<ValueAndFormat> list) {
        ValueEval[] valueEvalArr = new ValueEval[list.size()];
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < list.size(); i++) {
            ValueAndFormat valueAndFormat = list.get(i);
            d2 += valueAndFormat.value.doubleValue();
            valueEvalArr[i] = new NumberEval(valueAndFormat.value.doubleValue());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(1);
        linkedHashSet.add(new ValueAndFormat(Double.valueOf(list.isEmpty() ? 0.0d : d2 / ((double) list.size())), (String) null, this.decimalTextFormat));
        if (list.size() > 1) {
            d = ((NumberEval) AggregateFunction.STDEV.evaluate(valueEvalArr, 0, 0)).getNumberValue();
        }
        linkedHashSet.add(new ValueAndFormat(Double.valueOf(d), (String) null, this.decimalTextFormat));
        return linkedHashSet;
    }

    private Set<ValueAndFormat> getMeaningfulValues(CellRangeAddress cellRangeAddress, boolean z, Function<List<ValueAndFormat>, Set<ValueAndFormat>> function) {
        Set<ValueAndFormat> set = this.meaningfulRegionValues.get(cellRangeAddress);
        if (set != null) {
            return set;
        }
        ArrayList arrayList = new ArrayList(((cellRangeAddress.getLastColumn() - cellRangeAddress.getFirstColumn()) + 1) * ((cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow()) + 1));
        for (int firstRow = cellRangeAddress.getFirstRow(); firstRow <= cellRangeAddress.getLastRow(); firstRow++) {
            Row row = this.sheet.getRow(firstRow);
            if (row != null) {
                for (int firstColumn = cellRangeAddress.getFirstColumn(); firstColumn <= cellRangeAddress.getLastColumn(); firstColumn++) {
                    ValueAndFormat cellValue = getCellValue(row.getCell(firstColumn));
                    if (z || cellValue.isNumber()) {
                        arrayList.add(cellValue);
                    }
                }
            }
        }
        Set<ValueAndFormat> apply = function.apply(arrayList);
        this.meaningfulRegionValues.put(cellRangeAddress, apply);
        return apply;
    }

    private ValueAndFormat getCellValue(Cell cell) {
        if (cell != null) {
            String dataFormatString = cell.getCellStyle().getDataFormatString();
            CellType cellType = cell.getCellType();
            if (cellType == CellType.FORMULA) {
                cellType = cell.getCachedFormulaResultType();
            }
            int i = AnonymousClass1.$SwitchMap$org$apache$poi$ss$usermodel$CellType[cellType.ordinal()];
            if (i == 1) {
                return new ValueAndFormat(Double.valueOf(cell.getNumericCellValue()), dataFormatString, this.decimalTextFormat);
            }
            if (i == 2 || i == 3) {
                return new ValueAndFormat(cell.getStringCellValue(), dataFormatString);
            }
        }
        return new ValueAndFormat("", "");
    }

    /* renamed from: org.apache.poi.ss.formula.EvaluationConditionalFormatRule$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$poi$ss$usermodel$CellType;

        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|42) */
        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|(2:1|2)|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|42) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|39|40|42) */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0091 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x009d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00a9 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00b5 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x00c1 */
        static {
            /*
                org.apache.poi.ss.usermodel.CellType[] r0 = org.apache.poi.ss.usermodel.CellType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$poi$ss$usermodel$CellType = r0
                r1 = 1
                org.apache.poi.ss.usermodel.CellType r2 = org.apache.poi.ss.usermodel.CellType.NUMERIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.poi.ss.usermodel.CellType r3 = org.apache.poi.ss.usermodel.CellType.STRING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$org$apache$poi$ss$usermodel$CellType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.poi.ss.usermodel.CellType r4 = org.apache.poi.ss.usermodel.CellType.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                org.apache.poi.ss.usermodel.ConditionFilterType[] r3 = org.apache.poi.ss.usermodel.ConditionFilterType.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType = r3
                org.apache.poi.ss.usermodel.ConditionFilterType r4 = org.apache.poi.ss.usermodel.ConditionFilterType.FILTER     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0043 }
                org.apache.poi.ss.usermodel.ConditionFilterType r3 = org.apache.poi.ss.usermodel.ConditionFilterType.TOP_10     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x004d }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.UNIQUE_VALUES     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0058 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.DUPLICATE_VALUES     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0063 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.ABOVE_AVERAGE     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x006e }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.CONTAINS_TEXT     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0079 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.NOT_CONTAINS_TEXT     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0085 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.BEGINS_WITH     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x0091 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.ENDS_WITH     // Catch:{ NoSuchFieldError -> 0x0091 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0091 }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0091 }
            L_0x0091:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x009d }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.CONTAINS_BLANKS     // Catch:{ NoSuchFieldError -> 0x009d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009d }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009d }
            L_0x009d:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x00a9 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.NOT_CONTAINS_BLANKS     // Catch:{ NoSuchFieldError -> 0x00a9 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a9 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a9 }
            L_0x00a9:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x00b5 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.CONTAINS_ERRORS     // Catch:{ NoSuchFieldError -> 0x00b5 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b5 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b5 }
            L_0x00b5:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x00c1 }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.NOT_CONTAINS_ERRORS     // Catch:{ NoSuchFieldError -> 0x00c1 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c1 }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c1 }
            L_0x00c1:
                int[] r0 = $SwitchMap$org$apache$poi$ss$usermodel$ConditionFilterType     // Catch:{ NoSuchFieldError -> 0x00cd }
                org.apache.poi.ss.usermodel.ConditionFilterType r1 = org.apache.poi.ss.usermodel.ConditionFilterType.TIME_PERIOD     // Catch:{ NoSuchFieldError -> 0x00cd }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cd }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cd }
            L_0x00cd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.EvaluationConditionalFormatRule.AnonymousClass1.<clinit>():void");
        }
    }

    protected static class ValueAndFormat implements Comparable<ValueAndFormat> {
        private final DecimalFormat decimalTextFormat;
        private final String format;
        private final String string;
        /* access modifiers changed from: private */
        public final Double value;

        public ValueAndFormat(Double d, String str, DecimalFormat decimalFormat) {
            this.value = d;
            this.format = str;
            this.string = null;
            this.decimalTextFormat = decimalFormat;
        }

        public ValueAndFormat(String str, String str2) {
            this.value = null;
            this.format = str2;
            this.string = str;
            this.decimalTextFormat = null;
        }

        public boolean isNumber() {
            return this.value != null;
        }

        public Double getValue() {
            return this.value;
        }

        public String getString() {
            return this.string;
        }

        public String toString() {
            if (isNumber()) {
                return this.decimalTextFormat.format(getValue().doubleValue());
            }
            return getString();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ValueAndFormat)) {
                return false;
            }
            ValueAndFormat valueAndFormat = (ValueAndFormat) obj;
            if (!Objects.equals(this.value, valueAndFormat.value) || !Objects.equals(this.format, valueAndFormat.format) || !Objects.equals(this.string, valueAndFormat.string)) {
                return false;
            }
            return true;
        }

        public int compareTo(ValueAndFormat valueAndFormat) {
            int i;
            Double d = this.value;
            if (d == null && valueAndFormat.value != null) {
                return 1;
            }
            Double d2 = valueAndFormat.value;
            if (d2 == null && d != null) {
                return -1;
            }
            if (d == null) {
                i = 0;
            } else {
                i = d.compareTo(d2);
            }
            if (i != 0) {
                return i;
            }
            String str = this.string;
            if (str == null && valueAndFormat.string != null) {
                return 1;
            }
            String str2 = valueAndFormat.string;
            if (str2 == null && str != null) {
                return -1;
            }
            if (str == null) {
                return 0;
            }
            return str.compareTo(str2);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.string, this.value, this.format});
        }
    }
}
