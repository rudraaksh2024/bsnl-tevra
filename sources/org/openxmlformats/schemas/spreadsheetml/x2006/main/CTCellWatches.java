package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCellWatches extends XmlObject {
    public static final DocumentFactory<CTCellWatches> Factory;
    public static final SchemaType type;

    CTCellWatch addNewCellWatch();

    CTCellWatch getCellWatchArray(int i);

    CTCellWatch[] getCellWatchArray();

    List<CTCellWatch> getCellWatchList();

    CTCellWatch insertNewCellWatch(int i);

    void removeCellWatch(int i);

    void setCellWatchArray(int i, CTCellWatch cTCellWatch);

    void setCellWatchArray(CTCellWatch[] cTCellWatchArr);

    int sizeOfCellWatchArray();

    static {
        DocumentFactory<CTCellWatches> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcellwatches531atype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
