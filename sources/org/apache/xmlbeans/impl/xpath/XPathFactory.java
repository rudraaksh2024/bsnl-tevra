package org.apache.xmlbeans.impl.xpath;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.xpath.XPath;
import org.apache.xmlbeans.impl.xpath.saxon.SaxonXPath;
import org.apache.xmlbeans.impl.xpath.saxon.SaxonXQuery;
import org.apache.xmlbeans.impl.xpath.xmlbeans.XmlbeansXPath;

public class XPathFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int USE_SAXON = 4;
    private static final int USE_XMLBEANS = 1;
    private static final Map<String, WeakReference<Path>> _xmlbeansPathCache = new WeakHashMap();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static String getCurrentNodeVar(XmlOptions xmlOptions) {
        String xqueryCurrentNodeVar = XmlOptions.maskNull(xmlOptions).getXqueryCurrentNodeVar();
        if (xqueryCurrentNodeVar == null) {
            return "this";
        }
        if (!xqueryCurrentNodeVar.startsWith("$")) {
            return xqueryCurrentNodeVar;
        }
        throw new IllegalArgumentException("Omit the '$' prefix for the current node variable");
    }

    public static Path getCompiledPath(String str, XmlOptions xmlOptions) {
        XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
        return getCompiledPath(str, maskNull, getCurrentNodeVar(maskNull));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: org.apache.xmlbeans.impl.xpath.Path} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.xmlbeans.impl.xpath.Path getCompiledPath(java.lang.String r6, org.apache.xmlbeans.XmlOptions r7, java.lang.String r8) {
        /*
            boolean r0 = r7.isXPathUseSaxon()
            r1 = 1
            if (r0 == 0) goto L_0x0009
            r7 = 4
            goto L_0x0012
        L_0x0009:
            boolean r7 = r7.isXPathUseXmlBeans()
            if (r7 == 0) goto L_0x0011
            r7 = r1
            goto L_0x0012
        L_0x0011:
            r7 = 5
        L_0x0012:
            r0 = r7 & 4
            r2 = 0
            if (r0 == 0) goto L_0x001d
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            goto L_0x001e
        L_0x001d:
            r3 = r2
        L_0x001e:
            java.util.concurrent.locks.ReentrantReadWriteLock r4 = lock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r5 = r4.readLock()
            r5.lock()
            r7 = r7 & r1
            if (r7 == 0) goto L_0x0035
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<org.apache.xmlbeans.impl.xpath.Path>> r1 = _xmlbeansPathCache     // Catch:{ all -> 0x0033 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0033 }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ all -> 0x0033 }
            goto L_0x0036
        L_0x0033:
            r6 = move-exception
            goto L_0x0040
        L_0x0035:
            r1 = r2
        L_0x0036:
            if (r1 == 0) goto L_0x004a
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x0033 }
            r2 = r1
            org.apache.xmlbeans.impl.xpath.Path r2 = (org.apache.xmlbeans.impl.xpath.Path) r2     // Catch:{ all -> 0x0033 }
            goto L_0x004a
        L_0x0040:
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = lock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r7 = r7.readLock()
            r7.unlock()
            throw r6
        L_0x004a:
            if (r2 == 0) goto L_0x0054
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r6 = r4.readLock()
            r6.unlock()
            return r2
        L_0x0054:
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r4.readLock()
            r1.unlock()
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r1 = r4.writeLock()
            r1.lock()
            if (r7 == 0) goto L_0x007e
            java.util.Map<java.lang.String, java.lang.ref.WeakReference<org.apache.xmlbeans.impl.xpath.Path>> r1 = _xmlbeansPathCache     // Catch:{ all -> 0x007c }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x007c }
            java.lang.ref.WeakReference r1 = (java.lang.ref.WeakReference) r1     // Catch:{ all -> 0x007c }
            if (r1 == 0) goto L_0x0075
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x007c }
            org.apache.xmlbeans.impl.xpath.Path r1 = (org.apache.xmlbeans.impl.xpath.Path) r1     // Catch:{ all -> 0x007c }
            r2 = r1
        L_0x0075:
            if (r2 != 0) goto L_0x007e
            org.apache.xmlbeans.impl.xpath.Path r2 = getCompiledPathXmlBeans(r6, r8, r3)     // Catch:{ all -> 0x007c }
            goto L_0x007e
        L_0x007c:
            r6 = move-exception
            goto L_0x00bc
        L_0x007e:
            if (r2 != 0) goto L_0x0086
            if (r0 == 0) goto L_0x0086
            org.apache.xmlbeans.impl.xpath.Path r2 = getCompiledPathSaxon(r6, r8, r3)     // Catch:{ all -> 0x007c }
        L_0x0086:
            if (r2 != 0) goto L_0x00c6
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x007c }
            r8.<init>()     // Catch:{ all -> 0x007c }
            if (r7 == 0) goto L_0x0094
            java.lang.String r7 = " Trying XmlBeans path engine..."
            r8.append(r7)     // Catch:{ all -> 0x007c }
        L_0x0094:
            if (r0 == 0) goto L_0x009b
            java.lang.String r7 = " Trying Saxon path engine..."
            r8.append(r7)     // Catch:{ all -> 0x007c }
        L_0x009b:
            java.lang.RuntimeException r7 = new java.lang.RuntimeException     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x007c }
            r0.<init>()     // Catch:{ all -> 0x007c }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r8 = r0.append(r8)     // Catch:{ all -> 0x007c }
            java.lang.String r0 = " FAILED on "
            java.lang.StringBuilder r8 = r8.append(r0)     // Catch:{ all -> 0x007c }
            java.lang.StringBuilder r6 = r8.append(r6)     // Catch:{ all -> 0x007c }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x007c }
            r7.<init>(r6)     // Catch:{ all -> 0x007c }
            throw r7     // Catch:{ all -> 0x007c }
        L_0x00bc:
            java.util.concurrent.locks.ReentrantReadWriteLock r7 = lock
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r7 = r7.writeLock()
            r7.unlock()
            throw r6
        L_0x00c6:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r6 = r4.writeLock()
            r6.unlock()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.xpath.XPathFactory.getCompiledPath(java.lang.String, org.apache.xmlbeans.XmlOptions, java.lang.String):org.apache.xmlbeans.impl.xpath.Path");
    }

    private static Path getCompiledPathXmlBeans(String str, String str2, Map<String, String> map) {
        try {
            XmlbeansXPath xmlbeansXPath = new XmlbeansXPath(str, str2, XPath.compileXPath(str, str2, map));
            _xmlbeansPathCache.put(str, new WeakReference(xmlbeansXPath));
            return xmlbeansXPath;
        } catch (XPath.XPathCompileException unused) {
            return null;
        }
    }

    public static Path getCompiledPathSaxon(String str, String str2, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        try {
            XPath.compileXPath(str, str2, map);
        } catch (XPath.XPathCompileException unused) {
        }
        int parseInt = Integer.parseInt(map.getOrDefault(XPath._NS_BOUNDARY, "0"));
        map.remove(XPath._NS_BOUNDARY);
        return new SaxonXPath(str.substring(parseInt), str2, map);
    }

    public static String compilePath(String str, XmlOptions xmlOptions) {
        getCompiledPath(str, xmlOptions);
        return str;
    }

    public static XmlObject[] objectExecQuery(Cur cur, String str, XmlOptions xmlOptions) {
        return getCompiledQuery(str, xmlOptions).objectExecute(cur, xmlOptions);
    }

    public static XmlCursor cursorExecQuery(Cur cur, String str, XmlOptions xmlOptions) {
        return getCompiledQuery(str, xmlOptions).cursorExecute(cur, xmlOptions);
    }

    public static synchronized XQuery getCompiledQuery(String str, XmlOptions xmlOptions) {
        XQuery compiledQuery;
        synchronized (XPathFactory.class) {
            compiledQuery = getCompiledQuery(str, getCurrentNodeVar(xmlOptions), xmlOptions);
        }
        return compiledQuery;
    }

    static synchronized XQuery getCompiledQuery(String str, String str2, XmlOptions xmlOptions) {
        String str3;
        SaxonXQuery saxonXQuery;
        synchronized (XPathFactory.class) {
            XmlOptions maskNull = XmlOptions.maskNull(xmlOptions);
            HashMap hashMap = new HashMap();
            try {
                XPath.compileXPath(str, str2, hashMap);
                str3 = (String) hashMap.getOrDefault(XPath._NS_BOUNDARY, "0");
            } catch (XPath.XPathCompileException unused) {
                str3 = (String) hashMap.getOrDefault(XPath._NS_BOUNDARY, "0");
            } catch (Throwable th) {
                Integer.parseInt((String) hashMap.getOrDefault(XPath._NS_BOUNDARY, "0"));
                throw th;
            }
            saxonXQuery = new SaxonXQuery(str, str2, Integer.valueOf(Integer.parseInt(str3)), maskNull);
        }
        return saxonXQuery;
    }

    public static synchronized String compileQuery(String str, XmlOptions xmlOptions) {
        synchronized (XPathFactory.class) {
            getCompiledQuery(str, xmlOptions);
        }
        return str;
    }
}
