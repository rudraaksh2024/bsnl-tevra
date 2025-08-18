package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XSSFVBAPart extends POIXMLDocumentPart {
    /* access modifiers changed from: protected */
    public void prepareForCommit() {
    }

    protected XSSFVBAPart() {
    }

    protected XSSFVBAPart(PackagePart packagePart) {
        super(packagePart);
    }
}
