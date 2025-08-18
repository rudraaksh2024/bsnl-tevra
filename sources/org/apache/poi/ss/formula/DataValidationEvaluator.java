package org.apache.poi.ss.formula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.formula.eval.BlankEval;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressBase;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.util.SheetUtil;

public class DataValidationEvaluator {
    private final Map<String, List<? extends DataValidation>> validations = new HashMap();
    private final Workbook workbook;
    private final WorkbookEvaluator workbookEvaluator;

    public DataValidationEvaluator(Workbook workbook2, WorkbookEvaluatorProvider workbookEvaluatorProvider) {
        this.workbook = workbook2;
        this.workbookEvaluator = workbookEvaluatorProvider._getWorkbookEvaluator();
    }

    /* access modifiers changed from: protected */
    public WorkbookEvaluator getWorkbookEvaluator() {
        return this.workbookEvaluator;
    }

    public void clearAllCachedValues() {
        this.validations.clear();
    }

    private List<? extends DataValidation> getValidations(Sheet sheet) {
        List<? extends DataValidation> list = this.validations.get(sheet.getSheetName());
        if (list != null || this.validations.containsKey(sheet.getSheetName())) {
            return list;
        }
        List<? extends DataValidation> dataValidations = sheet.getDataValidations();
        this.validations.put(sheet.getSheetName(), dataValidations);
        return dataValidations;
    }

