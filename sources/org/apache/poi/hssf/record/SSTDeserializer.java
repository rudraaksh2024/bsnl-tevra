package org.apache.poi.hssf.record;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.util.IntMapper;

class SSTDeserializer {
    private static final Logger LOG = LogManager.getLogger((Class<?>) SSTDeserializer.class);
    private IntMapper<UnicodeString> strings;

    public SSTDeserializer(IntMapper<UnicodeString> intMapper) {
        this.strings = intMapper;
    }

    public void manufactureStrings(int i, RecordInputStream recordInputStream) {
        UnicodeString unicodeString;
        for (int i2 = 0; i2 < i; i2++) {
            if (recordInputStream.available() != 0 || recordInputStream.hasNextRecord()) {
                unicodeString = new UnicodeString(recordInputStream);
            } else {
                LOG.atError().log("Ran out of data before creating all the strings! String at index {}", (Object) Unbox.box(i2));
                unicodeString = new UnicodeString("");
            }
            addToStringTable(this.strings, unicodeString);
        }
    }

    public static void addToStringTable(IntMapper<UnicodeString> intMapper, UnicodeString unicodeString) {
        intMapper.add(unicodeString);
    }
}
