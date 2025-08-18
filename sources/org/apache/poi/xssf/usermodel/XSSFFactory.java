package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;

public class XSSFFactory extends POIXMLFactory {
    private static final XSSFFactory inst = new XSSFFactory();

    public static XSSFFactory getInstance() {
        return inst;
    }

    protected XSSFFactory() {
    }

    /* access modifiers changed from: protected */
    public POIXMLRelation getDescriptor(String str) {
        return XSSFRelation.getInstance(str);
    }
}
