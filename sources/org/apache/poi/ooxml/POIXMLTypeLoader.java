package org.apache.poi.ooxml;

import java.util.Collections;
import java.util.HashMap;
import org.apache.poi.openxml4j.opc.PackageNamespaces;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.xmlbeans.XmlOptions;

public class POIXMLTypeLoader {
    public static final XmlOptions DEFAULT_XML_OPTIONS;
    private static final String MS_EXCEL_URN = "urn:schemas-microsoft-com:office:excel";
    private static final String MS_OFFICE_URN = "urn:schemas-microsoft-com:office:office";
    private static final String MS_VML_URN = "urn:schemas-microsoft-com:vml";
    private static final String MS_WORD_URN = "urn:schemas-microsoft-com:office:word";

    static {
        XmlOptions xmlOptions = new XmlOptions();
        DEFAULT_XML_OPTIONS = xmlOptions;
        xmlOptions.setSaveOuter();
        xmlOptions.setUseDefaultNamespace();
        xmlOptions.setSaveAggressiveNamespaces();
        xmlOptions.setCharacterEncoding("UTF-8");
        xmlOptions.setDisallowDocTypeDeclaration(true);
        xmlOptions.setEntityExpansionLimit(1);
        HashMap hashMap = new HashMap();
        hashMap.put(XSSFRelation.NS_DRAWINGML, "a");
        hashMap.put(XSSFRelation.NS_CHART, "c");
        hashMap.put("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wp");
        hashMap.put(PackageNamespaces.MARKUP_COMPATIBILITY, "ve");
        hashMap.put("http://schemas.openxmlformats.org/officeDocument/2006/math", "m");
        hashMap.put(PackageRelationshipTypes.CORE_PROPERTIES_ECMA376_NS, "r");
        hashMap.put("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vt");
        hashMap.put("http://schemas.openxmlformats.org/presentationml/2006/main", "p");
        hashMap.put("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "w");
        hashMap.put("http://schemas.microsoft.com/office/word/2006/wordml", "wne");
        hashMap.put(MS_OFFICE_URN, "o");
        hashMap.put(MS_EXCEL_URN, "x");
        hashMap.put(MS_WORD_URN, "w10");
        hashMap.put(MS_VML_URN, "v");
        xmlOptions.setSaveSuggestedPrefixes(Collections.unmodifiableMap(hashMap));
    }
}
