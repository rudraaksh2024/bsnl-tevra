package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;

public interface CTInline extends XmlObject {
    public static final DocumentFactory<CTInline> Factory;
    public static final SchemaType type;

    CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr();

    CTNonVisualDrawingProps addNewDocPr();

    CTEffectExtent addNewEffectExtent();

    CTPositiveSize2D addNewExtent();

    CTGraphicalObject addNewGraphic();

    CTNonVisualGraphicFrameProperties getCNvGraphicFramePr();

    long getDistB();

    long getDistL();

    long getDistR();

    long getDistT();

    CTNonVisualDrawingProps getDocPr();

    CTEffectExtent getEffectExtent();

    CTPositiveSize2D getExtent();

    CTGraphicalObject getGraphic();

    boolean isSetCNvGraphicFramePr();

    boolean isSetDistB();

    boolean isSetDistL();

    boolean isSetDistR();

    boolean isSetDistT();

    boolean isSetEffectExtent();

    void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties);

    void setDistB(long j);

    void setDistL(long j);

    void setDistR(long j);

    void setDistT(long j);

    void setDocPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setEffectExtent(CTEffectExtent cTEffectExtent);

    void setExtent(CTPositiveSize2D cTPositiveSize2D);

    void setGraphic(CTGraphicalObject cTGraphicalObject);

    void unsetCNvGraphicFramePr();

    void unsetDistB();

    void unsetDistL();

    void unsetDistR();

    void unsetDistT();

    void unsetEffectExtent();

    STWrapDistance xgetDistB();

    STWrapDistance xgetDistL();

    STWrapDistance xgetDistR();

    STWrapDistance xgetDistT();

    void xsetDistB(STWrapDistance sTWrapDistance);

    void xsetDistL(STWrapDistance sTWrapDistance);

    void xsetDistR(STWrapDistance sTWrapDistance);

    void xsetDistT(STWrapDistance sTWrapDistance);

    static {
        DocumentFactory<CTInline> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctinline5726type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
