package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTView3D extends XmlObject {
    public static final DocumentFactory<CTView3D> Factory;
    public static final SchemaType type;

    CTDepthPercent addNewDepthPercent();

    CTExtensionList addNewExtLst();

    CTHPercent addNewHPercent();

    CTPerspective addNewPerspective();

    CTBoolean addNewRAngAx();

    CTRotX addNewRotX();

    CTRotY addNewRotY();

    CTDepthPercent getDepthPercent();

    CTExtensionList getExtLst();

    CTHPercent getHPercent();

    CTPerspective getPerspective();

    CTBoolean getRAngAx();

    CTRotX getRotX();

    CTRotY getRotY();

    boolean isSetDepthPercent();

    boolean isSetExtLst();

    boolean isSetHPercent();

    boolean isSetPerspective();

    boolean isSetRAngAx();

    boolean isSetRotX();

    boolean isSetRotY();

    void setDepthPercent(CTDepthPercent cTDepthPercent);

    void setExtLst(CTExtensionList cTExtensionList);

    void setHPercent(CTHPercent cTHPercent);

    void setPerspective(CTPerspective cTPerspective);

    void setRAngAx(CTBoolean cTBoolean);

    void setRotX(CTRotX cTRotX);

    void setRotY(CTRotY cTRotY);

    void unsetDepthPercent();

    void unsetExtLst();

    void unsetHPercent();

    void unsetPerspective();

    void unsetRAngAx();

    void unsetRotX();

    void unsetRotY();

    static {
        DocumentFactory<CTView3D> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctview3daf66type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
