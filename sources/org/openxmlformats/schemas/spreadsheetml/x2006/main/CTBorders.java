package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTBorders extends XmlObject {
    public static final DocumentFactory<CTBorders> Factory;
    public static final SchemaType type;

    CTBorder addNewBorder();

    CTBorder getBorderArray(int i);

    CTBorder[] getBorderArray();

    List<CTBorder> getBorderList();

    long getCount();

    CTBorder insertNewBorder(int i);

    boolean isSetCount();

    void removeBorder(int i);

    void setBorderArray(int i, CTBorder cTBorder);

    void setBorderArray(CTBorder[] cTBorderArr);

    void setCount(long j);

    int sizeOfBorderArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTBorders> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctborders0d66type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
