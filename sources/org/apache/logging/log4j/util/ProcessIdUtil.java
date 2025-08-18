package org.apache.logging.log4j.util;

public class ProcessIdUtil {
    public static final String DEFAULT_PROCESSID = "-";

    /* JADX WARNING: Can't wrap try/catch for region: R(3:4|5|6) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0044, code lost:
        return new java.io.File("/proc/self").getCanonicalFile().getName();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0045, code lost:
        return DEFAULT_PROCESSID;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0035 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getProcessId() {
        /*
            java.lang.String r0 = "java.lang.management.ManagementFactory"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "getRuntimeMXBean"
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0035 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r1, r3)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "java.lang.management.RuntimeMXBean"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r3 = "getName"
            java.lang.Class[] r4 = new java.lang.Class[r2]     // Catch:{ Exception -> 0x0035 }
            java.lang.reflect.Method r1 = r1.getDeclaredMethod(r3, r4)     // Catch:{ Exception -> 0x0035 }
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0035 }
            r4 = 0
            java.lang.Object r0 = r0.invoke(r4, r3)     // Catch:{ Exception -> 0x0035 }
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0035 }
            java.lang.Object r0 = r1.invoke(r0, r3)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "@"
            java.lang.String[] r0 = r0.split(r1)     // Catch:{ Exception -> 0x0035 }
            r0 = r0[r2]     // Catch:{ Exception -> 0x0035 }
            return r0
        L_0x0035:
            java.io.File r0 = new java.io.File     // Catch:{ IOException -> 0x0045 }
            java.lang.String r1 = "/proc/self"
            r0.<init>(r1)     // Catch:{ IOException -> 0x0045 }
            java.io.File r0 = r0.getCanonicalFile()     // Catch:{ IOException -> 0x0045 }
            java.lang.String r0 = r0.getName()     // Catch:{ IOException -> 0x0045 }
            return r0
        L_0x0045:
            java.lang.String r0 = "-"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.util.ProcessIdUtil.getProcessId():java.lang.String");
    }
}
