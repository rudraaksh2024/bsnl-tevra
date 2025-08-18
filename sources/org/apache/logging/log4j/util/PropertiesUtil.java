package org.apache.logging.log4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.util.PropertySource;

public final class PropertiesUtil {
    private static final PropertiesUtil LOG4J_PROPERTIES = new PropertiesUtil(LOG4J_PROPERTIES_FILE_NAME);
    private static final String LOG4J_PROPERTIES_FILE_NAME = "log4j2.component.properties";
    private static final String LOG4J_SYSTEM_PROPERTIES_FILE_NAME = "log4j2.system.properties";
    private final Environment environment;

    public PropertiesUtil(Properties properties) {
        this.environment = new Environment(new PropertiesPropertySource(properties));
    }

    public PropertiesUtil(String str) {
        this.environment = new Environment(new PropertyFilePropertySource(str));
    }

    static Properties loadClose(InputStream inputStream, Object obj) {
        StringBuilder sb;
        Properties properties = new Properties();
        if (inputStream != null) {
            try {
                properties.load(inputStream);
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e = e;
                    sb = new StringBuilder("Unable to close ");
                }
            } catch (IOException e2) {
                LowLevelLogUtil.logException("Unable to read " + obj, e2);
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e = e3;
                    sb = new StringBuilder("Unable to close ");
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    LowLevelLogUtil.logException("Unable to close " + obj, e4);
                }
                throw th;
            }
        }
        return properties;
        LowLevelLogUtil.logException(sb.append(obj).toString(), e);
        return properties;
    }

    public static PropertiesUtil getProperties() {
        return LOG4J_PROPERTIES;
    }

    public boolean hasProperty(String str) {
        return this.environment.containsKey(str);
    }

    public boolean getBooleanProperty(String str) {
        return getBooleanProperty(str, false);
    }

    public boolean getBooleanProperty(String str, boolean z) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? z : "true".equalsIgnoreCase(stringProperty);
    }

    public boolean getBooleanProperty(String str, boolean z, boolean z2) {
        String stringProperty = getStringProperty(str);
        if (stringProperty == null) {
            return z;
        }
        return stringProperty.isEmpty() ? z2 : "true".equalsIgnoreCase(stringProperty);
    }

    public Boolean getBooleanProperty(String[] strArr, String str, Supplier<Boolean> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Boolean.valueOf(getBooleanProperty(str2 + str));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Charset getCharsetProperty(String str) {
        return getCharsetProperty(str, Charset.defaultCharset());
    }

    public Charset getCharsetProperty(String str, Charset charset) {
        String stringProperty = getStringProperty(str);
        if (stringProperty == null) {
            return charset;
        }
        if (Charset.isSupported(stringProperty)) {
            return Charset.forName(stringProperty);
        }
        ResourceBundle charsetsResourceBundle = getCharsetsResourceBundle();
        if (charsetsResourceBundle.containsKey(str)) {
            String string = charsetsResourceBundle.getString(str);
            if (Charset.isSupported(string)) {
                return Charset.forName(string);
            }
        }
        LowLevelLogUtil.log("Unable to get Charset '" + stringProperty + "' for property '" + str + "', using default " + charset + " and continuing.");
        return charset;
    }

    public double getDoubleProperty(String str, double d) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Double.parseDouble(stringProperty);
            } catch (Exception unused) {
            }
        }
        return d;
    }

    public int getIntegerProperty(String str, int i) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Integer.parseInt(stringProperty.trim());
            } catch (Exception unused) {
            }
        }
        return i;
    }

    public Integer getIntegerProperty(String[] strArr, String str, Supplier<Integer> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Integer.valueOf(getIntegerProperty(str2 + str, 0));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public long getLongProperty(String str, long j) {
        String stringProperty = getStringProperty(str);
        if (stringProperty != null) {
            try {
                return Long.parseLong(stringProperty);
            } catch (Exception unused) {
            }
        }
        return j;
    }

    public Long getLongProperty(String[] strArr, String str, Supplier<Long> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return Long.valueOf(getLongProperty(str2 + str, 0));
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public Duration getDurationProperty(String str, Duration duration) {
        String stringProperty = getStringProperty(str);
        return stringProperty != null ? TimeUnit.getDuration(stringProperty) : duration;
    }

    public Duration getDurationProperty(String[] strArr, String str, Supplier<Duration> supplier) {
        for (String str2 : strArr) {
            if (hasProperty(str2 + str)) {
                return getDurationProperty(str2 + str, (Duration) null);
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public String getStringProperty(String[] strArr, String str, Supplier<String> supplier) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String stringProperty = getStringProperty(strArr[i] + str);
            if (stringProperty != null) {
                return stringProperty;
            }
        }
        if (supplier != null) {
            return supplier.get();
        }
        return null;
    }

    public String getStringProperty(String str) {
        return this.environment.get(str);
    }

    public String getStringProperty(String str, String str2) {
        String stringProperty = getStringProperty(str);
        return stringProperty == null ? str2 : stringProperty;
    }

    public static Properties getSystemProperties() {
        try {
            return new Properties(System.getProperties());
        } catch (SecurityException e) {
            LowLevelLogUtil.logException("Unable to access system properties.", e);
            return new Properties();
        }
    }

    public void reload() {
        this.environment.reload();
    }

    private static class Environment {
        private final Map<CharSequence, String> literal;
        private final Map<CharSequence, String> normalized;
        private final Set<PropertySource> sources;
        private final Map<List<CharSequence>, String> tokenized;

        private Environment(PropertySource propertySource) {
            this.sources = new TreeSet(new PropertySource.Comparator());
            this.literal = new ConcurrentHashMap();
            this.normalized = new ConcurrentHashMap();
            this.tokenized = new ConcurrentHashMap();
            try {
                new PropertyFilePropertySource(PropertiesUtil.LOG4J_SYSTEM_PROPERTIES_FILE_NAME).forEach(new PropertiesUtil$Environment$$ExternalSyntheticLambda1());
            } catch (SecurityException unused) {
            }
            this.sources.add(propertySource);
            for (ClassLoader load : LoaderUtil.getClassLoaders()) {
                try {
                    Iterator<S> it = ServiceLoader.load(PropertySource.class, load).iterator();
                    while (it.hasNext()) {
                        this.sources.add((PropertySource) it.next());
                    }
                } catch (Throwable unused2) {
                }
            }
            reload();
        }

        static /* synthetic */ void lambda$new$0(String str, String str2) {
            if (System.getProperty(str) == null) {
                System.setProperty(str, str2);
            }
        }

        /* access modifiers changed from: private */
        public synchronized void reload() {
            this.literal.clear();
            this.normalized.clear();
            this.tokenized.clear();
            for (PropertySource next : this.sources) {
                next.forEach(new PropertiesUtil$Environment$$ExternalSyntheticLambda0(this, next));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$reload$1$org-apache-logging-log4j-util-PropertiesUtil$Environment  reason: not valid java name */
        public /* synthetic */ void m1926lambda$reload$1$orgapachelogginglog4jutilPropertiesUtil$Environment(PropertySource propertySource, String str, String str2) {
            if (str != null && str2 != null) {
                this.literal.put(str, str2);
                List<CharSequence> list = PropertySource.Util.tokenize(str);
                if (list.isEmpty()) {
                    this.normalized.put(propertySource.getNormalForm(Collections.singleton(str)), str2);
                    return;
                }
                this.normalized.put(propertySource.getNormalForm(list), str2);
                this.tokenized.put(list, str2);
            }
        }

        private static boolean hasSystemProperty(String str) {
            try {
                return System.getProperties().containsKey(str);
            } catch (SecurityException unused) {
                return false;
            }
        }

        /* access modifiers changed from: private */
        public String get(String str) {
            if (this.normalized.containsKey(str)) {
                return this.normalized.get(str);
            }
            if (this.literal.containsKey(str)) {
                return this.literal.get(str);
            }
            if (hasSystemProperty(str)) {
                return System.getProperty(str);
            }
            for (PropertySource next : this.sources) {
                if (next.containsProperty(str)) {
                    return next.getProperty(str);
                }
            }
            return this.tokenized.get(PropertySource.Util.tokenize(str));
        }

        /* access modifiers changed from: private */
        public boolean containsKey(String str) {
            return this.normalized.containsKey(str) || this.literal.containsKey(str) || hasSystemProperty(str) || this.tokenized.containsKey(PropertySource.Util.tokenize(str));
        }
    }

    public static Properties extractSubset(Properties properties, String str) {
        Properties properties2 = new Properties();
        if (!(str == null || str.length() == 0)) {
            if (str.charAt(str.length() - 1) != '.') {
                str = str + '.';
            }
            ArrayList<String> arrayList = new ArrayList<>();
            for (String next : properties.stringPropertyNames()) {
                if (next.startsWith(str)) {
                    properties2.setProperty(next.substring(str.length()), properties.getProperty(next));
                    arrayList.add(next);
                }
            }
            for (String remove : arrayList) {
                properties.remove(remove);
            }
        }
        return properties2;
    }

    static ResourceBundle getCharsetsResourceBundle() {
        return ResourceBundle.getBundle("Log4j-charsets");
    }

    public static Map<String, Properties> partitionOnCommonPrefixes(Properties properties) {
        return partitionOnCommonPrefixes(properties, false);
    }

    public static Map<String, Properties> partitionOnCommonPrefixes(Properties properties, boolean z) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (String next : properties.stringPropertyNames()) {
            int indexOf = next.indexOf(46);
            if (indexOf >= 0) {
                String substring = next.substring(0, indexOf);
                if (!concurrentHashMap.containsKey(substring)) {
                    concurrentHashMap.put(substring, new Properties());
                }
                ((Properties) concurrentHashMap.get(substring)).setProperty(next.substring(indexOf + 1), properties.getProperty(next));
            } else if (z) {
                if (!concurrentHashMap.containsKey(next)) {
                    concurrentHashMap.put(next, new Properties());
                }
                ((Properties) concurrentHashMap.get(next)).setProperty("", properties.getProperty(next));
            }
        }
        return concurrentHashMap;
    }

    public boolean isOsWindows() {
        return getStringProperty("os.name", "").startsWith("Windows");
    }

    private enum TimeUnit {
        NANOS("ns,nano,nanos,nanosecond,nanoseconds", ChronoUnit.NANOS),
        MICROS("us,micro,micros,microsecond,microseconds", ChronoUnit.MICROS),
        MILLIS("ms,milli,millis,millsecond,milliseconds", ChronoUnit.MILLIS),
        SECONDS("s,second,seconds", ChronoUnit.SECONDS),
        MINUTES("m,minute,minutes", ChronoUnit.MINUTES),
        HOURS("h,hour,hours", ChronoUnit.HOURS),
        DAYS("d,day,days", ChronoUnit.DAYS);
        
        private final String[] descriptions;
        private final ChronoUnit timeUnit;

        private TimeUnit(String str, ChronoUnit chronoUnit) {
            this.descriptions = str.split(",");
            this.timeUnit = chronoUnit;
        }

        /* access modifiers changed from: package-private */
        public ChronoUnit getTimeUnit() {
            return this.timeUnit;
        }

        static Duration getDuration(String str) {
            String trim = str.trim();
            ChronoUnit chronoUnit = ChronoUnit.MILLIS;
            long j = 0;
            for (TimeUnit timeUnit2 : values()) {
                for (String str2 : timeUnit2.descriptions) {
                    if (trim.endsWith(str2)) {
                        chronoUnit = timeUnit2.timeUnit;
                        j = Long.parseLong(trim.substring(0, trim.length() - str2.length()));
                    }
                }
            }
            return Duration.of(j, chronoUnit);
        }
    }
}
