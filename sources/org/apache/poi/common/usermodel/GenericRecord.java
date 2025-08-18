package org.apache.poi.common.usermodel;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public interface GenericRecord {
    List<? extends GenericRecord> getGenericChildren() {
        return null;
    }

    Map<String, Supplier<?>> getGenericProperties();

    Enum<?> getGenericRecordType() {
        return null;
    }
}
