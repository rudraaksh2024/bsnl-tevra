package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;

public final class XSSFRelation extends POIXMLRelation {
    public static final XSSFRelation ACTIVEX_BINS = new XSSFRelation("application/vnd.ms-office.activeX", "http://schemas.microsoft.com/office/2006/relationships/activeXControlBinary", "/xl/activeX/activeX#.bin");
    public static final XSSFRelation ACTIVEX_CONTROLS = new XSSFRelation("application/vnd.ms-office.activeX+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/control", "/xl/activeX/activeX#.xml");
    public static final XSSFRelation CALC_CHAIN = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.calcChain+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/calcChain", "/xl/calcChain.xml", new XSSFRelation$$ExternalSyntheticLambda14(), new XSSFRelation$$ExternalSyntheticLambda15());
    public static final XSSFRelation CHART = new XSSFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/xl/charts/chart#.xml", new XSSFRelation$$ExternalSyntheticLambda22(), new XSSFRelation$$ExternalSyntheticLambda30());
    public static final XSSFRelation CHARTSHEET = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.chartsheet+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chartsheet", "/xl/chartsheets/sheet#.xml", (POIXMLRelation.NoArgConstructor) null, new XSSFRelation$$ExternalSyntheticLambda4());
    public static final XSSFRelation CTRL_PROP_RECORDS = new XSSFRelation((String) null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/ctrlProp", "/xl/ctrlProps/ctrlProp#.xml");
    public static final XSSFRelation CUSTOM_PROPERTIES = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.customProperty", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/customProperty", "/xl/customProperty#.bin");
    public static final XSSFRelation CUSTOM_XML_MAPPINGS = new XSSFRelation(ContentTypes.PLAIN_OLD_XML, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/xmlMaps", "/xl/xmlMaps.xml", new XSSFRelation$$ExternalSyntheticLambda31(), new XSSFRelation$$ExternalSyntheticLambda32());
    public static final XSSFRelation DIALOG_SHEET_BIN = new XSSFRelation((String) null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/dialogsheet", "/xl/dialogSheets/sheet#.bin");
    public static final XSSFRelation DRAWINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.drawing+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/drawing", "/xl/drawings/drawing#.xml", new XSSFRelation$$ExternalSyntheticLambda27(), new XSSFRelation$$ExternalSyntheticLambda28());
    public static final XSSFRelation EXTERNAL_LINKS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.externalLink+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/externalLink", "/xl/externalLinks/externalLink#.xml", new XSSFRelation$$ExternalSyntheticLambda16(), new XSSFRelation$$ExternalSyntheticLambda17());
    public static final XSSFRelation HDPHOTO_WDP = new XSSFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.HDPHOTO_PART, "/xl/media/hdphoto#.wdp", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGES = new XSSFRelation((String) null, PackageRelationshipTypes.IMAGE_PART, (String) null, new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_BMP = new XSSFRelation(PictureData.PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.bmp", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_DIB = new XSSFRelation(PictureData.PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.dib", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_EMF = new XSSFRelation(PictureData.PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.emf", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_EPS = new XSSFRelation(PictureData.PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.eps", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_GIF = new XSSFRelation(PictureData.PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.gif", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_JPEG = new XSSFRelation(PictureData.PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.jpeg", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_PICT = new XSSFRelation(PictureData.PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.pict", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_PNG = new XSSFRelation(PictureData.PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.png", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_TIFF = new XSSFRelation(PictureData.PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.tiff", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_WMF = new XSSFRelation(PictureData.PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.wmf", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation IMAGE_WPG = new XSSFRelation(PictureData.PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/xl/media/image#.wpg", new XSSFRelation$$ExternalSyntheticLambda1(), new XSSFRelation$$ExternalSyntheticLambda3());
    public static final XSSFRelation INTL_MACRO_SHEET_BIN = new XSSFRelation((String) null, "http://schemas.microsoft.com/office/2006/relationships/xlIntlMacrosheet", "/xl/macroSheets/sheet#.bin");
    public static final XSSFRelation MACROS_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.sheet.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation MACRO_ADDIN_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.addin.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation MACRO_SHEET_BIN = new XSSFRelation((String) null, "http://schemas.microsoft.com/office/2006/relationships/xlMacrosheet", "/xl/macroSheets/sheet#.bin");
    public static final XSSFRelation MACRO_TEMPLATE_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.template.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final String NS_CHART = "http://schemas.openxmlformats.org/drawingml/2006/chart";
    public static final String NS_DRAWINGML = "http://schemas.openxmlformats.org/drawingml/2006/main";
    public static final String NS_SPREADSHEETML = "http://schemas.openxmlformats.org/spreadsheetml/2006/main";
    public static final XSSFRelation OLEEMBEDDINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.oleObject", POIXMLDocument.OLE_OBJECT_REL_TYPE, "/xl/embeddings/oleObject#.bin");
    public static final XSSFRelation PACKEMBEDDINGS = new XSSFRelation((String) null, POIXMLDocument.PACK_OBJECT_REL_TYPE, (String) null);
    public static final XSSFRelation PIVOT_CACHE_DEFINITION = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotCacheDefinition+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotCacheDefinition", "/xl/pivotCache/pivotCacheDefinition#.xml", new XSSFRelation$$ExternalSyntheticLambda20(), new XSSFRelation$$ExternalSyntheticLambda21());
    public static final XSSFRelation PIVOT_CACHE_RECORDS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotCacheRecords+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotCacheRecords", "/xl/pivotCache/pivotCacheRecords#.xml", new XSSFRelation$$ExternalSyntheticLambda23(), new XSSFRelation$$ExternalSyntheticLambda25());
    public static final XSSFRelation PIVOT_TABLE = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.pivotTable+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/pivotTable", "/xl/pivotTables/pivotTable#.xml", new XSSFRelation$$ExternalSyntheticLambda18(), new XSSFRelation$$ExternalSyntheticLambda19());
    public static final XSSFRelation PRINTER_SETTINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.printerSettings", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/printerSettings", "/xl/printerSettings/printerSettings#.bin");
    public static final XSSFRelation SHARED_STRINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sharedStrings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "/xl/sharedStrings.xml", new XSSFRelation$$ExternalSyntheticLambda5(), new XSSFRelation$$ExternalSyntheticLambda12());
    public static final XSSFRelation SHEET_COMMENTS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/xl/comments#.xml", new XSSFRelation$$ExternalSyntheticLambda6(), new XSSFRelation$$ExternalSyntheticLambda7());
    public static final XSSFRelation SHEET_HYPERLINKS = new XSSFRelation((String) null, PackageRelationshipTypes.HYPERLINK_PART, (String) null);
    public static final XSSFRelation SINGLE_XML_CELLS = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.tableSingleCells+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/tableSingleCells", "/xl/tables/tableSingleCells#.xml", new XSSFRelation$$ExternalSyntheticLambda33(), new XSSFRelation$$ExternalSyntheticLambda34());
    public static final XSSFRelation STYLES = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml", PackageRelationshipTypes.STYLE_PART, "/xl/styles.xml", new XSSFRelation$$ExternalSyntheticLambda24(), new XSSFRelation$$ExternalSyntheticLambda26());
    public static final XSSFRelation TABLE = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.table+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/table", "/xl/tables/table#.xml", new XSSFRelation$$ExternalSyntheticLambda35(), new XSSFRelation$$ExternalSyntheticLambda36());
    public static final XSSFRelation TEMPLATE_WORKBOOK = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.template.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.xml");
    public static final XSSFRelation THEME = new XSSFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/xl/theme/theme#.xml", new XSSFRelation$$ExternalSyntheticLambda10(), new XSSFRelation$$ExternalSyntheticLambda13());
    public static final XSSFRelation VBA_MACROS = new XSSFRelation("application/vnd.ms-office.vbaProject", "http://schemas.microsoft.com/office/2006/relationships/vbaProject", "/xl/vbaProject.bin", new XSSFRelation$$ExternalSyntheticLambda8(), new XSSFRelation$$ExternalSyntheticLambda9());
    public static final XSSFRelation VML_DRAWINGS = new XSSFRelation("application/vnd.openxmlformats-officedocument.vmlDrawing", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/vmlDrawing", "/xl/drawings/vmlDrawing#.vml", new XSSFRelation$$ExternalSyntheticLambda29(), new XSSFRelation$$ExternalSyntheticLambda11());
    public static final XSSFRelation WORKBOOK = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/workbook", "/xl/workbook.xml");
    public static final XSSFRelation WORKSHEET = new XSSFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet", "/xl/worksheets/sheet#.xml", new XSSFRelation$$ExternalSyntheticLambda0(), new XSSFRelation$$ExternalSyntheticLambda2());
    public static final XSSFRelation XLSB_BINARY_WORKBOOK = new XSSFRelation("application/vnd.ms-excel.sheet.binary.macroEnabled.main", PackageRelationshipTypes.CORE_DOCUMENT, "/xl/workbook.bin");
    private static final Map<String, XSSFRelation> _table = new HashMap();

    private XSSFRelation(String str, String str2, String str3) {
        this(str, str2, str3, (POIXMLRelation.NoArgConstructor) null, (POIXMLRelation.PackagePartConstructor) null);
    }

    private XSSFRelation(String str, String str2, String str3, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(str, str2, str3, noArgConstructor, packagePartConstructor, (POIXMLRelation.ParentPartConstructor) null);
        _table.put(str2, this);
    }

    public static XSSFRelation getInstance(String str) {
        return _table.get(str);
    }
}
