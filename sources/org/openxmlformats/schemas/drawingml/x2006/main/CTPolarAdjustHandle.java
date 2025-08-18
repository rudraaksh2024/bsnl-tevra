package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPolarAdjustHandle extends XmlObject {
    public static final DocumentFactory<CTPolarAdjustHandle> Factory;
    public static final SchemaType type;

    CTAdjPoint2D addNewPos();

    String getGdRefAng();

    String getGdRefR();

    Object getMaxAng();

    Object getMaxR();

    Object getMinAng();

    Object getMinR();

    CTAdjPoint2D getPos();

    boolean isSetGdRefAng();

    boolean isSetGdRefR();

    boolean isSetMaxAng();

    boolean isSetMaxR();

    boolean isSetMinAng();

    boolean isSetMinR();

    void setGdRefAng(String str);

    void setGdRefR(String str);

    void setMaxAng(Object obj);

    void setMaxR(Object obj);

    void setMinAng(Object obj);

    void setMinR(Object obj);

    void setPos(CTAdjPoint2D cTAdjPoint2D);

    void unsetGdRefAng();

    void unsetGdRefR();

    void unsetMaxAng();

    void unsetMaxR();

    void unsetMinAng();

    void unsetMinR();

    STGeomGuideName xgetGdRefAng();

    STGeomGuideName xgetGdRefR();

    STAdjAngle xgetMaxAng();

    STAdjCoordinate xgetMaxR();

    STAdjAngle xgetMinAng();

    STAdjCoordinate xgetMinR();

    void xsetGdRefAng(STGeomGuideName sTGeomGuideName);

    void xsetGdRefR(STGeomGuideName sTGeomGuideName);

    void xsetMaxAng(STAdjAngle sTAdjAngle);

    void xsetMaxR(STAdjCoordinate sTAdjCoordinate);

    void xsetMinAng(STAdjAngle sTAdjAngle);

    void xsetMinR(STAdjCoordinate sTAdjCoordinate);

    static {
        DocumentFactory<CTPolarAdjustHandle> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpolaradjusthandled0a6type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
