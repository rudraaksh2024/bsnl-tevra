package org.apache.logging.log4j;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.Supplier;

public interface LogBuilder {
    public static final LogBuilder NOOP = new LogBuilder() {
    };

    void log() {
    }

    void log(CharSequence charSequence) {
    }

    void log(Object obj) {
    }

    void log(String str) {
    }

    void log(String str, Object obj) {
    }

    void log(String str, Object obj, Object obj2) {
    }

    void log(String str, Object obj, Object obj2, Object obj3) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
    }

    void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
    }

    void log(String str, Object... objArr) {
    }

    void log(String str, Supplier<?>... supplierArr) {
    }

    void log(Message message) {
    }

    void log(Supplier<Message> supplier) {
    }

    LogBuilder withLocation() {
        return this;
    }

    LogBuilder withLocation(StackTraceElement stackTraceElement) {
        return this;
    }

    LogBuilder withMarker(Marker marker) {
        return this;
    }

    LogBuilder withThrowable(Throwable th) {
        return this;
    }
}
