package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface PropertySource {
    boolean containsProperty(String str) {
        return false;
    }

    void forEach(BiConsumer<String, String> biConsumer) {
    }

    CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        return null;
    }

    int getPriority();

    String getProperty(String str) {
        return null;
    }

    public static class Comparator implements java.util.Comparator<PropertySource>, Serializable {
        private static final long serialVersionUID = 1;

        public int compare(PropertySource propertySource, PropertySource propertySource2) {
            return Integer.compare(((PropertySource) Objects.requireNonNull(propertySource)).getPriority(), ((PropertySource) Objects.requireNonNull(propertySource2)).getPriority());
        }
    }

    public static final class Util {
        private static final Map<CharSequence, List<CharSequence>> CACHE = new ConcurrentHashMap();
        private static final String PREFIXES = "(?i:^log4j2?[-._/]?|^org\\.apache\\.logging\\.log4j\\.)?";
        private static final Pattern PROPERTY_TOKENIZER = Pattern.compile("(?i:^log4j2?[-._/]?|^org\\.apache\\.logging\\.log4j\\.)?([A-Z]*[a-z0-9]+|[A-Z0-9]+)[-._/]?");

        public static List<CharSequence> tokenize(CharSequence charSequence) {
            Map<CharSequence, List<CharSequence>> map = CACHE;
            if (map.containsKey(charSequence)) {
                return map.get(charSequence);
            }
            ArrayList arrayList = new ArrayList();
            Matcher matcher = PROPERTY_TOKENIZER.matcher(charSequence);
            while (matcher.find()) {
                arrayList.add(matcher.group(1).toLowerCase());
            }
            CACHE.put(charSequence, arrayList);
            return arrayList;
        }

        public static CharSequence joinAsCamelCase(Iterable<? extends CharSequence> iterable) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (CharSequence charSequence : iterable) {
                if (z) {
                    sb.append(charSequence);
                } else {
                    sb.append(Character.toUpperCase(charSequence.charAt(0)));
                    if (charSequence.length() > 1) {
                        sb.append(charSequence.subSequence(1, charSequence.length()));
                    }
                }
                z = false;
            }
            return sb.toString();
        }

        private Util() {
        }
    }
}
