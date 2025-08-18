package org.apache.poi.hssf.record.cf;

import org.apache.poi.common.Duplicatable;
import org.apache.poi.util.LittleEndianInput;

public final class DataBarThreshold extends Threshold implements Duplicatable {
    public DataBarThreshold() {
    }

    public DataBarThreshold(DataBarThreshold dataBarThreshold) {
        super((Threshold) dataBarThreshold);
    }

    public DataBarThreshold(LittleEndianInput littleEndianInput) {
        super(littleEndianInput);
    }

    public DataBarThreshold copy() {
        return new DataBarThreshold(this);
    }
}
