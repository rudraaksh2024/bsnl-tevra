package org.apache.logging.log4j.util;

import java.util.Map;
import org.apache.xmlbeans.impl.common.NameUtil;

public class EnvironmentPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = -100;
    private static final String PREFIX = "LOG4J_";

    public int getPriority() {
        return -100;
    }

    public void forEach(BiConsumer<String, String> biConsumer) {
        try {
            for (Map.Entry next : System.getenv().entrySet()) {
                String str = (String) next.getKey();
                if (str.startsWith(PREFIX)) {
                    biConsumer.accept(str.substring(6), next.getValue());
                }
            }
        } catch (SecurityException e) {
            LowLevelLogUtil.logException("The system environment variables are not available to Log4j due to security restrictions: " + e, e);
        }
    }

    public CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        StringBuilder sb = new StringBuilder("LOG4J");
        for (CharSequence charSequence : iterable) {
            sb.append(NameUtil.USCORE);
            for (int i = 0; i < charSequence.length(); i++) {
                sb.append(Character.toUpperCase(charSequence.charAt(i)));
            }
        }
        return sb.toString();
    }
}
