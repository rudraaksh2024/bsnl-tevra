package org.apache.logging.log4j.spi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LoggingException;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.internal.DefaultLogBuilder;
import org.apache.logging.log4j.message.DefaultFlowMessageFactory;
import org.apache.logging.log4j.message.EntryMessage;
import org.apache.logging.log4j.message.FlowMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LambdaUtil;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.MessageSupplier;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StackLocatorUtil;
import org.apache.logging.log4j.util.Strings;
import org.apache.logging.log4j.util.Supplier;

public abstract class AbstractLogger implements ExtendedLogger, LocationAwareLogger, Serializable {
    private static final String CATCHING = "Catching";
    public static final Marker CATCHING_MARKER;
    public static final Class<? extends FlowMessageFactory> DEFAULT_FLOW_MESSAGE_FACTORY_CLASS = createFlowClassForProperty("log4j2.flowMessageFactory", DefaultFlowMessageFactory.class);
    public static final Class<? extends MessageFactory> DEFAULT_MESSAGE_FACTORY_CLASS = createClassForProperty("log4j2.messageFactory", ReusableMessageFactory.class, ParameterizedMessageFactory.class);
    public static final Marker ENTRY_MARKER;
    public static final Marker EXCEPTION_MARKER;
    public static final Marker EXIT_MARKER;
    public static final Marker FLOW_MARKER;
    private static final String FQCN = AbstractLogger.class.getName();
    private static final String THROWING = "Throwing";
    public static final Marker THROWING_MARKER;
    private static final ThreadLocal<int[]> recursionDepthHolder = new ThreadLocal<>();
    private static final long serialVersionUID = 2;
    private final FlowMessageFactory flowMessageFactory;
    protected final transient ThreadLocal<DefaultLogBuilder> logBuilder;
    private final MessageFactory2 messageFactory;
    protected final String name;

    /* access modifiers changed from: protected */
    public boolean requiresLocation() {
        return false;
    }

    static {
        Marker marker = MarkerManager.getMarker("FLOW");
        FLOW_MARKER = marker;
        ENTRY_MARKER = MarkerManager.getMarker("ENTER").setParents(marker);
        EXIT_MARKER = MarkerManager.getMarker("EXIT").setParents(marker);
        Marker marker2 = MarkerManager.getMarker("EXCEPTION");
        EXCEPTION_MARKER = marker2;
        THROWING_MARKER = MarkerManager.getMarker("THROWING").setParents(marker2);
        CATCHING_MARKER = MarkerManager.getMarker("CATCHING").setParents(marker2);
    }

    public AbstractLogger() {
        this.name = getClass().getName();
        this.messageFactory = createDefaultMessageFactory();
        this.flowMessageFactory = createDefaultFlowMessageFactory();
        this.logBuilder = new LocalLogBuilder(this);
    }

    public AbstractLogger(String str) {
        this(str, createDefaultMessageFactory());
    }

    public AbstractLogger(String str, MessageFactory messageFactory2) {
        this.name = str;
        this.messageFactory = messageFactory2 == null ? createDefaultMessageFactory() : narrow(messageFactory2);
        this.flowMessageFactory = createDefaultFlowMessageFactory();
        this.logBuilder = new LocalLogBuilder(this);
    }

    public static void checkMessageFactory(ExtendedLogger extendedLogger, MessageFactory messageFactory2) {
        String name2 = extendedLogger.getName();
        MessageFactory messageFactory3 = extendedLogger.getMessageFactory();
        if (messageFactory2 != null && !messageFactory3.equals(messageFactory2)) {
            StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with the message factory {}, which may create log events with unexpected formatting.", (Object) name2, (Object) messageFactory3, (Object) messageFactory2);
        } else if (messageFactory2 == null) {
            Class<?> cls = messageFactory3.getClass();
            Class<? extends MessageFactory> cls2 = DEFAULT_MESSAGE_FACTORY_CLASS;
            if (!cls.equals(cls2)) {
                StatusLogger.getLogger().warn("The Logger {} was created with the message factory {} and is now requested with a null message factory (defaults to {}), which may create log events with unexpected formatting.", (Object) name2, (Object) messageFactory3, (Object) cls2.getName());
            }
        }
    }

    public void catching(Level level, Throwable th) {
        catching(FQCN, level, th);
    }

