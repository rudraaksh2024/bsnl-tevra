package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableParts extends XmlObject {
    public static final DocumentFactory<CTTableParts> Factory;
    public static final SchemaType type;

    CTTablePart addNewTablePart();

    long getCount();

    CTTablePart getTablePartArray(int i);

    CTTablePart[] getTablePartArray();

    List<CTTablePart> getTablePartList();

    CTTablePart insertNewTablePart(int i);

    boolean isSetCount();

    void removeTablePart(int i);

    void setCount(long j);

    void setTablePartArray(int i, CTTablePart cTTablePart);

    void setTablePartArray(CTTablePart[] cTTablePartArr);

    int sizeOfTablePartArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTTableParts> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablepartsf6bbtype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
