package org.apache.poi.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Configurator {
    private static final Logger LOG = LogManager.getLogger((Class<?>) Configurator.class);

    public static int getIntValue(String str, int i) {
        String property = System.getProperty(str);
        if (property != null && !"".equals(property) && !"null".equals(property)) {
            try {
                return Integer.parseInt(property);
            } catch (Exception unused) {
                LOG.atError().log("System property -D{} does not contains a valid integer: {}", str, property);
            }
        }
        return i;
    }
}
