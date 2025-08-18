package org.apache.poi.util;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XMLHelper$$ExternalSyntheticLambda10 implements EntityResolver {
    public final InputSource resolveEntity(String str, String str2) {
        return XMLHelper.ignoreEntity(str, str2);
    }
}
