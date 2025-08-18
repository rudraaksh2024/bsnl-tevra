package org.apache.poi.hssf.record;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.LittleEndianOutput;

public final class BOFRecord extends StandardRecord {
    public static final int BUILD = 4307;
    public static final int BUILD_YEAR = 1996;
    public static final int HISTORY_MASK = 65;
    public static final int TYPE_CHART = 32;
    public static final int TYPE_EXCEL_4_MACRO = 64;
    public static final int TYPE_VB_MODULE = 6;
    public static final int TYPE_WORKBOOK = 5;
    public static final int TYPE_WORKSHEET = 16;
    public static final int TYPE_WORKSPACE_FILE = 256;
    public static final int VERSION = 1536;
    public static final short biff2_sid = 9;
    public static final short biff3_sid = 521;
    public static final short biff4_sid = 1033;
    public static final short biff5_sid = 2057;
    public static final short sid = 2057;
    private int field_1_version;
    private int field_2_type;
    private int field_3_build;
    private int field_4_year;
    private int field_5_history;
    private int field_6_rversion;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 16;
    }

    public short getSid() {
        return 2057;
    }

    public BOFRecord() {
    }

    public BOFRecord(BOFRecord bOFRecord) {
        super(bOFRecord);
        this.field_1_version = bOFRecord.field_1_version;
        this.field_2_type = bOFRecord.field_2_type;
        this.field_3_build = bOFRecord.field_3_build;
        this.field_4_year = bOFRecord.field_4_year;
        this.field_5_history = bOFRecord.field_5_history;
        this.field_6_rversion = bOFRecord.field_6_rversion;
    }

    private BOFRecord(int i) {
        this.field_1_version = VERSION;
        this.field_2_type = i;
        this.field_3_build = BUILD;
        this.field_4_year = BUILD_YEAR;
        this.field_5_history = 1;
        this.field_6_rversion = VERSION;
    }

    public static BOFRecord createSheetBOF() {
        return new BOFRecord(16);
    }

    public BOFRecord(RecordInputStream recordInputStream) {
        this.field_1_version = recordInputStream.readShort();
        this.field_2_type = recordInputStream.readShort();
        if (recordInputStream.remaining() >= 2) {
            this.field_3_build = recordInputStream.readShort();
        }
        if (recordInputStream.remaining() >= 2) {
            this.field_4_year = recordInputStream.readShort();
        }
        if (recordInputStream.remaining() >= 4) {
            this.field_5_history = recordInputStream.readInt();
        }
        if (recordInputStream.remaining() >= 4) {
            this.field_6_rversion = recordInputStream.readInt();
        }
    }

    public void setVersion(int i) {
        this.field_1_version = i;
    }

    public void setType(int i) {
        this.field_2_type = i;
    }

    public void setBuild(int i) {
        this.field_3_build = i;
    }

    public void setBuildYear(int i) {
        this.field_4_year = i;
    }

    public void setHistoryBitMask(int i) {
        this.field_5_history = i;
    }

    public void setRequiredVersion(int i) {
        this.field_6_rversion = i;
    }

    public int getVersion() {
        return this.field_1_version;
    }

    public int getType() {
        return this.field_2_type;
    }

    public int getBuild() {
        return this.field_3_build;
    }

    public int getBuildYear() {
        return this.field_4_year;
    }

    public int getHistoryBitMask() {
        return this.field_5_history;
    }

    public int getRequiredVersion() {
        return this.field_6_rversion;
    }

    /* access modifiers changed from: private */
    public String getTypeName() {
        int i = this.field_2_type;
        if (i == 5) {
            return "workbook";
        }
        if (i == 6) {
            return "vb module";
        }
        if (i == 16) {
            return "worksheet";
        }
        if (i == 32) {
            return "chart";
        }
        if (i != 64) {
            return i != 256 ? "#error unknown type#" : "workspace file";
        }
        return "excel 4 macro";
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getVersion());
        littleEndianOutput.writeShort(getType());
        littleEndianOutput.writeShort(getBuild());
        littleEndianOutput.writeShort(getBuildYear());
        littleEndianOutput.writeInt(getHistoryBitMask());
        littleEndianOutput.writeInt(getRequiredVersion());
    }

    public BOFRecord copy() {
        return new BOFRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.BOF;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("version", new BOFRecord$$ExternalSyntheticLambda0(this));
        linkedHashMap.put("type", new BOFRecord$$ExternalSyntheticLambda1(this));
        linkedHashMap.put("typeName", new BOFRecord$$ExternalSyntheticLambda2(this));
        linkedHashMap.put("build", new BOFRecord$$ExternalSyntheticLambda3(this));
        linkedHashMap.put("buildYear", new BOFRecord$$ExternalSyntheticLambda4(this));
        linkedHashMap.put("history", new BOFRecord$$ExternalSyntheticLambda5(this));
        linkedHashMap.put("requiredVersion", new BOFRecord$$ExternalSyntheticLambda6(this));
        return Collections.unmodifiableMap(linkedHashMap);
    }
}
