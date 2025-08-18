package org.apache.xmlbeans;

import org.apache.xmlbeans.XmlFactoryHook;
import org.apache.xmlbeans.impl.common.SystemCache;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.values.NamespaceContext;

public class ThreadLocalUtil {
    public static void clearAllThreadLocals() {
        XmlBeans.clearThreadLocals();
        XmlFactoryHook.ThreadContext.clearThreadLocals();
        StscState.clearThreadLocals();
        CharUtil.clearThreadLocals();
        Locale.clearThreadLocals();
        NamespaceContext.clearThreadLocals();
        SystemCache.get().clearThreadLocals();
    }
}
