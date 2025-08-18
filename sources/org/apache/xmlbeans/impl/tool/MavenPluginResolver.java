package org.apache.xmlbeans.impl.tool;

import com.sun.org.apache.xml.internal.resolver.CatalogManager;
import com.sun.org.apache.xml.internal.resolver.tools.CatalogResolver;
import org.xml.sax.EntityResolver;

public class MavenPluginResolver {
    public static EntityResolver getResolver(String str) {
        if (str == null) {
            return null;
        }
        CatalogManager staticManager = CatalogManager.getStaticManager();
        staticManager.setCatalogFiles(str);
        return new CatalogResolver(staticManager);
    }
}
