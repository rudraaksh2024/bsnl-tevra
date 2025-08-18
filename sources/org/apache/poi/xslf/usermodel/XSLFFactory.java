package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

public final class XSLFFactory extends POIXMLFactory {
    private static final XSLFFactory inst = new XSLFFactory();

    public static XSLFFactory getInstance() {
        return inst;
    }

    private XSLFFactory() {
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getDescriptor(String str) {
        return XSLFRelation.getInstance(str);
    }
}
