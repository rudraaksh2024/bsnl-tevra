package com.microsoft.schemas.office.visio.x2012.main;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlInt;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface ConnectType extends XmlObject {
    public static final DocumentFactory<ConnectType> Factory;
    public static final SchemaType type;

    String getFromCell();

    int getFromPart();

    long getFromSheet();

    String getToCell();

    int getToPart();

    long getToSheet();

    boolean isSetFromCell();

    boolean isSetFromPart();

    boolean isSetToCell();

    boolean isSetToPart();

    void setFromCell(String str);

    void setFromPart(int i);

    void setFromSheet(long j);

    void setToCell(String str);

    void setToPart(int i);

    void setToSheet(long j);

    void unsetFromCell();

    void unsetFromPart();

    void unsetToCell();

    void unsetToPart();

    XmlString xgetFromCell();

    XmlInt xgetFromPart();

    XmlUnsignedInt xgetFromSheet();

    XmlString xgetToCell();

    XmlInt xgetToPart();

    XmlUnsignedInt xgetToSheet();

    void xsetFromCell(XmlString xmlString);

    void xsetFromPart(XmlInt xmlInt);

    void xsetFromSheet(XmlUnsignedInt xmlUnsignedInt);

    void xsetToCell(XmlString xmlString);

    void xsetToPart(XmlInt xmlInt);

    void xsetToSheet(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<ConnectType> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "connecttypeea41type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
