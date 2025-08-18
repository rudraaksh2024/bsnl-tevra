package org.apache.poi.hpsf;

import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class Decimal {
    private short field_1_wReserved;
    private byte field_2_scale;
    private byte field_3_sign;
    private int field_4_hi32;
    private long field_5_lo64;

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        this.field_1_wReserved = littleEndianByteArrayInputStream.readShort();
        this.field_2_scale = littleEndianByteArrayInputStream.readByte();
        this.field_3_sign = littleEndianByteArrayInputStream.readByte();
        this.field_4_hi32 = littleEndianByteArrayInputStream.readInt();
        this.field_5_lo64 = littleEndianByteArrayInputStream.readLong();
    }
}
