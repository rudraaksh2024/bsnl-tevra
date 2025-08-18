package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTColor extends XmlObject {
    public static final DocumentFactory<CTColor> Factory;
    public static final SchemaType type;

    boolean getAuto();

    long getIndexed();

    byte[] getRgb();

    long getTheme();

    double getTint();

    boolean isSetAuto();

    boolean isSetIndexed();

    boolean isSetRgb();

    boolean isSetTheme();

    boolean isSetTint();

    void setAuto(boolean z);

    void setIndexed(long j);

    void setRgb(byte[] bArr);

    void setTheme(long j);

    void setTint(double d);

    void unsetAuto();

    void unsetIndexed();

    void unsetRgb();

    void unsetTheme();

    void unsetTint();

    XmlBoolean xgetAuto();

    XmlUnsignedInt xgetIndexed();

    STUnsignedIntHex xgetRgb();

    XmlUnsignedInt xgetTheme();

    XmlDouble xgetTint();

    void xsetAuto(XmlBoolean xmlBoolean);

    void xsetIndexed(XmlUnsignedInt xmlUnsignedInt);

    void xsetRgb(STUnsignedIntHex sTUnsignedIntHex);

    void xsetTheme(XmlUnsignedInt xmlUnsignedInt);

    void xsetTint(XmlDouble xmlDouble);

    static {
        DocumentFactory<CTColor> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcolord2c2type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
