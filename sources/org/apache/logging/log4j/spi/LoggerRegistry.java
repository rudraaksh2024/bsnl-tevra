package org.apache.logging.log4j.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.spi.ExtendedLogger;

public class LoggerRegistry<T extends ExtendedLogger> {
    private static final String DEFAULT_FACTORY_KEY = AbstractLogger.DEFAULT_MESSAGE_FACTORY_CLASS.getName();
    private final MapFactory<T> factory;
    private final Map<String, Map<String, T>> map;

    public interface MapFactory<T extends ExtendedLogger> {
        Map<String, T> createInnerMap();

        Map<String, Map<String, T>> createOuterMap();

        void putIfAbsent(Map<String, T> map, String str, T t);
    }

    public static class ConcurrentMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        public Map<String, T> createInnerMap() {
            return new ConcurrentHashMap();
        }

        public Map<String, Map<String, T>> createOuterMap() {
            return new ConcurrentHashMap();
        }

        public void putIfAbsent(Map<String, T> map, String str, T t) {
            ((ConcurrentMap) map).putIfAbsent(str, t);
        }
    }

    public static class WeakMapFactory<T extends ExtendedLogger> implements MapFactory<T> {
        public Map<String, T> createInnerMap() {
            return new WeakHashMap();
        }

        public Map<String, Map<String, T>> createOuterMap() {
            return new WeakHashMap();
        }

        public void putIfAbsent(Map<String, T> map, String str, T t) {
            map.put(str, t);
        }
    }

    public LoggerRegistry() {
        this(new ConcurrentMapFactory());
    }

    public LoggerRegistry(MapFactory<T> mapFactory) {
        this.factory = (MapFactory) Objects.requireNonNull(mapFactory, "factory");
        this.map = mapFactory.createOuterMap();
    }

    private static String factoryClassKey(Class<? extends MessageFactory> cls) {
        return cls == null ? DEFAULT_FACTORY_KEY : cls.getName();
    }

    private static String factoryKey(MessageFactory messageFactory) {
        return messageFactory == null ? DEFAULT_FACTORY_KEY : messageFactory.getClass().getName();
    }

    public T getLogger(String str) {
        return (ExtendedLogger) getOrCreateInnerMap(DEFAULT_FACTORY_KEY).get(str);
    }

    public T getLogger(String str, MessageFactory messageFactory) {
        return (ExtendedLogger) getOrCreateInnerMap(factoryKey(messageFactory)).get(str);
    }

    public Collection<T> getLoggers() {
        return getLoggers(new ArrayList());
    }

    public Collection<T> getLoggers(Collection<T> collection) {
        for (Map<String, T> values : this.map.values()) {
            collection.addAll(values.values());
        }
        return collection;
    }

    private Map<String, T> getOrCreateInnerMap(String str) {
        Map<String, T> map2 = this.map.get(str);
        if (map2 != null) {
            return map2;
        }
        Map<String, T> createInnerMap = this.factory.createInnerMap();
        this.map.put(str, createInnerMap);
        return createInnerMap;
    }

    public boolean hasLogger(String str) {
        return getOrCreateInnerMap(DEFAULT_FACTORY_KEY).containsKey(str);
    }

    public boolean hasLogger(String str, MessageFactory messageFactory) {
        return getOrCreateInnerMap(factoryKey(messageFactory)).containsKey(str);
    }

    public boolean hasLogger(String str, Class<? extends MessageFactory> cls) {
        return getOrCreateInnerMap(factoryClassKey(cls)).containsKey(str);
    }

    public void putIfAbsent(String str, MessageFactory messageFactory, T t) {
        this.factory.putIfAbsent(getOrCreateInnerMap(factoryKey(messageFactory)), str, t);
    }
}
