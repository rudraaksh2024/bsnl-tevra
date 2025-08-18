package org.apache.poi.ooxml.util;

import javax.xml.transform.TransformerFactory;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;

@Deprecated
@Removal(version = "6.0.0")
public final class TransformerHelper {
    private TransformerHelper() {
    }

    public static TransformerFactory getFactory() {
        return XMLHelper.getTransformerFactory();
    }
}
