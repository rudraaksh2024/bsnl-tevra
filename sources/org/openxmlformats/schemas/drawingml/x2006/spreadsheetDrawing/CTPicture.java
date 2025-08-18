package org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;

public interface CTPicture extends XmlObject {
    public static final DocumentFactory<CTPicture> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTPictureNonVisual addNewNvPicPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTBlipFillProperties getBlipFill();

    boolean getFPublished();

    String getMacro();

    CTPictureNonVisual getNvPicPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    boolean isSetFPublished();

    boolean isSetMacro();

    boolean isSetStyle();

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setFPublished(boolean z);

    void setMacro(String str);

    void setNvPicPr(CTPictureNonVisual cTPictureNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void unsetFPublished();

    void unsetMacro();

    void unsetStyle();

    XmlBoolean xgetFPublished();

    XmlString xgetMacro();

    void xsetFPublished(XmlBoolean xmlBoolean);

    void xsetMacro(XmlString xmlString);

    static {
        DocumentFactory<CTPicture> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicturee028type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
