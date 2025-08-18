package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDataBar extends XmlObject {
    public static final DocumentFactory<CTDataBar> Factory;
    public static final SchemaType type;

    CTCfvo addNewCfvo();

    CTColor addNewColor();

    CTCfvo getCfvoArray(int i);

    CTCfvo[] getCfvoArray();

    List<CTCfvo> getCfvoList();

    CTColor getColor();

    long getMaxLength();

    long getMinLength();

    boolean getShowValue();

    CTCfvo insertNewCfvo(int i);

    boolean isSetMaxLength();

    boolean isSetMinLength();

    boolean isSetShowValue();

    void removeCfvo(int i);

    void setCfvoArray(int i, CTCfvo cTCfvo);

    void setCfvoArray(CTCfvo[] cTCfvoArr);

    void setColor(CTColor cTColor);

    void setMaxLength(long j);

    void setMinLength(long j);

    void setShowValue(boolean z);

    int sizeOfCfvoArray();

    void unsetMaxLength();

    void unsetMinLength();

    void unsetShowValue();

    XmlUnsignedInt xgetMaxLength();

    XmlUnsignedInt xgetMinLength();

    XmlBoolean xgetShowValue();

    void xsetMaxLength(XmlUnsignedInt xmlUnsignedInt);

    void xsetMinLength(XmlUnsignedInt xmlUnsignedInt);

    void xsetShowValue(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTDataBar> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdatabar4128type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
