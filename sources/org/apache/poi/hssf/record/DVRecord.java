package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.BitField;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class DVRecord extends StandardRecord {
    private static final int[] FLAG_MASKS = {15, 112, 128, 256, 512, 262144, 524288, 7340032};
    private static final String[] FLAG_NAMES = {"DATA_TYPE", "ERROR_STYLE", "STRING_LIST_FORMULA", "EMPTY_CELL_ALLOWED", "SUPPRESS_DROPDOWN_ARROW", "SHOW_PROMPT_ON_CELL_SELECTED", "SHOW_ERROR_ON_INVALID_VALUE", "CONDITION_OPERATOR"};
    private static final UnicodeString NULL_TEXT_STRING = new UnicodeString("\u0000");
    private static final BitField opt_condition_operator = new BitField(7340032);
    private static final BitField opt_data_type = new BitField(15);
    private static final BitField opt_empty_cell_allowed = new BitField(256);
    private static final BitField opt_error_style = new BitField(112);
    private static final BitField opt_show_error_on_invalid_value = new BitField(524288);
    private static final BitField opt_show_prompt_on_cell_selected = new BitField(262144);
    private static final BitField opt_string_list_formula = new BitField(128);
    private static final BitField opt_suppress_dropdown_arrow = new BitField(512);
    public static final short sid = 446;
    private final UnicodeString _errorText;
    private final UnicodeString _errorTitle;
    private final Formula _formula1;
    private final Formula _formula2;
    private short _not_used_1 = 16352;
    private short _not_used_2 = 0;
    private int _option_flags;
    private final UnicodeString _promptText;
    private final UnicodeString _promptTitle;
    private final CellRangeAddressList _regions;

    public short getSid() {
        return sid;
    }

    public DVRecord(DVRecord dVRecord) {
        super(dVRecord);
        this._option_flags = dVRecord._option_flags;
        this._promptTitle = dVRecord._promptTitle.copy();
        this._errorTitle = dVRecord._errorTitle.copy();
        this._promptText = dVRecord._promptText.copy();
        this._errorText = dVRecord._errorText.copy();
        this._not_used_1 = dVRecord._not_used_1;
        Formula formula = dVRecord._formula1;
        CellRangeAddressList cellRangeAddressList = null;
        this._formula1 = formula == null ? null : formula.copy();
        this._not_used_2 = dVRecord._not_used_2;
        Formula formula2 = dVRecord._formula2;
        this._formula2 = formula2 == null ? null : formula2.copy();
        CellRangeAddressList cellRangeAddressList2 = dVRecord._regions;
        this._regions = cellRangeAddressList2 != null ? cellRangeAddressList2.copy() : cellRangeAddressList;
    }

    public DVRecord(int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, String str, String str2, boolean z5, String str3, String str4, Ptg[] ptgArr, Ptg[] ptgArr2, CellRangeAddressList cellRangeAddressList) {
        String str5 = str;
        String str6 = str2;
        String str7 = str3;
        String str8 = str4;
        if (str5 != null && str.length() > 32) {
            throw new IllegalStateException("Prompt-title cannot be longer than 32 characters, but had: " + str5);
        } else if (str6 != null && str2.length() > 255) {
            throw new IllegalStateException("Prompt-text cannot be longer than 255 characters, but had: " + str6);
        } else if (str7 != null && str3.length() > 32) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + str7);
        } else if (str8 == null || str4.length() <= 255) {
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            boolean z6 = z;
            boolean z7 = z2;
            boolean z8 = z3;
            this._option_flags = opt_show_error_on_invalid_value.setBoolean(opt_show_prompt_on_cell_selected.setBoolean(opt_string_list_formula.setBoolean(opt_suppress_dropdown_arrow.setBoolean(opt_empty_cell_allowed.setBoolean(opt_error_style.setValue(opt_condition_operator.setValue(opt_data_type.setValue(0, i), i2), i3), z), z2), z3), z4), z5);
            this._promptTitle = resolveTitleText(str);
            this._promptText = resolveTitleText(str2);
            this._errorTitle = resolveTitleText(str3);
            this._errorText = resolveTitleText(str4);
            this._formula1 = Formula.create(ptgArr);
            this._formula2 = Formula.create(ptgArr2);
            this._regions = cellRangeAddressList;
        } else {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + str8);
        }
    }

    public DVRecord(RecordInputStream recordInputStream) {
        this._option_flags = recordInputStream.readInt();
        this._promptTitle = readUnicodeString(recordInputStream);
        this._errorTitle = readUnicodeString(recordInputStream);
        this._promptText = readUnicodeString(recordInputStream);
        this._errorText = readUnicodeString(recordInputStream);
        int readUShort = recordInputStream.readUShort();
        this._not_used_1 = recordInputStream.readShort();
        this._formula1 = Formula.read(readUShort, recordInputStream);
        int readUShort2 = recordInputStream.readUShort();
        this._not_used_2 = recordInputStream.readShort();
        this._formula2 = Formula.read(readUShort2, recordInputStream);
        this._regions = new CellRangeAddressList(recordInputStream);
    }

    public int getDataType() {
        return opt_data_type.getValue(this._option_flags);
    }

    public int getErrorStyle() {
        return opt_error_style.getValue(this._option_flags);
    }

    public boolean getListExplicitFormula() {
        return opt_string_list_formula.isSet(this._option_flags);
    }

    public boolean getEmptyCellAllowed() {
        return opt_empty_cell_allowed.isSet(this._option_flags);
    }

    public boolean getSuppressDropdownArrow() {
        return opt_suppress_dropdown_arrow.isSet(this._option_flags);
    }

    public boolean getShowPromptOnCellSelected() {
        return opt_show_prompt_on_cell_selected.isSet(this._option_flags);
    }

    public boolean getShowErrorOnInvalidValue() {
        return opt_show_error_on_invalid_value.isSet(this._option_flags);
    }

    public int getConditionOperator() {
        return opt_condition_operator.getValue(this._option_flags);
    }

    public String getPromptTitle() {
        return resolveTitleString(this._promptTitle);
    }

    public String getErrorTitle() {
        return resolveTitleString(this._errorTitle);
    }

    public String getPromptText() {
        return resolveTitleString(this._promptText);
    }

    public String getErrorText() {
        return resolveTitleString(this._errorText);
    }

    public Ptg[] getFormula1() {
        return Formula.getTokens(this._formula1);
    }

    public Ptg[] getFormula2() {
        return Formula.getTokens(this._formula2);
    }

    public CellRangeAddressList getCellRangeAddress() {
        return this._regions;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeInt(this._option_flags);
        serializeUnicodeString(this._promptTitle, littleEndianOutput);
        serializeUnicodeString(this._errorTitle, littleEndianOutput);
        serializeUnicodeString(this._promptText, littleEndianOutput);
        serializeUnicodeString(this._errorText, littleEndianOutput);
        littleEndianOutput.writeShort(this._formula1.getEncodedTokenSize());
        littleEndianOutput.writeShort(this._not_used_1);
        this._formula1.serializeTokens(littleEndianOutput);
        littleEndianOutput.writeShort(this._formula2.getEncodedTokenSize());
        littleEndianOutput.writeShort(this._not_used_2);
        this._formula2.serializeTokens(littleEndianOutput);
        this._regions.serialize(littleEndianOutput);
    }

    private static UnicodeString resolveTitleText(String str) {
        if (str == null || str.length() < 1) {
            return NULL_TEXT_STRING;
        }
        return new UnicodeString(str);
    }

    private static String resolveTitleString(UnicodeString unicodeString) {
        if (unicodeString == null || unicodeString.equals(NULL_TEXT_STRING)) {
            return null;
        }
        return unicodeString.getString();
    }

    private static UnicodeString readUnicodeString(RecordInputStream recordInputStream) {
        return new UnicodeString(recordInputStream);
    }

    private static void serializeUnicodeString(UnicodeString unicodeString, LittleEndianOutput littleEndianOutput) {
        StringUtil.writeUnicodeString(littleEndianOutput, unicodeString.getString());
    }

    private static int getUnicodeStringSize(UnicodeString unicodeString) {
        String string = unicodeString.getString();
        return (string.length() * (StringUtil.hasMultibyte(string) ? 2 : 1)) + 3;
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return getUnicodeStringSize(this._promptTitle) + 12 + getUnicodeStringSize(this._errorTitle) + getUnicodeStringSize(this._promptText) + getUnicodeStringSize(this._errorText) + this._formula1.getEncodedTokenSize() + this._formula2.getEncodedTokenSize() + this._regions.getSize();
    }

    public DVRecord copy() {
        return new DVRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.DV;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("optionFlags", GenericRecordUtil.getBitsAsString((Supplier<Number>) new DVRecord$$ExternalSyntheticLambda0(this), FLAG_MASKS, FLAG_NAMES), "promptTitle", new DVRecord$$ExternalSyntheticLambda1(this), "errorTitle", new DVRecord$$ExternalSyntheticLambda2(this), "promptText", new DVRecord$$ExternalSyntheticLambda3(this), "errorText", new DVRecord$$ExternalSyntheticLambda4(this), "formula1", new DVRecord$$ExternalSyntheticLambda5(this), "formula2", new DVRecord$$ExternalSyntheticLambda6(this), "regions", new DVRecord$$ExternalSyntheticLambda7(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$0$org-apache-poi-hssf-record-DVRecord  reason: not valid java name */
    public /* synthetic */ Number m1992lambda$getGenericProperties$0$orgapachepoihssfrecordDVRecord() {
        return Integer.valueOf(this._option_flags);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getGenericProperties$1$org-apache-poi-hssf-record-DVRecord  reason: not valid java name */
    public /* synthetic */ Object m1993lambda$getGenericProperties$1$orgapachepoihssfrecordDVRecord() {
        return this._regions;
    }
}
