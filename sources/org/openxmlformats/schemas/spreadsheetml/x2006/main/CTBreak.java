package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBreak extends XmlObject {
    public static final DocumentFactory<CTBreak> Factory;
    public static final SchemaType type;

    long getId();

    boolean getMan();

    long getMax();

    long getMin();

    boolean getPt();

    boolean isSetId();

    boolean isSetMan();

    boolean isSetMax();

    boolean isSetMin();

    boolean isSetPt();

    void setId(long j);

    void setMan(boolean z);

    void setMax(long j);

    void setMin(long j);

    void setPt(boolean z);

    void unsetId();

    void unsetMan();

    void unsetMax();

    void unsetMin();

    void unsetPt();

    XmlUnsignedInt xgetId();

    XmlBoolean xgetMan();

    XmlUnsignedInt xgetMax();

    XmlUnsignedInt xgetMin();

    XmlBoolean xgetPt();

    void xsetId(XmlUnsignedInt xmlUnsignedInt);

    void xsetMan(XmlBoolean xmlBoolean);

    void xsetMax(XmlUnsignedInt xmlUnsignedInt);

    void xsetMin(XmlUnsignedInt xmlUnsignedInt);

    void xsetPt(XmlBoolean xmlBoolean);

    static {
        DocumentFactory<CTBreak> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctbreak815etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
