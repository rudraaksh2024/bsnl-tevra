package org.apache.poi.hssf.record;

import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.LittleEndianOutput;

public final class CountryRecord extends StandardRecord {
    public static final short sid = 140;
    private short field_1_default_country;
    private short field_2_current_country;

    /* access modifiers changed from: protected */
    public int getDataSize() {
        return 4;
    }

    public short getSid() {
        return 140;
    }

    public CountryRecord() {
    }

    public CountryRecord(CountryRecord countryRecord) {
        super(countryRecord);
        this.field_1_default_country = countryRecord.field_1_default_country;
        this.field_2_current_country = countryRecord.field_2_current_country;
    }

    public CountryRecord(RecordInputStream recordInputStream) {
        this.field_1_default_country = recordInputStream.readShort();
        this.field_2_current_country = recordInputStream.readShort();
    }

    public void setDefaultCountry(short s) {
        this.field_1_default_country = s;
    }

    public void setCurrentCountry(short s) {
        this.field_2_current_country = s;
    }

    public short getDefaultCountry() {
        return this.field_1_default_country;
    }

    public short getCurrentCountry() {
        return this.field_2_current_country;
    }

    public void serialize(LittleEndianOutput littleEndianOutput) {
        littleEndianOutput.writeShort(getDefaultCountry());
        littleEndianOutput.writeShort(getCurrentCountry());
    }

    public CountryRecord copy() {
        return new CountryRecord(this);
    }

    public HSSFRecordTypes getGenericRecordType() {
        return HSSFRecordTypes.COUNTRY;
    }

    public Map<String, Supplier<?>> getGenericProperties() {
        return GenericRecordUtil.getGenericProperties("defaultCountry", new CountryRecord$$ExternalSyntheticLambda0(this), "currentCountry", new CountryRecord$$ExternalSyntheticLambda1(this));
    }
}
