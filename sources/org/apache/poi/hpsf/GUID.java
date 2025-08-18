package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class GUID {
    private int _data1;
    private short _data2;
    private short _data3;
    private long _data4;

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this._data1 = littleEndianByteArrayInputStream.readInt();
        this._data2 = littleEndianByteArrayInputStream.readShort();
        this._data3 = littleEndianByteArrayInputStream.readShort();
        this._data4 = littleEndianByteArrayInputStream.readLong();
    }
}
