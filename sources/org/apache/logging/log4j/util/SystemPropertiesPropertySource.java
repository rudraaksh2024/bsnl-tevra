package org.apache.logging.log4j.util;

import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.util.PropertySource;

public class SystemPropertiesPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = 100;
    private static final String PREFIX = "log4j2.";

    public int getPriority() {
        return 100;
    }

    public void forEach(BiConsumer<String, String> biConsumer) {
        Object[] array;
        try {
            Properties properties = System.getProperties();
            synchronized (properties) {
                array = properties.keySet().toArray();
            }
            for (Object objects : array) {
                String objects2 = Objects.toString(objects, (String) null);
                biConsumer.accept(objects2, properties.getProperty(objects2));
            }
        } catch (SecurityException unused) {
        }
    }

    public CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        return PREFIX + PropertySource.Util.joinAsCamelCase(iterable);
    }
}
