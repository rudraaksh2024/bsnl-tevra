package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Date {
    private static final int SIZE = 8;
    private final byte[] _value = new byte[8];

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        littleEndianByteArrayInputStream.readFully(this._value);
    }
}
