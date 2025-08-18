package org.apache.logging.log4j.simple;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLogger;
import org.apache.logging.log4j.spi.LoggerContext;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.apache.logging.log4j.util.PropertiesUtil;

public class SimpleLoggerContext implements LoggerContext {
    protected static final String DEFAULT_DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss:SSS zzz";
    static final SimpleLoggerContext INSTANCE = new SimpleLoggerContext();
    private static final String SYSTEM_ERR = "system.err";
    private static final String SYSTEM_OUT = "system.out";
    protected static final String SYSTEM_PREFIX = "org.apache.logging.log4j.simplelog.";
    private final String dateTimeFormat;
    private final Level defaultLevel;
    private final LoggerRegistry<ExtendedLogger> loggerRegistry = new LoggerRegistry<>();
    private final PropertiesUtil props;
    private final boolean showContextMap;
    private final boolean showDateTime;
    private final boolean showLogName;
    private final boolean showShortName;
    private final PrintStream stream;

    public Object getExternalContext() {
        return null;
    }

    public boolean hasLogger(String str) {
        return false;
    }

    public boolean hasLogger(String str, Class<? extends MessageFactory> cls) {
        return false;
    }

    public boolean hasLogger(String str, MessageFactory messageFactory) {
        return false;
    }

    public SimpleLoggerContext() {
        PrintStream printStream;
        PropertiesUtil propertiesUtil = new PropertiesUtil("log4j2.simplelog.properties");
        this.props = propertiesUtil;
        this.showContextMap = propertiesUtil.getBooleanProperty("org.apache.logging.log4j.simplelog.showContextMap", false);
        this.showLogName = propertiesUtil.getBooleanProperty("org.apache.logging.log4j.simplelog.showlogname", false);
        this.showShortName = propertiesUtil.getBooleanProperty("org.apache.logging.log4j.simplelog.showShortLogname", true);
        boolean booleanProperty = propertiesUtil.getBooleanProperty("org.apache.logging.log4j.simplelog.showdatetime", false);
        this.showDateTime = booleanProperty;
        this.defaultLevel = Level.toLevel(propertiesUtil.getStringProperty("org.apache.logging.log4j.simplelog.level"), Level.ERROR);
        this.dateTimeFormat = booleanProperty ? propertiesUtil.getStringProperty("org.apache.logging.log4j.simplelog.dateTimeFormat", DEFAULT_DATE_TIME_FORMAT) : null;
        String stringProperty = propertiesUtil.getStringProperty("org.apache.logging.log4j.simplelog.logFile", SYSTEM_ERR);
        if (SYSTEM_ERR.equalsIgnoreCase(stringProperty)) {
            printStream = System.err;
        } else if (SYSTEM_OUT.equalsIgnoreCase(stringProperty)) {
            printStream = System.out;
        } else {
            try {
                printStream = new PrintStream(new FileOutputStream(stringProperty));
            } catch (FileNotFoundException unused) {
                printStream = System.err;
            }
        }
        this.stream = printStream;
    }

    public ExtendedLogger getLogger(String str) {
        return getLogger(str, (MessageFactory) null);
    }

    public ExtendedLogger getLogger(String str, MessageFactory messageFactory) {
        ExtendedLogger logger = this.loggerRegistry.getLogger(str, messageFactory);
        if (logger != null) {
            AbstractLogger.checkMessageFactory(logger, messageFactory);
            return logger;
        }
        this.loggerRegistry.putIfAbsent(str, messageFactory, new SimpleLogger(str, this.defaultLevel, this.showLogName, this.showShortName, this.showDateTime, this.showContextMap, this.dateTimeFormat, messageFactory, this.props, this.stream));
        return this.loggerRegistry.getLogger(str, messageFactory);
    }

    public LoggerRegistry<ExtendedLogger> getLoggerRegistry() {
        return this.loggerRegistry;
    }
}
