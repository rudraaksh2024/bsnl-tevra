package org.apache.logging.log4j.status;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.ParameterizedNoReferenceMessageFactory;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;

public final class StatusLogger extends AbstractLogger {
    private static final String DEFAULT_STATUS_LEVEL;
    public static final String DEFAULT_STATUS_LISTENER_LEVEL = "log4j2.StatusLogger.level";
    private static final int MAX_ENTRIES;
    public static final String MAX_STATUS_ENTRIES = "log4j2.status.entries";
    private static final String NOT_AVAIL = "?";
    private static final PropertiesUtil PROPS;
    public static final String STATUS_DATE_FORMAT = "log4j2.StatusLogger.DateFormat";
    private static final StatusLogger STATUS_LOGGER = new StatusLogger(StatusLogger.class.getName(), ParameterizedNoReferenceMessageFactory.INSTANCE);
    private static final long serialVersionUID = 2;
    private final Collection<StatusListener> listeners = new CopyOnWriteArrayList();
    private int listenersLevel;
    private final ReadWriteLock listenersLock = new ReentrantReadWriteLock();
    private final SimpleLogger logger;
    /* access modifiers changed from: private */
    public final Queue<StatusData> messages = new BoundedQueue(MAX_ENTRIES);
    private final Lock msgLock = new ReentrantLock();

    static {
        PropertiesUtil propertiesUtil = new PropertiesUtil("log4j2.StatusLogger.properties");
        PROPS = propertiesUtil;
        MAX_ENTRIES = propertiesUtil.getIntegerProperty(MAX_STATUS_ENTRIES, 200);
        DEFAULT_STATUS_LEVEL = propertiesUtil.getStringProperty(DEFAULT_STATUS_LISTENER_LEVEL);
    }

    private StatusLogger(String str, MessageFactory messageFactory) {
        super(str, messageFactory);
        PropertiesUtil propertiesUtil = PROPS;
        String stringProperty = propertiesUtil.getStringProperty(STATUS_DATE_FORMAT, "");
        this.logger = new SimpleLogger("StatusLogger", isDebugPropertyEnabled() ? Level.TRACE : Level.ERROR, false, true, !Strings.isEmpty(stringProperty), false, stringProperty, messageFactory, propertiesUtil, System.err);
        this.listenersLevel = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();
    }

    private boolean isDebugPropertyEnabled() {
        return PropertiesUtil.getProperties().getBooleanProperty(Constants.LOG4J2_DEBUG, false, true);
    }

    public static StatusLogger getLogger() {
        return STATUS_LOGGER;
    }

    public void setLevel(Level level) {
        this.logger.setLevel(level);
    }

    public void registerListener(StatusListener statusListener) {
        this.listenersLock.writeLock().lock();
        try {
            this.listeners.add(statusListener);
            Level statusLevel = statusListener.getStatusLevel();
            if (this.listenersLevel < statusLevel.intLevel()) {
                this.listenersLevel = statusLevel.intLevel();
            }
        } finally {
            this.listenersLock.writeLock().unlock();
        }
    }

    public void removeListener(StatusListener statusListener) {
        closeSilently(statusListener);
        this.listenersLock.writeLock().lock();
        try {
            this.listeners.remove(statusListener);
            int intLevel = Level.toLevel(DEFAULT_STATUS_LEVEL, Level.WARN).intLevel();
            for (StatusListener statusLevel : this.listeners) {
                int intLevel2 = statusLevel.getStatusLevel().intLevel();
                if (intLevel < intLevel2) {
                    intLevel = intLevel2;
                }
            }
            this.listenersLevel = intLevel;
        } finally {
            this.listenersLock.writeLock().unlock();
        }
    }

    public void updateListenerLevel(Level level) {
        if (level.intLevel() > this.listenersLevel) {
            this.listenersLevel = level.intLevel();
        }
    }

    public Iterable<StatusListener> getListeners() {
        return this.listeners;
    }

    public void reset() {
        this.listenersLock.writeLock().lock();
        try {
            for (StatusListener closeSilently : this.listeners) {
                closeSilently(closeSilently);
            }
        } finally {
            this.listeners.clear();
            this.listenersLock.writeLock().unlock();
            clear();
        }
    }

    private static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    public List<StatusData> getStatusData() {
        this.msgLock.lock();
        try {
            return new ArrayList(this.messages);
        } finally {
            this.msgLock.unlock();
        }
    }

    public void clear() {
        this.msgLock.lock();
        try {
            this.messages.clear();
        } finally {
            this.msgLock.unlock();
        }
    }

    public Level getLevel() {
        return this.logger.getLevel();
    }

    /* JADX INFO: finally extract failed */
    public void logMessage(String str, Level level, Marker marker, Message message, Throwable th) {
        StatusData statusData = new StatusData(str != null ? getStackTraceElement(str, Thread.currentThread().getStackTrace()) : null, level, message, th, (String) null);
        this.msgLock.lock();
        try {
            this.messages.add(statusData);
            this.msgLock.unlock();
            if (isDebugPropertyEnabled() || this.listeners.size() <= 0) {
                this.logger.logMessage(str, level, marker, message, th);
                return;
            }
            for (StatusListener next : this.listeners) {
                if (statusData.getLevel().isMoreSpecificThan(next.getStatusLevel())) {
                    next.log(statusData);
                }
            }
        } catch (Throwable th2) {
            this.msgLock.unlock();
            throw th2;
        }
    }

    private StackTraceElement getStackTraceElement(String str, StackTraceElement[] stackTraceElementArr) {
        if (str == null) {
            return null;
        }
        boolean z = false;
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            String className = stackTraceElement.getClassName();
            if (z && !str.equals(className)) {
                return stackTraceElement;
            }
            if (str.equals(className)) {
                z = true;
            } else if (NOT_AVAIL.equals(className)) {
                break;
            }
        }
        return null;
    }

    public boolean isEnabled(Level level, Marker marker, String str, Throwable th) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object... objArr) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, String str, Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, CharSequence charSequence, Throwable th) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, Object obj, Throwable th) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker, Message message, Throwable th) {
        return isEnabled(level, marker);
    }

    public boolean isEnabled(Level level, Marker marker) {
        if (isDebugPropertyEnabled()) {
            return true;
        }
        if (this.listeners.size() <= 0) {
            return this.logger.isEnabled(level, marker);
        }
        if (this.listenersLevel >= level.intLevel()) {
            return true;
        }
        return false;
    }

    private class BoundedQueue<E> extends ConcurrentLinkedQueue<E> {
        private static final long serialVersionUID = -3945953719763255337L;
        private final int size;

        BoundedQueue(int i) {
            this.size = i;
        }

        public boolean add(E e) {
            int i;
            super.add(e);
            while (true) {
                int size2 = StatusLogger.this.messages.size();
                i = this.size;
                if (size2 <= i) {
                    break;
                }
                StatusLogger.this.messages.poll();
            }
            return i > 0;
        }
    }
}
