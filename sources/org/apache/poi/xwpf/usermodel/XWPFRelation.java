package org.apache.poi.xwpf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda21;
import org.apache.poi.xslf.usermodel.XSLFRelation$$ExternalSyntheticLambda22;

public final class XWPFRelation extends POIXMLRelation {
    public static final XWPFRelation CHART = new XWPFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/word/charts/chart#.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda13(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda14());
    public static final XWPFRelation COMMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/word/comments.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda15(), (POIXMLRelation.ParentPartConstructor) new XWPFRelation$$ExternalSyntheticLambda16());
    public static final XWPFRelation DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation ENDNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.endnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/endnotes", "/word/endnotes.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda19(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda1());
    public static final XWPFRelation FONT_TABLE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.fontTable+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/fontTable", "/word/fontTable.xml");
    public static final XWPFRelation FOOTER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footer+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footer", "/word/footer#.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda11(), (POIXMLRelation.ParentPartConstructor) new XWPFRelation$$ExternalSyntheticLambda12());
    public static final XWPFRelation FOOTNOTE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.footnotes+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/footnotes", "/word/footnotes.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda17(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda18());
    public static final XWPFRelation GLOSSARY_DOCUMENT = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.document.glossary+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/glossaryDocument", "/word/glossary/document.xml");
    public static final XWPFRelation HEADER = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.header+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/header", "/word/header#.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda9(), (POIXMLRelation.ParentPartConstructor) new XWPFRelation$$ExternalSyntheticLambda10());
    public static final XWPFRelation HYPERLINK = new XWPFRelation((String) null, PackageRelationshipTypes.HYPERLINK_PART, (String) null);
    public static final XWPFRelation IMAGES = new XWPFRelation((String) null, PackageRelationshipTypes.IMAGE_PART, (String) null, (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_BMP = new XWPFRelation(PictureData.PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.bmp", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_DIB = new XWPFRelation(PictureData.PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.dib", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_EMF = new XWPFRelation(PictureData.PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.emf", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_EPS = new XWPFRelation(PictureData.PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.eps", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_GIF = new XWPFRelation(PictureData.PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.gif", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_JPEG = new XWPFRelation(PictureData.PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.jpeg", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_PICT = new XWPFRelation(PictureData.PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.pict", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_PNG = new XWPFRelation(PictureData.PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.png", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_TIFF = new XWPFRelation(PictureData.PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.tiff", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_WMF = new XWPFRelation(PictureData.PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.wmf", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation IMAGE_WPG = new XWPFRelation(PictureData.PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/word/media/image#.wpg", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda3(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda4());
    public static final XWPFRelation MACRO_DOCUMENT = new XWPFRelation("application/vnd.ms-word.document.macroEnabled.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation MACRO_TEMPLATE_DOCUMENT = new XWPFRelation("application/vnd.ms-word.template.macroEnabledTemplate.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation NUMBERING = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.numbering+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/numbering", "/word/numbering.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda0(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda2());
    public static final XWPFRelation SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.settings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/settings", "/word/settings.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda5(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda6());
    public static final XWPFRelation STYLES = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml", PackageRelationshipTypes.STYLE_PART, "/word/styles.xml", (POIXMLRelation.NoArgConstructor) new XWPFRelation$$ExternalSyntheticLambda7(), (POIXMLRelation.PackagePartConstructor) new XWPFRelation$$ExternalSyntheticLambda8());
    public static final XWPFRelation TEMPLATE = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.template.main+xml", PackageRelationshipTypes.CORE_DOCUMENT, "/word/document.xml");
    public static final XWPFRelation THEME = new XWPFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/word/theme/theme#.xml");
    public static final XWPFRelation WEB_SETTINGS = new XWPFRelation("application/vnd.openxmlformats-officedocument.wordprocessingml.webSettings+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/webSettings", "/word/webSettings.xml");
    public static final XWPFRelation WORKBOOK = new XWPFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", POIXMLDocument.PACK_OBJECT_REL_TYPE, "/word/embeddings/Microsoft_Excel_Worksheet#.xlsx", (POIXMLRelation.NoArgConstructor) new XSLFRelation$$ExternalSyntheticLambda21(), (POIXMLRelation.PackagePartConstructor) new XSLFRelation$$ExternalSyntheticLambda22());
    private static final Map<String, XWPFRelation> _table = new HashMap();

    private XWPFRelation(String str, String str2, String str3) {
        super(str, str2, str3);
        _table.put(str2, this);
    }

    private XWPFRelation(String str, String str2, String str3, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(str, str2, str3, noArgConstructor, packagePartConstructor, (POIXMLRelation.ParentPartConstructor) null);
        _table.put(str2, this);
    }

    private XWPFRelation(String str, String str2, String str3, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.ParentPartConstructor parentPartConstructor) {
        super(str, str2, str3, noArgConstructor, (POIXMLRelation.PackagePartConstructor) null, parentPartConstructor);
        _table.put(str2, this);
    }

    public static XWPFRelation getInstance(String str) {
        return _table.get(str);
    }
}
