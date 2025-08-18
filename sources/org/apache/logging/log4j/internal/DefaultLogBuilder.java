package org.apache.logging.log4j.internal;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Supplier;

public class DefaultLogBuilder implements LogBuilder {
    private static Message EMPTY_MESSAGE = new SimpleMessage("");
    private static final String FQCN = DefaultLogBuilder.class.getName();
    private static final Logger LOGGER = StatusLogger.getLogger();
    private volatile boolean inUse;
    private Level level;
    private StackTraceElement location;
    private final Logger logger;
    private Marker marker;
    private long threadId;
    private Throwable throwable;

    public DefaultLogBuilder(Logger logger2, Level level2) {
        this.logger = logger2;
        this.level = level2;
        this.threadId = Thread.currentThread().getId();
        this.inUse = true;
    }

    public DefaultLogBuilder(Logger logger2) {
        this.logger = logger2;
        this.inUse = false;
        this.threadId = Thread.currentThread().getId();
    }

    public LogBuilder reset(Level level2) {
        this.inUse = true;
        this.level = level2;
        this.marker = null;
        this.throwable = null;
        this.location = null;
        return this;
    }

    public LogBuilder withMarker(Marker marker2) {
        this.marker = marker2;
        return this;
    }

    public LogBuilder withThrowable(Throwable th) {
        this.throwable = th;
        return this;
    }

    public LogBuilder withLocation() {
        this.location = StackLocatorUtil.getStackTraceElement(2);
        return this;
    }

    public LogBuilder withLocation(StackTraceElement stackTraceElement) {
        this.location = stackTraceElement;
        return this;
    }

    public boolean isInUse() {
        return this.inUse;
    }

    public void log(Message message) {
        if (isValid()) {
            logMessage(message);
        }
    }

    public void log(CharSequence charSequence) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage((Object) charSequence));
        }
    }

    public void log(String str) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str));
        }
    }

    public void log(String str, Object... objArr) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, objArr));
        }
    }

    public void log(String str, Supplier<?>... supplierArr) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, LambdaUtil.getAll(supplierArr)));
        }
    }

    public void log(Supplier<Message> supplier) {
        if (isValid()) {
            logMessage(supplier.get());
        }
    }

    public void log(Object obj) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(obj));
        }
    }

    public void log(String str, Object obj) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj));
        }
    }

    public void log(String str, Object obj, Object obj2) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9));
        }
    }

    public void log(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        if (isValid()) {
            logMessage(this.logger.getMessageFactory().newMessage(str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10));
        }
    }

    public void log() {
        if (isValid()) {
            logMessage(EMPTY_MESSAGE);
        }
    }

    private void logMessage(Message message) {
        try {
            this.logger.logMessage(this.level, this.marker, FQCN, this.location, message, this.throwable);
        } finally {
            this.inUse = false;
        }
    }

    private boolean isValid() {
        if (!this.inUse) {
            LOGGER.warn("Attempt to reuse LogBuilder was ignored. {}", (Object) StackLocatorUtil.getCallerClass(2));
            return false;
        } else if (this.threadId == Thread.currentThread().getId()) {
            return true;
        } else {
            LOGGER.warn("LogBuilder can only be used on the owning thread. {}", (Object) StackLocatorUtil.getCallerClass(2));
            return false;
        }
    }
}
