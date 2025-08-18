package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTXYAdjustHandle extends XmlObject {
    public static final DocumentFactory<CTXYAdjustHandle> Factory;
    public static final SchemaType type;

    CTAdjPoint2D addNewPos();

    String getGdRefX();

    String getGdRefY();

    Object getMaxX();

    Object getMaxY();

    Object getMinX();

    Object getMinY();

    CTAdjPoint2D getPos();

    boolean isSetGdRefX();

    boolean isSetGdRefY();

    boolean isSetMaxX();

    boolean isSetMaxY();

    boolean isSetMinX();

    boolean isSetMinY();

    void setGdRefX(String str);

    void setGdRefY(String str);

    void setMaxX(Object obj);

    void setMaxY(Object obj);

    void setMinX(Object obj);

    void setMinY(Object obj);

    void setPos(CTAdjPoint2D cTAdjPoint2D);

    void unsetGdRefX();

    void unsetGdRefY();

    void unsetMaxX();

    void unsetMaxY();

    void unsetMinX();

    void unsetMinY();

    STGeomGuideName xgetGdRefX();

    STGeomGuideName xgetGdRefY();

    STAdjCoordinate xgetMaxX();

    STAdjCoordinate xgetMaxY();

    STAdjCoordinate xgetMinX();

    STAdjCoordinate xgetMinY();

    void xsetGdRefX(STGeomGuideName sTGeomGuideName);

    void xsetGdRefY(STGeomGuideName sTGeomGuideName);

    void xsetMaxX(STAdjCoordinate sTAdjCoordinate);

    void xsetMaxY(STAdjCoordinate sTAdjCoordinate);

    void xsetMinX(STAdjCoordinate sTAdjCoordinate);

    void xsetMinY(STAdjCoordinate sTAdjCoordinate);

    static {
        DocumentFactory<CTXYAdjustHandle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctxyadjusthandlefaf3type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
