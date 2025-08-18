package org.apache.poi.hpsf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class ClipboardData {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) ClipboardData.class);
    private static int MAX_RECORD_LENGTH = 100000000;
    private int _format;
    private byte[] _value;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        int readIndex = littleEndianByteArrayInputStream.getReadIndex();
        long readInt = (long) littleEndianByteArrayInputStream.readInt();
        if (readInt < 4) {
            LOG.atWarn().log("ClipboardData at offset {} size less than 4 bytes (doesn't even have format field!). Setting to format == 0 and hope for the best", (Object) Unbox.box(readIndex));
            this._format = 0;
            this._value = new byte[0];
            return;
        }
        this._format = littleEndianByteArrayInputStream.readInt();
        byte[] safelyAllocate = IOUtils.safelyAllocate(readInt - 4, MAX_RECORD_LENGTH);
        this._value = safelyAllocate;
        littleEndianByteArrayInputStream.readFully(safelyAllocate);
    }

    public byte[] getValue() {
        return this._value;
    }

    public byte[] toByteArray() {
        byte[] bArr = this._value;
        byte[] bArr2 = new byte[(bArr.length + 8)];
        LittleEndian.putInt(bArr2, 0, bArr.length + 4);
        LittleEndian.putInt(bArr2, 4, this._format);
        byte[] bArr3 = this._value;
        System.arraycopy(bArr3, 0, bArr2, 8, bArr3.length);
        return bArr2;
    }

    public void setValue(byte[] bArr) {
        this._value = (byte[]) bArr.clone();
    }
}
