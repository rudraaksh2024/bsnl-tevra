package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedByte;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCol extends XmlObject {
    public static final DocumentFactory<CTCol> Factory;
    public static final SchemaType type;

    boolean getBestFit();

    boolean getCollapsed();

    boolean getCustomWidth();

    boolean getHidden();

    long getMax();

    long getMin();

    short getOutlineLevel();

    boolean getPhonetic();

    long getStyle();

    double getWidth();

    boolean isSetBestFit();

    boolean isSetCollapsed();

    boolean isSetCustomWidth();

    boolean isSetHidden();

    boolean isSetOutlineLevel();

    boolean isSetPhonetic();

    boolean isSetStyle();

    boolean isSetWidth();

    void setBestFit(boolean z);

    void setCollapsed(boolean z);

    void setCustomWidth(boolean z);

    void setHidden(boolean z);

    void setMax(long j);

    void setMin(long j);

    void setOutlineLevel(short s);

    void setPhonetic(boolean z);

    void setStyle(long j);

    void setWidth(double d);

    void unsetBestFit();

    void unsetCollapsed();

    void unsetCustomWidth();

    void unsetHidden();

    void unsetOutlineLevel();

    void unsetPhonetic();

    void unsetStyle();

    void unsetWidth();

    XmlBoolean xgetBestFit();

    XmlBoolean xgetCollapsed();

    XmlBoolean xgetCustomWidth();

    XmlBoolean xgetHidden();

    XmlUnsignedInt xgetMax();

    XmlUnsignedInt xgetMin();

    XmlUnsignedByte xgetOutlineLevel();

    XmlBoolean xgetPhonetic();

    XmlUnsignedInt xgetStyle();

    XmlDouble xgetWidth();

    void xsetBestFit(XmlBoolean xmlBoolean);

    void xsetCollapsed(XmlBoolean xmlBoolean);

    void xsetCustomWidth(XmlBoolean xmlBoolean);

    void xsetHidden(XmlBoolean xmlBoolean);

    void xsetMax(XmlUnsignedInt xmlUnsignedInt);

    void xsetMin(XmlUnsignedInt xmlUnsignedInt);

    void xsetOutlineLevel(XmlUnsignedByte xmlUnsignedByte);

    void xsetPhonetic(XmlBoolean xmlBoolean);

    void xsetStyle(XmlUnsignedInt xmlUnsignedInt);

    void xsetWidth(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTCol> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcola95ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
