package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

public final class XWPFFactory extends POIXMLFactory {
    private static final XWPFFactory inst = new XWPFFactory();

    public static XWPFFactory getInstance() {
        return inst;
    }

    private XWPFFactory() {
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getDescriptor(String str) {
        return XWPFRelation.getInstance(str);
    }
}