    /* access modifiers changed from: protected */
    public void catching(String str, Level level, Throwable th) {
        Marker marker = CATCHING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessageSafely(str, level, marker, catchingMsg(th), th);
        }
    }

    public void catching(Throwable th) {
        Level level = Level.ERROR;
        Marker marker = CATCHING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessageSafely(FQCN, Level.ERROR, marker, catchingMsg(th), th);
        }
    }

    /* access modifiers changed from: protected */
    public Message catchingMsg(Throwable th) {
        return this.messageFactory.newMessage(CATCHING);
    }

    private static Class<? extends MessageFactory> createClassForProperty(String str, Class<ReusableMessageFactory> cls, Class<ParameterizedMessageFactory> cls2) {
        String str2;
        try {
            if (Constants.ENABLE_THREADLOCALS) {
                str2 = cls.getName();
            } else {
                str2 = cls2.getName();
            }
            return LoaderUtil.loadClass(PropertiesUtil.getProperties().getStringProperty(str, str2)).asSubclass(MessageFactory.class);
        } catch (Throwable unused) {
            return cls2;
        }
    }

    private static Class<? extends FlowMessageFactory> createFlowClassForProperty(String str, Class<DefaultFlowMessageFactory> cls) {
        try {
            return LoaderUtil.loadClass(PropertiesUtil.getProperties().getStringProperty(str, cls.getName())).asSubclass(FlowMessageFactory.class);
        } catch (Throwable unused) {
            return cls;
        }
    }

    private static MessageFactory2 createDefaultMessageFactory() {
        try {
            return narrow((MessageFactory) DEFAULT_MESSAGE_FACTORY_CLASS.newInstance());
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static MessageFactory2 narrow(MessageFactory messageFactory2) {
        if (messageFactory2 instanceof MessageFactory2) {
            return (MessageFactory2) messageFactory2;
        }
        return new MessageFactory2Adapter(messageFactory2);
    }

    private static FlowMessageFactory createDefaultFlowMessageFactory() {
        try {
            return (FlowMessageFactory) DEFAULT_FLOW_MESSAGE_FACTORY_CLASS.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    public void debug(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.DEBUG, marker, charSequence, (Throwable) null);
    }

    public void debug(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, charSequence, th);
    }

    public void debug(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, message != null ? message.getThrowable() : null);
    }

    public void debug(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, message, th);
    }

    public void debug(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, (Throwable) null);
    }

    public void debug(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, obj, th);
    }

    public void debug(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, marker, str, (Throwable) null);
    }

    public void debug(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, objArr);
    }

    public void debug(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, th);
    }

    public void debug(Message message) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void debug(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, message, th);
    }

    public void debug(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, charSequence, (Throwable) null);
    }

    public void debug(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, charSequence, th);
    }

    public void debug(Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, (Throwable) null);
    }

    public void debug(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, obj, th);
    }

    public void debug(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, (Throwable) null);
    }

    public void debug(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, objArr);
    }

    public void debug(String str, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, th);
    }

    public void debug(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, supplier, (Throwable) null);
    }

    public void debug(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, supplier, th);
    }

    public void debug(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, marker, supplier, (Throwable) null);
    }

    public void debug(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, supplierArr);
    }

    public void debug(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, supplier, th);
    }

    public void debug(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, supplierArr);
    }

    public void debug(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, (Throwable) null);
    }

    public void debug(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, marker, messageSupplier, th);
    }

    public void debug(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void debug(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, messageSupplier, th);
    }

    public void debug(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void debug(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.DEBUG, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void debug(String str, Object obj) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj);
    }

    public void debug(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void debug(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.DEBUG, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    /* access modifiers changed from: protected */
    public EntryMessage enter(String str, String str2, Supplier<?>... supplierArr) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level2 = Level.TRACE;
        EntryMessage entryMsg = entryMsg(str2, supplierArr);
        logMessageSafely(str, level2, marker, entryMsg, (Throwable) null);
        return entryMsg;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public EntryMessage enter(String str, String str2, MessageSupplier... messageSupplierArr) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level2 = Level.TRACE;
        EntryMessage entryMsg = entryMsg(str2, messageSupplierArr);
        logMessageSafely(str, level2, marker, entryMsg, (Throwable) null);
        return entryMsg;
    }

    /* access modifiers changed from: protected */
    public EntryMessage enter(String str, String str2, Object... objArr) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level2 = Level.TRACE;
        EntryMessage entryMsg = entryMsg(str2, objArr);
        logMessageSafely(str, level2, marker, entryMsg, (Throwable) null);
        return entryMsg;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public EntryMessage enter(String str, MessageSupplier messageSupplier) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level2 = Level.TRACE;
        EntryMessage newEntryMessage = this.flowMessageFactory.newEntryMessage(messageSupplier.get());
        logMessageSafely(str, level2, marker, newEntryMessage, (Throwable) null);
        return newEntryMessage;
    }

    /* access modifiers changed from: protected */
    public EntryMessage enter(String str, Message message) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return null;
        }
        Level level2 = Level.TRACE;
        EntryMessage newEntryMessage = this.flowMessageFactory.newEntryMessage(message);
        logMessageSafely(str, level2, marker, newEntryMessage, (Throwable) null);
        return newEntryMessage;
    }

    @Deprecated
    public void entry() {
        Object[] objArr = null;
        entry(FQCN, (Object[]) null);
    }

    public void entry(Object... objArr) {
        entry(FQCN, objArr);
    }

    /* access modifiers changed from: protected */
    public void entry(String str, Object... objArr) {
        Level level = Level.TRACE;
        Marker marker = ENTRY_MARKER;
        if (!isEnabled(level, marker, (Object) null, (Throwable) null)) {
            return;
        }
        if (objArr == null) {
            Supplier[] supplierArr = null;
            logMessageSafely(str, Level.TRACE, marker, entryMsg((String) null, (Supplier<?>[]) null), (Throwable) null);
            return;
        }
        logMessageSafely(str, Level.TRACE, marker, entryMsg((String) null, objArr), (Throwable) null);
    }

    /* access modifiers changed from: protected */
    public EntryMessage entryMsg(String str, Object... objArr) {
        int length = objArr == null ? 0 : objArr.length;
        if (length == 0) {
            if (Strings.isEmpty(str)) {
                return this.flowMessageFactory.newEntryMessage((Message) null);
            }
            return this.flowMessageFactory.newEntryMessage(new SimpleMessage(str));
        } else if (str != null) {
            return this.flowMessageFactory.newEntryMessage(new ParameterizedMessage(str, objArr));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("params(");
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                Message message = objArr[i];
                sb.append(message instanceof Message ? message.getFormattedMessage() : String.valueOf(message));
            }
            sb.append(')');
            return this.flowMessageFactory.newEntryMessage(new SimpleMessage((CharSequence) sb));
        }
    }

    /* access modifiers changed from: protected */
    public EntryMessage entryMsg(String str, MessageSupplier... messageSupplierArr) {
        String str2;
        int length = messageSupplierArr == null ? 0 : messageSupplierArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            Message message = messageSupplierArr[i].get();
            objArr[i] = message;
            if (message != null) {
                Message message2 = message;
                str2 = message.getFormattedMessage();
            } else {
                str2 = null;
            }
            objArr[i] = str2;
        }
        return entryMsg(str, objArr);
    }

    /* access modifiers changed from: protected */
    public EntryMessage entryMsg(String str, Supplier<?>... supplierArr) {
        int length = supplierArr == null ? 0 : supplierArr.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            Object obj = supplierArr[i].get();
            objArr[i] = obj;
            if (obj instanceof Message) {
                objArr[i] = ((Message) obj).getFormattedMessage();
            }
        }
        return entryMsg(str, objArr);
    }

    public void error(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, message != null ? message.getThrowable() : null);
    }

    public void error(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, message, th);
    }

    public void error(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.ERROR, marker, charSequence, (Throwable) null);
    }

    public void error(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, charSequence, th);
    }

    public void error(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, (Throwable) null);
    }

    public void error(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, obj, th);
    }

    public void error(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, marker, str, (Throwable) null);
    }

    public void error(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, objArr);
    }

    public void error(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, th);
    }

    public void error(Message message) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void error(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, message, th);
    }

    public void error(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, charSequence, (Throwable) null);
    }

    public void error(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, charSequence, th);
    }

    public void error(Object obj) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, (Throwable) null);
    }

    public void error(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, obj, th);
    }

    public void error(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, (Throwable) null);
    }

    public void error(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, objArr);
    }

    public void error(String str, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, th);
    }

    public void error(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, supplier, (Throwable) null);
    }

    public void error(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, supplier, th);
    }

    public void error(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, marker, supplier, (Throwable) null);
    }

    public void error(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, supplierArr);
    }

    public void error(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, supplier, th);
    }

    public void error(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, supplierArr);
    }

    public void error(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, (Throwable) null);
    }

    public void error(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, marker, messageSupplier, th);
    }

    public void error(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void error(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, messageSupplier, th);
    }

    public void error(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj);
    }

    public void error(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void error(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.ERROR, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void error(String str, Object obj) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj);
    }

    public void error(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2);
    }

    public void error(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void error(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.ERROR, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    @Deprecated
    public void exit() {
        exit(FQCN, (Object) null);
    }

    @Deprecated
    public <R> R exit(R r) {
        return exit(FQCN, r);
    }

    /* access modifiers changed from: protected */
    public <R> R exit(String str, R r) {
        Level level = Level.TRACE;
        Marker marker = EXIT_MARKER;
        CharSequence charSequence = null;
        if (isEnabled(level, marker, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(str, Level.TRACE, marker, exitMsg((String) null, r), (Throwable) null);
        }
        return r;
    }

    /* access modifiers changed from: protected */
    public <R> R exit(String str, String str2, R r) {
        Level level = Level.TRACE;
        Marker marker = EXIT_MARKER;
        CharSequence charSequence = null;
        if (isEnabled(level, marker, (CharSequence) null, (Throwable) null)) {
            logMessageSafely(str, Level.TRACE, marker, exitMsg(str2, r), (Throwable) null);
        }
        return r;
    }

    /* access modifiers changed from: protected */
    public Message exitMsg(String str, Object obj) {
        if (obj == null) {
            if (str == null) {
                return this.messageFactory.newMessage("Exit");
            }
            return this.messageFactory.newMessage("Exit: " + str);
        } else if (str == null) {
            return this.messageFactory.newMessage("Exit with(" + obj + ')');
        } else {
            return this.messageFactory.newMessage("Exit: " + str, obj);
        }
    }

    public void fatal(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, message != null ? message.getThrowable() : null);
    }

    public void fatal(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, message, th);
    }

    public void fatal(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.FATAL, marker, charSequence, (Throwable) null);
    }

    public void fatal(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, charSequence, th);
    }

    public void fatal(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, (Throwable) null);
    }

    public void fatal(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, obj, th);
    }

    public void fatal(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, marker, str, (Throwable) null);
    }

    public void fatal(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, objArr);
    }

    public void fatal(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, th);
    }

    public void fatal(Message message) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void fatal(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, message, th);
    }

    public void fatal(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, charSequence, (Throwable) null);
    }

    public void fatal(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, charSequence, th);
    }

    public void fatal(Object obj) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, (Throwable) null);
    }

    public void fatal(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, obj, th);
    }

    public void fatal(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, (Throwable) null);
    }

    public void fatal(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, objArr);
    }

    public void fatal(String str, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, th);
    }

    public void fatal(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, supplier, (Throwable) null);
    }

    public void fatal(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, supplier, th);
    }

    public void fatal(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, marker, supplier, (Throwable) null);
    }

    public void fatal(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, supplierArr);
    }

    public void fatal(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, supplier, th);
    }

    public void fatal(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, supplierArr);
    }

    public void fatal(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, (Throwable) null);
    }

    public void fatal(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, marker, messageSupplier, th);
    }

    public void fatal(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void fatal(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, messageSupplier, th);
    }

    public void fatal(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void fatal(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.FATAL, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void fatal(String str, Object obj) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj);
    }

    public void fatal(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void fatal(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.FATAL, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public <MF extends MessageFactory> MF getMessageFactory() {
        return this.messageFactory;
    }

    public String getName() {
        return this.name;
    }

    public void info(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.INFO, marker, message, message != null ? message.getThrowable() : null);
    }

    public void info(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, message, th);
    }

    public void info(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.INFO, marker, charSequence, (Throwable) null);
    }

    public void info(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, charSequence, th);
    }

    public void info(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, (Throwable) null);
    }

    public void info(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, obj, th);
    }

    public void info(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, marker, str, (Throwable) null);
    }

    public void info(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, marker, str, objArr);
    }

    public void info(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, str, th);
    }

    public void info(Message message) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void info(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, message, th);
    }

    public void info(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, charSequence, (Throwable) null);
    }

    public void info(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, charSequence, th);
    }

    public void info(Object obj) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, (Throwable) null);
    }

    public void info(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, obj, th);
    }

    public void info(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, (Throwable) null);
    }

    public void info(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, objArr);
    }

    public void info(String str, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, th);
    }

    public void info(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, (Marker) null, supplier, (Throwable) null);
    }

    public void info(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, supplier, th);
    }

    public void info(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, marker, supplier, (Throwable) null);
    }

    public void info(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.INFO, marker, str, supplierArr);
    }

    public void info(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, supplier, th);
    }

    public void info(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, supplierArr);
    }

    public void info(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, (Throwable) null);
    }

    public void info(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, marker, messageSupplier, th);
    }

    public void info(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void info(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, messageSupplier, th);
    }

    public void info(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj);
    }

    public void info(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void info(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.INFO, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void info(String str, Object obj) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj);
    }

    public void info(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2);
    }

    public void info(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void info(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.INFO, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public boolean isDebugEnabled() {
        return isEnabled(Level.DEBUG, (Marker) null, (String) null);
    }

    public boolean isDebugEnabled(Marker marker) {
        return isEnabled(Level.DEBUG, marker, (Object) null, (Throwable) null);
    }

    public boolean isEnabled(Level level) {
        return isEnabled(level, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isEnabled(Level level, Marker marker) {
        return isEnabled(level, marker, (Object) null, (Throwable) null);
    }

    public boolean isErrorEnabled() {
        return isEnabled(Level.ERROR, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isErrorEnabled(Marker marker) {
        return isEnabled(Level.ERROR, marker, (Object) null, (Throwable) null);
    }

    public boolean isFatalEnabled() {
        return isEnabled(Level.FATAL, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isFatalEnabled(Marker marker) {
        return isEnabled(Level.FATAL, marker, (Object) null, (Throwable) null);
    }

    public boolean isInfoEnabled() {
        return isEnabled(Level.INFO, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isInfoEnabled(Marker marker) {
        return isEnabled(Level.INFO, marker, (Object) null, (Throwable) null);
    }

    public boolean isTraceEnabled() {
        return isEnabled(Level.TRACE, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isTraceEnabled(Marker marker) {
        return isEnabled(Level.TRACE, marker, (Object) null, (Throwable) null);
    }

    public boolean isWarnEnabled() {
        return isEnabled(Level.WARN, (Marker) null, (Object) null, (Throwable) null);
    }

    public boolean isWarnEnabled(Marker marker) {
        return isEnabled(Level.WARN, marker, (Object) null, (Throwable) null);
    }

    public void log(Level level, Marker marker, Message message) {
        logIfEnabled(FQCN, level, marker, message, message != null ? message.getThrowable() : null);
    }

    public void log(Level level, Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, level, marker, message, th);
    }

    public void log(Level level, Marker marker, CharSequence charSequence) {
        Throwable th = null;
        logIfEnabled(FQCN, level, marker, charSequence, (Throwable) null);
    }

    public void log(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        if (isEnabled(level, marker, charSequence, th)) {
            logMessage(FQCN, level, marker, charSequence, th);
        }
    }

    public void log(Level level, Marker marker, Object obj) {
        Throwable th = null;
        logIfEnabled(FQCN, level, marker, obj, (Throwable) null);
    }

    public void log(Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(FQCN, level, marker, obj, th);
        }
    }

    public void log(Level level, Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, level, marker, str, (Throwable) null);
    }

    public void log(Level level, Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, level, marker, str, objArr);
    }

    public void log(Level level, Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, level, marker, str, th);
    }

    public void log(Level level, Message message) {
        logIfEnabled(FQCN, level, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void log(Level level, Message message, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, message, th);
    }

    public void log(Level level, CharSequence charSequence) {
        logIfEnabled(FQCN, level, (Marker) null, charSequence, (Throwable) null);
    }

    public void log(Level level, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, charSequence, th);
    }

    public void log(Level level, Object obj) {
        logIfEnabled(FQCN, level, (Marker) null, obj, (Throwable) null);
    }

    public void log(Level level, Object obj, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, obj, th);
    }

    public void log(Level level, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, level, (Marker) null, str, (Throwable) null);
    }

    public void log(Level level, String str, Object... objArr) {
        logIfEnabled(FQCN, level, (Marker) null, str, objArr);
    }

    public void log(Level level, String str, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, str, th);
    }

    public void log(Level level, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, level, (Marker) null, supplier, (Throwable) null);
    }

    public void log(Level level, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, supplier, th);
    }

    public void log(Level level, Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, level, marker, supplier, (Throwable) null);
    }

    public void log(Level level, Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, level, marker, str, supplierArr);
    }

    public void log(Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, level, marker, supplier, th);
    }

    public void log(Level level, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, level, (Marker) null, str, supplierArr);
    }

    public void log(Level level, Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, level, marker, messageSupplier, (Throwable) null);
    }

    public void log(Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, level, marker, messageSupplier, th);
    }

    public void log(Level level, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void log(Level level, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, level, (Marker) null, messageSupplier, th);
    }

    public void log(Level level, Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, level, marker, str, obj);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void log(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, level, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void log(Level level, String str, Object obj) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj);
    }

    public void log(Level level, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void log(Level level, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, level, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void logIfEnabled(String str, Level level, Marker marker, Message message, Throwable th) {
        if (isEnabled(level, marker, message, th)) {
            logMessageSafely(str, level, marker, message, th);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        if (isEnabled(level, marker, (Object) messageSupplier, th)) {
            logMessage(str, level, marker, messageSupplier, th);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, Object obj, Throwable th) {
        if (isEnabled(level, marker, obj, th)) {
            logMessage(str, level, marker, obj, th);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, CharSequence charSequence, Throwable th) {
        if (isEnabled(level, marker, charSequence, th)) {
            logMessage(str, level, marker, charSequence, th);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        if (isEnabled(level, marker, (Object) supplier, th)) {
            logMessage(str, level, marker, supplier, th);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2) {
        if (isEnabled(level, marker, str2)) {
            logMessage(str, level, marker, str2);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Supplier<?>... supplierArr) {
        if (isEnabled(level, marker, str2)) {
            logMessage(str, level, marker, str2, supplierArr);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object... objArr) {
        if (isEnabled(level, marker, str2, objArr)) {
            logMessage(str, level, marker, str2, objArr);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj) {
        if (isEnabled(level, marker, str2, obj)) {
            logMessage(str, level, marker, str2, obj);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2) {
        if (isEnabled(level, marker, str2, obj, obj2)) {
            logMessage(str, level, marker, str2, obj, obj2);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        if (isEnabled(level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10)) {
            logMessage(str, level, marker, str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
        }
    }

    public void logIfEnabled(String str, Level level, Marker marker, String str2, Throwable th) {
        if (isEnabled(level, marker, str2, th)) {
            logMessage(str, level, marker, str2, th);
        }
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, CharSequence charSequence, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(charSequence), th);
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, Object obj, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(obj), th);
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, MessageSupplier messageSupplier, Throwable th) {
        Message message = LambdaUtil.get(messageSupplier);
        if (th == null && message != null) {
            th = message.getThrowable();
        }
        logMessageSafely(str, level, marker, message, th);
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, Supplier<?> supplier, Throwable th) {
        Message message = LambdaUtil.getMessage(supplier, this.messageFactory);
        if (th == null && message != null) {
            th = message.getThrowable();
        }
        logMessageSafely(str, level, marker, message, th);
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Throwable th) {
        logMessageSafely(str, level, marker, this.messageFactory.newMessage(str2), th);
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2) {
        Message newMessage = this.messageFactory.newMessage(str2);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object... objArr) {
        Message newMessage = this.messageFactory.newMessage(str2, objArr);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj) {
        Message newMessage = this.messageFactory.newMessage(str2, obj);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        Message newMessage = this.messageFactory.newMessage(str2, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    /* access modifiers changed from: protected */
    public void logMessage(String str, Level level, Marker marker, String str2, Supplier<?>... supplierArr) {
        Message newMessage = this.messageFactory.newMessage(str2, LambdaUtil.getAll(supplierArr));
        logMessageSafely(str, level, marker, newMessage, newMessage.getThrowable());
    }

    public void logMessage(Level level, Marker marker, String str, StackTraceElement stackTraceElement, Message message, Throwable th) {
        try {
            incrementRecursionDepth();
            log(level, marker, str, stackTraceElement, message, th);
        } catch (Throwable th2) {
            decrementRecursionDepth();
            ReusableMessageFactory.release(message);
            throw th2;
        }
        decrementRecursionDepth();
        ReusableMessageFactory.release(message);
    }

    /* access modifiers changed from: protected */
    public void log(Level level, Marker marker, String str, StackTraceElement stackTraceElement, Message message, Throwable th) {
        logMessage(str, level, marker, message, th);
    }

    public void printf(Level level, Marker marker, String str, Object... objArr) {
        if (isEnabled(level, marker, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessageSafely(FQCN, level, marker, stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    public void printf(Level level, String str, Object... objArr) {
        if (isEnabled(level, (Marker) null, str, objArr)) {
            StringFormattedMessage stringFormattedMessage = new StringFormattedMessage(str, objArr);
            logMessageSafely(FQCN, level, (Marker) null, stringFormattedMessage, stringFormattedMessage.getThrowable());
        }
    }

    private void logMessageSafely(String str, Level level, Marker marker, Message message, Throwable th) {
        try {
            logMessageTrackRecursion(str, level, marker, message, th);
        } finally {
            ReusableMessageFactory.release(message);
        }
    }

    private void logMessageTrackRecursion(String str, Level level, Marker marker, Message message, Throwable th) {
        try {
            incrementRecursionDepth();
            tryLogMessage(str, getLocation(str), level, marker, message, th);
        } finally {
            decrementRecursionDepth();
        }
    }

    private static int[] getRecursionDepthHolder() {
        ThreadLocal<int[]> threadLocal = recursionDepthHolder;
        int[] iArr = threadLocal.get();
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[1];
        threadLocal.set(iArr2);
        return iArr2;
    }

    private static void incrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        recursionDepthHolder2[0] = recursionDepthHolder2[0] + 1;
    }

    private static void decrementRecursionDepth() {
        int[] recursionDepthHolder2 = getRecursionDepthHolder();
        int i = recursionDepthHolder2[0] - 1;
        recursionDepthHolder2[0] = i;
        if (i < 0) {
            throw new IllegalStateException("Recursion depth became negative: " + i);
        }
    }

    public static int getRecursionDepth() {
        return getRecursionDepthHolder()[0];
    }

    private void tryLogMessage(String str, StackTraceElement stackTraceElement, Level level, Marker marker, Message message, Throwable th) {
        try {
            log(level, marker, str, stackTraceElement, message, th);
        } catch (Throwable th2) {
            handleLogMessageException(th2, str, message);
        }
    }

    private StackTraceElement getLocation(String str) {
        if (requiresLocation()) {
            return StackLocatorUtil.calcLocation(str);
        }
        return null;
    }

    private void handleLogMessageException(Throwable th, String str, Message message) {
        if (!(th instanceof LoggingException)) {
            StatusLogger.getLogger().warn("{} caught {} logging {}: {}", (Object) str, (Object) th.getClass().getName(), (Object) message.getClass().getSimpleName(), (Object) message.getFormat(), (Object) th);
            return;
        }
        throw ((LoggingException) th);
    }

    public <T extends Throwable> T throwing(T t) {
        return throwing(FQCN, Level.ERROR, t);
    }

    public <T extends Throwable> T throwing(Level level, T t) {
        return throwing(FQCN, level, t);
    }

    /* access modifiers changed from: protected */
    public <T extends Throwable> T throwing(String str, Level level, T t) {
        Marker marker = THROWING_MARKER;
        if (isEnabled(level, marker, (Object) null, (Throwable) null)) {
            logMessageSafely(str, level, marker, throwingMsg(t), t);
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public Message throwingMsg(Throwable th) {
        return this.messageFactory.newMessage(THROWING);
    }

    public void trace(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, message != null ? message.getThrowable() : null);
    }

    public void trace(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, message, th);
    }

    public void trace(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.TRACE, marker, charSequence, (Throwable) null);
    }

    public void trace(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, charSequence, th);
    }

    public void trace(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, (Throwable) null);
    }

    public void trace(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, obj, th);
    }

    public void trace(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, marker, str, (Throwable) null);
    }

    public void trace(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, objArr);
    }

    public void trace(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, th);
    }

    public void trace(Message message) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void trace(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, message, th);
    }

    public void trace(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, charSequence, (Throwable) null);
    }

    public void trace(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, charSequence, th);
    }

    public void trace(Object obj) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, (Throwable) null);
    }

    public void trace(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, obj, th);
    }

    public void trace(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, (Throwable) null);
    }

    public void trace(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, objArr);
    }

    public void trace(String str, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, th);
    }

    public void trace(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, supplier, (Throwable) null);
    }

    public void trace(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, supplier, th);
    }

    public void trace(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, marker, supplier, (Throwable) null);
    }

    public void trace(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, supplierArr);
    }

    public void trace(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, supplier, th);
    }

    public void trace(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, supplierArr);
    }

    public void trace(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, (Throwable) null);
    }

    public void trace(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, marker, messageSupplier, th);
    }

    public void trace(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void trace(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, messageSupplier, th);
    }

    public void trace(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void trace(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.TRACE, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void trace(String str, Object obj) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj);
    }

    public void trace(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void trace(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.TRACE, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public EntryMessage traceEntry() {
        Object[] objArr = null;
        return enter(FQCN, (String) null, (Object[]) null);
    }

    public EntryMessage traceEntry(String str, Object... objArr) {
        return enter(FQCN, str, objArr);
    }

    public EntryMessage traceEntry(Supplier<?>... supplierArr) {
        return enter(FQCN, (String) null, supplierArr);
    }

    public EntryMessage traceEntry(String str, Supplier<?>... supplierArr) {
        return enter(FQCN, str, supplierArr);
    }

    public EntryMessage traceEntry(Message message) {
        return enter(FQCN, message);
    }

    public void traceExit() {
        exit(FQCN, (String) null, (Object) null);
    }

    public <R> R traceExit(R r) {
        return exit(FQCN, (String) null, r);
    }

    public <R> R traceExit(String str, R r) {
        return exit(FQCN, str, r);
    }

    public void traceExit(EntryMessage entryMessage) {
        if (entryMessage != null) {
            Level level = Level.TRACE;
            Marker marker = EXIT_MARKER;
            if (isEnabled(level, marker, (Message) entryMessage, (Throwable) null)) {
                logMessageSafely(FQCN, Level.TRACE, marker, this.flowMessageFactory.newExitMessage(entryMessage), (Throwable) null);
            }
        }
    }

    public <R> R traceExit(EntryMessage entryMessage, R r) {
        if (entryMessage != null) {
            Level level = Level.TRACE;
            Marker marker = EXIT_MARKER;
            if (isEnabled(level, marker, (Message) entryMessage, (Throwable) null)) {
                logMessageSafely(FQCN, Level.TRACE, marker, this.flowMessageFactory.newExitMessage((Object) r, entryMessage), (Throwable) null);
            }
        }
        return r;
    }

    public <R> R traceExit(Message message, R r) {
        if (message != null) {
            Level level = Level.TRACE;
            Marker marker = EXIT_MARKER;
            if (isEnabled(level, marker, message, (Throwable) null)) {
                logMessageSafely(FQCN, Level.TRACE, marker, this.flowMessageFactory.newExitMessage((Object) r, message), (Throwable) null);
            }
        }
        return r;
    }

    public void warn(Marker marker, Message message) {
        logIfEnabled(FQCN, Level.WARN, marker, message, message != null ? message.getThrowable() : null);
    }

    public void warn(Marker marker, Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, message, th);
    }

    public void warn(Marker marker, CharSequence charSequence) {
        logIfEnabled(FQCN, Level.WARN, marker, charSequence, (Throwable) null);
    }

    public void warn(Marker marker, CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, charSequence, th);
    }

    public void warn(Marker marker, Object obj) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, (Throwable) null);
    }

    public void warn(Marker marker, Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, obj, th);
    }

    public void warn(Marker marker, String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, marker, str, (Throwable) null);
    }

    public void warn(Marker marker, String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, marker, str, objArr);
    }

    public void warn(Marker marker, String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, str, th);
    }

    public void warn(Message message) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, message != null ? message.getThrowable() : null);
    }

    public void warn(Message message, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, message, th);
    }

    public void warn(CharSequence charSequence) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, charSequence, (Throwable) null);
    }

    public void warn(CharSequence charSequence, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, charSequence, th);
    }

    public void warn(Object obj) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, (Throwable) null);
    }

    public void warn(Object obj, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, obj, th);
    }

    public void warn(String str) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, (Throwable) null);
    }

    public void warn(String str, Object... objArr) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, objArr);
    }

    public void warn(String str, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, th);
    }

    public void warn(Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, (Marker) null, supplier, (Throwable) null);
    }

    public void warn(Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, supplier, th);
    }

    public void warn(Marker marker, Supplier<?> supplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, marker, supplier, (Throwable) null);
    }

    public void warn(Marker marker, String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.WARN, marker, str, supplierArr);
    }

    public void warn(Marker marker, Supplier<?> supplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, supplier, th);
    }

    public void warn(String str, Supplier<?>... supplierArr) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, supplierArr);
    }

    public void warn(Marker marker, MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, (Throwable) null);
    }

    public void warn(Marker marker, MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, marker, messageSupplier, th);
    }

    public void warn(MessageSupplier messageSupplier) {
        Throwable th = null;
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, (Throwable) null);
    }

    public void warn(MessageSupplier messageSupplier, Throwable th) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, messageSupplier, th);
    }

    public void warn(Marker marker, String str, Object obj) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void warn(Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.WARN, marker, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public void warn(String str, Object obj) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj);
    }

    public void warn(String str, Object obj, Object obj2) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public void warn(String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        logIfEnabled(FQCN, Level.WARN, (Marker) null, str, obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public LogBuilder atTrace() {
        return atLevel(Level.TRACE);
    }

    public LogBuilder atDebug() {
        return atLevel(Level.DEBUG);
    }

    public LogBuilder atInfo() {
        return atLevel(Level.INFO);
    }

    public LogBuilder atWarn() {
        return atLevel(Level.WARN);
    }

    public LogBuilder atError() {
        return atLevel(Level.ERROR);
    }

    public LogBuilder atFatal() {
        return atLevel(Level.FATAL);
    }

    public LogBuilder always() {
        DefaultLogBuilder defaultLogBuilder = this.logBuilder.get();
        if (defaultLogBuilder.isInUse()) {
            return new DefaultLogBuilder(this);
        }
        return defaultLogBuilder.reset(Level.OFF);
    }

    public LogBuilder atLevel(Level level) {
        if (isEnabled(level)) {
            return getLogBuilder(level).reset(level);
        }
        return LogBuilder.NOOP;
    }

    private DefaultLogBuilder getLogBuilder(Level level) {
        DefaultLogBuilder defaultLogBuilder = this.logBuilder.get();
        return (!Constants.ENABLE_THREADLOCALS || defaultLogBuilder.isInUse()) ? new DefaultLogBuilder(this, level) : defaultLogBuilder;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        try {
            Field declaredField = getClass().getDeclaredField("logBuilder");
            declaredField.setAccessible(true);
            declaredField.set(this, new LocalLogBuilder(this));
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            StatusLogger.getLogger().warn("Unable to initialize LogBuilder");
        }
    }

    private class LocalLogBuilder extends ThreadLocal<DefaultLogBuilder> {
        private AbstractLogger logger;

        LocalLogBuilder(AbstractLogger abstractLogger) {
            this.logger = abstractLogger;
        }

        /* access modifiers changed from: protected */
        public DefaultLogBuilder initialValue() {
            return new DefaultLogBuilder(this.logger);
        }
    }
}
