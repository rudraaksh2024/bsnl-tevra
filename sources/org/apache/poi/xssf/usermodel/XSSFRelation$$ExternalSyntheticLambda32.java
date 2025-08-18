package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xssf.model.MapInfo;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class XSSFRelation$$ExternalSyntheticLambda32 implements POIXMLRelation.PackagePartConstructor {
    public final POIXMLDocumentPart init(PackagePart packagePart) {
        return new MapInfo(packagePart);
    }
}
