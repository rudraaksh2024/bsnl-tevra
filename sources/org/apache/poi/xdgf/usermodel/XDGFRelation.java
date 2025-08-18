package org.apache.poi.xdgf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;

public class XDGFRelation extends POIXMLRelation {
    public static final XDGFRelation DOCUMENT = new XDGFRelation("application/vnd.ms-visio.drawing.main+xml", PackageRelationshipTypes.VISIO_CORE_DOCUMENT, "/visio/document.xml", (POIXMLRelation.PackagePartConstructor) null);
    public static final XDGFRelation IMAGES = new XDGFRelation((String) null, PackageRelationshipTypes.IMAGE_PART, (String) null, (POIXMLRelation.PackagePartConstructor) null);
    public static final XDGFRelation MASTER = new XDGFRelation("application/vnd.ms-visio.master+xml", "http://schemas.microsoft.com/visio/2010/relationships/master", "/visio/masters/master#.xml", new XDGFRelation$$ExternalSyntheticLambda1());
    public static final XDGFRelation MASTERS = new XDGFRelation("application/vnd.ms-visio.masters+xml", "http://schemas.microsoft.com/visio/2010/relationships/masters", "/visio/masters/masters.xml", new XDGFRelation$$ExternalSyntheticLambda0());
    public static final XDGFRelation PAGE = new XDGFRelation("application/vnd.ms-visio.page+xml", "http://schemas.microsoft.com/visio/2010/relationships/page", "/visio/pages/page#.xml", new XDGFRelation$$ExternalSyntheticLambda3());
    public static final XDGFRelation PAGES = new XDGFRelation("application/vnd.ms-visio.pages+xml", "http://schemas.microsoft.com/visio/2010/relationships/pages", "/visio/pages/pages.xml", new XDGFRelation$$ExternalSyntheticLambda2());
    public static final XDGFRelation WINDOW = new XDGFRelation("application/vnd.ms-visio.windows+xml", "http://schemas.microsoft.com/visio/2010/relationships/windows", "/visio/windows.xml", (POIXMLRelation.PackagePartConstructor) null);
    private static final Map<String, XDGFRelation> _table = new HashMap();

    private XDGFRelation(String str, String str2, String str3, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(str, str2, str3, (POIXMLRelation.NoArgConstructor) null, packagePartConstructor, (POIXMLRelation.ParentPartConstructor) null);
        _table.put(str2, this);
    }

    public static XDGFRelation getInstance(String str) {
        return _table.get(str);
    }
}