    public DataValidation getValidationForCell(CellReference cellReference) {
        DataValidationContext validationContextForCell = getValidationContextForCell(cellReference);
        if (validationContextForCell == null) {
            return null;
        }
        return validationContextForCell.getValidation();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r2 = (org.apache.poi.ss.usermodel.DataValidation) r0.next();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.poi.ss.formula.DataValidationEvaluator.DataValidationContext getValidationContextForCell(org.apache.poi.ss.util.CellReference r9) {
        /*
            r8 = this;
            org.apache.poi.ss.usermodel.Workbook r0 = r8.workbook
            java.lang.String r1 = r9.getSheetName()
            org.apache.poi.ss.usermodel.Sheet r0 = r0.getSheet(r1)
            r1 = 0
            if (r0 != 0) goto L_0x000e
            return r1
        L_0x000e:
            java.util.List r0 = r8.getValidations(r0)
            if (r0 != 0) goto L_0x0015
            return r1
        L_0x0015:
            java.util.Iterator r0 = r0.iterator()
        L_0x0019:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0045
            java.lang.Object r2 = r0.next()
            org.apache.poi.ss.usermodel.DataValidation r2 = (org.apache.poi.ss.usermodel.DataValidation) r2
            org.apache.poi.ss.util.CellRangeAddressList r3 = r2.getRegions()
            if (r3 != 0) goto L_0x002c
            return r1
        L_0x002c:
            org.apache.poi.ss.util.CellRangeAddress[] r3 = r3.getCellRangeAddresses()
            int r4 = r3.length
            r5 = 0
        L_0x0032:
            if (r5 >= r4) goto L_0x0019
            r6 = r3[r5]
            boolean r7 = r6.isInRange((org.apache.poi.ss.util.CellReference) r9)
            if (r7 == 0) goto L_0x0042
            org.apache.poi.ss.formula.DataValidationEvaluator$DataValidationContext r0 = new org.apache.poi.ss.formula.DataValidationEvaluator$DataValidationContext
            r0.<init>(r2, r8, r6, r9)
            return r0
        L_0x0042:
            int r5 = r5 + 1
            goto L_0x0032
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.ss.formula.DataValidationEvaluator.getValidationContextForCell(org.apache.poi.ss.util.CellReference):org.apache.poi.ss.formula.DataValidationEvaluator$DataValidationContext");
    }

    public List<ValueEval> getValidationValuesForCell(CellReference cellReference) {
        DataValidationContext validationContextForCell = getValidationContextForCell(cellReference);
        if (validationContextForCell == null) {
            return null;
        }
        return getValidationValuesForConstraint(validationContextForCell);
    }

    protected static List<ValueEval> getValidationValuesForConstraint(DataValidationContext dataValidationContext) {
        DataValidationConstraint validationConstraint = dataValidationContext.getValidation().getValidationConstraint();
        if (validationConstraint.getValidationType() != 3) {
            return null;
        }
        String formula1 = validationConstraint.getFormula1();
        ArrayList arrayList = new ArrayList();
        if (validationConstraint.getExplicitListValues() != null && validationConstraint.getExplicitListValues().length > 0) {
            for (String str : validationConstraint.getExplicitListValues()) {
                if (str != null) {
                    arrayList.add(new StringEval(str));
                }
            }
        } else if (formula1 != null) {
            ValueEval evaluateList = dataValidationContext.getEvaluator().getWorkbookEvaluator().evaluateList(formula1, dataValidationContext.getTarget(), dataValidationContext.getRegion());
            if (evaluateList instanceof TwoDEval) {
                TwoDEval twoDEval = (TwoDEval) evaluateList;
                for (int i = 0; i < twoDEval.getHeight(); i++) {
                    arrayList.add(twoDEval.getValue(i, 0));
                }
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public boolean isValidCell(CellReference cellReference) {
        DataValidationContext validationContextForCell = getValidationContextForCell(cellReference);
        if (validationContextForCell == null) {
            return true;
        }
        Cell cell = SheetUtil.getCell(this.workbook.getSheet(cellReference.getSheetName()), cellReference.getRow(), cellReference.getCol());
        if (cell == null || isType(cell, CellType.BLANK) || (isType(cell, CellType.STRING) && (cell.getStringCellValue() == null || cell.getStringCellValue().isEmpty()))) {
            return validationContextForCell.getValidation().getEmptyCellAllowed();
        }
        return ValidationEnum.isValid(cell, validationContextForCell);
    }

    public static boolean isType(Cell cell, CellType cellType) {
        CellType cellType2 = cell.getCellType();
        return cellType2 == cellType || (cellType2 == CellType.FORMULA && cell.getCachedFormulaResultType() == cellType);
    }

    public enum ValidationEnum {
        ANY {
            public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
                return true;
            }
        },
        INTEGER {
            public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
                if (!super.isValidValue(cell, dataValidationContext)) {
                    return false;
                }
                double numericCellValue = cell.getNumericCellValue();
                if (Double.compare(numericCellValue, (double) ((int) numericCellValue)) == 0) {
                    return true;
                }
                return false;
            }
        },
        DECIMAL,
        LIST {
            public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
                List<ValueEval> validationValuesForConstraint = DataValidationEvaluator.getValidationValuesForConstraint(dataValidationContext);
                if (validationValuesForConstraint == null) {
                    return true;
                }
                Iterator<ValueEval> it = validationValuesForConstraint.iterator();
                while (it.hasNext()) {
                    ValueEval next = it.next();
                    if (next instanceof RefEval) {
                        next = ((RefEval) next).getInnerValueEval(dataValidationContext.getSheetIndex());
                    }
                    if (next instanceof BlankEval) {
                        return true;
                    }
                    if (!(next instanceof ErrorEval)) {
                        if (next instanceof BoolEval) {
                            if (DataValidationEvaluator.isType(cell, CellType.BOOLEAN) && ((BoolEval) next).getBooleanValue() == cell.getBooleanCellValue()) {
                                return true;
                            }
                        } else if (next instanceof NumberEval) {
                            if (DataValidationEvaluator.isType(cell, CellType.NUMERIC) && ((NumberEval) next).getNumberValue() == cell.getNumericCellValue()) {
                                return true;
                            }
                        } else if ((next instanceof StringEval) && DataValidationEvaluator.isType(cell, CellType.STRING) && ((StringEval) next).getStringValue().equalsIgnoreCase(cell.getStringCellValue())) {
                            return true;
                        }
                    }
                }
                return false;
            }
        },
        DATE,
        TIME,
        TEXT_LENGTH {
            public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
                if (!DataValidationEvaluator.isType(cell, CellType.STRING)) {
                    return false;
                }
                return isValidNumericValue(Double.valueOf((double) cell.getStringCellValue().length()), dataValidationContext);
            }
        },
        FORMULA {
            public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
                ValueEval evaluate = dataValidationContext.getEvaluator().getWorkbookEvaluator().evaluate(dataValidationContext.getFormula1(), dataValidationContext.getTarget(), dataValidationContext.getRegion());
                if (evaluate instanceof RefEval) {
                    RefEval refEval = (RefEval) evaluate;
                    evaluate = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
                }
                if (evaluate instanceof BlankEval) {
                    return true;
                }
                if (evaluate instanceof ErrorEval) {
                    return false;
                }
                if (evaluate instanceof BoolEval) {
                    return ((BoolEval) evaluate).getBooleanValue();
                }
                if (!(evaluate instanceof NumberEval)) {
                    return false;
                }
                if (((NumberEval) evaluate).getNumberValue() != 0.0d) {
                    return true;
                }
                return false;
            }
        };

        public boolean isValidValue(Cell cell, DataValidationContext dataValidationContext) {
            return isValidNumericCell(cell, dataValidationContext);
        }

        /* access modifiers changed from: protected */
        public boolean isValidNumericCell(Cell cell, DataValidationContext dataValidationContext) {
            if (!DataValidationEvaluator.isType(cell, CellType.NUMERIC)) {
                return false;
            }
            return isValidNumericValue(Double.valueOf(cell.getNumericCellValue()), dataValidationContext);
        }

        /* access modifiers changed from: protected */
        public boolean isValidNumericValue(Double d, DataValidationContext dataValidationContext) {
            Double d2;
            try {
                Double evalOrConstant = evalOrConstant(dataValidationContext.getFormula1(), dataValidationContext);
                if (evalOrConstant == null) {
                    return true;
                }
                if (dataValidationContext.getOperator() != 0) {
                    if (dataValidationContext.getOperator() != 1) {
                        d2 = null;
                        return OperatorEnum.values()[dataValidationContext.getOperator()].isValid(d, evalOrConstant, d2);
                    }
                }
                d2 = evalOrConstant(dataValidationContext.getFormula2(), dataValidationContext);
                if (d2 == null) {
                    return true;
                }
                return OperatorEnum.values()[dataValidationContext.getOperator()].isValid(d, evalOrConstant, d2);
            } catch (NumberFormatException unused) {
                return false;
            }
        }

        private Double evalOrConstant(String str, DataValidationContext dataValidationContext) throws NumberFormatException {
            if (str == null || str.trim().isEmpty()) {
                return null;
            }
            try {
                return Double.valueOf(str);
            } catch (NumberFormatException unused) {
                ValueEval evaluate = dataValidationContext.getEvaluator().getWorkbookEvaluator().evaluate(str, dataValidationContext.getTarget(), dataValidationContext.getRegion());
                if (evaluate instanceof RefEval) {
                    RefEval refEval = (RefEval) evaluate;
                    evaluate = refEval.getInnerValueEval(refEval.getFirstSheetIndex());
                }
                if (evaluate instanceof BlankEval) {
                    return null;
                }
                if (evaluate instanceof NumberEval) {
                    return Double.valueOf(((NumberEval) evaluate).getNumberValue());
                }
                if (evaluate instanceof StringEval) {
                    String stringValue = ((StringEval) evaluate).getStringValue();
                    if (stringValue == null || stringValue.trim().isEmpty()) {
                        return null;
                    }
                    return Double.valueOf(stringValue);
                }
                throw new NumberFormatException("Formula '" + str + "' evaluates to something other than a number");
            }
        }

        public static boolean isValid(Cell cell, DataValidationContext dataValidationContext) {
            return values()[dataValidationContext.getValidation().getValidationConstraint().getValidationType()].isValidValue(cell, dataValidationContext);
        }
    }

    public enum OperatorEnum {
        BETWEEN {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) >= 0 && d.compareTo(d3) <= 0;
            }
        },
        NOT_BETWEEN {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) < 0 || d.compareTo(d3) > 0;
            }
        },
        EQUAL {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) == 0;
            }
        },
        NOT_EQUAL {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) != 0;
            }
        },
        GREATER_THAN {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) > 0;
            }
        },
        LESS_THAN {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) < 0;
            }
        },
        GREATER_OR_EQUAL {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) >= 0;
            }
        },
        LESS_OR_EQUAL {
            public boolean isValid(Double d, Double d2, Double d3) {
                return d.compareTo(d2) <= 0;
            }
        };
        
        public static final OperatorEnum IGNORED = null;

        public abstract boolean isValid(Double d, Double d2, Double d3);

        static {
            AnonymousClass1 r0;
            IGNORED = r0;
        }
    }

    public static class DataValidationContext {
        private final DataValidation dv;
        private final DataValidationEvaluator dve;
        private final CellRangeAddressBase region;
        private final CellReference target;

        public DataValidationContext(DataValidation dataValidation, DataValidationEvaluator dataValidationEvaluator, CellRangeAddressBase cellRangeAddressBase, CellReference cellReference) {
            this.dv = dataValidation;
            this.dve = dataValidationEvaluator;
            this.region = cellRangeAddressBase;
            this.target = cellReference;
        }

        public DataValidation getValidation() {
            return this.dv;
        }

        public DataValidationEvaluator getEvaluator() {
            return this.dve;
        }

        public CellRangeAddressBase getRegion() {
            return this.region;
        }

        public CellReference getTarget() {
            return this.target;
        }

        public int getOffsetColumns() {
            return this.target.getCol() - this.region.getFirstColumn();
        }

        public int getOffsetRows() {
            return this.target.getRow() - this.region.getFirstRow();
        }

        public int getSheetIndex() {
            return this.dve.getWorkbookEvaluator().getSheetIndex(this.target.getSheetName());
        }

        public String getFormula1() {
            return this.dv.getValidationConstraint().getFormula1();
        }

        public String getFormula2() {
            return this.dv.getValidationConstraint().getFormula2();
        }

        public int getOperator() {
            return this.dv.getValidationConstraint().getOperator();
        }
    }
}
