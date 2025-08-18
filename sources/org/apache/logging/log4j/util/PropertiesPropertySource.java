package org.apache.logging.log4j.util;

import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.util.PropertySource;

public class PropertiesPropertySource implements PropertySource {
    private static final String PREFIX = "log4j2.";
    private final Properties properties;

    public int getPriority() {
        return 0;
    }

    public PropertiesPropertySource(Properties properties2) {
        this.properties = properties2;
    }

    public void forEach(BiConsumer<String, String> biConsumer) {
        for (Map.Entry entry : this.properties.entrySet()) {
            biConsumer.accept((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        return PREFIX + PropertySource.Util.joinAsCamelCase(iterable);
    }
}
