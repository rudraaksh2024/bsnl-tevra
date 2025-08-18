package org.apache.xmlbeans.impl.common;

import org.apache.xmlbeans.SystemProperties;
import org.xml.sax.EntityResolver;

public class ResolverUtil {
    private static EntityResolver _entityResolver;

    static {
        try {
            String property = SystemProperties.getProperty("xmlbean.entityResolver");
            if (property != null) {
                _entityResolver = (EntityResolver) Class.forName(property).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
        } catch (Exception unused) {
            _entityResolver = null;
        }
    }

    public static EntityResolver getGlobalEntityResolver() {
        return _entityResolver;
    }

    public static EntityResolver resolverForCatalog(String str) {
        if (str == null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("org.apache.xml.resolver.CatalogManager");
            Object newInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            cls.getMethod("setCatalogFiles", new Class[]{String.class}).invoke(newInstance, new Object[]{str});
            return (EntityResolver) Class.forName("org.apache.xml.resolver.tools.CatalogResolver").getDeclaredConstructor(new Class[]{cls}).newInstance(new Object[]{newInstance});
        } catch (Exception unused) {
            return null;
        }
    }
}
