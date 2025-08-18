package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.util.StackLocatorUtil;

public class ExtendedLoggerWrapper extends AbstractLogger {
    private static final long serialVersionUID = 1;
    protected final ExtendedLogger logger;

    public ExtendedLoggerWrapper(ExtendedLogger extendedLogger, String str, MessageFactory messageFactory) {
        super(str, messageFactory);
        this.logger = extendedLogger;
    }

    public Level getLevel() {
        return this.logger.getLevel();
    }

    public boolean isEnabled(Level level, Marker marker, Message message, Throwable th) {
        return this.logger.isEnabled(level, marker, message, th);
    }

    public boolean isEnabled(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        return this.logger.isEnabled(level, marker, charSequence, th);
    }

    public boolean isEnabled(Level level, Marker marker, Object obj, Throwable th) {
        return this.logger.isEnabled(level, marker, obj, th);
    }

    public boolean isEnabled(Level level, Marker marker, String str) {
        return this.logger.isEnabled(level, marker, str);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object... objArr) {
        return this.logger.isEnabled(level, marker, str, objArr);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj) {
        return this.logger.isEnabled(level, marker, str, obj);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2) {
        return this.logger.isEnabled(level, marker, str, obj, obj2);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return this.logger.isEnabled(level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Throwable th) {
        return this.logger.isEnabled(level, marker, str, th);
    }

    public void logMessage(String str, Level level, Marker marker, Message message, Throwable th) {
        if (!(this.logger instanceof LocationAwareLogger) || !requiresLocation()) {
            this.logger.logMessage(str, level, marker, message, th);
            return;
        }
        ((LocationAwareLogger) this.logger).logMessage(level, marker, str, StackLocatorUtil.calcLocation(str), message, th);
    }
}
