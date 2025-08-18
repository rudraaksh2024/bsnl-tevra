package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCellStyleXfs extends XmlObject {
    public static final DocumentFactory<CTCellStyleXfs> Factory;
    public static final SchemaType type;

    CTXf addNewXf();

    long getCount();

    CTXf getXfArray(int i);

    CTXf[] getXfArray();

    List<CTXf> getXfList();

    CTXf insertNewXf(int i);

    boolean isSetCount();

    void removeXf(int i);

    void setCount(long j);

    void setXfArray(int i, CTXf cTXf);

    void setXfArray(CTXf[] cTXfArr);

    int sizeOfXfArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTCellStyleXfs> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellstylexfsa81ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
