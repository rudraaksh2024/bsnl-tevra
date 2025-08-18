package org.openxmlformats.schemas.drawingml.x2006.chart;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTManualLayout extends XmlObject {
    public static final DocumentFactory<CTManualLayout> Factory;
    public static final SchemaType type;

    CTExtensionList addNewExtLst();

    CTDouble addNewH();

    CTLayoutMode addNewHMode();

    CTLayoutTarget addNewLayoutTarget();

    CTDouble addNewW();

    CTLayoutMode addNewWMode();

    CTDouble addNewX();

    CTLayoutMode addNewXMode();

    CTDouble addNewY();

    CTLayoutMode addNewYMode();

    CTExtensionList getExtLst();

    CTDouble getH();

    CTLayoutMode getHMode();

    CTLayoutTarget getLayoutTarget();

    CTDouble getW();

    CTLayoutMode getWMode();

    CTDouble getX();

    CTLayoutMode getXMode();

    CTDouble getY();

    CTLayoutMode getYMode();

    boolean isSetExtLst();

    boolean isSetH();

    boolean isSetHMode();

    boolean isSetLayoutTarget();

    boolean isSetW();

    boolean isSetWMode();

    boolean isSetX();

    boolean isSetXMode();

    boolean isSetY();

    boolean isSetYMode();

    void setExtLst(CTExtensionList cTExtensionList);

    void setH(CTDouble cTDouble);

    void setHMode(CTLayoutMode cTLayoutMode);

    void setLayoutTarget(CTLayoutTarget cTLayoutTarget);

    void setW(CTDouble cTDouble);

    void setWMode(CTLayoutMode cTLayoutMode);

    void setX(CTDouble cTDouble);

    void setXMode(CTLayoutMode cTLayoutMode);

    void setY(CTDouble cTDouble);

    void setYMode(CTLayoutMode cTLayoutMode);

    void unsetExtLst();

    void unsetH();

    void unsetHMode();

    void unsetLayoutTarget();

    void unsetW();

    void unsetWMode();

    void unsetX();

    void unsetXMode();

    void unsetY();

    void unsetYMode();

    static {
        DocumentFactory<CTManualLayout> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmanuallayout872ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
