package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTExternalRow extends XmlObject {
    public static final DocumentFactory<CTExternalRow> Factory;
    public static final SchemaType type;

    CTExternalCell addNewCell();

    CTExternalCell getCellArray(int i);

    CTExternalCell[] getCellArray();

    List<CTExternalCell> getCellList();

    long getR();

    CTExternalCell insertNewCell(int i);

    void removeCell(int i);

    void setCellArray(int i, CTExternalCell cTExternalCell);

    void setCellArray(CTExternalCell[] cTExternalCellArr);

    void setR(long j);

    int sizeOfCellArray();

    XmlUnsignedInt xgetR();

    void xsetR(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTExternalRow> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctexternalrowa22etype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
