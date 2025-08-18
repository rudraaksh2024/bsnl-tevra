package org.apache.poi.xssf.binary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.util.Internal;

@Internal
public class XSSFBRelation extends POIXMLRelation {
    private static final Logger LOGGER = LogManager.getLogger((Class<?>) XSSFBRelation.class);
    static final XSSFBRelation SHARED_STRINGS_BINARY = new XSSFBRelation("application/vnd.ms-excel.sharedStrings", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "/xl/sharedStrings.bin");
    public static final XSSFBRelation STYLES_BINARY = new XSSFBRelation("application/vnd.ms-excel.styles", PackageRelationshipTypes.STYLE_PART, "/xl/styles.bin");

    private XSSFBRelation(String str, String str2, String str3) {
        super(str, str2, str3);
    }
}
