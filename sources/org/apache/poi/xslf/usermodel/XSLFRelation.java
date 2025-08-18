package org.apache.poi.xslf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackageRelationshipTypes;
import org.apache.poi.sl.usermodel.PictureData;

public final class XSLFRelation extends POIXMLRelation {
    public static final XSLFRelation CHART = new XSLFRelation("application/vnd.openxmlformats-officedocument.drawingml.chart+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/chart", "/ppt/charts/chart#.xml", new XSLFRelation$$ExternalSyntheticLambda23(), new XSLFRelation$$ExternalSyntheticLambda24());
    public static final XSLFRelation COMMENTS = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.comments+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/comments", "/ppt/comments/comment#.xml", new XSLFRelation$$ExternalSyntheticLambda16(), new XSLFRelation$$ExternalSyntheticLambda17());
    public static final XSLFRelation COMMENT_AUTHORS = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.commentAuthors+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/commentAuthors", "/ppt/commentAuthors.xml", new XSLFRelation$$ExternalSyntheticLambda11(), new XSLFRelation$$ExternalSyntheticLambda18());
    public static final XSLFRelation FONT = new XSLFRelation("application/x-fontdata", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/font", "/ppt/fonts/font#.fntdata", new XSLFRelation$$ExternalSyntheticLambda10(), new XSLFRelation$$ExternalSyntheticLambda12());
    public static final XSLFRelation HDPHOTO_WDP = new XSLFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.HDPHOTO_PART, "/ppt/media/hdphoto#.wdp", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation HYPERLINK = new XSLFRelation((String) null, PackageRelationshipTypes.HYPERLINK_PART, (String) null);
    public static final XSLFRelation IMAGES = new XSLFRelation((String) null, PackageRelationshipTypes.IMAGE_PART, (String) null, new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_BMP = new XSLFRelation(PictureData.PictureType.BMP.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.bmp", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_DIB = new XSLFRelation(PictureData.PictureType.DIB.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.dib", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_EMF = new XSLFRelation(PictureData.PictureType.EMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.emf", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_EPS = new XSLFRelation(PictureData.PictureType.EPS.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.eps", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_GIF = new XSLFRelation(PictureData.PictureType.GIF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.gif", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_JPEG = new XSLFRelation(PictureData.PictureType.JPEG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.jpeg", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_PICT = new XSLFRelation(PictureData.PictureType.PICT.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.pict", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_PNG = new XSLFRelation(PictureData.PictureType.PNG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.png", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_SVG = new XSLFRelation(PictureData.PictureType.SVG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.svg", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_TIFF = new XSLFRelation(PictureData.PictureType.TIFF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.tiff", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_WDP = new XSLFRelation(PictureData.PictureType.WDP.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wdp", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_WMF = new XSLFRelation(PictureData.PictureType.WMF.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wmf", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation IMAGE_WPG = new XSLFRelation(PictureData.PictureType.WPG.contentType, PackageRelationshipTypes.IMAGE_PART, "/ppt/media/image#.wpg", new XSLFRelation$$ExternalSyntheticLambda25(), new XSLFRelation$$ExternalSyntheticLambda1());
    public static final XSLFRelation MACRO = new XSLFRelation("application/vnd.ms-powerpoint.slideshow.macroEnabled.main+xml");
    public static final XSLFRelation MACRO_TEMPLATE = new XSLFRelation("application/vnd.ms-powerpoint.template.macroEnabled.main+xml");
    public static final XSLFRelation MAIN = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.presentation.main+xml");
    public static final XSLFRelation NOTES = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.notesSlide+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/notesSlide", "/ppt/notesSlides/notesSlide#.xml", new XSLFRelation$$ExternalSyntheticLambda0(), new XSLFRelation$$ExternalSyntheticLambda2());
    public static final XSLFRelation NOTES_MASTER = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.notesMaster+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/notesMaster", "/ppt/notesMasters/notesMaster#.xml", new XSLFRelation$$ExternalSyntheticLambda14(), new XSLFRelation$$ExternalSyntheticLambda15());
    static final String NS_DRAWINGML = "http://schemas.openxmlformats.org/drawingml/2006/main";
    public static final XSLFRelation OLE_OBJECT = new XSLFRelation("application/vnd.openxmlformats-officedocument.oleObject", POIXMLDocument.OLE_OBJECT_REL_TYPE, "/ppt/embeddings/oleObject#.bin", new XSLFRelation$$ExternalSyntheticLambda8(), new XSLFRelation$$ExternalSyntheticLambda9());
    public static final XSLFRelation PRESENTATIONML = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideshow.main+xml");
    public static final XSLFRelation PRESENTATIONML_TEMPLATE = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.template.main+xml");
    public static final XSLFRelation PRESENTATION_MACRO = new XSLFRelation("application/vnd.ms-powerpoint.presentation.macroEnabled.main+xml");
    public static final XSLFRelation SLIDE = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slide+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slide", "/ppt/slides/slide#.xml", new XSLFRelation$$ExternalSyntheticLambda3(), new XSLFRelation$$ExternalSyntheticLambda4());
    public static final XSLFRelation SLIDE_LAYOUT = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideLayout+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideLayout", "/ppt/slideLayouts/slideLayout#.xml", (POIXMLRelation.NoArgConstructor) null, new XSLFRelation$$ExternalSyntheticLambda7());
    public static final XSLFRelation SLIDE_MASTER = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.slideMaster+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/slideMaster", "/ppt/slideMasters/slideMaster#.xml", (POIXMLRelation.NoArgConstructor) null, new XSLFRelation$$ExternalSyntheticLambda13());
    public static final XSLFRelation TABLE_STYLES = new XSLFRelation("application/vnd.openxmlformats-officedocument.presentationml.tableStyles+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/tableStyles", "/ppt/tableStyles.xml", new XSLFRelation$$ExternalSyntheticLambda5(), new XSLFRelation$$ExternalSyntheticLambda6());
    public static final XSLFRelation THEME = new XSLFRelation("application/vnd.openxmlformats-officedocument.theme+xml", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/theme", "/ppt/theme/theme#.xml", new XSLFRelation$$ExternalSyntheticLambda19(), new XSLFRelation$$ExternalSyntheticLambda20());
    public static final XSLFRelation THEME_MANAGER = new XSLFRelation("application/vnd.openxmlformats-officedocument.themeManager+xml");
    public static final XSLFRelation VML_DRAWING = new XSLFRelation("application/vnd.openxmlformats-officedocument.vmlDrawing", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/vmlDrawing", "/ppt/drawings/vmlDrawing#.vml");
    public static final XSLFRelation WORKBOOK = new XSLFRelation("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", POIXMLDocument.PACK_OBJECT_REL_TYPE, "/ppt/embeddings/Microsoft_Excel_Worksheet#.xlsx", new XSLFRelation$$ExternalSyntheticLambda21(), new XSLFRelation$$ExternalSyntheticLambda22());
    private static final Map<String, XSLFRelation> _table = new HashMap();

    private XSLFRelation(String str) {
        this(str, (String) null, (String) null, (POIXMLRelation.NoArgConstructor) null, (POIXMLRelation.PackagePartConstructor) null);
    }

    private XSLFRelation(String str, String str2, String str3) {
        this(str, str2, str3, (POIXMLRelation.NoArgConstructor) null, (POIXMLRelation.PackagePartConstructor) null);
    }

    private XSLFRelation(String str, String str2, String str3, POIXMLRelation.NoArgConstructor noArgConstructor, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(str, str2, str3, noArgConstructor, packagePartConstructor, (POIXMLRelation.ParentPartConstructor) null);
        _table.put(str2, this);
    }

    public static XSLFRelation getInstance(String str) {
        return _table.get(str);
    }
}
