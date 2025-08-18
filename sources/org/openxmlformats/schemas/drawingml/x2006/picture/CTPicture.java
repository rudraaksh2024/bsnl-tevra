package org.openxmlformats.schemas.drawingml.x2006.picture;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTBlipFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTShapeProperties;

public interface CTPicture extends XmlObject {
    public static final DocumentFactory<CTPicture> Factory;
    public static final SchemaType type;

    CTBlipFillProperties addNewBlipFill();

    CTPictureNonVisual addNewNvPicPr();

    CTShapeProperties addNewSpPr();

    CTBlipFillProperties getBlipFill();

    CTPictureNonVisual getNvPicPr();

    CTShapeProperties getSpPr();

    void setBlipFill(CTBlipFillProperties cTBlipFillProperties);

    void setNvPicPr(CTPictureNonVisual cTPictureNonVisual);

    void setSpPr(CTShapeProperties cTShapeProperties);

    static {
        DocumentFactory<CTPicture> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicture1d48type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
