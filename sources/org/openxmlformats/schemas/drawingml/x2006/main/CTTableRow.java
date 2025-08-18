package org.openxmlformats.schemas.drawingml.x2006.main;

import java.util.List;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

public interface CTTableRow extends XmlObject {
    public static final DocumentFactory<CTTableRow> Factory;
    public static final SchemaType type;

    CTOfficeArtExtensionList addNewExtLst();

    CTTableCell addNewTc();

    CTOfficeArtExtensionList getExtLst();

    Object getH();

    CTTableCell getTcArray(int i);

    CTTableCell[] getTcArray();

    List<CTTableCell> getTcList();

    CTTableCell insertNewTc(int i);

    boolean isSetExtLst();

    void removeTc(int i);

    void setExtLst(CTOfficeArtExtensionList cTOfficeArtExtensionList);

    void setH(Object obj);

    void setTcArray(int i, CTTableCell cTTableCell);

    void setTcArray(CTTableCell[] cTTableCellArr);

    int sizeOfTcArray();

    void unsetExtLst();

    STCoordinate xgetH();

    void xsetH(STCoordinate sTCoordinate);

    static {
        DocumentFactory<CTTableRow> documentFactory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "cttablerow4ac7type");
        Factory = documentFactory;
        type = documentFactory.getType();
    }
}
