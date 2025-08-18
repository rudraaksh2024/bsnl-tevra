package org.apache.poi.xssf.usermodel;

import java.util.Arrays;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;

public class XSSFDataValidationConstraint implements DataValidationConstraint {
    private static final String LIST_SEPARATOR = ",";
    private static final Pattern LIST_SPLIT_REGEX = Pattern.compile("\\s*,\\s*");
    private static final int MAX_EXPLICIT_LIST_LENGTH = 257;
    private static final String QUOTE = "\"";
    private String[] explicitListOfValues;
    private String formula1;
    private String formula2;
    private int operator = -1;
    private final int validationType;

    public XSSFDataValidationConstraint(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            throw new IllegalArgumentException("List validation with explicit values must specify at least one value");
        }
        this.validationType = 3;
        setExplicitListValues(strArr);
        validate();
    }

    public XSSFDataValidationConstraint(int i, String str) {
        setFormula1(str);
        this.validationType = i;
        validate();
    }

    public XSSFDataValidationConstraint(int i, int i2, String str) {
        setFormula1(str);
        this.validationType = i;
        this.operator = i2;
        validate();
    }

    public XSSFDataValidationConstraint(int i, int i2, String str, String str2) {
        String str3;
        setFormula1(str);
        setFormula2(str2);
        this.validationType = i;
        this.operator = i2;
        validate();
        if (3 == i && (str3 = this.formula1) != null && isQuoted(str3)) {
            this.explicitListOfValues = LIST_SPLIT_REGEX.split(unquote(this.formula1));
        }
    }

    public String[] getExplicitListValues() {
        return this.explicitListOfValues;
    }

    public String getFormula1() {
        return this.formula1;
    }

    public String getFormula2() {
        return this.formula2;
    }

    public int getOperator() {
        return this.operator;
    }

    public int getValidationType() {
        return this.validationType;
    }

    public void setExplicitListValues(String[] strArr) {
        this.explicitListOfValues = strArr;
        if (strArr != null && strArr.length > 0) {
            StringBuilder sb = new StringBuilder(QUOTE);
            for (String str : strArr) {
                if (sb.length() > 1) {
                    sb.append(LIST_SEPARATOR);
                }
                sb.append(str);
            }
            sb.append(QUOTE);
            setFormula1(sb.toString());
        }
    }

    public void setFormula1(String str) {
        this.formula1 = removeLeadingEquals(str);
    }

    protected static String removeLeadingEquals(String str) {
        return (!isFormulaEmpty(str) && str.charAt(0) == '=') ? str.substring(1) : str;
    }

    private static boolean isQuoted(String str) {
        return str.startsWith(QUOTE) && str.endsWith(QUOTE);
    }

    private static String unquote(String str) {
        return isQuoted(str) ? str.substring(1, str.length() - 1) : str;
    }

    protected static boolean isFormulaEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public void setFormula2(String str) {
        this.formula2 = removeLeadingEquals(str);
    }

    public void setOperator(int i) {
        this.operator = i;
    }

    public void validate() {
        int i = this.validationType;
        if (i != 0) {
            if (i == 3) {
                if (isFormulaEmpty(this.formula1)) {
                    throw new IllegalArgumentException("A valid formula or a list of values must be specified for list validation.");
                } else if (this.formula1.length() > 257) {
                    throw new IllegalArgumentException("A valid formula or a list of values must be less than or equal to 255 characters (including separators).");
                }
            } else if (isFormulaEmpty(this.formula1)) {
                throw new IllegalArgumentException("Formula is not specified. Formula is required for all validation types except explicit list validation.");
            } else if (this.validationType != 7) {
                int i2 = this.operator;
                if (i2 == -1) {
                    throw new IllegalArgumentException("This validation type requires an operator to be specified.");
                } else if ((i2 == 0 || i2 == 1) && isFormulaEmpty(this.formula2)) {
                    throw new IllegalArgumentException("Between and not between comparisons require two formulae to be specified.");
                }
            }
        }
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        STDataValidationOperator.Enum enumR = XSSFDataValidation.operatorTypeMappings.get(Integer.valueOf(this.operator));
        sb.append(XSSFDataValidation.validationTypeMappings.get(Integer.valueOf(this.validationType)));
        sb.append(Chars.SPACE);
        int i = this.validationType;
        if (i != 0) {
            if (!(i == 3 || i == 7)) {
                sb.append(LIST_SEPARATOR).append(enumR).append(", ");
            }
            if (this.validationType != 3 || this.explicitListOfValues == null) {
                sb.append("").append(this.formula1).append(" ");
            } else {
                sb.append("").append(Arrays.asList(this.explicitListOfValues)).append(" ");
            }
            if (this.formula2 != null) {
                sb.append("").append(this.formula2).append(" ");
            }
        }
        return sb.toString();
    }
}
