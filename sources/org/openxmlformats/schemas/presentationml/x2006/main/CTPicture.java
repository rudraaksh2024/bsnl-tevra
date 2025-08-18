package org.openxmlformats.schemas.presentationml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeStyle;

public interface CTPicture extends XmlObject {
    public static final DocumentFactory<CTPicture> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTExtensionListModify addNewExtLst();

    CTPictureNonVisual addNewNvPicPr();

    CTShapeProperties addNewSpPr();

    CTShapeStyle addNewStyle();

    CTBlipFillProperties getBlipFill();

    CTExtensionListModify getExtLst();

    CTPictureNonVisual getNvPicPr();

    CTShapeProperties getSpPr();

    CTShapeStyle getStyle();

    boolean isSetExtLst();

    boolean isSetStyle();

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setExtLst(CTExtensionListModify cTExtensionListModify);

    void setNvPicPr(CTPictureNonVisual cTPictureNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    void setStyle(CTShapeStyle cTShapeStyle);

    void unsetExtLst();

    void unsetStyle();

    static {
        DocumentFactory<CTPicture> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicture4f11type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
