package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.StringUtil;

@Internal
public class UnicodeString {
    private static final Logger LOG = LogManager.getLogger((Class<?>) UnicodeString.class);
    private byte[] _value;

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        int readInt = littleEndianByteArrayInputStream.readInt();
        int i = readInt * 2;
        this._value = IOUtils.safelyAllocate((long) i, CodePageString.getMaxRecordLength());
        if (readInt != 0) {
            int readIndex = littleEndianByteArrayInputStream.getReadIndex();
            littleEndianByteArrayInputStream.readFully(this._value);
            byte[] bArr = this._value;
            if (bArr[i - 2] == 0 && bArr[i - 1] == 0) {
                TypedPropertyValue.skipPadding(littleEndianByteArrayInputStream);
                return;
            }
            throw new IllegalPropertySetDataException("UnicodeString started at offset #" + readIndex + " is not NULL-terminated");
        }
    }

    public byte[] getValue() {
        return this._value;
    }

    public String toJavaString() {
        byte[] bArr = this._value;
        if (bArr.length == 0) {
            return null;
        }
        String fromUnicodeLE = StringUtil.getFromUnicodeLE(bArr, 0, bArr.length >> 1);
        int indexOf = fromUnicodeLE.indexOf(0);
        if (indexOf == -1) {
            LOG.atWarn().log("String terminator (\\0) for UnicodeString property value not found. Continue without trimming and hope for the best.");
            return fromUnicodeLE;
        }
        if (indexOf != fromUnicodeLE.length() - 1) {
            LOG.atWarn().log("String terminator (\\0) for UnicodeString property value occured before the end of string. Trimming and hope for the best.");
        }
        return fromUnicodeLE.substring(0, indexOf);
    }

    public void setJavaValue(String str) throws UnsupportedEncodingException {
        this._value = CodePageUtil.getBytesInCodePage(str + "\u0000", 1200);
    }

    public int write(OutputStream outputStream) throws IOException {
        LittleEndian.putUInt((long) (this._value.length / 2), outputStream);
        outputStream.write(this._value);
        return this._value.length + 4;
    }
}
