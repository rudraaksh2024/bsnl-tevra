package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;

@Internal
public class CodePageString {
    private static final int DEFAULT_MAX_RECORD_LENGTH = 100000;
    private static final Logger LOG = LogManager.getLogger((Class<?>) CodePageString.class);
    private static int MAX_RECORD_LENGTH = 100000;
    private byte[] _value;

    public static void setMaxRecordLength(int i) {
        MAX_RECORD_LENGTH = i;
    }

    public static int getMaxRecordLength() {
        return MAX_RECORD_LENGTH;
    }

    public void read(LittleEndianByteArrayInputStream littleEndianByteArrayInputStream) {
        int readIndex = littleEndianByteArrayInputStream.getReadIndex();
        int readInt = littleEndianByteArrayInputStream.readInt();
        byte[] safelyAllocate = IOUtils.safelyAllocate((long) readInt, MAX_RECORD_LENGTH);
        this._value = safelyAllocate;
        if (readInt != 0) {
            littleEndianByteArrayInputStream.readFully(safelyAllocate);
            if (this._value[readInt - 1] != 0) {
                LOG.atWarn().log("CodePageString started at offset #{} is not NULL-terminated", (Object) Unbox.box(readIndex));
            }
            TypedPropertyValue.skipPadding(littleEndianByteArrayInputStream);
        }
    }

    public String getJavaValue(int i) throws UnsupportedEncodingException {
        if (i == -1) {
            i = 1252;
        }
        String stringFromCodePage = CodePageUtil.getStringFromCodePage(this._value, i);
        int indexOf = stringFromCodePage.indexOf(0);
        if (indexOf == -1) {
            LOG.atWarn().log("String terminator (\\0) for CodePageString property value not found. Continue without trimming and hope for the best.");
            return stringFromCodePage;
        }
        if (indexOf != stringFromCodePage.length() - 1) {
            LOG.atDebug().log("String terminator (\\0) for CodePageString property value occurred before the end of string. Trimming and hope for the best.");
        }
        return stringFromCodePage.substring(0, indexOf);
    }

    public int getSize() {
        return this._value.length + 4;
    }

    public void setJavaValue(String str, int i) throws UnsupportedEncodingException {
        if (i == -1) {
            i = 1252;
        }
        this._value = CodePageUtil.getBytesInCodePage(str + "\u0000", i);
    }

    public int write(OutputStream outputStream) throws IOException {
        LittleEndian.putUInt((long) this._value.length, outputStream);
        outputStream.write(this._value);
        return this._value.length + 4;
    }
}
