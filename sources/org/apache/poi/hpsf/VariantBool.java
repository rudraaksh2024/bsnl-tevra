package org.apache.poi.hpsf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class VariantBool {
    private static final Logger LOG = LogManager.getLogger((Class<?>) VariantBool.class);
    static final int SIZE = 2;
    private boolean _value;

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        short readShort = littleEndianByteArrayInputStream.readShort();
        if (readShort == -1) {
            this._value = true;
        } else if (readShort != 0) {
            LOG.atWarn().log("VARIANT_BOOL value '{}' is incorrect", (Object) Unbox.box(readShort));
            this._value = true;
        } else {
            this._value = false;
        }
    }

    public boolean getValue() {
        return this._value;
    }

    public void setValue(boolean z) {
        this._value = z;
    }
}
