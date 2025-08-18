package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTColorScale extends XmlObject {
    public static final DocumentFactory<CTColorScale> Factory;
    public static final SchemaType type;

    CTCfvo addNewCfvo();

    CTColor addNewColor();

    CTCfvo getCfvoArray(int i);

    CTCfvo[] getCfvoArray();

    List<CTCfvo> getCfvoList();

    CTColor getColorArray(int i);

    CTColor[] getColorArray();

    List<CTColor> getColorList();

    CTCfvo insertNewCfvo(int i);

    CTColor insertNewColor(int i);

    void removeCfvo(int i);

    void removeColor(int i);

    void setCfvoArray(int i, CTCfvo cTCfvo);

    void setCfvoArray(CTCfvo[] cTCfvoArr);

    void setColorArray(int i, CTColor cTColor);

    void setColorArray(CTColor[] cTColorArr);

    int sizeOfCfvoArray();

    int sizeOfColorArray();

    static {
        DocumentFactory<CTColorScale> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolorscale1a70type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
