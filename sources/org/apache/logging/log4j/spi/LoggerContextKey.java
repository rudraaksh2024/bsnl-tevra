package org.apache.logging.log4j.spi;

import org.apache.logging.log4j.message.MessageFactory;

@Deprecated
public class LoggerContextKey {
    public static String create(String str) {
        return create(str, AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS);
    }

    public static String create(String str, MessageFactory messageFactory) {
        return create(str, (Class<? extends MessageFactory>) messageFactory != null ? messageFactory.getClass() : AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS);
    }

    public static String create(String str, Class<? extends MessageFactory> cls) {
        if (cls == null) {
            cls = AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS;
        }
        return str + "." + cls.getName();
    }
}
