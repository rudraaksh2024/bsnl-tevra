package org.apache.xmlbeans.impl.soap;

import java.io.InputStream;

class FactoryFinder {
    FactoryFinder() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        throw new org.apache.xmlbeans.impl.soap.SOAPException("Provider " + r4 + " could not be instantiated: " + r0, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:4:0x000c, B:7:0x0013] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0018 A[ExcHandler: Exception (r0v9 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:4:0x000c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object newInstance(java.lang.String r4) throws org.apache.xmlbeans.impl.soap.SOAPException {
        /*
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ Exception -> 0x006b }
            java.lang.ClassLoader r0 = r0.getContextClassLoader()     // Catch:{ Exception -> 0x006b }
            java.lang.String r1 = "Provider "
            if (r0 != 0) goto L_0x0013
            java.lang.Class r0 = java.lang.Class.forName(r4)     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            goto L_0x001b
        L_0x0011:
            r0 = move-exception
            goto L_0x0052
        L_0x0013:
            java.lang.Class r0 = r0.loadClass(r4)     // Catch:{ ClassNotFoundException -> 0x001a, Exception -> 0x0018 }
            goto L_0x001b
        L_0x0018:
            r0 = move-exception
            goto L_0x0035
        L_0x001a:
            r0 = 0
        L_0x001b:
            if (r0 != 0) goto L_0x0027
            java.lang.Class<org.apache.xmlbeans.impl.soap.FactoryFinder> r0 = org.apache.xmlbeans.impl.soap.FactoryFinder.class
            java.lang.ClassLoader r0 = r0.getClassLoader()     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            java.lang.Class r0 = r0.loadClass(r4)     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
        L_0x0027:
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            java.lang.reflect.Constructor r0 = r0.getDeclaredConstructor(r3)     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            java.lang.Object r4 = r0.newInstance(r2)     // Catch:{ ClassNotFoundException -> 0x0011, Exception -> 0x0018 }
            return r4
        L_0x0035:
            org.apache.xmlbeans.impl.soap.SOAPException r2 = new org.apache.xmlbeans.impl.soap.SOAPException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.StringBuilder r4 = r3.append(r4)
            java.lang.String r1 = " could not be instantiated: "
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            r2.<init>(r4, r0)
            throw r2
        L_0x0052:
            org.apache.xmlbeans.impl.soap.SOAPException r2 = new org.apache.xmlbeans.impl.soap.SOAPException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r1)
            java.lang.StringBuilder r4 = r3.append(r4)
            java.lang.String r1 = " not found"
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            r2.<init>(r4, r0)
            throw r2
        L_0x006b:
            r4 = move-exception
            org.apache.xmlbeans.impl.soap.SOAPException r0 = new org.apache.xmlbeans.impl.soap.SOAPException
            java.lang.String r1 = r4.toString()
            r0.<init>(r1, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.soap.FactoryFinder.newInstance(java.lang.String):java.lang.Object");
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x006f A[Catch:{ Exception -> 0x0091 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0041 A[Catch:{ Exception -> 0x005a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object find(java.lang.String r4, java.lang.String r5) throws org.apache.xmlbeans.impl.soap.SOAPException {
        /*
            java.lang.String r0 = org.apache.xmlbeans.SystemProperties.getProperty(r4)     // Catch:{ SecurityException -> 0x000b }
            if (r0 == 0) goto L_0x000b
            java.lang.Object r4 = newInstance(r0)     // Catch:{ SecurityException -> 0x000b }
            return r4
        L_0x000b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x005a }
            r0.<init>()     // Catch:{ Exception -> 0x005a }
            java.lang.String r1 = "java.home"
            java.lang.String r1 = org.apache.xmlbeans.SystemProperties.getProperty(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Exception -> 0x005a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.String r1 = "lib"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.String r1 = java.io.File.separator     // Catch:{ Exception -> 0x005a }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.String r1 = "jaxm.properties"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ Exception -> 0x005a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x005a }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x005a }
            r1.<init>(r0)     // Catch:{ Exception -> 0x005a }
            boolean r0 = r1.exists()     // Catch:{ Exception -> 0x005a }
            if (r0 == 0) goto L_0x005a
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x005a }
            r0.<init>(r1)     // Catch:{ Exception -> 0x005a }
            java.util.Properties r1 = new java.util.Properties     // Catch:{ Exception -> 0x005a }
            r1.<init>()     // Catch:{ Exception -> 0x005a }
            r1.load(r0)     // Catch:{ Exception -> 0x005a }
            r0.close()     // Catch:{ Exception -> 0x005a }
            java.lang.String r0 = r1.getProperty(r4)     // Catch:{ Exception -> 0x005a }
            java.lang.Object r4 = newInstance(r0)     // Catch:{ Exception -> 0x005a }
            return r4
        L_0x005a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "META-INF/services/"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            java.io.InputStream r0 = getResource(r0)     // Catch:{ Exception -> 0x0091 }
            if (r0 == 0) goto L_0x0091
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0091 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0091 }
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8     // Catch:{ Exception -> 0x0091 }
            r2.<init>(r0, r3)     // Catch:{ Exception -> 0x0091 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0091 }
            java.lang.String r0 = r1.readLine()     // Catch:{ Exception -> 0x0091 }
            r1.close()     // Catch:{ Exception -> 0x0091 }
            if (r0 == 0) goto L_0x0091
            java.lang.String r1 = ""
            boolean r1 = r1.equals(r0)     // Catch:{ Exception -> 0x0091 }
            if (r1 != 0) goto L_0x0091
            java.lang.Object r4 = newInstance(r0)     // Catch:{ Exception -> 0x0091 }
            return r4
        L_0x0091:
            if (r5 == 0) goto L_0x0098
            java.lang.Object r4 = newInstance(r5)
            return r4
        L_0x0098:
            org.apache.xmlbeans.impl.soap.SOAPException r5 = new org.apache.xmlbeans.impl.soap.SOAPException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Provider for "
            r0.<init>(r1)
            java.lang.StringBuilder r4 = r0.append(r4)
            java.lang.String r0 = " cannot be found"
            java.lang.StringBuilder r4 = r4.append(r0)
            java.lang.String r4 = r4.toString()
            r0 = 0
            r5.<init>(r4, r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.soap.FactoryFinder.find(java.lang.String, java.lang.String):java.lang.Object");
    }

    private static InputStream getResource(String str) {
        ClassLoader classLoader;
        InputStream inputStream;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (SecurityException unused) {
            classLoader = null;
        }
        if (classLoader == null) {
            inputStream = ClassLoader.getSystemResourceAsStream(str);
        } else {
            inputStream = classLoader.getResourceAsStream(str);
        }
        Class<FactoryFinder> cls = FactoryFinder.class;
        if (inputStream == null) {
            inputStream = cls.getResourceAsStream(str);
        }
        return (inputStream != null || cls.getClassLoader() == null) ? inputStream : cls.getClassLoader().getResourceAsStream(str);
    }
}
