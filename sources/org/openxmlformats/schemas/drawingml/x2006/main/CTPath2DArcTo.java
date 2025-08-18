package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTPath2DArcTo extends XmlObject {
    public static final DocumentFactory<CTPath2DArcTo> Factory;
    public static final SchemaType type;

    Object getHR();

    Object getStAng();

    Object getSwAng();

    Object getWR();

    void setHR(Object obj);

    void setStAng(Object obj);

    void setSwAng(Object obj);

    void setWR(Object obj);

    STAdjCoordinate xgetHR();

    STAdjAngle xgetStAng();

    STAdjAngle xgetSwAng();

    STAdjCoordinate xgetWR();

    void xsetHR(STAdjCoordinate sTAdjCoordinate);

    void xsetStAng(STAdjAngle sTAdjAngle);

    void xsetSwAng(STAdjAngle sTAdjAngle);

    void xsetWR(STAdjCoordinate sTAdjCoordinate);

    static {
        DocumentFactory<CTPath2DArcTo> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctpath2darctodaa7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
