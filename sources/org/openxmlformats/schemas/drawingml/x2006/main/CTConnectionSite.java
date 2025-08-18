package org.openxmlformats.schemas.drawingml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTConnectionSite extends XmlObject {
    public static final DocumentFactory<CTConnectionSite> Factory;
    public static final SchemaType type;

    CTAdjPoint2D addNewPos();

    Object getAng();

    CTAdjPoint2D getPos();

    void setAng(Object obj);

    void setPos(CTAdjPoint2D cTAdjPoint2D);

    STAdjAngle xgetAng();

    void xsetAng(STAdjAngle sTAdjAngle);

    static {
        DocumentFactory<CTConnectionSite> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctconnectionsite6660type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
