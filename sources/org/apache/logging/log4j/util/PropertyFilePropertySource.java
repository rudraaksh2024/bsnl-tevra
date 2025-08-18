package org.apache.logging.log4j.util;

public class PropertyFilePropertySource extends PropertiesPropertySource {
    public int getPriority() {
        return 0;
    }

    public PropertyFilePropertySource(String str) {
        super(loadPropertiesFile(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Properties loadPropertiesFile(java.lang.String r5) {
        /*
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.util.Collection r5 = org.apache.logging.log4j.util.LoaderUtil.findResources(r5)
            java.util.Iterator r5 = r5.iterator()
        L_0x000d:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x0048
            java.lang.Object r1 = r5.next()
            java.net.URL r1 = (java.net.URL) r1
            java.io.InputStream r2 = r1.openStream()     // Catch:{ IOException -> 0x0034 }
            r0.load(r2)     // Catch:{ all -> 0x0026 }
            if (r2 == 0) goto L_0x000d
            r2.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x000d
        L_0x0026:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r4 = move-exception
            if (r2 == 0) goto L_0x0033
            r2.close()     // Catch:{ all -> 0x002f }
            goto L_0x0033
        L_0x002f:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch:{ IOException -> 0x0034 }
        L_0x0033:
            throw r4     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            r2 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unable to read "
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            org.apache.logging.log4j.util.LowLevelLogUtil.logException(r1, r2)
            goto L_0x000d
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.PropertyFilePropertySource.loadPropertiesFile(java.lang.String):java.util.Properties");
    }
}
