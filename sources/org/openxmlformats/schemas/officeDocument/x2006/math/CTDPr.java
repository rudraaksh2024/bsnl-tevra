package org.openxmlformats.schemas.officeDocument.x2006.math;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDPr extends XmlObject {
    public static final DocumentFactory<CTDPr> Factory;
    public static final SchemaType type;

    CTChar addNewBegChr();

    CTCtrlPr addNewCtrlPr();

    CTChar addNewEndChr();

    CTOnOff addNewGrow();

    CTChar addNewSepChr();

    CTShp addNewShp();

    CTChar getBegChr();

    CTCtrlPr getCtrlPr();

    CTChar getEndChr();

    CTOnOff getGrow();

    CTChar getSepChr();

    CTShp getShp();

    boolean isSetBegChr();

    boolean isSetCtrlPr();

    boolean isSetEndChr();

    boolean isSetGrow();

    boolean isSetSepChr();

    boolean isSetShp();

    void setBegChr(CTChar cTChar);

    void setCtrlPr(CTCtrlPr cTCtrlPr);

    void setEndChr(CTChar cTChar);

    void setGrow(CTOnOff cTOnOff);

    void setSepChr(CTChar cTChar);

    void setShp(CTShp cTShp);

    void unsetBegChr();

    void unsetCtrlPr();

    void unsetEndChr();

    void unsetGrow();

    void unsetSepChr();

    void unsetShp();

    static {
        DocumentFactory<CTDPr> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdpr2596type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
