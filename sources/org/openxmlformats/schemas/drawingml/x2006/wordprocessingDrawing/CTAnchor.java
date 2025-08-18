package org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualGraphicFrameProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPoint2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;

public interface CTAnchor extends XmlObject {
    public static final DocumentFactory<CTAnchor> Factory;
    public static final SchemaType type;

    CTNonVisualGraphicFrameProperties addNewCNvGraphicFramePr();

    CTNonVisualDrawingProps addNewDocPr();

    CTEffectExtent addNewEffectExtent();

    CTPositiveSize2D addNewExtent();

    CTGraphicalObject addNewGraphic();

    CTPosH addNewPositionH();

    CTPosV addNewPositionV();

    CTPoint2D addNewSimplePos();

    CTWrapNone addNewWrapNone();

    CTWrapSquare addNewWrapSquare();

    CTWrapThrough addNewWrapThrough();

    CTWrapTight addNewWrapTight();

    CTWrapTopBottom addNewWrapTopAndBottom();

    boolean getAllowOverlap();

    boolean getBehindDoc();

    CTNonVisualGraphicFrameProperties getCNvGraphicFramePr();

    long getDistB();

    long getDistL();

    long getDistR();

    long getDistT();

    CTNonVisualDrawingProps getDocPr();

    CTEffectExtent getEffectExtent();

    CTPositiveSize2D getExtent();

    CTGraphicalObject getGraphic();

    boolean getHidden();

    boolean getLayoutInCell();

    boolean getLocked();

    CTPosH getPositionH();

    CTPosV getPositionV();

    long getRelativeHeight();

    CTPoint2D getSimplePos();

    boolean getSimplePos2();

    CTWrapNone getWrapNone();

    CTWrapSquare getWrapSquare();

    CTWrapThrough getWrapThrough();

    CTWrapTight getWrapTight();

    CTWrapTopBottom getWrapTopAndBottom();

    boolean isSetCNvGraphicFramePr();

    boolean isSetDistB();

    boolean isSetDistL();

    boolean isSetDistR();

    boolean isSetDistT();

    boolean isSetEffectExtent();

    boolean isSetHidden();

    boolean isSetSimplePos2();

    boolean isSetWrapNone();

    boolean isSetWrapSquare();

    boolean isSetWrapThrough();

    boolean isSetWrapTight();

    boolean isSetWrapTopAndBottom();

    void setAllowOverlap(boolean z);

    void setBehindDoc(boolean z);

    void setCNvGraphicFramePr(CTNonVisualGraphicFrameProperties cTNonVisualGraphicFrameProperties);

    void setDistB(long j);

    void setDistL(long j);

    void setDistR(long j);

    void setDistT(long j);

    void setDocPr(CTNonVisualDrawingProps cTNonVisualDrawingProps);

    void setEffectExtent(CTEffectExtent cTEffectExtent);

    void setExtent(CTPositiveSize2D cTPositiveSize2D);

    void setGraphic(CTGraphicalObject cTGraphicalObject);

    void setHidden(boolean z);

    void setLayoutInCell(boolean z);

    void setLocked(boolean z);

    void setPositionH(CTPosH cTPosH);

    void setPositionV(CTPosV cTPosV);

    void setRelativeHeight(long j);

    void setSimplePos(CTPoint2D cTPoint2D);

    void setSimplePos2(boolean z);

    void setWrapNone(CTWrapNone cTWrapNone);

    void setWrapSquare(CTWrapSquare cTWrapSquare);

    void setWrapThrough(CTWrapThrough cTWrapThrough);

    void setWrapTight(CTWrapTight cTWrapTight);

    void setWrapTopAndBottom(CTWrapTopBottom cTWrapTopBottom);

    void unsetCNvGraphicFramePr();

    void unsetDistB();

    void unsetDistL();

    void unsetDistR();

    void unsetDistT();

    void unsetEffectExtent();

    void unsetHidden();

    void unsetSimplePos2();

    void unsetWrapNone();

    void unsetWrapSquare();

    void unsetWrapThrough();

    void unsetWrapTight();

    void unsetWrapTopAndBottom();

    XmlBoolean xgetAllowOverlap();

    XmlBoolean xgetBehindDoc();

    STWrapDistance xgetDistB();

    STWrapDistance xgetDistL();

    STWrapDistance xgetDistR();

    STWrapDistance xgetDistT();

    XmlBoolean xgetHidden();

    XmlBoolean xgetLayoutInCell();

    XmlBoolean xgetLocked();

    XmlUnsignedInt xgetRelativeHeight();

    XmlBoolean xgetSimplePos2();

    void xsetAllowOverlap(XmlBoolean xmlBoolean);

    void xsetBehindDoc(XmlBoolean xmlBoolean);

    void xsetDistB(STWrapDistance sTWrapDistance);

    void xsetDistL(STWrapDistance sTWrapDistance);

    void xsetDistR(STWrapDistance sTWrapDistance);

    void xsetDistT(STWrapDistance sTWrapDistance);

    void xsetHidden(XmlBoolean xmlBoolean);

    void xsetLayoutInCell(XmlBoolean xmlBoolean);

    void xsetLocked(XmlBoolean xmlBoolean);

    void xsetRelativeHeight(XmlUnsignedInt xmlUnsignedInt);

    void xsetSimplePos2(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTAnchor> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctanchorff8atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
