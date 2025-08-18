package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataValidation;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationErrorStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STDataValidationType;

public class XSSFDataValidation implements DataValidation {
    private static final int MAX_TEXT_LENGTH = 255;
    static Map<Integer, STDataValidationErrorStyle.Enum> errorStyleMappings;
    static Map<Integer, STDataValidationOperator.Enum> operatorTypeMappings = new HashMap();
    static Map<STDataValidationOperator.Enum, Integer> operatorTypeReverseMappings = new HashMap();
    static Map<STDataValidationErrorStyle.Enum, Integer> reverseErrorStyleMappings = MapUtils.invertMap(errorStyleMappings);
    static Map<Integer, STDataValidationType.Enum> validationTypeMappings = new HashMap();
    static Map<STDataValidationType.Enum, Integer> validationTypeReverseMappings = new HashMap();
    private CTDataValidation ctDataValidation;
    private CellRangeAddressList regions;
    private XSSFDataValidationConstraint validationConstraint;

    static {
        HashMap hashMap = new HashMap();
        errorStyleMappings = hashMap;
        hashMap.put(2, STDataValidationErrorStyle.INFORMATION);
        errorStyleMappings.put(0, STDataValidationErrorStyle.STOP);
        errorStyleMappings.put(1, STDataValidationErrorStyle.WARNING);
        operatorTypeMappings.put(0, STDataValidationOperator.BETWEEN);
        operatorTypeMappings.put(1, STDataValidationOperator.NOT_BETWEEN);
        operatorTypeMappings.put(2, STDataValidationOperator.EQUAL);
        operatorTypeMappings.put(3, STDataValidationOperator.NOT_EQUAL);
        operatorTypeMappings.put(4, STDataValidationOperator.GREATER_THAN);
        operatorTypeMappings.put(6, STDataValidationOperator.GREATER_THAN_OR_EQUAL);
        operatorTypeMappings.put(5, STDataValidationOperator.LESS_THAN);
        operatorTypeMappings.put(7, STDataValidationOperator.LESS_THAN_OR_EQUAL);
        for (Map.Entry next : operatorTypeMappings.entrySet()) {
            operatorTypeReverseMappings.put(next.getValue(), next.getKey());
        }
        validationTypeMappings.put(7, STDataValidationType.CUSTOM);
        validationTypeMappings.put(4, STDataValidationType.DATE);
        validationTypeMappings.put(2, STDataValidationType.DECIMAL);
        validationTypeMappings.put(3, STDataValidationType.LIST);
        validationTypeMappings.put(0, STDataValidationType.NONE);
        validationTypeMappings.put(6, STDataValidationType.TEXT_LENGTH);
        validationTypeMappings.put(5, STDataValidationType.TIME);
        validationTypeMappings.put(1, STDataValidationType.WHOLE);
        for (Map.Entry next2 : validationTypeMappings.entrySet()) {
            validationTypeReverseMappings.put(next2.getValue(), next2.getKey());
        }
    }

    XSSFDataValidation(CellRangeAddressList cellRangeAddressList, CTDataValidation cTDataValidation) {
        this(getConstraint(cTDataValidation), cellRangeAddressList, cTDataValidation);
    }

    public XSSFDataValidation(XSSFDataValidationConstraint xSSFDataValidationConstraint, CellRangeAddressList cellRangeAddressList, CTDataValidation cTDataValidation) {
        this.validationConstraint = xSSFDataValidationConstraint;
        this.ctDataValidation = cTDataValidation;
        this.regions = cellRangeAddressList;
    }

    /* access modifiers changed from: package-private */
    public CTDataValidation getCtDataValidation() {
        return this.ctDataValidation;
    }

    public void createErrorBox(String str, String str2) {
        if (str != null && str.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + str);
        } else if (str2 == null || str2.length() <= 255) {
            this.ctDataValidation.setErrorTitle(encodeUtf(str));
            this.ctDataValidation.setError(encodeUtf(str2));
        } else {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + str2);
        }
    }

    public void createPromptBox(String str, String str2) {
        if (str != null && str.length() > 255) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + str);
        } else if (str2 == null || str2.length() <= 255) {
            this.ctDataValidation.setPromptTitle(encodeUtf(str));
            this.ctDataValidation.setPrompt(encodeUtf(str2));
        } else {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + str2);
        }
    }

    private String encodeUtf(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c < ' ') {
                sb.append("_x").append(c < 16 ? "000" : TarConstants.VERSION_POSIX).append(Integer.toHexString(c)).append("_");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public boolean getEmptyCellAllowed() {
        return this.ctDataValidation.getAllowBlank();
    }

    public String getErrorBoxText() {
        return this.ctDataValidation.getError();
    }

    public String getErrorBoxTitle() {
        return this.ctDataValidation.getErrorTitle();
    }

    public int getErrorStyle() {
        return reverseErrorStyleMappings.get(this.ctDataValidation.getErrorStyle()).intValue();
    }

    public String getPromptBoxText() {
        return this.ctDataValidation.getPrompt();
    }

    public String getPromptBoxTitle() {
        return this.ctDataValidation.getPromptTitle();
    }

    public boolean getShowErrorBox() {
        return this.ctDataValidation.getShowErrorMessage();
    }

    public boolean getShowPromptBox() {
        return this.ctDataValidation.getShowInputMessage();
    }

    public boolean getSuppressDropDownArrow() {
        return !this.ctDataValidation.getShowDropDown();
    }

    public DataValidationConstraint getValidationConstraint() {
        return this.validationConstraint;
    }

    public void setEmptyCellAllowed(boolean z) {
        this.ctDataValidation.setAllowBlank(z);
    }

    public void setErrorStyle(int i) {
        this.ctDataValidation.setErrorStyle(errorStyleMappings.get(Integer.valueOf(i)));
    }

    public void setShowErrorBox(boolean z) {
        this.ctDataValidation.setShowErrorMessage(z);
    }

    public void setShowPromptBox(boolean z) {
        this.ctDataValidation.setShowInputMessage(z);
    }

    public void setSuppressDropDownArrow(boolean z) {
        if (this.validationConstraint.getValidationType() == 3) {
            this.ctDataValidation.setShowDropDown(!z);
        }
    }

    public CellRangeAddressList getRegions() {
        return this.regions;
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        for (CellRangeAddress formatAsString : this.regions.getCellRangeAddresses()) {
            sb.append(formatAsString.formatAsString());
        }
        sb.append(" => ");
        sb.append(this.validationConstraint.prettyPrint());
        return sb.toString();
    }

    private static XSSFDataValidationConstraint getConstraint(CTDataValidation cTDataValidation) {
        String formula1 = cTDataValidation.getFormula1();
        String formula2 = cTDataValidation.getFormula2();
        STDataValidationOperator.Enum operator = cTDataValidation.getOperator();
        return new XSSFDataValidationConstraint(validationTypeReverseMappings.get(cTDataValidation.getType()).intValue(), operatorTypeReverseMappings.get(operator).intValue(), formula1, formula2);
    }
}
