package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTable extends XmlObject {
    public static final DocumentFactory<CTTable> Factory;
    public static final SchemaType type;

    CTTableGrid addNewTblGrid();

    CTTableProperties addNewTblPr();

    CTTableRow addNewTr();

    CTTableGrid getTblGrid();

    CTTableProperties getTblPr();

    CTTableRow getTrArray(int i);

    CTTableRow[] getTrArray();

    List<CTTableRow> getTrList();

    CTTableRow insertNewTr(int i);

    boolean isSetTblPr();

    void removeTr(int i);

    void setTblGrid(CTTableGrid cTTableGrid);

    void setTblPr(CTTableProperties cTTableProperties);

    void setTrArray(int i, CTTableRow cTTableRow);

    void setTrArray(CTTableRow[] cTTableRowArr);

    int sizeOfTrArray();

    void unsetTblPr();

    static {
        DocumentFactory<CTTable> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttable5f3ftype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
