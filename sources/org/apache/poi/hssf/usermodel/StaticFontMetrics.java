package org.apache.poi.hssf.usermodel;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

final class StaticFontMetrics {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) StaticFontMetrics.class);
    private static final Map<String, FontDetails> fontDetailsMap = new HashMap();
    private static Properties fontMetricsProps;

    private StaticFontMetrics() {
    }

    public static synchronized FontDetails getFontDetails(Font font) {
        FontDetails fontDetails;
        synchronized (StaticFontMetrics.class) {
            if (fontMetricsProps == null) {
                try {
                    fontMetricsProps = loadMetrics();
                } catch (IOException e) {
                    throw new RuntimeException("Could not load font metrics", e);
                }
            }
            String name = font.getName();
            String str = "";
            if (font.isPlain()) {
                str = "plain";
            }
            if (font.isBold()) {
                str = str + "bold";
            }
            if (font.isItalic()) {
                str = str + "italic";
            }
            String buildFontHeightProperty = FontDetails.buildFontHeightProperty(name);
            String buildFontHeightProperty2 = FontDetails.buildFontHeightProperty(name + "." + str);
            if (fontMetricsProps.get(buildFontHeightProperty) == null && fontMetricsProps.get(buildFontHeightProperty2) != null) {
                name = name + "." + str;
            }
            Map<String, FontDetails> map = fontDetailsMap;
            fontDetails = map.get(name);
            if (fontDetails == null) {
                fontDetails = FontDetails.create(name, fontMetricsProps);
                map.put(name, fontDetails);
            }
        }
        return fontDetails;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0064, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0065, code lost:
        if (r1 != null) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006c, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006f, code lost:
        throw r2;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Properties loadMetrics() throws java.io.IOException {
        /*
            r0 = 0
            java.lang.String r1 = "font.metrics.filename"
            java.lang.String r1 = java.lang.System.getProperty(r1)     // Catch:{ SecurityException -> 0x0026 }
            if (r1 == 0) goto L_0x0039
            java.io.File r2 = new java.io.File     // Catch:{ SecurityException -> 0x0026 }
            r2.<init>(r1)     // Catch:{ SecurityException -> 0x0026 }
            boolean r1 = r2.exists()     // Catch:{ SecurityException -> 0x0024 }
            if (r1 != 0) goto L_0x0038
            org.apache.logging.log4j.Logger r1 = LOGGER     // Catch:{ SecurityException -> 0x0024 }
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()     // Catch:{ SecurityException -> 0x0024 }
            java.lang.String r3 = "font_metrics.properties not found at path {}"
            java.lang.String r4 = r2.getAbsolutePath()     // Catch:{ SecurityException -> 0x0024 }
            r1.log((java.lang.String) r3, (java.lang.Object) r4)     // Catch:{ SecurityException -> 0x0024 }
            goto L_0x0039
        L_0x0024:
            r0 = move-exception
            goto L_0x0029
        L_0x0026:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0029:
            org.apache.logging.log4j.Logger r1 = LOGGER
            org.apache.logging.log4j.LogBuilder r1 = r1.atWarn()
            org.apache.logging.log4j.LogBuilder r0 = r1.withThrowable(r0)
            java.lang.String r1 = "Can't access font.metrics.filename system property"
            r0.log((java.lang.String) r1)
        L_0x0038:
            r0 = r2
        L_0x0039:
            if (r0 == 0) goto L_0x0041
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r0)
            goto L_0x0049
        L_0x0041:
            java.lang.Class<org.apache.poi.hssf.usermodel.FontDetails> r0 = org.apache.poi.hssf.usermodel.FontDetails.class
            java.lang.String r1 = "/font_metrics.properties"
            java.io.InputStream r1 = r0.getResourceAsStream(r1)
        L_0x0049:
            if (r1 == 0) goto L_0x005b
            java.util.Properties r0 = new java.util.Properties     // Catch:{ all -> 0x0059 }
            r0.<init>()     // Catch:{ all -> 0x0059 }
            r0.load(r1)     // Catch:{ all -> 0x0059 }
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            return r0
        L_0x0059:
            r0 = move-exception
            goto L_0x0063
        L_0x005b:
            java.lang.String r0 = "font_metrics.properties not found in classpath"
            java.io.IOException r2 = new java.io.IOException     // Catch:{ all -> 0x0059 }
            r2.<init>(r0)     // Catch:{ all -> 0x0059 }
            throw r2     // Catch:{ all -> 0x0059 }
        L_0x0063:
            throw r0     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r2 = move-exception
            if (r1 == 0) goto L_0x006f
            r1.close()     // Catch:{ all -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r1 = move-exception
            r0.addSuppressed(r1)
        L_0x006f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.hssf.usermodel.StaticFontMetrics.loadMetrics():java.util.Properties");
    }
}
