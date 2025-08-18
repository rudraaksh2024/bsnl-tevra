package org.openxmlformats.schemas.drawingml.x2006.picture;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualPictureProperties;

public interface CTPictureNonVisual extends XmlObject {
    public static final DocumentFactory<CTPictureNonVisual> Factory;
    public static final SchemaType type;

    CTNonVisualPictureProperties addNewCNvPicPr();

    CTNonVisualDrawingProps addNewCNvPr();

    CTNonVisualPictureProperties getCNvPicPr();

    CTNonVisualDrawingProps getCNvPr();

    void setCNvPicPr(CTNonVisualPictureProperties cTNonVisualPictureProperties);

    void setCNvPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    static {
        DocumentFactory<CTPictureNonVisual> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpicturenonvisual05adtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
