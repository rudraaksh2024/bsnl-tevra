package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTDxfs extends XmlObject {
    public static final DocumentFactory<CTDxfs> Factory;
    public static final SchemaType type;

    CTDxf addNewDxf();

    long getCount();

    CTDxf getDxfArray(int i);

    CTDxf[] getDxfArray();

    List<CTDxf> getDxfList();

    CTDxf insertNewDxf(int i);

    boolean isSetCount();

    void removeDxf(int i);

    void setCount(long j);

    void setDxfArray(int i, CTDxf cTDxf);

    void setDxfArray(CTDxf[] cTDxfArr);

    int sizeOfDxfArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTDxfs> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctdxfsb26atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
