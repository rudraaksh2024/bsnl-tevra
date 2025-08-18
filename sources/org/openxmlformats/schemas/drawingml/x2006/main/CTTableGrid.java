package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableGrid extends XmlObject {
    public static final DocumentFactory<CTTableGrid> Factory;
    public static final SchemaType type;

    CTTableCol addNewGridCol();

    CTTableCol getGridColArray(int i);

    CTTableCol[] getGridColArray();

    List<CTTableCol> getGridColList();

    CTTableCol insertNewGridCol(int i);

    void removeGridCol(int i);

    void setGridColArray(int i, CTTableCol cTTableCol);

    void setGridColArray(CTTableCol[] cTTableColArr);

    int sizeOfGridColArray();

    static {
        DocumentFactory<CTTableGrid> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablegrid69a5type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
