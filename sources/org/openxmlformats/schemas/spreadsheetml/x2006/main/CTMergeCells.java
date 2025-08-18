package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlUnsignedInt;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTMergeCells extends XmlObject {
    public static final DocumentFactory<CTMergeCells> Factory;
    public static final SchemaType type;

    CTMergeCell addNewMergeCell();

    long getCount();

    CTMergeCell getMergeCellArray(int i);

    CTMergeCell[] getMergeCellArray();

    List<CTMergeCell> getMergeCellList();

    CTMergeCell insertNewMergeCell(int i);

    boolean isSetCount();

    void removeMergeCell(int i);

    void setCount(long j);

    void setMergeCellArray(int i, CTMergeCell cTMergeCell);

    void setMergeCellArray(CTMergeCell[] cTMergeCellArr);

    int sizeOfMergeCellArray();

    void unsetCount();

    XmlUnsignedInt xgetCount();

    void xsetCount(XmlUnsignedInt xmlUnsignedInt);

    static {
        DocumentFactory<CTMergeCells> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctmergecells1242type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
