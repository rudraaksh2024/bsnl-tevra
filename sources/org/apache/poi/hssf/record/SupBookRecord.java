package org.apache.poi.hssf.record;

import androidx.core.view.InputDeviceCompat;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public final class SupBookRecord extends StandardRecord {
    static final char CH_ALT_STARTUP_DIR = '\u0007';
    static final char CH_DOWN_DIR = '\u0003';
    static final char CH_LIB_DIR = '\b';
    static final char CH_LONG_VOLUME = '\u0005';
    static final char CH_SAME_VOLUME = '\u0002';
    static final char CH_STARTUP_DIR = '\u0006';
    static final char CH_UP_DIR = '\u0004';
    static final char CH_VOLUME = '\u0001';
    private static final Logger LOG = LogManager.getLogger((Class<?>) SupBookRecord.class);
    static final String PATH_SEPERATOR = System.getProperty("file.separator");
    private static final short SMALL_RECORD_SIZE = 4;
    private static final short TAG_ADD_IN_FUNCTIONS = 14849;
    private static final short TAG_INTERNAL_REFERENCES = 1025;
    public static final short sid = 430;
    private final boolean _isAddInFunctions;
    private short field_1_number_of_sheets;
    private String field_2_encoded_url;
    private final String[] field_3_sheet_names;

    public short getSid() {
        return sid;
    }

    public SupBookRecord(SupBookRecord supBookRecord) {
        super(supBookRecord);
        this.field_1_number_of_sheets = supBookRecord.field_1_number_of_sheets;
        this.field_2_encoded_url = supBookRecord.field_2_encoded_url;
        this.field_3_sheet_names = supBookRecord.field_3_sheet_names;
        this._isAddInFunctions = supBookRecord._isAddInFunctions;
    }

    private SupBookRecord(boolean z, short s) {
        this.field_1_number_of_sheets = s;
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        this._isAddInFunctions = z;
    }

    public SupBookRecord(String str, String[] strArr) {
        this.field_1_number_of_sheets = (short) strArr.length;
        this.field_2_encoded_url = str;
        this.field_3_sheet_names = strArr;
        this._isAddInFunctions = false;
    }

    public static SupBookRecord createInternalReferences(short s) {
        return new SupBookRecord(false, s);
    }

    public static SupBookRecord createAddInFunctions() {
        return new SupBookRecord(true, 1);
    }

    public static SupBookRecord createExternalReferences(String str, String[] strArr) {
        return new SupBookRecord(str, strArr);
    }

    public boolean isExternalReferences() {
        return this.field_3_sheet_names != null;
    }

    public boolean isInternalReferences() {
        return this.field_3_sheet_names == null && !this._isAddInFunctions;
    }

    public boolean isAddInFunctions() {
        return this.field_3_sheet_names == null && this._isAddInFunctions;
    }

    public SupBookRecord(RecordInputStream recordInputStream) {
        int remaining = recordInputStream.remaining();
        this.field_1_number_of_sheets = recordInputStream.readShort();
        if (remaining > 4) {
            this._isAddInFunctions = false;
            this.field_2_encoded_url = recordInputStream.readString();
            int i = this.field_1_number_of_sheets;
            String[] strArr = new String[i];
            for (int i2 = 0; i2 < i; i2++) {
                strArr[i2] = recordInputStream.readString();
            }
            this.field_3_sheet_names = strArr;
            return;
        }
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        short readShort = recordInputStream.readShort();
        if (readShort == 1025) {
            this._isAddInFunctions = false;
        } else if (readShort == 14849) {
            this._isAddInFunctions = true;
            if (this.field_1_number_of_sheets != 1) {
                throw new RuntimeException("Expected 0x0001 for number of sheets field in 'Add-In Functions' but got (" + this.field_1_number_of_sheets + ")");
            }
        } else {
            throw new RuntimeException("invalid EXTERNALBOOK code (" + Integer.toHexString(readShort) + ")");
        }
    }

    /* access modifiers changed from: protected */
    public int getDataSize() {
        if (!isExternalReferences()) {
            return 4;
        }
        int encodedSize = StringUtil.getEncodedSize(this.field_2_encoded_url) + 2;
        for (String encodedSize2 : this.field_3_sheet_names) {
            encodedSize += StringUtil.getEncodedSize(encodedSize2);
        }
        return encodedSize;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(this.field_1_number_of_sheets);
        if (isExternalReferences()) {
            StringUtil.writeUnicodeString(littleEndianOutput, this.field_2_encoded_url);
            for (String writeUnicodeString : this.field_3_sheet_names) {
                StringUtil.writeUnicodeString(littleEndianOutput, writeUnicodeString);
            }
            return;
        }
        littleEndianOutput.writeShort(this._isAddInFunctions ? 14849 : InputDeviceCompat.SOURCE_GAMEPAD);
    }

    public void setNumberOfSheets(short s) {
        this.field_1_number_of_sheets = s;
    }

    public short getNumberOfSheets() {
        return this.field_1_number_of_sheets;
    }

    public String getURL() {
        String str = this.field_2_encoded_url;
        if (str == null || str.length() < 2) {
            return str;
        }
        char charAt = str.charAt(0);
        if (charAt != 0) {
            if (charAt == 1) {
                return decodeFileName(str);
            }
            if (charAt != 2) {
                return str;
            }
        }
        return str.substring(1);
    }

    private static String decodeFileName(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case 1:
                    i++;
                    char charAt2 = str.charAt(i);
                    if (charAt2 != '@') {
                        sb.append(charAt2).append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                        break;
                    } else {
                        sb.append("\\\\");
                        break;
                    }
                case 2:
                case 3:
                    sb.append(PATH_SEPERATOR);
                    break;
                case 4:
                    sb.append("..").append(PATH_SEPERATOR);
                    break;
                case 5:
                    LOG.atWarn().log("Found unexpected key: ChLongVolume - IGNORING");
                    break;
                case 6:
                case 7:
                case 8:
                    LOG.atWarn().log("EXCEL.EXE path unknown - using this directory instead: .");
                    sb.append('.').append(PATH_SEPERATOR);
                    break;
                default:
                    sb.append(charAt);
                    break;
            }
            i++;
        }
        return sb.toString();
    }

    public String[] getSheetNames() {
        String[] strArr = this.field_3_sheet_names;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setURL(String str) {
        this.field_2_encoded_url = this.field_2_encoded_url.charAt(0) + str;
    }

    public SupBookRecord copy() {
        return new SupBookRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.SUP_BOOK;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("externalReferences", new SupBookRecord$$ExternalSyntheticLambda0(this), "internalReferences", new SupBookRecord$$ExternalSyntheticLambda1(this), ImagesContract.URL, new SupBookRecord$$ExternalSyntheticLambda2(this), "numberOfSheets", new SupBookRecord$$ExternalSyntheticLambda3(this), "sheetNames", new SupBookRecord$$ExternalSyntheticLambda4(this), "addInFunctions", new SupBookRecord$$ExternalSyntheticLambda5(this));
    }
}
