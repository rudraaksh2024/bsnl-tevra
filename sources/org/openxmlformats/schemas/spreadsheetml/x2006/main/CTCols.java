package org.openxmlformats.schemas.spreadsheetml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTCols extends XmlObject {
    public static final DocumentFactory<CTCols> Factory;
    public static final SchemaType type;

    CTCol addNewCol();

    CTCol getColArray(int i);

    CTCol[] getColArray();

    List<CTCol> getColList();

    CTCol insertNewCol(int i);

    void removeCol(int i);

    void setColArray(int i, CTCol cTCol);

    void setColArray(CTCol[] cTColArr);

    int sizeOfColArray();

    static {
        DocumentFactory<CTCols> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctcols627ctype");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
